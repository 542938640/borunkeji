package com.taro.controller.device;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSet;
import com.taro.common.DataSetObject;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.device.DeviceExtEntity;
import com.taro.exception.BusinessException;
import com.taro.service.device.DeviceExtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:DeviceExtController.java</p>
 *<p>Description:设备信息扩展 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:36
 */
@Api(tags = "设备信息扩展")
@RestController
@RequestMapping(value="/deviceExt")
public class DeviceExtController extends AbstractController<DeviceExtEntity> {

	public final static String MODULE = "设备信息扩展";

	public final static String ENTITY = "DeviceExtEntity";
	
	@Autowired
	private DeviceExtService deviceExtService;
	
	@Override
    protected DeviceExtService getService () {
        return deviceExtService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listSelectDevice",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listSelectDevice(HttpServletRequest request, @RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		List<DeviceExtEntity> result = getService().listSelectDevice(parameter);

		return new DataSet<DeviceExtEntity>(result).toJson();
	}
	
	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getDeviceExt/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getDeviceExt(@PathVariable("id") String id) {
		getBefore(id);
		
		DeviceExtEntity model = getService().getDeviceExt(id);
		return new DataSetObject<DeviceExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "page", value = "分页参数(页数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "rows", value = "分页参数(每页个条数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listDevice",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listDevice(HttpServletRequest request, 
			@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<DeviceExtEntity> result=getService().listDevice(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceExt",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceExt(HttpServletRequest request, @RequestBody DeviceExtEntity model) {
		
		Set<ConstraintViolation<DeviceExtEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveDeviceExt(model);
		return new DataSetObject<DeviceExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "分配设备商户", notes = "分配设备商户")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceTenants",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceTenants(HttpServletRequest request, @RequestBody DeviceExtEntity model) {
		getService().saveDeviceTenants(model);
		return new DataSetObject<DeviceExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "绑定设备收款账号", notes = "绑定设备收款账号")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceAccount",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceAccount(HttpServletRequest request, @RequestBody DeviceExtEntity model) {
		getService().saveDeviceAccount(model);
		return new DataSetObject<DeviceExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "设备解绑", notes = "设备解绑")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceUnbundling",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceUnbundling(HttpServletRequest request, @RequestBody DeviceExtEntity model) {
		getService().saveDeviceUnbundling(model);
		return new DataSetObject<DeviceExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "移至回收站", notes = "移至回收站")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceRecycle",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceRecycle(HttpServletRequest request, @RequestBody DeviceExtEntity model) {
		getService().saveDeviceRecycle(model);
		return new DataSetObject<DeviceExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}