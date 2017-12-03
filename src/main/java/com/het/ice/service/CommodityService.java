package com.het.ice.service;

import java.util.List;

import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.model.Commodity;
import com.het.ice.service.template.Result;

public interface CommodityService {

	Result<Void> create(Commodity com);

	Result<Void> update(Commodity com);

	Result<Void> updateState(Commodity com);

	Result<Void> delete(long comId);

	Result<Commodity> getById(long comId);

	Result<Commodity> getByName(String name);

	Result<List<Commodity>> queryByCondition(CommodityQuery query, String pageNum, String pageSize);

	Result<List<Commodity>> queryAll();

	Result<List<Commodity>> queryAllOnline(long catId);

	Result<List<Commodity>> queryByBizId(long bizId, String pageNum, String pageSize);

	Result<Void> resetSales();
}
