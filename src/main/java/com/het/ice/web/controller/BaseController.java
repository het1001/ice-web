package com.het.ice.web.controller;

import com.het.ice.util.OssUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class BaseController {

    protected void handle(String name, HttpServletResponse res) throws IOException {
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            os.write(OssUtil.getObject(name));
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }
}
