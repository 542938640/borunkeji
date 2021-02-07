package ${config.package_name}.entity.${config.module_name};

import java.io.Serializable;
import ${config.package_name}.entity.AbstractEntity;
<#list tableColumn as field>
	<#if field.field_type == "Date">
import java.sql.Date;
		<#break>
	</#if>
</#list>

/**
 *<p>Title:${config.class_name}Entity.java</p>
 *<p>Description:${tableInfo.remarks} Entity(对应表名:${tableInfo.table_name})</p>
 *@author ${config.author}
 *@version 1.0
 *@Automatically generate by Coder in ${config.generator_time}
 */
public class ${config.class_name}Entity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
<#list tableColumn as field>
	<#if field.field_name != "id"
		&& field.field_name != "remark"
		&& field.field_name != "sys_flag"
		&& field.field_name != "last_modifier"
		&& field.field_name != "last_modified_time"
		&& field.field_name != "creator"
		&& field.field_name != "create_time">
	// ${field.remarks}
    private ${field.field_type} ${field.field_name};
	</#if>
</#list>

<#list tableColumn as field>
	<#if field.field_name != "id"
		&& field.field_name != "remark"
		&& field.field_name != "sys_flag"
		&& field.field_name != "last_modifier"
		&& field.field_name != "last_modified_time"
		&& field.field_name != "creator"
		&& field.field_name != "create_time">
	/**
	 * <b>Summary:设置${field.remarks}</b>
	 * set${field.field_name?cap_first}
	 * @param ${field.field_name}
	 */
    public void set${field.field_name?cap_first}(${field.field_type} ${field.field_name}) {
    	this.${field.field_name} = ${field.field_name};
    }
    
    /**
	 * <b>Summary:获取${field.remarks}</b>
	 * get${field.field_name?cap_first}
	 * @return ${field.field_type}
	 */
    public ${field.field_type} get${field.field_name?cap_first}() {
    	return ${field.field_name};
    }
	</#if>
</#list>

}