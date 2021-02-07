package com.taro.utils;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtil extends StringUtils {
	
	public static boolean isBlank(final CharSequence cs) {
		return StringUtils.isBlank(cs) || "null".equals(cs);
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return StringUtils.isNotBlank(cs) && !"null".equals(cs);
	}
	
}
