package com.het.ice.service.conv;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.het.ice.dao.model.CommodityDO;
import com.het.ice.model.Commodity;
import com.het.ice.web.request.CommodityWO;
import org.springframework.util.CollectionUtils;

/**
 * 商品转换器
 *
 */
public class CommodityConvert {

	/**
	 * 商品-数据模型转为业务模型
	 *
	 * @param comDO
	 * @return
     */
	public static Commodity conv(CommodityDO comDO) {
		if (comDO == null) {
			return null;
		}

		Commodity com = new Commodity();
		com.setId(comDO.getId());
		com.setCode(comDO.getCode());
		com.setBizId(comDO.getBizId());
		com.setPricCatId(comDO.getPricCatId());
		com.setPackCatId(comDO.getPackCatId());
		com.setPackCat(comDO.getPackCat());

		com.setCreateTime(comDO.getCreateTime());
		com.setModifyTime(comDO.getModifyTime());
		com.setCreateUser(comDO.getCreateUser());
		com.setUpdateUser(comDO.getUpdateUser());
		com.setName(comDO.getName());
		com.setState(comDO.getState());
		com.setTotal(comDO.getTotal());
		com.setSales(comDO.getSales());
		com.setDaySales(comDO.getDaySales());
		com.setWeekSales(comDO.getWeekSales());
		com.setDesc(comDO.getDescription());

		com.setStandardPice(comDO.getStandardPice());
		com.setPricePi(comDO.getPricePi());
		com.setPriceBr(comDO.getPriceBr());
		com.setRetailPriceBr(comDO.getRetailPriceBr());
		com.setPersonType(comDO.getPersonType());
		com.setPosition(comDO.getPosition());
		com.setProfitPi(comDO.getProfitPi());
		com.setProfitBr(comDO.getProfitBr());

		com.setImgKey(comDO.getImgKey());

		com.setBrandId(comDO.getBrandId());
		com.setBrand(comDO.getBrand());
		com.setBarCode(comDO.getBarCode());
		com.setBarImgKey(comDO.getBarImgKey());

		com.setWeight(comDO.getWeight());

		return com;
	}

	/**
	 * 商品-业务模型转为数据模型
	 *
	 * @param com
	 * @return
     */
	public static CommodityDO conv(Commodity com) {
		if (com == null) {
			return null;
		}

		CommodityDO comDO = new CommodityDO();
		comDO.setId(com.getId());
		comDO.setCode(com.getCode());
		comDO.setBizId(com.getBizId());
		comDO.setPricCatId(com.getPricCatId());
		comDO.setPackCatId(com.getPackCatId());
		comDO.setPackCat(com.getPackCat());

		comDO.setCreateTime(com.getCreateTime());
		comDO.setModifyTime(com.getModifyTime());
		comDO.setCreateUser(com.getCreateUser());
		comDO.setUpdateUser(com.getUpdateUser());
		comDO.setName(com.getName());
		comDO.setState(com.getState());
		comDO.setTotal(com.getTotal());
		comDO.setSales(com.getSales());
		comDO.setDaySales(com.getDaySales());
		comDO.setWeekSales(com.getWeekSales());
		comDO.setDescription(com.getDesc());

		comDO.setStandardPice(com.getStandardPice());
		comDO.setPricePi(com.getPricePi());
		comDO.setPriceBr(com.getPriceBr());
		comDO.setRetailPriceBr(com.getRetailPriceBr());
		comDO.setPersonType(com.getPersonType());
		comDO.setPosition(com.getPosition());
		comDO.setProfitPi(com.getProfitPi());
		comDO.setProfitBr(com.getProfitBr());

		comDO.setImgKey(com.getImgKey());

		comDO.setBrandId(com.getBrandId());
		comDO.setBrand(com.getBrand());
		comDO.setBarCode(com.getBarCode());
		comDO.setBarImgKey(com.getBarImgKey());

		comDO.setWeight(com.getWeight());

		return comDO;
	}

	/**
	 * 商品-前端模型转为业务模型
	 *
	 * @param commodityWO
	 * @return
     */
	public static Commodity conv(CommodityWO commodityWO) {
		if (commodityWO == null) {
			return null;
		}

		Commodity com = new Commodity();
		com.setId(NumberUtils.toLong(commodityWO.getId(), 0));
		com.setName(commodityWO.getName());
		com.setBrandId(NumberUtils.toLong(commodityWO.getBrandId()));
		com.setDesc(commodityWO.getDesc());
		com.setPersonType(commodityWO.getPersonType());
		com.setPosition(commodityWO.getPosition());
		com.setPriceBr(commodityWO.getPriceBr());
		com.setPricePi(commodityWO.getPricePi());
		com.setRetailPriceBr(commodityWO.getRetailPriceBr());
		com.setStandardPice(commodityWO.getStandardPice());
		com.setImgKey(commodityWO.getFileKey());
		com.setImgKeys(commodityWO.getFileKeys());
		com.setBarCode(commodityWO.getBarCode());
		com.setPricCatId(NumberUtils.toLong(commodityWO.getPricCatId()));
		com.setPackCatId(NumberUtils.toLong(commodityWO.getPackCatId()));
		com.setWeight(NumberUtils.toInt(commodityWO.getWeight()));

		return com;
	}

	/**
	 * 商品list-数据模型转为业务模型
	 *
	 * @param comDOs
	 * @return
     */
	public static List<Commodity> conv(List<CommodityDO> comDOs) {
		List<Commodity> coms = new ArrayList<>();
		if (CollectionUtils.isEmpty(comDOs)) {
			return coms;
		}

		for (CommodityDO comDO : comDOs) {
			coms.add(conv(comDO));
		}

		return coms;
	}
}
