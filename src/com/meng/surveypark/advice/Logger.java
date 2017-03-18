package com.meng.surveypark.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.meng.surveypark.model.User;
import com.meng.surveypark.model.log.Log;
import com.meng.surveypark.service.LogService;
import com.meng.surveypark.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

//日志生成器
public class Logger {
	
	@Resource
	private LogService logService;
	
	//记录,环绕通知
	public Object record(ProceedingJoinPoint pjp)
	{
		Log log = new Log();
		try {
			//设置操作人
			ActionContext ac = ActionContext.getContext();
			if(ac != null)
			{
				Map<String, Object> session = ac.getSession();
				if(session != null)
				{
					User user = (User) session.get("user");
					if(user != null)
					{
						log.setOperator(user.getId() + ":" + user.getEmail());
					}
				}
			}
			//设置操作名称
			String mname = pjp.getSignature().getName();
			log.setOperateName(mname);
			//设置操作参数
			Object[] params = pjp.getArgs();
			log.setOperateParams(StringUtil.strArr(params));
			
			//调用目标对象的方法
			Object ret = pjp.proceed();
			
			//设置操作结果
			log.setOperateResult("success");
			//设置返回结果
			if(ret != null)
			{
				log.setResultMsg(ret.toString());
			}
			return ret;
		} catch (Throwable e) {
			log.setOperateResult("failure");
			//设置失败结果信息
			log.setResultMsg(e.getMessage());
		}
		finally
		{
			logService.saveEntity(log);
		}
		return null;
	}
}
