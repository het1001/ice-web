package com.het.ice.service.impl;

import com.het.ice.dao.LobDAO;
import com.het.ice.dao.model.LobDO;
import com.het.ice.enums.LobWhereUsedEnum;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/11.
 */
public class BaseServiceImpl {

    @Resource
    private LobDAO lobDAO;

    /**
     * 更新oss的使用场景
     *
     * @param key
     * @param lobWhereUsedEnum
     */
    protected void setLobUsed(String key, LobWhereUsedEnum lobWhereUsedEnum) {
        LobDO lobDO = lobDAO.getByOssKey(key);
        if (lobDO == null) {
            return;
        } else {
            lobDO.setIsUsed(1);
            lobDO.setWhereUse(lobWhereUsedEnum.getCode());
            lobDAO.update(lobDO);
        }
    }
}
