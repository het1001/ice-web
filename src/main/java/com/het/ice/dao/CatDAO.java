package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.CatDO;
import org.apache.ibatis.annotations.Param;

public interface CatDAO {

	void insert(CatDO catDO);

	void update(CatDO catDO);

	void delete(long id);

	CatDO getByName(@Param(value = "name") String name);

	List<CatDO> queryByBizId(@Param(value = "bizId") long bizId);
}
