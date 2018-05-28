/**
 * 
 */
package com.chenyf.tencent.ai.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

/**
 * @author c
 *
 */
public class ImageUtils {
	
	public static String toBase64(File file) throws IOException {
		byte[] bytes = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		return new String(bytes);
	}

}
