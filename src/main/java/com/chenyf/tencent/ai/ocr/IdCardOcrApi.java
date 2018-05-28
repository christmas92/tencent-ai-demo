package com.chenyf.tencent.ai.ocr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
public class IdCardOcrApi {

	enum CardType {
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

	private static String getIdCardInfo(AppInfo appInfo, File imageFile, CardType cardType) throws IOException {
		Map<String, String> params = new HashMap<>();
		String imageBase64 = ImageUtils.toBase64(imageFile);
		params.put("image", imageBase64);
		params.put("card_type", cardType.getType() + "");

		return ApiClient.post(idCardOcrUrl, appInfo, params);
	}

	public static IdCardFrontInfo getIdCardFrontInfo(AppInfo appInfo, File imageFile) throws IOException {
		String jsonStr = IdCardOcrApi.getIdCardInfo(appInfo, imageFile, CardType.FRONT);

		Result<IdCardFrontInfo> result = new Gson().fromJson(jsonStr, new TypeToken<Result<IdCardFrontInfo>>() {
		}.getType());
		int ret = result.getRet();
		if (ret != 0) {
			throw new RuntimeException("IdCardOcrApi error code " + ret + ":" + result.getMsg());
		}
		return result.getData();
	}
	
	public static IdCardBackInfo getIdCardBackInfo(AppInfo appInfo, File imageFil) throws IOException {
		String jsonStr = IdCardOcrApi.getIdCardInfo(appInfo, imageFil, CardType.BACK);

		Result<IdCardBackInfo> result = new Gson().fromJson(jsonStr, new TypeToken<Result<IdCardBackInfo>>() {
		}.getType());
		int ret = result.getRet();
		if (ret != 0) {
			throw new RuntimeException("IdCardOcrApi error code " + ret + ":" + result.getMsg());
		}
		return result.getData();
	}

	public class IdCardFrontInfo {

		@SerializedName("name")
		private String name;
		@SerializedName("sex")
		private String sex;
		@SerializedName("nation")
		private String nation;
		@SerializedName("birth")
		private String birth;
		@SerializedName("address")
		private String address;
		@SerializedName("id")
		private String id;
		@SerializedName("frontimage")
		private String frontImage;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getNation() {
			return nation;
		}

		public void setNation(String nation) {
			this.nation = nation;
		}

		public String getBirth() {
			return birth;
		}

		public void setBirth(String birth) {
			this.birth = birth;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFrontImage() {
			return frontImage;
		}

		public void setFrontImage(String frontImage) {
			this.frontImage = frontImage;
		}

	}

	public class IdCardBackInfo {

		@SerializedName("authority")
		private String authority;
		@SerializedName("valid_date")
		private String validDate;
		@SerializedName("backimage")
		private String backImage;

		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}

		public String getValidDate() {
			return validDate;
		}

		public void setValidDate(String validDate) {
			this.validDate = validDate;
		}

		public String getBackImage() {
			return backImage;
		}

		public void setBackImage(String backImage) {
			this.backImage = backImage;
		}

	}

}
