package com.het.ice.web.controller.app;

import com.het.ice.model.UserShopInfo;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import com.het.ice.web.controller.BaseController;
import com.het.ice.web.request.UserShopRequest;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/29.
 */

@Controller
@RequestMapping(value = "app/shopinfo")
public class AppUserShopInfoController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getShopInfo.json", method = RequestMethod.POST)
    public @ResponseBody ModelMap mainimg(@RequestBody UserShopRequest userShopRequest) throws IOException {
        WebResult webResult = new WebResult();

        Result<UserShopInfo> result = userService.getLastShopInfo(userShopRequest.getPhone());
        if (result.isSuccess()) {
            webResult.setData(true, result.getResult());
        } else {
            webResult.setMessage(false, "");
        }

        return webResult.getModel();
    }
}
