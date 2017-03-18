package com.meng.surveypark.model;

import java.io.Serializable;

import com.meng.surveypark.util.StringUtil;

/**
 * 问题类 
 */
public class Question extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1065111926894268520L;

	private final static String RN = "\r\n";
	
	private Integer id;
	
	/*
	 * 题型0-8
	 * 0.非矩阵式横向单选按钮
	 * 1.非矩阵式纵向单选按钮
	 * 2.非矩阵式横向复选按钮
	 * 3.非矩阵式纵向复选按钮
	 * 4.非矩阵式下拉列表
	 * 5.非矩阵式文本框
	 * 6.矩阵式单选按钮
	 * 7.矩阵式复选按钮
	 * 8.矩阵式下拉列表
	 */
	private int questionType;
	//题目
	private String title;
	//选项
	private String options;
	private String[] optionArr;
	//其他项
	private boolean other;
	//其他项样式：0-无；1-文本框；2-下拉列表
	private int otherType;
	//其他项下拉选项
	private String otherSelectOptions;
	private String[] otherSelectOptionArr;
	//矩阵式行标题集
	private String matrixRowTitles;
	private String[] matrixRowTitleArr;
	//矩阵式列标题集
	private String matrixColumnTitles;
	private String[] matrixColumnTitleArr;
	//矩阵式下拉选项集
	private String matrixSelectOptions;
	private String[] matrixSelectOptionArr;
	
	//建立从 Question 到 Page 的多对一关联关系
	private Page page;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	//重写setter方法
	public void setOptions(String options) {
		this.options = options;
		this.optionArr = StringUtil.strArr(options, RN);
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherType() {
		return otherType;
	}

	public void setOtherType(int otherType) {
		this.otherType = otherType;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	//重写setter方法
	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionArr = StringUtil.strArr(otherSelectOptions, RN);
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	//重写setter方法
	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitleArr = StringUtil.strArr(matrixRowTitles, RN);
	}

	public String getMatrixColumnTitles() {
		return matrixColumnTitles;
	}

	//重写setter方法
	public void setMatrixColumnTitles(String matrixColumnTitles) {
		this.matrixColumnTitles = matrixColumnTitles;
		this.matrixColumnTitleArr = StringUtil.strArr(matrixColumnTitles, RN);
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	//重写setter方法
	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionArr = StringUtil.strArr(matrixSelectOptions, RN);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public String[] getOtherSelectOptionArr() {
		return otherSelectOptionArr;
	}

	public String[] getMatrixRowTitleArr() {
		return matrixRowTitleArr;
	}

	public String[] getMatrixColumnTitleArr() {
		return matrixColumnTitleArr;
	}

	public String[] getMatrixSelectOptionArr() {
		return matrixSelectOptionArr;
	}

}
