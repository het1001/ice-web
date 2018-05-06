package com.het.ice.web.controller.pc;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.het.ice.dao.query.CommodityInQuery;
import com.het.ice.model.CommodityIn;
import com.het.ice.model.CommodityInItem;
import com.het.ice.service.CommodityInService;
import com.het.ice.service.conv.CommodityInItemConvert;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.CommodityInItemWO;
import com.het.ice.web.result.WebResult;

import net.sf.json.JSONArray;

/**
 * 入库控制器
 *
 */
@Controller
@RequestMapping(value = "pc/commodityIn")
public class CommodityInController {

	@Autowired
	private CommodityInService commodityInService;

	/**
	 * 新建入库
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "create.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap create(@RequestBody String param) {
		WebResult webResult = new WebResult();

		@SuppressWarnings("unchecked")
		List<CommodityInItemWO> comInWos = (List<CommodityInItemWO>) JSONArray.toCollection(JSONArray.fromObject(param),
				CommodityInItemWO.class);
		Result<Void> result = commodityInService.create(CommodityInItemConvert.convs(comInWos));
		if (result.isSuccess()) {
			webResult.setMessage(true, "保存成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 查询入库列表
	 *
	 * @param fromTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(String fromTime, String endTime, String pageNum, String pageSize)
			throws ParseException {
		WebResult webResult = new WebResult();

		CommodityInQuery query = new CommodityInQuery();
		if (StringUtils.isNotEmpty(fromTime)) {
			query.setFromTime(DateUtils.parseDate(fromTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		}

		if (StringUtils.isNotEmpty(endTime)) {
			query.setEndTime(DateUtils.parseDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}

		Result<List<CommodityIn>> result = commodityInService.queryByCondition(query, pageNum, pageSize);

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 查看入库详情
	 *
	 * @param inId
	 * @return
	 */
	@RequestMapping(value = "queryDetail.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap queryDetail(String inId) {
		WebResult webResult = new WebResult();

		Result<List<CommodityInItem>> result = commodityInService.queryDetail(NumberUtils.toLong(inId));

		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

}
