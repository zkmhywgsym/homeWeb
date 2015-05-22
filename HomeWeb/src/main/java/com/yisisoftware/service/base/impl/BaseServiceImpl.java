package com.yisisoftware.service.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yisisoftware.dao.base.IBaseDao;
import com.yisisoftware.service.base.IBaseService;
import com.yisisoftware.util.base.HqlFilter;


@Component
public class BaseServiceImpl<T> implements IBaseService<T>{

	private IBaseDao<T> baseDao;
	
	public IBaseDao<T> getBaseDao() {
		return baseDao;
	}
	@Resource
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Serializable save(T o) {

		return baseDao.save(o);
	}

	@Override
	public void delete(T o) {

		baseDao.delete(o);
	}

	@Override
	public void update(T o) {

		baseDao.update(o);
	}

	@Override
	public void saveOrUpdate(T o) {

		baseDao.saveOrUpdate(o);
	}

	@Override
	public T getById(Serializable id) {

		Class<T> c= (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		return baseDao.getById(c, id);
	}

	@Override
	public T getByHql(String hql) {

		return baseDao.getByHql(hql);
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseDao.getByHql(hql, params);
	}

	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return baseDao.find(hql);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, params);
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, page, rows);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, params, page, rows);
	}

	@Override
	public void deleteList(List list) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();i++){
			baseDao.delete((T) list.get(i));
		}
	}

	@Override
	public void updateList(List list) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();i++){
			baseDao.update((T) list.get(i));
		}
	}

	@Override
	public Long count(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseDao.count(hql, params);
	}

	@Override
	public void executeProcedure(String procedureName, Object[] objs) {
		// TODO Auto-generated method stub
		baseDao.executeProcedure(procedureName, objs);
	}

	@Override
	public T executeProcedure(String procedureName, Object[] objs, int type) {
		// TODO Auto-generated method stub
		return baseDao.executeProcedure(procedureName, objs, type);
	}

	@Override
	public Long countByFilter(HqlFilter hqlFilter) {
		
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
		String hql = "select count(*) from " + className + " t";
		return ((Long) find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams()).iterator().next()).longValue();
	}

	@Override
	public List<T> findByFilter(HqlFilter hqlFilter) {

		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "from " + className + " t";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<T> findByFilter(HqlFilter hqlFilter, int page, int rows) {

		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
		String hql = "from " + className + " t";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams(), page, rows);
	}

	@Override
	public T getByFilter(HqlFilter hqlFilter) {

		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "from " + className + " t";
		return getByHql(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public Long count() {

		return countByFilter(new HqlFilter());
	}

	@Override
	public List<T> find() {

		return findByFilter(new HqlFilter());
	}
	@Override
	public List findBySql(String sql) {
		
		return baseDao.findBySql(sql);
	}
	@Override
	public List findBySql(String sql, int page, int rows) {

		return baseDao.findBySql(sql, page, rows);
	}
	@Override
	public Object sum(String hql) {

		return baseDao.sum(hql);
	}

}
