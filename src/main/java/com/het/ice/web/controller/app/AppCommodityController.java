package com.het.ice.web.controller.app;

import com.het.ice.model.Commodity;
import com.het.ice.model.CommodityPic;
import com.het.ice.service.CommodityPicService;
import com.het.ice.service.CommodityService;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.CommodityRequest;
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

@Controller
@RequestMapping(value = "app/commodity")
public class AppCommodityController {

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private CommodityPicService commodityPicService;

	/**
	 * 商品列表
	 *
	 * @return
	 */
	@RequestMapping(value = "queryAllOnline.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap queryAllOnline() {
		WebResult webResult = new WebResult();

		Result<List<Commodity>> result = commodityService.queryAllOnline();
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryPicByComId.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap queryPicByComId(@RequestBody CommodityRequest request) {
		WebResult webResult = new WebResult();

		Result<List<CommodityPic>> result = commodityPicService.queryAllByComId(NumberUtils.toLong(request.getId()));
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
