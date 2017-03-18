package com.meng.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.Page;
import com.meng.surveypark.model.Survey;
import com.meng.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page> {

	private static final long serialVersionUID = -8072262237878189569L;

	//注入surveyService
	@Resource
	private SurveyService surveyService;
	
	//接收sid
	private Integer sid;
	
	//接收pid
	private Integer pid;
	
	//增加页
	public String toAddPage()
	{
		return "addPagePage";
	}
	
	//编辑页
	public String editPage()
	{
		this.model = surveyService.getPage(pid);
		return "editPagePage";
	}
	
	//更新/保存页
	public String saveOrUpdatePage()
	{
		Survey survey = new Survey();
		survey.setId(sid);
		//维护关联关系
		model.setSurvey(survey);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}
	
	//删除页
	public String deletePage()
	{
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
}
