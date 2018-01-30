package com.het.ice.service.conv;

import com.het.ice.dao.model.OrderDO;
import com.het.ice.dao.model.OrderListDO;
import com.het.ice.dao.model.OrderTraceDO;
import com.het.ice.model.Order;
import com.het.ice.model.OrderList;
import com.het.ice.model.OrderTrace;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderConvert {

    /**
     * 数据模型转为业务模型
     *
     * @param doModel
     * @return
     */
    public static Order conv(OrderDO doModel,
                             List<OrderListDO> orderListDOS,
                             List<OrderTraceDO> orderTraceDOS) {
        if (doModel == null) {
            return null;
        }

        Order model = new Order();
        model.setId(doModel.getId());
        model.setPhone(doModel.getPhone());
        model.setOrderNum(doModel.getOrderNum());
        model.setAddress(doModel.getAddress());
        model.setPriceTotal(doModel.getPriceTotal());
        model.setState(doModel.getState());
        model.setUserName(doModel.getUserName());
        model.setExpDelTimeDes(doModel.getExpDelTimeDes());
        model.setTime(doModel.getTime());
        model.setOrderLists(convOrderList(orderListDOS));
        model.setOrderTraces(convOrderTrace(orderTraceDOS));
        model.setCreateTime(doModel.getCreateTime());
        model.setModifyTime(doModel.getModifyTime());
        return model;
    }

    private static List<OrderList> convOrderList(List<OrderListDO> orderListDOS) {
        List<OrderList> orderLists = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderListDOS)) {
            for (OrderListDO orderListDO : orderListDOS) {
                OrderList orderList = new OrderList();
                orderList.setComId(orderListDO.getComId());
                orderList.setComName(orderListDO.getComName());
                orderList.setComPrice(orderListDO.getComPrice());
                orderList.setComNum(orderListDO.getComNum());
                orderList.setComStandard(orderListDO.getComStandard());
                orderLists.add(orderList);
            }
        }

        return orderLists;
    }

    private static List<OrderTrace> convOrderTrace(List<OrderTraceDO> orderTraceDOS) {
        List<OrderTrace> orderTraces = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderTraceDOS)) {
            for (OrderTraceDO orderTraceDO : orderTraceDOS) {
                OrderTrace orderTrace = new OrderTrace();
                orderTrace.setOperate(orderTraceDO.getOperate());
                orderTrace.setDescription(orderTraceDO.getDescription());
                orderTraces.add(orderTrace);
            }
        }

        return orderTraces;
    }
}
