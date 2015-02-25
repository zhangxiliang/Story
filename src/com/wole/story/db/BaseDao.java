package com.wole.story.db;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.dao.Dao;

/***
 * 
 * 基础sqlite DAO   
 * Created By Zhangxiliang
 * Date：2014年11月18日 
 * Version： 2.0   
 * Copyright (c) 2014 56.com Software corporation All Rights Reserved.     
 *
 */
public class BaseDao<T> {
	protected DatabaseHelper helper;
    protected Dao<T,Integer> baseDao;
	public BaseDao() {
		helper = DatabaseManager.getHelper();
		
		Class < T >  entityClass  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
		try {
			
			baseDao=helper.getDao(entityClass);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void add(T entity) {
		try {
			baseDao.createOrUpdate(entity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delte(T entiry){
		try {
			baseDao.delete(entiry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(T entity){
		try {
			baseDao.update(entity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void addList(Collection<T> collection) {
		if (collection == null && collection.size() == 0) {
			return;
		}
		
		try {
			for (T entity : collection) {
				baseDao.createOrUpdate(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<T> queryAll(){
		List<T> list=null;
		try {
			list=baseDao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
}
