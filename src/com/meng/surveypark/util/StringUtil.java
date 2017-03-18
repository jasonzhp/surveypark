package com.meng.surveypark.util;

import java.util.Set;

import com.meng.surveypark.model.BaseEntity;

//字符串工具类
public class StringUtil {

	//把字符串分割成数组
	public static String[] strArr(String s, String regex)
	{
		if(ValidateUtil.isValidate(s))
			return s.split(regex);
		return null;
	}

	//判断values数组是否含有value
	public static boolean contains(String[] values, String value) {
		if(ValidateUtil.isValidate(values))
		{
			for(String s:values)
			{
				if(s.equals(value))
					return true;
			}
		}
		return false;
	}

	//把数组转化成字符串，用“，”分割开
	public static String strArr(Object[] arr) {
		String temp = "";
		if(ValidateUtil.isValidate(arr))
		{
			for(Object s:arr)
			{
				temp = temp + s.toString() + ",";
			}
			return temp.substring(0, temp.length()-1);
		}
		return temp;
	}
	
	//抽取实体的id，形成字符串
	public static String extractIds(Set<? extends BaseEntity> entities)
	{
		String temp = "";
		if(ValidateUtil.isValidate(entities))
		{
			for(BaseEntity entity : entities)
			{
				temp = temp + entity.getId() + ",";
			}
			return temp.substring(0, temp.length() - 1);
		}
		return temp;
	}
	
	//抽取字符串的前面一部分出来
	public static String getDescString(String desc)
	{
		if(desc != null && desc.trim().length() > 30)
		{
			return desc.substring(0, 27) + "...";
		}
		return desc;
	}
	
}
