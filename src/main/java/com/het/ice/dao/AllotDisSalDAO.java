package com.het.ice.dao;

import com.het.ice.dao.model.AllotDisSalDO;
import com.het.ice.dao.query.AllotDisSlaQuery;

import java.util.List;

public interface AllotDisSalDAO {

    void insert(AllotDisSalDO allotDisSalDO);

    void update(AllotDisSalDO allotDisSalDO);

    void delete(long id);

    List<AllotDisSalDO> queryByDisId(AllotDisSlaQuery allotDisSlaQuery);

    List<AllotDisSalDO> queryByDisIdAndType(AllotDisSlaQuery allotDisSlaQuery);

    List<AllotDisSalDO> queryBySlaId(AllotDisSlaQuery allotDisSlaQuery);
}
