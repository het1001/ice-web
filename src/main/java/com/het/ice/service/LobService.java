package com.het.ice.service;

import java.util.List;

import com.het.ice.dao.query.LobQuery;
import com.het.ice.model.Lob;
import com.het.ice.service.template.Result;

public interface LobService {

	public Result<Void> create(Lob lob);

	public Result<Void> update(Lob lob);

	public Result<Void> delete(long lobId);

	public Result<Lob> getById(long lobId);

	public Result<String> getKeyByComId(long comId);

	public Result<Lob> getByKey(String key);

	public Result<Lob> getByComIdAndType(LobQuery lobQuery);

	public Result<List<Lob>> queryByComId(long comId);
}
