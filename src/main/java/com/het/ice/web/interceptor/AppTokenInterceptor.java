package com.het.ice.web.interceptor;

import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppTokenInterceptor extends HandlerInterceptorAdapter {

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

		String token = request.getHeader("token");

		Result<User> result = userService.getByToken(token, UserTypeEnum.NORMAL);
		if (result.isSuccess() && result.getResult() != null) {
			return true;
		} else {
			response.getOutputStream().write(new String("{\"errorMsg\":\"invalid token!!!\"}").getBytes());
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
