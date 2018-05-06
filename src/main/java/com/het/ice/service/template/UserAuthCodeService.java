package com.het.ice.service.template;

import com.het.ice.model.UserAuthCode;

public interface UserAuthCodeService {

	/**
	 * 创建
	 *
	 * @param userAuthCode
	 * @return
	 */
	Result<Long> create(UserAuthCode userAuthCode);

	/**
	 * 设置为使用状态
	 *
	 * @param phone
	 * @param code
	 * @return
	 */
	Result<Boolean> setUsed(String phone, String code);

	/**
	 * 根据手机号和验证码查询实例
	 *
	 * @param phone
	 * @param code
	 * @return
	 */
	Result<UserAuthCode> getByPhoneAndCode(String phone, String code);

	/**
	 * 根据手机号查询最新的验证码
	 *
	 * @param phone
	 * @return
	 */
	Result<UserAuthCode> getLastByPhone(String phone);

	/**
	 *
	 *
	 * @param phone
	 * @return
	 */
	Result<Boolean> isLargeCount(String phone);
}
