package com.taro.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title:StringUtil.java</p>
 * <p>Description:字符串工具类</p>
 * <p>Copyright:Copyright (c) 2017</p>
 * @author gavin
 * @version 1.0 2017-6-21
 */
public class StringUtil {
	/**
	 * <b>Summary: 判断字符串是否为空 </b> isNull
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			if (obj instanceof String && "".equals(((String) obj).trim())) {
				return true;
			}
			return false;
		}
	}

	/**
	 * 过滤掉为null的字符串
	 * 
	 * @param: String param
	 * @return: String
	 */
	public static String deNull(String param) {
		if (isNull(param))
			return "";
		return param.trim();
	}

	/**
	 * <b>Summary:获取uuid</b> getUUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

	/**
	 * 获取客户端真实ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!isNull(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!isNull(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 将字符串集合转成逗号分隔的字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String ListToString(List<String> list) {
		if (list != null && list.size() > 0) {
			return list.toString().replace("[", "").replace("]", "")
					.replaceAll(" ", "");
		} else {
			return "";
		}
	}

	public static List<String> stringToList(String str){
		List<String> list = new ArrayList<String>();
		if(str != null) {
			String[] strs = str.split(",");
			for(String s : strs) {
				list.add(s);
			}
		}
		return list;
	}
	
	/**
	 * checkPassword:(校验密码强度). <br/>
	 * 
	 * @author gavin
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(String password) {
		boolean flag = false;
		Pattern enoughPattern = Pattern.compile("(?=.{8,}).*");
		Pattern mediumPattern = Pattern
				.compile("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))|"
						+ "((?=.*[A-Z])(?=.*[a-z])(?=.*\\W))|"
						+ "((?=.*[A-Z])(?=.*[0-9])(?=.*\\W))|"
						+ "((?=.*[a-z])(?=.*[0-9])(?=.*\\W))).*$");
		Pattern strongPattern = Pattern
				.compile("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$");

		Matcher enoughMatcher = enoughPattern.matcher(password);
		Matcher mediumMatcher = mediumPattern.matcher(password);
		Matcher strongMatcher = strongPattern.matcher(password);
		if (!enoughMatcher.matches()) {// 密码小于八位
			flag = false;
		} else if (strongMatcher.matches()) { // 密码为八位及以上并且大写字母、小写字母、数字、特殊字符都包括
			flag = true;
		} else if (mediumMatcher.matches()) { // 密码为八位及以上并且大写字母、小写字母、数字、特殊字符三项
			flag = true;
		} else { // 如果密码为8位及以下，就算字母、数字、特殊字符三项都包括，强度也是不够
			flag = false;
		}
		return flag;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param str
	 * @return
	 */
	public static int getLength(String value) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 截取字符串长度(中文2个字节，半个中文显示一个)
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getSubstring(String str, int len) {
		if (str.length() < len / 2)
			return str;
		int count = 0;
		StringBuffer sb = new StringBuffer();
		String[] ss = str.split("");
		for (int i = 1; i < ss.length; i++) {
			count += ss[i].getBytes().length > 1 ? 2 : 1;
			sb.append(ss[i]);
			if (count >= len)
				break;
		}
		// 不需要显示...的可以直接return sb.toString();
		return (sb.toString().length() < str.length()) ? sb.append("...")
				.toString() : str;
	}
	
	/**
	 * stringToSubList:(分解list). <br/>
	 * 例如a,b,c,d
	 * 分解成    a,b,c,d
	 * a,b,c
	 * a,b
	 * a
	 * @author gavin
	 * @param str
	 * @return
	 */
	public static List<String> stringToSubList(String str){
		List<String> list = new ArrayList<String>();
		if(str != null ) {
			String[] sa = str.split(",");
			for(int i=sa.length; i > 0; i--) {
				StringBuffer s = new StringBuffer();
				for(int j=0; j<i; j++) {
					s.append(sa[j]);
					if(j != i-1) {
						s.append(",");
					}
				}
				list.add(s.toString());
			}
		}
		return list;
	}
}