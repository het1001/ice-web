package com.het.ice.service.conv;

import com.het.ice.dao.model.UserPhoneInfoDO;
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
		model.setManufacturer(doModel.getManufacturer());
		model.setModel(doModel.getModel());
		model.setDeviceUniqueId(doModel.getDeviceUniqueId());
		model.setDeviceId(doModel.getDeviceId());
		model.setDeviceName(doModel.getDeviceName());
		model.setSysName(doModel.getSysName());
		model.setSysVersion(doModel.getSysVersion());
		model.setImei(doModel.getImei());
		model.setAppVersion(doModel.getAppVersion());
		model.setCreateTime(doModel.getCreateTime());
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
		doModel.setManufacturer(model.getManufacturer());
		doModel.setModel(model.getModel());
		doModel.setDeviceUniqueId(model.getDeviceUniqueId());
		doModel.setDeviceId(model.getDeviceId());
		doModel.setDeviceName(model.getDeviceName());
		doModel.setSysName(model.getSysName());
		doModel.setSysVersion(model.getSysVersion());
		doModel.setImei(model.getImei());
		doModel.setAppVersion(model.getAppVersion());
		doModel.setCreateTime(model.getCreateTime());
		return doModel;
	}
}
