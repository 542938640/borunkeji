package com.taro.utils;


	import java.security.MessageDigest;  
	import java.security.NoSuchAlgorithmException;  
	  
	public class CreateMD5 {  
	  
	    //静态方法，便于作为工具类  
	    public static String getMd5(String plainText) {  
	        try {  
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            md.update(plainText.getBytes());  
	            byte b[] = md.digest();  
	  
	            int i;  
	  
	            StringBuffer buf = new StringBuffer("");  
	            for (int offset = 0; offset < b.length; offset++) {  
	                i = b[offset];  
	                if (i < 0)  
	                    i += 256;  
	                if (i < 16)  
	                    buf.append("0");  
	                buf.append(Integer.toHexString(i));  
	            }  
	            //32位加密  
	            return buf.toString();  
	            // 16位的加密  
	            //return buf.toString().substring(8, 24);  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	  
	    }


		/**
		 * md5加密
		 *
		 * @param sourceString
		 *            需要加密的字符串
		 * @return 加密后字符串
		 */
		public static String MD5Encode(String sourceString) {
			String resultString = null;
			try {
				resultString = new String(sourceString);
				MessageDigest md = MessageDigest.getInstance("MD5");
				resultString = byte2hexString(md.digest(resultString.getBytes()));
			} catch (Exception ex) {
			}
			return resultString;
		}

		private static final String byte2hexString(byte[] bytes) {
			StringBuffer bf = new StringBuffer(bytes.length * 2);
			for (int i = 0; i < bytes.length; i++) {
				if ((bytes[i] & 0xff) < 0x10) {
					bf.append("0");
				}
				bf.append(Long.toString(bytes[i] & 0xff, 16));
			}
			return bf.toString();
		}
	      

	
}
