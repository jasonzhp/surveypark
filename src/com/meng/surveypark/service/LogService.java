package com.meng.surveypark.service;

import java.util.List;

import com.meng.surveypark.model.log.Log;

//��־����
public interface LogService extends BaseService<Log>{

	//����ָ����������־��
	void createLogTable(String tableName);

	//������� i �·ݵ���־
	List<Log> findNearestLogs(int i);

}
