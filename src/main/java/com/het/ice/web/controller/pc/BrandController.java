package com.het.ice.web.controller.pc;

import com.het.ice.model.Brand;
import com.het.ice.service.BrandService;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.BrandWO;
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
 * 品牌控制器
 *
 */
@Controller
@RequestMapping(value = "pc/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	/**
	 * 新建
	 * 
	 * @param brandWO
	 * @return
	 */
	@RequestMapping(value = "create.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap create(@RequestBody BrandWO brandWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = brandService.create(brandWO);

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
	 * @param brandWO
	 * @return
     */
	@RequestMapping(value = "update.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap update(@RequestBody BrandWO brandWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = brandService.update(brandWO);

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
	 * @param brandWO
     * @return
     */
	@RequestMapping(value = "delete.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap delete(@RequestBody BrandWO brandWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = brandService.delete(NumberUtils.toLong(brandWO.getId()));

		if (result.isSuccess()) {
			webResult.setMessage(true, "删除成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 全部列表
	 *
	 * @return
	 */
	@RequestMapping(value = "queryAll.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryAll() {
		WebResult webResult = new WebResult();
		Result<List<Brand>> result = brandService.queryAll();
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
