package com.chenyf.tencent.ai.ocr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chenyf.tencent.ai.utils.ApiClient;
import com.chenyf.tencent.ai.utils.ApiClient.Result;
import com.chenyf.tencent.ai.utils.AppInfo;
import com.chenyf.tencent.ai.utils.ImageUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * @author chenyifei
 * @date 2018年5月26日
 * 
 */
public class BizLicenseOcrApi {

	private static final String bizLicenseOcrUrl = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_bizlicenseocr";

	public static BizLicenseInfo getBusinessCardInfo(AppInfo appInfo, File imageFile) throws IOException {
		Map<String, String> params = new HashMap<>();
		String imageBase64 = ImageUtils.toBase64(imageFile);
		params.put("image", imageBase64);

		String jsonStr = ApiClient.post(bizLicenseOcrUrl, appInfo, params);
		
		Result<BizLicenseInfo> result = new Gson().fromJson(jsonStr, new TypeToken<Result<BizLicenseInfo>>() {
		}.getType());
		int ret = result.getRet();
		if (ret != 0) {
			throw new RuntimeException("BizLicenseOcrUrl error code " + ret + ":" + result.getMsg());
		}
		return result.getData();
	}

	public class BizLicenseInfo {

		@SerializedName("angle")
		private String angle;
		@SerializedName("item_list")
		private List<Item> itemList;

		public String getAngle() {
			return angle;
		}

		public void setAngle(String angle) {
			this.angle = angle;
		}

		public List<Item> getItemList() {
			return itemList;
		}

		public void setItemList(List<Item> itemList) {
			this.itemList = itemList;
		}
		
		public class Item {
			@SerializedName("item")
			private String item;
			@SerializedName("itemstring")
			private String itemStr;

			public String getItem() {
				return item;
			}

			public void setItem(String item) {
				this.item = item;
			}

			public String getItemStr() {
				return itemStr;
			}

			public void setItemStr(String itemStr) {
				this.itemStr = itemStr;
			}

		}
	}
	
	
}
