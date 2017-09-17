package com.het.ice.service.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.het.ice.dao.CommodityPicDAO;
import com.het.ice.dao.model.CommodityPicDO;
import com.het.ice.service.exception.ParamCheckException;
import com.het.ice.util.BarcodeUtil;
import com.het.ice.util.CommonConstants;
import com.het.ice.util.OssUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import org.springframework.util.CollectionUtils;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class CommodityServiceImpl implements CommodityService {

	private Logger logger = Logger.getLogger(CommodityServiceImpl.class);

	@Resource
	private CommodityDAO commodityDao;

	@Resource
	private CommodityPicDAO commodityPicDAO;

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

				logger.info("查询条件：name=" + query.getName());

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

				// 计算终端极润
				com.setProfitBr(com.getRetailPriceBr() - com.getPriceBr());
				com.setProfitPi(com.getProfitBr() * com.getStandardPice());

				// 生成条形码图片
				if (StringUtils.isNotBlank(com.getBarCode())) {
					String key = OssUtil.putObject(new ByteArrayInputStream(BarcodeUtil.generate(com.getBarCode())), ".png");
					com.setBarImgKey(key);
				}

				CommodityDO commodityDO = CommodityConvert.conv(com);

				commodityDao.insert(commodityDO);

				// 主图
				createComPic(commodityDO.getId(), com.getImgKey(), 1);

				// 次图
				if (!CollectionUtils.isEmpty(com.getImgKeys())) {
					for (String key : com.getImgKeys()) {
						createComPic(commodityDO.getId(), key, 0);
					}
				}
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
				comDo.setProfitBr(com.getRetailPriceBr() - com.getPriceBr());
				comDo.setProfitPi(com.getProfitBr() * com.getStandardPice());
				comDo.setRetailPriceBr(com.getRetailPriceBr());
				comDo.setDescription(com.getDesc());
				comDo.setPersonType(com.getPersonType());
				comDo.setPosition(com.getPosition());
				comDo.setBrand(com.getBrand());

				CommodityPicDO commodityPicDO = commodityPicDAO.getMainByComId(comDo.getId());
				if (!StringUtils.equals(commodityPicDO.getPicKey(), com.getImgKey())) {
					commodityPicDO.setPicKey(com.getImgKey());
					commodityPicDAO.update(commodityPicDO);
				}

				List<CommodityPicDO> commodityPicDOS = commodityPicDAO.queryOtherByComId(comDo.getId());
				List<String> imgKeys = com.getImgKeys();
				if (!CollectionUtils.isEmpty(commodityPicDOS)) {
					if (CollectionUtils.isEmpty(imgKeys)) {
						for (CommodityPicDO commodityPicDO1 : commodityPicDOS) {
							commodityPicDAO.delete(commodityPicDO1.getId());
						}
					} else {
						for (CommodityPicDO commodityPicDO1 : commodityPicDOS) {
							if (!imgKeys.contains(commodityPicDO1.getPicKey())) {
								commodityPicDAO.delete(commodityPicDO1.getId());
							}
						}

						for (String imgKey : imgKeys) {
							boolean canAdd = true;

							for (CommodityPicDO commodityPicDO1 : commodityPicDOS) {
								if (StringUtils.equals(imgKey, commodityPicDO1.getPicKey())) {
									canAdd = false;
								}
							}

							if (canAdd) {
								createComPic(comDo.getId(), imgKey, 0);
							}
						}
					}
				} else {
					if (!CollectionUtils.isEmpty(imgKeys)) {
						for (String imgKey : imgKeys) {
							createComPic(comDo.getId(), imgKey, 0);
						}
					}
				}

				// if 用户删除了原来的条形码，则把条形码图片key删除
				if (StringUtils.isBlank(com.getBarCode())) {
					if (StringUtils.isNotBlank(comDo.getBarCode())) {
						comDo.setBarImgKey(null);
					}
				} else {
					if (!StringUtils.equals(com.getBarCode(), comDo.getBarCode())) {
						comDo.setBarCode(com.getBarCode());
						String key = OssUtil.putObject(new ByteArrayInputStream(BarcodeUtil.generate(com.getBarCode())), ".png");
						comDo.setBarImgKey(key);
					}
				}

				commodityDao.update(comDo);
			}
		});
	}

	/**
	 * 创建商品图对象
	 *
	 * @param comId
	 * @param imgKey
	 */
	private void createComPic(long comId, String imgKey, int isMain) {
		CommodityPicDO commodityPicDO = new CommodityPicDO();
		commodityPicDO.setComId(comId);
		commodityPicDO.setIsMain(isMain);
		commodityPicDO.setPicKey(imgKey);
		commodityPicDAO.insert(commodityPicDO);
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
