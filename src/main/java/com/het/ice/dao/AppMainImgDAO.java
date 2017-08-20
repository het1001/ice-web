package com.het.ice.dao;

import com.het.ice.dao.model.AppMainImgDO;
import com.het.ice.dao.query.AppImageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppMainImgDAO {

    void insert(AppMainImgDO appMainImgDO);

    void update(AppMainImgDO appMainImgDO);

    void delete(long id);

    AppMainImgDO getById(@Param(value = "id") long id);

    List<AppMainImgDO> queryActiveImage();

    List<AppMainImgDO> queryByCondition(AppImageQuery query);

    int getCountByCondition(AppImageQuery query);
}
