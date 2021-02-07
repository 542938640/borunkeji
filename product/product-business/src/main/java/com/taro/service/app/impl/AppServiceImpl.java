package com.taro.service.app.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.taro.constants.Constant;
import com.taro.dao.merchants.MerchantsCouponDao;
import com.taro.dao.sec.SecUserDao;
import com.taro.entity.device.DeviceExtEntity;
import com.taro.entity.device.DeviceOutletsEntity;
import com.taro.entity.merchants.MerchantsCouponEntity;
import com.taro.entity.merchants.MerchantsCouponReqEntity;
import com.taro.entity.merchants.MerchantsEntity;
import com.taro.entity.sec.SecUserEntity;
import com.taro.exception.BusinessException;
import com.taro.service.app.AppService;
import com.taro.service.device.DeviceExtService;
import com.taro.service.device.DeviceOutletsService;
import com.taro.service.finance.FinanceNoteDetailService;
import com.taro.service.merchants.MerchantsCouponReqService;
import com.taro.service.merchants.MerchantsCouponService;
import com.taro.service.merchants.MerchantsService;
import com.taro.service.sec.SecUserService;
import com.taro.sms.SmsAliyunBRXK;
import com.taro.utils.MD5Utils;
import com.taro.utils.MyStringUtil;
import com.taro.utils.PhoneFormatCheckUtils;
import com.taro.utils.UuidUtil;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private SecUserDao secUserDao;
	
	@Autowired
	private SecUserService secUserService;
	
	@Autowired
	private DeviceExtService deviceExtService;

	@Autowired
	private DeviceOutletsService deviceOutletsService;

	@Autowired
	private MerchantsService merchantsService;

	@Autowired
	private MerchantsCouponService merchantsCouponService;

	@Autowired
	private MerchantsCouponDao merchantsCouponDao;

	@Autowired
	private MerchantsCouponReqService merchantsCouponReqService;

	@Autowired
	private FinanceNoteDetailService financeNoteDetailService;
	
    @Override
    public DeviceExtEntity getDeviceInfo(String device_did) {
    	if(MyStringUtil.isBlank(device_did)) {
            throw new BusinessException("设备编码不能为空");
    	}
    	
    	Map<String, Object> queryMap = Maps.newHashMap();
    	queryMap.put("sysFlag", "1");
    	queryMap.put("device_did", device_did);
    	List<DeviceExtEntity> deviceList = deviceExtService.list(queryMap);
    	if(CollectionUtils.isEmpty(deviceList)) {
            throw new BusinessException("设备编码查询为空");
    	}
    	
    	DeviceExtEntity device = deviceList.get(0);
    	
    	// 查询网点信息
    	if(MyStringUtil.isNotBlank(device.getOutlets_pid())) {
    		DeviceOutletsEntity deviceOutlets = deviceOutletsService.get(device.getOutlets_pid());
    		if(null != deviceOutlets) {
    			device.setOutlets_name(deviceOutlets.getName());
    			device.setOutlets_address(deviceOutlets.getAddress());
    			device.setOutlets_contacts(deviceOutlets.getContacts());
    			device.setOutlets_phone(deviceOutlets.getPhone());
    			device.setOutlets_telephone(deviceOutlets.getTelephone());
    		}
    	}
    	
    	return device;
    }

	@Override
    public List<SecUserEntity> listDeviceUser(Map<String, Object> param) {
		if(null == param || !param.containsKey("device_did")) {
            throw new BusinessException("查询参数不能为空");
    	}
    	String device_did = String.valueOf(param.get("device_did"));
    	if(MyStringUtil.isBlank(device_did)) {
            throw new BusinessException("设备编码不能为空");
    	}
    	Map<String, Object> queryMap = Maps.newHashMap();
    	queryMap.put("sysFlag", "1");
    	queryMap.put("device_did", device_did);
    	List<DeviceExtEntity> deviceList = deviceExtService.list(queryMap);
    	if(CollectionUtils.isEmpty(deviceList)) {
            throw new BusinessException("设备编码查询为空");
    	}
    	
    	DeviceExtEntity device = deviceList.get(0);
    	
    	// 查询用户信息
    	if(MyStringUtil.isNotBlank(device.getTenants_pid())) {
    		queryMap.clear();
    		queryMap.put("sysFlag", "1");
    		queryMap.put("status", Constant.USERSTATUS1);
    		queryMap.put("tenants_pid", device.getTenants_pid());
    		return secUserDao.listSecUser(queryMap);
    	}
        return new ArrayList<>();
    }
	
	@Override
	public boolean getVerifyCode(HttpServletRequest request, Map<String, Object> param) {
		if(null == param || !param.containsKey("phoneNumbers")) {
            throw new BusinessException("手机号码不能为空");
    	}
		String phoneNumbers = String.valueOf(param.get("phoneNumbers"));
		if(MyStringUtil.isBlank(phoneNumbers) || !PhoneFormatCheckUtils.isPhoneLegal(phoneNumbers)) {
            throw new BusinessException("手机号码格式错误");
		}
		
		// 生成6位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        
        // 将验证码存到session中,同时存入创建时间
        param.put("verifyCode", verifyCode);
        param.put("createTime", System.currentTimeMillis());
        request.getSession().setAttribute("verifyCodeParam", param);

        // 将验证码放入模板参数
        JSONObject smsParam = new JSONObject();
        smsParam.put("code", verifyCode);
        boolean sendFlag = SmsAliyunBRXK.sendSms(SmsAliyunBRXK.MERCHANT_REGISTER_TEMPLATE, phoneNumbers, smsParam);
        
        if(sendFlag && param.containsKey("tenants_pid")) {
        	String tenants_pid = String.valueOf(param.get("tenants_pid"));
        	financeNoteDetailService.saveNum(tenants_pid, "1");// 发送验证码统计短信数量
        }
        
		return sendFlag;
	}

    @Override
    public MerchantsEntity registerMerchants(HttpServletRequest request, MerchantsEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}else if(MyStringUtil.isBlank(model.getUsername())) {
            throw new BusinessException("手机号不能为空!");
    	}else if(MyStringUtil.isBlank(model.getVercode())) {
            throw new BusinessException("短信验证码不能为空!");
    	}else if(MyStringUtil.isBlank(model.getPassword())) {
            throw new BusinessException("登录密码不能为空!");
    	}else if(MyStringUtil.isBlank(model.getPassword2())) {
            throw new BusinessException("确认密码不能为空!");
    	}else if(MyStringUtil.isBlank(model.getTenants_pid())) {
            throw new BusinessException("网点id不能为空!");
    	}
    	
    	String username = model.getUsername();
    	
    	SecUserEntity queryUser = secUserService.getByUserName(username);
    	if(null != queryUser) {
            throw new BusinessException("该手机号已经注册!");
    	}
    	
    	String password = model.getPassword();
    	if(!password.equals(model.getPassword2())) {
            throw new BusinessException("确认密码与登录密码不一致!");
    	}

    	// 验证短信验证码
		checkVerCode(request, model.getVercode(), username);
		
		String merchants_pid = UuidUtil.get32UUID();
    	
    	SecUserEntity saveUser = new SecUserEntity();
		String salt = UuidUtil.get32UUID();
		saveUser.setUsername(username);
		saveUser.setSalt(salt);
		saveUser.setPassword(MD5Utils.getSaltMD5(password, salt));
		saveUser.setStatus(Constant.USERSTATUS1);
		saveUser.setC1("0");
		saveUser.setC10(merchants_pid);
		secUserService.save(saveUser);
		
		MerchantsEntity saveModel = new MerchantsEntity();
		saveModel.setId(merchants_pid);
		saveModel.setTenants_pid(model.getTenants_pid());
		saveModel.setUser_pid(saveUser.getId());
		saveModel.setRegister_code(model.getRegister_code());
		saveModel.setStatus("0");
		merchantsService.save(saveModel);
        return saveModel;
    }
    
    @Override
    public MerchantsCouponReqEntity receiveCoupon(HttpServletRequest request, MerchantsCouponReqEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}else if(MyStringUtil.isBlank(model.getCoupon_pid())) {
            throw new BusinessException("优惠券id不能为空!");
    	}else if(MyStringUtil.isBlank(model.getReq_phone())) {
            throw new BusinessException("手机号码不能为空!");
    	}
    	
    	String coupon_pid = model.getCoupon_pid();
    	MerchantsCouponEntity coupon = merchantsCouponService.get(coupon_pid);
    	if(null == coupon) {
            throw new BusinessException("优惠券id查询为空!");
    	}else if("0".equals(coupon.getEnable()) || "0".equals(coupon.getSysFlag())) {
            throw new BusinessException("优惠卷已停用,请确认!");
    	}else if(!"1".equals(coupon.getEnable())) {
            throw new BusinessException("优惠卷已结束,请确认!");
    	}else if((coupon.getFrant_num() - coupon.getReq_num()) <= 0) {
            throw new BusinessException("优惠卷已发放完,请确认!");
    	}
    	
    	String req_phone = model.getReq_phone();
    	
    	// 短信验证码,若不为空则验证
    	if(MyStringUtil.isNotBlank(model.getVercode())) {
        	// 验证短信验证码
    		checkVerCode(request, model.getVercode(), req_phone);
    	}
    	
    	// 生成领取码(用于核销)
    	List<MerchantsCouponReqEntity> queryList = null;
    	Map<String, Object> queryMap = Maps.newHashMap();
    	queryMap.put("coupon_pid", coupon_pid);
    	queryMap.put("sysFlag", "1");
    	String req_code = "";
    	boolean flag = true;
    	while(flag) {
    		req_code = getStringRandom();
        	queryMap.put("req_code", req_code);
        	queryList = merchantsCouponReqService.list(queryMap);
        	if(CollectionUtils.isEmpty(queryList)) {
        		flag = false;
        	}
    	}
    	model.setReq_code(req_code);
    	model.setReq_num(1);
    	model.setReq_time(DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS));
    	model.setC1("0");// 未核销
    	
		merchantsCouponReqService.save(model);
		
		merchantsCouponDao.updateReqNum(coupon_pid);

        // 发送领取码给用户
        JSONObject smsParam = new JSONObject();
        smsParam.put("code", req_code);
		SmsAliyunBRXK.sendSms(SmsAliyunBRXK.MERCHANT_COUPON_TEMPLATE, req_phone, smsParam);

    	financeNoteDetailService.saveNum(coupon.getTenants_pid(), "2");// 领劵吧统计短信数量
    	
        return model;
    }
    
    // 验证短信验证码
    private void checkVerCode(HttpServletRequest request, String verCode, String phone) {
    	try {
        	Map<String, Object> verifyCodeParam = (Map) request.getSession().getAttribute("verifyCodeParam");
        	if(null == verifyCodeParam) {
                throw new BusinessException("验证码错误!");
        	}
        	String verifyCode = String.valueOf(verifyCodeParam.get("verifyCode"));
        	String phoneNumbers = String.valueOf(verifyCodeParam.get("phoneNumbers"));
        	Long createTime = Long.valueOf(String.valueOf(verifyCodeParam.get("createTime")));
        	if(!phone.equals(phoneNumbers)) {
                throw new BusinessException("手机号错误!");
        	}else if(!verCode.equals(verifyCode)) {
                throw new BusinessException("验证码错误!");
        	}else if((System.currentTimeMillis() - createTime) > 1000 * 60 * 30) {
                throw new BusinessException("验证码已过期,请重新发送!");
        	}
		} catch (Exception e) {
            throw new BusinessException("验证码错误!");
		}
    }
    
    // 生成领取码
    private String getStringRandom() {
    	String val = "";  
    	Random random = new Random();  
    	//参数length，表示生成几位随机数  
    	for(int i = 0; i < 6; i++) {  
    		String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
    		//输出字母还是数字  
    		if("char".equalsIgnoreCase(charOrNum)){ 
    			//输出是大写字母还是小写字母  
    			int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
				val += (char)(random.nextInt(26) + temp);  
			}else if("num".equalsIgnoreCase(charOrNum)) {  
				val += String.valueOf(random.nextInt(10));  
    		}
		}
        return val;
    }

    @Override
    public MerchantsCouponEntity getCoupon(String id) {
        if (MyStringUtil.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        return merchantsCouponDao.getCoupon(id);
    }
    
}
