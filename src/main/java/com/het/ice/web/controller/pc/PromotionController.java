package com.het.ice.web.controller.pc;

import com.het.ice.dao.query.PromotionQuery;
import com.het.ice.enums.PromotionStateEnum;
import com.het.ice.model.Promotion;
import com.het.ice.service.PromotionService;
import com.het.ice.service.conv.PromotionConvert;
import com.het.ice.service.template.Result;
import com.het.ice.web.request.PromotionWO;
import com.het.ice.web.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 促销控制器
 *
 */
@Controller
@RequestMapping(value = "pc/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	/**
	 * 新建促销
	 * 
	 * @param promotionWO
	 * @return
	 */
	@RequestMapping(value = "create.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap create(@RequestBody PromotionWO promotionWO) throws ParseException {
		WebResult webResult = new WebResult();

		Result<Void> result = promotionService.create(PromotionConvert.conv(promotionWO));

		if (result.isSuccess()) {
			webResult.setMessage(true, "保存成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 更新
	 *
	 * @param promotionWO
	 * @return
     */
	@RequestMapping(value = "update.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap update(@RequestBody PromotionWO promotionWO) throws ParseException {
		WebResult webResult = new WebResult();

		Result<Void> result = promotionService.update(PromotionConvert.conv(promotionWO));
		if (result.isSuccess()) {
			webResult.setMessage(true, "更新成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 启用
	 *
	 * @param promotionWO
	 * @return
	 */
	@RequestMapping(value = "online.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap online(@RequestBody PromotionWO promotionWO) throws ParseException {
		WebResult webResult = new WebResult();

		Result<Void> result = promotionService.updateState(promotionWO.getId(), PromotionStateEnum.ON_LINE);
		if (result.isSuccess()) {
			webResult.setMessage(true, "更新成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 更新
	 *
	 * @param promotionWO
	 * @return
	 */
	@RequestMapping(value = "offline.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap offline(@RequestBody PromotionWO promotionWO) throws ParseException {
		WebResult webResult = new WebResult();

		Result<Void> result = promotionService.updateState(promotionWO.getId(), PromotionStateEnum.OFF_LINE);
		if (result.isSuccess()) {
			webResult.setMessage(true, "更新成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 删除
	 *
	 * @param promotionWO
	 * @return
	 */
	@RequestMapping(value = "delete.json", method = { RequestMethod.POST })
	public @ResponseBody ModelMap delete(@RequestBody PromotionWO promotionWO) {
		WebResult webResult = new WebResult();

		Result<Void> result = promotionService.delete(promotionWO.getId());

		if (result.isSuccess()) {
			webResult.setMessage(true, "删除成功");
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}

	/**
	 * 列表
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "queryList.json", method = { RequestMethod.GET })
	public @ResponseBody ModelMap list(Long comId, String state, Boolean isCurrent, String pageNum, String pageSize)
			throws ParseException {
		WebResult webResult = new WebResult();

		PromotionQuery query = new PromotionQuery();
		query.setComId(comId == null ? 0 : comId);
		query.setState(state);
		if (isCurrent != null && isCurrent) {
			query.setDate(new Date());
		}

		Result<List<Promotion>> result = promotionService.queryByCondition(query, pageNum, pageSize);
		if (result.isSuccess()) {
			webResult.setData(true, result.getResult());
			webResult.setTotal(result.getTotal());
		} else {
			webResult.setMessage(false, result.getErrorMsg());
		}

		return webResult.getModel();
	}
}
