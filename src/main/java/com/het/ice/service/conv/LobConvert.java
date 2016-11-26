package com.het.ice.service.conv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.het.ice.dao.model.LobDO;
import com.het.ice.enums.LobTypeEnum;
import com.het.ice.model.Lob;

public class LobConvert {

	public static Lob conv(LobDO lobDO) {
		if (lobDO == null) {
			return null;
		}

		Lob lob = new Lob();
		lob.setId(lobDO.getId());
		lob.setName(lobDO.getName());
		lob.setComId(lobDO.getComId());
		lob.setCreateTime(lobDO.getCreateTime());
		lob.setModifyTime(lobDO.getModifyTime());
		lob.setCreateUser(lobDO.getCreateUser());
		lob.setUpdateUser(lobDO.getUpdateUser());
		lob.setBlobValue(lobDO.getBlobValue());
		lob.setType(LobTypeEnum.getByCode(lobDO.getType()));
		lob.setActive(lobDO.getActive());

		return lob;
	}

	public static LobDO conv(Lob lob) {
		if (lob == null) {
			return null;
		}

		LobDO lobDO = new LobDO();
		lobDO.setId(lob.getId());
		lobDO.setName(lob.getName());
		lobDO.setComId(lob.getComId());
		lobDO.setCreateTime(lob.getCreateTime());
		lobDO.setModifyTime(lob.getModifyTime());
		lobDO.setCreateUser(lob.getCreateUser());
		lobDO.setUpdateUser(lob.getUpdateUser());
		lobDO.setBlobValue(lob.getBlobValue());
		lobDO.setType(lob.getType().getCode());
		lobDO.setActive(lob.getActive());

		return lobDO;
	}

	public static List<Lob> conv(List<LobDO> lobDOs) {
		List<Lob> lobs = new ArrayList<Lob>();
		if (CollectionUtils.isEmpty(lobDOs)) {
			return lobs;
		}

		for (LobDO lobDO : lobDOs) {
			lobs.add(conv(lobDO));
		}

		return lobs;
	}
}
