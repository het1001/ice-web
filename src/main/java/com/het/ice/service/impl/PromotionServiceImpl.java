package com.het.ice.service.impl;

import com.het.ice.dao.CommodityDAO;
import com.het.ice.dao.PromotionDAO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.dao.model.PromotionDO;
import com.het.ice.dao.query.PromotionQuery;
import com.het.ice.enums.PromotionStateEnum;
import com.het.ice.model.Promotion;
import com.het.ice.service.PromotionService;
import com.het.ice.service.conv.PromotionConvert;
import com.het.ice.service.exception.BizException;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Resource
    private Template template;

    @Resource
    private PromotionDAO promotionDAO;

    @Resource
    private CommodityDAO commodityDAO;

    @Override
    public Result<Void> create(Promotion promotion) {
        return template.complete(new ResultCallback<Void>() {

            private CommodityDO commodityDO;

            @Override
            public void check() {
                AssertUtil.moreThanZero(promotion.getComId(), "商品id");
                AssertUtil.moreThanZero(promotion.getArithId(), "算法id");

                commodityDO = commodityDAO.getById(promotion.getComId());
                AssertUtil.isNull(commodityDO, "商品不存在");
            }

            @Override
            public void excute() {
                promotion.setState(PromotionStateEnum.OFF_LINE);
                promotion.setComName(commodityDO.getName());

                PromotionDO promotionDO = PromotionConvert.conv(promotion);
                promotionDAO.insert(promotionDO);
            }
        });
    }

    @Override
    public Result<Void> update(Promotion promotion) {
        return template.complete(new ResultCallback<Void>() {

            private CommodityDO commodityDO;

            private PromotionDO promotionDO;

            @Override
            public void check() {
                AssertUtil.moreThanZero(promotion.getComId(), "商品id");
                AssertUtil.moreThanZero(promotion.getArithId(), "算法id");

                commodityDO = commodityDAO.getById(promotion.getComId());
                AssertUtil.isNull(commodityDO, "商品不存在");

                promotionDO = promotionDAO.getById(promotion.getId());
                AssertUtil.isNull(promotionDO, "促销模型不存在");
            }

            @Override
            public void excute() {
                promotion.setState(PromotionStateEnum.getByCode(promotionDO.getState()));
                promotion.setComName(commodityDO.getName());
                promotionDAO.update(PromotionConvert.conv(promotion));
            }
        });
    }

    @Override
    public Result<Void> updateState(long id, PromotionStateEnum state) {
        return template.complete(new ResultCallback<Void>() {

            private PromotionDO promotionDO;

            @Override
            public void check() {
                promotionDO = promotionDAO.getById(id);
                AssertUtil.isNull(promotionDO, "促销不存在");
            }

            @Override
            public void excute() {
                if (state == PromotionStateEnum.ON_LINE && promotionDO.getDeadline().before(new Date())) {
                    throw new BizException("该促销已结束，请重新设置生效时间后启用");
                }

                promotionDO.setState(state.getCode());
                promotionDAO.update(promotionDO);
            }
        });
    }

    @Override
    public Result<Void> delete(Long id) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.moreThanZero(id, "id");
            }

            @Override
            public void excute() {
                promotionDAO.delete(id);
            }
        });
    }

    @Override
    public Result<List<Promotion>> queryByCondition(PromotionQuery query, String pageNum, String pageSize) {
        return template.pageQuery(new PageResultCallback<List<Promotion>>() {

            @Override
            public void excute(int start, int size) {
                query.setStart(start);
                query.setLimit(size);

                List<PromotionDO> promotionDOS = promotionDAO.queryByCondition(query);
                returnValue = PromotionConvert.conv(promotionDOS);

                total = promotionDAO.getCountByCondition(query);
            }
        }, pageNum, pageSize);
    }

    @Override
    public Result<List<Promotion>> queryCurrent() {
        return template.complete(new ResultCallback<List<Promotion>>() {
            @Override
            public void excute() {
                returnValue = PromotionConvert.conv(promotionDAO.queryCurrent());
            }
        });
    }
}
