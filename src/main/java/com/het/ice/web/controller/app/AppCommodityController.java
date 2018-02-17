package com.het.ice.web.controller.app;

import com.het.ice.enums.CatTypeEnum;
import com.het.ice.model.Brand;
import com.het.ice.model.Cat;
import com.het.ice.model.Commodity;
import com.het.ice.model.CommodityPic;
import com.het.ice.service.BrandService;
import com.het.ice.service.CatService;
import com.het.ice.service.CommodityPicService;
import com.het.ice.service.CommodityService;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.CommodityRequest;
import com.het.ice.web.result.WebResult;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "app/commodity")
public class AppCommodityController {

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private CommodityPicService commodityPicService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private CatService catService;

	/**
	 * 商品列表
	 *
	 * @return
	 */
	@RequestMapping(value = "queryAllOnline.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap queryAllOnline(@RequestBody CommodityRequest request) {
		WebResult webResult = new WebResult();
		Result<List<Commodity>> result = commodityService.queryAllOnline(request.getBrandId(), request.getPricCatId(), request.getPackCatId());
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryPicByComId.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap queryPicByComId(@RequestBody CommodityRequest request) {
		WebResult webResult = new WebResult();

		Result<List<CommodityPic>> result = commodityPicService.queryAllByComId(NumberUtils.toLong(request.getId()));
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "getConditions.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap getConditions() {
		WebResult webResult = new WebResult();

		Result<List<Brand>> brands = brandService.queryAll();
		Result<List<Cat>> cat1s = catService.queryByType(CatTypeEnum.PRICE);
		Result<List<Cat>> cat2s = catService.queryByType(CatTypeEnum.PACKAGE);
		if (brands.isSuccess() && cat1s.isSuccess() && cat2s.isSuccess()) {
			Map<String, Object> result = new HashMap<>();
			result.put("brandId", new Condition("brandId", "品牌", brands.getResult()));
			result.put("pricCat", new Condition("pricCatId", "价格类", cat1s.getResult()));
			result.put("packCat", new Condition("packCatId", "包装类", cat2s.getResult()));
			webResult.setData(true, result);
		} else {
			webResult.setMessage(false, "失败啦");
		}

		return webResult.getModel();
	}

	class Condition {
		private String code;

		private String desc;

		private Object data;

		Condition(String code, String desc, Object data) {
			this.code = code;
			this.desc = desc;
			this.data = data;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
	}
}
