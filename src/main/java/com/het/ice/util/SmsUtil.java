package com.het.ice.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.het.ice.enums.DingTypeEnum;
import net.sf.json.JSONArray;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 短信工具
 * 
 * @author Administrator
 *
 */
public class SmsUtil {
	public static boolean send(String phone, String code) {
		try {
			final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
			final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
			final String accessKeyId = "LTAIifrLWuAMwVpR";//你的accessKeyId,参考本文档步骤2
			final String accessKeySecret = "DdsIl69gVLedduE1hEJUkWb6Sy3EAF";//你的accessKeySecret，参考本文档步骤2

			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
					accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

			IAcsClient acsClient = new DefaultAcsClient(profile);

			SendSmsRequest request = new SendSmsRequest();
			//使用post提交
			request.setMethod(MethodType.POST);
			//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
			request.setPhoneNumbers(phone);
			//必填:短信签名-可在短信控制台中找到
			request.setSignName("白云冷饮");
			//必填:短信模板-可在短信控制台中找到
			request.setTemplateCode("SMS_33555146");
			//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			request.setTemplateParam("{\"code\": \"" + code + "\"}");

			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				return true;
			}
			JSONArray mobls = new JSONArray();
			mobls.add("18757169657");

			DingdingSendUtil.send(DingTypeEnum.TEXT, "短信服务异常", sendSmsResponse.getCode(), mobls, false);
			return false;
		} catch (ServerException e) {
			e.printStackTrace();
			return false;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
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

	/**
	 * 创建随机验证码
	 *
	 * @return
	 */
	public static String createAuthCode() {
		int max=999999;
		int min=100000;
		Random random = new Random();

		return (random.nextInt(max)%(max-min+1) + min) + "";
	}
}
