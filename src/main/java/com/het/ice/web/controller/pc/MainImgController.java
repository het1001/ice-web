package com.het.ice.web.controller.pc;

import com.het.ice.dao.query.AppImageQuery;
import com.het.ice.model.AppMainImg;
import com.het.ice.service.AppMainImgService;
import com.het.ice.service.conv.AppMainImgConvert;
import com.het.ice.service.template.Result;
import com.het.ice.web.controller.BaseController;
import com.het.ice.web.request.AppMainImageCreateWO;
import com.het.ice.web.request.AppMainImageDeleteWO;
import com.het.ice.web.request.AppMainImageEditWO;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/29.
 */

@Controller
@RequestMapping(value = "pc/appmainimg")
public class MainImgController extends BaseController{

    @Autowired
    private AppMainImgService appMainImgService;

    @RequestMapping(value = "queryList.json", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryList(String name, String active, String isMain, String pageNum, String pageSize) throws IOException {
        WebResult webResult = new WebResult();

        AppImageQuery query = new AppImageQuery();
        query.setName(name);

        if (StringUtils.isNotBlank(active)) {
            query.setActive(NumberUtils.toInt(active));
        }

        if (StringUtils.isNotBlank(isMain)) {
            query.setIsMain(NumberUtils.toInt(isMain));
        }

        Result<List<AppMainImg>> result = appMainImgService.queryByCondition(query, pageNum, pageSize);
        if (result.isSuccess()) {
            webResult.setData(true, result.getResult());
            webResult.setTotal(result.getTotal());
        } else {
            webResult.setMessage(false, "");
        }

        return webResult.getModel();
    }

    @RequestMapping(value = "create.json", method = { RequestMethod.POST })
    public @ResponseBody ModelMap create(@RequestBody AppMainImageCreateWO appMainImageCreateWO) {
        WebResult webResult = new WebResult();

        Result<Void> result = appMainImgService.create(AppMainImgConvert.conv(appMainImageCreateWO));

        if (result.isSuccess()) {
            webResult.setMessage(true, "保存成功");
        } else {
            webResult.setMessage(false, result.getErrorMsg());
        }

        return webResult.getModel();
    }

    /**
     *
     * @param appMainImageEditWO
     * @return
     */
    @RequestMapping(value = "edit.json", method = { RequestMethod.POST })
    public @ResponseBody ModelMap edit(@RequestBody AppMainImageEditWO appMainImageEditWO) {
        WebResult webResult = new WebResult();

        Result<Void> result = appMainImgService.update(AppMainImgConvert.conv(appMainImageEditWO));

        if (result.isSuccess()) {
            webResult.setMessage(true, "保存成功");
        } else {
            webResult.setMessage(false, result.getErrorMsg());
        }

        return webResult.getModel();
    }

    @RequestMapping(value = "delete.json", method = { RequestMethod.POST })
    public @ResponseBody ModelMap delete(@RequestBody AppMainImageDeleteWO appMainImageDeleteWO) {
        WebResult webResult = new WebResult();

        Result<Void> result = appMainImgService.delete(appMainImageDeleteWO.getId());
        if (result.isSuccess()) {
            webResult.setMessage(true, "删除成功");
        } else {
            webResult.setMessage(false, result.getErrorMsg());
        }

        return webResult.getModel();
    }
}
