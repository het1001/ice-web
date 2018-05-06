package com.het.ice.service;

import com.het.ice.model.UserDeliveryAddr;
import com.het.ice.service.template.Result;

import java.util.List;

public interface UserDeliveryAddrService {

	Result<Void> create(UserDeliveryAddr userDeliveryAddr);

	Result<Void> update(UserDeliveryAddr userDeliveryAddr);

	Result<Void> delete(long id);

	Result<List<UserDeliveryAddr>> queryByPhone(String phone);
}
