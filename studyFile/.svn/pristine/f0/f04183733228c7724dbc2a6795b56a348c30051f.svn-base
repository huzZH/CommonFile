package cn.ssha.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DownloadUtils {
	public static String getDownloadFileName(String agent, String filename) throws UnsupportedEncodingException {
		if (agent.contains("MSIE")) {
			// IE浏览器
			filename = URLEncoder.encode(filename, "utf-8");

		}else {
			// 其它浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}

		return filename;
	}
	/**
	 * 下载文件
	 */
	public static InputStream downloadFile(String filePath){
		File file = new File(filePath);
		FileInputStream fileInputStream = null;
		if(file.exists()){
			try {
				fileInputStream = new FileInputStream(file);
				return fileInputStream;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("转换文件流失败！");
			}
		}
		return null;
	}
}
