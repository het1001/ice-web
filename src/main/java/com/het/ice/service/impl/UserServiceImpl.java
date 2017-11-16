package com.het.ice.service.impl;

import com.het.ice.dao.*;
import com.het.ice.dao.model.*;
import com.het.ice.dao.query.AuthCodeQuery;
import com.het.ice.dao.query.UserQuery;
import com.het.ice.enums.LobWhereUsedEnum;
import com.het.ice.enums.UserOperateEnum;
import com.het.ice.enums.UserStateEnum;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import com.het.ice.model.UserAuthCode;
import com.het.ice.model.UserInfo;
import com.het.ice.model.UserPhoneInfo;
import com.het.ice.service.UserService;
import com.het.ice.service.conv.UserAuthCodeConvert;
import com.het.ice.service.conv.UserConvert;
import com.het.ice.service.conv.UserInfoConvert;
import com.het.ice.service.conv.UserPhoneInfoConvert;
import com.het.ice.service.exception.BizException;
import com.het.ice.service.exception.ParamCheckException;
import com.het.ice.service.exception.ResultCodeEnum;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.*;
import com.het.ice.web.request.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author houenteng
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Resource
	private UserDAO userDao;

	@Resource
	private UserAuthCodeDAO userAuthCodeDAO;

	@Resource
	private UserPhoneInfoDAO userPhoneInfoDAO;

	@Resource
	private UserInfoDAO userInfoDAO;

	@Resource
	private UserOperateTraceDAO userOperateTraceDAO;

	@Resource
	private UserDeliveryAddrDAO userDeliveryAddrDAO;

	@Resource
	private Template template;

	@Override
	public Result<Void> authAndInitUser(final UserGetAuthCodeRequest request) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(request.getPhone(), 32, "手机号");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByUserName(request.getPhone(), UserTypeEnum.NORMAL.getCode());
				// 用户为null，则为新用户
				if (userDO == null) {
					/**
					 * 1.1 用户不存在，则添加user
					 */
					User user = new User();
					user.setUserName(request.getPhone());
					user.setPhone(request.getPhone());
					user.setType(UserTypeEnum.NORMAL);
					user.setState(UserStateEnum.CREATE);
					user.setProperty(new JSONObject());
					userDO = UserConvert.conv(user);
					userDao.insert(userDO);

					/**
					 * 1.2 添加用户设备信息
					 */
					UserPhoneInfo info = new UserPhoneInfo();
					info.setUserId(userDO.getId());
					info.setPhone(request.getPhone());
					info.setManufacturer(request.getManufacturer());
					info.setModel(request.getModel());
					info.setDeviceUniqueId(request.getDeviceUniqueId());
					info.setDeviceId(request.getDeviceId());
					info.setDeviceName(request.getDeviceName());
					info.setSysName(request.getSysName());
					info.setSysVersion(request.getSysVersion());
					info.setImei(request.getImei());
					info.setAppVersion(request.getAppVersion());
					userPhoneInfoDAO.insert(UserPhoneInfoConvert.conv(info));

					/**
					 * 1.3 生成验证码，并发送给用户，发送失败则抛出异常
					 */
					sendCode(request.getPhone(), request.getDeviceUniqueId());

					UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
					userOperateTraceDO.setPhone(request.getPhone());
					userOperateTraceDO.setOperate(UserOperateEnum.CREATE.getCode());
					userOperateTraceDO.setMemo("用户注册-获取验证码");
					userOperateTraceDAO.insert(userOperateTraceDO);
				} else {
					/** 2.1 判断用户状态，CREATE & AUTHED 为可通过用户 */
					if (UserStateEnum.getByCode(userDO.getState()) == UserStateEnum.CREATE
							|| UserStateEnum.getByCode(userDO.getState()) == UserStateEnum.AUTHED) {

						/**
						 * 2.2 查询当天该用户发送验证码有没有超过5次
						 */
						assertSendAuthCode(request.getPhone(), request.getDeviceUniqueId());

						UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
						userOperateTraceDO.setPhone(request.getPhone());
						userOperateTraceDO.setOperate(UserOperateEnum.GET_AUTH_CODE.getCode());
						userOperateTraceDO.setMemo("获取验证码");
						userOperateTraceDAO.insert(userOperateTraceDO);
					} else {
						throw new BizException(ResultCodeEnum.USER_AUTHED);
					}
				}
			}
		});
	}

	@Override
	public Result<Void> authCode(final UserAuthRequest request) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(request.getPhone(), 32, "手机号");
				AssertUtil.lengthThan(request.getAuthCode(), 6, "验证码");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByUserName(request.getPhone(), UserTypeEnum.NORMAL.getCode());
				if (userDO == null) {
					/**
					 * 1. 用户为null，则抛出异常
					 */
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				} else {
					/**
					 * 2.1 用户为添加状态，则校验验证码
					 */
					if (UserStateEnum.getByCode(userDO.getState()) == UserStateEnum.CREATE
							|| UserStateEnum.getByCode(userDO.getState()) == UserStateEnum.AUTHED) {
						UserAuthCodeDO userAuthCodeDO = userAuthCodeDAO.getByPhoneAndCode(request.getPhone(), request.getAuthCode());
						/**
						 * 2.2 存在验证码则校验通过
						 */
						if (userAuthCodeDO != null) {
							userDO.setState(UserStateEnum.AUTHED.getCode());
							userDao.update(userDO);

							userAuthCodeDO.setUsed(1);
							userAuthCodeDO.setUseTime(new Date());
							userAuthCodeDAO.update(userAuthCodeDO);

							UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
							userOperateTraceDO.setPhone(request.getPhone());
							userOperateTraceDO.setOperate(UserOperateEnum.AUTH.getCode());
							userOperateTraceDO.setMemo("校验验证码通过");
							userOperateTraceDAO.insert(userOperateTraceDO);
						} else {
							throw new BizException(ResultCodeEnum.AUTH_FAILED);
						}
					} else {
						if (StringUtils.isBlank(request.getType())) {
							throw new BizException(ResultCodeEnum.USER_AUTHED);
						}
					}
				}
			}
		});
	}

	@Override
	public Result<Void> setPassWord(final UserSetPassWordRequest request) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(request.getPhone(), 32, "手机号");
				AssertUtil.lengthThan(request.getPassWord(), 32, "密码");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByUserName(request.getPhone(), UserTypeEnum.NORMAL.getCode());
				/**
				 * 用户为null，则抛出异常
				 */
				if (userDO == null) {
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				} else {
					if (UserStateEnum.getByCode(userDO.getState()) == UserStateEnum.AUTHED) {
						userDO.setPassWord(MD5Util.encry(request.getPassWord()));
						userDO.setState(UserStateEnum.PASSED.getCode());
						userDao.update(userDO);

						UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
						userOperateTraceDO.setPhone(request.getPhone());
						userOperateTraceDO.setOperate(UserOperateEnum.SET_PASSWORD.getCode());
						userOperateTraceDO.setMemo("设置密码");
						userOperateTraceDAO.insert(userOperateTraceDO);
					} else {
						if (StringUtils.isBlank(request.getType())) {
							throw new BizException(ResultCodeEnum.USER_NOT_AUTHED);
						}

						userDO.setPassWord(MD5Util.encry(request.getPassWord()));
						userDao.update(userDO);

						UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
						userOperateTraceDO.setPhone(request.getPhone());
						userOperateTraceDO.setOperate(UserOperateEnum.RE_SET_PASSWORD.getCode());
						userOperateTraceDO.setMemo("重置密码");
						userOperateTraceDAO.insert(userOperateTraceDO);
					}
				}
			}
		});
	}

	/**
	 *
	 * @param phone
	 */
	private void assertSendAuthCode(String phone, String deviceUniqueId) {
		AuthCodeQuery authCodeQuery = new AuthCodeQuery();
		authCodeQuery.setPhone(phone);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		authCodeQuery.setStartTime(calendar1.getTime());

		Calendar calendar2 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY, 23);
		calendar1.set(Calendar.MINUTE, 59);
		calendar1.set(Calendar.SECOND, 59);
		authCodeQuery.setEndTime(calendar2.getTime());

		int count = userAuthCodeDAO.getCountBetween(authCodeQuery);
		if (count >= 5) {
			/**
			 * 2.2.1 大于等于5次
			 */
			throw new BizException(ResultCodeEnum.AUTH_CODE_SEND_TIMES_OUT);
		} else {
			/**
			 * 2.2.2 判断一下最近的验证码有没有超过一分钟，避免有人恶意攻击
			 */
			List<UserAuthCodeDO> userAuthCodeDOs = userAuthCodeDAO.queryLastOneMinute(phone);
			if (CollectionUtils.isEmpty(userAuthCodeDOs)) {
				sendCode(phone, deviceUniqueId);
			} else {
				throw new BizException(ResultCodeEnum.AUTH_CODE_SEND_TOO_FAST);
			}
		}
	}

	/**
	 *
	 * @param phone
	 * @return
	 */
	private void sendCode(String phone, String deviceUniqueId) {
		String authCode = SmsUtil.createAuthCode();
		UserAuthCode userAuthCode = new UserAuthCode();
		userAuthCode.setPhone(phone);
		userAuthCode.setCode(authCode);
		userAuthCode.setDeviceUniqueId(deviceUniqueId);
		userAuthCodeDAO.insert(UserAuthCodeConvert.conv(userAuthCode));
		if (!SmsUtil.send(phone, authCode)) {
			throw new BizException(ResultCodeEnum.AUTH_CODE_SEND_FAILED);
		}
	}

	/**
	 *
	 * @param name
	 * @param pw
	 * @param isLogin
	 * @return
	 */
	@Override
	public Result<User> bizLogin(final String name, final String pw, final boolean isLogin) {
		return template.complete(new ResultCallback<User>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(name, 32, "用户名");
				AssertUtil.lengthThan(pw, 32, "密码");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getUser(name, MD5Util.encry(pw), UserTypeEnum.BIZ.getCode());
				User user = UserConvert.conv(userDO);

				// 设置最后登录时间
				if (userDO != null && isLogin) {
					userDO.setLastLoginTime(new Date());
					userDao.update(userDO);
				}

				returnValue = user;
			}

		});
	}

	/**
	 *
	 *
	 * @param request
	 * @return
	 */
	@Override
	public Result<User> normalLogin(final UserLoginRequest request) {
		return template.complete(new ResultCallback<User>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(request.getUserName(), 32, "手机号");
				AssertUtil.lengthThan(request.getPassWord(), 32, "密码");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByUserName(request.getUserName(), UserTypeEnum.NORMAL.getCode());
				if (userDO == null) {
					// 用户名不存在
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				} else {
					if (UserStateEnum.getByCode(userDO.getState()) == UserStateEnum.CREATE) {
						throw new BizException(ResultCodeEnum.USER_NOT_COMPLEAT_REGISTER);
					} else if (UserStateEnum.getByCode(userDO.getState()) == UserStateEnum.AUTHED) {
						throw new BizException(ResultCodeEnum.USER_NOT_SET_PASSWORD);
					}

					if (!StringUtils.equals(MD5Util.encry(request.getPassWord()), userDO.getPassWord())) {
						// 密码错误
						throw new BizException(ResultCodeEnum.PWD_CHECK_FAILED);
					} else {
						// 生成token
						String token = UUIDUtil.getCode();

						userDO.setLastLoginTime(new Date());
						userDO.setToken(token);
						userDao.update(userDO);
						returnValue = UserConvert.conv(userDO);

						UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
						userOperateTraceDO.setPhone(request.getUserName());

						JSONObject jsonObject = new JSONObject();
						jsonObject.put("position", request.getPosition());
						jsonObject.put("device", request.getDevice());
						userOperateTraceDO.setNewInfo(jsonObject.toString());
						if (StringUtils.equals(request.getType(), "AUTO")) {
							userOperateTraceDO.setOperate(UserOperateEnum.AUTO_LOGIN.getCode());
						} else {
							userOperateTraceDO.setOperate(UserOperateEnum.LOGIN.getCode());
						}
						userOperateTraceDO.setMemo("登录 token=" + token);
						userOperateTraceDAO.insert(userOperateTraceDO);
					}
				}
			}

		});
	}

	@Override
	public Result<Void> getAuthCode(UserGetAuthCodeRequest request) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(request.getPhone(), 32, "手机号");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByUserName(request.getPhone(), UserTypeEnum.NORMAL.getCode());
				// 用户为null，则为新用户
				if (userDO == null) {
					// 用户名不存在
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				} else {
					assertSendAuthCode(request.getPhone(), request.getDeviceUniqueId());

					UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
					userOperateTraceDO.setPhone(request.getPhone());
					userOperateTraceDO.setOperate(UserOperateEnum.GET_AUTH_CODE.getCode());
					userOperateTraceDO.setMemo("获取校验码-非第一次校验");
					userOperateTraceDAO.insert(userOperateTraceDO);
				}
			}
		});
	}

	@Override
	public Result<Void> completeUserInfo(UserInfoRequest request) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(request.getPhone(), 32, "手机号");
				AssertUtil.lengthThan(request.getShopName(), 128, "店铺名称");
				AssertUtil.lengthThan(request.getShopAddress(), 256, "店铺地址");
				AssertUtil.lengthThan(request.getShopImgKey(), 64, "店铺图片key");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByUserName(request.getPhone(), UserTypeEnum.NORMAL.getCode());
				// 用户为null，则为新用户
				if (userDO == null) {
					// 用户名不存在
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				} else {
					if (UserStateEnum.getByCode(userDO.getState()) != UserStateEnum.PASSED
							&& UserStateEnum.getByCode(userDO.getState()) != UserStateEnum.AUDIT_NO) {
						throw new BizException(ResultCodeEnum.USER_EXCEPTION);
					}

					UserInfoDO userInfoDO = new UserInfoDO();
					userInfoDO.setPhone(request.getPhone());
					userInfoDO.setUserName(request.getUserName());
					userInfoDO.setFreezerType(request.getFreezerType());
					userInfoDO.setFreezerModel(request.getFreezerModel());
					try {
						userInfoDO.setArkTime(DateUtils.parseDate(request.getArkTime() + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					userInfoDO.setDistrictId(NumberUtils.toLong(request.getDistrictId()));
					userInfoDO.setShopName(request.getShopName());
					userInfoDO.setShopAddress(request.getShopAddress());
					userInfoDO.setShopImgKey(request.getShopImgKey());
					userInfoDO.setIsAccess(0);
					userInfoDAO.insert(userInfoDO);

					UserOperateTraceDO userOperateTraceDO = new UserOperateTraceDO();
					userOperateTraceDO.setPhone(request.getPhone());
					userOperateTraceDO.setOperate(UserOperateEnum.COMPLETE_INFO.getCode());
					userOperateTraceDO.setMemo("提交店铺信息");
					userOperateTraceDAO.insert(userOperateTraceDO);

					userDO.setState(UserStateEnum.AUDITING.getCode());
					userDao.update(userDO);

					setLobUsed(request.getShopImgKey(), LobWhereUsedEnum.USER_SHOP_PIC);
				}
			}
		});
	}

	/**
	 * 注册用户
	 * 
	 */
	@Override
	public Result<Long> createUser(final User user) {

		return template.complete(new ResultCallback<Long>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(user.getUserName(), "用户名");
				AssertUtil.isNull(user.getType(), "用户类型");

				AssertUtil.isNotNull(userDao.getByUserName(user.getUserName(), user.getType().getCode()), "用户名已存在");
			}

			@Override
			public void excute() {
				// 密码加密
				if (StringUtils.isNotBlank(user.getPassWord())) {
					user.setPassWord(MD5Util.encry(user.getPassWord()));
				}

				UserDO userDO = UserConvert.conv(user);
				userDao.insert(userDO);

				returnValue = userDO.getId();
			}

		});
	}

	/**
	 *
	 * @param user
	 * @return
     */
	@Override
	public Result<Boolean> updateUser(final User user) {

		return template.complete(new ResultCallback<Boolean>() {

			@Override
			public void excute() {
				if (StringUtils.isNotBlank(user.getPassWord())) {
					user.setPassWord(MD5Util.encry(user.getPassWord()));
				}

				UserDO userDO = UserConvert.conv(user);
				int result = userDao.update(userDO);

				returnValue = result > 0;
			}

		});
	}

	@Override
	public Result<Boolean> modifyPwd(final String name, final String pw, final String type, final String newPw) {
		return template.complete(new ResultCallback<Boolean>() {

			private UserDO userDO;

			@Override
			public void check() {
				AssertUtil.isEmpty("用户名", name);
				AssertUtil.isEmpty("密码", pw);
				AssertUtil.isEmpty("新密码", newPw);
				AssertUtil.isNull("用户类型", type);

				userDO = userDao.getUser(name, MD5Util.encry(pw), type);

				if (userDO == null) {
					throw new ParamCheckException("用户名或密码错误");
				}
			}

			@Override
			public void excute() {
				userDO.setPassWord(MD5Util.encry(newPw));

				int result = userDao.update(userDO);
				returnValue = result > 0;
			}

		});
	}

	/**
	 *
	 *
	 * @param name
	 * @param type
     * @return
     */
	@Override
	public Result<User> getByUserName(final String name, final UserTypeEnum type) {
		return template.complete(new ResultCallback<User>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(name, 32, "用户名");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByUserName(name, type.getCode());
				returnValue = UserConvert.conv(userDO);
			}

		});
	}

	/**
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public Result<User> getByPhone(final String phone) {
		return template.complete(new ResultCallback<User>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(phone, 11, "手机号");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByPhone(phone);
				returnValue = UserConvert.conv(userDO);
			}

		});
	}

	/**
	 *
	 *
	 * @param token
	 * @param type
	 * @return
	 */
	@Override
	public Result<User> getByToken(final String token, final UserTypeEnum type) {
		return template.complete(new ResultCallback<User>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(token, "token");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getByToken(token, type.getCode());
				returnValue = UserConvert.conv(userDO);
			}
		});
	}

	/**
	 *
	 * @param query
	 * @param pageNum
	 * @param pageSize
     * @return
     */
	@Override
	public Result<List<User>> query(final UserQuery query, final String pageNum,
										   final String pageSize) {
		return template.pageQuery(new PageResultCallback<List<User>>() {

			@Override
			public void excute(int start, int size) {
				query.setStart(start);
				query.setLimit(size);

				List<UserDO> userDOs = userDao.query(query);
				returnValue = UserConvert.conv(userDOs);

				total = userDao.getCount(query);
			}

		}, pageNum, pageSize);
	}

	@Override
	public Result<Void> auditUserInfo(final UserActionRequest request) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(request.getId(), "用户ID");
				if (!StringUtils.equals(request.getAction(), "agree")) {
					AssertUtil.isEmpty(request.getMemo(), "不通过意见");
				}
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getById(NumberUtils.toLong(request.getId()));
				if (userDO == null) {
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				}

				UserInfoDO userInfoDO = userInfoDAO.getLastNotAccessByPhone(userDO.getPhone());
				if (StringUtils.equals(request.getAction(), "agree")) {
					userDO.setState(UserStateEnum.NORMAL.getCode());
					userDO.setRealName(userInfoDO.getUserName());
					userDO.setShopName(userInfoDO.getShopName());
					userDO.setShopAddress(userInfoDO.getShopAddress());
					userDao.update(userDO);

					userInfoDO.setIsAccess(1);
					userInfoDAO.update(userInfoDO);

					UserDeliveryAddrDO userDeliveryAddrDO = new UserDeliveryAddrDO();
					userDeliveryAddrDO.setPhone(userInfoDO.getPhone());
					userDeliveryAddrDO.setFullName(userInfoDO.getUserName());
					userDeliveryAddrDO.setDelPhone(userInfoDO.getPhone());
					userDeliveryAddrDO.setAddress(userInfoDO.getShopAddress());
					userDeliveryAddrDO.setStatus(1);
					userDeliveryAddrDAO.insert(userDeliveryAddrDO);
				} else {
					userDO.setState(UserStateEnum.AUDIT_NO.getCode());
					userDao.update(userDO);

					userInfoDO.setIsAccess(-1);
					userInfoDO.setAuditMemo(request.getMemo());
					userInfoDAO.update(userInfoDO);
				}
			}
		});
	}

	@Override
	public Result<Void> freeae(final String userId) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(userId, "用户ID");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getById(NumberUtils.toLong(userId));
				if (userDO == null) {
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				}

				if (!StringUtils.equals(UserTypeEnum.NORMAL.getCode(), userDO.getType())) {
					throw new BizException(ResultCodeEnum.USER_TYPE_ERROR);
				}

				if (StringUtils.equals(UserStateEnum.FREEAE.getCode(), userDO.getState())) {
					throw new BizException(ResultCodeEnum.USER_IS_FREEAED);
				}

				String property = userDO.getProperty();
				JSONObject jsonObject;
				if (StringUtils.isNotBlank(property)) {
					jsonObject = new JSONObject();
				} else {
					jsonObject = JSONObject.fromObject(property);
				}

				jsonObject.put(CommonConstants.USER_PROPERTY_LAST_STATE, userDO.getState());
				userDO.setProperty(jsonObject.toString());

				userDO.setState(UserStateEnum.FREEAE.getCode());
				userDao.update(userDO);
			}
		});
	}

	@Override
	public Result<Void> unFreeae(final String userId) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(userId, "用户ID");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getById(NumberUtils.toLong(userId));
				if (userDO == null) {
					throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
				}

				if (!StringUtils.equals(UserTypeEnum.NORMAL.getCode(), userDO.getType())) {
					throw new BizException(ResultCodeEnum.USER_TYPE_ERROR);
				}

				if (!StringUtils.equals(UserStateEnum.FREEAE.getCode(), userDO.getState())) {
					throw new BizException(ResultCodeEnum.USER_IS_NOT_FREEAED);
				}

				String property = userDO.getProperty();
				if (StringUtils.isBlank(property)) {
					throw new BizException(ResultCodeEnum.USER_EXCEPTION);
				}

				JSONObject jsonObject = JSONObject.fromObject(property);
				userDO.setState(jsonObject.getString(CommonConstants.USER_PROPERTY_LAST_STATE));
				userDao.update(userDO);
			}
		});
	}

	@Override
	public Result<UserInfo> getLastUserInfo(final String phone) {
		return template.complete(new ResultCallback<UserInfo>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(phone, "手机号");
			}

			@Override
			public void excute() {
				returnValue = UserInfoConvert.conv(userInfoDAO.getLastByPhone(phone));
			}
		});
	}

	@Override
	public Result<UserInfo> getAccessUserInfo(final String phone) {
		return template.complete(new ResultCallback<UserInfo>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(phone, "手机号");
			}

			@Override
			public void excute() {
				returnValue = UserInfoConvert.conv(userInfoDAO.getAccessByPhone(phone));
			}
		});
	}

}
