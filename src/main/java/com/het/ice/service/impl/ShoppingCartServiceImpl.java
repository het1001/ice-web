package com.het.ice.service.impl;

import com.googlecode.aviator.AviatorEvaluator;
import com.het.ice.dao.ArithmeticDAO;
import com.het.ice.dao.CommodityDAO;
import com.het.ice.dao.PromotionDAO;
import com.het.ice.dao.ShoppingCartDAO;
import com.het.ice.dao.model.ArithmeticDO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.dao.model.PromotionDO;
import com.het.ice.dao.model.ShoppingCartDO;
import com.het.ice.dao.query.PromotionQuery;
import com.het.ice.dao.query.ShoppingCartQuery;
import com.het.ice.enums.PromotionStateEnum;
import com.het.ice.model.ShoppingCart;
import com.het.ice.service.ShoppingCartService;
import com.het.ice.service.conv.ShoppingCartConvert;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import com.het.ice.util.DoubleUtil;
import com.het.ice.util.InvokeUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartDAO shoppingCartDAO;

    @Resource
    private CommodityDAO commodityDAO;

    @Resource
    private PromotionDAO promotionDAO;

    @Resource
    private ArithmeticDAO arithmeticDAO;

    @Resource
    private Template template;

    @Override
    public Result<Void> create(ShoppingCart shoppingCart) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void check() {
                AssertUtil.moreThanZero(shoppingCart.getComId(), "商品id");
            }

            @Override
            public void excute() {
                ShoppingCartQuery query = new ShoppingCartQuery();
                query.setPhone(shoppingCart.getPhone());
                query.setComId(shoppingCart.getComId());
                ShoppingCartDO oldShoppingCartDO = shoppingCartDAO.getByPhoneAndComId(query);
                if (oldShoppingCartDO == null) {
                    ShoppingCartDO shoppingCartDO = ShoppingCartConvert.conv(shoppingCart);
                    shoppingCartDAO.insert(shoppingCartDO);
                } else {
                    oldShoppingCartDO.setComNum(oldShoppingCartDO.getComNum() + shoppingCart.getComNum());
                    shoppingCartDAO.update(oldShoppingCartDO);
                }
            }
        });
    }

    @Override
    public Result<Void> update(ShoppingCart shoppingCart) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void excute() {
                ShoppingCartDO shoppingCartDO = ShoppingCartConvert.conv(shoppingCart);
                shoppingCartDAO.update(shoppingCartDO);
            }
        });
    }

    @Override
    public Result<Void> delete(long comId) {
        return template.complete(new ResultCallback<Void>() {

            @Override
            public void excute() {
                shoppingCartDAO.delete(comId);
            }
        });
    }

    @Override
    public Result<List<ShoppingCart>> queryByPhone(String phone) {
        return template.complete(new ResultCallback<List<ShoppingCart>>() {
            @Override
            public void excute() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
                List<ShoppingCart> shoppingCarts = ShoppingCartConvert.conv(shoppingCartDAO.queryByPhone(phone));
                if (!CollectionUtils.isEmpty(shoppingCarts)) {
                    for (ShoppingCart shoppingCart : shoppingCarts) {
                        CommodityDO commodityDO = commodityDAO.getById(shoppingCart.getComId());
                        shoppingCart.setComName(commodityDO.getName());
                        shoppingCart.setPrice(commodityDO.getPricePi());
                        shoppingCart.setImgKey(commodityDO.getImgKey());
                        shoppingCart.setComStock(commodityDO.getTotal());

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
                            if (arithmeticDO.getType() == 1) {

                                // 参数
                                Map<String, Object> env = new HashMap<>();

                                JSONObject jsonObject = JSONObject.fromObject(promotionDO.getParams());
                                env.putAll(jsonObject);

                                // 入参解析
                                for (String inPa : arithmeticDO.getInParams().split(",")) {
                                    if (inPa.indexOf(".") > -1) {
                                        String[] arrs = inPa.split("\\.");
                                        if (StringUtils.equals(arrs[0],"commodity")) {
                                            env.put(arrs[1], InvokeUtil.get(arrs[1], commodityDO));
                                        } else if (StringUtils.equals(arrs[0],"shopCart")) {
                                            env.put(arrs[1], InvokeUtil.get(arrs[1], shoppingCart));
                                        }
                                    }
                                }

                                Object result = AviatorEvaluator.execute(arithmeticDO.getFunction(), env);

                                // 把值根据出参设置到对象里
                                String[] outArrs = arithmeticDO.getOutParam().split("\\.");
                                if (StringUtils.equals(outArrs[0],"commodity")) {
                                    InvokeUtil.set(outArrs[1], commodityDO, result);
                                } else if (StringUtils.equals(outArrs[0],"shopCart")) {
                                    InvokeUtil.set(outArrs[1], shoppingCart, result);
                                }
                            }
                        }

                        shoppingCart.setTotalPrice(DoubleUtil.multiply(commodityDO.getPricePi(), shoppingCart.getComNum()));
                    }
                }

                returnValue = shoppingCarts;
            }
        });
    }
}
