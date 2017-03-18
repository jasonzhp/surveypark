package com.meng.surveypark.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * 页面类 
 */
public class Page extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6145290201032403366L;

	private Integer id;
	private String title = "未命名";
	private String description;
	
	private float orderno;
	
	//映射从 Page 到 Survey 的多对一关联关系
	//使用transient修饰，在虚拟机不会对此进行序列化
	private transient Survey survey;
	
	//映射从 Page 到 Question 的一对多关联关系
	private Set<Question> questions = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if(this.id!=null)
			this.orderno = this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public float getOrderno() {
		return orderno;
	}

	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}
	
	
	
}
