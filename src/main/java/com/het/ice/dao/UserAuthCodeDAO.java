package com.het.ice.dao;

import com.het.ice.dao.model.UserAuthCodeDO;
import com.het.ice.dao.query.AuthCodeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserAuthCodeDAO {
	/**
	 *
	 * @param userAuthCodeDO
	 */
	int insert(UserAuthCodeDO userAuthCodeDO);

	/**
	 *
	 * @param userAuthCodeDO
	 */
	int update(UserAuthCodeDO userAuthCodeDO);

	/**
	 *
	 * @param phone
	 * @param code
	 * @return
	 */
	UserAuthCodeDO getByPhoneAndCode(@Param(value = "phone") String phone, @Param(value = "code") String code);

	/**
	 *
	 * @param phone
	 * @return
	 */
	List<UserAuthCodeDO> queryByPhone(String phone);

	/**
	 *
	 *
	 * @param phone
	 * @return
	 */
	List<UserAuthCodeDO> queryLastOneMinute(@Param(value = "phone") String phone);

	/**
	 *
	 * @param authCodeQuery
	 * @return
	 */
	int getCountBetween(AuthCodeQuery authCodeQuery);
}
