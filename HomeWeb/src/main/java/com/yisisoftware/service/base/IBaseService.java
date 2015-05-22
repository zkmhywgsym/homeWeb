package com.yisisoftware.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yisisoftware.util.base.HqlFilter;


/**
 * 基础业务逻辑类，其他service继承此service获得基本的业务
 * 
 * @author jonny
 *
 * @param	T
 */
public interface IBaseService<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * 				对象
	 * @return	对象的ID
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 * 				对象
	 */
	public void delete(T o);
	
	/**
	 * 更新一个对象
	 * 
	 * @param o
	 * 				对象
	 */
	public void update(T o);
	
	/**
	 * 保存或更新一个对象
	 * 
	 * @param o
	 * 				对象
	 */
	public void saveOrUpdate(T o);
	
	/**
	 * 通过主键获得一个对象
	 * 
	 * @param c
	 * 				类名.class
	 * @param id
	 * 				主键
	 * @return	对象
	 */
	public T getById(Serializable id);
	
	/**
	 * 通过HQL语句获得一个对象
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
	public T getByHql(String hql,Map<String, Object> params);
	
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
	public List<T> find(String hql ,Map<String, Object> params);
	
	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 * 				HQL语句
	 * @param page
	 * 				要显示第几页 首页为第1页
	 * @param rows
	 * 				每页显示多少条
	 * @return	List
	 */
	public List<T> find(String hql,int page,int rows);
	
	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 * 				HQL语句
	 * @param params
	 * 					参数
	 * @param page
	 * 					要显示第几页 首页为第1页
	 * @param rows
	 * 					每页显示多少条
	 * @return	List
	 */
	public List<T> find(String hql ,Map<String, Object> params,int page, int rows);
	
	/**
	 * 删除对象列表
	 * 
	 * @param list
	 * 				对象列表
	 */
	public void deleteList(List list);
	
	/**
	 * 更新对象列表
	 * 
	 * @param list
	 * 				对象列表
	 */
	public void updateList(List list);
	
	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T)
	 * @return long
	 */
	public Long count(String hql);
	
	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句()
	 * @return long
	 */
	public Object sum(String hql);
	
	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T where xx = :xx)
	 * @param params
	 *            参数
	 * @return long
	 */
	public Long count(String hql, Map<String, Object> params);
	
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
	
	/**
	 * 统计数目
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countByFilter(HqlFilter hqlFilter);
	
	/**
	 * 获得对象列表
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public List<T> findByFilter(HqlFilter hqlFilter);
	
	/**
	 * 获得分页后对象列表
	 * 
	 * @param hqlFilter	查询条件
	 * @param page	当前页 首页为第1页
	 * @param rows	每页显示多少条
	 * @return
	 */
	public List<T> findByFilter(HqlFilter hqlFilter, int page, int rows);
	
	/**
	 * 获取一个对象
	 * @param hqlFilter
	 * @return
	 */
	public T getByFilter(HqlFilter hqlFilter);
	
	/**
	 * 统计数目
	 * 
	 * @return long
	 */
	public Long count();
	
	/**
	 * 获得对象列表
	 * 
	 * @return
	 */
	public List<T> find();
	
	/**
	 * 通过原生sql语句获得对象列表
	 * 
	 * @param sql
	 * 				原生sql语句
	 * @return	
	 * 			返回结果集
	 */
	public List findBySql(String sql);
	
	/**
	 * 通过原生sql语句获得对象列表
	 * 
	 * @param sql
	 * 				原生sql语句
	 * @param page
	 * 				当前页面	首页为第1页
	 * @param rows
	 * 				每页显示多少条
	 * @return	
	 * 			返回结果集
	 */
	public List findBySql(String sql,int page,int rows);
}
