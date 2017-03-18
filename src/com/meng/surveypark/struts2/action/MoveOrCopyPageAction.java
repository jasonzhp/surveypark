package com.meng.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.Page;
import com.meng.surveypark.model.Survey;
import com.meng.surveypark.model.User;
import com.meng.surveypark.service.SurveyService;
import com.meng.surveypark.struts2.UserAware;

// 移动/复制Action
@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {

	private static final long serialVersionUID = -2816852900378596831L;
	
	@Resource
	private SurveyService surveyService;
	
	//原页id
	private Integer srcPid;
	
	//目标页id
	private Integer targetPid;
	
	//目标调查id
	private Integer sid;
	
	//移动位置：0-之前  1-之后
	private int pos;
	
	private List<Survey> mySurveys;

	//接收user
	private User user;
	
	//到达移动/复制页面
	public String toSelectTargetPage()
	{
		this.setMySurveys(surveyService.getSurveyWithPages(user));
		return "moveOrCopyPageListPage";
	}
	
	//移动/复制页
	public String doMoveOrCopyPage()
	{
		this.surveyService.moveOrCopyPage(srcPid, targetPid, pos);
		return "designSurveyAction";
	}

	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	//注入user
	public void setUser(User user) {
		this.user = user;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public Integer getTargetPid() {
		return targetPid;
	}

	public void setTargetPid(Integer targetPid) {
		this.targetPid = targetPid;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

}
