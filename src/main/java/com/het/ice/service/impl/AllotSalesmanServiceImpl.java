package com.het.ice.service.impl;

import com.het.ice.dao.AllotDisSalDAO;
import com.het.ice.dao.AllotSalesmanDAO;
import com.het.ice.dao.model.AllotDisSalDO;
import com.het.ice.dao.model.AllotSalesmanDO;
import com.het.ice.dao.query.AllotDisSlaQuery;
import com.het.ice.dao.query.AllotSalesmanQuery;
import com.het.ice.model.AllotSalesman;
import com.het.ice.service.AllotSalesmanService;
import com.het.ice.service.conv.AllotSalesmanConvert;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AllotSalesmanServiceImpl implements AllotSalesmanService {

    @Resource
    private Template template;

    @Resource
    private AllotSalesmanDAO allotSalesmanDAO;

    @Resource
    private AllotDisSalDAO allotDisSalDAO;

    @Override
    public Result<Void> create(AllotSalesman allotSalesman) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(allotSalesman.getName(), "名称");
            }

            @Override
            public void excute() {
                AllotSalesmanDO allotSalesmanDO = AllotSalesmanConvert.conv(allotSalesman);

                allotSalesmanDAO.insert(allotSalesmanDO);
            }
        });
    }

    @Override
    public Result<Void> update(AllotSalesman allotSalesman) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(allotSalesman.getName(), "名称");
            }

            @Override
            public void excute() {
                AllotSalesmanDO allotSalesmanDO = AllotSalesmanConvert.conv(allotSalesman);

                allotSalesmanDAO.update(allotSalesmanDO);

                AllotDisSlaQuery query = new AllotDisSlaQuery();
                query.setSalId(allotSalesmanDO.getId());
                List<AllotDisSalDO> allotDisSalDOS = allotDisSalDAO.queryBySlaId(query);
                if (!CollectionUtils.isEmpty(allotDisSalDOS)) {
                    for (AllotDisSalDO allotDisSalDO : allotDisSalDOS) {
                        allotDisSalDO.setSalName(allotSalesmanDO.getName());
                        allotDisSalDO.setSalPhone(allotSalesmanDO.getPhone());
                        allotDisSalDO.setSalType(allotSalesmanDO.getType());
                        allotDisSalDAO.update(allotDisSalDO);
                    }
                }
            }
        });
    }

    @Override
    public Result<Void> delete(long id) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.moreThanZero(id, "ID");
            }

            @Override
            public void excute() {
                allotSalesmanDAO.delete(id);

                AllotDisSlaQuery query = new AllotDisSlaQuery();
                query.setSalId(id);
                List<AllotDisSalDO> allotDisSalDOS = allotDisSalDAO.queryBySlaId(query);
                if (!CollectionUtils.isEmpty(allotDisSalDOS)) {
                    for (AllotDisSalDO allotDisSalDO : allotDisSalDOS) {
                        allotDisSalDAO.delete(allotDisSalDO.getId());
                    }
                }
            }
        });
    }

    @Override
    public Result<AllotSalesman> getByUniqueKey(String uniqueKey) {
        return template.complete(new ResultCallback<AllotSalesman>() {
            @Override
            public void excute() {
                returnValue = AllotSalesmanConvert.conv(allotSalesmanDAO.getByUniqueKey(uniqueKey));
            }
        });
    }

    @Override
    public Result<List<AllotSalesman>> queryByCondition(AllotSalesmanQuery query, String pageNum, String pageSize) {
        return template.pageQuery(new PageResultCallback<List<AllotSalesman>>() {

            @Override
            public void excute(int start, int size) {
                query.setStart(start);
                query.setLimit(size);

                List<AllotSalesmanDO> allotSalesmanDOS = allotSalesmanDAO.queryByCondition(query);
                returnValue = AllotSalesmanConvert.conv(allotSalesmanDOS);

                total = allotSalesmanDAO.getCountByCondition(query);
            }

        }, pageNum, pageSize);
    }

    @Override
    public Result<List<AllotSalesman>> queryByType(String type) {
        return template.complete(new ResultCallback<List<AllotSalesman>>() {
            @Override
            public void excute() {
                returnValue = AllotSalesmanConvert.conv(allotSalesmanDAO.queryByType(type));
            }
        });
    }

    @Override
    public Result<List<AllotSalesman>> queryAll() {
        return template.complete(new ResultCallback<List<AllotSalesman>>() {
            @Override
            public void excute() {
                returnValue = AllotSalesmanConvert.conv(allotSalesmanDAO.queryAll());
            }
        });
    }
}
