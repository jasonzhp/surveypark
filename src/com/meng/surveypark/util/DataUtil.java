package com.meng.surveypark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;

public class DataUtil {

	//用md5加密
	public static String md5(String src)
	{
		try
		{
			if(ValidateUtil.isValidate(src))
			{
				StringBuffer buffer = new StringBuffer();
				char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
				byte[] bytes = src.getBytes();
				MessageDigest digest = MessageDigest.getInstance("MD5");
				byte[] targ = digest.digest(bytes);
				for(byte b:targ)
				{
					buffer.append(chars[(b>>>4)&15]);
					buffer.append(chars[b&15]);
				}
				return buffer.toString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	//深度复制，使用串行化技术
	public static Serializable deeplyCopy(Serializable src)
	{
		try
		{
			//序列化对象
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			
			byte[] bytes = baos.toByteArray();
			
			//反序列化对象
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
