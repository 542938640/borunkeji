package ${config.package_name}.service.${config.module_name};

import ${config.package_name}.entity.${config.module_name}.${config.class_name}Entity;
import ${config.package_name}.service.IService;

/**
 *<p>Title:${config.class_name}Service.java</p>
 *<p>Description:${tableInfo.remarks} Service</p>
 *@author ${config.author}
 *@version 1.0
 *@Automatically generate by Coder in ${config.generator_time}
 */
public interface ${config.class_name}Service extends IService<${config.class_name}Entity> {
	
	${config.class_name}Entity save${config.class_name}(${config.class_name}Entity model);
	
}