package com.meng.surveypark.service;

import java.util.List;

import com.meng.surveypark.model.log.Log;

//日志服务
public interface LogService extends BaseService<Log>{

	//生成指定表名的日志表
	void createLogTable(String tableName);

	//查找最近 i 月份的日志
	List<Log> findNearestLogs(int i);

}
