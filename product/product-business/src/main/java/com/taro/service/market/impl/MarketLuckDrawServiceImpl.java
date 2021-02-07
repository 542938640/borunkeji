package com.taro.service.market.impl;import java.util.List;import java.util.Map;import org.apache.commons.collections4.CollectionUtils;import org.apache.commons.lang3.StringUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import com.google.common.collect.Lists;import com.google.common.collect.Maps;import com.taro.dao.market.MarketLuckDrawDao;import com.taro.entity.market.MarketLuckDrawEntity;import com.taro.entity.market.MarketLuckDrawPrizeEntity;import com.taro.exception.BusinessException;import com.taro.service.AbstractService;import com.taro.service.device.TSlotInfoService;import com.taro.service.file.FileManageService;import com.taro.service.market.MarketLuckDrawPrizeService;import com.taro.service.market.MarketLuckDrawService;import com.taro.utils.MyStringUtil;import com.taro.utils.UuidUtil;/** *<p>Title:MarketLuckDrawServiceImpl.java</p> *<p>Description:幸运抽奖 ServiceImpl</p> *@author 张宇唯 *@version 1.0 *@Automatically generate by Coder in 2020-10-27 10:06:13 */@Servicepublic class MarketLuckDrawServiceImpl extends AbstractService<MarketLuckDrawEntity> implements MarketLuckDrawService {		@Autowired	private MarketLuckDrawDao marketLuckDrawDao;	    @Override    protected MarketLuckDrawDao getDao() {        return marketLuckDrawDao;    }	@Autowired	private MarketLuckDrawPrizeService marketLuckDrawPrizeService;		@Autowired	private FileManageService fileManageService;	@Autowired	private TSlotInfoService tSlotInfoService;	    @Override    public MarketLuckDrawEntity saveMarketLuckDraw(MarketLuckDrawEntity model) {    	if(null == model) {            throw new BusinessException("保存对象不能为空!");    	}    	MarketLuckDrawEntity saveModel = new MarketLuckDrawEntity();    	saveModel.setDevice_did(model.getDevice_did());    	saveModel.setAct_id(model.getAct_id());    	saveModel.setAct_name(model.getAct_name());    	saveModel.setStart_time(model.getStart_time());    	saveModel.setEnd_time(model.getEnd_time());    	saveModel.setContent(model.getContent());    	saveModel.setCompany(model.getCompany());    	saveModel.setPhone(model.getPhone());    	saveModel.setIs_number(model.getIs_number());    	saveModel.setNumber(model.getNumber());    	    	if(StringUtils.isBlank(model.getId())) {    		Map<String, Object> queryMap = Maps.newHashMap();    		queryMap.put("device_did", model.getDevice_did());    		Long count = getDao().listCount(queryMap);    		    		saveModel.setAct_id(String.valueOf(count + 1));    		saveModel.setStatus("0");// 待上线    		super.save(saveModel);    		    		// 先保存奖品    		MarketLuckDrawPrizeEntity prize = null;    		for(int i = 1; i <= 6; i++) {    			prize = new MarketLuckDrawPrizeEntity();    			prize.setLuckdraw_pid(saveModel.getId());    			prize.setPrize_type("1");    			prize.setPrize_image(UuidUtil.get32UUID());    			prize.setNum(0);    			prize.setSort(i);    			marketLuckDrawPrizeService.save(prize);    		}    		    	}else {    		saveModel.setId(model.getId());    		super.update(saveModel);    	}    	        return saveModel;    }    @Override    public MarketLuckDrawEntity saveMarketLuckDrawPrize(MarketLuckDrawEntity model) {    	if(null == model) {            throw new BusinessException("保存对象不能为空!");    	}    	//删除前端已删除的文件    	if(StringUtils.isNotBlank(model.getDeleteFileId())) {    		fileManageService.deleteFile(model.getDeleteFileId());    	}    	    	if(CollectionUtils.isNotEmpty(model.getPrizeList())) {    		for(MarketLuckDrawPrizeEntity prize : model.getPrizeList()) {    	    	//激活照片    	    	if(StringUtils.isNotBlank(prize.getPrize_image())) {    	    		fileManageService.activation(prize.getPrize_image());    	    	}    	    	marketLuckDrawPrizeService.update(prize);    		}    	}    	        return model;    }        @Override    public MarketLuckDrawEntity saveMarketLuckDrawRule(MarketLuckDrawEntity model) {    	if(null == model) {            throw new BusinessException("保存对象不能为空!");    	}    	    	MarketLuckDrawEntity updateModel = new MarketLuckDrawEntity();    	updateModel.setId(model.getId());    	updateModel.setIs_specific_user(model.getIs_specific_user());    	updateModel.setSpecific_user(model.getSpecific_user());    	updateModel.setIs_frequency(model.getIs_frequency());    	updateModel.setFrequency_num(model.getFrequency_num());    	updateModel.setFrequency_max_num(model.getFrequency_max_num());    	updateModel.setIs_register(model.getIs_register());    	updateModel.setIs_phone(model.getIs_phone());    	super.update(updateModel);    	    	if(CollectionUtils.isNotEmpty(model.getPrizeList())) {    		MarketLuckDrawPrizeEntity prizeUpdateModel = null;    		for(MarketLuckDrawPrizeEntity prize : model.getPrizeList()) {    			prizeUpdateModel = new MarketLuckDrawPrizeEntity();    			prizeUpdateModel.setId(prize.getId());    			prizeUpdateModel.setPhone(prize.getPhone());    	    	marketLuckDrawPrizeService.update(prize);    		}    	}    	        return updateModel;    }        @Override    public MarketLuckDrawEntity goOnline(String id) {    	if(MyStringUtil.isBlank(id)) {            throw new BusinessException("保存对象id不能为空!");    	}    	    	MarketLuckDrawEntity model = super.get(id);    	if(null == model) {            throw new BusinessException("查询对象为空!");    	}    	    	Map<String, Object> queryMap = Maps.newHashMap();    	queryMap.put("sysFlag", "1");    	queryMap.put("device_did", model.getDevice_did());    	queryMap.put("status", "1");    	List<MarketLuckDrawEntity> queryList = super.list(queryMap);    	if(CollectionUtils.isNotEmpty(queryList)) {    		for(MarketLuckDrawEntity luckDraw: queryList) {    			luckDraw.setStatus("2");// 移至历史    			super.update(luckDraw);    		}    	}    	    	model.setStatus("1");    	super.update(model);    	    	// 通知socket活动更新    	tSlotInfoService.updateSiDown(model.getDevice_did());    	        return model;    }    @Override    public MarketLuckDrawEntity getRunByDevice(String device_did) {        if (MyStringUtil.isBlank(device_did)) {            throw new BusinessException("传入对象id不能为空");        }                boolean flag = true;                MarketLuckDrawEntity model = null;                Map<String, Object> queryMap = Maps.newHashMap();    	queryMap.put("sysFlag", "1");    	queryMap.put("device_did", device_did);    	queryMap.put("status", "1");    	List<MarketLuckDrawEntity> queryList = super.list(queryMap);    	if(CollectionUtils.isNotEmpty(queryList)) {    		model = queryList.get(0);    		//    		// 判断活动是否已经结束//    		if(MyStringUtil.isNotBlank(model.getEnd_time())) {//        		try {//					Date endDate = new SimpleDateFormat("yyyyMMddHHmm").parse(model.getEnd_time());//					if(endDate.getTime() < new Date().getTime()) {//						flag = false;//						// 活动时间已结束,移至历史//				    	model.setStatus("2");//				    	super.update(model);//				    	// 通知socket活动更新//				    	tSlotInfoService.updateSiDown(model.getDevice_did());//					}//				} catch (ParseException e) {//					flag = false;//				}//    		}    		    		queryMap.clear();    		queryMap.put("luckdraw_pid", model.getId());    		queryMap.put("sysFlag", "1");    		model.setPrizeList(marketLuckDrawPrizeService.list(queryMap));    	}    	    	if(!flag) {    		model = new MarketLuckDrawEntity();    		List<MarketLuckDrawPrizeEntity> prizeList = Lists.newArrayList();    		prizeList.add(new MarketLuckDrawPrizeEntity());    		prizeList.add(new MarketLuckDrawPrizeEntity());    		prizeList.add(new MarketLuckDrawPrizeEntity());    		prizeList.add(new MarketLuckDrawPrizeEntity());    		prizeList.add(new MarketLuckDrawPrizeEntity());    		prizeList.add(new MarketLuckDrawPrizeEntity());    		model.setPrizeList(prizeList);    	}                return model;    }    @Override    public MarketLuckDrawEntity getMarketLuckDraw(String id) {        if (MyStringUtil.isBlank(id)) {            throw new BusinessException("传入对象id不能为空");        }                MarketLuckDrawEntity model = super.get(id);        if(null == model) {            throw new BusinessException("查询对象为空");        }        Map<String, Object> queryMap = Maps.newHashMap();		queryMap.put("luckdraw_pid", model.getId());		queryMap.put("sysFlag", "1");		model.setPrizeList(marketLuckDrawPrizeService.list(queryMap));                return model;    }    @Override    public MarketLuckDrawEntity getAndLock(String id) {        if (MyStringUtil.isBlank(id)) {            throw new BusinessException("传入对象id不能为空");        }                MarketLuckDrawEntity model = getDao().getAndLock(id);        if(null == model) {            throw new BusinessException("查询对象为空");        }        Map<String, Object> queryMap = Maps.newHashMap();		queryMap.put("luckdraw_pid", model.getId());		queryMap.put("sysFlag", "1");		model.setPrizeList(marketLuckDrawPrizeService.listAndLock(queryMap));                return model;    }}