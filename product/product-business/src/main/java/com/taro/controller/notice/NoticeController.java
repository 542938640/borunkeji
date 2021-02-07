package com.taro.controller.notice;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.notice.NoticeEntity;
import com.taro.exception.BusinessException;
import com.taro.service.notice.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:NoticeController.java</p>
 *<p>Description:信息发布 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:16:01
 */
@Api(tags = "信息发布")
@RestController
@RequestMapping(value="/notice")
public class NoticeController extends AbstractController<NoticeEntity> {

	public static final String MODULE = "信息发布";

	public static final String ENTITY = "NoticeEntity";
	
	@Autowired
	private NoticeService noticeService;
	
	@Override
    protected NoticeService getService () {
        return noticeService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取首页列表数据", notes = "获取首页列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "page", value = "分页参数(页数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "rows", value = "分页参数(每页个条数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listHome",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listHome(@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<NoticeEntity> result=getService().listHome(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveNotice",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveNotice(@RequestBody NoticeEntity model) {
		
		Set<ConstraintViolation<NoticeEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveNotice(model);
		return new DataSetObject<NoticeEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "发布通知", notes = "发布通知")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/publish/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String publish(@PathVariable("id") String id) {
		getBefore(id);
		
		NoticeEntity model=getService().publish(id);
		return new DataSetObject<NoticeEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "根据实例id获取流程详情", notes = "根据实例id获取流程详情")
	@ApiImplicitParam(name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/getNotice", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getNotice(@RequestParam String id) {
		getBefore(id);
		
		NoticeEntity model=getService().getNotice(id);
		return new DataSetObject<NoticeEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "根据实例ids删除多个实例", notes = "根据实例ids删除多个实例")
	@ApiImplicitParam(name = "ids", value = "实例ids", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/deleteNotice",method = RequestMethod.GET)
	public String deleteNotice(@RequestParam String ids) {
		deleteBefore(ids);
		int deleteRowCount = getService().deleteNotice(ids);
		return new Message(deleteRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"删除"+deleteRowCount+"条记录").toJson();
	}
	
}