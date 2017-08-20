package com.het.ice.web.controller.app;

import com.het.ice.model.User;
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

@Controller
@RequestMapping(value = "app/user")
public class AppUserController {

	@Autowired
	private UserService userService;

	private Logger logger = Logger.getLogger(AppUserController.class);

	/**
	 * 登录
	 *
	 * @param userLoginRequest
	 * @return
	 */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap login(@RequestBody UserLoginRequest userLoginRequest) {
		WebResult webResult = new WebResult();
		Result<User> result = userService.normalLogin(userLoginRequest);
		if (result.isSuccess()) {
			UserLoginResult userLoginResult = new UserLoginResult();
			userLoginResult.setState(result.getResult().getState().getCode());
			userLoginResult.setToken(result.getResult().getToken());
			webResult.setData(true, userLoginResult);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 获取手机验证码，也是初始化用户的第一步
	 *
	 * @return
	 */
	@RequestMapping(value = "/getInitAuthCode.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap getInitAuthCode(@RequestBody UserGetAuthCodeRequest request) {
		WebResult webResult = new WebResult(new ModelMap());
		Result<Void> result = userService.authAndInitUser(request);
		if (result.isSuccess()) {
			webResult.setResultCode(true, ResultCode.SUCCESS);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}
		return webResult.getModel();
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAuthCode.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap getAuthCode(@RequestBody UserGetAuthCodeRequest request) {
		WebResult webResult = new WebResult(new ModelMap());
		Result<Void> result = userService.getAuthCode(request);
		if (result.isSuccess()) {
			webResult.setResultCode(true, ResultCode.SUCCESS);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}
		return webResult.getModel();
	}

	/**
	 * 验证码校验
	 * @param userAuthRequest
     * @return
     */
	@RequestMapping(value = "/authCode.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap getAuthCode(@RequestBody UserAuthRequest userAuthRequest) {
		WebResult webResult = new WebResult(new ModelMap());
		Result<Void> result = userService.authCode(userAuthRequest);

		if (result.isSuccess()) {
			webResult.setResultCode(true, ResultCode.SUCCESS);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 设置密码
	 *
	 * @param userSetPassWordRequest
	 * @return
	 */
	@RequestMapping(value = "/setPassWord.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap setPassWord(@RequestBody UserSetPassWordRequest userSetPassWordRequest) {
		WebResult webResult = new WebResult(new ModelMap());
		Result<Void> result = userService.setPassWord(userSetPassWordRequest);
		if (result.isSuccess()) {
			webResult.setResultCode(true, ResultCode.SUCCESS);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	@RequestMapping(value = "/reSetPassWord.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap reSetPassWord(@RequestBody UserSetPassWordRequest userSetPassWordRequest) {
		WebResult webResult = new WebResult(new ModelMap());
		Result<Void> result = userService.setPassWord(userSetPassWordRequest);
		if (result.isSuccess()) {
			webResult.setResultCode(true, ResultCode.SUCCESS);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 提交商铺信息
	 *
	 * @param userShopRequest
	 * @return
	 */
	@RequestMapping(value = "/completeShopInfo.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap completeInfo(@RequestBody UserShopRequest userShopRequest) {
		WebResult webResult = new WebResult(new ModelMap());

		Result<Void> result = userService.completeShopInfo(userShopRequest);
		if (result.isSuccess()) {
			webResult.setResultCode(true, ResultCode.SUCCESS);
		} else {
			webResult.setResultCode(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @param userLoginRequest
	 * @return
	 */
	/*@RequestMapping(value = "/modifyPwd.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap modifyPwd(@RequestBody UserLoginRequest userLoginRequest) {

		WebResult webResult = new WebResult();

		Result<Boolean> result = userService.modifyPwd(userLoginRequest.getUserName(), userLoginRequest.getPassWord(), userLoginRequest.getType(),
				userLoginRequest.getNewPassWord());
		if (result.isSuccess()) {
			if (result.getResult() != null) {
				webResult.setMessage(true, "密码修改成功");
			} else {
				webResult.setMessage(false, "密码修改失败");
			}
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}*/
}
