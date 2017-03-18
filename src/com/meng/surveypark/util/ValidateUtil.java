package com.meng.surveypark.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.meng.surveypark.model.User;
import com.meng.surveypark.model.security.Right;
import com.meng.surveypark.struts2.UserAware;
import com.meng.surveypark.struts2.action.BaseAction;

//校验类工具类
public class ValidateUtil {

	//判断字符串有效性
	public static boolean isValidate(String src)
	{
		if(src==null||"".equals(src.trim()))
				return false;
		return true;
	}
	
	//判断集合的有效性
	public static boolean isValidate(Collection c)
	{
		if(c==null||c.isEmpty())
			return false;
		return true;
	}

	//判断数组是否有效
	public static boolean isValidate(Object[] values) {
		if(values==null||values.length==0)
			return false;
		return true;
	}
	
	//判断是否有权限
	@SuppressWarnings("rawtypes")
	public static boolean hasRight(String namespace, String actionName, HttpServletRequest request, BaseAction action)
	{
		if(!isValidate(namespace)
				|| namespace.equals("/"))
		{
			namespace = "";
		}
		//将超链接中的参数滤掉
		if(actionName != null && actionName.contains("?"))
		{
			actionName = actionName.substring(0, actionName.indexOf("?"));
		}
		String url = namespace + "/" + actionName;
		
		HttpSession session = request.getSession();
		ServletContext sc = session.getServletContext();
		@SuppressWarnings("unchecked")
		Map<String, Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
		Right right = map.get(url);
		//判断是否是公共资源
		if(right == null
				|| right.isCommon())
		{
			return true;
		}
		else
		{
			User user = (User) session.getAttribute("user");
			//判断是否登录了
			if(user == null)
			{
				return false;
			}
			else
			{
				//注入user
				if(action != null
						&& action instanceof UserAware)
				{
					((UserAware) action).setUser(user);
				}
				//判断是否是超级管理员
				if(user.isSuperAdmin())
				{
					return true;
				}
				else
				{
					//判断是否有权限
					if(user.hasRight(right))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
	}
}
