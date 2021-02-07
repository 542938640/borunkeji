package com.taro.common.spring;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;

/**
 * ClassName: LicenseSystemCache <br/>
 * Function: 校验工具类. <br/>
 * date: 2017-10-21 下午12:33:40 <br/>
 * @author gavin
 * @version
 */
public class Init implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Map<String, String> licensePropertiesMap = null;

	public static void verify() throws Exception {
		//Init license = new Init();
		//license.licenseInit();
	}
	
	/**
	 * <b>Summary: </b> licenseInit(授权验证)
	 * @throws Exception
	 */
	private void licenseInit() throws Exception {
		String licenseInfo = "";
		try {
			String path = this.getClass().getResource("/").getPath() + File.separator;	//获取项目跟路径
			byte[] info = RSACoder.decryptByPublicKey(FileUtils.readFileToByteArray(new File(path + "license.lic")), FileUtils.readFileToString(new File(path + "publicCerts.store")));
			licenseInfo = new String(info) ;
		} catch (Exception e) {
			throw new Exception(RSACoder.encryptBASE64("read license error".getBytes()));
		}
		Properties prop = new Properties();
		InputStream in = new ByteArrayInputStream(licenseInfo.getBytes());
		prop.load(in);
		Set<String> setKey = prop.stringPropertyNames();
		if (setKey != null && setKey.size() > 0) {
			if(licensePropertiesMap == null){
				licensePropertiesMap = new HashMap<String, String>();
			}
			for (String key : setKey) {
				if(licensePropertiesMap.get(key) != null && licensePropertiesMap.get(key).equals(prop.getProperty(key))){
					//如果值存在，并且值相等，不做处理
				} else {
					licensePropertiesMap.put(key, prop.getProperty(key));
				}
			}
		}
		this.licenseVerify();
	}

	/**
	 * 
	 * <b>Summary: </b> licenseValdata(验证授权文件)
	 * @return
	 * @throws UnknownHostException
	 */
	private boolean licenseVerify() throws Exception {
		String lictype = licensePropertiesMap.get("lictype"); // 授权类型
		String generatedate = licensePropertiesMap.get("generatedate"); // 授权日期
		String overdue = licensePropertiesMap.get("overdue"); // 授权到期日期
		String mac = licensePropertiesMap.get("mac").toUpperCase().trim(); // 服务器MAC地址
		String ip = licensePropertiesMap.get("ip").toUpperCase().trim(); // 服务器IP
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(new Date());
		
		if (now.compareTo(generatedate) < 0){	//如果当前日期小于license产生日期
			throw new Exception(RSACoder.encryptBASE64("date error".getBytes()));
		}
		if (now.compareTo(overdue) >= 0){
			throw new Exception(RSACoder.encryptBASE64("overdue".getBytes()));
		}
		
		if("1".equals(lictype)){//试用、开发不需要验证ip和mac，只有正式版才验证
			if ((mac == null && !"".equals(mac)) && (ip == null && !"".equals(ip))) {
				throw new Exception(RSACoder.encryptBASE64("file error".getBytes()));
			}
			String locIp = this.getAllIp().toUpperCase();
			String locMac = this.getAllMacAddress().toUpperCase();
			if (locIp.indexOf(ip.toUpperCase()) == -1) {
				throw new Exception(RSACoder.encryptBASE64("ip error".getBytes()));
			}
			if (locMac.indexOf(mac) == -1) {
				throw new Exception(RSACoder.encryptBASE64("mac error".getBytes()));
			}
		}
		return true;
	}

	/**
	 * <b>Summary: </b> getMacAddress(获取所有本机IP)
	 * @param host
	 * @return
	 */
	private String getAllIp() throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			Enumeration<NetworkInterface> macList = NetworkInterface.getNetworkInterfaces();
			while (macList.hasMoreElements()) {
				NetworkInterface face = macList.nextElement();
				Enumeration<InetAddress> inetAddresses = face.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					if (inetAddress != null) { // IPV4
						sb.append(inetAddress.getHostAddress() + ";");
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(RSACoder.encryptBASE64("get ip error".getBytes()));
		}
		return sb.toString();
	}

	/**
	 * 
	 * <b>Summary: </b> getMacAddress(获取所有本机MAC地址)
	 * 
	 * @param host
	 * @return
	 */
	private String getAllMacAddress() throws Exception {
		String mac = "";
		StringBuffer win = new StringBuffer();
		StringBuffer linux = new StringBuffer();
		try {
			Enumeration<NetworkInterface> macList = NetworkInterface.getNetworkInterfaces();
			while (macList.hasMoreElements()) {
				NetworkInterface face = macList.nextElement();
				byte[] macs = face.getHardwareAddress();
				if (macs != null) {
					for (int i = 0; i < macs.length; i++) {
						mac = Integer.toHexString(macs[i] & 0xFF);
						if (mac.length() == 1) {
							mac = '0' + mac;
						}
						win.append(mac + "-");
						linux.append(mac + ":");
					}
					win.append(";");
					linux.append(";");
				}
			}
		} catch (Exception e) {
			throw new Exception(RSACoder.encryptBASE64("get mac error".getBytes()));
		}
		return win.toString() + linux.toString();
	}

}