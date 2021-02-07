package com.taro.service.market.impl;import java.text.SimpleDateFormat;import java.util.Date;import java.util.List;import java.util.Map;import org.apache.commons.collections4.CollectionUtils;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.scheduling.annotation.Scheduled;import org.springframework.stereotype.Component;import com.google.common.collect.Maps;import com.taro.entity.market.MarketLuckDrawEntity;import com.taro.entity.market.MarketPayActEntity;import com.taro.service.device.TSlotInfoService;import com.taro.service.market.MarketLuckDrawService;import com.taro.service.market.MarketPayActService;import com.taro.utils.MyStringUtil;@Componentpublic class MarketTask {	private static final Logger LOG = Logger.getLogger(MarketTask.class);	@Autowired	private MarketLuckDrawService marketLuckDrawService;	@Autowired	private MarketPayActService marketPayActService;	@Autowired	private TSlotInfoService tSlotInfoService;		// 每小时执行一次	@Scheduled(cron = "0 0 * * * ?")    public void execute() {		long start = System.currentTimeMillis();        LOG.info("MerchantsTask--->进入定时任务:营销活动");    	Date endDate = null;    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");    	        Map<String, Object> queryMap = Maps.newHashMap();        queryMap.put("sysFlag", "1");        queryMap.put("status", "1");// 在运行        List<MarketLuckDrawEntity> queryLuckDrawList = marketLuckDrawService.list(queryMap);        if(CollectionUtils.isNotEmpty(queryLuckDrawList)) {    		try {    			for(MarketLuckDrawEntity luckDraw : queryLuckDrawList) {    				if(null != luckDraw && MyStringUtil.isNotBlank(luckDraw.getEnd_time())) {    					endDate = simpleDateFormat.parse(luckDraw.getEnd_time());    	        		if(endDate.getTime() < new Date().getTime()) {    	        			luckDraw.setStatus("2");// 已结束    	        			marketLuckDrawService.update(luckDraw);    	        			tSlotInfoService.updateSiDown(luckDraw.getDevice_did());    	        		}    				}    			}			} catch (Exception e) {		        LOG.info("MerchantsTask--->定时任务:  营销活动(幸运抽奖)出现异常: " + e.getMessage());			}        }        List<MarketPayActEntity> queryPayActList = marketPayActService.list(queryMap);        if(CollectionUtils.isNotEmpty(queryPayActList)) {    		try {    			for(MarketPayActEntity payAct : queryPayActList) {    				if(null != payAct && MyStringUtil.isNotBlank(payAct.getEnd_time())) {    					endDate = simpleDateFormat.parse(payAct.getEnd_time());    	        		if(endDate.getTime() < new Date().getTime()) {    	        			payAct.setStatus("2");// 已结束    	        			marketPayActService.update(payAct);    	        			tSlotInfoService.updateSiDown(payAct.getDevice_did());    	        		}    				}    			}			} catch (Exception e) {		        LOG.info("MerchantsTask--->定时任务:  营销活动(支付活动)出现异常: " + e.getMessage());			}        }                LOG.info("MerchantsTask--->退出定时任务: 营销活动。方法执行时长: "+ (System.currentTimeMillis() - start) + "ms");	}	}