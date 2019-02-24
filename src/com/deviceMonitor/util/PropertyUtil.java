package com.deviceMonitor.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Property参数工具
 */
public final class PropertyUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);
	/**
	 *构造函数
	 * 
	 */
	private PropertyUtil() {

	}

	/** Property map */
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 获取配置参数
	 * 
	 * @param strKey
	 *            key
	 * @return String value
	 */
	public static String getPropertyValue(String strKey) {
		return map.get(strKey);
	}

	/**
	 * 设置参数
	 * 
	 * @param strKey
	 *            strKey
	 * @param value
	 *            value
	 */
	public static void setPropertyValue(String strKey, String value) {
		map.put(strKey, value);
	}

	static {
		// 读取Property文件
		Properties prop = new Properties();
		try {
			prop.load(Resources.getResourceAsStream("resources/config.properties"));
			
			Enumeration<?> en = prop.propertyNames();
			while (en.hasMoreElements()) {
				String strKey = (String) en.nextElement();
				String strValue = StringUtil.strRemoveNullAndBlank(prop.getProperty(strKey));

				PropertyUtil.setPropertyValue(strKey, strValue);
			}
		} catch (Exception e) {
			LOGGER.error("读取Property文件：" + e.getMessage());
		}
	}
}
