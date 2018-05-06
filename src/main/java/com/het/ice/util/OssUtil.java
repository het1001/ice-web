package com.het.ice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 阿里云oss工具
 * 
 * @author Administrator
 *
 */
public class OssUtil {

	private static final String ENDPOIOT = "oss-cn-hangzhou.aliyuncs.com";

	private static final String KEY = "LTAIifrLWuAMwVpR";

	private static final String SECRIT = "DdsIl69gVLedduE1hEJUkWb6Sy3EAF";

	public static final String bucketName = "ice2016";

	/**
	 * 获取oss数据
	 *
	 * @param key
	 * @return
	 */
	public static byte[] getObject(String key) {
		try {
			return IOUtils.toByteArray(getObjectStream(key));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public static InputStream getObjectStream(String key) {
		OSSClient ossClient = new OSSClient(ENDPOIOT, KEY, SECRIT);
		try {
			return ossClient.getObject(bucketName, key).getObjectContent();
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();
		}

		return null;
	}

	/**
	 * 附件存储
	 *
	 * @param input
	 * @param xff
	 * @return
	 */
	public static String putObject(InputStream input, String xff) {
		String key = UUIDUtil.getCode() + xff;
		OSSClient ossClient = new OSSClient(ENDPOIOT, KEY, SECRIT);
		ossClient.putObject(bucketName, key, input);
		ossClient.shutdown();
		return key;
	}

	/**
	 * 删除附件
	 *
	 * @param key
	 */
	public static void deleteObject(String key) {
		OSSClient ossClient = new OSSClient(ENDPOIOT, KEY, SECRIT);
		ossClient.deleteObject(bucketName, key);
		ossClient.shutdown();
	}

	/**
	 *
	 * @return
	 */
	public static OSSClient getOSSClient() {
		return new OSSClient(ENDPOIOT, KEY, SECRIT);
	}
}
