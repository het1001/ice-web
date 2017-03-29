package com.het.ice.web.interceptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.het.ice.enums.UserTypeEnum;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.het.ice.model.User;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private List<String> allowUrls;

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		request.setCharacterEncoding("utf-8");
		String requestUrl = request.getRequestURI();

		for (String url : allowUrls) {
			if (requestUrl.endsWith(url)) {
				return true;
			}

			Pattern pattern = Pattern.compile(url);
			Matcher matcher = pattern.matcher(requestUrl);
			if (matcher.find()) {
				return true;
			}
		}

		Pattern pattern = Pattern.compile("^/ice/app/.*.json$");
		Matcher matcher = pattern.matcher(requestUrl);
		if (matcher.matches()) {
			String token = request.getParameter("token");
			Result<User> result = userService.getByToken(token, UserTypeEnum.NORMAL);
			if (result.isSuccess() && result.getResult() != null) {
				return true;
			} else {
				response.getOutputStream().write(new String("{\"errorMsg\":\"invalid token!!!\"}").getBytes());
				return false;
			}
		}

		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/login.htm");
			return false;
		}
	}

	/**
	 * @param allowUrls
	 *            the allowUrls to set
	 */
	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}

}
