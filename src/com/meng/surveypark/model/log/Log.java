package com.meng.surveypark.model.log;

import java.util.Date;

import com.meng.surveypark.model.BaseEntity;

public class Log {

	//uuid作为主键，避免连接两个表时有相同的id，造成表显示不全
	private String id;						
	
	private String operator;				//操作人
	private String operateName;				//操作名称，方法名
	private String operateParams;			//操作参数
	private String operateResult;			//操作结果，成功还是失败
	private String resultMsg;				//结果消息
	private Date operateTime = new Date();	//操作时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateParams() {
		return operateParams;
	}
	public void setOperateParams(String operateParams) {
		this.operateParams = operateParams;
	}
	public String getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	

}
