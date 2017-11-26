package com.het.ice.service.impl;

import com.googlecode.aviator.AviatorEvaluator;
import com.het.ice.dao.*;
import com.het.ice.dao.model.*;
import com.het.ice.dao.query.OrderQuery;
import com.het.ice.dao.query.PromotionQuery;
import com.het.ice.enums.OrderOprateEnum;
import com.het.ice.enums.OrderStateEnum;
import com.het.ice.enums.PromotionStateEnum;
import com.het.ice.model.Order;
import com.het.ice.service.OrderService;
import com.het.ice.service.conv.OrderConvert;
import com.het.ice.service.exception.BizException;
import com.het.ice.service.exception.ParamCheckException;
import com.het.ice.service.exception.ResultCodeEnum;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import com.het.ice.util.DoubleUtil;
import com.het.ice.util.InvokeUtil;
import com.het.ice.util.UUIDUtil;
import com.het.ice.web.request.OrderWO;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDAO orderDAO;

    @Resource
    private OrderListDAO orderListDAO;

    @Resource
    private OrderTraceDAO orderTraceDAO;

    @Resource
    private ShoppingCartDAO shoppingCartDAO;

    @Resource
    private CommodityDAO commodityDAO;

    @Resource
    private PromotionDAO promotionDAO;

    @Resource
    private ArithmeticDAO arithmeticDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private Template template;

    @Override
    public Result<Void> create(OrderWO orderWO) {
        return template.complete(new ResultCallback<Void>() {

            private List<ShoppingCartDO> shoppingCartDOS;

            private Map<Long, CommodityDO> comMap;

            private UserDO userDO;

            @Override
            public void check() {
                AssertUtil.lengthThan(orderWO.getPhone(), 32, "手机号");
                userDO = userDAO.getByPhone(orderWO.getPhone());
                AssertUtil.isNull(userDO, "用户不存在");

                List<String> list = orderWO.getList();
                if (CollectionUtils.isEmpty(list)) {
                    throw new ParamCheckException("没有提交数据");
                }

                shoppingCartDOS = new ArrayList<>();
                comMap = new HashMap<>();

                for (String shopId : list) {
                    ShoppingCartDO shoppingCartDO = shoppingCartDAO.getById(NumberUtils.toLong(shopId));
                    //排除掉删除的
                    if (shoppingCartDO == null) {
                        continue;
                    }

                    CommodityDO commodityDO = commodityDAO.getById(shoppingCartDO.getComId());

                    if (shoppingCartDO.getComNum() > commodityDO.getTotal()) {
                        throw new ParamCheckException(commodityDO.getName() + "库存不足");
                    }

                    shoppingCartDOS.add(shoppingCartDO);
                    comMap.put(shoppingCartDO.getId(), commodityDO);
                }
            }

            @Override
            public void excute() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
                double price = 0;

                String orderNum = UUIDUtil.getNum();

                OrderDO orderDO = new OrderDO();
                orderDO.setOrderNum(orderNum);
                orderDO.setPhone(userDO.getPhone());
                orderDO.setUserName(userDO.getRealName());
                orderDO.setAddress(userDO.getShopAddress());
                orderDO.setState(OrderStateEnum.CREATED.getCode());

                for (ShoppingCartDO shoppingCartDO : shoppingCartDOS) {
                    CommodityDO commodityDO = comMap.get(shoppingCartDO.getId());

                    PromotionQuery query = new PromotionQuery();
                    query.setComId(commodityDO.getId());
                    query.setDate(new Date());
                    query.setState(PromotionStateEnum.ON_LINE.getCode());
                    query.setStart(0);
                    query.setLimit(1);
                    List<PromotionDO> promotionDOS = promotionDAO.queryByCondition(query);

                    // 有促销，按公式计算促销结果
                    if (!CollectionUtils.isEmpty(promotionDOS)) {
                        // 只取第一个促销 TODO 待优化
                        PromotionDO promotionDO = promotionDOS.get(0);

                        // 获取算法模型
                        ArithmeticDO arithmeticDO = arithmeticDAO.getById(promotionDO.getArithId());

                        // 参数
                        Map<String, Object> env = new HashMap<>();
                        env.putAll(JSONObject.fromObject(promotionDO.getParams()));

                        // type==1 要运算一下
                        if (arithmeticDO.getType() == 1) {

                            // 入参解析
                            for (String inPa : arithmeticDO.getInParams().split(",")) {
                                if (inPa.indexOf(".") > -1) {
                                    String[] arrs = StringUtils.split(inPa, "\\.");
                                    String model = arrs[0];
                                    String param = arrs[1];
                                    if (StringUtils.equals(model,"commodity")) {
                                        env.put(param, InvokeUtil.get(param, commodityDO));
                                    } else if (StringUtils.equals(model,"shopCart")) {
                                        env.put(param, InvokeUtil.get(param, shoppingCartDO));
                                    }
                                }
                            }

                            Object result = AviatorEvaluator.execute(arithmeticDO.getFunction(), env);

                            // 把值根据出参设置到对象里
                            String[] outArrs = arithmeticDO.getOutParam().split("\\.");
                            if (StringUtils.equals(outArrs[0],"commodity")) {
                                InvokeUtil.set(outArrs[1], commodityDO, result);
                            } else if (StringUtils.equals(outArrs[0],"shopCart")) {
                                InvokeUtil.set(outArrs[1], shoppingCartDO, result);
                            }
                        } else {
                            // 先写死，还没有好的方案。。
                            int x = (Integer) env.get("x");
                            int y = (Integer) env.get("y");
                            int z = (Integer) env.get("z");

                            int temp = shoppingCartDO.getComNum() / x;
                            if (temp > 0) {
                                CommodityDO zDO = commodityDAO.getById(z);
                                OrderListDO zOrderListDO = new OrderListDO();
                                zOrderListDO.setComId(zDO.getId());
                                zOrderListDO.setComName(zDO.getName() + "[赠品]");
                                zOrderListDO.setComNum(temp * y);
                                zOrderListDO.setComStandard(zDO.getStandardPice());
                                zOrderListDO.setOrderNum(orderNum);
                                zOrderListDO.setComPrice(0);

                                orderListDAO.insert(zOrderListDO);
                            }
                        }
                    }

                    OrderListDO orderListDO = new OrderListDO();
                    orderListDO.setComId(commodityDO.getId());
                    orderListDO.setComName(commodityDO.getName());
                    orderListDO.setComNum(shoppingCartDO.getComNum());
                    orderListDO.setComStandard(commodityDO.getStandardPice());
                    orderListDO.setOrderNum(orderNum);
                    orderListDO.setComPrice(DoubleUtil.multiply(commodityDO.getPricePi(), shoppingCartDO.getComNum()));

                    orderListDAO.insert(orderListDO);

                    // 设置库存
                    commodityDO.setTotal(commodityDO.getTotal() - shoppingCartDO.getComNum());
                    commodityDAO.update(commodityDO);

                    shoppingCartDAO.delete(shoppingCartDO.getId());

                    price = DoubleUtil.add(price, orderListDO.getComPrice());
                }

                orderDO.setPriceTotal(price);
                orderDAO.insert(orderDO);

                OrderTraceDO orderTraceDO = new OrderTraceDO();
                orderTraceDO.setOrderNum(orderNum);
                orderTraceDO.setOperate(OrderOprateEnum.CREATE.getCode());
                orderTraceDO.setOperateDisplay(OrderOprateEnum.CREATE.getDesc());
                orderTraceDO.setDescription("");

                orderTraceDAO.insert(orderTraceDO);
            }
        });
    }

    @Override
    public Result<List<Order>> queryByPhone(final String phone) {
        return template.complete(new ResultCallback<List<Order>>() {

            @Override
            public void check() {
                AssertUtil.lengthThan(phone, 32, "手机号");
            }

            @Override
            public void excute() {

                List<Order> orders = new ArrayList<>();
                List<OrderDO> orderDOS = orderDAO.queryByPhone(phone);
                if (!CollectionUtils.isEmpty(orderDOS)) {
                    for (OrderDO orderDO : orderDOS) {
                        orders.add(OrderConvert.conv(orderDO, orderListDAO.queryByOrderNum(orderDO.getOrderNum()),
                                orderTraceDAO.queryByOrderNum(orderDO.getOrderNum())));
                    }
                }

                returnValue = orders;
            }
        });
    }

    @Override
    public Result<List<Order>> queryByCondition(final OrderQuery query, final String pageNum,
                                                final String pageSize) {
        return template.pageQuery(new PageResultCallback<List<Order>>() {

            @Override
            public void excute(int start, int size) {
                query.setStart(start);
                query.setLimit(size);

                List<OrderDO> doList = orderDAO.queryByCondition(query);

                List<Order> orders = new ArrayList<>();
                if (!CollectionUtils.isEmpty(doList)) {
                    for (OrderDO orderDO : doList) {
                        orders.add(OrderConvert.conv(orderDO, orderListDAO.queryByOrderNum(orderDO.getOrderNum()),
                                orderTraceDAO.queryByOrderNum(orderDO.getOrderNum())));
                    }
                }

                returnValue = orders;

                total = orderDAO.countByCondition(query);
            }

        }, pageNum, pageSize);
    }

    @Override
    public Result<Void> verify(final String id) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(id, "订单ID");
            }

            @Override
            public void excute() {
                OrderDO orderDO = orderDAO.getById(NumberUtils.toLong(id));
                if (orderDO == null) {
                    throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
                }

                orderDO.setState(OrderStateEnum.ACCEPT.getCode());
                orderDAO.update(orderDO);

                OrderTraceDO orderTraceDO = new OrderTraceDO();
                orderTraceDO.setOrderNum(orderDO.getOrderNum());
                orderTraceDO.setOperate(OrderOprateEnum.ACCEPT.getCode());
                orderTraceDO.setOperateDisplay(OrderOprateEnum.ACCEPT.getDesc());
                orderTraceDAO.insert(orderTraceDO);
            }
        });
    }

    @Override
    public Result<Void> cancel(final String id) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(id, "订单ID");
            }

            @Override
            public void excute() {
                OrderDO orderDO = orderDAO.getById(NumberUtils.toLong(id));
                if (orderDO == null) {
                    throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
                }

                orderDO.setState(OrderStateEnum.CANCELED.getCode());
                orderDAO.update(orderDO);

                List<OrderListDO> orderListDOS = orderListDAO.queryByOrderNum(orderDO.getOrderNum());
                if (!CollectionUtils.isEmpty(orderListDOS)) {
                    for (OrderListDO orderListDO : orderListDOS) {
                        CommodityDO commodityDO = commodityDAO.getById(orderListDO.getComId());
                        commodityDO.setTotal(commodityDO.getTotal() + orderListDO.getComNum());
                        commodityDAO.update(commodityDO);
                    }
                }

                OrderTraceDO orderTraceDO = new OrderTraceDO();
                orderTraceDO.setOrderNum(orderDO.getOrderNum());
                orderTraceDO.setOperate(OrderOprateEnum.CANCEL.getCode());
                orderTraceDO.setOperateDisplay(OrderOprateEnum.CANCEL.getDesc());
                orderTraceDAO.insert(orderTraceDO);
            }
        });
    }

    @Override
    public Result<Void> complete(final String id) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.isEmpty(id, "订单ID");
            }

            @Override
            public void excute() {
                OrderDO orderDO = orderDAO.getById(NumberUtils.toLong(id));
                if (orderDO == null) {
                    throw new BizException(ResultCodeEnum.USER_NOT_EXIST);
                }

                orderDO.setState(OrderStateEnum.COMPLETED.getCode());
                orderDAO.update(orderDO);

                OrderTraceDO orderTraceDO = new OrderTraceDO();
                orderTraceDO.setOrderNum(orderDO.getOrderNum());
                orderTraceDO.setOperate(OrderOprateEnum.COMPLETE.getCode());
                orderTraceDO.setOperateDisplay(OrderOprateEnum.COMPLETE.getDesc());
                orderTraceDAO.insert(orderTraceDO);
            }
        });
    }
}
