package com.taro.entity.device;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:DeviceExtEntity.java</p>
 *<p>Description: Entity(对应表名:t_device_ext)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 23:15:15
 */
public class DeviceExtEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 绑定账号id
	private String account_pid;
	// 设备id
    private String device_pid;
	// 设备Did
    private String device_did;
	// 网点id
    private String outlets_pid;
	// 设备地址
    private String device_address;
	// 经度
    private Double longitude;
	// 纬度
    private Double latitude;
	// 终端号id
    private String ter_pid;
	// 上线时间
    private String online_time;
	// 到期时间
    private String due_time;
	// 4G卡号
    private String number_4g;
	// 状态(0:待分配,1:在运营,2:回收站)
    private String status;

	// 绑定账号名称
	private String account_name;
	// 租户名称
    private String tenants_name;
    
	// 设备id
    private String device_info_id;
	// 设备编号
    private String device_info_did;
	// 设备别名
    private String device_info_dialias;
	// 设备所属商户
    private String device_info_tenants;
	// 设备版本
    private String device_info_version;
    
	// 网点名称
    private String outlets_name;
	// 网点地址
    private String outlets_address;
	// 网点联系人
    private String outlets_contacts;
	// 网点手机号码
    private String outlets_phone;
	// 网点座机号码
    private String outlets_telephone;
    
	// 银联支付组id
    private String unionpay_pid;
	// 银联支付组名称
    private String unionpay_name;
	// 银联商户号id
    private String unionpay_mer_pid;
	// 银联商户号名称
    private String unionpay_mer_name;

	// 终端号名称
    private String ter_name;
    
    private List<String> idList;
    
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_pid() {
		return account_pid;
	}

	public void setAccount_pid(String account_pid) {
		this.account_pid = account_pid;
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
	 * <b>Summary:设置网点id</b>
	 * setOutlets_pid
	 * @param outlets_pid
	 */
    public void setOutlets_pid(String outlets_pid) {
    	this.outlets_pid = outlets_pid;
    }
    
    /**
	 * <b>Summary:获取网点id</b>
	 * getOutlets_pid
	 * @return String
	 */
    public String getOutlets_pid() {
    	return outlets_pid;
    }
	/**
	 * <b>Summary:设置设备地址</b>
	 * setDevice_address
	 * @param device_address
	 */
    public void setDevice_address(String device_address) {
    	this.device_address = device_address;
    }
    
    /**
	 * <b>Summary:获取设备地址</b>
	 * getDevice_address
	 * @return String
	 */
    public String getDevice_address() {
    	return device_address;
    }
	/**
	 * <b>Summary:设置经度</b>
	 * setLongitude
	 * @param longitude
	 */
    public void setLongitude(Double longitude) {
    	this.longitude = longitude;
    }
    
    /**
	 * <b>Summary:获取经度</b>
	 * getLongitude
	 * @return Double
	 */
    public Double getLongitude() {
    	return longitude;
    }
	/**
	 * <b>Summary:设置纬度</b>
	 * setLatitude
	 * @param latitude
	 */
    public void setLatitude(Double latitude) {
    	this.latitude = latitude;
    }
    
    /**
	 * <b>Summary:获取纬度</b>
	 * getLatitude
	 * @return Double
	 */
    public Double getLatitude() {
    	return latitude;
    }
	/**
	 * <b>Summary:设置终端号id</b>
	 * setTer_pid
	 * @param ter_pid
	 */
    public void setTer_pid(String ter_pid) {
    	this.ter_pid = ter_pid;
    }
    
    /**
	 * <b>Summary:获取终端号id</b>
	 * getTer_pid
	 * @return String
	 */
    public String getTer_pid() {
    	return ter_pid;
    }
	/**
	 * <b>Summary:设置上线时间</b>
	 * setOnline_time
	 * @param online_time
	 */
    public void setOnline_time(String online_time) {
    	this.online_time = online_time;
    }
    
    /**
	 * <b>Summary:获取上线时间</b>
	 * getOnline_time
	 * @return String
	 */
    public String getOnline_time() {
    	return online_time;
    }
	/**
	 * <b>Summary:设置到期时间</b>
	 * setDue_time
	 * @param due_time
	 */
    public void setDue_time(String due_time) {
    	this.due_time = due_time;
    }
    
    /**
	 * <b>Summary:获取到期时间</b>
	 * getDue_time
	 * @return String
	 */
    public String getDue_time() {
    	return due_time;
    }
	/**
	 * <b>Summary:设置4G卡号</b>
	 * setNumber_4g
	 * @param number_4g
	 */
    public void setNumber_4g(String number_4g) {
    	this.number_4g = number_4g;
    }
    
    /**
	 * <b>Summary:获取4G卡号</b>
	 * getNumber_4g
	 * @return String
	 */
    public String getNumber_4g() {
    	return number_4g;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDevice_info_id() {
		return device_info_id;
	}

	public void setDevice_info_id(String device_info_id) {
		this.device_info_id = device_info_id;
	}

	public String getDevice_info_did() {
		return device_info_did;
	}

	public void setDevice_info_did(String device_info_did) {
		this.device_info_did = device_info_did;
	}

	public String getDevice_info_tenants() {
		return device_info_tenants;
	}

	public void setDevice_info_tenants(String device_info_tenants) {
		this.device_info_tenants = device_info_tenants;
	}

	public String getOutlets_name() {
		return outlets_name;
	}

	public void setOutlets_name(String outlets_name) {
		this.outlets_name = outlets_name;
	}

	public String getOutlets_address() {
		return outlets_address;
	}

	public void setOutlets_address(String outlets_address) {
		this.outlets_address = outlets_address;
	}

	public String getOutlets_contacts() {
		return outlets_contacts;
	}

	public void setOutlets_contacts(String outlets_contacts) {
		this.outlets_contacts = outlets_contacts;
	}

	public String getOutlets_phone() {
		return outlets_phone;
	}

	public void setOutlets_phone(String outlets_phone) {
		this.outlets_phone = outlets_phone;
	}

	public String getOutlets_telephone() {
		return outlets_telephone;
	}

	public void setOutlets_telephone(String outlets_telephone) {
		this.outlets_telephone = outlets_telephone;
	}

	public String getTenants_name() {
		return tenants_name;
	}

	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

	public String getDevice_info_version() {
		return device_info_version;
	}

	public void setDevice_info_version(String device_info_version) {
		this.device_info_version = device_info_version;
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

	public String getUnionpay_pid() {
		return unionpay_pid;
	}

	public void setUnionpay_pid(String unionpay_pid) {
		this.unionpay_pid = unionpay_pid;
	}

	public String getUnionpay_name() {
		return unionpay_name;
	}

	public void setUnionpay_name(String unionpay_name) {
		this.unionpay_name = unionpay_name;
	}

	public String getUnionpay_mer_pid() {
		return unionpay_mer_pid;
	}

	public void setUnionpay_mer_pid(String unionpay_mer_pid) {
		this.unionpay_mer_pid = unionpay_mer_pid;
	}

	public String getUnionpay_mer_name() {
		return unionpay_mer_name;
	}

	public void setUnionpay_mer_name(String unionpay_mer_name) {
		this.unionpay_mer_name = unionpay_mer_name;
	}

	public String getTer_name() {
		return ter_name;
	}

	public void setTer_name(String ter_name) {
		this.ter_name = ter_name;
	}

	public String getDevice_info_dialias() {
		return device_info_dialias;
	}

	public void setDevice_info_dialias(String device_info_dialias) {
		this.device_info_dialias = device_info_dialias;
	}

}