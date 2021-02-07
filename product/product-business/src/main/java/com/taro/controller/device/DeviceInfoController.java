package com.taro.controller.device;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSetObject;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.device.DeviceInfoEntity;
import com.taro.exception.BusinessException;
import com.taro.service.device.DeviceInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:DeviceInfoController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-17 11:47:03
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/deviceInfo")
public class DeviceInfoController extends AbstractController<DeviceInfoEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "DeviceInfoEntity";
	
	@Autowired
	private DeviceInfoService deviceInfoService;
	
	@Override
    protected DeviceInfoService getService () {
        return deviceInfoService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listHomeNum",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listHomeNum(HttpServletRequest request, @RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		DeviceInfoEntity model = getService().listHomeNum(parameter);

		return new DataSetObject<DeviceInfoEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceInfo",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceInfo(@RequestBody DeviceInfoEntity model) {
		
		Set<ConstraintViolation<DeviceInfoEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveDeviceInfo(model);
		return new DataSetObject<DeviceInfoEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}