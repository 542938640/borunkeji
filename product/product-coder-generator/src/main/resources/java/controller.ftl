package ${config.package_name}.controller.${config.module_name};

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
import ${config.package_name}.controller.AbstractController;
import ${config.package_name}.entity.${config.module_name}.${config.class_name}Entity;
import ${config.package_name}.service.${config.module_name}.${config.class_name}Service;

/**
 *<p>Title:${config.class_name}Controller.java</p>
 *<p>Description:${tableInfo.remarks} Action</p>
 *@author ${config.author}
 *@version 1.0
 *@Automatically generate by Coder in ${config.generator_time}
 */
@Api(tags = "${tableInfo.remarks}")
@RestController
@RequestMapping(value="/${config.class_name?uncap_first}")
public class ${config.class_name}Controller extends AbstractController<${config.class_name}Entity> {

	public final static String MODULE = "${tableInfo.remarks}";

	public final static String ENTITY = "${config.class_name}Entity";
	
	@Autowired
	private ${config.class_name}Service ${config.class_name?uncap_first}Service;
	
	@Override
    protected ${config.class_name}Service getService () {
        return ${config.class_name?uncap_first}Service;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/save${config.class_name}",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String save${config.class_name}(@RequestBody ${config.class_name}Entity model) {
		
		Set<ConstraintViolation<${config.class_name}Entity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().save${config.class_name}(model);
		return new DataSetObject<${config.class_name}Entity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}