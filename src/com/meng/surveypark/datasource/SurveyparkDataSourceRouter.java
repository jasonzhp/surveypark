package com.meng.surveypark.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

//自定义数据源路由器（用于分布式数据库）
public class SurveyparkDataSourceRouter extends AbstractRoutingDataSource {

	//检查当前key
	protected Object determineCurrentLookupKey() {
		SurveyparkToken token = SurveyparkToken.getCurrentToken();
		if(token != null)
		{
			Integer id = token.getSurvey().getId();
			
			//解除当前线程的令牌
			SurveyparkToken.unbindToken();
			
			return ((id % 2) == 0)? "even" : "odd";
		}
		return null;
	}

}
