package com.het.ice.service.impl;

import com.het.ice.dao.ArithmeticDAO;
import com.het.ice.model.Arithmetic;
import com.het.ice.service.ArithmeticService;
import com.het.ice.service.conv.ArithmeticConvert;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArithmeticServiceImpl implements ArithmeticService {

    @Resource
    private ArithmeticDAO arithmeticDAO;

    @Resource
    private Template template;

    @Override
    public Result<List<Arithmetic>> queryAll() {
        return template.complete(new ResultCallback<List<Arithmetic>>() {

            @Override
            public void excute() {
                returnValue = ArithmeticConvert.conv(arithmeticDAO.queryAll());
            }
        });
    }
}
