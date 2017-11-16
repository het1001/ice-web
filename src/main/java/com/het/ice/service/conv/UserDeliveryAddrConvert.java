package com.het.ice.service.conv;

import com.het.ice.dao.model.UserDeliveryAddrDO;
import com.het.ice.dao.model.UserPhoneInfoDO;
import com.het.ice.model.UserDeliveryAddr;
import com.het.ice.model.UserPhoneInfo;

/**
 *
 * 用户地址信息转换器
 *
 * @author Administrator
 *
 */
public class UserDeliveryAddrConvert {

	public static UserDeliveryAddr conv(UserDeliveryAddrDO doModel) {
		if (doModel == null) {
			return null;
		}

		UserDeliveryAddr model = new UserDeliveryAddr();
		model.setId(doModel.getId());
		model.setPhone(doModel.getPhone());
		model.setFullName(doModel.getFullName());
		model.setAddress(doModel.getAddress());
		model.setDelPhone(doModel.getDelPhone());
		model.setStatus(doModel.getStatus());
		model.setCreateTime(doModel.getCreateTime());
		model.setModifyTime(doModel.getModifyTime());
		return model;
	}

	public static UserDeliveryAddrDO conv(UserDeliveryAddr model) {
		if (model == null) {
			return null;
		}

		UserDeliveryAddrDO doModel = new UserDeliveryAddrDO();
		doModel.setId(model.getId());
		doModel.setPhone(model.getPhone());
		doModel.setFullName(model.getFullName());
		doModel.setAddress(model.getAddress());
		doModel.setDelPhone(model.getDelPhone());
		doModel.setStatus(model.getStatus());
		doModel.setCreateTime(model.getCreateTime());
		doModel.setModifyTime(model.getModifyTime());
		return doModel;
	}
}
