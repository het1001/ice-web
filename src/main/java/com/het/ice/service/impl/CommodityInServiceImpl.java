package com.het.ice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.het.ice.dao.CommodityDAO;
import com.het.ice.dao.CommodityInDAO;
import com.het.ice.dao.CommodityInItemDAO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.dao.model.CommodityInDO;
import com.het.ice.dao.query.CommodityInQuery;
import com.het.ice.model.CommodityIn;
import com.het.ice.model.CommodityInItem;
import com.het.ice.service.CommodityInService;
import com.het.ice.service.conv.CommodityInConvert;
import com.het.ice.service.conv.CommodityInItemConvert;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;
import com.het.ice.util.UUIDUtil;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class CommodityInServiceImpl implements CommodityInService {

	@Resource
	private CommodityDAO commodityDao;

	@Resource
	private CommodityInDAO commodityInDao;

	@Resource
	private CommodityInItemDAO commodityInItemDao;

	/**
	 * 
	 */
	@Resource
	private Template template;

	@Override
	public Result<Void> create(final List<CommodityInItem> items) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(items, "入库商品不能为空");
			}

			@Override
			public void excute() {

				CommodityIn comIn = new CommodityIn();
				comIn.setInNum("IN" + UUIDUtil.getNum());
				comIn.setCreateUser("HOU");
				comIn.setUpdateUser("HOU");

				CommodityInDO commodityInDO = CommodityInConvert.conv(comIn);
				commodityInDao.insert(commodityInDO);

				for (CommodityInItem commodityInItem : items) {
					CommodityDO comDO = commodityDao.getById(commodityInItem.getComId());
					comDO.setTotal(comDO.getTotal() + commodityInItem.getNum());
					commodityDao.update(comDO);

					commodityInItem.setComName(comDO.getName());
					commodityInItem.setComStandard(comDO.getStandardPice());
					commodityInItem.setPriceBr(commodityInItem.getPricePi() / commodityInItem.getComStandard());
					commodityInItem.setInId(commodityInDO.getId());
					commodityInItem.setCreateUser("HOU");
					commodityInItem.setUpdateUser("HOU");
					commodityInItemDao.insert(CommodityInItemConvert.conv(commodityInItem));
				}
			}
		});
	}

	/**
	 * 
	 */
	@Override
	public Result<List<CommodityIn>> queryByCondition(final CommodityInQuery query, final String pageNum,
			final String pageSize) {
		return template.pageQuery(new PageResultCallback<List<CommodityIn>>() {

			@Override
			public void check() {

			}

			@Override
			public void excute(int start, int size) {
				query.setStart(start);
				query.setLimit(size);

				List<CommodityInDO> comInDOs = commodityInDao.queryByCondition(query);
				returnValue = CommodityInConvert.conv(comInDOs);
				total = commodityInDao.getCountByCondition(query);
			}

		}, pageNum, pageSize);
	}

	@Override
	public Result<List<CommodityInItem>> queryDetail(final long inId) {
		return template.complete(new ResultCallback<List<CommodityInItem>>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(inId, "入库单id");
			}

			@Override
			public void excute() {
				returnValue = CommodityInItemConvert.conv(commodityInItemDao.queryByInId(inId));
			}
		});
	}

}
