package com.taro.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

/**  
 * @Package: com.taro.tdevice.utils  
 * @File: ClassRefUtil.java   
 * @Description: TODO 
 * @Author: tanquanlong  
 * @Date: 2017年1月19日  
 * @Copyright taro(c)2010-2017
 */

/*
 * 通过Java反射机制，动态给对象属性赋值，并获取属性值
 */
public class ClassRefUtil {
 
     
    /** 
     * 取Bean的属性和值对应关系的MAP 
     * @param bean 
     * @return Map 
     */ 
    public static Map<String, String> getFieldValueMap(Object bean) {  
        Class<?> cls = bean.getClass();  
        Map<String, String> valueMap = new HashMap<String, String>();  
        // 取出bean里的所有方法  
        Method[] methods = cls.getDeclaredMethods();  
        Field[] fields = cls.getDeclaredFields();  
   
        for (Field field : fields) {  
            try {  
                String fieldType = field.getType().getSimpleName();  
                String fieldGetName = parGetName(field.getName());  
                if (!checkGetMet(methods, fieldGetName)) {  
                    continue;  
                }  
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});  
                Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});  
                String result = null;  
                if ("Date".equals(fieldType)) {  
                    result = fmtDate((Date) fieldVal);  
                } else {  
                    if (null != fieldVal) {  
                        result = String.valueOf(fieldVal);  
                    }  
                }  
                valueMap.put(field.getName(), result);  
            } catch (Exception e) {  
                continue;  
            }  
        }  
        return valueMap;  
   
    }  
   
    /**
     * getField:(递归得到属性值，该属性可以在本类里，也可以在父类里，也可以在父类父类....). <br/>
     *
     * @author gavin
     * @param object
     * @param fieldName
     * @param isParent
     * @return
     */
    public static Object getField(Class c ,Object object, String fieldName){
    	Object o = null;
    	try {
			Field field = c.getDeclaredField(fieldName);
			field.setAccessible(true);//修改访问权限
			try {
				o = field.get(object);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    	} catch (NoSuchFieldException e) {
			//如果找不到属性，则到父类去找，所以这里的异常必须try catch，不能抛出
    		getField(c.getSuperclass(), object, fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	return o;
    }
    
    /** 
     * set属性的值到Bean 
     * @param bean 
     * @param valMap 
     */ 
    public static void setFieldValue(Object bean, Map<String, String> valMap) {  
        Class<?> cls = bean.getClass();  
        // 取出bean里的所有方法  
        Method[] cmethods = cls.getDeclaredMethods();  
        Field[] cfields = cls.getDeclaredFields();  
   
        Class<?> superClass = cls.getSuperclass();
        Field[] sfields = superClass.getDeclaredFields();
        Method[] smethods = cls.getDeclaredMethods();
        Field[] fields =  (Field[]) ArrayUtils.addAll(cfields, sfields);
        Method[] methods =  (Method[]) ArrayUtils.addAll(cmethods, smethods);
        for (Field field : fields) {  
            try {  
                String fieldSetName = parSetName(field.getName());  
                if (!checkSetMet(methods, fieldSetName)) {  
                    continue;  
                }  
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());  
                String value = valMap.get(field.getName());  
                if (null != value && !"".equals(value)) {  
                    String fieldType = field.getType().getSimpleName();  
                    if ("String".equals(fieldType)) {  
                        fieldSetMet.invoke(bean, value);  
                    } else if ("Date".equals(fieldType)) {  
                        Date temp = parseDate(value);  
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {  
                        Integer intval = Integer.parseInt(value);  
                        fieldSetMet.invoke(bean, intval);  
                    } else if ("Long".equalsIgnoreCase(fieldType)) {  
                        Long temp = Long.parseLong(value);  
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Double".equalsIgnoreCase(fieldType)) {  
                        Double temp = Double.parseDouble(value);  
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {  
                        Boolean temp = Boolean.parseBoolean(value);  
                        fieldSetMet.invoke(bean, temp);  
                    }else if("BigDecimal".equalsIgnoreCase(fieldType)){
                    	fieldSetMet.invoke(bean, new BigDecimal(value)); 
                    }else {  
                        System.out.println("not supper type" + fieldType);  
                    }  
                }  
            } catch (Exception e) {  
                continue;  
            }  
        }  
    }  
   
    /** 
     * 格式化string为Date 
     * @param datestr 
     * @return date 
     */ 
    private static Date parseDate(String datestr) {  
        if (null == datestr || "".equals(datestr)) {  
            return null;  
        }  
        try {  
            String fmtstr = null;  
            if (datestr.indexOf(':') > 0) {  
                fmtstr = "yyyy-MM-dd HH:mm:ss";  
            } else {  
                fmtstr = "yyyy-MM-dd";  
            }  
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);  
            return sdf.parse(datestr);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
    /** 
     * 日期转化为String 
     * @param date 
     * @return date string 
     */ 
    private static String fmtDate(Date date) {  
        if (null == date) {  
            return null;  
        }  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);  
            return sdf.format(date);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
   
    /** 
     * 判断是否存在某属性的 set方法 
     * @param methods 
     * @param fieldSetMet 
     * @return boolean 
     */ 
    private static boolean checkSetMet(Method[] methods, String fieldSetMet) {  
        for (Method met : methods) {  
            if (fieldSetMet.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  
    /** 
     * 判断是否存在某属性的 get方法 
     * @param methods 
     * @param fieldGetMet 
     * @return boolean 
     */ 
    private static boolean checkGetMet(Method[] methods, String fieldGetMet) {  
        for (Method met : methods) {  
            if (fieldGetMet.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  
   
    /** 
     * 拼接某属性的 get方法 
     * @param fieldName 
     * @return String 
     */ 
    private static String parGetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return "get" + fieldName.substring(0, 1).toUpperCase()  
                + fieldName.substring(1);  
    }  
    /** 
     * 拼接在某属性的 set方法 
     * @param fieldName 
     * @return String 
     */ 
    private static String parSetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return "set" + fieldName.substring(0, 1).toUpperCase()  
                + fieldName.substring(1);  
    } 
 
}