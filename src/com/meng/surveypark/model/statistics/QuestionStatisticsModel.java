package com.meng.surveypark.model.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.meng.surveypark.model.Question;

//问题统计模型
public class QuestionStatisticsModel implements Serializable {

	private static final long serialVersionUID = -7766616456212199154L;
	
	//问题
	private Question question;
	//回答该问题的人数
	private int count;
	
	//问题选项集合
	private List<OptionStatisticsModel> osms = new ArrayList<>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOsms() {
		return osms;
	}

	public void setOsms(List<OptionStatisticsModel> osms) {
		this.osms = osms;
	}
	
}
