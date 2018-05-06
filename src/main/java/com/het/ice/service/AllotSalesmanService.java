package com.het.ice.service;

import com.het.ice.dao.query.AllotSalesmanQuery;
import com.het.ice.model.AllotSalesman;
import com.het.ice.service.template.Result;

import java.util.List;

public interface AllotSalesmanService {
    Result<Void> create(AllotSalesman allotSalesman);

    Result<Void> update(AllotSalesman allotSalesman);

    Result<Void> delete(long id);

    Result<AllotSalesman> getByUniqueKey(String uniqueKey);

    Result<List<AllotSalesman>> queryByCondition(AllotSalesmanQuery query, String pageNum, String pageSize);

    Result<List<AllotSalesman>> queryByType(String type);

    Result<List<AllotSalesman>> queryAll();
}
