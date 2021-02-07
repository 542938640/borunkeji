package com.taro.controller.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import com.taro.common.DataSetObject;
import com.taro.common.Page;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taro.exception.BusinessException;
import com.taro.constants.Constant;

import java.util.Map;
import java.util.Set;
import com.taro.controller.AbstractController;
import com.taro.entity.device.DeviceOutletsEntity;
import com.taro.service.device.DeviceOutletsService;

/**
 *<p>Title:DeviceOutletsController.java</p>
 *<p>Description:设备网点信息 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:18
 */
@Api(tags = "设备网点信息")
@RestController
@RequestMapping(value="/deviceOutlets")
public class DeviceOutletsController extends AbstractController<DeviceOutletsEntity> {

	public static final String MODULE = "设备网点信息";

	public static final String ENTITY = "DeviceOutletsEntity";
	
	@Autowired
	private DeviceOutletsService deviceOutletsService;
	
	@Override
    protected DeviceOutletsService getService () {
        return deviceOutletsService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "page", value = "分页参数(页数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "rows", value = "分页参数(每页个条数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listDeviceOutlets",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listDeviceOutlets(HttpServletRequest request, 
			@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<DeviceOutletsEntity> result=getService().list(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceOutlets",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceOutlets(HttpServletRequest request, @RequestBody DeviceOutletsEntity model) {
		
		Set<ConstraintViolation<DeviceOutletsEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveDeviceOutlets(model);
		return new DataSetObject<DeviceOutletsEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}