package com.het.ice.web.controller;

import com.het.ice.util.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "appAndroid")
public class AppController {

    /**
     * @return
     */
    @RequestMapping(value = "download.htm", method = {RequestMethod.GET})
    public ModelAndView main() {
        return  new ModelAndView(new RedirectView("http://ice2016.oss-cn-hangzhou.aliyuncs.com/iceApp" + CommonConstants.APP_VERSION + ".apk"));
    }
}
