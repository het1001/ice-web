package com.het.ice.dao;

import com.het.ice.dao.model.OrderListDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderListDAO {

	void insert(OrderListDO orderListDO);

	void update(OrderListDO orderListDO);

	void delete(long id);

	OrderListDO getById(long id);

	List<OrderListDO> queryByOrderNum(@Param("orderNum") String orderNum);

	List<OrderListDO> queryWeekFinishListByComId(@Param("comId") long comId);
}
