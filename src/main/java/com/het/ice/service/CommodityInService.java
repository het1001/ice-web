package com.het.ice.service;

import java.util.List;

import com.het.ice.dao.query.CommodityInQuery;
import com.het.ice.model.CommodityIn;
import com.het.ice.model.CommodityInItem;
import com.het.ice.service.template.Result;

/**
 * 
 * 
 * @author Administrator
 *
 */
public interface CommodityInService {

	Result<Void> create(List<CommodityInItem> items);

	Result<List<CommodityIn>> queryByCondition(CommodityInQuery query, String pageNum, String pageSize);

	Result<List<CommodityInItem>> queryDetail(long inId);
}
