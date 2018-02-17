package com.het.ice.service;

import com.het.ice.model.Brand;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.BrandWO;

import java.util.List;

public interface BrandService {
    Result<Void> create(BrandWO brandWO);

    Result<Void> update(BrandWO brandWO);

    Result<Void> delete(long id);

    Result<List<Brand>> queryAll();
}
