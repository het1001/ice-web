package com.het.ice.service.conv;

import com.het.ice.dao.model.CatDO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.model.Cat;
import com.het.ice.model.Commodity;
import com.het.ice.web.request.CatWO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CatConvert {

    public static CatDO conv(CatWO catWO) {
        if (catWO == null) {
            return null;
        }

        CatDO catDO = new CatDO();
        catDO.setId(NumberUtils.toLong(catWO.getId()));
        catDO.setName(catWO.getName());
        catDO.setOrderr(NumberUtils.toInt(catWO.getOrderr()));

        return catDO;
    }

    public static Cat conv(CatDO catDO) {
        if (catDO == null) {
            return null;
        }

        Cat cat = new Cat();
        cat.setId(catDO.getId());
        cat.setName(catDO.getName());
        cat.setOrderr(catDO.getOrderr());

        return cat;
    }

    public static List<Cat> conv(List<CatDO> catDOS) {
        List<Cat> cats = new ArrayList<>();
        if (CollectionUtils.isEmpty(catDOS)) {
            return cats;
        }

        for (CatDO catDO : catDOS) {
            cats.add(conv(catDO));
        }

        return cats;
    }
}
