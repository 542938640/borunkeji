package com.taro.service.market;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.taro.entity.market.MarketGiftEntity;
import com.taro.entity.market.MarketLuckDrawEntity;
import com.taro.entity.market.MarketPayActEntity;
import com.taro.entity.market.OrderExtEntity;

public interface MarketAppService {

    List<MarketGiftEntity> listGift(Map<String, Object> param);

    OrderExtEntity saveGift(OrderExtEntity model, MultipartFile[] files);

	MarketLuckDrawEntity getLuckdrawRunByDevice(String device_did);
	
    OrderExtEntity saveLuckdraw(OrderExtEntity model, MultipartFile[] files);

	MarketPayActEntity getPayactRunByDevice(String type, String device_did);
	
    OrderExtEntity savePayactSign(OrderExtEntity model, MultipartFile[] files);

    OrderExtEntity savePayactCallback(OrderExtEntity model);
    
    List<OrderExtEntity> listAppHomeNum(Map<String, Object> param);
    
    List<OrderExtEntity> listAppHomeDaysNum(Map<String, Object> param);
}
