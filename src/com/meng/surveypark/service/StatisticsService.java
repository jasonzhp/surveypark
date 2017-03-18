package com.meng.surveypark.service;

import com.meng.surveypark.model.statistics.QuestionStatisticsModel;

//统计服务
public interface StatisticsService {

	//对指定问题进行统计
	QuestionStatisticsModel statistics(Integer qid);
}
