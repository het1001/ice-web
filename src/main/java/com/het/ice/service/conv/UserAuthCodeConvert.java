package com.het.ice.service.conv;

import com.het.ice.dao.model.UserAuthCodeDO;
import com.het.ice.model.UserAuthCode;

/**
 * Created by Administrator on 2017/5/24.
 */
public class UserAuthCodeConvert {

    public static UserAuthCode conv(UserAuthCodeDO doModel) {
        if (doModel == null) {
            return null;
        }

        UserAuthCode model = new UserAuthCode();
        model.setId(doModel.getId());
        model.setPhone(doModel.getPhone());
        model.setCode(doModel.getCode());
        model.setUsed(doModel.getUsed() == 1);
        model.setUseTime(doModel.getUseTime());
        model.setDeviceUniqueId(doModel.getDeviceUniqueId());
        model.setCreateTime(doModel.getCreateTime());
        return model;
    }

    public static UserAuthCodeDO conv(UserAuthCode model) {
        if (model == null) {
            return null;
        }

        UserAuthCodeDO doModel = new UserAuthCodeDO();
        doModel.setId(model.getId());
        doModel.setPhone(model.getPhone());
        doModel.setCode(model.getCode());
        doModel.setUsed(model.isUsed() ? 1 : 0);
        doModel.setUseTime(model.getUseTime());
        doModel.setDeviceUniqueId(model.getDeviceUniqueId());
        doModel.setCreateTime(model.getCreateTime());
        return doModel;
    }
}
