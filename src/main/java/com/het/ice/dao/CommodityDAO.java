package com.het.ice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.het.ice.dao.model.CommodityDO;
import com.het.ice.dao.query.CommodityQuery;

public interface CommodityDAO {
    void insert(CommodityDO commodityDO);

    void update(CommodityDO commodityDO);

    void delete(long id);

    CommodityDO getById(long id);

    CommodityDO getByName(String name);

    List<CommodityDO> queryByBizId(@Param("bizId") long bizId, @Param("start") int pageNum,
          @Param("limit") int pageSize);

    List<CommodityDO> queryByCondition(CommodityQuery commodityQuery);

    int getCountByBizId(@Param("bizId") long bizId);

    int getCountByCondition(CommodityQuery commodityQuery);

    List<CommodityDO> queryAllOnline(CommodityQuery commodityQuery);

    List<CommodityDO> queryAll();
}
