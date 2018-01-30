package com.het.ice.web.controller.pc;

import com.het.ice.dao.query.OrderQuery;
import com.het.ice.model.Order;
import com.het.ice.service.OrderService;
import com.het.ice.service.template.Result;
import com.het.ice.web.controller.BaseController;
import com.het.ice.web.request.OrderActionRequest;
import com.het.ice.web.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/29.
 */

@Controller
@RequestMapping(value = "pc/order")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    /**
     * 根据订单状态（创建的、确认的、取消的、完成的）查询订单分页列表
     *
     * @param state
     * @param pageNum
     * @param pageSize
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "queryList.json", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryList(String state, String pageNum, String pageSize) throws IOException {
        WebResult webResult = new WebResult();

        OrderQuery query = new OrderQuery();
        query.setState(state);

        Result<List<Order>> result = orderService.queryByCondition(query, pageNum, pageSize);;
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
     * @param request
     * @return
     */
    @RequestMapping(value = "verify.json", method = { RequestMethod.POST })
    public @ResponseBody ModelMap verify(@RequestBody OrderActionRequest request) {
        WebResult webResult = new WebResult();
        Result<Void> result = orderService.verify(request.getId());
        if (result.isSuccess()) {
            webResult.setSuccess(true);
        } else {
            webResult.setMessage(false, result.getErrorMsg());
        }
        return webResult.getModel();
    }
}
