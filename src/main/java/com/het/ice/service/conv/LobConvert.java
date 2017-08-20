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
		lob.setOssKey(lobDO.getOssKey());
		lob.setIsUsed(lobDO.getIsUsed());
		lob.setWhereUse(lobDO.getWhereUse());
		lob.setCreateTime(lobDO.getCreateTime());
		lob.setModifyTime(lobDO.getModifyTime());
		return lob;
	}

	public static LobDO conv(Lob lob) {
		if (lob == null) {
			return null;
		}

		LobDO lobDO = new LobDO();
		lobDO.setId(lob.getId());
		lobDO.setOssKey(lob.getOssKey());
		lobDO.setIsUsed(lob.getIsUsed());
		lobDO.setWhereUse(lob.getWhereUse());
		lobDO.setCreateTime(lob.getCreateTime());
		lobDO.setModifyTime(lob.getModifyTime());
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
