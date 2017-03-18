package com.meng.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.log.Log;
import com.meng.surveypark.service.LogService;

@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 4992001955557147973L; 

	@Resource
	private LogService logService;
	
	private List<Log> nearestLogs;
	
	//最近月份数的日志，默认为2
	private int months = 2;

	//查找最近的日志
	public String findNearestLogs()
	{
		nearestLogs = logService.findNearestLogs(months);
		return "logListPage";
	}
	
	public List<Log> getNearestLogs() {
		return nearestLogs;
	}

	public void setNearestLogs(List<Log> nearestLogs) {
		this.nearestLogs = nearestLogs;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}
}
