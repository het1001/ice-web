package com.het.ice.service.impl;

import com.het.ice.dao.CatDAO;
import com.het.ice.dao.CommodityDAO;
import com.het.ice.dao.model.AllotSalesmanDO;
import com.het.ice.dao.model.CatDO;
import com.het.ice.dao.query.CatQuery;
import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.enums.CatTypeEnum;
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
    private CommodityDAO commodityDAO;

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
    public Result<Void> delete(final long id) {
        return template.complete(new ResultCallback<Void>() {

            private CatDO catDO;

            @Override
            public void check() {
                AssertUtil.moreThanZero(id, "类目id");
                catDO = catDAO.getById(id);
                AssertUtil.isNull(catDO, "类目");
                CommodityQuery query = new CommodityQuery();
                query.setBizId(CommonConstants.DEFAULT_BIZ_ID);
                query.setStart(0);
                query.setLimit(1);
                if (CatTypeEnum.PRICE == CatTypeEnum.getByCode(catDO.getType())) {
                    query.setPricCatId(catDO.getId());
                }

                if (CatTypeEnum.PRICE == CatTypeEnum.getByCode(catDO.getType())) {
                    query.setPricCatId(catDO.getId());
                }

                AssertUtil.isNotEmpty(commodityDAO.queryByCondition(query), "还有商品关联了该分类，请修改后重试");
            }

            @Override
            public void excute() {
                catDAO.delete(id);
            }
        });
    }

    @Override
    public Result<List<Cat>> queryByType(CatTypeEnum type) {
        return template.complete(new ResultCallback<List<Cat>>() {

            @Override
            public void excute() {
                CatQuery query = new CatQuery();
                query.setBizId(CommonConstants.DEFAULT_BIZ_ID);
                query.setType(type.getCode());

                List<CatDO> catDOS = catDAO.queryByType(query);
                returnValue = CatConvert.conv(catDOS);
            }

        });
    }
}
