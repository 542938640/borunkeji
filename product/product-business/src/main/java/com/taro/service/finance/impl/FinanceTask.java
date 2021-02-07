package com.taro.service.finance.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taro.constants.Constant;
import com.taro.entity.device.DeviceExtEntity;
import com.taro.entity.finance.FinanceEntity;
import com.taro.entity.sec.SecTenantsEntity;
import com.taro.service.device.DeviceExtService;
import com.taro.service.finance.FinanceService;
import com.taro.service.sec.SecTenantsService;
import com.taro.utils.MD5Utils;

@Component
public class FinanceTask {
	private static final Logger LOG = Logger.getLogger(FinanceTask.class);
	
	private String API_URL = "https://api.tibiot.cn/api/v1/card/iotData";

	@Value("borunkeji.flow.username")
	private String username;
	
	@Value("borunkeji.flow.password")
	private String password;
	
	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private SecTenantsService secTenantsService;

	@Autowired
	private DeviceExtService deviceExtService;
	
	// 每小时执行一次
	@Scheduled(cron = "0 0 * * * ?")
    public void execute() {
		long start = System.currentTimeMillis();
        LOG.info("FinanceTask--->进入定时任务: 统计流量和时间");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date currentDate = new Date();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("sysFlag", "1");
        List<SecTenantsEntity> queryTenantsList = secTenantsService.list(queryMap);
        if(CollectionUtils.isNotEmpty(queryTenantsList)) {
        	FinanceEntity finance = null;
        	List<DeviceExtEntity> deviceList = null;
        	Double flow = null;
        	Long time = null;
        	Long deviceTime = null;
        	for(SecTenantsEntity tenants : queryTenantsList) {
                queryMap.put("tenants_pid", tenants.getId());
                deviceList = deviceExtService.list(queryMap);
                if(CollectionUtils.isNotEmpty(deviceList)) {
                	flow = 0D;
                	time = 0L;
                	for(DeviceExtEntity device : deviceList) {
                		if(null == device) {
                			continue;
                		}
                		if(StringUtils.isNotBlank(device.getNumber_4g())) {
                			flow += getNumber4GFlow(device.getNumber_4g());
                		}
                		if(StringUtils.isNotBlank(device.getDue_time())) {
                			deviceTime = Long.valueOf(device.getDue_time());
                			if(deviceTime > time) {
                				time = deviceTime;
                			}
                		}
                	}
                	finance = financeService.getByTenants(tenants.getId());
                	if(time > 0) {
                    	try {
							finance.setDays(daysBetween(currentDate, sdf.parse(String.valueOf(time))));
						} catch (ParseException e) {
					        LOG.error("FinanceTask--->定时任务: 统计流量和时间: "+ e.getMessage());
						}
                	}
                	finance.setC1(String.valueOf(flow));
                	if(StringUtils.isBlank(finance.getId())) {
                		financeService.save(finance);
                	}else {
                		financeService.update(finance);
                	}
                }
        	}
        }
        
        LOG.info("FinanceTask--->退出定时任务: 统计流量和时间。方法执行时长: "+ (System.currentTimeMillis() - start) + "ms");
	}
	
	public int getNumber4GFlow(String cardNumber) {
		Integer flow = 0;
		String tkey = DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS);
		String pwd = MD5Utils.getStrrMD5(MD5Utils.getStrrMD5(password) + tkey);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
    	// 创建Get请求
    	HttpGet httpGet = new HttpGet(API_URL + "?card_type=2&username=" + username + "&password=" + pwd + "&tkey=" + tkey + "&msisdn=" + cardNumber);
        // 响应模型
     	CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Get请求
			response = httpClient.execute(httpGet);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
            JSONObject data = JSON.parseObject(EntityUtils.toString(responseEntity, "utf-8"));
            if (null != data && data.containsKey("status")) {
            	Integer status = data.getInteger("status");
            	if (null != status && status.equals(0)) {
            		JSONObject flowData = data.getJSONObject("data");
            		if (null != flowData && flowData.containsKey("left_flow")) {
            			String left_flow = flowData.getString("left_flow");
            			if (StringUtils.isNotBlank(left_flow)) {
            				if (left_flow.indexOf("MB") != -1) {
            					left_flow = left_flow.replace("MB", "");
            					flow = Integer.valueOf(left_flow);
            				} else if (left_flow.indexOf("KB") != -1) {
            					left_flow = left_flow.replace("KB", "");
            					flow = Integer.valueOf(left_flow) / 1024;
            				} else if (left_flow.indexOf("GB") != -1) {
            					left_flow = left_flow.replace("GB", "");
            					flow = Integer.valueOf(left_flow) * 1024;
            				} else if (left_flow.indexOf("TB") != -1) {
            					left_flow = left_flow.replace("TB", "");
            					flow = Integer.valueOf(left_flow) * 1024 * 1024;
            				} else if (left_flow.indexOf("EB") != -1) {
            					left_flow = left_flow.replace("EB", "");
            					flow = Integer.valueOf(left_flow) * 1024 * 1024 * 1024;
            				}
            			}
            		}
            	} else {
        	        LOG.error("FinanceTask--->定时任务: 请求4G卡号[" + cardNumber + "]失败: " + data.getString("message"));
            	}
            } else {
    	        LOG.error("FinanceTask--->定时任务: 请求4G卡号[" + cardNumber + "]失败: 请求结果为空");
            }
		} catch (Exception e) {
	        LOG.error("FinanceTask--->定时任务: 请求4G卡号[" + cardNumber + "]失败: "+ e.getMessage());
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flow;
	}
	
	/** 
     * 计算两个日期之间相差的天数 
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException 
     */  
    private int daysBetween(Date smdate, Date bdate) throws ParseException {  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(bdate);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);
          
       return Integer.parseInt(String.valueOf(between_days));         
    }
    
}
