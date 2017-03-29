package com.het.ice.web.controller.app;

import com.aliyun.oss.common.utils.DateUtil;
import com.het.ice.enums.UserStateEnum;
import com.het.ice.enums.UserTypeEnum;
import com.het.ice.model.User;
import com.het.ice.model.UserPhoneInfo;
import com.het.ice.service.UserPhoneInfoService;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import com.het.ice.util.SmsUtil;
import com.het.ice.web.model.UserWO;
import com.het.ice.web.model.WebResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping(value = "app/user")
public class AppUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserPhoneInfoService userPhoneInfoService;

    /**
     * 登录验证
     *
     * @param userWO
     * @return
     */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public @ResponseBody ModelMap login(@RequestBody UserWO userWO) {
		WebResult webResult = new WebResult();

		Result<User> result = userService.checkNormalUser(userWO.getUserName(), userWO.getPassWord());
		if (result.isSuccess()) {
			webResult.setSuccess(true);
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 获取手机验证码
	 *
	 * @return
	 */
	@RequestMapping(value = "/getAuthCode.json", method = RequestMethod.GET)
	public @ResponseBody ModelMap getAuthCode(String phone, String imei, String brand, String model, String version, String sdkVersion) {
		WebResult webResult = new WebResult(new ModelMap());

		Result<User> result = userService.getByUserName(phone, UserTypeEnum.NORMAL);

		if (result.isSuccess() && result.getResult() != null) {
			User user = result.getResult();
			// 如果是刚创建的用户，说明还没有验证过，再发送一次验证码
			if (user.getState() == UserStateEnum.CREATE) {
				if (StringUtils.isNotBlank(user.getAuthCode())) {
					DateUtils.addHours(user.getAuthTime(), 1);
					if (DateUtils.addHours(user.getAuthTime(), 1).before(new Date())) {
						String authCode = SmsUtil.createAuthCode();
						boolean sendRe = SmsUtil.send(phone, authCode);
						if (sendRe) {
							user.setAuthCode(authCode);
							user.setAuthTime(new Date());
						}

						userService.updateUser(user);
						webResult.setResultCode(true, ResultCode.SUCCESS);
					} else {
						webResult.setResultCode(true, ResultCode.AUTHED);
					}
				}
			} else if (user.getState() == UserStateEnum.AUTHED) {
				webResult.setResultCode(true, ResultCode.AUTH_CODE_USEING);
			} else {
				webResult.setSuccess(true);
				webResult.setResultCode(true, ResultCode.USER_EXIST);
			}
		} else {
			User user = new User();
			user.setUserName(phone);
			user.setType(UserTypeEnum.NORMAL);
			user.setState(UserStateEnum.CREATE);

			String authCode = SmsUtil.createAuthCode();
			boolean sendRe = SmsUtil.send(phone, authCode);
			if (sendRe) {
				user.setAuthCode(authCode);
				user.setAuthTime(new Date());
			}

			Result<Long> createRe = userService.createUser(user);
			if (createRe.isSuccess() && createRe.getResult() > 0) {
				UserPhoneInfo info = new UserPhoneInfo();
				info.setUserId(createRe.getResult());
				info.setPhone(phone);
				info.setImei(imei);
				info.setBrand(brand);
				info.setModel(model);
				info.setVersion(version);
				info.setSdkVersion(sdkVersion);
				userPhoneInfoService.create(info);

				webResult.setResultCode(true, ResultCode.SUCCESS);
			} else {
				webResult.setResultCode(false, ResultCode.USER_CREATE_ERROR);
			}
		}

		return webResult.getModel();
	}

	/**
	 * 验证码校验
	 * @param phone
	 * @param code
     * @return
     */
	@RequestMapping(value = "/authCode.json", method = RequestMethod.GET)
	public @ResponseBody ModelMap getAuthCode(String phone, String code) {
		WebResult webResult = new WebResult(new ModelMap());

		Result<User> result = userService.getByUserName(phone, UserTypeEnum.NORMAL);

		if (result.isSuccess() && result.getResult() != null) {
			User user = result.getResult();
			if (StringUtils.isNotBlank(user.getAuthCode()) && StringUtils.isNotBlank(code) && StringUtils.equals(user.getAuthCode(), code)) {
				user.setState(UserStateEnum.AUTHED);
				userService.updateUser(user);
				webResult.setResultCode(true, ResultCode.SUCCESS);
			} else {
				webResult.setResultCode(false, ResultCode.AUTH_ERROR);
			}
		}

		return webResult.getModel();
	}

	@RequestMapping(value = "/completeInfo.json", method = RequestMethod.GET)
	public @ResponseBody ModelMap completeInfo(String phone, String address, String shopName, String shopImgKey) {
		WebResult webResult = new WebResult(new ModelMap());

		Result<User> result = userService.getByUserName(phone, UserTypeEnum.NORMAL);

		if (result.isSuccess() && result.getResult() != null) {
			User user = result.getResult();
			if (user.getState() == UserStateEnum.AUTHED) {
				user.setAddress(address);
				user.setShopName(shopName);
				user.setShopImgKey(shopImgKey);
				// 置为审核中
				user.setState(UserStateEnum.AUDITING);
				webResult.setResultCode(true, ResultCode.SUCCESS);
			} else {
				webResult.setResultCode(false, ResultCode.AUTH_ERROR);
			}
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
