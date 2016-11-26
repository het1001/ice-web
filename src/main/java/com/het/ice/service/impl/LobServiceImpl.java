package com.het.ice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.het.ice.dao.LobDAO;
import com.het.ice.dao.model.LobDO;
import com.het.ice.dao.query.LobQuery;
import com.het.ice.enums.LobTypeEnum;
import com.het.ice.model.Lob;
import com.het.ice.service.LobService;
import com.het.ice.service.conv.LobConvert;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;

@Service
public class LobServiceImpl implements LobService {

	@Resource
	private LobDAO lobDao;

	@Resource
	private Template template;

	@Override
	public Result<Void> create(final Lob lob) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void excute() {
				lobDao.insert(LobConvert.conv(lob));
			}
		});
	}

	@Override
	public Result<Void> update(Lob lob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Void> delete(long lobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Lob> getById(long lobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<Lob>> queryByComId(final long comId) {
		return this.template.complete(new ResultCallback<List<Lob>>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(comId, "商品id");
			}

			@Override
			public void excute() {
				List<LobDO> lobDOs = lobDao.queryByComId(comId);
				returnValue = LobConvert.conv(lobDOs);
			}
		});
	}

	/**
	 * 
	 */
	@Override
	public Result<Lob> getByComIdAndType(final LobQuery lobQuery) {
		return this.template.complete(new ResultCallback<Lob>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(lobQuery.getComId(), "商品id");
				AssertUtil.isEmpty(lobQuery.getType(), "资源类型");
				AssertUtil.isNull(LobTypeEnum.getByCode(lobQuery.getType()), "资源类型在系统中不存在");
			}

			@Override
			public void excute() {
				returnValue = LobConvert.conv(lobDao.getByComIdAndType(lobQuery));
			}
		});
	}

	@Override
	public Result<Lob> getByKey(final String key) {
		return this.template.complete(new ResultCallback<Lob>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(key, "key");
			}

			@Override
			public void excute() {
				returnValue = LobConvert.conv(lobDao.getByName(key));
			}
		});
	}

	@Override
	public Result<String> getKeyByComId(final long comId) {
		return this.template.complete(new ResultCallback<String>() {

			@Override
			public void check() {
				// AssertUtil.isEmpty(key, "key");
			}

			@Override
			public void excute() {
				LobDO lobDO = lobDao.getLastByComId(comId);
				if (lobDO != null) {
					returnValue = lobDO.getName();
				}

				// returnValue = LobConvert.conv(lobDao.getByName(key));
			}
		});
	}

}
