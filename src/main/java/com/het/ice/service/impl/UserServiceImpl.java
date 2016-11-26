package com.het.ice.service.impl;

import java.util.Date;

import javax.annotation.Resource;

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
				AssertUtil.isEmpty(user.getPassWord(), "密码");
				AssertUtil.isNull(user.getType(), "用户类型");

				AssertUtil.isNotNull(userDao.getByUserName(user.getUserName(), user.getType().getCode()), "用户名已存在");
			}

			@Override
			public void excute() {
				// 密码加密
				user.setPassWord(MD5Util.encry(user.getPassWord()));

				UserDO userDO = UserConvert.conv(user);
				userDao.insert(userDO);

				returnValue = userDO.getId();
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

	// public static void main(String[] args) {
	// // 47bce5c74f589f4867dbd57e9ca9f808
	// ApplicationContext context = new
	// ClassPathXmlApplicationContext("applicationContext.xml");
	// UserService book = (UserService) context.getBean("userService");
	// Result<User> re = book.checkUser("aaa", "aaa");
	// System.out.println(re.isSuccess());
	// System.out.println(re.getErrorMsg());
	// System.out.println(re.getResult());
	//
	// }

}
