package com.het.ice.service;

import com.het.ice.dao.query.OrderQuery;
import com.het.ice.model.Order;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.OrderWO;

import java.util.List;

public interface OrderService {

	Result<Void> create(OrderWO orderWO);

	Result<List<Order>> queryByPhone(String phone);

	Result<List<Order>> queryByCondition(OrderQuery query, String pageNum, String pageSize);

	Result<Void> verify(String id);

	Result<Void> cancel(String id);

	Result<Void> complete(String id);
}
