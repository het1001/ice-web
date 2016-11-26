package com.het.ice.service.conv;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.het.ice.dao.model.CommodityDO;
import com.het.ice.model.Commodity;
import com.het.ice.web.model.CommodityWO;

public class CommodityConvert {

	public static Commodity conv(CommodityDO comDO) {
		if (comDO == null) {
			return null;
		}

		Commodity com = new Commodity();
		com.setId(comDO.getId());
		com.setCode(comDO.getCode());
		com.setBizId(comDO.getBizId());
		com.setCatId(comDO.getCatId());
		com.setCreateTime(comDO.getCreateTime());
		com.setModifyTime(comDO.getModifyTime());
		com.setCreateUser(comDO.getCreateUser());
		com.setUpdateUser(comDO.getUpdateUser());
		com.setName(comDO.getName());
		com.setState(comDO.getState());
		com.setTotal(comDO.getTotal());
		com.setSales(comDO.getSales());
		com.setDesc(comDO.getDescription());

		com.setStandardPice(comDO.getStandardPice());
		com.setPricePi(comDO.getPricePi());
		com.setPriceBr(comDO.getPriceBr());
		com.setRetailPriceBr(comDO.getRetailPriceBr());
		com.setPersonType(comDO.getPersonType());
		com.setPosition(comDO.getPosition());
		com.setProfitPi(comDO.getProfitPi());
		com.setProfitBr(comDO.getProfitBr());

		com.setPromotion(comDO.getPromotion());
		com.setBrand(comDO.getBrand());
		com.setImgKey(comDO.getImgKey());

		return com;
	}

	public static CommodityDO conv(Commodity com) {
		if (com == null) {
			return null;
		}

		CommodityDO comDO = new CommodityDO();
		comDO.setId(com.getId());
		comDO.setCode(com.getCode());
		comDO.setBizId(com.getBizId());
		comDO.setCatId(com.getCatId());
		comDO.setCreateTime(com.getCreateTime());
		comDO.setModifyTime(com.getModifyTime());
		comDO.setCreateUser(com.getCreateUser());
		comDO.setUpdateUser(com.getUpdateUser());
		comDO.setName(com.getName());
		comDO.setState(com.getState());
		comDO.setTotal(com.getTotal());
		comDO.setSales(com.getSales());
		comDO.setDescription(com.getDesc());

		comDO.setStandardPice(com.getStandardPice());
		comDO.setPricePi(com.getPricePi());
		comDO.setPriceBr(com.getPriceBr());
		comDO.setRetailPriceBr(com.getRetailPriceBr());
		comDO.setPersonType(com.getPersonType());
		comDO.setPosition(com.getPosition());
		comDO.setProfitPi(com.getProfitPi());
		comDO.setProfitBr(com.getProfitBr());

		comDO.setPromotion(com.getPromotion());
		comDO.setBrand(com.getBrand());
		comDO.setImgKey(com.getImgKey());

		return comDO;
	}

	public static Commodity conv(CommodityWO commodityWO) {
		if (commodityWO == null) {
			return null;
		}

		Commodity com = new Commodity();
		com.setId(NumberUtils.toLong(commodityWO.getId(), 0));
		com.setName(commodityWO.getName());
		com.setBrand(commodityWO.getBrand());
		com.setDesc(commodityWO.getDesc());
		com.setPersonType(commodityWO.getPersonType());
		com.setPosition(commodityWO.getPosition());
		com.setPriceBr(commodityWO.getPriceBr());
		com.setPricePi(commodityWO.getPricePi());
		com.setPromotion(commodityWO.getPromotion());
		com.setRetailPriceBr(commodityWO.getRetailPriceBr());
		com.setStandardPice(commodityWO.getStandardPice());
		com.setImgKey(commodityWO.getFileKey());

		return com;
	}

	public static List<Commodity> conv(List<CommodityDO> comDOs) {
		List<Commodity> coms = new ArrayList<Commodity>();
		if (comDOs == null || comDOs.size() == 0) {
			return coms;
		}

		for (CommodityDO comDO : comDOs) {
			coms.add(conv(comDO));
		}

		return coms;
	}
}
