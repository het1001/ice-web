package com.het.ice.service;

import com.het.ice.model.UserPhoneInfo;
import com.het.ice.service.template.Result;

public interface UserPhoneInfoService {

	/**
	 * 创建
	 * 
	 * @param userPhoneInfo
	 * @return
	 */
	Result<Long> create(UserPhoneInfo userPhoneInfo);
}
