package com.het.ice.web.controller.app;

import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.model.Commodity;
import com.het.ice.service.CommodityService;
import com.het.ice.service.template.Result;
import com.het.ice.web.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "app/commodity")
public class AppCommodityController {

	@Autowired
	private CommodityService commodityService;

	/**
	 * 商品列表
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String pageNum, String pageSize) {
		WebResult webResult = new WebResult();
		CommodityQuery query = new CommodityQuery();
		query.setBizId(1);
		query.setStatus("1");

		Result<List<Commodity>> result = commodityService.queryByCondition(query, pageNum, pageSize);

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
