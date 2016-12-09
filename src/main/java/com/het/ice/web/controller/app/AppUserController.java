package com.het.ice.web.controller.app;

import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import com.het.ice.web.model.UserWO;
import com.het.ice.web.model.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "app/user")
public class AppUserController {

	@Autowired
	private UserService userService;

    /**
     * 登录验证
     *
     * @param userWO
     * @return
     */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap login(@RequestBody UserWO userWO) {
		ModelMap model = new ModelMap();

		WebResult webResult = new WebResult(model);

		Result<User> result = userService.checkUser(userWO.getUserName(), userWO.getPassWord(), UserTypeEnum.NORMAL.getCode(), true);
		if (result.isSuccess()) {
			if (result.getResult() != null) {
				webResult.setSuccess(true);
			} else {
				webResult.setMessage(false, "用户名或密码错误");
			}
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}


	/**
	 *
	 *
	 * @param userWO
	 * @return
	 */
	@RequestMapping(value = "/registe.json", method = RequestMethod.POST)
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
	@RequestMapping(value = "/modifyPwd.json", method = RequestMethod.POST)
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
