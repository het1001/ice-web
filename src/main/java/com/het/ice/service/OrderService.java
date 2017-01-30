package com.het.ice.service;

import java.util.List;

import com.het.ice.model.Commodity;
import com.het.ice.service.template.Result;

public interface OrderService {

	public Result<Void> create(Commodity com);

	public Result<Void> update(Commodity com);

	public Result<Void> delete(long comId);

	public Result<Commodity> getById(long comId);

	public Result<List<Commodity>> queryByBizId(long bizId);
}
