package com.meng.surveypark.service;

import java.util.List;
import java.util.Set;

import com.meng.surveypark.model.security.Right;

//权限服务
public interface RightService extends BaseService<Right>{

	//保存/更新权限
	void saveOrUpdateRight(Right right);

	//按照rightURL追加权限
	void appendRightByURL(String rightUrl);

	//批量更新权限
	void batchUpdateRights(List<Right> rights);

	//查找在指定范围内的right
	List<Right> findRightsInRange(Integer[] ids);

	//查找不在指定范围内的right
	List<Right> findRightsNotInRange(Set<Right> rights);

	//得到最大的pos
	int getMaxPos();

}
