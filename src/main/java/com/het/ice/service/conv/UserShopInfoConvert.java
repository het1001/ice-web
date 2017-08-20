package com.het.ice.service.conv;

import com.het.ice.dao.model.UserShopInfoDO;
import com.het.ice.model.UserShopInfo;

/**
 * Created by Administrator on 2017/5/24.
 */
public class UserShopInfoConvert {

    public static UserShopInfo conv(UserShopInfoDO doModel) {
        if (doModel == null) {
            return null;
        }

        UserShopInfo model = new UserShopInfo();
        model.setId(doModel.getId());
        model.setPhone(doModel.getPhone());
        model.setShopName(doModel.getShopName());
        model.setShopAddress(doModel.getShopAddress());
        model.setShopImgKey(doModel.getShopImgKey());
        model.setIsAccess(doModel.getIsAccess());
        model.setAuditMemo(doModel.getAuditMemo());
        model.setCreateTime(doModel.getCreateTime());
        return model;
    }

    public static UserShopInfoDO conv(UserShopInfo model) {
        if (model == null) {
            return null;
        }

        UserShopInfoDO doModel = new UserShopInfoDO();
        doModel.setId(model.getId());
        doModel.setPhone(model.getPhone());
        doModel.setShopName(model.getShopName());
        doModel.setShopAddress(model.getShopAddress());
        doModel.setShopImgKey(model.getShopImgKey());
        doModel.setIsAccess(model.getIsAccess());
        doModel.setAuditMemo(model.getAuditMemo());
        doModel.setCreateTime(model.getCreateTime());
        return doModel;
    }
}
