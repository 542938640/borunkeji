package com.taro.controller.advert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.ConstraintViolation;
import com.taro.common.DataSetObject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.taro.exception.BusinessException;
import com.taro.constants.Constant;
import java.util.Set;
import com.taro.controller.AbstractController;
import com.taro.entity.advert.AdvertEndEntity;
import com.taro.service.advert.AdvertEndService;

/**
 *<p>Title:AdvertEndController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-04 10:14:01
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/advertEnd")
public class AdvertEndController extends AbstractController<AdvertEndEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "AdvertEndEntity";
	
	@Autowired
	private AdvertEndService advertEndService;
	
	@Override
    protected AdvertEndService getService () {
        return advertEndService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveAdvertEnd",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveAdvertEnd(@RequestBody AdvertEndEntity model) {
		
		Set<ConstraintViolation<AdvertEndEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveAdvertEnd(model);
		return new DataSetObject<AdvertEndEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getByDeviceDid/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getByDeviceDid(@PathVariable("id") String id) {
		getBefore(id);
		
		AdvertEndEntity model=getService().getByDeviceDid(id);
		return new DataSetObject<AdvertEndEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveUseWait",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveUseWait(@RequestBody AdvertEndEntity model) {
		getService().saveUseWait(model);
		return new DataSetObject<AdvertEndEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getUseWaitByDeviceDid/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getUseWaitByDeviceDid(@PathVariable("id") String id) {
		getBefore(id);
		AdvertEndEntity model=getService().getUseWaitByDeviceDid(id);
		return new DataSetObject<AdvertEndEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
}