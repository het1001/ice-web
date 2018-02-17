package com.het.ice.dao;

import com.het.ice.dao.model.BrandDO;
import com.het.ice.dao.model.CatDO;
import com.het.ice.dao.query.CatQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandDAO {

	void insert(BrandDO brandDO);

	void update(BrandDO brandDO);

	void delete(long id);

	BrandDO getById(long id);

	BrandDO getByName(@Param(value = "name") String name);

	List<BrandDO> queryAll();
}
