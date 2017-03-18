package com.meng.surveypark.model.security;

import com.meng.surveypark.model.BaseEntity;

//权限类
public class Right extends BaseEntity {

	private static final long serialVersionUID = 5856983684450064746L;
	
	private Integer id;
	private String rightName = "未命名";
	private String rightUrl;
	private String rightDesc;
	//权限码，1<<n
	private long rightCode;
	//权限位，相当于对权限进行分组，从0开始
	private Integer rightPos;
	
	private boolean common;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getRightUrl() {
		return rightUrl;
	}
	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}
	public String getRightDesc() {
		return rightDesc;
	}
	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}
	public long getRightCode() {
		return rightCode;
	}
	public void setRightCode(long rightCode) {
		this.rightCode = rightCode;
	}
	public Integer getRightPos() {
		return rightPos;
	}
	public void setRightPos(Integer rightPos) {
		this.rightPos = rightPos;
	}
	public boolean isCommon() {
		return common;
	}
	public void setCommon(boolean common) {
		this.common = common;
	}
}
