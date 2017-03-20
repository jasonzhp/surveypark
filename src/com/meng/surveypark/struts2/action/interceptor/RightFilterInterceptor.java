package com.meng.surveypark.struts2.action.interceptor;

import org.apache.struts2.ServletActionContext;

import com.meng.surveypark.struts2.action.BaseAction;
import com.meng.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

//登录拦截器
public class RightFilterInterceptor implements Interceptor {

	private static final long serialVersionUID = -1523469947816412432L;

	public void destroy() {

	}

	public void init() {

	}

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		ActionProxy proxy = arg0.getProxy();
		String namespace = proxy.getNamespace();
		String actionName = proxy.getActionName();
		if(ValidateUtil.hasRight(namespace, actionName, ServletActionContext.getRequest(), action))
		{
			return arg0.invoke();
		}
		return "login";
	}
}
