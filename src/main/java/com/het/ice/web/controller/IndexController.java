package com.het.ice.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "index.htm", method = { RequestMethod.GET })
	public String index() {
		return "index";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "login.htm", method = { RequestMethod.GET })
	public String login() {
		return "login";
	}

	@RequestMapping(value = "loginout.htm", method = RequestMethod.GET)
	public String login(HttpSession httpSession, ModelMap model) {

		httpSession.invalidate();

		return "redirect:login.htm";
	}

}
