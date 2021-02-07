package com.taro.controller.device;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.device.TSlotInfoEntity;
import com.taro.exception.BusinessException;
import com.taro.service.device.TSlotInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:TSlotInfoController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:18:59
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/tSlotInfo")
public class TSlotInfoController extends AbstractController<TSlotInfoEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "TSlotInfoEntity";
	
	@Autowired
	private TSlotInfoService tSlotInfoService;
	
	@Override
    protected TSlotInfoService getService () {
        return tSlotInfoService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getDeviceSlot/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getDeviceSlot(@PathVariable("id") String id) {
		getBefore(id);
		
		TSlotInfoEntity model = getService().getDeviceSlot(id);
		return new DataSetObject<TSlotInfoEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "保存设备货道", notes = "保存设备货道")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceSlot",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceSlot(HttpServletRequest request, @RequestBody TSlotInfoEntity model) {
		getService().saveDeviceSlot(model);
		return new DataSetObject<TSlotInfoEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveTSlotInfo",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveTSlotInfo(@RequestBody TSlotInfoEntity model) {
		
		Set<ConstraintViolation<TSlotInfoEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveTSlotInfo(model);
		return new DataSetObject<TSlotInfoEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "更新货道商品", notes = "更新货道商品")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam (name = "model", value = "更新修改实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/updateProduct",produces = Constant.JSON_UTF_8, consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String updateProduct(@RequestBody TSlotInfoEntity model) {
		int updateRowCount=getService().updateProduct(model);
		return new Message(updateRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"更新"+updateRowCount+"条记录").toJson();
	}

	@ApiOperation(value = "更新货道容量", notes = "更新货道容量")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam (name = "model", value = "更新修改实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/updateCapacity",produces = Constant.JSON_UTF_8, consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String updateCapacity(@RequestBody TSlotInfoEntity model) {
		int updateRowCount=getService().updateCapacity(model);
		return new Message(updateRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"更新"+updateRowCount+"条记录").toJson();
	}

	@ApiOperation(value = "更新商品销售价格", notes = "更新商品销售价格")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam (name = "model", value = "更新修改实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/updatePrice",produces = Constant.JSON_UTF_8, consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String updateStock(@RequestBody TSlotInfoEntity model) {
		int updateRowCount=getService().updatePrice(model);
		return new Message(updateRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"更新"+updateRowCount+"条记录").toJson();
	}
}