package com.taro.entity.merchants;import java.io.Serializable;import com.taro.entity.AbstractEntity;public class MerchantsCheckEntity extends AbstractEntity implements Serializable {	private static final long serialVersionUID = 1L;		private String idea;			// 审核结果 0:退回,1:通过		private String idea_message;	// 审核信息	public String getIdea() {		return idea;	}	public void setIdea(String idea) {		this.idea = idea;	}	public String getIdea_message() {		return idea_message;	}	public void setIdea_message(String idea_message) {		this.idea_message = idea_message;	}}