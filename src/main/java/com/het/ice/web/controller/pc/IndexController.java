package com.het.ice.web.controller.pc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "pc/index.htm", method = { RequestMethod.GET })
	public String index() {
		return "index";
	}

	/**
	 * 登录页面
	 *
	 * @return
	 */
	@RequestMapping(value = "pc/login.htm", method = { RequestMethod.GET })
	public String login() {
		return "login";
	}

	/**
	 * 登出
	 *
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pc/loginout.htm", method = RequestMethod.GET)
	public String login(HttpSession httpSession, ModelMap model) {

		httpSession.invalidate();

		return "redirect:login.htm";
	}

}
