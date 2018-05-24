package com.chenyf.tencent.ai;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chenyf.tencent.ai.utils.AppInfo;
import com.chenyf.tencent.ai.utils.Authc;
import com.chenyf.tencent.ai.utils.ImageUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TencentAiDemoApplicationTests {

	@Autowired
	private AppInfo appInfo;
	
	@Test
	public void contextLoads() throws IOException {
//		System.out.println(appInfo.getAppID());
//		System.out.println(appInfo.getAppKey());
		File file = new File("F:\\chenyf\\eclipse-workspace\\tencent-ai-demo\\src\\test\\java\\test.jpg");
		String image = ImageUtils.toBase64(file);
		String appid = appInfo.getAppID();
		String timeStamp = "1493468759";
		String nonceStr = "fa577ce340859f9fe";
		String cardType = "0";
		
		HashMap<String, String> map = new HashMap<>();
		map.put("app_id", appid);
		map.put("time_stamp", System.currentTimeMillis() / 1000 + "");
		map.put("nonce_str", nonceStr);
		map.put("image", image);
		map.put("card_type", cardType);
		String sign = Authc.getSign(map, appInfo.getAppKey());
		map.put("sign", sign.toUpperCase());
		System.out.println(map);
	}
}
