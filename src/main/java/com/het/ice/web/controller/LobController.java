package com.het.ice.web.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.het.ice.enums.LobTypeEnum;
import com.het.ice.model.Lob;
import com.het.ice.service.LobService;
import com.het.ice.service.template.Result;
import com.het.ice.util.OssUtil;
import com.het.ice.web.model.WebResult;

@Controller
@RequestMapping(value = "lob")
public class LobController {

	@Autowired
	private LobService lobService;

	@RequestMapping(value = "getKeyByComid.json", method = RequestMethod.GET)
	public @ResponseBody ModelMap getKeyByComid(String comId) throws IOException {

		WebResult webResult = new WebResult();
		Result<String> result = lobService.getKeyByComId(NumberUtils.toLong(comId));
		if (result.isSuccess() && result.getResult() != null) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, "获取附件key失败");
		}
		return webResult.getModel();
	}

	@RequestMapping(value = "/getByName", method = RequestMethod.GET)
	public @ResponseBody ModelMap list(String name, HttpServletResponse res) throws IOException {

		ModelMap map = new ModelMap();
		handle(name, res, map);
		return map;
	}

	private void handle(String name, HttpServletResponse res, ModelMap map) throws IOException {
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			os.write(OssUtil.getObject(name));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			os.close();
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ModelMap uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse res) throws IOException {
		WebResult webResult = new WebResult();

		String code = OssUtil.putObject(file.getInputStream());

		Lob lob = new Lob();
		lob.setName(code);
		lob.setActive(1);
		lob.setType(LobTypeEnum.THUMBNAIL);
		lob.setCreateUser("hou");
		lob.setUpdateUser("hou");
		Result<Void> result = lobService.create(lob);

		file.getInputStream().close();

		if (result.isSuccess()) {
			webResult.setData(true, code);
		} else {
			webResult.setMessage(false, "文件上传失败，" + result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
