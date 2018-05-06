package com.het.ice.service.conv;

import com.het.ice.dao.model.ArithmeticDO;
import com.het.ice.model.Arithmetic;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticConvert {

    public static Arithmetic conv(ArithmeticDO arithmeticDO) {
        if (arithmeticDO == null) {
            return null;
        }

        Arithmetic arithmetic = new Arithmetic();
        arithmetic.setName(arithmeticDO.getName());
        arithmetic.setFunction(arithmeticDO.getFunction());
        arithmetic.setFuncKeys(arithmeticDO.getFuncKeys());
        arithmetic.setInParams(arithmeticDO.getInParams());
        arithmetic.setOutParam(arithmeticDO.getOutParam());
        arithmetic.setId(arithmeticDO.getId());
        arithmetic.setCreateTime(arithmeticDO.getCreateTime());
        arithmetic.setModifyTime(arithmeticDO.getModifyTime());

        return arithmetic;
    }

    /**
     * list-数据模型转为业务模型
     *
     * @param doModels
     * @return
     */
    public static List<Arithmetic> conv(List<ArithmeticDO> doModels) {
        List<Arithmetic> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(doModels)) {
            return models;
        }

        for (ArithmeticDO doModel : doModels) {
            models.add(conv(doModel));
        }

        return models;
    }
}
