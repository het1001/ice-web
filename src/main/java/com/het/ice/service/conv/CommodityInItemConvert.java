package com.het.ice.service.conv;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.het.ice.dao.model.CommodityInItemDO;
import com.het.ice.model.CommodityInItem;
import com.het.ice.web.model.CommodityInItemWO;

public class CommodityInItemConvert {

	public static CommodityInItem conv(CommodityInItemDO comDO) {
		if (comDO == null) {
			return null;
		}

		CommodityInItem com = new CommodityInItem();
		com.setId(comDO.getId());
		com.setInId(comDO.getInId());
		com.setComId(comDO.getComId());
		com.setComName(comDO.getComName());
		com.setComStandard(comDO.getComStandard());
		com.setPricePi(comDO.getPricePi());
		com.setPriceBr(comDO.getPriceBr());
		com.setNum(comDO.getNum());
		com.setTotal(comDO.getTotal());
		com.setCreateTime(comDO.getCreateTime());
		com.setModifyTime(comDO.getModifyTime());
		com.setCreateUser(comDO.getCreateUser());
		com.setUpdateUser(comDO.getUpdateUser());

		return com;
	}

	public static CommodityInItemDO conv(CommodityInItem com) {
		if (com == null) {
			return null;
		}

		CommodityInItemDO comDO = new CommodityInItemDO();
		comDO.setId(com.getId());
		comDO.setInId(com.getInId());
		comDO.setComId(com.getComId());
		comDO.setComName(com.getComName());
		comDO.setComStandard(com.getComStandard());
		comDO.setPricePi(com.getPricePi());
		comDO.setPriceBr(com.getPriceBr());
		comDO.setNum(com.getNum());
		comDO.setTotal(com.getTotal());
		comDO.setCreateTime(com.getCreateTime());
		comDO.setModifyTime(com.getModifyTime());
		comDO.setCreateUser(com.getCreateUser());
		comDO.setUpdateUser(com.getUpdateUser());

		return comDO;
	}

	public static CommodityInItem conv(CommodityInItemWO comWO) {
		if (comWO == null) {
			return null;
		}

		CommodityInItem com = new CommodityInItem();
		com.setComId(NumberUtils.toLong(comWO.getComId()));
		com.setPricePi(comWO.getPricePi());
		com.setNum(comWO.getNum());
		com.setTotal(comWO.getPricePi() * comWO.getNum());
		return com;
	}

	public static List<CommodityInItem> conv(List<CommodityInItemDO> comDOs) {
		List<CommodityInItem> coms = new ArrayList<CommodityInItem>();
		if (comDOs == null || comDOs.size() == 0) {
			return coms;
		}

		for (CommodityInItemDO comDO : comDOs) {
			coms.add(conv(comDO));
		}

		return coms;
	}

	public static List<CommodityInItem> convs(List<CommodityInItemWO> comWOs) {
		List<CommodityInItem> coms = new ArrayList<CommodityInItem>();
		if (comWOs == null || comWOs.size() == 0) {
			return coms;
		}

		for (CommodityInItemWO comDO : comWOs) {
			coms.add(conv(comDO));
		}

		return coms;
	}
}
