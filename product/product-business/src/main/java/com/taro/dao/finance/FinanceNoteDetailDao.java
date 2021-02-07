package com.taro.dao.finance;

import com.taro.dao.AbstractDao;
import com.taro.entity.finance.FinanceNoteDetailEntity;

/**
 *<p>Title:FinanceNoteDetailDao.java</p>
 *<p>Description: Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-02-05 17:05:55
 */
public interface FinanceNoteDetailDao extends AbstractDao<FinanceNoteDetailEntity> {

    int updateNum(FinanceNoteDetailEntity entity);
    
}