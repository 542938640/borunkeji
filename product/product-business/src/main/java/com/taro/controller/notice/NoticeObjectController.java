package com.taro.controller.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.ConstraintViolation;
import com.taro.common.DataSetObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.taro.exception.BusinessException;
import com.taro.constants.Constant;
import java.util.Set;
import com.taro.controller.AbstractController;
import com.taro.entity.notice.NoticeObjectEntity;
import com.taro.service.notice.NoticeObjectService;

/**
 *<p>Title:NoticeObjectController.java</p>
 *<p>Description:信息发布对象 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:15:50
 */
@Api(tags = "信息发布对象")
@RestController
@RequestMapping(value="/noticeObject")
public class NoticeObjectController extends AbstractController<NoticeObjectEntity> {

	public final static String MODULE = "信息发布对象";

	public final static String ENTITY = "NoticeObjectEntity";
	
	@Autowired
	private NoticeObjectService noticeObjectService;
	
	@Override
    protected NoticeObjectService getService () {
        return noticeObjectService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveNoticeObject",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveNoticeObject(@RequestBody NoticeObjectEntity model) {
		
		Set<ConstraintViolation<NoticeObjectEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveNoticeObject(model);
		return new DataSetObject<NoticeObjectEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}