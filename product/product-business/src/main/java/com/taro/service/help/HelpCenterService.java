package com.taro.service.help;import java.util.Map;import com.taro.common.Page;import com.taro.entity.help.HelpCenterEntity;import com.taro.service.IService;/** *<p>Title:HelpCenterService.java</p> *<p>Description:帮助中心 Service</p> *@author 张宇唯 *@version 1.0 *@Automatically generate by Coder in 2020-12-11 17:24:11 */public interface HelpCenterService extends IService<HelpCenterEntity> {		HelpCenterEntity saveHelpCenter(HelpCenterEntity model);    Page<HelpCenterEntity> listHelpCenter(int pageNum, int pageSize, Map<String, Object> parameter);    }