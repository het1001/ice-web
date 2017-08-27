package com.het.ice.service;

import com.het.ice.model.CommodityPic;
import com.het.ice.service.template.Result;

import java.util.List;

/**
 *
 */
public interface CommodityPicService {

	Result<CommodityPic> getMainByComId(long comId);

	Result<List<CommodityPic>> queryOthersByComId(long comId);

	Result<List<CommodityPic>> queryAllByComId(long comId);
}
