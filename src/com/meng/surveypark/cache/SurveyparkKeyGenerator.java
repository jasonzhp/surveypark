package com.meng.surveypark.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.meng.surveypark.util.StringUtil;

//自定义缓存key生成器
public class SurveyparkKeyGenerator implements KeyGenerator {

	/**
	 * arg0:所调用的类
	 * arg1:所调用的方法
	 * arg2:方法参数
	 */
	//key形如：SurveyServiceImpl.findMySurvey(user.toString());
	public Object generate(Object arg0, Method arg1, Object... arg2) {
		String simpleName = arg0.getClass().getSimpleName();
		String mname = arg1.getName();
		String params = StringUtil.strArr(arg2);
		System.out.println(simpleName + "." + mname + "(" + params + ")");
		return simpleName + "." + mname + "(" + params + ")";
	}

}
