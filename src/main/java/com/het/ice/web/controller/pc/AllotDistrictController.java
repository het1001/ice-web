package com.het.ice.web.controller.pc;

import com.het.ice.dao.query.AllotDistrictQuery;
import com.het.ice.enums.SalesmanTypeEnum;
import com.het.ice.model.AllotDistrict;
import com.het.ice.model.AllotSalesman;
import com.het.ice.service.AllotDistrictService;
import com.het.ice.service.conv.AllotDistrictConvert;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.AllotDistrictWO;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 片区控制器
 *
 */
@Controller
@RequestMapping(value = "pc/district")
public class AllotDistrictController {

	@Autowired
	private AllotDistrictService allotDistrictService;

	/**
	 * 新建片区
	 * 
	 * @param allotDistrictWO
	 * @return
	 */
	@RequestMapping(value = "create.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap create(@RequestBody AllotDistrictWO allotDistrictWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = allotDistrictService.create(AllotDistrictConvert.conv(allotDistrictWO),
				allotDistrictWO.getSalesmanIds());

		if (result.isSuccess()) {
			webResult.setMessage(true, "保存成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 更新片区
	 *
	 * @param allotDistrictWO
	 * @return
     */
	@RequestMapping(value = "update.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap update(@RequestBody AllotDistrictWO allotDistrictWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = allotDistrictService.update(AllotDistrictConvert.conv(allotDistrictWO),
				allotDistrictWO.getSalesmanIds());

		if (result.isSuccess()) {
			webResult.setMessage(true, "更新成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 删除片区
	 *
	 * @param allotDistrictWO
	 * @return
	 */
	@RequestMapping(value = "delete.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap delete(@RequestBody AllotDistrictWO allotDistrictWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = allotDistrictService.delete(NumberUtils.toLong(allotDistrictWO.getId()));

		if (result.isSuccess()) {
			webResult.setMessage(true, "删除成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 检查片区名称是否已存在
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "check.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap check(String id, String name) {
		WebResult webResult = new WebResult();

		Result<AllotDistrict> result = allotDistrictService.getByName(name);

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

		return webResult.getModel();
	}

	/**
	 * 片区列表
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String name, String pageNum, String pageSize) {
		WebResult webResult = new WebResult();

		AllotDistrictQuery query = new AllotDistrictQuery();
		query.setName(name);

		Result<List<AllotDistrict>> result = allotDistrictService.queryByCondition(query, pageNum, pageSize);

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
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "querySalesmanById.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryByType(String id) {
		WebResult webResult = new WebResult();

		Result<Map<String, List<String>>> result = allotDistrictService.getSalIdsByDisId(NumberUtils.toLong(id, 0));

		if (result.isSuccess()) {
			webResult.setSuccess(true);
			webResult.getModel().putAll(result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
