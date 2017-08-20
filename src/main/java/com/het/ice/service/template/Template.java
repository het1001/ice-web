package com.het.ice.service.template;

import com.het.ice.service.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.het.ice.service.exception.ParamCheckException;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class Template {

	Logger LOGGER = Logger.getLogger(Template.class);

	/**
	 * 
	 * @param callback
	 * @return
	 */
	public <T> Result<T> complete(ResultCallback<T> callback) {

		Result<T> result = new Result<T>();

		try {
			callback.check();

			callback.before();

			callback.excute();

			callback.after();

			result.setResult(callback.returnValue);
		} catch (ParamCheckException e) {
			LOGGER.error("参数校验出错", e);
			buildResult(result, e);
		} catch (BizException e) {
			// 业务异常
			LOGGER.error("业务异常", e);
			buildBizResult(result, e);
		} catch (Exception e) {
			LOGGER.error("系统异常", e);
			buildResult(result, e);
		}

		return result;
	}

	/**
	 * 
	 * 
	 * @param callback
	 * @return
	 */
	public <T> Result<T> pageQuery(PageResultCallback<T> callback, String pageNum, String pageSize) {

		Result<T> result = new Result<T>();

		try {
			callback.check();

			callback.before();

			int num = NumberUtils.toInt(pageNum, 1);
			int limit = NumberUtils.toInt(pageSize, 50);
			int start = (num - 1) * limit;
			callback.excute(start, limit);

			callback.after();

			result.setResult(callback.returnValue);
			result.setTotal(callback.total);
		} catch (ParamCheckException e) {
			buildResult(result, e);
		} catch (BizException e) {
			// 业务异常
			buildBizResult(result, e);
		} catch (Exception e) {
			e.printStackTrace();
			buildResult(result, e);
		}

		return result;
	}

	private <T> void buildBizResult(Result<T> result, BizException e) {
		result.setSuccess(false);
		result.setErrorMsg(e.getResultMessage());
	}

	private <T> void buildResult(Result<T> result, Exception e) {
		result.setSuccess(false);
		if (StringUtils.isEmpty(e.getMessage())) {
			result.setErrorMsg(e.toString());
		} else {
			result.setErrorMsg(e.getMessage());
		}
	}

}
