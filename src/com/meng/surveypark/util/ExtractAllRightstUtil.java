package com.meng.surveypark.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.meng.surveypark.service.RightService;

//提取所有权限的工具类
public class ExtractAllRightstUtil {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		RightService rightService = (RightService) ac.getBean("rightService");
		ClassLoader loader = ExtractAllRightstUtil.class.getClassLoader();
		URL url = loader.getResource("com/meng/surveypark/struts2/action");
		File file = new File(url.toURI());
		File[] fileList = file.listFiles();
		String fname = "";
		for(File f : fileList)
		{
			fname = f.getName();
			if(!fname.equals("BaseAction.class")
					&& fname.endsWith(".class"))
			{
				processAction(fname, rightService);
			}
		}
	}

	//处理所有action类，捕获所有URL地址，形成权限
	private static void processAction(String fname, RightService rightService) {
		try {
			String packageName = "com.meng.surveypark.struts2.action";
			String simpleClassName = fname.substring(0, fname.indexOf(".class"));
			//得到具体类
			Class clazz = Class.forName(packageName + "." + simpleClassName);
			Method[] methods = clazz.getDeclaredMethods();
			Class returnType = null;		//返回类型
			Class[] parameterType = null;	//参数类型
			String mname = null;			//方法名
			String rightUrl = null;
			for(Method m : methods)
			{
				returnType = m.getReturnType();
				parameterType = m.getParameterTypes();
				mname = m.getName();
				//选出有效的方法
				if(returnType == String.class
						&& !ValidateUtil.isValidate(parameterType)
						&& Modifier.isPublic(m.getModifiers())
						&& !mname.startsWith("get"))
				{
					if(mname.equals("execute"))
					{
						rightUrl = "/" + simpleClassName;
					}
					else
					{
						rightUrl = "/" + simpleClassName + "_" + mname;
					}
					rightService.appendRightByURL(rightUrl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
