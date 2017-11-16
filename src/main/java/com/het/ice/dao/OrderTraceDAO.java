package com.het.ice.dao;

import com.het.ice.dao.model.OrderTraceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderTraceDAO {

	void insert(OrderTraceDO orderTraceDO);

	void update(OrderTraceDO orderTraceDO);

	void delete(long id);

	OrderTraceDO getById(long id);

	List<OrderTraceDO> queryByOrderNum(@Param("orderNum") String orderNum);
}
