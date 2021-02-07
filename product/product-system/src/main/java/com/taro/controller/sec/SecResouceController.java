package com.taro.controller.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.taro.controller.AbstractController;
import com.taro.entity.sec.SecResouceEntity;
import com.taro.service.sec.SecResouceService;

/**
 *<p>Title:SecResouceController.java</p>
 *<p>Description:资源表 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:08
 */
@RestController
@RequestMapping(value="/secresouce")
public class SecResouceController extends AbstractController<SecResouceEntity>{

	public final static String MODULE = "资源";

	public final static String ENTITY = "SecResouceEntity";
	
	@Autowired
	private SecResouceService secResouceService;
	
	@Override
    protected SecResouceService getService () {
        return secResouceService;
    }

	@Override
    public String getModule() {
		return MODULE;
	}

}