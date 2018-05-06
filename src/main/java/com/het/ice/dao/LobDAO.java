package com.het.ice.dao;

import com.het.ice.dao.model.LobDO;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author Administrator
 *
 */
public interface LobDAO {

	long insert(LobDO lobDO);

	void update(LobDO lobDO);

	void delete(long id);

	LobDO getByOssKey(@Param(value = "ossKey") String ossKey);
}
