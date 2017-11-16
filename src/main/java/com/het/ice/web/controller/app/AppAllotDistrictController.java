package com.het.ice.web.controller.app;

import com.het.ice.model.AllotDistrict;
import com.het.ice.service.AllotDistrictService;
import com.het.ice.service.template.Result;
import com.het.ice.web.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 片区控制器
 *
 */
@Controller
@RequestMapping(value = "app/district")
public class AppAllotDistrictController {

	@Autowired
	private AllotDistrictService allotDistrictService;

	/**
	 * 查询全部
	 *
	 * @return
	 */
	@RequestMapping(value = "queryAll.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap queryAll() {
		WebResult webResult = new WebResult();

		Result<List<AllotDistrict>> result = allotDistrictService.queryAll();

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
