package com.taro.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.taro.exception.BusinessException;

/**
 * @File: Utility.java
 * @Description: TODO
 * @Author: 张宇唯
 * @Date: 2016年12月23日
 * @Copyright taro(c)2010-2016
 */
public class Utility {

	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 *            参数集合
	 * @param key
	 *            参数key值
	 * @return
	 */
	public static String getParameter(Map<String, Object> parameter, String key) {
		Object value = parameter.get(key);
		return value == null ? "" : value.toString();
	}

	/**
	 * 当v为null,或者 当v为空字符串, 或则 当v为boolean值false时,抛出异常
	 * 
	 * @param v
	 * @param msg
	 */
	public static void raiseIfWrong(Object v, String msg) {
		if (v == null)
			throw new BusinessException(msg);
		if (v.getClass().equals(String.class)
				&& StringUtils.isBlank((String) v))
			throw new BusinessException(msg);
		if (v.getClass().equals(Boolean.class) && !((Boolean) v).booleanValue())
			throw new BusinessException(msg);
	}

	/**
	 * @param values
	 * @return
	 */
	public static String toIdString(Collection<?> values) {
		return toIdString(values, ",", "id", true);
	}

	/**
	 * 将集合转换成字符串 如[1,2,3] --> "1,2,3", 支持复杂对象,值默认取getId
	 * 
	 * @param values
	 * @param separator
	 * @param fieldName
	 * @param idIsString
	 * @return
	 */
	public static String toIdString(Collection<?> values, String separator,
			String fieldName, boolean idIsString) {
		if (values == null || values.isEmpty())
			return "";
		StringBuilder sBuilder = new StringBuilder();
		boolean needSeparator = false;
		for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
			Object v = iterator.next();
			String id = null;
			// 分割符
			if (!needSeparator)
				needSeparator = true;
			else
				sBuilder.append(separator);
			// 取得id
			if (v.getClass().equals(String.class)
					|| v.getClass().equals(Integer.class)
					|| v.getClass().equals(Float.class)
					|| v.getClass().equals(Double.class)) {
				id = v.toString();
			} else {
				try {
					id = v.getClass()
							.getMethod(
									"get"
											+ fieldName.substring(0, 1)
													.toUpperCase()
											+ fieldName.substring(1)).invoke(v)
							.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 组织成字符串
			if (idIsString)
				sBuilder.append("'");
			sBuilder.append(id);
			if (idIsString)
				sBuilder.append("'");
		}
		return sBuilder.toString();
	}

	/**
	 * 把一个对象转换成Double型数据 当obj为null时，返回null
	 * 
	 * @param obj
	 * @return
	 */
	public final static Double objToDouble(Object obj) {
		if (obj == null) {
			return null;
		}

		return Double.valueOf(obj.toString());
	}

	/**
	 * 把一个对象转换成Integer型数据 当obj为null时，返回null
	 * 
	 * @param obj
	 * @return
	 */
	public final static Integer objToInteger(Object obj) {
		if (obj == null) {
			return null;
		}

		return Integer.valueOf(obj.toString());
	}

	/**
	 * 把一个对象转换成字符串 当obj为null时，返回null
	 * 
	 * @param obj
	 * @return
	 */
	public final static String objToString(Object obj) {
		if (obj == null) {
			return null;
		}

		return obj.toString();
	}

	/**
	 * 转换指定格式的查询条件为Hashtable<key:条件名称，value:条件值>
	 * queryStr的格式为"条件名称1:条件值1,条件名称2:条件值2,条件名称3:条件值3,..."
	 * 
	 * @param queryStr
	 * @return
	 */
	public final static Hashtable<String, String> turnQueryStr(String queryStr) {
		Hashtable<String, String> hs = new Hashtable<String, String>();
		String[] queryList = queryStr.split(",");
		for (int i = 0; i < queryList.length; i++) {
			String keyValue = queryList[i];
			String[] keyValueList = keyValue.split(":");
			if (keyValueList.length == 2) {
				String key = keyValueList[0].trim();
				String value = keyValueList[1];
				hs.put(key, value);
			}
		}

		return hs;
	}

	public final static Map<String, String> turnParameterStr(String parameter) {
		Map<String, String> hs = new HashMap<String, String>();
		String[] queryList = parameter.split("&");
		for (int i = 0; i < queryList.length; i++) {
			String keyValue = queryList[i];
			String[] keyValueList = keyValue.split("=");
			if (keyValueList.length == 2) {
				String key = keyValueList[0].trim();
				String value = keyValueList[1];
				hs.put(key, value);
			}
		}

		return hs;
	}

	/**
	 * 获取指定月份的前一个月
	 * 
	 * @param curYear
	 *            年度
	 * @param curMonth
	 *            月份
	 * @return 返回格式：year-month，例如，curYear=2009，curMonth=01，则返回2008-12
	 */
	public final static String getPriorMonth(String curYear, String curMonth) {
		int iCurMonth = Integer.parseInt(curMonth);
		int iCurYear = Integer.parseInt(curYear);

		int iPirorMonth = iCurMonth - 1;
		if (iCurMonth == 1) {
			iPirorMonth = 12;
			iCurYear--;
		}

		String pirorMonth = String.valueOf(iPirorMonth);
		if (iPirorMonth < 10) {
			pirorMonth = "0" + pirorMonth;
		}

		return String.valueOf(iCurYear) + "-" + pirorMonth;
	}

	/**
	 * 获取指定月份的前一个月
	 * 
	 * @param curDay
	 *            当前月的任意一天
	 * @return 返回格式：year-month，例如，curDay=20090102，则返回2008-12
	 */
	public final static String getPriorMonth(String curDay) {
		String curYear = curDay.substring(0, 4);
		String curMonth = curDay.substring(4, 6);
		return getPriorMonth(curYear, curMonth);
	}

	/**
	 * 获取与某日期间隔天数的日期
	 * 
	 * @param curDay
	 *            日期
	 * @param intervalDay
	 *            间隔天数（如，为1表示是这个日期的后一天，为-2表示是这个日期的前二天）
	 * @return
	 */
	public final static String getIntervalDay(String curDay, int intervalDay) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date tmpDate = null;
		try {
			tmpDate = df.parse(curDay);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(tmpDate);
		rightNow.add(Calendar.DAY_OF_MONTH, intervalDay);
		return df.format(rightNow.getTime());
	}

	/**
	 * 对Double类型数字只保留指定位数的小数
	 * 
	 * @param number
	 *            值
	 * @param binary
	 *            小数位数
	 * @return
	 */
	public final static Double round(Double number, Integer binary) {
		BigDecimal b = new BigDecimal(number);
		number = b.setScale(binary, BigDecimal.ROUND_HALF_UP).doubleValue();
		return number;
	}

	/**
	 * 对Float类型数字只保留指定位数的小数
	 * 
	 * @param number
	 *            值
	 * @param binary
	 *            小数位数
	 * @return
	 */
	public final static Float round(Float number, Integer binary) {
		BigDecimal b = new BigDecimal(number);
		number = b.setScale(binary, BigDecimal.ROUND_HALF_UP).floatValue();
		return number;
	}

	/**
	 * isNull:(判断字符串或对象是否为空). <br/>
	 * 
	 * @author gavin
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			if (obj instanceof String
					&& ("".equals(((String) obj).trim()) || "null"
							.equals(((String) obj).trim()))) {
				return true;
			}
			return false;
		}
	}

	/**
	 * 
	* @Title: getBrowserName  
	* @Description: TODO(获取浏览器名称)  
	* @param @param agent
	* @param @return    参数  
	* @return String    返回类型  
	* @author yushengke@taro.com.cn
	* @throws
	 */
	public static String getBrowserName(String agent) {
		if (agent.indexOf("msie 7") > 0) {
			return "IE7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "IE8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "IE9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "IE10";
		} else if (agent.indexOf("msie") > 0) {
			return "IE";
		} else if (agent.indexOf("opera") > 0) {
			return "Opera";
		} else if (agent.indexOf("chrome") > 0) {
			return "Chrome";
		} else if (agent.indexOf("mozilla") > 0) {
			return "Chrome";
		} else if (agent.indexOf("firefox") > 0) {
			return "Firefox";
		} else if (agent.indexOf("webkit") > 0) {
			return "Webkit";
		} else if (agent.indexOf("safari") > 0) {
			return "Safari";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "IE11";
		} else {
			return "Others";
		}
	}
}
