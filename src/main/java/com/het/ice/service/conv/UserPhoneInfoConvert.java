package com.het.ice.service.conv;

import com.het.ice.dao.model.UserDO;
import com.het.ice.dao.model.UserPhoneInfoDO;
import com.het.ice.enums.UserStateEnum;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import com.het.ice.model.UserPhoneInfo;

/**
 *
 * 用户手机信息转换器
 *
 * @author Administrator
 *
 */
public class UserPhoneInfoConvert {

	public static UserPhoneInfo conv(UserPhoneInfoDO doModel) {
		if (doModel == null) {
			return null;
		}

		UserPhoneInfo model = new UserPhoneInfo();
		model.setId(doModel.getId());
		model.setUserId(doModel.getUserId());
		model.setPhone(doModel.getPhone());
		model.setModel(doModel.getModel());
		model.setImei(doModel.getImei());
		model.setBrand(doModel.getBrand());
		model.setVersion(doModel.getVersion());
		model.setSdkVersion(doModel.getSdkVersion());
		model.setCreateTime(doModel.getCreateTime());
		model.setModifyTime(doModel.getModifyTime());
		return model;
	}

	public static UserPhoneInfoDO conv(UserPhoneInfo model) {
		if (model == null) {
			return null;
		}

		UserPhoneInfoDO doModel = new UserPhoneInfoDO();
		doModel.setId(model.getId());
		doModel.setUserId(model.getUserId());
		doModel.setPhone(model.getPhone());
		doModel.setModel(model.getModel());
		doModel.setImei(model.getImei());
		doModel.setBrand(model.getBrand());
		doModel.setVersion(model.getVersion());
		doModel.setSdkVersion(model.getSdkVersion());
		doModel.setCreateTime(model.getCreateTime());
		doModel.setModifyTime(model.getModifyTime());
		return doModel;
	}
}
