package com.het.ice.service.conv;

import com.het.ice.dao.model.AllotSalesmanDO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.enums.SalesmanTypeEnum;
import com.het.ice.model.AllotDistrict;
import com.het.ice.model.AllotSalesman;
import com.het.ice.model.Commodity;
import com.het.ice.web.request.AllotSalesmanWO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务员转换器
 *
 */
public class AllotSalesmanConvert {

	/**
	 * 数据模型转为业务模型
	 *
	 * @param allotSalesmanDO
	 * @return
     */
	public static AllotSalesman conv(AllotSalesmanDO allotSalesmanDO) {
		if (allotSalesmanDO == null) {
			return null;
		}

		AllotSalesman allotSalesman = new AllotSalesman();
		allotSalesman.setId(allotSalesmanDO.getId());
		allotSalesman.setName(allotSalesmanDO.getName());
		allotSalesman.setUniqueKey(allotSalesmanDO.getUniqueKey());
		allotSalesman.setPhone(allotSalesmanDO.getPhone());
		allotSalesman.setSalesmanTypeEnum(SalesmanTypeEnum.getByCode(allotSalesmanDO.getType()));
		allotSalesman.setCreateTime(allotSalesmanDO.getCreateTime());
		allotSalesman.setModifyTime(allotSalesmanDO.getModifyTime());
		return allotSalesman;
	}

	/**
	 * 业务模型转为数据模型
	 *
	 * @param allotSalesman
	 * @return
     */
	public static AllotSalesmanDO conv(AllotSalesman allotSalesman) {
		if (allotSalesman == null) {
			return null;
		}

		AllotSalesmanDO allotSalesmanDO = new AllotSalesmanDO();
		allotSalesmanDO.setId(allotSalesman.getId());
		allotSalesmanDO.setName(allotSalesman.getName());
		allotSalesmanDO.setUniqueKey(allotSalesman.getUniqueKey());
		allotSalesmanDO.setPhone(allotSalesman.getPhone());
		allotSalesmanDO.setType(allotSalesman.getSalesmanTypeEnum().getCode());
		allotSalesmanDO.setCreateTime(allotSalesman.getCreateTime());
		allotSalesmanDO.setModifyTime(allotSalesman.getModifyTime());
		return allotSalesmanDO;
	}

	/**
	 * 业务员-前端模型转为业务模型
	 *
	 * @param allotSalesmanWO
	 * @return
     */
	public static AllotSalesman conv(AllotSalesmanWO allotSalesmanWO) {
		if (allotSalesmanWO == null) {
			return null;
		}

		AllotSalesman allotSalesman = new AllotSalesman();
		allotSalesman.setId(NumberUtils.toLong(allotSalesmanWO.getId(), 0));
		allotSalesman.setName(allotSalesmanWO.getName());
		allotSalesman.setUniqueKey(allotSalesmanWO.getUniqueKey());
		allotSalesman.setPhone(allotSalesmanWO.getPhone());
		allotSalesman.setSalesmanTypeEnum(SalesmanTypeEnum.getByCode(allotSalesmanWO.getType()));
		return allotSalesman;
	}

	/**
	 * list-数据模型转为业务模型
	 *
	 * @param allotSalesmanDOS
	 * @return
     */
	public static List<AllotSalesman> conv(List<AllotSalesmanDO> allotSalesmanDOS) {
		List<AllotSalesman> allotSalesmens = new ArrayList<>();
		if (CollectionUtils.isEmpty(allotSalesmanDOS)) {
			return allotSalesmens;
		}

		for (AllotSalesmanDO allotSalesmanDO : allotSalesmanDOS) {
			allotSalesmens.add(conv(allotSalesmanDO));
		}

		return allotSalesmens;
	}
}
