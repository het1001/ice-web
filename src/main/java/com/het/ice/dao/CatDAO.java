package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.CatDO;

public interface CatDAO {

	void insert(CatDO catDO);

	void update(CatDO catDO);

	void delete(long id);

	CatDO getById(long id);

	List<CatDO> queryByBizId(long bizId);
}
