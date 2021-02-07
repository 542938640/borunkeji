package com.taro.entity.market;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:MarketLuckDrawEntity.java</p>
 *<p>Description:幸运抽奖 Entity(对应表名:t_market_luckdraw)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 10:06:13
 */
public class MarketLuckDrawEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 设备id
    private String device_pid;
	// 设备Did
    private String device_did;
	// 活动状态(0=待上线,1=在运行,2=历史)
    private String status;
	// 活动id
    private String act_id;
	// 活动名称
    private String act_name;
	// 开始时间
    private String start_time;
	// 结束时间
    private String end_time;
	// 活动内容
    private String content;
	// 主办单位
    private String company;
	// 咨询电话
    private String phone;
	// 是否指定参与人数
    private String is_number;
	// 参与人数
    private Integer number;
	// 是否指定用户参与
    private String is_specific_user;
	// 指定用户
    private String specific_user;
	// 是否指定参与频率
    private String is_frequency;
	// 频率类型
    private String frequency_type;
	// 频率数量
    private Integer frequency_num;
	// 频率最大数量
    private Integer frequency_max_num;
	// 客户是否需要登记
    private String is_register;
	// 是否指定手机号
    private String is_phone;
	// 
    private String c1;
	// 
    private String c2;
	// 
    private String c3;
	// 
    private String c4;
	// 
    private String c5;

    //删除的图片id
    private String deleteFileId;
    
    private List<MarketLuckDrawPrizeEntity> prizeList;
    
	/**
	 * <b>Summary:设置租户id</b>
	 * setTenants_pid
	 * @param tenants_pid
	 */
    public void setTenants_pid(String tenants_pid) {
    	this.tenants_pid = tenants_pid;
    }
    
    /**
	 * <b>Summary:获取租户id</b>
	 * getTenants_pid
	 * @return String
	 */
    public String getTenants_pid() {
    	return tenants_pid;
    }
	/**
	 * <b>Summary:设置设备id</b>
	 * setDevice_pid
	 * @param device_pid
	 */
    public void setDevice_pid(String device_pid) {
    	this.device_pid = device_pid;
    }
    
    /**
	 * <b>Summary:获取设备id</b>
	 * getDevice_pid
	 * @return String
	 */
    public String getDevice_pid() {
    	return device_pid;
    }
	/**
	 * <b>Summary:设置设备Did</b>
	 * setDevice_did
	 * @param device_did
	 */
    public void setDevice_did(String device_did) {
    	this.device_did = device_did;
    }
    
    /**
	 * <b>Summary:获取设备Did</b>
	 * getDevice_did
	 * @return String
	 */
    public String getDevice_did() {
    	return device_did;
    }
	/**
	 * <b>Summary:设置活动状态(0=待上线,1=在运行,2=历史)</b>
	 * setStatus
	 * @param status
	 */
    public void setStatus(String status) {
    	this.status = status;
    }
    
    /**
	 * <b>Summary:获取活动状态(0=待上线,1=在运行,2=历史)</b>
	 * getStatus
	 * @return String
	 */
    public String getStatus() {
    	return status;
    }
	/**
	 * <b>Summary:设置活动id</b>
	 * setAct_id
	 * @param act_id
	 */
    public void setAct_id(String act_id) {
    	this.act_id = act_id;
    }
    
    /**
	 * <b>Summary:获取活动id</b>
	 * getAct_id
	 * @return String
	 */
    public String getAct_id() {
    	return act_id;
    }
	/**
	 * <b>Summary:设置活动名称</b>
	 * setAct_name
	 * @param act_name
	 */
    public void setAct_name(String act_name) {
    	this.act_name = act_name;
    }
    
    /**
	 * <b>Summary:获取活动名称</b>
	 * getAct_name
	 * @return String
	 */
    public String getAct_name() {
    	return act_name;
    }
	/**
	 * <b>Summary:设置开始时间</b>
	 * setStart_time
	 * @param start_time
	 */
    public void setStart_time(String start_time) {
    	this.start_time = start_time;
    }
    
    /**
	 * <b>Summary:获取开始时间</b>
	 * getStart_time
	 * @return String
	 */
    public String getStart_time() {
    	return start_time;
    }
	/**
	 * <b>Summary:设置结束时间</b>
	 * setEnd_time
	 * @param end_time
	 */
    public void setEnd_time(String end_time) {
    	this.end_time = end_time;
    }
    
    /**
	 * <b>Summary:获取结束时间</b>
	 * getEnd_time
	 * @return String
	 */
    public String getEnd_time() {
    	return end_time;
    }
	/**
	 * <b>Summary:设置活动内容</b>
	 * setContent
	 * @param content
	 */
    public void setContent(String content) {
    	this.content = content;
    }
    
    /**
	 * <b>Summary:获取活动内容</b>
	 * getContent
	 * @return String
	 */
    public String getContent() {
    	return content;
    }
	/**
	 * <b>Summary:设置主办单位</b>
	 * setCompany
	 * @param company
	 */
    public void setCompany(String company) {
    	this.company = company;
    }
    
    /**
	 * <b>Summary:获取主办单位</b>
	 * getCompany
	 * @return String
	 */
    public String getCompany() {
    	return company;
    }
	/**
	 * <b>Summary:设置咨询电话</b>
	 * setPhone
	 * @param phone
	 */
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    
    /**
	 * <b>Summary:获取咨询电话</b>
	 * getPhone
	 * @return String
	 */
    public String getPhone() {
    	return phone;
    }
	/**
	 * <b>Summary:设置是否指定参与人数</b>
	 * setIs_number
	 * @param is_number
	 */
    public void setIs_number(String is_number) {
    	this.is_number = is_number;
    }
    
    /**
	 * <b>Summary:获取是否指定参与人数</b>
	 * getIs_number
	 * @return String
	 */
    public String getIs_number() {
    	return is_number;
    }
	/**
	 * <b>Summary:设置参与人数</b>
	 * setNumber
	 * @param number
	 */
    public void setNumber(Integer number) {
    	this.number = number;
    }
    
    /**
	 * <b>Summary:获取参与人数</b>
	 * getNumber
	 * @return Integer
	 */
    public Integer getNumber() {
    	return number;
    }
	/**
	 * <b>Summary:设置是否指定用户参与</b>
	 * setIs_specific_user
	 * @param is_specific_user
	 */
    public void setIs_specific_user(String is_specific_user) {
    	this.is_specific_user = is_specific_user;
    }
    
    /**
	 * <b>Summary:获取是否指定用户参与</b>
	 * getIs_specific_user
	 * @return String
	 */
    public String getIs_specific_user() {
    	return is_specific_user;
    }
	/**
	 * <b>Summary:设置指定用户</b>
	 * setSpecific_user
	 * @param specific_user
	 */
    public void setSpecific_user(String specific_user) {
    	this.specific_user = specific_user;
    }
    
    /**
	 * <b>Summary:获取指定用户</b>
	 * getSpecific_user
	 * @return String
	 */
    public String getSpecific_user() {
    	return specific_user;
    }
	/**
	 * <b>Summary:设置是否指定参与频率</b>
	 * setIs_frequency
	 * @param is_frequency
	 */
    public void setIs_frequency(String is_frequency) {
    	this.is_frequency = is_frequency;
    }
    
    /**
	 * <b>Summary:获取是否指定参与频率</b>
	 * getIs_frequency
	 * @return String
	 */
    public String getIs_frequency() {
    	return is_frequency;
    }
	/**
	 * <b>Summary:设置频率类型</b>
	 * setFrequency_type
	 * @param frequency_type
	 */
    public void setFrequency_type(String frequency_type) {
    	this.frequency_type = frequency_type;
    }
    
    /**
	 * <b>Summary:获取频率类型</b>
	 * getFrequency_type
	 * @return String
	 */
    public String getFrequency_type() {
    	return frequency_type;
    }
	/**
	 * <b>Summary:设置频率数量</b>
	 * setFrequency_num
	 * @param frequency_num
	 */
    public void setFrequency_num(Integer frequency_num) {
    	this.frequency_num = frequency_num;
    }
    
    /**
	 * <b>Summary:获取频率数量</b>
	 * getFrequency_num
	 * @return Integer
	 */
    public Integer getFrequency_num() {
    	return frequency_num;
    }
	/**
	 * <b>Summary:设置频率最大数量</b>
	 * setFrequency_max_num
	 * @param frequency_max_num
	 */
    public void setFrequency_max_num(Integer frequency_max_num) {
    	this.frequency_max_num = frequency_max_num;
    }
    
    /**
	 * <b>Summary:获取频率最大数量</b>
	 * getFrequency_max_num
	 * @return Integer
	 */
    public Integer getFrequency_max_num() {
    	return frequency_max_num;
    }
	/**
	 * <b>Summary:设置客户是否需要登记</b>
	 * setIs_register
	 * @param is_register
	 */
    public void setIs_register(String is_register) {
    	this.is_register = is_register;
    }
    
    /**
	 * <b>Summary:获取客户是否需要登记</b>
	 * getIs_register
	 * @return String
	 */
    public String getIs_register() {
    	return is_register;
    }
	/**
	 * <b>Summary:设置是否指定手机号</b>
	 * setIs_phone
	 * @param is_phone
	 */
    public void setIs_phone(String is_phone) {
    	this.is_phone = is_phone;
    }
    
    /**
	 * <b>Summary:获取是否指定手机号</b>
	 * getIs_phone
	 * @return String
	 */
    public String getIs_phone() {
    	return is_phone;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC1
	 * @param c1
	 */
    public void setC1(String c1) {
    	this.c1 = c1;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC1
	 * @return String
	 */
    public String getC1() {
    	return c1;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC2
	 * @param c2
	 */
    public void setC2(String c2) {
    	this.c2 = c2;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC2
	 * @return String
	 */
    public String getC2() {
    	return c2;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC3
	 * @param c3
	 */
    public void setC3(String c3) {
    	this.c3 = c3;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC3
	 * @return String
	 */
    public String getC3() {
    	return c3;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC4
	 * @param c4
	 */
    public void setC4(String c4) {
    	this.c4 = c4;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC4
	 * @return String
	 */
    public String getC4() {
    	return c4;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC5
	 * @param c5
	 */
    public void setC5(String c5) {
    	this.c5 = c5;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC5
	 * @return String
	 */
    public String getC5() {
    	return c5;
    }

	public String getDeleteFileId() {
		return deleteFileId;
	}

	public void setDeleteFileId(String deleteFileId) {
		this.deleteFileId = deleteFileId;
	}

	public List<MarketLuckDrawPrizeEntity> getPrizeList() {
		return prizeList;
	}

	public void setPrizeList(List<MarketLuckDrawPrizeEntity> prizeList) {
		this.prizeList = prizeList;
	}

}