package com.het.ice.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.het.ice.util.ApkUtil;
import com.het.ice.web.result.WebResult;

@Controller
@RequestMapping(value = "app")
public class AppController {

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "download.htm", method = { RequestMethod.GET })
	public String main() {
		return "appDownload";
	}

	@RequestMapping(value = "download", method = { RequestMethod.GET })
	public @ResponseBody ModelMap create(String version, HttpServletResponse response) throws IOException {

		WebResult webResult = new WebResult();

		File file = null;
		if (StringUtils.isNotEmpty(version)) {
			file = ApkUtil.getApkByVersion(version);
		} else {
			file = ApkUtil.getApk();
		}
		if (file.exists()) {
			response.setContentType("application/vnd.android.package-archive");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));

			FileInputStream reader = null;
			try {
				reader = new FileInputStream(file);

				OutputStream out = response.getOutputStream();

				byte[] b = new byte[1024];
				int n;
				while ((n = reader.read(b)) != -1) {
					out.write(b, 0, n);
				}

				out.flush();
				out.close();
			} finally {
				if (reader != null) {
					reader.close();
				}
			}
		} else {
			webResult.setMessage(false, "文件不存在");
		}

		return webResult.getModel();
	}
}
