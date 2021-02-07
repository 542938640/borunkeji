package com.taro.controller.app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSet;
import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.constants.Constant;
import com.taro.entity.device.DeviceExtEntity;
import com.taro.entity.merchants.MerchantsCouponEntity;
import com.taro.entity.merchants.MerchantsCouponReqEntity;
import com.taro.entity.merchants.MerchantsEntity;
import com.taro.entity.sec.SecUserEntity;
import com.taro.service.app.AppService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/app")
public class AppController {
	
	@Autowired
	private AppService appService;
	
	@ApiOperation(value = "根据设备编码获取本机信息", notes = "根据设备编码获取本机信息")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "设备编码", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/device/getDeviceInfo/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getDeviceInfo(@PathVariable("id") String id) {
		DeviceExtEntity model = appService.getDeviceInfo(id);
		return new DataSetObject<DeviceExtEntity>(model).toJson();
	}

	@ApiOperation(value = "根据设备编码获取用户信息", notes = "根据设备编码获取用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "设备编码", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/device/listUser", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listDeviceUser(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		List<SecUserEntity> result = appService.listDeviceUser(param);
		return new DataSet<SecUserEntity>(result).toJson();
	}

	@ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
	@RequestMapping(value = "/getVerifyCode", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getVerifyCode(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		boolean success = appService.getVerifyCode(request, param);
		return new Message(success ? Constant.STATUS_OK : Constant.STATUS_ERROR_GENERAL, success ? "发送成功!" : "发送失败!").toJson();
	}
	
	@ApiOperation(value = "注册商户", notes = "注册商户")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "MerchantsEntity", paramType = "query")
	})
	@RequestMapping(value = "/merchants/register",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String registerMerchants(HttpServletRequest request, @RequestBody MerchantsEntity model) {
		model = appService.registerMerchants(request, model);
		return new DataSetObject<MerchantsEntity>(model).toJson();
	}

	@ApiOperation(value = "领取优惠券", notes = "领取优惠券")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "MerchantsCouponReqEntity", paramType = "query")
	})
	@RequestMapping(value = "/merchants/receiveCoupon",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String receiveCoupon(HttpServletRequest request, @RequestBody MerchantsCouponReqEntity model) {
		model = appService.receiveCoupon(request, model);
		return new DataSetObject<MerchantsCouponReqEntity>(model).toJson();
	}

	@ApiOperation(value = "根据优惠券id获取优惠券详情", notes = "根据优惠券id获取优惠券详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/merchants/getCoupon/{id}",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String getCoupon(@PathVariable("id") String id) {
		MerchantsCouponEntity model = appService.getCoupon(id);
		return new DataSetObject<MerchantsCouponEntity>(model).toJson();
	}
}
