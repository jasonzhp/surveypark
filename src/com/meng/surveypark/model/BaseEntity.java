package com.meng.surveypark.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 8467805754513400888L;

	public abstract void setId(Integer id);
	
	public abstract Integer getId();
	
	public String toString()
	{
		try {
			StringBuffer buffer = new StringBuffer();
			Class clazz = this.getClass();
			buffer.append(clazz.getSimpleName());
			buffer.append("{");
			Field[] fields = clazz.getDeclaredFields();
			Class fType = null;
			String fName = null;
			Object fValue = null;
			for(Field f : fields)
			{
				fType = f.getType();
				fName = f.getName();
				//设置成任何修饰符修饰的字段都可以访问
				f.setAccessible(true);
				fValue = f.get(this);
				if(fName.equals("password"))
				{
					continue;
				}
				//如果是基本数据类型或者包装类型，而且不是静态的
				if((fType.isPrimitive()
						|| fType == Integer.class
						|| fType == Boolean.class
						|| fType == Short.class
						|| fType == Long.class
						|| fType == Byte.class
						|| fType == Character.class
						|| fType == Float.class
						|| fType == Double.class
						|| fType == String.class)
						&& !Modifier.isStatic(f.getModifiers()))
				{
					buffer.append(fName);
					buffer.append(":");
					buffer.append(fValue);
					buffer.append(",");
				}
			}
			buffer.append("}");
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
