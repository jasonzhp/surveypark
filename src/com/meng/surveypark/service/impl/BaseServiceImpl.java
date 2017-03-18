package com.meng.surveypark.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import com.meng.surveypark.dao.BaseDao;
import com.meng.surveypark.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl()
	{
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	//！！！不能在定义字段上使用该注释，因为需要子类覆盖该 set 方法
	@Resource
	public void setDao(BaseDao<T> baseDao) {
		this.dao = baseDao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public void BatchEntityByHQL(String hql, Object... objects) {
		dao.BatchEntityByHQL(hql, objects);
	}
	
	//执行sql语句
	public void executeSql(String sql, Object... objects)
	{
		dao.executeSql(sql, objects);
	}

	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}
	
	//单值检索，要确保查询结果有且只有一条记录
	public Object uniqueResult(String hql, Object... objects)
	{
		return dao.uniqueResult(hql, objects);
	}
	
	//查找所有的实体
	public List<T> findAllEntities()
	{
		String hql = "from " + clazz.getSimpleName();
		return this.findEntityByHQL(hql);
	}
	
	//执行sql语句
	public List<T> executeSQLQuery(Class clazz, String sql, Object...objects)
	{
		return dao.executeSQLQuery(clazz, sql, objects);
	}

}
