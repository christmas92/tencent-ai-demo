package com.chenyf.tencent.ai.ocr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.chenyf.tencent.ai.utils.ApiClient;
import com.chenyf.tencent.ai.utils.AppInfo;
import com.chenyf.tencent.ai.utils.ImageUtils;

/**
 * @author chenyifei
 * @date 2018年5月26日
 * 
 */
public class IdCardOcrApi {
	
	public enum CardType{
		FRONT(0), BACK(1);
		
		private int type;
		
		private CardType(int type) {
			this.type = type;
		}
		
		public int getType() {
			return type;
		}
	}
	
	private static final String idCardOcrUrl = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_idcardocr";
	
	public static Map<String, String> getIdCardInfo(AppInfo appInfo, File imageFile, CardType cardType) throws IOException{
		Map<String, String> params = new HashMap<>();
		String imageBase64 = ImageUtils.toBase64(imageFile);
		params.put("image", imageBase64);
		params.put("card_type", cardType.getType() + "");
		
		ApiClient.requestPost(idCardOcrUrl, appInfo, params);
		
		return params;
	}

}
