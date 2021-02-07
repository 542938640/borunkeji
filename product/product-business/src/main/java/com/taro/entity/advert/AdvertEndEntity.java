package com.taro.entity.advert;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:AdvertEndEntity.java</p>
 *<p>Description: Entity(对应表名:t_advert_end)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-04 10:14:01
 */
public class AdvertEndEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 设备id
    private String device_pid;
	// 设备Did
    private String device_did;
	// 是否启用待机广告
    private String usewait_flag;
	// 素材id
    private String file_pid;
	// 素材类型
    private String file_type;
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
    // 积分换礼
    private String jfhl;
    // 幸运抽奖
    private String xycj;
    // 微信借记卡
    private String wxjjk;
    // 手机银行
    private String sjyh;
    // 微信信用卡
    private String wxxyk;
    // 云闪付
    private String ysf;
    // 支付宝
    private String zfb;
    
    //删除的图片id
    private String deleteFileId;
    
    private List<AdvertEndDetailEntity> detailList;
    
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
	 * <b>Summary:设置是否启用待机广告</b>
	 * setUsewait_flag
	 * @param usewait_flag
	 */
    public void setUsewait_flag(String usewait_flag) {
    	this.usewait_flag = usewait_flag;
    }
    
    /**
	 * <b>Summary:获取是否启用待机广告</b>
	 * getUsewait_flag
	 * @return String
	 */
    public String getUsewait_flag() {
    	return usewait_flag;
    }
	/**
	 * <b>Summary:设置素材id</b>
	 * setFile_pid
	 * @param file_pid
	 */
    public void setFile_pid(String file_pid) {
    	this.file_pid = file_pid;
    }
    
    /**
	 * <b>Summary:获取素材id</b>
	 * getFile_pid
	 * @return String
	 */
    public String getFile_pid() {
    	return file_pid;
    }
	/**
	 * <b>Summary:设置素材类型</b>
	 * setFile_type
	 * @param file_type
	 */
    public void setFile_type(String file_type) {
    	this.file_type = file_type;
    }
    
    /**
	 * <b>Summary:获取素材类型</b>
	 * getFile_type
	 * @return String
	 */
    public String getFile_type() {
    	return file_type;
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

	public List<AdvertEndDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<AdvertEndDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public String getJfhl() {
		return jfhl;
	}

	public void setJfhl(String jfhl) {
		this.jfhl = jfhl;
	}

	public String getXycj() {
		return xycj;
	}

	public void setXycj(String xycj) {
		this.xycj = xycj;
	}

	public String getWxjjk() {
		return wxjjk;
	}

	public void setWxjjk(String wxjjk) {
		this.wxjjk = wxjjk;
	}

	public String getSjyh() {
		return sjyh;
	}

	public void setSjyh(String sjyh) {
		this.sjyh = sjyh;
	}

	public String getWxxyk() {
		return wxxyk;
	}

	public void setWxxyk(String wxxyk) {
		this.wxxyk = wxxyk;
	}

	public String getYsf() {
		return ysf;
	}

	public void setYsf(String ysf) {
		this.ysf = ysf;
	}

	public String getZfb() {
		return zfb;
	}

	public void setZfb(String zfb) {
		this.zfb = zfb;
	}

}