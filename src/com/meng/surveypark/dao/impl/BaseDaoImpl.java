package com.meng.surveypark.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.meng.surveypark.dao.BaseDao;

/**
 * 抽象的 DAO ，专门用于继承
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	//注入 SessionFactory
	@Resource
	private SessionFactory factory;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl()
	{
		//得到泛型化超类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	public void saveEntity(T t) {
		factory.getCurrentSession().save(t);
	}

	public void updateEntity(T t) {
		factory.getCurrentSession().update(t);
	}

	public void saveOrUpdateEntity(T t) {
		factory.getCurrentSession().saveOrUpdate(t);
	}

	public void deleteEntity(T t) {
		factory.getCurrentSession().delete(t);
	}

	public void BatchEntityByHQL(String hql, Object... objects) {
		Query query = factory.getCurrentSession().createQuery(hql);
		for(int i=0; i<objects.length; i++)
		{
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}
	
	public void executeSql(String sql, Object...objects)
	{
		SQLQuery query = factory.getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<objects.length; i++)
		{
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	public T loadEntity(Integer id) {
		return (T) factory.getCurrentSession().load(clazz, id);
	}

	public T getEntity(Integer id) {
		return (T) factory.getCurrentSession().get(clazz, id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query query = factory.getCurrentSession().createQuery(hql);
		for(int i=0; i<objects.length; i++)
		{
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}
	
	//单值检索，要确保查询结果有且只有一条记录
	public Object uniqueResult(String hql, Object... objects)
	{
		Query query = factory.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++)
		{
			query.setParameter(i, objects[i]);
		}
		return query.uniqueResult();
	}
	
	public List<T> executeSQLQuery(Class clazz, String sql, Object...objects)
	{
		SQLQuery query = factory.getCurrentSession().createSQLQuery(sql);
		//封装为实体
		if(clazz != null)
		{
			query.addEntity(clazz);
		}
		for(int i = 0;i<objects.length;i++)
		{
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

}
