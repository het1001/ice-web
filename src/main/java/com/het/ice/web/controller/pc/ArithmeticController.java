package com.het.ice.web.controller.pc;

import com.het.ice.model.Arithmetic;
import com.het.ice.service.ArithmeticService;
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
 * 算法控制器
 *
 */
@Controller
@RequestMapping(value = "pc/arithmetic")
public class ArithmeticController {

	@Autowired
	private ArithmeticService arithmeticService;

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "queryAll.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryAll() {
		WebResult webResult = new WebResult();

		Result<List<Arithmetic>> result = arithmeticService.queryAll();
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
