package com.taro.service.finance;import com.taro.entity.finance.FinanceAuditEntity;import com.taro.service.IService;/** *<p>Title:FinanceAuditService.java</p> *<p>Description:财务核销 Service</p> *@author 张宇唯 *@version 1.0 *@Automatically generate by Coder in 2021-01-28 14:44:32 */public interface FinanceAuditService extends IService<FinanceAuditEntity> {		FinanceAuditEntity saveFinanceAudit(FinanceAuditEntity model);	FinanceAuditEntity getFinanceAudit(String id);	}