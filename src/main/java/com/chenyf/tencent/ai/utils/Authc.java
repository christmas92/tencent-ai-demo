/**
 * 
 */
package com.chenyf.tencent.ai.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author c
 *
 */
public class Authc {
	
	private static final String APP_KEY_NAME = "app_key";
	
	public static String getSign(Map<String, String> params, String appKey) {
		
		TreeMap<String, String> sortedParams = new TreeMap<>(params);
		
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> entry : sortedParams.entrySet()) {
			sb.append(entry.getKey()).append("=");
			
			String value = entry.getValue();
			try {
				value = URLEncoder.encode(entry.getValue(), Charsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				
			}
			sb.append(value).append("&");
		}
		sb.append(APP_KEY_NAME).append("=").append(appKey);
		
		String result = DigestUtils.md5Hex(sb.toString());

		return result;
	}

}
