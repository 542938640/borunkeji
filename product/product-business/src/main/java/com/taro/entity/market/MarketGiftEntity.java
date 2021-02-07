package com.taro.entity.market;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:MarketGiftEntity.java</p>
 *<p>Description:积分换礼 Entity(对应表名:t_market_gift)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:20:40
 */
public class MarketGiftEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 设备id
    private String device_pid;
	// 设备Did
    private String device_did;
	// 图片
    private String image;
	// 名称
    private String name;
	// 品牌
    private String brand;
	// 规格
    private String specs;
	// 库存
    private Integer stock;
	// 积分
    private Integer points;
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

	// 优惠券id
    private String coupon_id;
	// 优惠券名称
    private String coupon_name;
	// 开始日期
    private String start_date;
	// 结束日期
    private String end_date;
	// 面值(元)
    private Double face_value;
	// 类型(1:满多少可用,2:指定产品,3:无限制)
    private String coupon_type;
	// 满多少元可用
    private Double full_value;
	// 发放数量
    private Integer frant_num;
	// 领取数量
    private Integer req_num;

    // 门店名称
    private String store_name;
    // 门店联系电话
    private String store_phone;
    // 门店业务类型
    private String store_trade;
    // 门店地址
    private String store_address;
    
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
	 * <b>Summary:设置图片</b>
	 * setImage
	 * @param image
	 */
    public void setImage(String image) {
    	this.image = image;
    }
    
    /**
	 * <b>Summary:获取图片</b>
	 * getImage
	 * @return String
	 */
    public String getImage() {
    	return image;
    }
	/**
	 * <b>Summary:设置名称</b>
	 * setName
	 * @param name
	 */
    public void setName(String name) {
    	this.name = name;
    }
    
    /**
	 * <b>Summary:获取名称</b>
	 * getName
	 * @return String
	 */
    public String getName() {
    	return name;
    }
	/**
	 * <b>Summary:设置品牌</b>
	 * setBrand
	 * @param brand
	 */
    public void setBrand(String brand) {
    	this.brand = brand;
    }
    
    /**
	 * <b>Summary:获取品牌</b>
	 * getBrand
	 * @return String
	 */
    public String getBrand() {
    	return brand;
    }
	/**
	 * <b>Summary:设置规格</b>
	 * setSpecs
	 * @param specs
	 */
    public void setSpecs(String specs) {
    	this.specs = specs;
    }
    
    /**
	 * <b>Summary:获取规格</b>
	 * getSpecs
	 * @return String
	 */
    public String getSpecs() {
    	return specs;
    }
	/**
	 * <b>Summary:设置库存</b>
	 * setStock
	 * @param stock
	 */
    public void setStock(Integer stock) {
    	this.stock = stock;
    }
    
    /**
	 * <b>Summary:获取库存</b>
	 * getStock
	 * @return Integer
	 */
    public Integer getStock() {
    	return stock;
    }
	/**
	 * <b>Summary:设置积分</b>
	 * setPoints
	 * @param points
	 */
    public void setPoints(Integer points) {
    	this.points = points;
    }
    
    /**
	 * <b>Summary:获取积分</b>
	 * getPoints
	 * @return Integer
	 */
    public Integer getPoints() {
    	return points;
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

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Double getFace_value() {
		return face_value;
	}

	public void setFace_value(Double face_value) {
		this.face_value = face_value;
	}

	public String getCoupon_type() {
		return coupon_type;
	}

	public void setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
	}

	public Double getFull_value() {
		return full_value;
	}

	public void setFull_value(Double full_value) {
		this.full_value = full_value;
	}

	public Integer getFrant_num() {
		return frant_num;
	}

	public void setFrant_num(Integer frant_num) {
		this.frant_num = frant_num;
	}

	public Integer getReq_num() {
		return req_num;
	}

	public void setReq_num(Integer req_num) {
		this.req_num = req_num;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_phone() {
		return store_phone;
	}

	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}

	public String getStore_trade() {
		return store_trade;
	}

	public void setStore_trade(String store_trade) {
		this.store_trade = store_trade;
	}

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

}