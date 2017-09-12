package com.het.ice.web.controller.pc;

import com.het.ice.dao.query.AllotSalesmanQuery;
import com.het.ice.enums.SalesmanTypeEnum;
import com.het.ice.model.AllotSalesman;
import com.het.ice.service.AllotSalesmanService;
import com.het.ice.service.conv.AllotSalesmanConvert;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.AllotDistrictWO;
import com.het.ice.web.request.AllotSalesmanWO;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 业务（配送）员控制器
 *
 */
@Controller
@RequestMapping(value = "pc/salesman")
public class AllotSalesmanController {

	@Autowired
	private AllotSalesmanService allotSalesmanService;

	/**
	 * 新建业务员
	 * 
	 * @param allotSalesmanWO
	 * @return
	 */
	@RequestMapping(value = "create.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap create(@RequestBody AllotSalesmanWO allotSalesmanWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = allotSalesmanService.create(AllotSalesmanConvert.conv(allotSalesmanWO));

		if (result.isSuccess()) {
			webResult.setMessage(true, "保存成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 更新业务员
	 *
	 * @param allotSalesmanWO
	 * @return
     */
	@RequestMapping(value = "update.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap update(@RequestBody AllotSalesmanWO allotSalesmanWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = allotSalesmanService.update(AllotSalesmanConvert.conv(allotSalesmanWO));

		if (result.isSuccess()) {
			webResult.setMessage(true, "更新成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 删除业务员
	 *
	 * @param allotSalesmanWO
	 * @return
	 */
	@RequestMapping(value = "delete.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap delete(@RequestBody AllotSalesmanWO allotSalesmanWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = allotSalesmanService.delete(NumberUtils.toLong(allotSalesmanWO.getId()));

		if (result.isSuccess()) {
			webResult.setMessage(true, "删除成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 检查唯一标识是否已存在
	 * 
	 * @param uniqueKey
	 * @return
	 */
	@RequestMapping(value = "check.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap check(String id, String uniqueKey) {
		WebResult webResult = new WebResult();

		if (StringUtils.isEmpty(uniqueKey)) {
			webResult.setData(true, true);
		} else {
			Result<AllotSalesman> result = allotSalesmanService.getByUniqueKey(uniqueKey);

			if (result.isSuccess()) {
				boolean checkResult = false;
				if (result.getResult() == null) {
					checkResult = true;
				} else if (result.getResult().getId() == NumberUtils.toLong(id, 0)) {
					checkResult = true;
				}

				webResult.setData(true, checkResult);
			} else {
				webResult.setMessage(false, result.getErrorMsg());
			}
		}

		return webResult.getModel();
	}

	/**
	 * 业务员列表
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String name, String pageNum, String pageSize) {
		WebResult webResult = new WebResult();

		AllotSalesmanQuery query = new AllotSalesmanQuery();
		query.setName(name);

		Result<List<AllotSalesman>> result = allotSalesmanService.queryByCondition(query, pageNum, pageSize);
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "queryAll.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryAll() {
		WebResult webResult = new WebResult();

		Result<List<AllotSalesman>> result = allotSalesmanService.queryAll();
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 按类型搜索全部业务员
	 *
	 * @return
	 */
	@RequestMapping(value = "queryByType.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryByType() {
		WebResult webResult = new WebResult();

		Result<List<AllotSalesman>> deliverResult = allotSalesmanService.queryByType(SalesmanTypeEnum.DELIVERYMEN.getCode());
		if (deliverResult.isSuccess()) {
			webResult.setSuccess(true);
			webResult.getModel().put("deliveryMen", deliverResult.getResult());

			Result<List<AllotSalesman>> salesmanResult = allotSalesmanService.queryByType(SalesmanTypeEnum.SALESMAN.getCode());

			if (salesmanResult.isSuccess()) {
				webResult.setSuccess(true);
				webResult.getModel().put("salesman", salesmanResult.getResult());
			} else {
				webResult.setMessage(false, salesmanResult.getErrorMsg());
			}

		} else {
			webResult.setMessage(false, deliverResult.getErrorMsg());
		}

		return webResult.getModel();
	}
}
