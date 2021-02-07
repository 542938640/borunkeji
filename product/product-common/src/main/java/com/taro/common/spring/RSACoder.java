package com.taro.common.spring;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: RSACoder <br/>
 * Function: RSA加密  <br/>
 * date: 2017-10-21 下午5:39:27 <br/>
 * @author gavin
 * @version
 */
public class RSACoder {
	private static final String KEY_ALGORITHM = "RSA";
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static Map<String, Key> keyMap = new HashMap<String, Key>(2);
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
 
    /**
     * decryptBASE64:(base64解密). <br/>
     * @author gavin
     * @param key
     * @return
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }
 
    /**
     * encryptBASE64:(base64加密). <br/>
     * @author gavin
     * @param bytes
     * @return
     */
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
 
    /**
     * 用私钥对信息生成数字签名
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }
 
    /**
     * 校验数字签名
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(String data, String publicKey, String sign) throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data.getBytes());
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }
 
    /**
     * decryptByPrivateKey:(用私钥解密). <br/>
     * @author gavin
     * @param data  加密后的数据
     * @param key	私钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String prikey) throws Exception{
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(prikey);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
 
    /**
     * decryptByPublicKey:(用公钥解密). <br/>
     * @author gavin
     * @param data	加密后的数据
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String pubkey) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(pubkey);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
 
    /**
     * encryptByPublicKey:(用公钥加密). <br/>
     * @author gavin
     * @param data	要加密的数据
     * @param key	公钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String pubkey) throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(pubkey);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }
 
    /**
     * encryptByPrivateKey:(用私钥加密). <br/>
     * @author gavin
     * @param data	要加密的数据
     * @param key	私钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(String data, String prikey) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(prikey);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data.getBytes());
    }
 
    /**
     * 取得私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey() throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        if(key != null){
        	return encryptBASE64(key.getEncoded());
        } else {
        	System.out.println("请先初始化秘钥  调用initKey");
        	return null;
        }
    }
 
    /**
     * 取得公钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey() throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        if(key != null){
        	return encryptBASE64(key.getEncoded());
        } else {
        	System.out.println("请先初始化秘钥  调用initKey");
        	return null;
        }
    }
 
    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static void initKey(String seed) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom secrand = new SecureRandom();
		secrand.setSeed(seed.getBytes()); // 初始化随机产生器
        keyPairGen.initialize(1024,secrand);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
    }
    
    /*public static void main(String[] args) throws Exception {
    	System.out.println(new String(RSACoder.decryptBASE64("5o6I5p2D5aSx6LSl77yM5bqU55SoSVDkuI7mjojmnYPnmoRJUOS4jeS4gOiHtA=="),"UTF-8"));

//		RSACoder.initKey("Gavin_123!#@$%&^*()");
		RSACoder.initKey("taro_123!#@$%&^*()");

		System.out.println(getPrivateKey());
		System.out.println(getPublicKey());

		//私钥加密
		byte[] inputData = encryptByPrivateKey("123456",getPrivateKey());
		System.out.println(new String(inputData));
		//公钥解密
		byte[] data = decryptByPublicKey(inputData, getPublicKey());
		System.out.println(new String(data));




		//公钥加密
		byte[] inputData2 = encryptByPublicKey("123456",getPublicKey());
		System.out.println(new String(inputData2,"UTF-8"));
		//私钥解密
		byte[] data2 = decryptByPrivateKey(RSACoder.decryptBASE64("c19eW4v9DCrOp8ZI20hUM1YPyQ/4Gs977Nj9+GC5UN+2euVDSXFsyZFMiVdxl0mZlOp4TKjNGRn1j2PMGN8506f3AdPXwqeGBC3Dl3tFLFv1mUVb1MqY9eM7vBh4Vh+wkRGTTVfcKIjI1o6E6gc29SKcPW7YgCTVa4mLDQNPODE="), getPrivateKey());
		System.out.println(new String(data2));
	}*/
}