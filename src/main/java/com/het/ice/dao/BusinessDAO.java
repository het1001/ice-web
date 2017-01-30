package com.het.ice.dao;

import com.het.ice.dao.model.BusinessDO;

/**
 * 业务DAO
 */
public interface BusinessDAO {

	void insert(BusinessDO businessDO);

	void update(BusinessDO businessDO);

	void delete(long id);

	BusinessDO getById(long id);

	BusinessDO getByName(String name);
}
