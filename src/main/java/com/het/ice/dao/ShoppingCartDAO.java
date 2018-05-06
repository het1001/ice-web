package com.het.ice.dao;

import com.het.ice.dao.model.ShoppingCartDO;
import com.het.ice.dao.query.ShoppingCartQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartDAO {

	void insert(ShoppingCartDO shoppingCartDO);

	void update(ShoppingCartDO shoppingCartDO);

	void delete(long id);

	ShoppingCartDO getById(long id);

	ShoppingCartDO getByPhoneAndComId(ShoppingCartQuery query);

	List<ShoppingCartDO> queryByPhone(@Param("phone") String phone);
}
