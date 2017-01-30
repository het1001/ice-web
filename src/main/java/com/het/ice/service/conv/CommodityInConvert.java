package com.het.ice.service.conv;

import java.util.ArrayList;
import java.util.List;

import com.het.ice.dao.model.CommodityInDO;
import com.het.ice.model.CommodityIn;

public class CommodityInConvert {

	public static CommodityIn conv(CommodityInDO comInDO) {
		if (comInDO == null) {
			return null;
		}

		CommodityIn comIn = new CommodityIn();
		comIn.setId(comInDO.getId());
		comIn.setInNum(comInDO.getInNum());
		comIn.setDescription(comInDO.getDescription());
		comIn.setCreateTime(comInDO.getCreateTime());
		comIn.setModifyTime(comInDO.getModifyTime());
		comIn.setCreateUser(comInDO.getCreateUser());
		comIn.setUpdateUser(comInDO.getUpdateUser());

		return comIn;
	}

	public static CommodityInDO conv(CommodityIn com) {
		if (com == null) {
			return null;
		}

		CommodityInDO comDO = new CommodityInDO();
		comDO.setId(com.getId());
		comDO.setInNum(com.getInNum());
		comDO.setDescription(com.getDescription());
		comDO.setCreateTime(com.getCreateTime());
		comDO.setModifyTime(com.getModifyTime());
		comDO.setCreateUser(com.getCreateUser());
		comDO.setUpdateUser(com.getUpdateUser());

		return comDO;
	}

	public static List<CommodityIn> conv(List<CommodityInDO> comDOs) {
		List<CommodityIn> coms = new ArrayList<CommodityIn>();
		if (comDOs == null || comDOs.size() == 0) {
			return coms;
		}

		for (CommodityInDO comDO : comDOs) {
			coms.add(conv(comDO));
		}

		return coms;
	}
}
