package com.het.ice.web.controller.pc;

import java.util.List;

import com.het.ice.util.CommonConstants;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.model.Commodity;
import com.het.ice.service.CommodityService;
import com.het.ice.service.conv.CommodityConvert;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.CommodityWO;
import com.het.ice.web.result.WebResult;

/**
 * 商品控制器
 *
 */
@Controller
@RequestMapping(value = "pc/commodity")
public class CommodityController {

	@Autowired
	private CommodityService commodityService;

	/**
	 * 新建商品
	 * 
	 * @param commodityWO
	 * @return
	 */
	@RequestMapping(value = "create.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap create(@RequestBody CommodityWO commodityWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = commodityService.create(CommodityConvert.conv(commodityWO));

		if (result.isSuccess()) {
			webResult.setMessage(true, "保存成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 更新商品
	 *
	 * @param commodityWO
	 * @return
     */
	@RequestMapping(value = "update.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap update(@RequestBody CommodityWO commodityWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = commodityService.update(CommodityConvert.conv(commodityWO));

		if (result.isSuccess()) {
			webResult.setMessage(true, "更新成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 检查商品名称是否已存在
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "check.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap check(String id, String name) {
		WebResult webResult = new WebResult();

		Result<Commodity> result = commodityService.getByName(name);

		if (result.isSuccess()) {
			boolean checkResult = false;
			if (result.getResult() == null) {
				checkResult = true;
			} else if (result.getResult().getId() == NumberUtils.toLong(id, 0)) {
				checkResult = true;
			}

			webResult.setData(true, checkResult);
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 商品列表
	 * 
	 * @param name
	 * @param brandId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String name, String brandId, String status, String pricCatId, String packCatId, String pageNum, String pageSize) {
		WebResult webResult = new WebResult();

		CommodityQuery query = new CommodityQuery();
		query.setBizId(CommonConstants.DEFAULT_BIZ_ID);
		query.setName(name);
		query.setBrandId(NumberUtils.toLong(brandId));
		query.setPricCatId(NumberUtils.toLong(pricCatId));
		query.setPackCatId(NumberUtils.toLong(packCatId));
		query.setStatus(status);

		Result<List<Commodity>> result = commodityService.queryByCondition(query, pageNum, pageSize);

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryAll.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap all() {
		WebResult webResult = new WebResult();

		Result<List<Commodity>> result = commodityService.queryAll();

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	@RequestMapping(value = "online.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap online(@RequestBody CommodityWO commodityWO) {
		WebResult webResult = new WebResult();
		updateState(webResult, commodityWO.getId(), 1);
		return webResult.getModel();
	}

	@RequestMapping(value = "offline.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap offline(@RequestBody CommodityWO commodityWO) {
		WebResult webResult = new WebResult();
		updateState(webResult, commodityWO.getId(), 0);
		return webResult.getModel();
	}

	private void updateState(WebResult webResult, String id, int state) {
		Result<Commodity> result = commodityService.getById(NumberUtils.toLong(id));

		if (result.isSuccess() && result.getResult() != null) {
			Commodity com = result.getResult();
			com.setState(state);
			Result<Void> re = commodityService.updateState(com);
			if (re.isSuccess()) {
				webResult.setData(true, true);
			} else {
				webResult.setMessage(false, re.getErrorMsg());
			}
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}
	}
}
