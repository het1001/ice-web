package com.het.ice.service.impl;

import com.het.ice.dao.CommodityPicDAO;
import com.het.ice.dao.model.CommodityPicDO;
import com.het.ice.model.CommodityPic;
import com.het.ice.service.CommodityPicService;
import com.het.ice.service.conv.CommodityPicConvert;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class CommodityPicServiceImpl implements CommodityPicService {

	private Logger logger = Logger.getLogger(CommodityPicServiceImpl.class);

	@Resource
	private CommodityPicDAO commodityPicDAO;

	/**
	 * 
	 */
	@Resource
	private Template template;

	@Override
	public Result<CommodityPic> getMainByComId(long comId) {
		return template.complete(new ResultCallback<CommodityPic>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(comId, "商品id");
			}

			@Override
			public void excute() {
				CommodityPicDO comPicDO = commodityPicDAO.getMainByComId(comId);
				returnValue = CommodityPicConvert.conv(comPicDO);
			}
		});
	}

	@Override
	public Result<List<CommodityPic>> queryOthersByComId(long comId) {
		return template.complete(new ResultCallback<List<CommodityPic>>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(comId, "商品id");
			}

			@Override
			public void excute() {
				returnValue = CommodityPicConvert.conv(commodityPicDAO.queryOtherByComId(comId));
			}

		});
	}

	@Override
	public Result<List<CommodityPic>> queryAllByComId(long comId) {
		return template.complete(new ResultCallback<List<CommodityPic>>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(comId, "商品id");
			}

			@Override
			public void excute() {
				returnValue = CommodityPicConvert.conv(commodityPicDAO.queryByComId(comId));
			}
		});
	}
}
