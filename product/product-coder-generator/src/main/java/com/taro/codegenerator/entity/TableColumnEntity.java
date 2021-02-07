package com.taro.codegenerator.entity;

public class TableColumnEntity {
	private String column_name;			// 列名称 
	private String type_name;			// 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的 
	private Integer column_size;		// 列的大小。 
	private Integer decimal_digits;		// 小数部分的位数
	private Integer num_prec_radix;		// 基数（通常为 10 或 2）
	private String remarks;				// 描述列的注释（可为 null）
	private String column_def;			// 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null） 
	private String is_nullable;			// ISO 规则用于确定列是否包括 null。
	
	private String field_name;			// 实体对应名称
	private String field_type;			// 实体对应类型
	
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getColumn_size() {
		return column_size;
	}
	public void setColumn_size(Integer column_size) {
		this.column_size = column_size;
	}
	public Integer getDecimal_digits() {
		return decimal_digits;
	}
	public void setDecimal_digits(Integer decimal_digits) {
		this.decimal_digits = decimal_digits;
	}
	public Integer getNum_prec_radix() {
		return num_prec_radix;
	}
	public void setNum_prec_radix(Integer num_prec_radix) {
		this.num_prec_radix = num_prec_radix;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getColumn_def() {
		return column_def;
	}
	public void setColumn_def(String column_def) {
		this.column_def = column_def;
	}
	public String getIs_nullable() {
		return is_nullable;
	}
	public void setIs_nullable(String is_nullable) {
		this.is_nullable = is_nullable;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getField_type() {
		return field_type;
	}
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
}
