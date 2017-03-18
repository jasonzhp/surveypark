package com.meng.surveypark.service;

import com.meng.surveypark.model.User;

public interface UserService extends BaseService<User> {

	//判断email是否占用
	boolean isRegisted(String email);

	//返回对应email和密码的用户
	User validateUserInfo(String email, String md5);

	//更新用户授权（只能更新角色设置）
	void updateUserAuthorize(User user, Integer[] ids);

	//清除用户授权
	void clearUserAuthorize(Integer userId);

}
