package com.het.ice.service;

import com.het.ice.dao.query.AppImageQuery;
import com.het.ice.model.AppMainImg;
import com.het.ice.service.template.Result;

import java.util.List;

public interface AppMainImgService {

	/**
	 *
	 * @return
	 */
	Result<String> getImgKey();

	/**
	 *
	 * @param query
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Result<List<AppMainImg>> queryByCondition(AppImageQuery query, String pageNum, String pageSize);

	/**
	 * 新建
	 *
	 * @param appMainImg
	 * @return
	 */
    Result<Void> create(AppMainImg appMainImg);

	/**
	 * 更新
	 *
	 * @param appMainImg
	 * @return
	 */
	Result<Void> update(AppMainImg appMainImg);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	Result<Void> delete(long id);
}
