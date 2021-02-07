package ${config.package_name}.service.${config.module_name}.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import ${config.package_name}.dao.${config.module_name}.${config.class_name}Dao;
import ${config.package_name}.entity.${config.module_name}.${config.class_name}Entity;
import ${config.package_name}.service.AbstractService;
import ${config.package_name}.service.${config.module_name}.${config.class_name}Service;

/**
 *<p>Title:${config.class_name}ServiceImpl.java</p>
 *<p>Description:${tableInfo.remarks} ServiceImpl</p>
 *@author ${config.author}
 *@version 1.0
 *@Automatically generate by Coder in ${config.generator_time}
 */
@Service
public class ${config.class_name}ServiceImpl extends AbstractService<${config.class_name}Entity> implements ${config.class_name}Service {
	
	@Autowired
	private ${config.class_name}Dao ${config.class_name?uncap_first}Dao;
	
    @Override
    protected ${config.class_name}Dao getDao() {
        return ${config.class_name?uncap_first}Dao;
    }
    
    @Override
    public ${config.class_name}Entity save${config.class_name} (${config.class_name}Entity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }
    
}