package com.het.ice.dao;

import java.util.List;

import com.het.ice.dao.model.LobDO;
import com.het.ice.dao.query.LobQuery;

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

	LobDO getById(long id);

	LobDO getByName(String name);

	LobDO getLastByComId(long comId);

	LobDO getByComIdAndType(LobQuery lobQuery);

	List<LobDO> queryByComId(long comId);
}
