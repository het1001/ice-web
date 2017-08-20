package com.het.ice.dao;

import com.het.ice.dao.model.UserDO;
import com.het.ice.dao.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
	 * 根据用户名、用户类型获取用户
	 *
	 * @param userName
	 * @param type
	 * @return
	 */
	UserDO getByUserName(@Param(value = "userName") String userName, @Param(value = "type") String type);

	/**
	 * 根据手机号查用户（只针对客户）
	 *
	 * @param phone
	 * @return
	 */
	UserDO getByPhone(@Param(value = "phone") String phone);

	/**
	 * 根据token、用户类型获取用户
	 *
	 * @param token
	 * @param type
	 * @return
	 */
	UserDO getByToken(@Param(value = "token") String token, @Param(value = "type") String type);


	/**
	 * 根据状态查询用户
	 *
	 * @param userQuery
	 * @return
     */
	List<UserDO> query(UserQuery userQuery);

	/**
	 * 根据状态查询用户总量
	 *
	 * @param query
	 * @return
     */
	int getCount(UserQuery query);
}
