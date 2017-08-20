package com.het.ice.service.impl;

import com.het.ice.dao.LobDAO;
import com.het.ice.model.Lob;
import com.het.ice.service.LobService;
import com.het.ice.service.conv.LobConvert;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void excute() {
				lobDao.update(LobConvert.conv(lob));
			}
		});
	}

	@Override
	public Result<Void> delete(long lobId) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void excute() {
				lobDao.delete(lobId);
			}
		});
	}
}
