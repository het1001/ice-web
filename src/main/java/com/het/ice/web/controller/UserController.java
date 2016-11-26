package com.het.ice.web.controller;

import javax.servlet.http.HttpSession;

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
import com.het.ice.web.model.UserWO;
import com.het.ice.web.model.WebResult;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

    /**
     * 登录验证
     *
     * @param userWO
     * @param httpSession
     * @return
     */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap login(@RequestBody UserWO userWO, HttpSession httpSession) {
		ModelMap model = new ModelMap();

        RequestMethod.GET.toString();

		WebResult webResult = new WebResult(model);

		Result<User> result = userService.checkUser(userWO.getUserName(), userWO.getPassWord(), userWO.getType(), true);
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

	@RequestMapping(value = "/loginout.htm", method = RequestMethod.GET)
	public String login(HttpSession httpSession, ModelMap model) {

		httpSession.invalidate();

		return "redirect:login";
	}

	/**
	 *
	 *
	 * @param userWO
	 * @return
	 */
	@RequestMapping(value = "/registe", method = RequestMethod.POST)
	public @ResponseBody ModelMap registe(@RequestBody UserWO userWO) {

		WebResult webResult = new WebResult();

		User user = new User();
		user.setUserName(userWO.getUserName());
		user.setPassWord(userWO.getPassWord());
		user.setType(UserTypeEnum.getByCode(userWO.getType()));

		if (user.getType() == null) {
			user.setType(UserTypeEnum.NORMAL);
		}

		Result<Long> result = userService.createUser(user);
		if (result.isSuccess()) {
			if (result.getResult() != null) {
				webResult.setMessage(true, "用户注册成功");
				// 存入session
			} else {
				webResult.setMessage(false, "用户注册失败");
			}
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @param userWO
	 * @return
	 */
	@RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
	public @ResponseBody ModelMap modifyPwd(@RequestBody UserWO userWO) {

		WebResult webResult = new WebResult();

		Result<Boolean> result = userService.modifyPwd(userWO.getUserName(), userWO.getPassWord(), userWO.getType(),
				userWO.getNewPassWord());
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
	}

    public static void main() {

    }
}
