package com.het.ice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtil {

	public static String getCode() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * @return
	 */
	public static String getNum() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date());
	}
}
