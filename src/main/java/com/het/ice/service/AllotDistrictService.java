package com.het.ice.service;

import com.het.ice.dao.query.AllotDistrictQuery;
import com.het.ice.model.AllotDistrict;
import com.het.ice.service.template.Result;

import java.util.List;
import java.util.Map;

public interface AllotDistrictService {

	Result<Void> create(AllotDistrict allotDistrict, List<String> salesmanIds);

	Result<Void> update(AllotDistrict allotDistrict, List<String> salesmanIds);

	Result<Void> delete(long id);

	Result<AllotDistrict> getByName(String name);

	Result<List<AllotDistrict>> queryByCondition(AllotDistrictQuery query, String pageNum, String pageSize);

	Result<List<AllotDistrict>> queryAll();

	Result<Map<String, List<String>>> getSalIdsByDisId(long id);
}
