package com.taro.entity;

import java.io.Serializable;

public class TreeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;			//树id
	private String name;		//树name 一般是显示出来的看得到的树节点名称 比如用户的真实姓名
	private String logic_name;	//树节点逻辑名  一般是看不到的树节点代码，比如 用户的登录名
	private String pId;	//父id
	
	private String checked;		//是否选择
	
	private String other1;		//备用字段1
	private String other2;		//备用字段2
	private String other3;		//备用字段3
	private String other4;     //备用字段4
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogic_name() {
		return logic_name;
	}
	public void setLogic_name(String logic_name) {
		this.logic_name = logic_name;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getOther2() {
		return other2;
	}
	public void setOther2(String other2) {
		this.other2 = other2;
	}
	public String getOther3() {
		return other3;
	}
	public void setOther3(String other3) {
		this.other3 = other3;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getOther4() {
		return other4;
	}
	public void setOther4(String other4) {
		this.other4 = other4;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checked == null) ? 0 : checked.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logic_name == null) ? 0 : logic_name.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((other1 == null) ? 0 : other1.hashCode());
		result = prime * result + ((other2 == null) ? 0 : other2.hashCode());
		result = prime * result + ((other3 == null) ? 0 : other3.hashCode());
		result = prime * result + ((other4 == null) ? 0 : other4.hashCode());
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeBean other = (TreeBean) obj;
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logic_name == null) {
			if (other.logic_name != null)
				return false;
		} else if (!logic_name.equals(other.logic_name))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (other1 == null) {
			if (other.other1 != null)
				return false;
		} else if (!other1.equals(other.other1))
			return false;
		if (other2 == null) {
			if (other.other2 != null)
				return false;
		} else if (!other2.equals(other.other2))
			return false;
		if (other3 == null) {
			if (other.other3 != null)
				return false;
		} else if (!other3.equals(other.other3))
			return false;
		if (other4 == null) {
			if (other.other4 != null)
				return false;
		} else if (!other4.equals(other.other4))
			return false;
		if (pId == null) {
			if (other.pId != null)
				return false;
		} else if (!pId.equals(other.pId))
			return false;
		return true;
	}
}
