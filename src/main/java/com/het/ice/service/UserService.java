package com.het.ice.service;

import com.het.ice.model.User;
import com.het.ice.service.template.Result;

public interface UserService {

	/**
	 * 用户登录验证
	 * 
	 * @param name
	 * @param pw
	 * @param type
	 * @return
	 */
	Result<User> checkUser(String name, String pw, String type, boolean isLogin);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	Result<Long> createUser(User user);

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
}
