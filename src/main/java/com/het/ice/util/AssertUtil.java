package com.het.ice.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.het.ice.service.exception.ParamCheckException;

/**
 * 校验
 * 
 * @author Administrator
 *
 */
public class AssertUtil {

	/**
	 * 
	 * @param field
	 * @param object
	 */
	public static void isNull(Object object, String field) {
		if (object == null) {
			throw new ParamCheckException(field);
		}
	}

	/**
	 * 
	 * @param field
	 * @param object
	 */
	public static void isNotNull(Object object, String field) {
		if (object != null) {
			throw new ParamCheckException(field);
		}
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param field
	 * @param string
	 */
	public static void isEmpty(String string, String field) {
		if (StringUtils.isEmpty(string)) {
			throw new ParamCheckException(field + "不能为空");
		}
	}

	/**
	 * 
	 * @param string
	 * @param length
	 * @param field
	 */
	public static void lengthThan(String string, int length, String field) {
		if (StringUtils.isEmpty(string)) {
			throw new ParamCheckException(field + "不能为空");
		}

		if (string.length() > length) {
			throw new ParamCheckException(field + "长度超出" + length + "个字符");
		}
	}

	/**
	 * 
	 * @param os
	 * @param field
	 */
	public static void isEmpty(Object[] os, String field) {
		if (os == null || os.length == 0) {
			throw new ParamCheckException(field);
		}
	}

	/**
	 * 
	 * @param list
	 * @param field
	 */
	public static void isEmpty(List<?> list, String field) {
		if (CollectionUtils.isEmpty(list)) {
			throw new ParamCheckException(field);
		}
	}

	public static void isNotEmpty(List<?> list, String field) {
		if (!CollectionUtils.isEmpty(list)) {
			throw new ParamCheckException(field);
		}
	}

	/**
	 * id 是否大于0
	 * 
	 * @param id
	 * @param field
	 */
	public static void moreThanZero(long id, String field) {
		if (id <= 0) {
			throw new ParamCheckException(field + "小于等于0");
		}
	}
}
