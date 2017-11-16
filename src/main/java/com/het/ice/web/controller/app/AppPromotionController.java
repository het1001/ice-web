package com.het.ice.web.controller.app;

import com.het.ice.model.Promotion;
import com.het.ice.service.PromotionService;
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
 * 促销控制器
 *
 */
@Controller
@RequestMapping(value = "app/promotion")
public class AppPromotionController {

	@Autowired
	private PromotionService promotionService;

	@RequestMapping(value = "queryCurrent.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap queryCurrent() {
		WebResult webResult = new WebResult();

		Result<List<Promotion>> result = promotionService.queryCurrent();
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
