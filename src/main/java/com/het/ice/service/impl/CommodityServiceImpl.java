package com.het.ice.service.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.het.ice.service.exception.ParamCheckException;
import com.het.ice.util.BarcodeUtil;
import com.het.ice.util.CommonConstants;
import com.het.ice.util.OssUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.het.ice.dao.CommodityDAO;
import com.het.ice.dao.model.CommodityDO;
import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.model.Commodity;
import com.het.ice.service.CommodityService;
import com.het.ice.service.conv.CommodityConvert;
import com.het.ice.service.template.PageResultCallback;
import com.het.ice.service.template.Result;
import com.het.ice.service.template.ResultCallback;
import com.het.ice.service.template.Template;
import com.het.ice.util.AssertUtil;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class CommodityServiceImpl implements CommodityService {

	@Resource
	private CommodityDAO commodityDao;

	/**
	 * 
	 */
	@Resource
	private Template template;

	/**
	 * 
	 */
	@Override
	public Result<List<Commodity>> queryByBizId(final long bizId, String pageNum, String pageSize) {
		return template.pageQuery(new PageResultCallback<List<Commodity>>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(bizId, "业务id");
			}

			@Override
			public void excute(int start, int size) {
				List<CommodityDO> commodityDOs = commodityDao.queryByBizId(bizId, start, size);
				returnValue = CommodityConvert.conv(commodityDOs);

				total = commodityDao.getCountByBizId(bizId);
			}

		}, pageNum, pageSize);
	}

	/***
	 * 
	 */
	@Override
	public Result<List<Commodity>> queryByCondition(final CommodityQuery query, final String pageNum,
			final String pageSize) {
		return template.pageQuery(new PageResultCallback<List<Commodity>>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(query.getBizId(), "业务id");
			}

			@Override
			public void excute(int start, int size) {
				query.setStart(start);
				query.setLimit(size);
				List<CommodityDO> commodityDOs = commodityDao.queryByCondition(query);
				returnValue = CommodityConvert.conv(commodityDOs);

				total = commodityDao.getCountByCondition(query);
			}

		}, pageNum, pageSize);
	}

	/**
	 *
	 * @param com
	 * @return
     */
	@Override
	public Result<Void> create(final Commodity com) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(com.getName(), "名称");
				AssertUtil.isEmpty(com.getImgKey(), "商品图片");

				if (StringUtils.isNotBlank(com.getBarCode())) {
					if (com.getBarCode().length() != CommonConstants.BAR_CODE_LENGTH) {
						throw new ParamCheckException("条形码长度为13位");
					}
				}
			}

			@Override
			public void excute() {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String code = format.format(new Date());
				com.setCode(code);
				com.setBizId(1);
				com.setCreateUser("hou");
				com.setUpdateUser("hou");

				// 计算价格/支
				com.setPriceBr(com.getPricePi() / com.getStandardPice());

				// 生成条形码图片
				if (StringUtils.isNotBlank(com.getBarCode())) {
					String key = OssUtil.putObject(new ByteArrayInputStream(BarcodeUtil.generate(com.getBarCode())));
					com.setBarImgKey(key);
				}

				commodityDao.insert(CommodityConvert.conv(com));

				System.out.println(com.getId());
			}
		});
	}

	@Override
	public Result<Void> update(final Commodity com) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(com.getId(), "商品id");

				if (StringUtils.isNotBlank(com.getBarCode())) {
					if (com.getBarCode().length() != CommonConstants.BAR_CODE_LENGTH) {
						throw new ParamCheckException("条形码长度为13位");
					}
				}
			}

			@Override
			public void excute() {
				com.setUpdateUser("hou");
				CommodityDO comDo = commodityDao.getById(com.getId());
				comDo.setName(com.getName());
				comDo.setStandardPice(com.getStandardPice());
				comDo.setPricePi(com.getPricePi());
				comDo.setPriceBr(com.getPricePi() / com.getStandardPice());
				comDo.setRetailPriceBr(com.getRetailPriceBr());
				comDo.setDescription(com.getDesc());
				comDo.setPersonType(com.getPersonType());
				comDo.setPosition(com.getPosition());
				comDo.setPromotion(com.getPromotion());
				comDo.setBrand(com.getBrand());
				comDo.setImgKey(com.getImgKey());

				// if 用户删除了原来的条形码，则把条形码图片key删除
				if (StringUtils.isBlank(com.getBarCode())) {
					if (StringUtils.isNotBlank(comDo.getBarCode())) {
						comDo.setBarImgKey(null);
					}
				} else {
					if (!StringUtils.equals(com.getBarCode(), comDo.getBarCode())) {
						comDo.setBarCode(com.getBarCode());
						String key = OssUtil.putObject(new ByteArrayInputStream(BarcodeUtil.generate(com.getBarCode())));
						comDo.setBarImgKey(key);
					}
				}

				commodityDao.update(comDo);
			}
		});
	}

	@Override
	public Result<Void> updateState(final Commodity com) {
		return template.complete(new ResultCallback<Void>() {

			@Override
			public void excute() {
				com.setUpdateUser("hou");
				CommodityDO comDo = commodityDao.getById(com.getId());
				comDo.setState(com.getState());

				commodityDao.update(comDo);
			}
		});
	}

	@Override
	public Result<Void> delete(long comId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Commodity> getById(final long comId) {
		return template.complete(new ResultCallback<Commodity>() {

			@Override
			public void check() {
				AssertUtil.moreThanZero(comId, "商品id");
			}

			@Override
			public void excute() {
				CommodityDO com = commodityDao.getById(comId);
				returnValue = CommodityConvert.conv(com);
			}
		});
	}

	/**
	 * 
	 */
	@Override
	public Result<Commodity> getByName(final String name) {
		return template.complete(new ResultCallback<Commodity>() {

			@Override
			public void check() {
				AssertUtil.isEmpty(name, "商品名称");
			}

			@Override
			public void excute() {
				CommodityDO com = commodityDao.getByName(name);
				returnValue = CommodityConvert.conv(com);
			}

		});
	}

	@Override
	public Result<List<Commodity>> queryAll() {
		return template.complete(new ResultCallback<List<Commodity>>() {

			@Override
			public void excute() {
				List<CommodityDO> commodityDOs = commodityDao.queryAll();
				returnValue = CommodityConvert.conv(commodityDOs);
			}

		});

	}

}
