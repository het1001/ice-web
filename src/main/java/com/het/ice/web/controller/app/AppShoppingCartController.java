package com.het.ice.web.controller.app;

import com.het.ice.model.ShoppingCart;
import com.het.ice.model.User;
import com.het.ice.service.ShoppingCartService;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.*;
import com.het.ice.web.result.UserLoginResult;
import com.het.ice.web.result.WebResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "app/shoppingcart")
public class AppShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

//	private Logger logger = Logger.getLogger(AppShoppingCartController.class);

	/**
	 * 创建
	 *
	 * @param shoppingCart
	 * @return
	 */
	@RequestMapping(value = "/create.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap create(@RequestBody ShoppingCart shoppingCart) {
		WebResult webResult = new WebResult();
		Result<Void> result = shoppingCartService.create(shoppingCart);
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 更新
	 *
	 * @param shoppingCart
	 * @return
	 */
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap update(@RequestBody ShoppingCart shoppingCart) {
		WebResult webResult = new WebResult();
		Result<Void> result = shoppingCartService.update(shoppingCart);
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 删除
	 *
	 * @param shoppingCart
	 * @return
	 */
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap delete(@RequestBody ShoppingCart shoppingCart) {
		WebResult webResult = new WebResult();
		Result<Void> result = shoppingCartService.delete(shoppingCart.getId());
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 根据phone查询
	 *
	 * @param shoppingCart
	 * @return
	 */
	@RequestMapping(value = "/queryByPhone.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap queryByPhone(@RequestBody ShoppingCart shoppingCart) {
		WebResult webResult = new WebResult();
		Result<List<ShoppingCart>> result = shoppingCartService.queryByPhone(shoppingCart.getPhone());
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
