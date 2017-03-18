package com.meng.surveypark.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.meng.surveypark.model.security.Right;
import com.meng.surveypark.service.RightService;

/**
 *初始化权限监听器，在spring初始化完成后调用
 */
@SuppressWarnings("rawtypes")
@Component
public class InitializeRightListener implements ApplicationListener, ServletContextAware {

	@Resource
	private RightService rightService;
	
	//接收ServletContext
	private ServletContext sc;
	
	public void onApplicationEvent(ApplicationEvent arg0) {
		//上下文刷新事件
		if(arg0 instanceof ContextRefreshedEvent)
		{
			//查出所有权限
			List<Right> rights = rightService.findAllEntities();
			Map<String, Right> map = new HashMap<>();
			for(Right r : rights)
			{
				map.put(r.getRightUrl(), r);
			}
			sc.setAttribute("all_rights_map", map);
		}
	}

	//注入ServletContext
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
}
