package com.meng.surveypark.schedule;

import com.meng.surveypark.service.LogService;
import com.meng.surveypark.util.LogUtil;

//创建日志表的石英任务
public class CreateLogTableTask {

	LogService logService;
	
	//生成日志表，一次生成后三个月的表
	protected void executeTask() {
		//后一个月
		String tableName = LogUtil.generateTableName(1);
		logService.createLogTable(tableName);
		//后两个月
		tableName = LogUtil.generateTableName(2);		
		logService.createLogTable(tableName);
		//后三个月
		tableName = LogUtil.generateTableName(3);
		logService.createLogTable(tableName);
	}

	//注入logService
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
