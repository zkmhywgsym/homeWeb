package com.yisisoftware.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作类
 * 
 * 其他DAO继承此类，获取常用数据库操作方法，不需要自己建立DAO
 * 
 * @author jonny
 *
 * @param <T>
 * 			
 */
public interface IBaseDao<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o		
	 * 			对象
	 * @return		对象ID
	 */
	public Serializable save(T o);
	
	/**
	 * 删除一个对象
	 * 
	 * @param o
	 * 			对象
	 */
	public void delete(T o);
	
	/**
	 * 更新一个对象
	 * @param o
	 * 			对象
	 */
	public void update(T o);
	
	/**
	 * 保存或更新一个对象
	 * 
	 * @param o
	 * 			对象
	 */
	public void saveOrUpdate(T o);
	
	/**
	 * 通过主键获得对象
	 * 
	 * @param c
	 * 				类型.class
	 * @param id
	 * 				主键
	 * @return	对象
	 */
	public T getById(Class<T> c, Serializable id);
	
	/**
	 * 通过hql语句获得一个对象
	 * 
	 * @param hql
	 * 				HQL语句
	 * @return	对象
	 */
	public T getByHql(String hql);
	
	/**
	 * 通过HQL语句获得一个对象
	 * 
	 * @param hql
	 * 				HQL语句
	 * @param params
	 * 					参数
	 * @return	对象
	 */
	public T getByHql(String hql, Map<String, Object> params);
	
	/**
	 * 通过原生sql获得对象列表
	 * 
	 * @param sql
	 * 				原生sql语句
	 * @return	List
	 */
	public List findBySql(String sql);
	
	/**
	 * 通过原生sql获得对象列表
	 * 
	 * @param sql
	 * 				原生sql语句
	 * 
	 * @param page
	 * 				显示第几页 首页为第1页
	 * 
	 * @param rows
	 * 				每页显示多少条
	 * 
	 * @return	List
	 */
	public List findBySql(String sql,int page,int rows);
	
	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 * 				HQL语句
	 * @return	List
	 */
	public List<T> find(String hql);
	
	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 * 				HQL语句
	 * @param params
	 * 					参数
	 * @return	List
	 */
	public List<T> find(String hql,Map<String, Object> params);
	
	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 * 				HQL语句
	 * @param page
	 * 				要显示第几页 首页为第0页
	 * @param rows
	 * 				每页显示多少条
	 * @return	List
	 */
	public List<T> find(String hql,int page,int rows);
	
	/**
	 * 获得分页后的对象列表
	 * @param hql
	 * 				HQL语句
	 * @param params
	 * 					参数
	 * @param page
	 * 					要显示第几页 首页为第0页
	 * @param rows
	 * 					每页显示多少条
	 * @return	List
	 */
	public List<T> find(String hql,Map<String, Object> params,int page,int rows);
	
	/**
	 * 统计数目
	 * 
	 * @param hql
	 * 				HQL语句(select count(*) from T)
	 * @return	Long
	 */
	public Long count(String hql);
	
	/**
	 * 统计数目
	 * 
	 * @param hql
	 * 				HQL语句()
	 * @return	Long
	 */
	public Object sum(String hql);
	
	/**
	 * 统计数目
	 * 
	 * @param hql
	 * 				HQL语句(select count(*) from T where xx =: xx)
	 * @param params
	 * 					参数
	 * @return	Long
	 */
	public Long count(String hql,Map<String, Object> params);
	
	/**
	 * 执行存储过程
	 * 
	 * @param procedureName
	 * 						存储过程名称
	 * @param objs
	 * 				同一类型参数列表
	 */
	public void executeProcedure(String procedureName,Object[] objs);
	
	
	/**
	 * 执行存储过程
	 * 
	 * @param procedureName
	 * 						存储过程名称
	 * @param objs
	 * 				同一类型参数列表
	 * @param type 
	 * 				返回值的类型java.sql.Types
	 * @return 对象
	 */
	public T executeProcedure(String procedureName,Object[] objs,int type);
	
	
	
	
}
