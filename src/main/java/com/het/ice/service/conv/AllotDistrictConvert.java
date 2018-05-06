package com.het.ice.service.conv;

import com.het.ice.dao.model.AllotDistrictDO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.model.AllotDistrict;
import com.het.ice.model.Commodity;
import com.het.ice.web.request.AllotDistrictWO;
import com.het.ice.web.request.CommodityWO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 片区转换器
 *
 */
public class AllotDistrictConvert {

	/**
	 * 数据模型转为业务模型
	 *
	 * @param allotDistrictDO
	 * @return
     */
	public static AllotDistrict conv(AllotDistrictDO allotDistrictDO) {
		if (allotDistrictDO == null) {
			return null;
		}

		AllotDistrict allotDistrict = new AllotDistrict();
		allotDistrict.setId(allotDistrictDO.getId());
		allotDistrict.setName(allotDistrictDO.getName());
		allotDistrict.setCreateTime(allotDistrictDO.getCreateTime());
		allotDistrict.setModifyTime(allotDistrictDO.getModifyTime());
		return allotDistrict;
	}

	/**
	 * 业务模型转为数据模型
	 *
	 * @param allotDistrict
	 * @return
     */
	public static AllotDistrictDO conv(AllotDistrict allotDistrict) {
		if (allotDistrict == null) {
			return null;
		}

		AllotDistrictDO allotDistrictDO = new AllotDistrictDO();
		allotDistrictDO.setId(allotDistrict.getId());
		allotDistrictDO.setName(allotDistrict.getName());
		allotDistrictDO.setCreateTime(allotDistrict.getCreateTime());
		allotDistrictDO.setModifyTime(allotDistrict.getModifyTime());
		return allotDistrictDO;
	}

	/**
	 * 前端模型转为业务模型
	 *
	 * @param allotDistrictWO
	 * @return
     */
	public static AllotDistrict conv(AllotDistrictWO allotDistrictWO) {
		if (allotDistrictWO == null) {
			return null;
		}

		AllotDistrict allotDistrict = new AllotDistrict();
		allotDistrict.setId(NumberUtils.toLong(allotDistrictWO.getId(), 0));
		allotDistrict.setName(allotDistrictWO.getName());
		return allotDistrict;
	}

	/**
	 * list-数据模型转为业务模型
	 *
	 * @param allotDistrictDOS
	 * @return
     */
	public static List<AllotDistrict> conv(List<AllotDistrictDO> allotDistrictDOS) {
		List<AllotDistrict> allotDistricts = new ArrayList<>();
		if (CollectionUtils.isEmpty(allotDistrictDOS)) {
			return allotDistricts;
		}

		for (AllotDistrictDO allotDistrictDO : allotDistrictDOS) {
			allotDistricts.add(conv(allotDistrictDO));
		}

		return allotDistricts;
	}
}
