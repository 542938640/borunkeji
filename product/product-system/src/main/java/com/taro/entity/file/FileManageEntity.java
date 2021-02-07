package com.taro.entity.file;

import java.io.Serializable;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:FileManageEntity.java</p>
 *<p>Description:文件管理 Entity(对应表名:t_file_manage)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public class FileManageEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String type;							//业务类型
    private String busi_key;						//业务id
    private String busi_type;						//业务文件类型
    private String busi_flag;						//业务是否保存
    private String file_name;						//原文件名称
    private String file_extname;					//文件扩展名
    private String file_savename;					//文件保存在服务器上的名称
    private String file_path;						//文件保存路径
    private String file_size;						//文件大小
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBusi_key() {
		return busi_key;
	}
	public void setBusi_key(String busi_key) {
		this.busi_key = busi_key;
	}
	public String getBusi_type() {
		return busi_type;
	}
	public void setBusi_type(String busi_type) {
		this.busi_type = busi_type;
	}
	public String getBusi_flag() {
		return busi_flag;
	}
	public void setBusi_flag(String busi_flag) {
		this.busi_flag = busi_flag;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_extname() {
		return file_extname;
	}
	public void setFile_extname(String file_extname) {
		this.file_extname = file_extname;
	}
	public String getFile_savename() {
		return file_savename;
	}
	public void setFile_savename(String file_savename) {
		this.file_savename = file_savename;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
}