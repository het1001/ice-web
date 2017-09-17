package com.het.ice.dao;

import com.het.ice.dao.model.ArithmeticDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArithmeticDAO {

    ArithmeticDO getById(@Param(value = "id") long id);

    List<ArithmeticDO> queryAll();
}
