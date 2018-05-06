package com.het.ice.service.conv;

import com.het.ice.dao.model.UserDO;
import com.het.ice.enums.UserStateEnum;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
		user.setRealName(userDO.getRealName());
		user.setShopName(userDO.getShopName());
		user.setShopAddress(userDO.getShopAddress());
		user.setFreezerType(userDO.getFreezerType());
		user.setDistrictId(userDO.getDistrictId());
		user.setStandbyPhone(userDO.getStandbyPhone());

		user.setCreateTime(userDO.getCreateTime());
		user.setModifyTime(userDO.getModifyTime());
		user.setCreateUser(userDO.getCreateUser());
		user.setUpdateUser(userDO.getUpdateUser());

		user.setState(UserStateEnum.getByCode(userDO.getState()));
		user.setLastLoginTime(userDO.getLastLoginTime());
		user.setToken(userDO.getToken());
		user.setProperty(JSONObject.fromObject(userDO.getProperty()));
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
		userDO.setRealName(user.getRealName());
		userDO.setShopName(user.getShopName());
		userDO.setShopAddress(user.getShopAddress());
		userDO.setFreezerType(user.getFreezerType());
		userDO.setDistrictId(user.getDistrictId());
		userDO.setStandbyPhone(user.getStandbyPhone());

		userDO.setCreateTime(user.getCreateTime());
		userDO.setModifyTime(user.getModifyTime());
		userDO.setCreateUser(user.getCreateUser());
		userDO.setUpdateUser(user.getUpdateUser());

		userDO.setState(user.getState().getCode());
		userDO.setLastLoginTime(user.getLastLoginTime());
		userDO.setProperty(user.getProperty() != null ? user.getProperty().toString() : "");
		return userDO;
	}

	/**
	 *
	 *
	 * @param userDOs
	 * @return
     */
	public static List<User> conv(List<UserDO> userDOs) {
		List<User> users = new ArrayList<User>();
		if (userDOs == null || userDOs.size() == 0) {
			return users;
		}

		for (UserDO userDO : userDOs) {
			users.add(conv(userDO));
		}

		return users;
	}
}
