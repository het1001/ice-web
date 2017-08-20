package com.het.ice.dao;

import com.het.ice.dao.model.UserShopInfoDO;

public interface UserShopInfoDAO {

	/**
	 *
	 * @param userShopInfoDO
	 */
	int insert(UserShopInfoDO userShopInfoDO);

	/**
	 *
	 * @param userShopInfoDO
	 * @return
	 */
	int update(UserShopInfoDO userShopInfoDO);

	/**
     * 根据手机号获取通过审核的店铺信息
	 *
	 * @param phone
     * @return
     */
	UserShopInfoDO getAccessByPhone(String phone);

	/**
	 * 根据手机号获取最新一条店铺信息
	 *
	 * @param phone
	 * @return
	 */
	UserShopInfoDO getLastByPhone(String phone);

	/**
	 * 根据手机号获取最新一条没有审核的店铺信息
	 *
	 * @param phone
	 * @return
	 */
	UserShopInfoDO getLastNotAccessByPhone(String phone);
}
