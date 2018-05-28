package com.chenyf.tencent.ai.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author chenyifei
 * @date 2018年5月26日
 * 
 */
public class ApiClient {

	private static String getSign(Map<String, String> params, String appKey) {

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

	public static String post(String url, AppInfo appInfo, Map<String, String> params) throws IOException {

		params.put("app_id", appInfo.getAppID());
		params.put("time_stamp", System.currentTimeMillis() / 1000 + "");
		params.put("nonce_str", System.currentTimeMillis() + "");

		String sign = ApiClient.getSign(params, appInfo.getAppKey());
		params.put("sign", sign);

		OkHttpClient client = new OkHttpClient();

		FormBody.Builder builder = new FormBody.Builder();
		for (Entry<String, String> param : params.entrySet()) {
			builder.add(param.getKey(), param.getValue());
		}

		Request request = new Request.Builder().url(url).post(builder.build()).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().string();
		} else {
			throw new RuntimeException("Unexpected code " + response);
		}
	}

	public class Result<T> {
		private int ret;
		private String msg;
		private T data;

		public int getRet() {
			return ret;
		}

		public void setRet(int ret) {
			this.ret = ret;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

	}

}
