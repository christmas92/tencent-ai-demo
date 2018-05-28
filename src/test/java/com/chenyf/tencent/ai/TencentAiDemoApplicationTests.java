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

import com.chenyf.tencent.ai.ocr.IdCardOcrApi;
import com.chenyf.tencent.ai.ocr.IdCardOcrApi.CardType;
import com.chenyf.tencent.ai.utils.AppInfo;
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
		File file = new File("/Users/chenyifei/Desktop/idcardtest.jpeg");
		IdCardOcrApi.getIdCardInfo(appInfo, file, CardType.FRONT);
	}
}
