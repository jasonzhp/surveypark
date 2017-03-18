package com.meng.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.id.UUIDHexGenerator;
import org.springframework.stereotype.Service;

import com.meng.surveypark.dao.BaseDao;
import com.meng.surveypark.model.log.Log;
import com.meng.surveypark.service.LogService;
import com.meng.surveypark.util.LogUtil;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	//重写该方法，目的是覆盖超类中该方法的注解，指明注入的DAO对象，否则spring无法确定注入哪一个DAO
	@Resource(name="logDao")
	public void setDao(BaseDao<Log> baseDao) {
		super.setDao(baseDao);
	}
	
	//生成指定表名的日志表
	public void createLogTable(String tableName)
	{
		String sql = "create table if not exists "+tableName+" like logs";
		this.executeSql(sql);
	}
	
	//重写saveEntity方法，动态插入日志表
	public void saveEntity(Log t) {
		//取出当前月份表名
		String tableName = LogUtil.generateTableName(0);
		String insertSQL = "insert into " + tableName
				+ "(id, operator, operate_name, operate_params, operate_result, result_msg, operate_time)"
				+ "value(?,?,?,?,?,?,?)";
		
		//生成主键值
		UUIDHexGenerator generator = new UUIDHexGenerator();
		String id = (String) generator.generate(null, null);
		
		this.executeSql(insertSQL, id,
					t.getOperator(), t.getOperateName(), t.getOperateParams(), 
					t.getOperateResult(), t.getResultMsg(),t.getOperateTime());
	}
	
	//查找最近 i 月份的日志
	public List<Log> findNearestLogs(int i)
	{
		//获得当前表名
		String tableName = LogUtil.generateTableName(0);
		//查找最近 i 月份的日志表名
		String querySQL = "select table_name from information_schema.tables "
						+ "where table_schema = 'surveypark' "
						+ "and table_name like 'logs_%' "
						+ "and table_name <= '"+ tableName +"' "
						+ "order by table_name desc "
						+ "limit 0, "+ i +"";
		List tableNames = this.executeSQLQuery(null, querySQL);
		int size = tableNames.size();
		String logSQL = "";
		String logName = "";
		//获得最近月份的日志
		for(int n = 0;n<size;n++)
		{
			logName = (String) tableNames.get(n);
			if(n == size-1)
			{
				logSQL = logSQL + "select * from "+ logName +"";
			}
			else
			{
				logSQL = logSQL + "select * from "+ logName +" union ";
			}
		}
		//封装成log实体
		return this.executeSQLQuery(Log.class, logSQL);
	}

}
