package com.meng.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.User;
import com.meng.surveypark.service.RightService;
import com.meng.surveypark.service.UserService;
import com.meng.surveypark.util.DataUtil;

@Controller
@Scope("prototype")
//通过实现SessionMap来获得session的Map，耦合度低
public class LoginAction extends BaseAction<User> implements SessionAware{

	private static final long serialVersionUID = 7573071327839777417L;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RightService rightService;

	//接收session的Map
	private Map<String, Object> sessionMap;

	//到达登陆页面
	public String toLoginPage()
	{
		return "loginPage";
	}
	
	//进行登陆
	public String doLogin()
	{
		return "success";
	}
	
	//进行登陆校验，并且只校验 doLogin 方法
	public void validateDoLogin()
	{
		User user = userService.validateUserInfo(model.getEmail(), DataUtil.md5(model.getPassword()));
		if(user==null)
			this.addActionError("E-mail或密码错误");
		else
		{
			int maxPos = rightService.getMaxPos();
			user.setRightSum(new long[maxPos + 1]);
			//计算用户权限总和
			user.calculateRightSum();
			
			sessionMap.put("user", user);
		}
	}

	//注入session的Map
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
}
