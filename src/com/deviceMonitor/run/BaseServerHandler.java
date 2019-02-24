package com.deviceMonitor.run;

import java.io.DataOutputStream;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deviceMonitor.constant.ResultEnum;
import com.deviceMonitor.model.syn.SynResultMessage;
import com.deviceMonitor.util.JacksonUtil;

public class BaseServerHandler {

	private static final String[] CONTEXT = {
			"classpath:springConfig/applicationContext-servlet.xml",
			"classpath:springConfig/jdbc-context.xml" };

	private static ClassPathXmlApplicationContext context = null;

	static {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(CONTEXT);
		}
	}

	public static void writeSynResultMessage(DataOutputStream dos, ResultEnum resultEnum, String msg) throws Exception {
		SynResultMessage resultMessage = new SynResultMessage();
		resultMessage.setReturn(resultEnum.getValue());
		resultMessage.setMsg(msg);
		String result = JacksonUtil.bean2Json(resultMessage);

		// 返回客户端消息
		dos.writeUTF(result);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Object obj, String beanId) {
		return ((T) context.getBean(beanId));
	}
}
