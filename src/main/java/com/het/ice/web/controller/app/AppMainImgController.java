package com.het.ice.web.controller.app;

import com.het.ice.service.AppMainImgService;
import com.het.ice.service.template.Result;
import com.het.ice.web.controller.BaseController;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/29.
 */

@Controller
public class AppMainImgController extends BaseController{

    @Autowired
    private AppMainImgService appMainImgService;

    @RequestMapping(value = "/app/getMainKey.json", method = RequestMethod.GET)
    public @ResponseBody ModelMap mainimg(HttpServletResponse res) throws IOException {
        WebResult webResult = new WebResult();

        Result<String> result = appMainImgService.getImgKey();
        if (result.isSuccess() && StringUtils.isNotBlank(result.getResult())) {
            webResult.setData(true, result.getResult());
        } else {
            webResult.setMessage(false, "");
        }

        return webResult.getModel();
    }
}
