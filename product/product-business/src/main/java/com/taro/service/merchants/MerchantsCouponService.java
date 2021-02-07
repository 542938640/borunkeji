package com.taro.service.merchants;import java.util.List;import java.util.Map;import com.taro.common.Page;import com.taro.entity.merchants.MerchantsCheckEntity;import com.taro.entity.merchants.MerchantsCouponEntity;import com.taro.entity.merchants.MerchantsCouponReqEntity;import com.taro.service.IService;/** *<p>Title:MerchantsCouponService.java</p> *<p>Description:商户优惠券 Service</p> *@author 张宇唯 *@version 1.0 *@Automatically generate by Coder in 2020-12-17 19:21:30 */public interface MerchantsCouponService extends IService<MerchantsCouponEntity> {    List<MerchantsCouponEntity> listCouponByTrade(Map<String, Object> parameter);    Page<MerchantsCouponEntity> listCoupon(int pageNum, int pageSize, Map<String, Object> parameter);		MerchantsCouponEntity saveMerchantsCoupon(MerchantsCouponEntity model);	MerchantsCouponEntity startAutoWorkFlow(MerchantsCouponEntity model);		MerchantsCouponEntity checkAutoWorkFlow(MerchantsCheckEntity model);	MerchantsCouponEntity saveFrantNum(MerchantsCouponEntity model);		MerchantsCouponEntity saveStatus(MerchantsCouponEntity model);		MerchantsCouponEntity saveEnable(MerchantsCouponEntity model);		MerchantsCouponEntity saveIsShare(MerchantsCouponEntity model);		MerchantsCouponEntity actAutoWorkFlow();		MerchantsCouponReqEntity saveLogout(MerchantsCouponReqEntity model);    Page<MerchantsCouponEntity> listLocalCoupon(int pageNum, int pageSize, Map<String, Object> parameter);    Page<MerchantsCouponEntity> listShareCoupon(int pageNum, int pageSize, Map<String, Object> parameter);		MerchantsCouponEntity saveShare(MerchantsCouponEntity model);	}