package com.het.ice.web.controller.app;

import com.het.ice.enums.LobTypeEnum;
import com.het.ice.model.Lob;
import com.het.ice.service.template.Result;
import com.het.ice.util.OssUtil;
import com.het.ice.web.controller.BaseController;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/29.
 */

@Controller
public class AppLobController extends BaseController{

    @RequestMapping(value = "/app/uploadImage.json", method = RequestMethod.POST)
    public @ResponseBody ModelMap uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        WebResult webResult = new WebResult();

        String fileName = file.getOriginalFilename();
        String xff = fileName.substring(fileName.lastIndexOf("."));

        String code = OssUtil.putObject(file.getInputStream(), xff);
        file.getInputStream().close();
        webResult.setData(true, code);

        return webResult.getModel();
    }
}
