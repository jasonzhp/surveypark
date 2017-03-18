package com.meng.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meng.surveypark.dao.BaseDao;
import com.meng.surveypark.model.security.Right;
import com.meng.surveypark.service.RightService;
import com.meng.surveypark.util.StringUtil;
import com.meng.surveypark.util.ValidateUtil;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	//重写该方法，目的是覆盖超类中该方法的注解，指明注入的DAO对象，否则spring无法确定注入哪一个DAO
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> baseDao) {
		super.setDao(baseDao);
	}
	
	//保存/更新权限
	public void saveOrUpdateRight(Right right)
	{
		int pos;
		long code;
		//判断是保存还是更新
		//如果为保存
		if(right.getId()==null)
		{
//			String hql = "from Right r order by r.rightPos desc, r.rightCode desc";
//			List<Right> list = this.findEntityByHQL(hql);
//			//如果不为空表
//			if(ValidateUtil.isValidate(list))
//			{
//				Right top = list.get(0);
//				//判断权限码是否达到最大值
//				if(1L<<60 == top.getRightCode())
//				{
//					pos = top.getRightPos() + 1;
//					code = 1L;
//				}
//				else
//				{
//					pos = top.getRightPos();
//					code = top.getRightCode() << 1;
//				}
//			}
//			//如果为空表
//			else
//			{
//				pos = 0;
//				code = 1L;
//			}
			//以上代码方法性能不高，下面代码性能更佳
			String hql = "select max(r.rightPos), max(r.rightCode) from Right r "
					+ "where r.rightPos = (select max(rr.rightPos) from Right rr)";
			Object[] top = (Object[]) this.uniqueResult(hql);
			//因为有可能为空值，所以定义类型为包装型而不是基本类型
			Integer topPos = (Integer) top[0];
			Long topCode = (Long) top[1];
			//如果为空表
			if(topPos == null)
			{
				pos = 0;
				code = 1L;
			}
			else
			{
				//判断权限码是否达到最大值
				if(1L << 60 == topCode)
				{
					pos = topPos + 1;
					code = 1;
				}
				else
				{
					pos = topPos;
					code = topCode << 1;
				}
			}
			
			right.setRightCode(code);
			right.setRightPos(pos);
		}
		this.saveOrUpdateEntity(right);
	}
	
	//按照rightURL追加权限
	public void appendRightByURL(String rightUrl)
	{
		String hql = "select count(r) from Right r where r.rightUrl = ?";
		Long l = (Long) this.uniqueResult(hql, rightUrl);
		if(l == 0)
		{
			Right r = new Right();
			r.setRightUrl(rightUrl);
			this.saveOrUpdateRight(r);
		}
	}
	
	//批量更新权限
	public void batchUpdateRights(List<Right> rights)
	{
		String hql = "update Right r set r.rightName = ?, r.common = ? where r.id = ?";
		if(ValidateUtil.isValidate(rights))
		{
			for(Right r:rights)
			{
				this.BatchEntityByHQL(hql, r.getRightName(), r.isCommon(), r.getId());
			}
		}
	}
	
	//查找在指定范围内的right
	public List<Right> findRightsInRange(Integer[] ids)
	{
		if(ValidateUtil.isValidate(ids))
		{
			String hql = "from Right r where r.id in(" + StringUtil.strArr(ids) + ")";
			return this.findEntityByHQL(hql);
		}
		return null;
	}
	
	//查找不在指定范围内的right
	public List<Right> findRightsNotInRange(Set<Right> rights)
	{
		if(!ValidateUtil.isValidate(rights))
		{
			return this.findAllEntities();
		}
		else
		{
			String hql = "from Right r where r.id not in(" + StringUtil.extractIds(rights) + ")";
			return this.findEntityByHQL(hql);
		}
	}
	
	//得到最大的pos
	public int getMaxPos()
	{
		String hql = "select max(r.rightPos) from Right r";
		Integer maxPos = (Integer) this.uniqueResult(hql);
		return maxPos == null? 0 : maxPos;
	}

}
