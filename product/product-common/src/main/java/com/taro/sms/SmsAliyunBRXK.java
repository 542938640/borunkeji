package com.taro.sms;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.taro.exception.BusinessException;
import com.taro.utils.MyStringUtil;

// 博润信科
public class SmsAliyunBRXK {
	
	private static final Logger LOG = Logger.getLogger(SmsAliyunBRXK.class);

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static final String ACCESS_KEY_ID = "LTAI4Fi387HuegdrVT2tnpyq";
    private static final String ACCESS_KEY_SECRET = "R5h9KV7oYCjNd1naP8WZNnwAK7hjch";
    
    // 短信签名-可在短信控制台中找到
    private static final String SIGH_NAME = "博润信科";
    
    // 验证码模板
    public static final String MERCHANT_REGISTER_TEMPLATE = "SMS_209170812";

    // 领取优惠券通知模板
    public static final String MERCHANT_COUPON_TEMPLATE = "SMS_209170807";
    
    public static boolean sendSms(String templateCode, String phoneNumbers, JSONObject param) {
    	if(MyStringUtil.isBlank(templateCode)) {
			throw new BusinessException("短信模板不能为空!");
    	}
    	if(MyStringUtil.isBlank(phoneNumbers)) {
			throw new BusinessException("短信发送手机号码不能为空!");
    	}
    	if(null == param) {
    		param = new JSONObject();
    	}
    	String paramStr = param.toJSONString();
    	boolean success = false;
    	
		try {
	    	//发短信
			SendSmsResponse response = SmsAliyun.sendSms(ACCESS_KEY_ID, ACCESS_KEY_SECRET, phoneNumbers, 
					SIGH_NAME, templateCode, paramStr);
			String returnCode = response.getCode();
			if(null != returnCode && "OK".equals(returnCode)) {
				success = true;
			}

	        LOG.info("阿里云短信平台--->发送短信  phoneNumbers: " + phoneNumbers + ", templateCode: " + templateCode + ", param: " + paramStr
	        		+ ", Code: " + response.getCode() + ", Message: " + response.getMessage()
	        		+ ", RequestId: " + response.getRequestId() + ", BizId: " + response.getBizId());
	        
		} catch (ClientException e) {
			e.printStackTrace();
			success = false;
		}
    	
    	return success;
    }
    
}
