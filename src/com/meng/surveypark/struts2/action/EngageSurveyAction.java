package com.meng.surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.datasource.SurveyparkToken;
import com.meng.surveypark.model.Answer;
import com.meng.surveypark.model.Page;
import com.meng.surveypark.model.Survey;
import com.meng.surveypark.service.SurveyService;
import com.meng.surveypark.util.StringUtil;
import com.meng.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware, SessionAware, ParameterAware{

	private static final long serialVersionUID = -1232717785135931924L;
	
	private static final String CURRENT_SURVEY = "currentSurvey";
	
	private static final String ALL_PARAM_MAP = "allParamMap";
	
	//接收SurveyService
	@Resource
	private SurveyService surveyService;
	
	//接收ServletContext
	private ServletContext sc;
	
	private List<Survey> surveys;

	//接收sid
	private Integer sid;
	
	//当前页面
	private Page currentPage;
	
	//当前页面的ID
	private Integer currentPid;

	//接收sessionMap
	private Map<String, Object> sessionMap;

	//接收所有参数的Map
	private Map<String, String[]> paramMap;
	
	//到达参与调查列表页面
	public String findAllAvailableSurveys()
	{
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	
	//首次进入参与调查
	public String entry()
	{
		this.currentPage = surveyService.getFirstPage(sid);
		//存放survey到session中
		sessionMap.put(CURRENT_SURVEY, currentPage.getSurvey());
		sessionMap.put(ALL_PARAM_MAP, new HashMap<Integer, Map<String, String[]>>());
		return "engageSurveyPage";
	}
	
	//执行调查
	public String doEngageSurvey()
	{
		String submitName = this.getSubmitName();
		//防止用户直接输入地址，submitName为空，所以需检验
		if(!ValidateUtil.isValidate(submitName))
		{
			this.currentPage = surveyService.getFirstPage(sid);
			//存放survey到session中
			sessionMap.put(CURRENT_SURVEY, currentPage.getSurvey());
			sessionMap.put(ALL_PARAM_MAP, new HashMap<Integer, Map<String, String[]>>());
			return "engageSurveyPage";
		}
		else
		{
			//上一步
			if(submitName.endsWith("pre"))
			{
				//合并参数
				this.mergeParamIntoSession();
				this.currentPage = surveyService.getPrePage(this.currentPid);
				return "engageSurveyPage";
			}
			//下一步
			if(submitName.endsWith("next"))
			{
				this.mergeParamIntoSession();
				this.currentPage = surveyService.getNextPage(this.currentPid);
				return "engageSurveyPage";
			}
			//完成
			if(submitName.endsWith("done"))
			{
				this.mergeParamIntoSession();
				
				//绑定令牌
				SurveyparkToken token = new SurveyparkToken();
				token.setSurvey(getCurrentSurvey());
				SurveyparkToken.bindToken(token);
				
				//答案入库
				this.surveyService.saveAnswers(this.processAnswers());
				//清空session的数据
				this.clearSessionData();
				return "engageSurveyAction";
			}
			//退出
			if(submitName.endsWith("exit"))
			{
				this.clearSessionData();
				return "engageSurveyAction";
			}
		}
		return null;
	}
	
	//答案入库
	private List<Answer> processAnswers() {
		Map<Integer, String> matrixRadioMap = new HashMap<>();
		List<Answer> answers = new ArrayList<>();
		Answer a = null;
		String key = null;
		String[] value = null;
		Map<Integer, Map<String, String[]>> map = this.getAllParamMap();
		for(Map<String, String[]> m:map.values()) 
		{
			for(Entry<String, String[]> entry:m.entrySet())
			{
				key = entry.getKey();
				value = entry.getValue();
				//处理“q”开头的key
				if(key.startsWith("q"))
				{
					//处理常规参数
					if(!key.contains("other") && !key.contains("_"))
					{
						a = new Answer();
						a.setAnswerId(StringUtil.strArr(value));
						a.setQuestionId(this.getQid(key));
						a.setSurveyId(this.getCurrentSurvey().getId());
						
						a.setOtherAnswer(StringUtil.strArr(m.get(key + "other")));
						answers.add(a);
					}
					else if(key.contains("_"))
					{
						Integer radioQid = this.getMatrixRadioQid(key);
						String oldValue = matrixRadioMap.get(radioQid);
						if(oldValue==null)
						{
							matrixRadioMap.put(radioQid, StringUtil.strArr(value));
						}
						else
						{
							matrixRadioMap.put(radioQid, oldValue + "," + StringUtil.strArr(value)); 
						}
					}
				}
			}
		}
		//单独处理矩阵式单选按钮
		this.processMatrixRadioMap(matrixRadioMap,answers);
		return answers;
	}

	//处理矩阵式单选按钮
	private void processMatrixRadioMap(Map<Integer, String> map,
			List<Answer> answers) {
		Integer key = null;
		String value = null;
		Answer a  = null;
		for(Entry<Integer, String> entry: map.entrySet())
		{
			key = entry.getKey();
			value = entry.getValue();
			a = new Answer();
			a.setAnswerId(value);
			a.setQuestionId(key);
			a.setSurveyId(this.getCurrentSurvey().getId());
			a.setOtherAnswer("");
			answers.add(a);
		}
	}

	//获得矩阵式单选按钮问题的id：q7_1-->7
	private Integer getMatrixRadioQid(String key) {
		return Integer.parseInt(key.substring(1, key.lastIndexOf("_")));
	}

	//获取当前的调查对象
	private Survey getCurrentSurvey() {
		return (Survey) sessionMap.get(CURRENT_SURVEY);
	}

	//得到问题的ID：q7-->7
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}

	//清除session的数据
	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAM_MAP);
	}

	//合并参数到session中
	private void mergeParamIntoSession() {
		Map<Integer, Map<String, String[]>> allParamMap = this.getAllParamMap();
		allParamMap.put(currentPid, paramMap);
	}

	//得到session存放的所有参数的Map
	private Map<Integer, Map<String, String[]>> getAllParamMap() {
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAM_MAP);
	}

	//获取submit的名称
	private String getSubmitName() {
		for(String key:paramMap.keySet())
		{
			if(key.startsWith("submit_"))
				return key;
		}
		return null;
	}
	
	//用于radio,checkbox,select答案的回显
	public String setTag(String name, String value, String tag)
	{
		Map<String,String[]> map = this.getAllParamMap().get(currentPage.getId());
		String[] values = map.get(name);
		if(StringUtil.contains(values, value))
			return tag;
		return "";
	}
	
	//用于text答案的回显
	public String setText(String name)
	{
		Map<String,String[]> map = this.getAllParamMap().get(currentPage.getId());
		String[] values = map.get(name);
		return "value='"+values[0]+"'";
	}

	//获得图片的URL地址
	public String getImageUrl(String path)
	{
		if(ValidateUtil.isValidate(path))
		{
			String realPath = sc.getRealPath(path);
			File file = new File(realPath);
			if(file.exists())
				return sc.getContextPath() + path;
		}
		return sc.getContextPath() + "/question.jpg";
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	//注入ServletContext
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getCurrentPid() {
		return currentPid;
	}

	public void setCurrentPid(Integer currentPid) {
		this.currentPid = currentPid;
	}
	
	//注入sessionMap
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
	//注入paramMap
	public void setParameters(Map<String, String[]> arg0) {
		this.paramMap = arg0;
	}

}
