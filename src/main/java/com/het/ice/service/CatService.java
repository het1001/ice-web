package com.het.ice.service;

import com.het.ice.enums.CatTypeEnum;
import com.het.ice.model.Cat;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.CatWO;

import java.util.List;

public interface CatService {
    Result<Void> create(CatWO catWO);

    Result<Void> update(CatWO catWO);

    Result<Void> delete(long id);

    Result<List<Cat>> queryByType(CatTypeEnum type);
}
