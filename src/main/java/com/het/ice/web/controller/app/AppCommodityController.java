package com.het.ice.web.controller.app;

import com.het.ice.dao.query.CommodityQuery;
import com.het.ice.model.Commodity;
import com.het.ice.service.CommodityService;
import com.het.ice.service.conv.CommodityConvert;
import com.het.ice.service.template.Result;
import com.het.ice.web.model.CommodityWO;
import com.het.ice.web.model.WebResult;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "appcommodity")
public class AppCommodityController {

	@Autowired
	private CommodityService commodityService;

	/**
	 * 商品列表
	 * 
	 * @param name
	 * @param brand
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String name, String brand, String status, String pageNum, String pageSize) {
		WebResult webResult = new WebResult();

		CommodityQuery query = new CommodityQuery();
		query.setBizId(1);
		query.setName(name);
		query.setBrand(brand);
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

	public static void main(String[] args) {
		Pattern p = Pattern.compile("^/app/.*.json$");
		Matcher m = p.matcher("/1app/test/test.json");
		System.out.print(m.find());
	}
}
