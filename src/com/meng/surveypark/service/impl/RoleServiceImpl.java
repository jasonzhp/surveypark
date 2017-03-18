package com.meng.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meng.surveypark.dao.BaseDao;
import com.meng.surveypark.model.security.Right;
import com.meng.surveypark.model.security.Role;
import com.meng.surveypark.service.RightService;
import com.meng.surveypark.service.RoleService;
import com.meng.surveypark.util.StringUtil;
import com.meng.surveypark.util.ValidateUtil;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Resource
	private RightService rightService;
	
	//重写该方法，目的是覆盖超类中该方法的注解，指明注入的DAO对象，否则spring无法确定注入哪一个DAO
	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> baseDao) {
		super.setDao(baseDao);
	}
	
	//保存或更新角色
	public void saveOrUpdateRole(Role r, Integer[] ids)
	{
		//如果没有授权权限
		if(!ValidateUtil.isValidate(ids))
		{
			r.getRights().clear();
		}
		else
		{
			List<Right> list = rightService.findRightsInRange(ids);
			r.setRights(new HashSet<Right>(list));
		}
		this.saveOrUpdateEntity(r);
	}
	
	//查找不在指定范围内的role
	public List<Role> findRolesNotInRange(Set<Role> roles)
	{
		if(!ValidateUtil.isValidate(roles))
		{
			return this.findAllEntities();
		}
		else
		{
			String hql = "from Role r where r.id not in("+ StringUtil.extractIds(roles) +")";
			return this.findEntityByHQL(hql);
		}
	}
	
	//查找在指定范围内的role
	public List<Role> findRolesInRange(Integer[] ids)
	{
		if(ValidateUtil.isValidate(ids))
		{
			String hql = "from Role r where r.id in("+StringUtil.strArr(ids)+")";
			return this.findEntityByHQL(hql);
		}
		return null;
	}

}
