package com.het.ice.web.controller.pc;

import com.het.ice.enums.CatTypeEnum;
import com.het.ice.model.Cat;
import com.het.ice.service.CatService;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.CatWO;
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

/**
 * 类目控制器
 *
 */
@Controller
@RequestMapping(value = "pc/cat")
public class CatController {

	@Autowired
	private CatService catService;

	/**
	 * 新建
	 * 
	 * @param catWO
	 * @return
	 */
	@RequestMapping(value = "create.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap create(@RequestBody CatWO catWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = catService.create(catWO);

		if (result.isSuccess()) {
			webResult.setMessage(true, "保存成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 更新
	 *
	 * @param catWO
	 * @return
     */
	@RequestMapping(value = "update.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap update(@RequestBody CatWO catWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = catService.update(catWO);

		if (result.isSuccess()) {
			webResult.setMessage(true, "更新成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 删除
	 *
	 * @param catWO
	 * @return
	 */
	@RequestMapping(value = "delete.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap delete(@RequestBody CatWO catWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = catService.delete(NumberUtils.toLong(catWO.getId()));

		if (result.isSuccess()) {
			webResult.setMessage(true, "删除成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 价格类型列表
	 *
	 * @return
	 */
	@RequestMapping(value = "queryPriceList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryPriceList() {
		return queryByType(CatTypeEnum.PRICE);
	}

	/**
	 * 价格类型列表
	 *
	 * @return
	 */
	@RequestMapping(value = "queryPackageList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryPackageList() {
		return queryByType(CatTypeEnum.PACKAGE);
	}

	/**
	 * 按类型查
	 *
	 * @param typeEnum
	 * @return
	 */
	private ModelMap queryByType(CatTypeEnum typeEnum) {
		WebResult webResult = new WebResult();
		Result<List<Cat>> result = catService.queryByType(typeEnum);
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
