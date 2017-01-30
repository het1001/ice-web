package com.het.ice.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.het.ice.dao.model.CommodityDO;
import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.dao.query.UserQuery;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.Commodity;
import com.het.ice.service.conv.CommodityConvert;
import com.het.ice.service.template.PageResultCallback;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.het.ice.dao.UserDAO;
import com.het.ice.dao.model.UserDO;
import com.het.ice.model.User;
import com.het.ice.service.UserService;
import com.het.ice.service.conv.UserConvert;
import com.het.ice.service.exception.ParamCheckException;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import com.het.ice.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDAO userDao;

	@Resource
	private Template template;

	/**
	 * 
	 */
	@Override
	public Result<User> checkUser(final String name, final String pw, final String type, final boolean isLogin) {
		return template.complete(new ResultCallback<User>() {

			@Override
			public void check() {
				AssertUtil.lengthThan(name, 32, "用户名");
				AssertUtil.lengthThan(pw, 32, "密码");
				AssertUtil.lengthThan(type, 10, "用户类型");
			}

			@Override
			public void excute() {
				UserDO userDO = userDao.getUser(name, MD5Util.encry(pw), type);
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
	 * @param query
	 * @param pageNum
	 * @param pageSize
     * @return
     */
	@Override
	public Result<List<User>> queryByState(final UserQuery query, final String pageNum,
										   final String pageSize) {
		return template.pageQuery(new PageResultCallback<List<User>>() {

			@Override
			public void excute(int start, int size) {
				query.setStart(start);
				query.setLimit(size);


				List<UserDO> userDOs = userDao.queryByState(query);
				returnValue = UserConvert.conv(userDOs);

				total = userDao.getCountByState(query);
			}

		}, pageNum, pageSize);
	}

}
