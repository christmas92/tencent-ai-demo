package com.chenyf.tencent.ai;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chenyf.tencent.ai.ocr.BizCardOcrApi;
import com.chenyf.tencent.ai.ocr.IdCardOcrApi;
import com.chenyf.tencent.ai.ocr.IdCardOcrApi.IdCardBackInfo;
import com.chenyf.tencent.ai.ocr.IdCardOcrApi.IdCardFrontInfo;
import com.chenyf.tencent.ai.utils.AppInfo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TencentAiDemoApplicationTests {

	@Autowired
	private AppInfo appInfo;
	
	@Test
	public void contextLoads() throws IOException {
//		System.out.println(appInfo.getAppID());
//		System.out.println(appInfo.getAppKey());
//		File imageFile = new File("E:\\test.jpg");
//		IdCardFrontInfo idCardInfo = IdCardOcrApi.getIdCardFrontInfo(appInfo, imageFile);
//		IdCardBackInfo backInfo = IdCardOcrApi.getIdCardBackInfo(appInfo, imageFile);
//		System.out.println(backInfo.getAuthority());
//		File imageFile = new File("D:\\business.jpg");
//		BusinessCardInfo businessCardInfo = BizCardOcrApi.getBusinessCardInfo(appInfo, imageFile);
//		System.out.println(businessCardInfo.getItemList().get(0).getItemStr());
	}
}
