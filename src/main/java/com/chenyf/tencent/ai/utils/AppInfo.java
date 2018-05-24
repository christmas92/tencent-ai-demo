/**
 * 
 */
package com.chenyf.tencent.ai.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author c
 *
 */
@Component
@ConfigurationProperties(prefix="tencent.ai")
public class AppInfo {

	private String AppID;
	private String AppKey;

	public String getAppID() {
		return AppID;
	}

	public void setAppID(String appID) {
		AppID = appID;
	}

	public String getAppKey() {
		return AppKey;
	}

	public void setAppKey(String appKey) {
		AppKey = appKey;
	}

}
