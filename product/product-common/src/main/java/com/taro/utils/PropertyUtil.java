package com.taro.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class PropertyUtil {

	private static Map<String, Properties> propertyMap = new ConcurrentHashMap<String, Properties>();

	public static String getProperty(String path, String property)
			throws Exception {

		if (!propertyMap.containsKey(path)) {
			Properties mProps = new Properties();
			mProps.load(PropertyUtil.class.getClassLoader().getResource(path)
					.openStream());
			propertyMap.put(path, mProps);
		}

		return propertyMap.get(path).getProperty(property);
	}

	public static String getProperty(String property) throws Exception {
		return getProperty("system.properties", property);
	}
	
	/**
	 * 在办业务进度查询详情 URL 配置文件
	 * @param property
	 * @return
	 * @throws Exception
	 */
	public static String getZbywjdProperty(String property) throws Exception {
		return getProperty("zbywjd.properties", property);
	}

	public static void setProperty(String key, String value, String fileURL) {
		Properties prop = new Properties();
		InputStream fis = null;
		OutputStream fos = null;
		try {
			java.net.URL url = PropertyUtil.class.getClassLoader().getResource(fileURL);
			File file = new File(url.toURI());
			if (!file.exists())
				file.createNewFile();
			fis = new FileInputStream(file);
			prop.load(fis);
			fis.close();// 一定要在修改值之前关闭fis
			fos = new FileOutputStream(file);
			prop.setProperty(key, value);
			prop.store(fos, "Update '" + key + "' value");
			fos.close();
			propertyMap.put(fileURL, prop);

		} catch (IOException e) {
			System.err.println("Visit " + fileURL + " for updating " + value
					+ " value error");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void setProperty(String key, String value) throws Exception {
		
		setProperty(key, value, "system.properties");

	}
}
