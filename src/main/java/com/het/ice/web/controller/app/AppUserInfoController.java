package com.het.ice.web.controller.app;

import com.het.ice.model.UserInfo;
import com.het.ice.service.UserService;
import com.het.ice.service.template.Result;
import com.het.ice.web.controller.BaseController;
import com.het.ice.web.request.UserInfoRequest;
import com.het.ice.web.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/29.
 */
@Controller
@RequestMapping(value = "app/shopinfo")
public class AppUserInfoController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getShopInfo.json", method = RequestMethod.POST)
    public @ResponseBody ModelMap mainimg(@RequestBody UserInfoRequest userInfoRequest) throws IOException {
        WebResult webResult = new WebResult();

        Result<UserInfo> result = userService.getLastUserInfo(userInfoRequest.getPhone());
        if (result.isSuccess()) {
            webResult.setData(true, result.getResult());
        } else {
            webResult.setMessage(false, "");
        }

        return webResult.getModel();
    }
}
