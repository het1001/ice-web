package com.het.ice.service.conv;

import com.het.ice.dao.model.CommodityPicDO;
import com.het.ice.model.CommodityPic;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品图片转换器
 *
 */
public class CommodityPicConvert {

	/**
	 * 商品图片-数据模型转为业务模型
	 *
	 * @param comPicDO
	 * @return
     */
	public static CommodityPic conv(CommodityPicDO comPicDO) {
		if (comPicDO == null) {
			return null;
		}

		CommodityPic comPic = new CommodityPic();
		comPic.setId(comPicDO.getId());
		comPic.setCreateTime(comPicDO.getCreateTime());
		comPic.setModifyTime(comPicDO.getModifyTime());
		comPic.setComId(comPicDO.getComId());
		comPic.setIsMain(comPicDO.getIsMain());
		comPic.setPicKey(comPicDO.getPicKey());

		return comPic;
	}

	/**
	 * 商品图片-业务模型转为数据模型
	 *
	 * @param comPic
	 * @return
     */
	public static CommodityPicDO conv(CommodityPic comPic) {
		if (comPic == null) {
			return null;
		}

		CommodityPicDO comPicDO = new CommodityPicDO();
		comPicDO.setId(comPic.getId());
		comPicDO.setCreateTime(comPic.getCreateTime());
		comPicDO.setModifyTime(comPic.getModifyTime());
		comPicDO.setComId(comPic.getComId());
		comPicDO.setIsMain(comPic.getIsMain());
		comPicDO.setPicKey(comPic.getPicKey());

		return comPicDO;
	}

	/**
	 * 商品图片list-数据模型转为业务模型
	 *
	 * @param comPicDOs
	 * @return
     */
	public static List<CommodityPic> conv(List<CommodityPicDO> comPicDOs) {
		List<CommodityPic> commodityPics = new ArrayList<>();
		if (CollectionUtils.isEmpty(comPicDOs)) {
			return commodityPics;
		}

		for (CommodityPicDO commodityPicDO : comPicDOs) {
			commodityPics.add(conv(commodityPicDO));
		}

		return commodityPics;
	}
}
