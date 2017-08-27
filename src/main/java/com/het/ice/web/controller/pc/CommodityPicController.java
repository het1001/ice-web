package com.het.ice.web.controller.pc;

import com.het.ice.model.CommodityPic;
import com.het.ice.service.CommodityPicService;
import com.het.ice.service.template.Result;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品图片控制器
 *
 */
@Controller
@RequestMapping(value = "pc/commodityPic")
public class CommodityPicController {

	@Autowired
	private CommodityPicService commodityPicService;

	/**
	 * 获取商品主图
	 * 
	 * @param comId
	 * @return
	 */
	@RequestMapping(value = "getMainPicKey.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap getMainPicKey(String comId) {
		WebResult webResult = new WebResult();

		Result<CommodityPic> result = commodityPicService.getMainByComId(NumberUtils.toLong(comId, 0));

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 商品列表
	 * 
	 * @param comId
	 * @return
	 */
	@RequestMapping(value = "queryOtherPicKeys.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String comId) {
		WebResult webResult = new WebResult();
		Result<List<CommodityPic>> result = commodityPicService.queryOthersByComId(NumberUtils.toLong(comId, 0));

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
