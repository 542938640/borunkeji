package com.taro.entity.pub;

import java.io.Serializable;
import java.math.BigDecimal;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PubDicEntity.java</p>
 *<p>Description:基础数据实例 Entity(对应表名:t_pub_dic)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public class PubDicEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String code;									//编码
    private String name;									//名称
    private String base_pid;								//基类ID
    private Integer order;									//排序
    private String value1;									//
    private String value2;									//
    private String value3;									//
    private String value4;									//
    private String value5;									//
    private String other_pid1;								//
    private String other_pid2;								//
    private String other_pid3;								//
    private String other_pid4;								//
    private String other_pid5;								//
    private String other_name1;								//
    private String other_name2;								//
    private String other_name3;								//
    private String other_name4;								//
    private String other_name5;								//
    private BigDecimal double1;								//
    private BigDecimal double2;								//
    private BigDecimal double3;								//
    private BigDecimal double4;								//
    private BigDecimal double5;								//
    private Integer num1;									//
    private Integer num2;									//
    private Integer num3;									//
    private Integer num4;									//
    private Integer num5;									//

    private String structure_pid;							//结构id
    private String structure_relation_pid;					//结构关系id
    private String parentdic_pid;							//父节点id
    
	public String getStructure_pid() {
		return structure_pid;
	}
	public void setStructure_pid(String structure_pid) {
		this.structure_pid = structure_pid;
	}
	public String getParentdic_pid() {
		return parentdic_pid;
	}
	public void setParentdic_pid(String parentdic_pid) {
		this.parentdic_pid = parentdic_pid;
	}
	public String getStructure_relation_pid() {
		return structure_relation_pid;
	}
	public void setStructure_relation_pid(String structure_relation_pid) {
		this.structure_relation_pid = structure_relation_pid;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBase_pid() {
		return base_pid;
	}
	public void setBase_pid(String base_pid) {
		this.base_pid = base_pid;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getValue5() {
		return value5;
	}
	public void setValue5(String value5) {
		this.value5 = value5;
	}
	public String getOther_pid1() {
		return other_pid1;
	}
	public void setOther_pid1(String other_pid1) {
		this.other_pid1 = other_pid1;
	}
	public String getOther_pid2() {
		return other_pid2;
	}
	public void setOther_pid2(String other_pid2) {
		this.other_pid2 = other_pid2;
	}
	public String getOther_pid3() {
		return other_pid3;
	}
	public void setOther_pid3(String other_pid3) {
		this.other_pid3 = other_pid3;
	}
	public String getOther_pid4() {
		return other_pid4;
	}
	public void setOther_pid4(String other_pid4) {
		this.other_pid4 = other_pid4;
	}
	public String getOther_pid5() {
		return other_pid5;
	}
	public void setOther_pid5(String other_pid5) {
		this.other_pid5 = other_pid5;
	}
	public String getOther_name1() {
		return other_name1;
	}
	public void setOther_name1(String other_name1) {
		this.other_name1 = other_name1;
	}
	public String getOther_name2() {
		return other_name2;
	}
	public void setOther_name2(String other_name2) {
		this.other_name2 = other_name2;
	}
	public String getOther_name3() {
		return other_name3;
	}
	public void setOther_name3(String other_name3) {
		this.other_name3 = other_name3;
	}
	public String getOther_name4() {
		return other_name4;
	}
	public void setOther_name4(String other_name4) {
		this.other_name4 = other_name4;
	}
	public String getOther_name5() {
		return other_name5;
	}
	public void setOther_name5(String other_name5) {
		this.other_name5 = other_name5;
	}
	public BigDecimal getDouble1() {
		return double1;
	}
	public void setDouble1(BigDecimal double1) {
		this.double1 = double1;
	}
	public BigDecimal getDouble2() {
		return double2;
	}
	public void setDouble2(BigDecimal double2) {
		this.double2 = double2;
	}
	public BigDecimal getDouble3() {
		return double3;
	}
	public void setDouble3(BigDecimal double3) {
		this.double3 = double3;
	}
	public BigDecimal getDouble4() {
		return double4;
	}
	public void setDouble4(BigDecimal double4) {
		this.double4 = double4;
	}
	public BigDecimal getDouble5() {
		return double5;
	}
	public void setDouble5(BigDecimal double5) {
		this.double5 = double5;
	}
	public Integer getNum1() {
		return num1;
	}
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}
	public Integer getNum2() {
		return num2;
	}
	public void setNum2(Integer num2) {
		this.num2 = num2;
	}
	public Integer getNum3() {
		return num3;
	}
	public void setNum3(Integer num3) {
		this.num3 = num3;
	}
	public Integer getNum4() {
		return num4;
	}
	public void setNum4(Integer num4) {
		this.num4 = num4;
	}
	public Integer getNum5() {
		return num5;
	}
	public void setNum5(Integer num5) {
		this.num5 = num5;
	}

}