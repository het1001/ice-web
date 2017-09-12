package com.het.ice.dao;

import com.het.ice.dao.model.AllotSalesmanDO;
import com.het.ice.dao.query.AllotSalesmanQuery;
import com.het.ice.model.AllotSalesman;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllotSalesmanDAO {

    void insert(AllotSalesmanDO allotSalesmanDO);

    void update(AllotSalesmanDO allotSalesmanDO);

    void delete(long id);

    AllotSalesmanDO getById(@Param(value = "id") long id);

    AllotSalesmanDO getByUniqueKey(@Param(value = "uniqueKey") String uniqueKey);

    int getCountByCondition(AllotSalesmanQuery allotSalesmanQuery);

    List<AllotSalesmanDO> queryByCondition(AllotSalesmanQuery allotSalesmanQuery);

    List<AllotSalesmanDO> queryByType(@Param(value = "type") String type);

    List<AllotSalesmanDO> queryAll();
}
