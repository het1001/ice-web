package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.OrderDO;

public interface OrderTraceDAO {
	void insert(OrderDO orderDO);

	void update(OrderDO orderDO);

	void delete(long id);

	OrderDO getById(long id);

	List<OrderDO> queryByUserId(long userId);
}
