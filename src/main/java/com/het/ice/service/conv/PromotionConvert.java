package com.het.ice.service.conv;

import com.het.ice.dao.model.PromotionDO;
import com.het.ice.enums.PromotionStateEnum;
import com.het.ice.model.Promotion;
import com.het.ice.web.request.PromotionWO;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.*;

/**
 *
 *
 */
public class PromotionConvert {


    /**
     * 数据模型转为业务模型
     *
     * @param promotionDO
     * @return
     */
    public static Promotion conv(PromotionDO promotionDO) {
        if (promotionDO == null) {
            return null;
        }

        Promotion promotion = new Promotion();
        promotion.setId(promotionDO.getId());
        promotion.setComId(promotionDO.getComId());
        promotion.setComName(promotionDO.getComName());
        promotion.setArithId(promotionDO.getArithId());
        promotion.setDesc(promotionDO.getDescription());
        promotion.setEffectiveDate(promotionDO.getEffectiveDate());
        promotion.setDeadline(promotionDO.getDeadline());
        promotion.setState(PromotionStateEnum.getByCode(promotionDO.getState()));
        promotion.setParams(parseParams(promotionDO.getParams()));
        promotion.setCreateTime(promotionDO.getCreateTime());
        promotion.setModifyTime(promotionDO.getModifyTime());

        return promotion;
    }

    private static Map<String, Object> parseParams(String params) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty(params)) {
            JSONObject jsonObject = JSONObject.fromObject(params);
            Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                map.put(entry.getKey(), entry.getValue());
            }
        }

        return map;
    }

    /**
     * 业务模型转为数据模型
     *
     * @param promotion
     * @return
     */
    public static PromotionDO conv(Promotion promotion) {
        if (promotion == null) {
            return null;
        }

        PromotionDO promotionDO = new PromotionDO();
        promotionDO.setId(promotion.getId());
        promotionDO.setComId(promotion.getComId());
        promotionDO.setComName(promotion.getComName());
        promotionDO.setArithId(promotion.getArithId());
        promotionDO.setDescription(promotion.getDesc());
        promotionDO.setEffectiveDate(promotion.getEffectiveDate());
        promotionDO.setDeadline(promotion.getDeadline());
        promotionDO.setState(promotion.getState().getCode());
        promotionDO.setParams(JSONObject.fromObject(promotion.getParams()).toString());

        promotionDO.setCreateTime(promotionDO.getCreateTime());
        promotionDO.setModifyTime(promotionDO.getModifyTime());

        return promotionDO;
    }

    /**
     * 前端模型转为业务模型
     *
     * @param promotionWO
     * @return
     */
    public static Promotion conv(PromotionWO promotionWO) throws ParseException {
        if (promotionWO == null) {
            return null;
        }

        Promotion promotion = new Promotion();
        promotion.setId(promotionWO.getId());
        promotion.setComId(promotionWO.getComId());
        promotion.setArithId(promotionWO.getArithId());
        promotion.setDesc(promotionWO.getDesc());
        promotion.setEffectiveDate(DateUtils.parseDate(promotionWO.getEffectiveDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        promotion.setDeadline(DateUtils.parseDate(promotionWO.getDeadline() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        promotion.setParams(parseParams(promotionWO.getParams()));

        return promotion;
    }

    /**
     * list-数据模型转为业务模型
     *
     * @param doModels
     * @return
     */
    public static List<Promotion> conv(List<PromotionDO> doModels) {
        List<Promotion> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(doModels)) {
            return models;
        }

        for (PromotionDO doModel : doModels) {
            models.add(conv(doModel));
        }

        return models;
    }
}
