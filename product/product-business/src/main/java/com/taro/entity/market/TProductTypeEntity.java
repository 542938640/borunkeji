package com.taro.entity.market;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:TProductTypeEntity.java</p>
 *<p>Description: Entity(对应表名:tproducttype)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-25 13:15:37
 */
public class TProductTypeEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 分类名称
    private String t_fname;
	// 父节点
    private Integer t_fparentid;
	// 创建时间
    private Date t_fcreatedate;
	// 创建用户
    private String t_fcreateuser;
	// 父节点名称
    private String t_fparentname;

    private List<TProductTypeEntity> children;
    
	/**
	 * <b>Summary:设置分类名称</b>
	 * setT_fname
	 * @param t_fname
	 */
    public void setT_fname(String t_fname) {
    	this.t_fname = t_fname;
    }
    
    /**
	 * <b>Summary:获取分类名称</b>
	 * getT_fname
	 * @return String
	 */
    public String getT_fname() {
    	return t_fname;
    }
	/**
	 * <b>Summary:设置父节点</b>
	 * setT_fparentid
	 * @param t_fparentid
	 */
    public void setT_fparentid(Integer t_fparentid) {
    	this.t_fparentid = t_fparentid;
    }
    
    /**
	 * <b>Summary:获取父节点</b>
	 * getT_fparentid
	 * @return Integer
	 */
    public Integer getT_fparentid() {
    	return t_fparentid;
    }
	/**
	 * <b>Summary:设置创建时间</b>
	 * setT_fcreatedate
	 * @param t_fcreatedate
	 */
    public void setT_fcreatedate(Date t_fcreatedate) {
    	this.t_fcreatedate = t_fcreatedate;
    }
    
    /**
	 * <b>Summary:获取创建时间</b>
	 * getT_fcreatedate
	 * @return Date
	 */
    public Date getT_fcreatedate() {
    	return t_fcreatedate;
    }
	/**
	 * <b>Summary:设置创建用户</b>
	 * setT_fcreateuser
	 * @param t_fcreateuser
	 */
    public void setT_fcreateuser(String t_fcreateuser) {
    	this.t_fcreateuser = t_fcreateuser;
    }
    
    /**
	 * <b>Summary:获取创建用户</b>
	 * getT_fcreateuser
	 * @return String
	 */
    public String getT_fcreateuser() {
    	return t_fcreateuser;
    }

	public List<TProductTypeEntity> getChildren() {
		return children;
	}

	public void setChildren(List<TProductTypeEntity> children) {
		this.children = children;
	}

	public String getT_fparentname() {
		return t_fparentname;
	}

	public void setT_fparentname(String t_fparentname) {
		this.t_fparentname = t_fparentname;
	}

}