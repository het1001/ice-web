package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.CatDO;
import com.het.ice.dao.query.CatQuery;
import com.het.ice.model.Cat;
import org.apache.ibatis.annotations.Param;

public interface CatDAO {

	void insert(CatDO catDO);

	void update(CatDO catDO);

	void delete(long id);

	CatDO getById(long id);

	CatDO getByName(@Param(value = "name") String name);

	List<CatDO> queryByType(CatQuery query);
}
