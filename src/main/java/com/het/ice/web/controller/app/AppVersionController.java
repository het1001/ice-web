package com.het.ice.web.controller.app;

import com.het.ice.util.CommonConstants;
import com.het.ice.web.request.VersionRequest;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "app")
public class AppVersionController {

	/**
	 *
	 * @param version
	 * @return
	 */
	@RequestMapping(value = "isUpdate.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap isUpdate(@RequestBody VersionRequest version) {
		WebResult webResult = new WebResult();

		if (StringUtils.isNotEmpty(version.getVersion())) {
			webResult.setData(Integer.valueOf(version.getVersion()) < CommonConstants.APP_VERSION, CommonConstants.APP_VERSION);
		}

		return webResult.getModel();
	}
}
