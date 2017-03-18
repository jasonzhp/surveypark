package com.meng.surveypark.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.meng.surveypark.service.LogService;
import com.meng.surveypark.util.LogUtil;

//使用spring监听器实现启动时创建连续三张日志表
@SuppressWarnings("rawtypes")
@Component
public class InitializeLogTablesListener implements ApplicationListener {

	@Resource
	LogService logService;
	
	//初始化日志表
	public void onApplicationEvent(ApplicationEvent arg0) {
		//上下文刷新事件
		if(arg0 instanceof ContextRefreshedEvent)
		{
			//创建当前月份日志表
			String tableName = LogUtil.generateTableName(0);
			logService.createLogTable(tableName);
			
			tableName = LogUtil.generateTableName(1);
			logService.createLogTable(tableName);
			
			tableName = LogUtil.generateTableName(2);
			logService.createLogTable(tableName);
		}
	}

}
