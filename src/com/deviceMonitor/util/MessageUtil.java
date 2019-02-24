package com.deviceMonitor.util;

import java.util.Random;

import com.deviceMonitor.util.message.client.AbsRestClient;
import com.deviceMonitor.util.message.client.JsonReqClient;
import com.deviceMonitor.util.message.client.XmlReqClient;

public class MessageUtil {
	private static final String ACCOUNT_SID = "6e8c05388f98391f39163074f69e66e5";
	private static final String AUTH_TOKEN = "772655b7e90b107d683b47760c22acb2";
	private static final String APP_ID = "b18635cd28c442619bbf8d51b3b15913";
	public static final String REGISTER_TEMPLATE_ID = "16591"; // 注册验证码
	public static final String FIND_PWD_TEMPLATE_ID = "16675"; // 忘记密码
	//private static final String REST_URL = "https://api.ucpaas.com";

	static AbsRestClient InstantiationRestAPI(boolean enable) {
		if (enable) {
			return new JsonReqClient();
		} else {
			return new XmlReqClient();
		}
	}

	public static String testTemplateSMS(boolean json, String random, String templateId, String to) {
		String result = "";
		try {
			String minute = "3";
			result = InstantiationRestAPI(json).templateSMS(ACCOUNT_SID,
					AUTH_TOKEN, APP_ID, templateId, to, random + "," + minute);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getRandom() {
		String random = "";
		do {
			Random rand = new Random();
			random = String.valueOf(rand.nextInt(900000) + 100000);
		} while (StringUtil.isBlank(random) || random.length() != 6);
		
		return random;
	}
}
