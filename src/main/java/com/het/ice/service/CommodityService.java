package com.het.ice.service;

import java.util.List;

import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.model.Commodity;
import com.het.ice.service.template.Result;

public interface CommodityService {

	public Result<Void> create(Commodity com);

	public Result<Void> update(Commodity com);

	public Result<Void> updateState(Commodity com);

	public Result<Void> delete(long comId);

	public Result<Commodity> getById(long comId);

	public Result<Commodity> getByName(String name);

	Result<List<Commodity>> queryByCondition(CommodityQuery query, String pageNum, String pageSize);

	Result<List<Commodity>> queryAll();

	public Result<List<Commodity>> queryByBizId(long bizId, String pageNum, String pageSize);
}
