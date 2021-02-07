package com.taro.service.app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.taro.entity.device.DeviceExtEntity;
import com.taro.entity.merchants.MerchantsCouponEntity;
import com.taro.entity.merchants.MerchantsCouponReqEntity;
import com.taro.entity.merchants.MerchantsEntity;
import com.taro.entity.sec.SecUserEntity;

public interface AppService {

	DeviceExtEntity getDeviceInfo(String device_did);
	
	List<SecUserEntity> listDeviceUser(Map<String, Object> param);

	boolean getVerifyCode(HttpServletRequest request, Map<String, Object> param);

	MerchantsEntity registerMerchants(HttpServletRequest request, MerchantsEntity model);
	
	MerchantsCouponReqEntity receiveCoupon(HttpServletRequest request, MerchantsCouponReqEntity model);

	MerchantsCouponEntity getCoupon(String id);
	
}
