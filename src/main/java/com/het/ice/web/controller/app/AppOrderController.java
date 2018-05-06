package com.het.ice.web.controller.app;

import com.het.ice.model.Order;
import com.het.ice.service.OrderService;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.OrderWO;
import com.het.ice.web.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "app/order")
public class AppOrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 创建
	 *
	 * @param orderWO
	 * @return
	 */
	@RequestMapping(value = "/create.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap create(@RequestBody OrderWO orderWO) {
		WebResult webResult = new WebResult();
		Result<String> result = orderService.create(orderWO);
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @param orderWO
	 * @return
	 */
	@RequestMapping(value = "/cancel.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap cancel(@RequestBody OrderWO orderWO) {
		WebResult webResult = new WebResult();
		Result<Void> result = orderService.cancel(orderWO.getId(), orderWO.getPhone());
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @param orderWO
	 * @return
	 */
	@RequestMapping(value = "complete.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap complete(@RequestBody OrderWO orderWO) {
		WebResult webResult = new WebResult();
		Result<Void> result = orderService.complete(orderWO.getId(), orderWO.getPhone());
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}
		return webResult.getModel();
	}

	/**
	 * 根据phone查询
	 *
	 * @param orderWO
	 * @return
	 */
	@RequestMapping(value = "/queryByPhone.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap queryByPhone(@RequestBody OrderWO orderWO) {
		WebResult webResult = new WebResult();
		Result<List<Order>> result = orderService.queryByPhone(orderWO.getPhone());
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
