package com.meng.surveypark.util;

import java.text.DecimalFormat;
import java.util.Calendar;

public class LogUtil {

	//生成表名，格式：logs_2016_03
	//偏移量
	public static String generateTableName(int offset) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		//0-11
		int month = calendar.get(Calendar.MONTH) + 1 + offset;
		if(month > 12)
		{
			month = month - 12;
			year++;
		}
		if(month < 1)
		{
			month = month + 12;
			year--;
		}
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00");
		return "logs_" + year + "_" + df.format(month);
	}
	
}
