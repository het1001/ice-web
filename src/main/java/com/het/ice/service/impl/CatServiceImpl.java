package com.het.ice.service.impl;

import com.het.ice.dao.CatDAO;
import com.het.ice.dao.model.AllotSalesmanDO;
import com.het.ice.dao.model.CatDO;
import com.het.ice.model.AllotSalesman;
import com.het.ice.model.Cat;
import com.het.ice.service.CatService;
import com.het.ice.service.conv.AllotSalesmanConvert;
import com.het.ice.service.conv.CatConvert;
import com.het.ice.service.exception.ParamCheckException;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import com.het.ice.util.CommonConstants;
import com.het.ice.web.request.CatWO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CatServiceImpl implements CatService {

    @Resource
    private CatDAO catDAO;

    @Resource
    private Template template;

    @Override
    public Result<Void> create(final CatWO catWO) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(catWO.getName(), "名称");
                AssertUtil.isNotNull(catDAO.getByName(catWO.getName()), "类目名称已存在");
            }

            @Override
            public void excute() {
                CatDO catDO = CatConvert.conv(catWO);
                catDO.setBizId(CommonConstants.DEFAULT_BIZ_ID);
                catDAO.insert(catDO);
            }
        });
    }

    @Override
    public Result<Void> update(CatWO catWO) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(catWO.getName(), "名称");
                CatDO catDO = catDAO.getByName(catWO.getName());
                if (catDO != null && catDO.getId() != NumberUtils.toLong(catWO.getId())) {
                    throw new ParamCheckException("类目名称已存在");
                }
            }

            @Override
            public void excute() {
                CatDO catDO = CatConvert.conv(catWO);
                catDAO.update(catDO);
            }
        });
    }

    @Override
    public Result<List<Cat>> queryAll() {
        return template.complete(new ResultCallback<List<Cat>>() {

            @Override
            public void excute() {
                List<CatDO> catDOS = catDAO.queryByBizId(CommonConstants.DEFAULT_BIZ_ID);
                returnValue = CatConvert.conv(catDOS);
            }

        });
    }
}
