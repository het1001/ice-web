package com.het.ice.service.conv;

import com.het.ice.dao.model.BrandDO;
import com.het.ice.dao.model.CatDO;
import com.het.ice.enums.CatTypeEnum;
import com.het.ice.model.Brand;
import com.het.ice.model.Cat;
import com.het.ice.web.request.CatWO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BrandConvert {

    public static Brand conv(BrandDO brandDO) {
        if (brandDO == null) {
            return null;
        }

        Brand brand = new Brand();
        brand.setId(brandDO.getId());
        brand.setName(brandDO.getName());
        return brand;
    }

    public static List<Brand> conv(List<BrandDO> brandDOS) {
        List<Brand> brands = new ArrayList<>();
        if (CollectionUtils.isEmpty(brandDOS)) {
            return brands;
        }

        for (BrandDO catDO : brandDOS) {
            brands.add(conv(catDO));
        }

        return brands;
    }
}
