package com.meng.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meng.surveypark.dao.BaseDao;
import com.meng.surveypark.model.User;
import com.meng.surveypark.model.security.Role;
import com.meng.surveypark.service.RoleService;
import com.meng.surveypark.service.UserService;
import com.meng.surveypark.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource
	private RoleService roleService;
	
	//重写该方法，目的是覆盖超类中该方法的注解，指明注入的DAO对象，否则spring无法确定注入哪一个DAO
	@Resource(name="userDao")
	public void setDao(BaseDao<User> baseDao) {
		super.setDao(baseDao);
	}

	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?";
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValidate(list);
	}

	public User validateUserInfo(String email, String md5) {
		String hql = "from User u where u.email = ? and u.password = ?";
		List<User> list = this.findEntityByHQL(hql, email, md5);
		return ValidateUtil.isValidate(list)?list.get(0):null;
	}
	
	//更新用户授权（只能更新角色设置）
	public void updateUserAuthorize(User user, Integer[] ids)
	{
		//一定要在数据库的查询该用户，否则会将用户信息改掉
		User newUser = this.getEntity(user.getId());
		if(!ValidateUtil.isValidate(ids))
		{
			newUser.getRoles().clear();
		}
		else
		{
			List<Role> roles = roleService.findRolesInRange(ids);
			newUser.setRoles(new HashSet<Role>(roles));
			//不需要再调用saveOrUpdate方法保存对象，因为从数据库查出的对象处于持久化状态，与session关联在一起的，service执行完以后它会自动提交！！！
		}
	}
	
	//清除用户授权
	public void clearUserAuthorize(Integer userId)
	{
		this.getEntity(userId).getRoles().clear();
	}

}
