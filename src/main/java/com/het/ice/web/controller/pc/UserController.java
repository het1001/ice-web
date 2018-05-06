package com.het.ice.web.controller.pc;

import javax.servlet.http.HttpSession;

import com.het.ice.dao.query.UserQuery;
import com.het.ice.model.UserInfo;
import com.het.ice.web.request.UserActionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.UserLoginRequest;
import com.het.ice.web.result.WebResult;

import java.util.List;

@Controller
@RequestMapping(value = "pc/user")
public class UserController {

	@Autowired
	private UserService userService;

    /**
     * 登录验证
     *
     * @param userLoginRequest
     * @param httpSession
     * @return
     */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap login(@RequestBody UserLoginRequest userLoginRequest, String anchor, HttpSession httpSession) {
		ModelMap model = new ModelMap();

		WebResult webResult = new WebResult(model);
		Result<User> result = userService.bizLogin(userLoginRequest.getUserName(), userLoginRequest.getPassWord(), true);
		if (result.isSuccess()) {
			if (result.getResult() != null) {
				webResult.setSuccess(true);
				httpSession.setAttribute("user", result.getResult());
			} else {
				webResult.setMessage(false, "用户名或密码错误");
			}
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 根据手机号获取用户
	 *
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/getUser.json", method = RequestMethod.GET)
	public @ResponseBody ModelMap checkUser(String phone) {
		WebResult webResult = new WebResult();
		Result<User> result = userService.getByUserName(phone, UserTypeEnum.NORMAL);
		if (result.isSuccess()) {
			webResult.setSuccess(true);
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 登出
	 *
	 * @param httpSession
     * @return
     */
	@RequestMapping(value = "/loginout.htm", method = RequestMethod.GET)
	public String login(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:login";
	}

	/**
	 *
	 * @param state
	 * @param pageNum
	 * @param pageSize
     * @return
     */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String phone, String state, String freezerType, String pageNum, String pageSize) {
		WebResult webResult = new WebResult();

		UserQuery query = new UserQuery();
		query.setPhone(phone);
		query.setState(state);
		query.setFreezerType(freezerType);

		Result<List<User>> result = userService.query(query, pageNum, pageSize);

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 审核店铺信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "auditShopInfo.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap auditShopInfo(@RequestBody UserActionRequest request) {
		WebResult webResult = new WebResult();
		Result<Void> result = userService.auditUserInfo(request);
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	@RequestMapping(value = "getLastShopInfo.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap getLastShopInfo(String phone) {
		WebResult webResult = new WebResult();
		Result<UserInfo> result = userService.getLastUserInfo(phone);
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	@RequestMapping(value = "getAccessShopInfo.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap getAccessShopInfo(String phone) {
		WebResult webResult = new WebResult();
		Result<UserInfo> result = userService.getAccessUserInfo(phone);
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 冻结
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "freeae.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap freeae(@RequestBody UserActionRequest request) {
		WebResult webResult = new WebResult();
		Result<Void> result = userService.freeae(request.getId());
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 解冻
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "unFreeae.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap unFreeae(@RequestBody UserActionRequest request) {
		WebResult webResult = new WebResult();
		Result<Void> result = userService.unFreeae(request.getId());
		if (result.isSuccess()) {
			webResult.setSuccess(true);
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
