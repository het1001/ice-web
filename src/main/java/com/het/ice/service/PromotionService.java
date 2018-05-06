package com.het.ice.service;

import com.het.ice.dao.query.PromotionQuery;
import com.het.ice.enums.PromotionStateEnum;
import com.het.ice.model.Promotion;
import com.het.ice.service.template.Result;

import java.util.List;

public interface PromotionService {

    Result<Void> create(Promotion promotion);

    Result<Void> update(Promotion promotion);

    Result<Void> updateState(long id, PromotionStateEnum state);

    Result<Void> delete(Long id);

    Result<List<Promotion>> queryByCondition(PromotionQuery query, String pageNum, String pageSize);

    Result<List<Promotion>> queryCurrent();
}
