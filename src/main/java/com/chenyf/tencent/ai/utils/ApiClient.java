package com.chenyf.tencent.ai.utils;

import static org.assertj.core.api.Assertions.entry;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * @author chenyifei
 * @date 2018年5月26日
 * 
 */
public class ApiClient {

//	public static Map<String, String> createCommonParams(String appid) {
//		Map<String, String> params = new HashMap<>();
//		params.put("app_id", appid);
//		params.put("time_stamp", System.currentTimeMillis() / 1000 + "");
//		params.put("nonce_str", System.currentTimeMillis() + "");
//		return params;
//	}

	

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
		sb.append("app_key").append("=").append(appKey);

		String result = DigestUtils.md5Hex(sb.toString()).toUpperCase();

		return result;
	}
	
	public static void requestPost(String url, AppInfo appInfo, Map<String, String> params){
		
		params.put("app_id", appInfo.getAppID());
		params.put("time_stamp", System.currentTimeMillis() / 1000 + "");
		params.put("nonce_str", System.currentTimeMillis() + "");
		
		String sign = ApiClient.getSign(params, appInfo.getAppKey());
		params.put("sign", sign);
		
		
		
		Builder builder = new FormBody.Builder();
		for (Entry<String, String> param : params.entrySet()) {
			builder.add(param.getKey(), param.getValue());
		}
		
		OkHttpClient client = new OkHttpClient();
		FormBody formBody = builder.build();
		Request request = new Request.Builder()
				.url(url)
				.post(formBody)
				.build();
		try {
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
