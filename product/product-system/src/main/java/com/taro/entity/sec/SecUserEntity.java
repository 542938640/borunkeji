package com.taro.entity.sec;

import java.io.Serializable;
import java.util.Set;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:SecUserEntity.java</p>
 *<p>Description:用户表 Entity(对应表名:t_sec_user)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public class SecUserEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String username;								//用户名
    private String password;								//密码
    private String nickname;								//昵称
    private String sex;										//性别
    private String birthday;								//生日
    private String salt;									//盐
    private String picture;									//照片
    private String phonenum;								//手机号码
    private String email;									//邮箱
    private String status;									//状态
    private String login_status;							//登录状态
    private String c1;										
    private String c2;										
    private String c3;										
    private String c4;										
    private String c5;										
    private String c6;										
    private String c7;										
    private String c8;										
    private String c9;										
    private String c10;										
    private String tenants_pid;								// 租户id
    
    private String deleteFileId;							//删除的照片id

    private String status_name;								//状态
    private String login_status_name;						//登录状态
    
    private Serializable token;								//sessionId
    
    private Set<String> permList;							//权限List
    
    private String old_password;

    private String new_password;

    private String tenants_name;								// 租户名称
    
	public String getDeleteFileId() {
		return deleteFileId;
	}
	public void setDeleteFileId(String deleteFileId) {
		this.deleteFileId = deleteFileId;
	}
	public Set<String> getPermList() {
		return permList;
	}
	public void setPermList(Set<String> permList) {
		this.permList = permList;
	}
	public Serializable getToken() {
		return token;
	}
	public void setToken(Serializable token) {
		this.token = token;
	}
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public String getC4() {
		return c4;
	}
	public void setC4(String c4) {
		this.c4 = c4;
	}
	public String getC5() {
		return c5;
	}
	public void setC5(String c5) {
		this.c5 = c5;
	}
	public String getC6() {
		return c6;
	}
	public void setC6(String c6) {
		this.c6 = c6;
	}
	public String getC7() {
		return c7;
	}
	public void setC7(String c7) {
		this.c7 = c7;
	}
	public String getC8() {
		return c8;
	}
	public void setC8(String c8) {
		this.c8 = c8;
	}
	public String getC9() {
		return c9;
	}
	public void setC9(String c9) {
		this.c9 = c9;
	}
	public String getC10() {
		return c10;
	}
	public void setC10(String c10) {
		this.c10 = c10;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getLogin_status_name() {
		return login_status_name;
	}
	public void setLogin_status_name(String login_status_name) {
		this.login_status_name = login_status_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public String getTenants_pid() {
		return tenants_pid;
	}
	public void setTenants_pid(String tenants_pid) {
		this.tenants_pid = tenants_pid;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public String getTenants_name() {
		return tenants_name;
	}
	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

}