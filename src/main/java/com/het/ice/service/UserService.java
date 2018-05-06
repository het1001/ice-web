package com.het.ice.service;

import com.het.ice.dao.query.UserQuery;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.AllotDistrict;
import com.het.ice.model.User;
import com.het.ice.model.UserInfo;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.*;

import java.util.List;

public interface UserService {

	/**
	 * 初始化用户且获取验证码
	 * @param request
	 * @return
	 */
	Result<Void> authAndInitUser(UserGetAuthCodeRequest request);

	/**
	 * 校验验证码
	 *
	 * @param request
	 * @return
	 */
	Result<Void> authCode(UserAuthRequest request);

	/**
	 * 设置密码
	 *
	 * @param request
	 * @return
	 */
	Result<Void> setPassWord(UserSetPassWordRequest request);

	/**
	 * 管理员用户登录验证
	 * 
	 * @param name
	 * @param pw
	 * @return
	 */
	Result<User> bizLogin(String name, String pw, boolean isLogin);

	/**
	 * 普通用户登录验证
	 *
	 * @param request
	 * @return
	 */
	Result<User> normalLogin(UserLoginRequest request);

	/**
	 * 获取验证码
	 *
	 * @param request
	 * @return
	 */
	Result<Void> getAuthCode(UserGetAuthCodeRequest request);

	/**
	 * 设置密码
	 *
	 * @param request
	 * @return
	 */
	Result<Void> completeUserInfo(UserInfoRequest request);

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @return
	 */
	Result<Long> createUser(User user);

	/**
	 * 注册用户
	 *
	 * @param user
	 * @return
	 */
	Result<Boolean> updateUser(User user);

	/**
	 * 修改密码
	 * 
	 * @param name
	 * @param pw
	 * @param type
	 * @param newPw
	 * @return
	 */
	Result<Boolean> modifyPwd(String name, String pw, String type, String newPw);

	/**
	 *
	 * @param name
	 * @param type
	 * @return
	 */
	Result<User> getByUserName(String name, UserTypeEnum type);

	/**
	 *
	 *
	 * @param phone
	 * @return
	 */
	Result<User> getByPhone(String phone);

	/**
	 *
	 * @param token
	 * @param type
	 * @return
	 */
	Result<User> getByToken(String token, UserTypeEnum type);

	/**
	 * 按状态查询用户列表（仅限会员账户）
	 * @param query
	 * @param pageNum
	 * @param pageSize
     * @return
     */
	Result<List<User>> query(UserQuery query, String pageNum,
									String pageSize);

	/**
	 *
	 * @param request
	 * @return
	 */
	Result<Void> auditUserInfo(UserActionRequest request);

	/**
	 * 冻结账户（仅限会员账户）
	 * @param userId
	 * @return
	 */
	Result<Void> freeae(String userId);

	/**
	 * 解冻账户（仅限会员账户）
	 * @param userId
	 * @return
	 */
	Result<Void> unFreeae(String userId);

	/**
	 * 获取最后一个未审核的店铺信息
	 *
	 * @param phone
	 * @return
	 */
	Result<UserInfo> getLastUserInfo(String phone);

	/**
	 * 获取审核好的店铺信息
	 *
	 * @param phone
	 * @return
	 */
	Result<UserInfo> getAccessUserInfo(String phone);

	Result<Void> updateVersion(UserUpdateVersionRequest userUpdateVersionRequest);

	Result<AllotDistrict> getDistrictInfo(UserInfoRequest userInfoRequest);
}
