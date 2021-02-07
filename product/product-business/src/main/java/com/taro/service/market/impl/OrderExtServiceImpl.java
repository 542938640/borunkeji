package com.taro.service.market.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taro.common.Page;
import com.taro.dao.market.OrderExtDao;
import com.taro.entity.market.OrderExtEntity;
import com.taro.entity.sec.SecTenantsEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.market.OrderExtService;
import com.taro.service.sec.SecTenantsService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:OrderExtServiceImpl.java</p>
 *<p>Description:订单扩展表 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-29 16:25:37
 */
@Service
public class OrderExtServiceImpl extends AbstractService<OrderExtEntity> implements OrderExtService {
	
	@Autowired
	private OrderExtDao orderExtDao;
	
    @Override
    protected OrderExtDao getDao() {
        return orderExtDao;
    }

	@Autowired
	private SecTenantsService secTenantsService;

	@Override
    public List<OrderExtEntity> listAppHomeNum(Map<String, Object> param){
		addDateParam(param);
    	return getDao().listAppHomeNum(param);
    }
	
	@Override
    public List<OrderExtEntity> listAppHomeDaysNum(Map<String, Object> param){
		addDateParam(param);
    	return getDao().listAppHomeDaysNum(param);
    }
	
    @Override
    public OrderExtEntity listHomeNum(Map<String, Object> parameter) {
    	OrderExtEntity model = new OrderExtEntity();
    	List<SecTenantsEntity> orgList = null;
    	
        addDateParam(parameter);
        
        String[] tenants_pids = null;
        String tenants_pid = String.valueOf(parameter.get("tenants_pid"));
        String tenants_name = String.valueOf(parameter.get("tenants_name"));
        
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("parent_pid", tenants_pid);
        queryMap.put("sys_flag", "1");
        List<SecTenantsEntity> queryList = secTenantsService.list(queryMap);
        if(CollectionUtils.isNotEmpty(queryList)) {
        	int l = queryList.size();
        	tenants_pids = new String[l];
        	for(int i =0; i < l; i++) {
        		tenants_pids[i] = queryList.get(i).getId();
        	}
        	orgList = queryList;
        }else {
        	tenants_pids = new String[1];
        	tenants_pids[0] = tenants_pid;
        	orgList = Lists.newArrayList();
        	SecTenantsEntity org = new SecTenantsEntity();
        	org.setId(tenants_pid);
        	org.setName(tenants_name);
        	orgList.add(org);
        }
        
        // new
        String act_type = String.valueOf(parameter.get("act_type"));
        List<OrderExtEntity> orgNumList = Lists.newArrayList();
        OrderExtEntity orderExt = null;
        for(SecTenantsEntity org : orgList) {
        	parameter.put("tenants", org.getId());
        	orderExt = new OrderExtEntity();
        	orderExt.setOrg_id(org.getId());
        	orderExt.setOrg_num(getDao().listHomeSumNum(parameter));
        	if(MyStringUtil.isNotBlank(act_type) && "1".equals(act_type)) {
        		orderExt.setPrize_num(getDao().listHomeSumPrizeNum(parameter));
        	}
        	orgNumList.add(orderExt);
        }
        
//    	parameter.put("tenantsArr", tenants_pids);
    	
    	model.setOrgList(orgList);
//    	model.setOrgNumList(getDao().listHomeNum(parameter));
    	model.setOrgNumList(orgNumList);
    	
        return model;
    }
    
    @Override
    public OrderExtEntity saveOrderExt (OrderExtEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }

    @Override
    public List<OrderExtEntity> listOrder(Map<String, Object> parameter) {
        addDateParam(parameter);
        return getDao().listOrder(parameter);
    }
    
    @Override
    public Page<OrderExtEntity> listOrder(int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<OrderExtEntity> reuslt = this.listOrder(parameter);
        return new Page<>(reuslt);
    }

    @Override
    public Long listCount(Map<String, Object> parameter) {
        addDateParam(parameter);
        return getDao().listCount(parameter);
    }
    
    public void addDateParam(Map<String, Object> parameter) {
        // 根据开始时间、结束时间分表查询
        String start_time = String.valueOf(parameter.get("start_time"));
        String end_time = String.valueOf(parameter.get("end_time"));
        if(MyStringUtil.isNotBlank(start_time) && MyStringUtil.isNotBlank(end_time)) {

//			try {
//	            parameter.put("start_time", new SimpleDateFormat("yyyy-MM-dd").parse(start_time));
//	            parameter.put("end_time", new SimpleDateFormat("yyyy-MM-dd").parse(end_time));
//			} catch (ParseException e) {
//                throw new BusinessException("查询时间错误!");
//			}
            parameter.put("start_date", start_time.replaceAll("-", "") + "000000");
            parameter.put("end_date", end_time.replaceAll("-", "") + "235959");
            
            Date nowDate = new Date();
            Integer year = nowDate.getYear() + 1900;
            Integer month = nowDate.getMonth() + 2;
            
    		String[] startTimeArr = start_time.split("-");
    		String[] endTimeArr = end_time.split("-");
    		
    		Integer startYear = Integer.valueOf(startTimeArr[0]);
    		Integer endYear = Integer.valueOf(endTimeArr[0]);

    		Integer startMonth = Integer.valueOf(startTimeArr[1]);
    		Integer endMonth = Integer.valueOf(endTimeArr[1]);
    		
    		if(startYear > year || endYear > year || (endYear.equals(year) && (endMonth > month))) {
                throw new BusinessException("只能查询当前时间之前的订单!");
    		}
    		
    		parameter.put("lists", getDateLists(startYear, endYear, startMonth, endMonth));
        }
    }
    
    private List<Map<String, Object>> getDateLists(Integer startYear, Integer endYear, Integer startMonth, Integer endMonth) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> map = null;
		// 开始时间和结束时间同年
		if(startYear.equals(endYear)) {
			for(; startMonth <= endMonth; startMonth++) {
				map = Maps.newHashMap();
				map.put("year", startYear);
				map.put("month", startMonth);
				list.add(map);
			}
		}else {
			for(; startMonth <= 12; startMonth++) {
				map = Maps.newHashMap();
				map.put("year", startYear);
				map.put("month", startMonth);
				list.add(map);
			}
			startYear ++;
			endYear --;
			for(; startYear <= endYear; startYear++) {
				for(int i = 1; i <= 12; i++) {
					map = Maps.newHashMap();
					map.put("year", startYear);
					map.put("month", i);
					list.add(map);
				}
			}
			for(int i = 1; i <= endMonth; i++) {
				map = Maps.newHashMap();
				map.put("year", endYear + 1);
				map.put("month", i);
				list.add(map);
			}
		}
		return list;
    }
    
    public static void main(String[] args) {
		String start_time = "2020-03-01";
		String end_time = "2020-08-21";
		String[] startTimeArr = start_time.split("-");
		String[] endTimeArr = end_time.split("-");
		
		Integer startYear = Integer.valueOf(startTimeArr[0]);
		Integer endYear = Integer.valueOf(endTimeArr[0]);

		Integer startMonth = Integer.valueOf(startTimeArr[1]);
		Integer endMonth = Integer.valueOf(endTimeArr[1]);
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> map = null;
		// 开始时间和结束时间同年
		if(startYear.equals(endYear)) {
			for(; startMonth < endMonth; startMonth++) {
				map = Maps.newHashMap();
				map.put("year", startYear);
				map.put("month", startMonth);
				list.add(map);
			}
		}else {
			for(; startMonth <= 12; startMonth++) {
				map = Maps.newHashMap();
				map.put("year", startYear);
				map.put("month", startMonth);
				list.add(map);
			}
			startYear ++;
			endYear --;
			for(; startYear <= endYear; startYear++) {
				for(int i = 1; i <= 12; i++) {
					map = Maps.newHashMap();
					map.put("year", startYear);
					map.put("month", i);
					list.add(map);
				}
			}
			for(int i = 1; i <= endMonth; i++) {
				map = Maps.newHashMap();
				map.put("year", endYear + 1);
				map.put("month", i);
				list.add(map);
			}
		}
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> m : list) {
				System.out.println(m.get("year") + ":" + m.get("month"));
			}
		}
	}
    
}