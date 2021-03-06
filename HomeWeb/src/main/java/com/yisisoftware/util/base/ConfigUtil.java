package com.yisisoftware.util.base;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author jonny
 *
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");
	
	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}
	/**
	 * 获得appkey
	 * 
	 * @return
	 */
	public static final String getSmsAppKey() {
		return bundle.getString("smsAppKey");
	}
	/**
	 * 获得smsURL
	 * 
	 * @return
	 */
	public static final String getSmsURL() {
		return bundle.getString("smsURL");
	}

	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}
}
