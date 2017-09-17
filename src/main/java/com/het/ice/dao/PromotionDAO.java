package com.het.ice.dao;

import com.het.ice.dao.model.PromotionDO;
import com.het.ice.dao.query.PromotionQuery;

import java.util.List;

/**
 * @author Administrator
 */
public interface PromotionDAO {

    int insert(PromotionDO promotionDO);

    int update(PromotionDO promotionDO);

    int delete(long id);

    PromotionDO getById(long id);

    int getCountByCondition(PromotionQuery query);

    List<PromotionDO> queryByCondition(PromotionQuery query);

    List<PromotionDO> queryCurrent();


}
