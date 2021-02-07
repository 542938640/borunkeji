package com.taro.entity.market;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.taro.entity.AbstractEntity;
import com.taro.entity.sec.SecTenantsEntity;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:OrderExtEntity.java</p>
 *<p>Description:订单扩展表 Entity(对应表名:t_order_ext)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-29 16:25:37
 */
public class OrderExtEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 订单id
    private Integer order_info;
	// 设备id
    private String device_pid;
	// 设备did
    private String device_did;
	// 活动类型(1:积分换礼,2:幸运抽奖,3:微信借记卡,4:手机银行)
    private String act_type;
	// 活动id
    private String act_pid;
	// 礼品id
    private String prize_pid;
	// 用户姓名
    private String user_name;
	// 用户手机
    private String user_phone;
	// 用户签名
    private String user_sign;
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

	// 活动名称
    private String act_name;
	// 礼品名称
    private String prize_name;
	// 礼品积分
    private Integer prize_points;
	// 礼品数量
    private Integer prize_num;
	// 礼品等级
    private Integer prize_grade;
	// 礼品价格
    private Double prize_price;
	// 网点名称
    private String outlets_name;

	// 订单号
    private String order_info_no;
	// 销售价格
    private Double order_info_price;
	// 出货结果(正常0；非0为出货失败的故障代码)
    private Integer order_info_result;
	// 交易状态(0支付成功，待出货；1已退款；2退款失败；3支付成功并出货)
    private Integer order_info_status;
	// 时间
    private Date order_info_time;

	// 机构id
    private String org_id;
	// 机构订单数量
    private Integer org_num;
    
    private List<SecTenantsEntity> orgList;
    
    private List<OrderExtEntity> orgNumList;

	// 活动数量
    private Integer act_type_num;
    
	// 订单天数
    private String days;
	// 订单天数数量
    private Integer days_num;
    
    // 年
    private String year;
    // 月
    private String month;
    
	/**
	 * <b>Summary:设置订单id</b>
	 * setOrder_info
	 * @param order_info
	 */
    public void setOrder_info(Integer order_info) {
    	this.order_info = order_info;
    }
    
    /**
	 * <b>Summary:获取订单id</b>
	 * getOrder_info
	 * @return Integer
	 */
    public Integer getOrder_info() {
    	return order_info;
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
	 * <b>Summary:设置设备did</b>
	 * setDevice_did
	 * @param device_did
	 */
    public void setDevice_did(String device_did) {
    	this.device_did = device_did;
    }
    
    /**
	 * <b>Summary:获取设备did</b>
	 * getDevice_did
	 * @return String
	 */
    public String getDevice_did() {
    	return device_did;
    }
	/**
	 * <b>Summary:设置活动类型</b>
	 * setAct_type
	 * @param act_type
	 */
    public void setAct_type(String act_type) {
    	this.act_type = act_type;
    }
    
    /**
	 * <b>Summary:获取活动类型</b>
	 * getAct_type
	 * @return String
	 */
    public String getAct_type() {
    	return act_type;
    }
	/**
	 * <b>Summary:设置活动id</b>
	 * setAct_pid
	 * @param act_pid
	 */
    public void setAct_pid(String act_pid) {
    	this.act_pid = act_pid;
    }
    
    /**
	 * <b>Summary:获取活动id</b>
	 * getAct_pid
	 * @return String
	 */
    public String getAct_pid() {
    	return act_pid;
    }
	/**
	 * <b>Summary:设置礼品id</b>
	 * setPrize_pid
	 * @param prize_pid
	 */
    public void setPrize_pid(String prize_pid) {
    	this.prize_pid = prize_pid;
    }
    
    /**
	 * <b>Summary:获取礼品id</b>
	 * getPrize_pid
	 * @return String
	 */
    public String getPrize_pid() {
    	return prize_pid;
    }
	/**
	 * <b>Summary:设置用户姓名</b>
	 * setUser_name
	 * @param user_name
	 */
    public void setUser_name(String user_name) {
    	this.user_name = user_name;
    }
    
    /**
	 * <b>Summary:获取用户姓名</b>
	 * getUser_name
	 * @return String
	 */
    public String getUser_name() {
    	return user_name;
    }
	/**
	 * <b>Summary:设置用户手机</b>
	 * setUser_phone
	 * @param user_phone
	 */
    public void setUser_phone(String user_phone) {
    	this.user_phone = user_phone;
    }
    
    /**
	 * <b>Summary:获取用户手机</b>
	 * getUser_phone
	 * @return String
	 */
    public String getUser_phone() {
    	return user_phone;
    }
	/**
	 * <b>Summary:设置用户签名</b>
	 * setUser_sign
	 * @param user_sign
	 */
    public void setUser_sign(String user_sign) {
    	this.user_sign = user_sign;
    }
    
    /**
	 * <b>Summary:获取用户签名</b>
	 * getUser_sign
	 * @return String
	 */
    public String getUser_sign() {
    	return user_sign;
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

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getPrize_name() {
		return prize_name;
	}

	public void setPrize_name(String prize_name) {
		this.prize_name = prize_name;
	}

	public Integer getPrize_points() {
		return prize_points;
	}

	public void setPrize_points(Integer prize_points) {
		this.prize_points = prize_points;
	}

	public Integer getPrize_num() {
		return prize_num;
	}

	public void setPrize_num(Integer prize_num) {
		this.prize_num = prize_num;
	}

	public Integer getPrize_grade() {
		return prize_grade;
	}

	public void setPrize_grade(Integer prize_grade) {
		this.prize_grade = prize_grade;
	}

	public Double getPrize_price() {
		return prize_price;
	}

	public void setPrize_price(Double prize_price) {
		this.prize_price = prize_price;
	}

	public String getOutlets_name() {
		return outlets_name;
	}

	public void setOutlets_name(String outlets_name) {
		this.outlets_name = outlets_name;
	}

	public String getOrder_info_no() {
		return order_info_no;
	}

	public void setOrder_info_no(String order_info_no) {
		this.order_info_no = order_info_no;
	}

	public Double getOrder_info_price() {
		return order_info_price;
	}

	public void setOrder_info_price(Double order_info_price) {
		this.order_info_price = order_info_price;
	}

	public Integer getOrder_info_result() {
		return order_info_result;
	}

	public void setOrder_info_result(Integer order_info_result) {
		this.order_info_result = order_info_result;
	}

	public Integer getOrder_info_status() {
		return order_info_status;
	}

	public void setOrder_info_status(Integer order_info_status) {
		this.order_info_status = order_info_status;
	}

	public Date getOrder_info_time() {
		return order_info_time;
	}

	public void setOrder_info_time(Date order_info_time) {
		this.order_info_time = order_info_time;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Integer getOrg_num() {
		return org_num;
	}

	public void setOrg_num(Integer org_num) {
		this.org_num = org_num;
	}

	public List<SecTenantsEntity> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<SecTenantsEntity> orgList) {
		this.orgList = orgList;
	}

	public List<OrderExtEntity> getOrgNumList() {
		return orgNumList;
	}

	public void setOrgNumList(List<OrderExtEntity> orgNumList) {
		this.orgNumList = orgNumList;
	}

	public String getYear() {
		if(MyStringUtil.isBlank(year)) {
			return String.valueOf(new Date().getYear() + 1900);
		}
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		if(MyStringUtil.isBlank(month)) {
			return String.valueOf(new Date().getMonth() + 1);
		}
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getAct_type_num() {
		return act_type_num;
	}

	public void setAct_type_num(Integer act_type_num) {
		this.act_type_num = act_type_num;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Integer getDays_num() {
		return days_num;
	}

	public void setDays_num(Integer days_num) {
		this.days_num = days_num;
	}
	
}