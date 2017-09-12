package com.het.ice.dao;

import com.het.ice.dao.model.AllotDistrictDO;
import com.het.ice.dao.query.AllotDistrictQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllotDistrictDAO {

    void insert(AllotDistrictDO allotDistrictDO);

    void update(AllotDistrictDO allotDistrictDO);

    void delete(long id);

    AllotDistrictDO getByName(@Param(value = "name") String name);

    int getCountByCondition(AllotDistrictQuery allotDistrictQuery);

    List<AllotDistrictDO> queryByCondition(AllotDistrictQuery allotDistrictQuery);

    List<AllotDistrictDO> queryAll();
}
