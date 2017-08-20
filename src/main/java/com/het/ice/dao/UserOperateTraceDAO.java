package com.het.ice.dao;

import com.het.ice.dao.model.UserAuthCodeDO;
import com.het.ice.dao.model.UserOperateTraceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOperateTraceDAO {

	/**
	 *
	 * @param userOperateTraceDO
	 */
	int insert(UserOperateTraceDO userOperateTraceDO);

	/**
	 *
	 * @param phone
	 * @return
	 */
	List<UserAuthCodeDO> queryByPhone(@Param(value = "phone") String phone);
}
