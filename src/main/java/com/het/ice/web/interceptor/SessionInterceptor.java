package com.het.ice.web.interceptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.het.ice.model.User;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private List<String> allowUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		request.setCharacterEncoding("utf-8");
		String requestUrl = request.getRequestURI();

		if (requestUrl.indexOf(".htm") != -1 || requestUrl.indexOf(".json") != -1) {
			/**
			 * 登录页login.do不进行拦截
			 */
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

			User user = (User) request.getSession().getAttribute("user");
			System.out.println("user！！！！！！: " + user);
			if (user != null) {
				return true;
			} else {
				response.sendRedirect(request.getContextPath() + "/login.htm");
				return false;
			}
		} else {
			return true;
		}

		/*
		 * if (user == null) { response.sendRedirect(request.getContextPath() +
		 * "/login.htm"); }
		 */
	}

	/**
	 * @param allowUrls
	 *            the allowUrls to set
	 */
	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}

}
