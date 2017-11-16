package com.het.ice.service.conv;

import com.het.ice.dao.model.ShoppingCartDO;
import com.het.ice.model.ShoppingCart;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartConvert {

    /**
     * 数据模型转为业务模型
     *
     * @param shoppingCartDO
     * @return
     */
    public static ShoppingCart conv(ShoppingCartDO shoppingCartDO) {
        if (shoppingCartDO == null) {
            return null;
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartDO.getId());
        shoppingCart.setPhone(shoppingCartDO.getPhone());
        shoppingCart.setComId(shoppingCartDO.getComId());
        shoppingCart.setComNum(shoppingCartDO.getComNum());
        shoppingCart.setCreateTime(shoppingCartDO.getCreateTime());
        shoppingCart.setModifyTime(shoppingCartDO.getModifyTime());
        return shoppingCart;
    }

    /**
     * 业务模型转为数据模型
     *
     * @param shoppingCart
     * @return
     */
    public static ShoppingCartDO conv(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            return null;
        }

        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        shoppingCartDO.setId(shoppingCart.getId());
        shoppingCartDO.setPhone(shoppingCart.getPhone());
        shoppingCartDO.setComId(shoppingCart.getComId());
        shoppingCartDO.setComNum(shoppingCart.getComNum());
        shoppingCartDO.setCreateTime(shoppingCart.getCreateTime());
        shoppingCartDO.setModifyTime(shoppingCart.getModifyTime());
        return shoppingCartDO;
    }

    /**
     * list-数据模型转为业务模型
     *
     * @param doModels
     * @return
     */
    public static List<ShoppingCart> conv(List<ShoppingCartDO> doModels) {
        List<ShoppingCart> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(doModels)) {
            return models;
        }

        for (ShoppingCartDO doModel : doModels) {
            models.add(conv(doModel));
        }

        return models;
    }
}
