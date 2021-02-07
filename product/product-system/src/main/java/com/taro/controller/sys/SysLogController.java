package com.taro.controller.sys;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSetObject;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.sys.SysLogEntity;
import com.taro.exception.BusinessException;
import com.taro.service.sys.SysLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:SysLogController.java</p>
 *<p>Description:系统日志 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-12-02 21:24:31
 */
@Api(tags = "系统日志")
@RestController
@RequestMapping(value="/sysLog")
public class SysLogController extends AbstractController<SysLogEntity> {
	
	public final static String MODULE = "系统日志";

	public final static String ENTITY = "SysLogEntity";
	
	@Autowired
	private SysLogService sysLogService;
	
	@Override
    protected SysLogService getService () {
        return sysLogService;
    }

	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveSysLog",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveSysLog(@RequestBody SysLogEntity model) {
		
		Set<ConstraintViolation<SysLogEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveSysLog(model);
		return new DataSetObject<SysLogEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}