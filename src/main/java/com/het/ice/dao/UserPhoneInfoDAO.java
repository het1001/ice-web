package com.het.ice.dao;

import com.het.ice.dao.model.UserPhoneInfoDO;
import org.apache.ibatis.annotations.Param;

public interface UserPhoneInfoDAO {
	/**
	 * 
	 * @param userPhoneInfoDO
	 */
	int insert(UserPhoneInfoDO userPhoneInfoDO);

	int update(UserPhoneInfoDO userPhoneInfoDO);

	void delete(long id);

	UserPhoneInfoDO getById(long id);

	/**
	 * 根据手机号查用户信息
	 *
	 * @param phone
	 * @return
	 */
	UserPhoneInfoDO getByPhone(@Param(value = "phone") String phone);

}
