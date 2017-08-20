package com.het.ice.service.impl;

import com.het.ice.dao.AppMainImgDAO;
import com.het.ice.dao.model.AppMainImgDO;
import com.het.ice.dao.query.AppImageQuery;
import com.het.ice.model.AppMainImg;
import com.het.ice.service.AppMainImgService;
import com.het.ice.service.conv.AppMainImgConvert;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/29.
 */
@Service
public class AppMainImgServiceImpl implements AppMainImgService {

    @Resource
    private AppMainImgDAO appMainImgDAO;

    @Resource
    private Template template;

    @Override
    public Result<String> getImgKey() {
        return template.complete(new ResultCallback<String>() {

            @Override
            public void excute() {
                List<AppMainImgDO> appMainImgDOS = appMainImgDAO.queryActiveImage();
                if (!CollectionUtils.isEmpty(appMainImgDOS)) {
                    Random random = new Random(new Date().getTime());
                    returnValue = appMainImgDOS.get(random.nextInt(appMainImgDOS.size())).getImageKey();
                }
            }
        });
    }

    @Override
    public Result<List<AppMainImg>> queryByCondition(AppImageQuery query, String pageNum, String pageSize) {
        return template.pageQuery(new PageResultCallback<List<AppMainImg>>() {

            @Override
            public void excute(int start, int size) {
                query.setStart(start);
                query.setLimit(size);

                List<AppMainImgDO> appMainImgDOS = appMainImgDAO.queryByCondition(query);
                returnValue = AppMainImgConvert.conv(appMainImgDOS);
                total = appMainImgDAO.getCountByCondition(query);
            }

        }, pageNum, pageSize);
    }

    @Override
    public Result<Void> create(AppMainImg appMainImg) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(appMainImg.getName(), "名称");
                AssertUtil.isEmpty(appMainImg.getImageKey(), "图片");
            }

            @Override
            public void excute() {
                appMainImg.setActive(0);

                appMainImgDAO.insert(AppMainImgConvert.conv(appMainImg));
            }
        });
    }

    @Override
    public Result<Void> update(AppMainImg appMainImg) {
        return template.complete(new ResultCallback<Void>() {

            private AppMainImgDO appMainImgDO;

            @Override
            public void check() {
                AssertUtil.moreThanZero(appMainImg.getId(), "ID");
                appMainImgDO = appMainImgDAO.getById(appMainImg.getId());
                AssertUtil.isNull(appMainImgDO, "图片对象为空");
            }

            @Override
            public void excute() {
                appMainImgDO.setActive(appMainImg.getActive());
                appMainImgDAO.update(appMainImgDO);
            }
        });
    }

    @Override
    public Result<Void> delete(long id) {
        return template.complete(new ResultCallback<Void>() {

            private AppMainImgDO appMainImgDO;

            @Override
            public void check() {
                AssertUtil.moreThanZero(id, "ID");
                appMainImgDO = appMainImgDAO.getById(id);
                AssertUtil.isNull(appMainImgDO, "图片对象为空");
            }

            @Override
            public void excute() {
                appMainImgDAO.delete(id);
            }
        });
    }
}
