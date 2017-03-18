package com.meng.surveypark.struts2.action.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.meng.surveypark.service.RightService;
import com.meng.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CatchUrlInterceptor implements Interceptor {

	private static final long serialVersionUID = -480401902546157181L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		String namespace = proxy.getNamespace();	//命名空间
		String actionName = proxy.getActionName();	//actionName
		if(!ValidateUtil.isValidate(namespace)
				|| namespace.equals("/"))
		{
			namespace = "";
		}
		String url = namespace + "/" + actionName;
		
//		//取得application中的spring容器，可用以下方法
////	ApplicationContext ac = (ApplicationContext) invocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//		//也可以用下面的方法
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		RightService rightService = (RightService) ac.getBean("rightService");
		rightService.appendRightByURL(url);
		return invocation.invoke();
	}

}
