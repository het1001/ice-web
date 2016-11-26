package com.het.ice.dao;

import org.apache.ibatis.annotations.Param;

import com.het.ice.dao.model.UserDO;

public interface UserDAO {
	/**
	 * 
	 * @param userDO
	 */
	int insert(UserDO userDO);

	int update(UserDO userDO);

	void delete(long id);

	UserDO getById(long id);

	/**
	 * 根据用户名、密码、用户类型获取用户
	 *
	 * @param userName
	 * @param passWord
	 * @param type
	 * @return
	 */
	UserDO getUser(@Param(value = "userName") String userName, @Param(value = "passWord") String passWord,
			@Param(value = "type") String type);

	/**
	 * 
	 * @param userName
	 * @param type
	 * @return
	 */
	UserDO getByUserName(@Param(value = "userName") String userName, @Param(value = "type") String type);

}
