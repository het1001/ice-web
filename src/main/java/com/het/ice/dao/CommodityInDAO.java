package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.CommodityInDO;
import com.het.ice.dao.query.CommodityInQuery;

/**
 * @author Administrator
 */
public interface CommodityInDAO {

    int insert(CommodityInDO comInDO);

    int delete(long id);

    int getCountByCondition(CommodityInQuery query);

    List<CommodityInDO> queryByCondition(CommodityInQuery query);
}
