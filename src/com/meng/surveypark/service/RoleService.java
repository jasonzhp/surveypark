package com.meng.surveypark.service;

import java.util.List;
import java.util.Set;

import com.meng.surveypark.model.security.Role;

//角色服务
public interface RoleService extends BaseService<Role>{

	//保存或更新角色
	void saveOrUpdateRole(Role r, Integer[] ids);

	//查找不在指定范围内的role
	List<Role> findRolesNotInRange(Set<Role> roles);

	//查找在指定范围内的role
	List<Role> findRolesInRange(Integer[] ids);

}
