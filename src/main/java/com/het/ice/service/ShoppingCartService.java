package com.het.ice.service;

import com.het.ice.model.ShoppingCart;
import com.het.ice.service.template.Result;

import java.util.List;

public interface ShoppingCartService {

	Result<Void> create(ShoppingCart shoppingCart);

	Result<Void> update(ShoppingCart shoppingCart);

	Result<Void> delete(long comId);

	Result<List<ShoppingCart>> queryByPhone(String phone);
}
