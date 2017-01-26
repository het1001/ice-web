package com.het.ice.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

/**
 * 短信发送
 * 
 * @author Administrator
 *
 */
public class SmsUtil {

	public static boolean send(String phone, String code) {

		return true;

		/*try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIifrLWuAMwVpR",
					"DdsIl69gVLedduE1hEJUkWb6Sy3EAF");
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");

			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName("白云冷饮");
			request.setTemplateCode("SMS_33555146");
			request.setParamString("{\"code\": \"" + code + "\"}");
			request.setRecNum(phone);
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			return true;
		} catch (ServerException e) {
			e.printStackTrace();
			return false;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}*/
	}

	/*
	 * 说明：移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189
	 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 验证号码 手机号 固话均可
	 */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;

		String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";

		Pattern pattern = Pattern.compile(expression);

		Matcher matcher = pattern.matcher(phoneNumber);

		if (matcher.matches()) {
			isValid = true;
		}

		return isValid;

	}

	public static String createAuthCode() {
		int max=999999;
		int min=100000;
		Random random = new Random();

		return (random.nextInt(max)%(max-min+1) + min) + "";
	}
}
