package com.taro.entity.market;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:TProductEntity.java</p>
 *<p>Description: Entity(对应表名:tproduct)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-25 13:15:58
 */
public class TProductEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 商品分类
    private Integer pdcitype;
	// 创建时间
    private String createdate;
	// 角色描述
    private String description;
	// 创建者
    private String fatherid;
	// 商品厂家
    private String manufacturers;
	// 品牌ID
    private String pdbrandid;
	// 品牌名称
    private String pdbrandname;
	// 地址市
    private String pdcity;
	// 条码
    private String pdcode;
	// 商品说明书
    private String pdcontent;
	// 进价
    private Double pdcostprice;
	// 商品图片
    private String pdimgurl;
	// 商品名称
    private String pdname;
	// 产地
    private String pdproductionplace;
	// 地址省
    private String pdprovince;
	// 保质期
    private String pdqualityperiod;
	// 商品零售价
    private Double pdretailprice;
	// 商品规格
    private String pdspecification;
	// 名称首拼码
    private String pdspell;
	// 销售状态
    private String pdstatus;
	// 商品储存模式
    private String pdstoragemode;
	// 商品本地类型
    private Integer pdtype;
	// 默认是0 私有  通过审核是1 公有
    private Integer pstatus;
	// 
    private String pradultlimit;
	// 
    private String praudit;
	// 
    private String prdiscount;
	// 
    private String prmemberprice;
	// 
    private String prpack;
	// 
    private String prpromotionprice;
	// 
    private String prtaxrate;

	// 商品分类
    private String pdcitype_name;

	// 商品厂家
    private String manufacturers_name;
    
	// 品牌名称
    private String pdbrand_name;

	// 品牌名称
    private String slot_pid;
    
    //删除的图片id
    private String deleteFileId;

	// 是否启用(1:启用,0:停用)
    private String enable;
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
    
	/**
	 * <b>Summary:设置</b>
	 * setPdcitype
	 * @param pdcitype
	 */
    public void setPdcitype(Integer pdcitype) {
    	this.pdcitype = pdcitype;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPdcitype
	 * @return Integer
	 */
    public Integer getPdcitype() {
    	return pdcitype;
    }
	/**
	 * <b>Summary:设置创建时间</b>
	 * setCreatedate
	 * @param createdate
	 */
    public void setCreatedate(String createdate) {
    	this.createdate = createdate;
    }
    
    /**
	 * <b>Summary:获取创建时间</b>
	 * getCreatedate
	 * @return String
	 */
    public String getCreatedate() {
    	return createdate;
    }
	/**
	 * <b>Summary:设置角色描述</b>
	 * setDescription
	 * @param description
	 */
    public void setDescription(String description) {
    	this.description = description;
    }
    
    /**
	 * <b>Summary:获取角色描述</b>
	 * getDescription
	 * @return String
	 */
    public String getDescription() {
    	return description;
    }
	/**
	 * <b>Summary:设置创建者</b>
	 * setFatherid
	 * @param fatherid
	 */
    public void setFatherid(String fatherid) {
    	this.fatherid = fatherid;
    }
    
    /**
	 * <b>Summary:获取创建者</b>
	 * getFatherid
	 * @return String
	 */
    public String getFatherid() {
    	return fatherid;
    }
	/**
	 * <b>Summary:设置商品厂家</b>
	 * setManufacturers
	 * @param manufacturers
	 */
    public void setManufacturers(String manufacturers) {
    	this.manufacturers = manufacturers;
    }
    
    /**
	 * <b>Summary:获取商品厂家</b>
	 * getManufacturers
	 * @return String
	 */
    public String getManufacturers() {
    	return manufacturers;
    }
	/**
	 * <b>Summary:设置品牌ID</b>
	 * setPdbrandid
	 * @param pdbrandid
	 */
    public void setPdbrandid(String pdbrandid) {
    	this.pdbrandid = pdbrandid;
    }
    
    /**
	 * <b>Summary:获取品牌ID</b>
	 * getPdbrandid
	 * @return String
	 */
    public String getPdbrandid() {
    	return pdbrandid;
    }
	/**
	 * <b>Summary:设置品牌名称</b>
	 * setPdbrandname
	 * @param pdbrandname
	 */
    public void setPdbrandname(String pdbrandname) {
    	this.pdbrandname = pdbrandname;
    }
    
    /**
	 * <b>Summary:获取品牌名称</b>
	 * getPdbrandname
	 * @return String
	 */
    public String getPdbrandname() {
    	return pdbrandname;
    }
	/**
	 * <b>Summary:设置地址市</b>
	 * setPdcity
	 * @param pdcity
	 */
    public void setPdcity(String pdcity) {
    	this.pdcity = pdcity;
    }
    
    /**
	 * <b>Summary:获取地址市</b>
	 * getPdcity
	 * @return String
	 */
    public String getPdcity() {
    	return pdcity;
    }
	/**
	 * <b>Summary:设置条码</b>
	 * setPdcode
	 * @param pdcode
	 */
    public void setPdcode(String pdcode) {
    	this.pdcode = pdcode;
    }
    
    /**
	 * <b>Summary:获取条码</b>
	 * getPdcode
	 * @return String
	 */
    public String getPdcode() {
    	return pdcode;
    }
	/**
	 * <b>Summary:设置商品说明书</b>
	 * setPdcontent
	 * @param pdcontent
	 */
    public void setPdcontent(String pdcontent) {
    	this.pdcontent = pdcontent;
    }
    
    /**
	 * <b>Summary:获取商品说明书</b>
	 * getPdcontent
	 * @return String
	 */
    public String getPdcontent() {
    	return pdcontent;
    }
	/**
	 * <b>Summary:设置进价</b>
	 * setPdcostprice
	 * @param pdcostprice
	 */
    public void setPdcostprice(Double pdcostprice) {
    	this.pdcostprice = pdcostprice;
    }
    
    /**
	 * <b>Summary:获取进价</b>
	 * getPdcostprice
	 * @return Double
	 */
    public Double getPdcostprice() {
    	return pdcostprice;
    }
	/**
	 * <b>Summary:设置商品图片</b>
	 * setPdimgurl
	 * @param pdimgurl
	 */
    public void setPdimgurl(String pdimgurl) {
    	this.pdimgurl = pdimgurl;
    }
    
    /**
	 * <b>Summary:获取商品图片</b>
	 * getPdimgurl
	 * @return String
	 */
    public String getPdimgurl() {
    	return pdimgurl;
    }
	/**
	 * <b>Summary:设置商品名称</b>
	 * setPdname
	 * @param pdname
	 */
    public void setPdname(String pdname) {
    	this.pdname = pdname;
    }
    
    /**
	 * <b>Summary:获取商品名称</b>
	 * getPdname
	 * @return String
	 */
    public String getPdname() {
    	return pdname;
    }
	/**
	 * <b>Summary:设置产地</b>
	 * setPdproductionplace
	 * @param pdproductionplace
	 */
    public void setPdproductionplace(String pdproductionplace) {
    	this.pdproductionplace = pdproductionplace;
    }
    
    /**
	 * <b>Summary:获取产地</b>
	 * getPdproductionplace
	 * @return String
	 */
    public String getPdproductionplace() {
    	return pdproductionplace;
    }
	/**
	 * <b>Summary:设置地址省</b>
	 * setPdprovince
	 * @param pdprovince
	 */
    public void setPdprovince(String pdprovince) {
    	this.pdprovince = pdprovince;
    }
    
    /**
	 * <b>Summary:获取地址省</b>
	 * getPdprovince
	 * @return String
	 */
    public String getPdprovince() {
    	return pdprovince;
    }
	/**
	 * <b>Summary:设置保质期</b>
	 * setPdqualityperiod
	 * @param pdqualityperiod
	 */
    public void setPdqualityperiod(String pdqualityperiod) {
    	this.pdqualityperiod = pdqualityperiod;
    }
    
    /**
	 * <b>Summary:获取保质期</b>
	 * getPdqualityperiod
	 * @return String
	 */
    public String getPdqualityperiod() {
    	return pdqualityperiod;
    }
	/**
	 * <b>Summary:设置商品零售价</b>
	 * setPdretailprice
	 * @param pdretailprice
	 */
    public void setPdretailprice(Double pdretailprice) {
    	this.pdretailprice = pdretailprice;
    }
    
    /**
	 * <b>Summary:获取商品零售价</b>
	 * getPdretailprice
	 * @return Double
	 */
    public Double getPdretailprice() {
    	return pdretailprice;
    }
	/**
	 * <b>Summary:设置商品规格</b>
	 * setPdspecification
	 * @param pdspecification
	 */
    public void setPdspecification(String pdspecification) {
    	this.pdspecification = pdspecification;
    }
    
    /**
	 * <b>Summary:获取商品规格</b>
	 * getPdspecification
	 * @return String
	 */
    public String getPdspecification() {
    	return pdspecification;
    }
	/**
	 * <b>Summary:设置名称首拼码</b>
	 * setPdspell
	 * @param pdspell
	 */
    public void setPdspell(String pdspell) {
    	this.pdspell = pdspell;
    }
    
    /**
	 * <b>Summary:获取名称首拼码</b>
	 * getPdspell
	 * @return String
	 */
    public String getPdspell() {
    	return pdspell;
    }
	/**
	 * <b>Summary:设置销售状态</b>
	 * setPdstatus
	 * @param pdstatus
	 */
    public void setPdstatus(String pdstatus) {
    	this.pdstatus = pdstatus;
    }
    
    /**
	 * <b>Summary:获取销售状态</b>
	 * getPdstatus
	 * @return String
	 */
    public String getPdstatus() {
    	return pdstatus;
    }
	/**
	 * <b>Summary:设置商品储存模式</b>
	 * setPdstoragemode
	 * @param pdstoragemode
	 */
    public void setPdstoragemode(String pdstoragemode) {
    	this.pdstoragemode = pdstoragemode;
    }
    
    /**
	 * <b>Summary:获取商品储存模式</b>
	 * getPdstoragemode
	 * @return String
	 */
    public String getPdstoragemode() {
    	return pdstoragemode;
    }
	/**
	 * <b>Summary:设置商品本地类型</b>
	 * setPdtype
	 * @param pdtype
	 */
    public void setPdtype(Integer pdtype) {
    	this.pdtype = pdtype;
    }
    
    /**
	 * <b>Summary:获取商品本地类型</b>
	 * getPdtype
	 * @return Integer
	 */
    public Integer getPdtype() {
    	return pdtype;
    }
	/**
	 * <b>Summary:设置默认是0 私有  通过审核是1 公有</b>
	 * setPstatus
	 * @param pstatus
	 */
    public void setPstatus(Integer pstatus) {
    	this.pstatus = pstatus;
    }
    
    /**
	 * <b>Summary:获取默认是0 私有  通过审核是1 公有</b>
	 * getPstatus
	 * @return Integer
	 */
    public Integer getPstatus() {
    	return pstatus;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPradultlimit
	 * @param pradultlimit
	 */
    public void setPradultlimit(String pradultlimit) {
    	this.pradultlimit = pradultlimit;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPradultlimit
	 * @return String
	 */
    public String getPradultlimit() {
    	return pradultlimit;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPraudit
	 * @param praudit
	 */
    public void setPraudit(String praudit) {
    	this.praudit = praudit;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPraudit
	 * @return String
	 */
    public String getPraudit() {
    	return praudit;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPrdiscount
	 * @param prdiscount
	 */
    public void setPrdiscount(String prdiscount) {
    	this.prdiscount = prdiscount;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPrdiscount
	 * @return String
	 */
    public String getPrdiscount() {
    	return prdiscount;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPrmemberprice
	 * @param prmemberprice
	 */
    public void setPrmemberprice(String prmemberprice) {
    	this.prmemberprice = prmemberprice;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPrmemberprice
	 * @return String
	 */
    public String getPrmemberprice() {
    	return prmemberprice;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPrpack
	 * @param prpack
	 */
    public void setPrpack(String prpack) {
    	this.prpack = prpack;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPrpack
	 * @return String
	 */
    public String getPrpack() {
    	return prpack;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPrpromotionprice
	 * @param prpromotionprice
	 */
    public void setPrpromotionprice(String prpromotionprice) {
    	this.prpromotionprice = prpromotionprice;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPrpromotionprice
	 * @return String
	 */
    public String getPrpromotionprice() {
    	return prpromotionprice;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPrtaxrate
	 * @param prtaxrate
	 */
    public void setPrtaxrate(String prtaxrate) {
    	this.prtaxrate = prtaxrate;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPrtaxrate
	 * @return String
	 */
    public String getPrtaxrate() {
    	return prtaxrate;
    }

	public String getPdcitype_name() {
		return pdcitype_name;
	}

	public String getManufacturers_name() {
		return manufacturers_name;
	}

	public void setManufacturers_name(String manufacturers_name) {
		this.manufacturers_name = manufacturers_name;
	}

	public void setPdcitype_name(String pdcitype_name) {
		this.pdcitype_name = pdcitype_name;
	}

	public String getPdbrand_name() {
		return pdbrand_name;
	}

	public void setPdbrand_name(String pdbrand_name) {
		this.pdbrand_name = pdbrand_name;
	}

	public String getDeleteFileId() {
		return deleteFileId;
	}

	public void setDeleteFileId(String deleteFileId) {
		this.deleteFileId = deleteFileId;
	}

	public String getSlot_pid() {
		return slot_pid;
	}

	public void setSlot_pid(String slot_pid) {
		this.slot_pid = slot_pid;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

}