package com.meng.surveypark.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.meng.surveypark.model.security.Right;
import com.meng.surveypark.model.security.Role;

/**
 * 用户类
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = 2746823475415029426L;
	
	private Integer id;
	private String nickName;
	private String email;
	private String password;
	//注册时间不能改
	private Date regDate = new Date();
	
	//角色集合
	private Set<Role> roles = new HashSet<>();
	
	//用户权限总和数组,使用基本类型而不用包装类型，这样空值位置为0
	private long[] rightSum;
	
	//是否是超级管理员
	private boolean superAdmin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public long[] getRightSum() {
		return rightSum;
	}
	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}
	public boolean isSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
	
	//计算用户权限总和
	public void calculateRightSum() {
		int pos = 0;
		long code = 0;
		for(Role role : roles)
		{
			if(role.getRoleValue().equals("-1"))
			{
				this.superAdmin = true;
				break;
			}
			for(Right right : role.getRights())
			{
				pos = right.getRightPos();
				code = right.getRightCode();
				rightSum[pos] = rightSum[pos] | code;
			}
		}
		//释放资源，减轻session负担
		roles = null;
	}
	
	//该用户是否有指定权限
	public boolean hasRight(Right r) {
		int pos = r.getRightPos();
		long code = r.getRightCode();
		return (rightSum[pos] & code) != 0;
	}
}
