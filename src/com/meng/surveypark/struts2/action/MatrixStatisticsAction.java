package com.meng.surveypark.struts2.action;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.Question;
import com.meng.surveypark.model.statistics.OptionStatisticsModel;
import com.meng.surveypark.model.statistics.QuestionStatisticsModel;
import com.meng.surveypark.service.StatisticsService;

@Controller
@Scope("prototype")
public class MatrixStatisticsAction extends BaseAction<Question> {

	private static final long serialVersionUID = -6466327918777594318L;
	
	//文本框颜色 rgb
	private String[] colors={
			"#ff0000",
			"#00ff00",
			"#0000ff",
			"#ffff00",
			"#ff00ff",
			"00ffff"
	};
	
	//接收qid
	private Integer qid;
	
	private QuestionStatisticsModel qsm;
	
	@Resource
	private StatisticsService ss;
	
	public String execute()
	{
		this.qsm = ss.statistics(qid);
		return "" + qsm.getQuestion().getQuestionType();
	}
	
	//得到该选项的百分比
	public String getScale(int rowIndex, int columnIndex)
	{
		//问题回答人数
		int qcount = qsm.getCount();
		//选项回答人数
		int ocount = 0;
		for(OptionStatisticsModel osm:qsm.getOsms())
		{
			if(osm.getMatrixRowIndex() == rowIndex &&
					osm.getMatrixColumnIndex() == columnIndex)
			{
				ocount = osm.getCount();
				break;
			}
		}
		float scale = 0;
		if(qcount != 0)
		{
			scale = (float)ocount / qcount * 100;
		}
		DecimalFormat format = new DecimalFormat("#,###.00");
		return "" + ocount + "(" +format.format(scale) + "%)";
	}

	//得到该选项的百分比
	public String getScale(int rowIndex, int columnIndex, int optIndex)
	{
		//问题回答人数
		int qcount = qsm.getCount();
		//选项回答人数
		int ocount = 0;
		for(OptionStatisticsModel osm:qsm.getOsms())
		{
			if(osm.getMatrixRowIndex() == rowIndex &&
					osm.getMatrixColumnIndex() == columnIndex &&
					osm.getMatrixSelectIndex() == optIndex)
			{
				ocount = osm.getCount();
				break;
			}
		}
		float scale = 0;
		if(qcount != 0)
		{
			scale = (float)ocount / qcount * 100;
		}
		DecimalFormat format = new DecimalFormat("#,###.00");
		return "" + ocount + "(" +format.format(scale) + "%)";
	}
	
	//得到该选项的百分比的整数部分，作为选项文本框的长度
	public int getPercent(int rowIndex, int columnIndex, int optIndex)
	{
		//问题回答人数
		int qcount = qsm.getCount();
		//选项回答人数
		int ocount = 0;
		for(OptionStatisticsModel osm:qsm.getOsms())
		{
			if(osm.getMatrixRowIndex() == rowIndex &&
					osm.getMatrixColumnIndex() == columnIndex &&
					osm.getMatrixSelectIndex() == optIndex)
			{
				ocount = osm.getCount();
				break;
			}
		}
		float scale = 0;
		if(qcount != 0)
		{
			scale = (float)ocount / qcount * 100;
		}
		return (int)scale;
	}
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public QuestionStatisticsModel getQsm() {
		return qsm;
	}

	public void setQsm(QuestionStatisticsModel qsm) {
		this.qsm = qsm;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}
	
	

}
