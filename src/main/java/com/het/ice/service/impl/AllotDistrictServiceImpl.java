package com.het.ice.service.impl;

import com.het.ice.dao.AllotDisSalDAO;
import com.het.ice.dao.AllotDistrictDAO;
import com.het.ice.dao.AllotSalesmanDAO;
import com.het.ice.dao.model.AllotDisSalDO;
import com.het.ice.dao.model.AllotDistrictDO;
import com.het.ice.dao.model.AllotSalesmanDO;
import com.het.ice.dao.query.AllotDisSlaQuery;
import com.het.ice.dao.query.AllotDistrictQuery;
import com.het.ice.enums.SalesmanTypeEnum;
import com.het.ice.model.AllotDistrict;
import com.het.ice.service.AllotDistrictService;
import com.het.ice.service.conv.AllotDistrictConvert;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllotDistrictServiceImpl implements AllotDistrictService {

    @Resource
    private AllotDistrictDAO allotDistrictDAO;

    @Resource
    private AllotSalesmanDAO allotSalesmanDAO;

    @Resource
    private AllotDisSalDAO allotDisSalDAO;

    @Resource
    private Template template;

    @Override
    public Result<Void> create(AllotDistrict allotDistrict, List<String> salesmanIds) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(allotDistrict.getName(), "名称");
            }

            @Override
            public void excute() {

                AllotDistrictDO allotDistrictDO = AllotDistrictConvert.conv(allotDistrict);

                allotDistrictDAO.insert(allotDistrictDO);

                if (!CollectionUtils.isEmpty(salesmanIds)) {
                    for (String salesmanId : salesmanIds) {
                        createDisSla(allotDistrictDO.getId(), allotDistrictDO.getName(), salesmanId);
                    }
                }

            }
        });
    }

    /**
     *
     * @param districtId
     * @param districtName
     * @param salesmanId
     */
    private void createDisSla(long districtId, String districtName, String salesmanId) {
        AllotSalesmanDO allotSalesmanDO = allotSalesmanDAO.getById(NumberUtils.toLong(salesmanId, 0));
        if (allotSalesmanDO != null) {
            AllotDisSalDO allotDisSalDO = new AllotDisSalDO();
            allotDisSalDO.setDisId(districtId);
            allotDisSalDO.setDisName(districtName);
            allotDisSalDO.setSalId(allotSalesmanDO.getId());
            allotDisSalDO.setSalName(allotSalesmanDO.getName());
            allotDisSalDO.setSalPhone(allotSalesmanDO.getPhone());
            allotDisSalDO.setSalType(allotSalesmanDO.getType());

            allotDisSalDAO.insert(allotDisSalDO);
        }
    }

    @Override
    public Result<Void> update(AllotDistrict allotDistrict, List<String> salesmanIds) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(allotDistrict.getName(), "名称");
            }

            @Override
            public void excute() {
                AllotDistrictDO allotDistrictDO = AllotDistrictConvert.conv(allotDistrict);
                allotDistrictDAO.update(allotDistrictDO);

                AllotDisSlaQuery query = new AllotDisSlaQuery();
                query.setDisId(allotDistrictDO.getId());
                List<AllotDisSalDO> allotDisSalDOS = allotDisSalDAO.queryByDisId(query);

                if (CollectionUtils.isEmpty(allotDisSalDOS)) {
                    if (!CollectionUtils.isEmpty(salesmanIds)) {
                        for (String salesmanId : salesmanIds) {
                            createDisSla(allotDistrictDO.getId(), allotDistrictDO.getName(), salesmanId);
                        }
                    }
                } else {
                    if (CollectionUtils.isEmpty(salesmanIds)) {
                        for (AllotDisSalDO allotDisSalDO : allotDisSalDOS) {
                            allotDisSalDAO.delete(allotDisSalDO.getId());
                        }
                    } else {
                        List<String> disSalIds = new ArrayList<>();
                        for (AllotDisSalDO allotDisSalDO : allotDisSalDOS) {
                            disSalIds.add(allotDisSalDO.getSalId() + "");
                        }

                        for (String salesmanId : salesmanIds) {
                            if (!disSalIds.contains(salesmanId)) {
                                createDisSla(allotDistrictDO.getId(), allotDistrictDO.getName(), salesmanId);
                            }
                        }

                        for (AllotDisSalDO allotDisSalDO : allotDisSalDOS) {
                            if (!salesmanIds.contains(allotDisSalDO.getSalId() + "")) {
                                allotDisSalDAO.delete(allotDisSalDO.getId());
                            } else {
                                // update name
                                allotDisSalDO.setDisName(allotDistrictDO.getName());
                                allotDisSalDAO.update(allotDisSalDO);
                            }
                        }
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
                allotDistrictDAO.delete(id);

                // 把关联关系都查出来删除
                AllotDisSlaQuery query = new AllotDisSlaQuery();
                query.setDisId(id);
                List<AllotDisSalDO> allotDisSalDOS = allotDisSalDAO.queryByDisId(query);

                if (!CollectionUtils.isEmpty(allotDisSalDOS)) {
                    for (AllotDisSalDO allotDisSalDO : allotDisSalDOS) {
                        allotDisSalDAO.delete(allotDisSalDO.getId());
                    }
                }
            }
        });
    }

    @Override
    public Result<AllotDistrict> getByName(String name) {
        return template.complete(new ResultCallback<AllotDistrict>() {
            @Override
            public void excute() {
                returnValue = AllotDistrictConvert.conv(allotDistrictDAO.getByName(name));
            }
        });
    }

    @Override
    public Result<List<AllotDistrict>> queryByCondition(AllotDistrictQuery query, String pageNum, String pageSize) {
        return template.pageQuery(new PageResultCallback<List<AllotDistrict>>() {

            @Override
            public void excute(int start, int size) {
                query.setStart(start);
                query.setLimit(size);

                List<AllotDistrictDO> allotDistrictDOS = allotDistrictDAO.queryByCondition(query);
                returnValue = AllotDistrictConvert.conv(allotDistrictDOS);

                total = allotDistrictDAO.getCountByCondition(query);
            }

        }, pageNum, pageSize);
    }

    @Override
    public Result<List<AllotDistrict>> queryAll() {
        return template.complete(new ResultCallback<List<AllotDistrict>>() {
            @Override
            public void excute() {
                returnValue = AllotDistrictConvert.conv(allotDistrictDAO.queryAll());
            }
        });
    }

    @Override
    public Result<Map<String, List<String>>> getSalIdsByDisId(long id) {
        return template.complete(new ResultCallback<Map<String, List<String>>>() {
            @Override
            public void check() {
                AssertUtil.moreThanZero(id, "ID");
            }

            @Override
            public void excute() {
                Map<String, List<String>> result = new HashMap<>(2);

                AllotDisSlaQuery query = new AllotDisSlaQuery();
                query.setDisId(id);
                query.setSalType(SalesmanTypeEnum.DELIVERYMEN.getCode());
                List<AllotDisSalDO> allotDisSalDOS = allotDisSalDAO.queryByDisIdAndType(query);
                List<String> deliverymenIds = new ArrayList<>();
                if (!CollectionUtils.isEmpty(allotDisSalDOS)) {
                    for (AllotDisSalDO allotDisSalDO : allotDisSalDOS) {
                        deliverymenIds.add(allotDisSalDO.getSalId() + "");
                    }
                }

                query.setSalType(SalesmanTypeEnum.SALESMAN.getCode());
                List<AllotDisSalDO> allotDisSalDOS2 = allotDisSalDAO.queryByDisIdAndType(query);
                List<String> salesmanIds = new ArrayList<>();
                if (!CollectionUtils.isEmpty(allotDisSalDOS2)) {
                    for (AllotDisSalDO allotDisSalDO : allotDisSalDOS2) {
                        salesmanIds.add(allotDisSalDO.getSalId() + "");
                    }
                }

                result.put("districtor", deliverymenIds);
                result.put("salesman", salesmanIds);

                returnValue = result;
            }
        });
    }
}
