package com.het.ice.util;

import java.io.IOException;
import java.io.InputStream;

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

	private static final String bucketName = "ice2016";

	public static byte[] getObject(String key) {
		OSSClient ossClient = new OSSClient(ENDPOIOT, KEY, SECRIT);
		try {
			return IOUtils.toByteArray(ossClient.getObject(bucketName, key).getObjectContent());
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();
		}

		return null;
	}

	public static String putObject(InputStream input) {
		String key = UUIDUtil.getCode();
		OSSClient ossClient = new OSSClient(ENDPOIOT, KEY, SECRIT);
		PutObjectResult result = ossClient.putObject(bucketName, key, input);
		ossClient.shutdown();
		System.out.println(result);

		return key;
	}

}
