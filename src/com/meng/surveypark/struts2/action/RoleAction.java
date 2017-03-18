package com.meng.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.security.Right;
import com.meng.surveypark.model.security.Role;
import com.meng.surveypark.service.RightService;
import com.meng.surveypark.service.RoleService;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = -8378179577465896243L;
	
	private List<Role> allRoles;
	
	private Integer roleId;
	
	//属于该角色的权限id
	private Integer[] ownRightIds;
	
	//不属于该角色的权限集合
	private List<Right> noOwnRights;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private RightService rightService;

	//查找并显示所有角色
	public String findAllRoles()
	{
		this.allRoles = roleService.findAllEntities(); 
		return "roleListPage";
	}
	
	//去增加角色页面
	public String toAddRolePage()
	{
		this.noOwnRights = rightService.findAllEntities();
		return "addRolePage";
	}
	
	//保存或更新角色
	public String saveOrUpdateRole()
	{
		roleService.saveOrUpdateRole(model, ownRightIds);
		return "roleListAction";
	}
	
	//修改角色
	public String editRole()
	{
		this.model = roleService.getEntity(roleId);
		this.noOwnRights = rightService.findRightsNotInRange(model.getRights());
		return "editRolePage";
	}
	
	//删除角色
	public String deleteRole()
	{
		Role r = new Role();
		r.setId(roleId);
		roleService.deleteEntity(r);
		return "roleListAction";
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer[] getOwnRightIds() {
		return ownRightIds;
	}

	public void setOwnRightIds(Integer[] ownRightIds) {
		this.ownRightIds = ownRightIds;
	}

	public List<Right> getNoOwnRights() {
		return noOwnRights;
	}

	public void setNoOwnRights(List<Right> noOwnRights) {
		this.noOwnRights = noOwnRights;
	}

}
