package com.meng.surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.User;
import com.meng.surveypark.service.UserService;
import com.meng.surveypark.util.DataUtil;
import com.meng.surveypark.util.ValidateUtil;

/**
 * 注册Action
 */
@Controller
@Scope("prototype")	//设置为原型的，便是Action为原型bean，即非单例的
public class RegAction extends BaseAction<User> {

	private static final long serialVersionUID = 7596751436715547336L;
	
	private String confirmPassword;
	
	//注入UserService
	@Resource
	private UserService userService;
	
	//到达注册页面
	@SkipValidation
	public String toRegPage()
	{
		return "regPage";
	}
	
	/**
	 *进行用户注册 
	 */
	public String doReg()
	{
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return SUCCESS;
	}
	
	public void validate()
	{
		//非空
		if(!ValidateUtil.isValidate(model.getEmail()))
			this.addFieldError("email", "E-mail是必填项");
		if(!ValidateUtil.isValidate(model.getPassword()))
			this.addFieldError("password", "密码是必填项");
		if(!ValidateUtil.isValidate(model.getNickName()))
			this.addFieldError("nickName", "昵称是必填项");
		if(this.hasErrors())
			return;
		//email格式正确
		if(!model.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
		{
			this.addFieldError("email", "E-mail的格式不正确");
			return;
		}
		//密码一致
		if(!model.getPassword().equals(confirmPassword))
		{
			this.addFieldError("password", "密码不一致");
			return;
		}
		//email占用
		if(userService.isRegisted(model.getEmail()))
			this.addFieldError("email", "E-mail已经占用");
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
