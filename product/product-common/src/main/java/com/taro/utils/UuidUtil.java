package com.taro.utils;

import java.util.UUID;

/**
 * @File: UuidUtil.java
 * @Description: 生成主键
 * @Author: 张宇唯
 * @Date: 2016年12月15日
 * @Copyright taro(c)2010-2016
 */
public class UuidUtil {
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	public static String get36UUID() {
		String uuid = UUID.randomUUID().toString().trim();
		return uuid;
	}
	
}
