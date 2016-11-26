package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.OrderDO;

/**
 * 订单DAO
 * 
 * @author Administrator
 *
 */
public interface OrderDAO {

	/**
	 * 新建
	 * 
	 * @param orderDO
	 */
	long insert(OrderDO orderDO);

	/**
	 * 更新
	 * 
	 * @param orderDO
	 */
	void update(OrderDO orderDO);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void delete(long id);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	OrderDO getById(long id);

	/**
	 * 根据用户id获取订单
	 * 
	 * @param userId
	 * @return
	 */
	List<OrderDO> queryByUserId(long userId);
}
