package com.het.ice.dao;

import com.het.ice.dao.model.OrderDO;
import com.het.ice.dao.query.OrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
	 * 根据phone获取订单
	 * 
	 * @param phone
	 * @return
	 */
	List<OrderDO> queryByPhone(@Param("phone") String phone);

	/**
	 *
	 * @param query
	 * @return
	 */
	List<OrderDO> queryByCondition(OrderQuery query);

	/**
	 * 根据状态查询用户总量
	 *
	 * @param query
	 * @return
	 */
	int countByCondition(OrderQuery query);
}
