package com.het.ice.dao;

import com.het.ice.dao.model.UserInfoDO;

public interface UserInfoDAO {

	/**
	 *
	 * @param userInfoDO
	 */
	int insert(UserInfoDO userInfoDO);

	/**
	 *
	 * @param userInfoDO
	 * @return
	 */
	int update(UserInfoDO userInfoDO);

	/**
     * 根据手机号获取通过审核的店铺信息
	 *
	 * @param phone
     * @return
     */
	UserInfoDO getAccessByPhone(String phone);

	/**
	 * 根据手机号获取最新一条店铺信息
	 *
	 * @param phone
	 * @return
	 */
	UserInfoDO getLastByPhone(String phone);

	/**
	 * 根据手机号获取最新一条没有审核的店铺信息
	 *
	 * @param phone
	 * @return
	 */
	UserInfoDO getLastNotAccessByPhone(String phone);
}
