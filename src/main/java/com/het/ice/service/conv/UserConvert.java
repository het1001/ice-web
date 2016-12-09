package com.het.ice.service.conv;

import com.het.ice.dao.model.UserDO;
import com.het.ice.enums.UserStateEnum;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;

/**
 *
 * 用户转换器
 *
 * @author Administrator
 *
 */
public class UserConvert {

	public static User conv(UserDO userDO) {
		if (userDO == null) {
			return null;
		}

		User user = new User();
		user.setId(userDO.getId());
		user.setUserName(userDO.getUserName());
		user.setPassWord(userDO.getPassWord());
		user.setType(UserTypeEnum.getByCode(userDO.getType()));
		user.setPhone(userDO.getPhone());
		user.setAddress(userDO.getAddress());
		user.setCreateTime(userDO.getCreateTime());
		user.setModifyTime(userDO.getModifyTime());
		user.setCreateUser(userDO.getCreateUser());
		user.setUpdateUser(userDO.getUpdateUser());
		user.setState(UserStateEnum.getByCode(userDO.getState()));
		return user;
	}

	public static UserDO conv(User user) {
		if (user == null) {
			return null;
		}

		UserDO userDO = new UserDO();
		userDO.setId(user.getId());
		userDO.setUserName(user.getUserName());
		userDO.setPassWord(user.getPassWord());
		userDO.setType(user.getType().getCode());
		userDO.setPhone(user.getPhone());
		userDO.setAddress(user.getAddress());
		userDO.setCreateTime(user.getCreateTime());
		userDO.setModifyTime(user.getModifyTime());
		userDO.setCreateUser(user.getCreateUser());
		userDO.setUpdateUser(user.getUpdateUser());
		userDO.setState(user.getState().getCode());
		return userDO;
	}
}
