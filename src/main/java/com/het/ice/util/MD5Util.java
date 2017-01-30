package com.het.ice.util;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码生成器
 * 
 * @author Administrator
 *
 */
public class MD5Util {

	/**
	 * 
	 * 
	 * @param pw
	 * @return
	 */
	public static String encry(String pw) {
		if (StringUtils.isEmpty(pw)) {
			return "";
		}

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5 = md.digest(pw.getBytes());

			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5.length; i++) {
				int val = (md5[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (Exception e) {
		}
		return "";
	}

}
