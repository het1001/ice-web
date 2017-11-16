package com.het.ice.service.conv;

import com.het.ice.dao.model.UserInfoDO;
import com.het.ice.model.UserInfo;

/**
 * Created by Administrator on 2017/5/24.
 */
public class UserInfoConvert {

    public static UserInfo conv(UserInfoDO doModel) {
        if (doModel == null) {
            return null;
        }

        UserInfo model = new UserInfo();
        model.setId(doModel.getId());
        model.setPhone(doModel.getPhone());
        model.setUserName(doModel.getUserName());
        model.setShopName(doModel.getShopName());
        model.setShopAddress(doModel.getShopAddress());
        model.setShopImgKey(doModel.getShopImgKey());
        model.setFreezerType(doModel.getFreezerType());
        model.setArkTime(doModel.getArkTime());
        model.setFreezerModel(doModel.getFreezerModel());
        model.setDistrictId(doModel.getDistrictId());
        model.setIsAccess(doModel.getIsAccess());
        model.setAuditMemo(doModel.getAuditMemo());
        model.setCreateTime(doModel.getCreateTime());
        return model;
    }

    public static UserInfoDO conv(UserInfo model) {
        if (model == null) {
            return null;
        }

        UserInfoDO doModel = new UserInfoDO();
        doModel.setId(model.getId());
        doModel.setPhone(model.getPhone());
        doModel.setUserName(model.getUserName());
        doModel.setShopName(model.getShopName());
        doModel.setShopAddress(model.getShopAddress());
        doModel.setShopImgKey(model.getShopImgKey());
        doModel.setFreezerType(model.getFreezerType());
        doModel.setArkTime(model.getArkTime());
        doModel.setFreezerModel(model.getFreezerModel());
        doModel.setDistrictId(model.getDistrictId());
        doModel.setIsAccess(model.getIsAccess());
        doModel.setAuditMemo(model.getAuditMemo());
        doModel.setCreateTime(model.getCreateTime());
        return doModel;
    }
}
