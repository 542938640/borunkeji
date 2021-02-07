package com.taro.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.annotation.JSONField;
import com.taro.constants.Constant;
import com.taro.utils.SecurityMyUtils;
import com.taro.utils.UuidUtil;

/**
 * @Package: com.taro.tdevice.entity
 * @File: AbstractEntity.java
 * @Description: TODO
 * @Author: tanquanlong
 * @Date: 2016年12月13日
 * @Copyright taro(c)2010-2016
 */
public abstract class AbstractEntity extends IdEntity {

	private static final long serialVersionUID = 6208752434151625366L;
	// 备注
	protected String remark;
	// 系统状态(1:正常;0:禁用)
	protected String sysFlag;
	// 创建人
	protected String creator;
	//创建人名称
	protected String creator_name;
	// 创建时间
	protected String createTime;
	// 最后修改人
	protected String lastModifier;
	// 最后修改人名称
	protected String lastModifier_name;
	// 最后修改时间
	protected String lastModifiedTime;
	// 当前登录人组织结构路由
	protected String orgRoute;
	// 当前登录人租户id
	protected String tenants_pid;

	//2019-03-05 张宇唯
	protected String task_def;				//环节id
	protected String task_name;				//环节名称
	protected WorkFlowBean workFlowData;	//工作流对象
	
	public String getTask_def() {
		return task_def;
	}
	public void setTask_def(String task_def) {
		this.task_def = task_def;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public WorkFlowBean getWorkFlowData() {
		return workFlowData;
	}
	public void setWorkFlowData(WorkFlowBean workFlowData) {
		this.workFlowData = workFlowData;
	}

	public AbstractEntity() {

	}

	public void reflash(boolean isCreator) {
		String currentDate = DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS);
		this.sysFlag = this.sysFlag == null || "".equals(this.sysFlag) ? Constant.VALUE_SYSFLAG
				: this.sysFlag;
		if (isCreator) {
			if(StringUtils.isBlank(super.id)){	//如果用户id由外部传入，这里不主动赋值 update by Gavin 20170626
				super.id = UuidUtil.get32UUID();
			}
			this.createTime = currentDate;
		}
		this.lastModifiedTime = currentDate;

		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			String userId = SecurityMyUtils.getUserId();
			if (userId != null) {
				if (isCreator) {
					this.creator = userId;
					//路由不在这里插入了 2018-04-03 ZYW
//					if(!StringUtils.isBlank(this.orgRoute)){
//						this.orgRoute = this.orgRoute+","+SecurityMyUtils.getOrgRoute();
//					}else{
//						this.orgRoute = SecurityMyUtils.getOrgRoute();
//					}
				}
				this.lastModifier = userId;
			}
		}
	}

	@JSONField(serialize = false, deserialize = false)
	public Timestamp getLastModifiedTimestamp() {
		if (StringUtils.isBlank(lastModifiedTime)) {
			return new Timestamp(0);
		}
		try {
			Date date = DateUtils.parseDate(lastModifiedTime,
					Constant.DF_DATE_YYYYMMDDHHMMSS);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
		}
		return new Timestamp(0);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getOrgRoute() {
		return orgRoute;
	}

	public void setOrgRoute(String orgRoute) {
		this.orgRoute = orgRoute;
	}


	public String getCreator_name() {
		return this.creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	public String getLastModifier_name() {
		return this.lastModifier_name;
	}

	public void setLastModifier_name(String lastModifier_name) {
		this.lastModifier_name = lastModifier_name;
	}
	public String getTenants_pid() {
		return tenants_pid;
	}
	public void setTenants_pid(String tenants_pid) {
		this.tenants_pid = tenants_pid;
	}
	
}