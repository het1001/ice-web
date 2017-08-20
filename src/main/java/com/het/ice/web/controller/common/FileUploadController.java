package com.het.ice.web.controller.common;

import com.het.ice.enums.LobTypeEnum;
import com.het.ice.model.Lob;
import com.het.ice.service.LobService;
import com.het.ice.service.template.Result;
import com.het.ice.util.OssUtil;
import com.het.ice.web.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/9.
 */
@Controller
@RequestMapping(value = "common")
public class FileUploadController {

    @Autowired
    private LobService lobService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        WebResult webResult = new WebResult();

        String fileName = file.getOriginalFilename();
        String xff = fileName.substring(fileName.lastIndexOf("."));

        String code = OssUtil.putObject(file.getInputStream(), xff);

        Lob lob = new Lob();
        lob.setOssKey(code);
        lob.setIsUsed(0);
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
