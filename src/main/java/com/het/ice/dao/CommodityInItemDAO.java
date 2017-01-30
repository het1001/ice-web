package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.CommodityInItemDO;
import com.het.ice.dao.query.CommodityInItemQuery;

public interface CommodityInItemDAO {

    void insert(CommodityInItemDO comInDO);

    int delete(long id);

    int update(CommodityInItemDO comInDO);

    CommodityInItemDO getById(long id);

    List<CommodityInItemDO> queryByInId(long id);

    int getCountByCom(CommodityInItemQuery query);

    List<CommodityInItemDO> queryByCom(CommodityInItemQuery query);
}
