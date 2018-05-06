package com.het.ice.service.impl;

import com.het.ice.dao.BrandDAO;
import com.het.ice.dao.CatDAO;
import com.het.ice.dao.CommodityDAO;
import com.het.ice.dao.model.BrandDO;
import com.het.ice.dao.model.CatDO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.dao.query.CatQuery;
import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.enums.CatTypeEnum;
import com.het.ice.model.Brand;
import com.het.ice.model.Cat;
import com.het.ice.model.Commodity;
import com.het.ice.service.BrandService;
import com.het.ice.service.CatService;
import com.het.ice.service.conv.BrandConvert;
import com.het.ice.service.conv.CatConvert;
import com.het.ice.service.exception.ParamCheckException;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import com.het.ice.util.CommonConstants;
import com.het.ice.web.request.BrandWO;
import com.het.ice.web.request.CatWO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandDAO brandDAO;

    @Resource
    private CommodityDAO commodityDAO;

    @Resource
    private Template template;

    @Override
    public Result<Void> create(BrandWO brandWO) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(brandWO.getName(), "名称");
                AssertUtil.isNotNull(brandDAO.getByName(brandWO.getName()), "名称已存在");
            }

            @Override
            public void excute() {
                BrandDO brandDO = new BrandDO();
                brandDO.setName(brandWO.getName());
                brandDAO.insert(brandDO);
            }
        });
    }

    @Override
    public Result<Void> update(BrandWO brandWO) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(brandWO.getName(), "名称");
                BrandDO brandDO = brandDAO.getByName(brandWO.getName());
                if (brandDO != null && brandDO.getId() != NumberUtils.toLong(brandWO.getId())) {
                    throw new ParamCheckException("名称已存在");
                }
            }

            @Override
            public void excute() {
                BrandDO brandDO = new BrandDO();
                brandDO.setId(NumberUtils.toLong(brandWO.getId()));
                brandDO.setName(brandWO.getName());
                brandDAO.update(brandDO);

                CommodityQuery query = new CommodityQuery();
                query.setBrandId(brandDO.getId());
                query.setStart(0);
                query.setLimit(1000);
                List<CommodityDO> commodityDOS = commodityDAO.queryByCondition(query);
                if (!CollectionUtils.isEmpty(commodityDOS)) {
                    for (CommodityDO commodityDO : commodityDOS) {
                        commodityDO.setBrand(brandWO.getName());
                        commodityDAO.update(commodityDO);
                    }
                }
            }
        });
    }

    @Override
    public Result<Void> delete(final long id) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.moreThanZero(id, "类目id");
                AssertUtil.isNull(brandDAO.getById(id), "类目");

                CommodityQuery query = new CommodityQuery();
                query.setBrandId(id);
                query.setStart(0);
                query.setLimit(1);
                AssertUtil.isNotEmpty(commodityDAO.queryByCondition(query), "还有商品关联了该品牌，请修改后重试");
            }

            @Override
            public void excute() {
                brandDAO.delete(id);
            }
        });
    }

    @Override
    public Result<List<Brand>> queryAll() {
        return template.complete(new ResultCallback<List<Brand>>() {

            @Override
            public void excute() {
                returnValue = BrandConvert.conv(brandDAO.queryAll());
            }
        });
    }
}
