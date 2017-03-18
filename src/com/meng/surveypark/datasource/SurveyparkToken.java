package com.meng.surveypark.datasource;

import com.meng.surveypark.model.Survey;

//令牌
public class SurveyparkToken {

	private static ThreadLocal<SurveyparkToken> l = new ThreadLocal<>();
	
	private Survey survey;
	
	//给当前线程绑定指定令牌
	public static void bindToken(SurveyparkToken token)
	{
		l.set(token);
	}
	
	//解除当前线程的令牌
	public static void unbindToken()
	{
		l.remove();
	}
	
	//得到当前线程绑定的令牌
	public static SurveyparkToken getCurrentToken()
	{
		return l.get();
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	
	
}
