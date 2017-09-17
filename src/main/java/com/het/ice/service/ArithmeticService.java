package com.het.ice.service;

import com.het.ice.model.Arithmetic;
import com.het.ice.service.template.Result;

import java.util.List;

public interface ArithmeticService {

    Result<List<Arithmetic>> queryAll();
}
