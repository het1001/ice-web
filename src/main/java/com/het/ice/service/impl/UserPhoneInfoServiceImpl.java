package com.het.ice.service.impl;

import com.het.ice.dao.UserPhoneInfoDAO;
import com.het.ice.dao.model.UserPhoneInfoDO;
import com.het.ice.model.UserPhoneInfo;
import com.het.ice.service.UserPhoneInfoService;
import com.het.ice.service.conv.UserPhoneInfoConvert;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserPhoneInfoServiceImpl implements UserPhoneInfoService {

	@Resource
	private UserPhoneInfoDAO userPhoneInfoDAO;

	@Resource
	private Template template;

	/**
	 *
	 * 
	 */
	@Override
	public Result<Long> create(final UserPhoneInfo userPhoneInfo) {

		return template.complete(new ResultCallback<Long>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(userPhoneInfo.getPhone(), "手机号");
			}

			@Override
			public void excute() {

				UserPhoneInfoDO userPhoneInfoDO = UserPhoneInfoConvert.conv(userPhoneInfo);

				System.out.println("*******************************_" + userPhoneInfoDO.getBrand());

				userPhoneInfoDAO.insert(userPhoneInfoDO);
				System.out.println("*******************************_" + userPhoneInfoDO.getId());
				returnValue = userPhoneInfoDO.getId();
			}

		});
	}
}
