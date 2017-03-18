package com.meng.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.User;
import com.meng.surveypark.model.security.Role;
import com.meng.surveypark.service.RoleService;
import com.meng.surveypark.service.UserService;

@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	private static final long serialVersionUID = 3708318019023000464L;

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	private List<User> allUsers;
	
	private Integer userId;
	
	//���ڸ��û��Ľ�ɫid
	private Integer[] ownRoleIds;
	
	//�����ڸ��û��Ľ�ɫ
	private List<Role> noOwnRoles;
	
	//�������е��û���Ȩ
	public String findAllUsers()
	{
		this.allUsers = userService.findAllEntities();
		return "userAuthorizeListPage";
	}
	
	//�޸��û���Ȩ
	public String editUserAuthorize()
	{
		this.model = userService.getEntity(userId);
		this.noOwnRoles = roleService.findRolesNotInRange(model.getRoles());
		return "editUserAuthorizePage";
	}
	
	//�����û�Ȩ�ޣ�ֻ�ܸ��½�ɫ���ã�
	public String updateUserAuthorize()
	{
		userService.updateUserAuthorize(model, ownRoleIds);
		return "findUserAuthorizeListAction";
	}
	
	//����û���Ȩ
	public String clearUserAuthorize()
	{
		userService.clearUserAuthorize(userId);
		return "findUserAuthorizeListAction";
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer[] getOwnRoleIds() {
		return ownRoleIds;
	}

	public void setOwnRoleIds(Integer[] ownRoleIds) {
		this.ownRoleIds = ownRoleIds;
	}

	public List<Role> getNoOwnRoles() {
		return noOwnRoles;
	}

	public void setNoOwnRoles(List<Role> noOwnRoles) {
		this.noOwnRoles = noOwnRoles;
	}
}
