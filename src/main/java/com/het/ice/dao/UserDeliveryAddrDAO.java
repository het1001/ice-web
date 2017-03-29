package com.het.ice.dao;

import com.het.ice.dao.model.UserDeliveryAddrDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface UserDeliveryAddrDAO {

	int insert(UserDeliveryAddrDO userDeliveryAddrDO);

	int update(UserDeliveryAddrDO userDeliveryAddrDO);

	void delete(long id);

	UserDeliveryAddrDO getById(long id);

	/**
	 * 根据用户id查收货地址
	 *
	 * @param userId
	 * @return
	 */
	List<UserDeliveryAddrDO> queryByUserId(@Param(value = "userId") long userId);

}
