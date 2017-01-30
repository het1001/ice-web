package com.het.ice.web.model;

import org.springframework.ui.ModelMap;

public class WebResult {

	public static final String SUCCESS = "success";

	public static final String DATA = "data";

	public static final String TOTAL = "total";

	public static final String MESSAGE = "message";

	public static final String ERROR_MSG = "errorMsg";

	public static final String RESULT_CODE = "resultCode";

	private ModelMap model = new ModelMap();

	public WebResult() {
	}

	public WebResult(ModelMap model) {
		this.model = model;
	}

	public WebResult(boolean isSuccess, String message, Object data) {
		setResult(isSuccess, message, data);
	}

	public void setResult(boolean isSuccess, String message, Object data) {
		model.put(SUCCESS, isSuccess);
		if (isSuccess) {
			model.put(MESSAGE, message);
		} else {
			model.put(ERROR_MSG, message);
		}

		model.put(DATA, data);
	}

	public void setSuccess(boolean isSuccess) {
		setResult(isSuccess, null, null);
	}

	public void setMessage(boolean isSuccess, String message) {
		setResult(isSuccess, message, null);
	}

	public void setData(boolean isSuccess, Object data) {
		setResult(isSuccess, null, data);
	}

	public void setTotal(int total) {
		model.put(TOTAL, total);
	}

	public void setResultCode(boolean isSuccess, String resultCode) {
		model.put(SUCCESS, isSuccess);
		model.put(RESULT_CODE, resultCode);
	}

	public ModelMap getModel() {
		return model;
	}
}
