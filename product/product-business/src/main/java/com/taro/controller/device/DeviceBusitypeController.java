package com.taro.controller.device;

import java.util.List;
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

import com.taro.common.DataSet;
import com.taro.common.DataSetObject;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.device.DeviceBusitypeEntity;
import com.taro.exception.BusinessException;
import com.taro.service.device.DeviceBusitypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:DeviceBusitypeController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-16 11:26:40
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/app/deviceBusitype")
public class DeviceBusitypeController extends AbstractController<DeviceBusitypeEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "DeviceBusitypeEntity";
	
	@Autowired
	private DeviceBusitypeService deviceBusitypeService;
	
	@Override
    protected DeviceBusitypeService getService () {
        return deviceBusitypeService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listDeviceBusitype",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listDeviceBusitype(HttpServletRequest request, @RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		parameter.put("sysFlag", "1");
		List<DeviceBusitypeEntity> result = getService().list(parameter);

		return new DataSet<DeviceBusitypeEntity>(result).toJson();
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceBusitype",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceBusitype(@RequestBody DeviceBusitypeEntity model) {
		
		Set<ConstraintViolation<DeviceBusitypeEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveDeviceBusitype(model);
		return new DataSetObject<DeviceBusitypeEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}