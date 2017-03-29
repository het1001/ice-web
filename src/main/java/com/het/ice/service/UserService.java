package com.het.ice.service;

import com.het.ice.dao.query.UserQuery;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import com.het.ice.service.template.Result;

import java.util.List;

public interface UserService {

	/**
	 * 管理员用户登录验证
	 * 
	 * @param name
	 * @param pw
	 * @param type
	 * @return
	 */
	Result<User> checkUser(String name, String pw, String type, boolean isLogin);

	/**
	 * 普通用户登录验证
	 *
	 * @param name
	 * @param pw
	 * @return
	 */
	Result<User> checkNormalUser(final String name, final String pw);

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
	Result<List<User>> queryByState(UserQuery query, String pageNum,
									String pageSize);
}
