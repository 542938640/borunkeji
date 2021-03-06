<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- @Automatically generate by Coder in ${config.generator_time} -->
<#assign table_prefix = "T"/>
<mapper namespace="${config.package_name}.dao.${config.module_name}.${config.class_name}Dao">

  	<resultMap id="BaseResultMap" type="${config.package_name}.entity.${config.module_name}.${config.class_name}Entity">
	  	<id column="${tablePrimaryKey}" property="id" jdbcType="VARCHAR" />
  		<result column="${tablePrimaryKey}" property="id" jdbcType="VARCHAR" />
		<#list tableColumn as field>
  		<result column="${field.column_name?upper_case}" property="${field.field_name}" jdbcType="${field.type_name?upper_case}" />
		</#list>
	  	<result column="F_SYS_FLAG" property="sysFlag" jdbcType="VARCHAR" />
	  	<result column="F_REMARK" property="remark" jdbcType="VARCHAR" />
	  	<result column="F_LAST_MODIFIER" property="lastModifier" jdbcType="VARCHAR" />
	  	<result column="F_LAST_MODIFIED_TIME" property="lastModifiedTime" jdbcType="VARCHAR" />
	  	<result column="F_CREATOR" property="creator" jdbcType="VARCHAR" />
	  	<result column="F_CREATE_TIME" property="createTime" jdbcType="VARCHAR" />
  	</resultMap>

  	<sql id="Base_Column_List" >
  		<#assign Base_Column_List = "t.${tablePrimaryKey}, "/>
		<#list tableColumn as field>
  		<#if field_has_next>
			<#if (field_index + 1) % 5 == 0>
				<#assign Base_Column_List = Base_Column_List + table_prefix + "." + field.column_name?upper_case +",\r		"/>
			<#else>
				<#assign Base_Column_List = Base_Column_List + table_prefix + "." + field.column_name?upper_case + ", "/>
			</#if>
		<#else>
			    <#assign Base_Column_List = Base_Column_List + table_prefix + "." + field.column_name?upper_case/>
		</#if>
		</#list>
		${Base_Column_List}, 
		t.F_SYS_FLAG, t.F_REMARK, t.F_LAST_MODIFIER, t.F_LAST_MODIFIED_TIME, t.F_CREATOR, t.F_CREATE_TIME
  	</sql>

  	<sql id="where_sql">
		<if test="id != null and id != ''" > 
			and t.${tablePrimaryKey} = ${"#{"}id, jdbcType=VARCHAR}
		</if>
	<#list tableColumn as field>
		<if test="${field.field_name} != null and ${field.field_name} != ''" > 
			and ${table_prefix}.${field.column_name?upper_case} = ${"#{"}${field.field_name}, jdbcType=${field.type_name?upper_case}}
		</if>
	</#list>
		<if test="sysFlag != null and sysFlag != ''" > 
			and t.F_SYS_FLAG = ${"#{"}sysFlag, jdbcType=VARCHAR}
		</if>
		<if test="remark != null and remark != ''" > 
			and t.F_REMARK = ${"#{"}remark, jdbcType=VARCHAR}
		</if>
		<if test="lastModifier != null and lastModifier != ''" > 
			and t.F_LAST_MODIFIER = ${"#{"}lastModifier, jdbcType=VARCHAR}
		</if>
		<if test="lastModifiedTime != null and lastModifiedTime != ''" > 
			and t.F_LAST_MODIFIED_TIME = ${"#{"}lastModifiedTime, jdbcType=VARCHAR}
		</if>
		<if test="creator != null and creator != ''" > 
			and t.F_CREATOR = ${"#{"}creator, jdbcType=VARCHAR}
		</if>
		<if test="createTime != null and createTime != ''" > 
			and t.F_CREATE_TIME = ${"#{"}createTime, jdbcType=VARCHAR}
		</if>
  	</sql>

	<#assign insert_field = ""/>
	<#assign insert_values = ""/>
	<#assign batchInsert_values = ""/>
	<#list tableColumn as field>
		<#if field_has_next>
			<#if (field_index + 1) % 3 == 0>
				<#assign insert_field = insert_field + field.column_name?upper_case + ",\r			"/>
				<#assign insert_values = insert_values + "#\{" + field.field_name + ", jdbcType="+field.type_name?upper_case+"},\r			"/>
				<#assign batchInsert_values = batchInsert_values + "#\{entity." + field.field_name + ", jdbcType="+field.type_name?upper_case+"},\r			"/>
			<#else>
				<#assign insert_field = insert_field + field.column_name?upper_case + ", "/>
				<#assign insert_values = insert_values + "#\{" + field.field_name+", jdbcType=" + field.type_name?upper_case+"}, "/>
				<#assign batchInsert_values = batchInsert_values + "#\{entity."+field.field_name+", jdbcType=" + field.type_name?upper_case+"}, "/>
			</#if>
		<#else>
			<#assign insert_field = insert_field + field.column_name?upper_case />
			<#assign insert_values = insert_values + "#\{" + field.field_name+",jdbcType=" + field.type_name?upper_case+"} "/>
			<#assign batchInsert_values = batchInsert_values + "#\{entity."+field.field_name+",jdbcType=" + field.type_name?upper_case+"} "/>
		</#if>
	</#list>
	<!-- 新增 -->
  	<insert id="insert" parameterType="${config.package_name}.entity.${config.module_name}.${config.class_name}Entity" >
    	INSERT INTO ${tableInfo.table_name?upper_case} (
    		${tablePrimaryKey}, ${insert_field}, 
		  	F_SYS_FLAG, F_REMARK, F_LAST_MODIFIER,
		  	F_LAST_MODIFIED_TIME, F_CREATOR, F_CREATE_TIME
    	) VALUES (
    		${"#{"}id, jdbcType=VARCHAR}, ${insert_values}, 
		  	${"#{"}sysFlag, jdbcType=VARCHAR}, ${"#{"}remark, jdbcType=VARCHAR}, ${"#{"}lastModifier, jdbcType=VARCHAR},
		  	${"#{"}lastModifiedTime, jdbcType=VARCHAR}, ${"#{"}creator, jdbcType=VARCHAR}, ${"#{"}createTime, jdbcType=VARCHAR} 
    	)
  	</insert>

	<!-- 批量新增 -->
  	<insert id="insertObjects" parameterType="list">
		INSERT INTO ${tableInfo.table_name?upper_case} (
    		${tablePrimaryKey}, ${insert_field}, 
		  	F_SYS_FLAG, F_REMARK, F_LAST_MODIFIER,
		  	F_LAST_MODIFIED_TIME, F_CREATOR, F_CREATE_TIME
		)
		<foreach collection="list" item="entity" index="index" separator="union all">
			SELECT
    		${"#{"}entity.id, jdbcType=VARCHAR}, ${batchInsert_values}, 
  			${"#{"}entity.sysFlag,jdbcType=VARCHAR}, ${"#{"}entity.remark,jdbcType=VARCHAR}, ${"#{"}entity.lastModifier,jdbcType=VARCHAR},
  			${"#{"}entity.lastModifiedTime,jdbcType=VARCHAR}, ${"#{"}entity.creator,jdbcType=VARCHAR}, ${"#{"}entity.createTime,jdbcType=VARCHAR} 
			FROM DUAL
		</foreach>
  	</insert>

	<!-- 更新 -->
  	<update id="update" parameterType="${config.package_name}.entity.${config.module_name}.${config.class_name}Entity">
    	UPDATE ${tableInfo.table_name?upper_case}
	    <set>
      		<if test="id != null" > ${tablePrimaryKey} = ${"#{"}id, jdbcType=VARCHAR}, </if>
		  	<#list tableColumn as field>
      		<if test="${field.field_name} != null" > ${field.column_name?upper_case} = ${"#{"}${field.field_name}, jdbcType=${field.type_name?upper_case}}, </if>
		  	</#list>
	      	<if test="sysFlag != null" > F_SYS_FLAG = ${"#{"}sysFlag, jdbcType=VARCHAR}, </if>
	      	<if test="remark != null" > F_REMARK = ${"#{"}remark, jdbcType=VARCHAR}, </if>
	      	<if test="lastModifier != null" > F_LAST_MODIFIER = ${"#{"}lastModifier, jdbcType=VARCHAR}, </if>
	      	<if test="lastModifiedTime != null" > F_LAST_MODIFIED_TIME = ${"#{"}lastModifiedTime, jdbcType=VARCHAR}, </if>
	      	<if test="creator != null" > F_CREATOR = ${"#{"}creator, jdbcType=VARCHAR}, </if>
	      	<if test="createTime != null" > F_CREATE_TIME = ${"#{"}createTime, jdbcType=VARCHAR}, </if>
	    </set>
    	WHERE ${tablePrimaryKey} = ${"#{"}id, jdbcType=VARCHAR}
  	</update>

	<!-- 批量更新 -->
  	<update id="batchUpdate" parameterType="java.util.List">
		<foreach collection="list" item="entity" separator=";">
			UPDATE ${tableInfo.table_name?upper_case}
			<set>
		      	<if test="id != null" > ${tablePrimaryKey} = ${"#{"}entity.id, jdbcType=VARCHAR}, </if>
			  	<#list tableColumn as field>
	      		<if test="${field.field_name} != null" > ${field.column_name?upper_case} = ${"#{"}entity.${field.field_name}, jdbcType=${field.type_name?upper_case}}, </if>
			  	</#list>
		      	<if test="sysFlag != null" > F_SYS_FLAG = ${"#{"}entity.sysFlag, jdbcType=VARCHAR}, </if>
		      	<if test="remark != null" > F_REMARK = ${"#{"}entity.remark, jdbcType=VARCHAR}, </if>
		      	<if test="lastModifier != null" > F_LAST_MODIFIER = ${"#{"}entity.lastModifier, jdbcType=VARCHAR}, </if>
		      	<if test="lastModifiedTime != null" > F_LAST_MODIFIED_TIME = ${"#{"}entity.lastModifiedTime, jdbcType=VARCHAR}, </if>
		      	<if test="creator != null" > F_CREATOR = ${"#{"}entity.creator, jdbcType=VARCHAR}, </if>
		      	<if test="createTime != null" > F_CREATE_TIME = ${"#{"}entity.createTime, jdbcType=VARCHAR}, </if>
			</set>
    		WHERE ${tablePrimaryKey} = ${"#{"}id, jdbcType=VARCHAR}
		</foreach>
  	</update>

	<!-- 逻辑删除 -->
  	<update id="delete" parameterType="java.lang.String" >
    	UPDATE ${tableInfo.table_name?upper_case} SET
			F_SYS_FLAG = '0',
		    F_LAST_MODIFIER = ${"#{"}lastModifier, jdbcType=VARCHAR},
			F_LAST_MODIFIED_TIME = ${"#{"}lastModifiedTime, jdbcType=VARCHAR}
    	WHERE ${tablePrimaryKey} = ${"#{"}id, jdbcType=VARCHAR}
  	</update>

	<!-- 逻辑删除所有 -->
  	<update id="deleteAll" parameterType="map">
		UPDATE ${tableInfo.table_name?upper_case} SET 
			F_SYS_FLAG = '0',
		    F_LAST_MODIFIER = ${"#{"}lastModifier, jdbcType=VARCHAR},
			F_LAST_MODIFIED_TIME = ${"#{"}lastModifiedTime, jdbcType=VARCHAR}
		WHERE ${tablePrimaryKey} IN
		<foreach item="item" collection="ids" open="(" separator="," close=")">
        	${"#{"}item}
    	</foreach>
  	</update>

	<!-- 查询条件 模糊查询用concat函数:name like concat(concat('%',${"#{"}bean.name,jdbcType=VARCHAR}),'%'),	这样做主要是为了支持mysql和oracle以及防止sql注入 -->
  	<!-- 列表查询 -->
  	<select id="list" resultMap="BaseResultMap" parameterType="map">
	    SELECT 
	    	<include refid="Base_Column_List" />
	    FROM ${tableInfo.table_name?upper_case} ${table_prefix}
	    <where>
			<include refid="where_sql"/>
		</where>
  	</select>

	<!-- 根据ID查询单条记录 -->
  	<select id="get" resultMap="BaseResultMap" parameterType="java.lang.String" >
    	SELECT 
    		<include refid="Base_Column_List" />
    	FROM ${tableInfo.table_name?upper_case} ${table_prefix}
    	WHERE ${table_prefix}.${tablePrimaryKey} = ${"#{"}id,jdbcType=VARCHAR}
  	</select>

</mapper>