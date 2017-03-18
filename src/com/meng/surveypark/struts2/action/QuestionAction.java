package com.meng.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.Page;
import com.meng.surveypark.model.Question;
import com.meng.surveypark.service.SurveyService;

//QuestionAction
@Controller
@Scope("prototype")
class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = -7167079488678228351L;
	
	//接收sid
	private Integer sid;
	
	//接收pid
	private Integer pid;
	
	//接收pid
	private Integer qid;
	
	//接收题型
	private Integer questionType;
	
	@Resource
	private SurveyService surveyService;
	
	//到达选择题型页面
	public String toSelectQuestionType()
	{
		return "selectQuestionTypePage";
	}
	
	//到达增加问题的页面
	public String toDesignQuestionPage()
	{
		return "" + model.getQuestionType();
	}
	
	//保存/更新问题
	public String saveOrUpdateQuestion()
	{
		Page page = new Page();
		page.setId(pid);
		//维护关联关系
		model.setPage(page);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}

	//编辑问题
	public String editQuestion()
	{
		this.model = surveyService.getQuestion(qid);
		return "" + model.getQuestionType();
	}
	
	//删除问题
	public String deleteQuestion()
	{
		surveyService.deleteQuestion(qid);
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

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

}
