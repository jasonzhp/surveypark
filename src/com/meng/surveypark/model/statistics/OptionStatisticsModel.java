package com.meng.surveypark.model.statistics;

import java.io.Serializable;

//选项统计模型
public class OptionStatisticsModel implements Serializable {

	private static final long serialVersionUID = 1163878331763768600L;
	
	//选项索引
	private int optionIndex;
	//选项标签
	private String optionLabel;
	
	//选项行索引
	private int matrixRowIndex;
	//选项行标签
	private String matrixRowLabel;
	private int matrixColumnIndex;
	private String matrixColumnLabel;
	private int matrixSelectIndex;
	private String matrixSelectLabel;
	
	//选择该选项的人数
	private int count;

	public int getOptionIndex() {
		return optionIndex;
	}

	public void setOptionIndex(int optionIndex) {
		this.optionIndex = optionIndex;
	}

	public String getOptionLabel() {
		return optionLabel;
	}

	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}

	public int getMatrixRowIndex() {
		return matrixRowIndex;
	}

	public void setMatrixRowIndex(int matrixRowIndex) {
		this.matrixRowIndex = matrixRowIndex;
	}

	public String getMatrixRowLabel() {
		return matrixRowLabel;
	}

	public void setMatrixRowLabel(String matrixRowLabel) {
		this.matrixRowLabel = matrixRowLabel;
	}

	public int getMatrixColumnIndex() {
		return matrixColumnIndex;
	}

	public void setMatrixColumnIndex(int matrixColumnIndex) {
		this.matrixColumnIndex = matrixColumnIndex;
	}

	public String getMatrixColumnLabel() {
		return matrixColumnLabel;
	}

	public void setMatrixColumnLabel(String matrixColumnLabel) {
		this.matrixColumnLabel = matrixColumnLabel;
	}

	public int getMatrixSelectIndex() {
		return matrixSelectIndex;
	}

	public void setMatrixSelectIndex(int matrixSelectIndex) {
		this.matrixSelectIndex = matrixSelectIndex;
	}

	public String getMatrixSelectLabel() {
		return matrixSelectLabel;
	}

	public void setMatrixSelectLabel(String matrixSelectLabel) {
		this.matrixSelectLabel = matrixSelectLabel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
