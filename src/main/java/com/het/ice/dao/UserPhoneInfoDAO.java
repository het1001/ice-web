package com.het.ice.dao;

import com.het.ice.dao.model.UserPhoneInfoDO;
import org.apache.ibatis.annotations.Param;

public interface UserPhoneInfoDAO {
	/**
	 * 
	 * @param userPhoneInfoDO
	 */
	int insert(UserPhoneInfoDO userPhoneInfoDO);
}
