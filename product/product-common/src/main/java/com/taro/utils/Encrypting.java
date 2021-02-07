package com.taro.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.taro.common.spring.RSACoder;

/**
 * ClassName: Encrypting <br/>
 * Function: 系统加密解密类. <br/>
 * date: 2017年11月24日 上午9:00:10 <br/>
 * @author gavin
 * @version
 */
public class Encrypting {
	//公钥，和前台一致
	public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQdPn6+Dy0m7fpsvqF/79Uhmo2HneV1Ee40Bx2rk8FtocEz6YxbSr4I4k2WKEr+7Zker9jYgjJMISF3lmH5wnGealTbp1ADSTzto8dLhMYogxfhnJSbk5TjYPFtNDOE7JMMpaW9ZhOkknozfUgZ+s9mVsecUosN31dUimN3D+woQIDAQAB";
	public static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANB0+fr4PLSbt+my+oX/v1SGajYed5XUR7jQHHauTwW2hwTPpjFtKvgjiTZYoSv7tmR6v2NiCMkwhIXeWYfnCcZ5qVNunUANJPO2jx0uExiiDF+GclJuTlONg8W00M4Tskwylpb1mE6SSejN9SBn6z2ZWx5xSiw3fV1SKY3cP7ChAgMBAAECgYB0mZniacv68kczL2u2XFF3RCBQ2UXmxvC/TZk+QU3uUiS/6i554yhv1+C+R2EPqyJgndBEFOf5MWxPPQozXGY4XgZd5mHOotF7k7Ulj0dT3V8t5iGJ5ZfmCP8vzJBLKAIOl3V+8+GRGeE34AyvAEImDC/opQ5PF2Nn18oepXPkYQJBAOtvLezGR+CnncrbWrWYaLX/cAg746XG0kibDIUjJWJXmEyhNYKm9afamoyZCBOa5+S+8xadUtmfRyY9dIa1HcUCQQDiqoZnkejnT35OBj1SIkEozKaauRty24hbrjz+dU3n1LMiK1wo7ac34RUCi+kWKrg9ap9wzl5j0GBem8k5B/EtAkA5v2SJXj1FKnf2yQUf48lTUttc+aul/vWXX4ibo3pI++lcanJgoKlCycj80ZB8lpNotTmVA3PN81aVV5+cZxJtAkA+KU/k1XIPBZD46uMkRDqkasOCCSPK8wPJSNZ3Xko4Ou/JfiSvIMXMIFE3upD4wEx4o2YIdMAUFZBoQccEefYdAkBGWaKL1KcaX5uZ3KAze5YnKQ9Xc4tYnE3FHEf76ssnoWA9OuUdf5cexoczBht5cKleERqDcU0Op39sLBWrXRvT";
	
	/**
	 * encryptSingle:(字符串RSA公钥加密). <br/>
	 * @author gavin
	 * @param value
	 * @return
	 */
    public static String encryptSingle(String value) {
    	String enStr = "";
    	if(value != null && !"".equals(value)) {
    		try {
    			enStr = RSACoder.encryptBASE64(RSACoder.encryptByPublicKey(value, publicKey));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return enStr;
    }
 
    /**
     * encryptMap:(map对象RSA公钥加密). <br/>
     * @author gavin
     * @param map
     * @return
     */
    public static String encryptMap(Map<String,String> map) {
    	String enStr = "";
    	if(map != null && map.size() > 0) {
    		StringBuffer str = new StringBuffer();
    		Map<String,String> sortMap = new TreeMap<String,String>(map);	//构建成TreeMap，来排序map键，约束好按key字母字典顺序排序
    		for(String key : sortMap.keySet()) {
    			String value = sortMap.get(key);
    			str.append(key + ":" + value + ";");	//约束好，各字段中间以分号分隔
    		}
    		if(str.toString().endsWith(";")) {
    			String bulidStr = str.substring(0,str.toString().length() - 1);
    			try {
					enStr = RSACoder.encryptBASE64(RSACoder.encryptByPublicKey(bulidStr, publicKey));
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
    	return enStr;
    }
    
    /**
     * decryptSingle:(RSA私钥解密字符串). <br/>
     * @author gavin
     * @param encryptedStr	由RSA加密后，再经 base64加密后的字符串
     * @return
     */
    public static String decryptSingle(String encryptedStr) {
    	String str = "";
    	if(encryptedStr != null && !"".equals(encryptedStr)) {
    		try {
    			str = new String(RSACoder.decryptByPrivateKey(RSACoder.decryptBASE64(encryptedStr), privateKey));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return str;
    }
    
    /**
     * decryptMap:(RSA私钥解密字符串，并且对比map是否一致). <br/>
     * @author gavin
     * @param map			接收参数
     * @param encryptedStr	由RSA加密后，再经 base64加密后的字符串
     * @return
     */
    public static boolean decryptMap(Map<String,String> map,String encryptedStr) {
    	boolean flag = false;
    	if(encryptedStr == null || "".equals(encryptedStr)) {
    		encryptedStr = map.get("_sign_");	//如果未传加密字符串，则取map中约束好的字段  _sign_
    	}
    	if(encryptedStr != null && !"".equals(encryptedStr)) {
    		try {
    			if(map != null && map.size() > 0) {
    				if(map.containsKey("_sign_")) {	//如果map中包含加密字符串，则从map中删除字段  _sign_
    					map.remove("_sign_");
    				}
    	    		StringBuffer str = new StringBuffer();
    	    		String bulidStr = "";	//map构建的字符串（按key的字典顺序排列）
    	    		Map<String,String> sortMap = new TreeMap<String,String>(map);	//构建成TreeMap，来排序map键，约束好按key字母字典顺序排序
    	    		for(String key : sortMap.keySet()) {
    	    			String value = sortMap.get(key);
    	    			str.append(key + ":" + value + ";");	//约束好，各字段中间以分号分隔
    	    		}
    	    		if(str.toString().endsWith(";")) {
    	    			bulidStr = str.substring(0,str.toString().length() - 1);
    	    		}
    	    		
    	    		String decryptStr = new String(RSACoder.decryptByPrivateKey(RSACoder.decryptBASE64(encryptedStr), privateKey));
        			if(str != null && decryptStr.equals(bulidStr)) {	//如果用私钥解密出来的字符串和传入的map字符串相等，则返回true
        				flag = true;
        			}
    			}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return flag;
    }
    
    /**
     * decryptMap:(RSA私钥解密字符串，返回map对象). <br/>
     * @author gavin
     * @param map			接收参数
     * @param encryptedStr	由RSA加密后，再经 base64加密后的字符串
     * @return
     */
    public static Map<String,String> decryptMap(String encryptedStr) {
    	Map<String,String> map = null;
    	if(encryptedStr != null && !"".equals(encryptedStr)) {
    		try {
    			//私钥解密出来的字符串
	    		String decryptStr = new String(RSACoder.decryptByPrivateKey(RSACoder.decryptBASE64(encryptedStr), privateKey));
    			if(decryptStr != null) {
    				map = new HashMap<String,String>();
    				String[] mapArray = decryptStr.split(";");	//解密出来的字符串格式是：    aaa:xxx;bbbb:yyyy
    				if(mapArray.length > 0) {
    					for(String s : mapArray) {
    						String[] keyValue = s.split(":");
    						if(keyValue != null) {
    							if(keyValue.length == 2) {
        							map.put(keyValue[0], keyValue[1]);
        						} else if (keyValue.length == 1){	//这种是参数为空的时候，只能解析出一个
        							map.put(keyValue[0], "");
        						}
    						}
    					}
    				}
    			}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return map;
    }

}