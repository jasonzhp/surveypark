package com.meng.surveypark.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.meng.surveypark.model.User;
import com.meng.surveypark.model.log.Log;
import com.meng.surveypark.service.LogService;
import com.meng.surveypark.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

//��־������
public class Logger {
	
	@Resource
	private LogService logService;
	
	//��¼,����֪ͨ
	public Object record(ProceedingJoinPoint pjp)
	{
		Log log = new Log();
		try {
			//���ò�����
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
			//���ò�������
			String mname = pjp.getSignature().getName();
			log.setOperateName(mname);
			//���ò�������
			Object[] params = pjp.getArgs();
			log.setOperateParams(StringUtil.strArr(params));
			
			//����Ŀ�����ķ���
			Object ret = pjp.proceed();
			
			//���ò������
			log.setOperateResult("success");
			//���÷��ؽ��
			if(ret != null)
			{
				log.setResultMsg(ret.toString());
			}
			return ret;
		} catch (Throwable e) {
			log.setOperateResult("failure");
			//����ʧ�ܽ����Ϣ
			log.setResultMsg(e.getMessage());
		}
		finally
		{
			logService.saveEntity(log);
		}
		return null;
	}
}
