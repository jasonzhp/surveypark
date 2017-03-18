package com.meng.surveypark.struts2.action;

import java.awt.Font;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.Page;
import com.meng.surveypark.model.statistics.OptionStatisticsModel;
import com.meng.surveypark.model.statistics.QuestionStatisticsModel;
import com.meng.surveypark.service.StatisticsService;

@Controller
@Scope("prototype")
public class ChartOutputAction extends BaseAction<Page> {

	private static final long serialVersionUID = 5137693562357640156L;
	//平面饼图
	private static final int CHARTTYPE_PIE_2D = 0;
	//立体饼图
	private static final int CHARTTYPE_PIE_3D = 1;
	//水平平面柱状图
	private static final int CHARTTYPE_BAR_2D_H = 2;
	//垂直平面柱状图
	private static final int CHARTTYPE_BAR_2D_V = 3;
	//水平立体柱状图
	private static final int CHARTTYPE_BAR_3D_H = 4;
	//垂直立体柱状图
	private static final int CHARTTYPE_BAR_3D_V = 5;
	//平面折线图
	private static final int CHARTTYPE_LINE_2D = 6;
	//立体折线图
	private static final int CHARTTYPE_LINE_3D = 7;
	
	//接收问题id
	private Integer qid;
	
	//接收图表类型
	private int chartType;
	
	//接收statisticsService
	@Resource
	private StatisticsService statisticsService;
	
	public String execute() throws Exception
	{
		return SUCCESS;
	}
	
	public JFreeChart getChart()
	{
		JFreeChart chart = null;
		QuestionStatisticsModel qsm = statisticsService.statistics(qid);
		//饼图数据集
		DefaultPieDataset pieds = null;
		//种类数据集
		DefaultCategoryDataset catds = null;
		
		//构造数据集
		if(this.chartType < 2)
		{
			pieds = new DefaultPieDataset();
			for(OptionStatisticsModel osm:qsm.getOsms())
			{
				pieds.setValue(osm.getOptionLabel(), osm.getCount());
			}
		}
		else
		{
			catds = new DefaultCategoryDataset();
			for(OptionStatisticsModel osm:qsm.getOsms())
			{
				catds.addValue(osm.getCount(), osm.getOptionLabel(), "");
			}
		}
		
		//判断要求的图表
		switch(this.chartType)
		{
			case CHARTTYPE_PIE_2D:
				chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(), pieds, true, false, false);
				break;
			case CHARTTYPE_PIE_3D:
				chart = ChartFactory.createPieChart3D(qsm.getQuestion().getTitle(), pieds, true, false, false);
				//设置前景透明度
				chart.getPlot().setForegroundAlpha(0.6f);;
				break;
			case CHARTTYPE_BAR_2D_H:
				chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(), "", "", catds,
								PlotOrientation.HORIZONTAL, true, false, false);
				break;
			case CHARTTYPE_BAR_2D_V:
				chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(), "", "", catds,
								PlotOrientation.VERTICAL, true, true, true);
				break;
			case CHARTTYPE_BAR_3D_H:
				chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(), "", "", catds,
								PlotOrientation.HORIZONTAL, true, true, true);
				break;
			case CHARTTYPE_BAR_3D_V:
				chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(), "", "", catds,
								PlotOrientation.VERTICAL, true, true, true);
				break;
			case CHARTTYPE_LINE_2D:
				chart = ChartFactory.createLineChart(qsm.getQuestion().getTitle(), "", "", catds,
								PlotOrientation.VERTICAL, true, true, true);
				break;
			case CHARTTYPE_LINE_3D:
				chart = ChartFactory.createLineChart3D(qsm.getQuestion().getTitle(), "", "", catds,
								PlotOrientation.VERTICAL, true, true, true);
				break;
		}
		
		//设置标题和提示条中文
		Font font = new Font("宋体", Font.BOLD,20);
		chart.getTitle().setFont(font);
		chart.getLegend().setItemFont(font);
		//设置饼图特效
		if(chart.getPlot() instanceof PiePlot)
		{
			PiePlot piePlot = (PiePlot) chart.getPlot();
			piePlot.setLabelFont(font);
			piePlot.setExplodePercent(0, 0.1);
			piePlot.setStartAngle(-15);
			piePlot.setDirection(Rotation.CLOCKWISE);
			piePlot.setNoDataMessage("No data to display");
		}
		//设置非饼图特效
		else
		{
			chart.getCategoryPlot().getRangeAxis().setLabelFont(font);
			chart.getCategoryPlot().getRangeAxis().setTickLabelFont(font);
			chart.getCategoryPlot().getDomainAxis().setLabelFont(font);
		}
		
		return chart;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public int getChartType() {
		return chartType;
	}

	public void setChartType(int chartType) {
		this.chartType = chartType;
	}

}