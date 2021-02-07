package com.taro.service.finance;

import com.taro.entity.finance.FinanceNoteDetailEntity;
import com.taro.service.IService;

/**
 *<p>Title:FinanceNoteDetailService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-02-05 13:48:14
 */
public interface FinanceNoteDetailService extends IService<FinanceNoteDetailEntity> {
	
	FinanceNoteDetailEntity saveFinanceNoteDetail(FinanceNoteDetailEntity model);

	FinanceNoteDetailEntity saveNum(String tenants_pid, String busi_type);
    
}