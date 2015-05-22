package com.yisisoftware.dao.base.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.yisisoftware.dao.base.IBaseDao;

@Component("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	protected final Log log=LogFactory.getLog(getClass());
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public Serializable save(T o) {
		
		if(o !=null){
			return getHibernateTemplate().save(o);
		}
		return null;
	}

	@Override
	public void delete(T o) {
		
		if(o != null){
			getHibernateTemplate().delete(o);
		}
	}

	@Override
	public void update(T o) {
		
		if(o != null){
			getHibernateTemplate().update(o);
		}
		
	}

	@Override
	public void saveOrUpdate(T o) {
		
		if(o != null){
			getHibernateTemplate().saveOrUpdate(o);
		}
	}

	@Override
	public T getById(Class<T> c, Serializable id) {
		
		return getHibernateTemplate().get(c, id);
	}

	@Override
	public T getByHql(String hql) {
	
		List<T> list=getHibernateTemplate().find(hql);
		if(list != null || list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		
		List<T> list;
		if(params != null || params.size()!=0){
			
			List<String> keyList=new ArrayList<String>();
			List valueList=new ArrayList();
			Set set=params.entrySet();
			Iterator iter=set.iterator();
			while(iter.hasNext()){
				Map.Entry<String, Object> entry= (Map.Entry<String, Object>) iter.next();
				keyList.add(entry.getKey());
				valueList.add(entry.getValue());
			}
			
			String [] paramNames=keyList.toArray(new String[keyList.size()]);
			Object [] values=valueList.toArray(new Object[valueList.size()]);
			list=getHibernateTemplate().findByNamedParam(hql, paramNames, values);
		}else{
			list=getHibernateTemplate().find(hql);
		}
		
		if(list != null && list.size() !=0){		//且的关系
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<T> find(String hql) {

		List<T> list=getHibernateTemplate().find(hql);
		if(list == null){
			return Collections.EMPTY_LIST;
		}
		return list;
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		
		List<T> list;
		if(params != null && params.size()!=0){
			
			List<String> keyList=new ArrayList<String>();
			List valueList=new ArrayList();
			Set set=params.entrySet();
			Iterator iter=set.iterator();
			while(iter.hasNext()){
				Map.Entry<String, Object> entry= (Map.Entry<String, Object>) iter.next();
				keyList.add(entry.getKey());
				valueList.add(entry.getValue());
			}
			
			String [] paramNames=keyList.toArray(new String[keyList.size()]);
			Object [] values=valueList.toArray(new Object[valueList.size()]);
			list=getHibernateTemplate().findByNamedParam(hql, paramNames, values);
		}else{
			list=getHibernateTemplate().find(hql);
		}
		return list;
	}

	@Override
	public List<T> find(final String hql, final int page, final int rows) {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {

				Query query=session.createQuery(hql);
				query.setFirstResult((page - 1)*rows);
				query.setMaxResults(rows);
				List<T> list=query.list();
				return (T) list;
			}
		});
	}

	@Override
	public List<T> find(final String hql, final Map<String, Object> params, final int page,
			final int rows) {

		System.out.println(hql);
		return getHibernateTemplate().executeFind(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query=session.createQuery(hql);
				if(params != null && params.size()>0){
					Set set=params.entrySet();
					Iterator iter=set.iterator();
					while(iter.hasNext()){
						Map.Entry<String, Object> entry= (Map.Entry<String, Object>) iter.next();
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				query.setFirstResult((page - 1)*rows);
				query.setMaxResults(rows);
				List list=query.list();
				return (T) list;
			}
		});
	}

	@Override
	public Long count(String hql) {
		Long count=((Long) getHibernateTemplate().find(hql).iterator().next()).longValue();
		return count;
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {

		List list=find(hql, params);
		if(list !=null || list.size()>0){
			return ((Long) list.iterator().next()).longValue();
		}
		return 0L;
	}

	@Override
	public void executeProcedure(final String procedureName, final Object[] objs) {
		
		getHibernateTemplate().execute(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				CallableStatement cs=session.connection().prepareCall("{call "+procedureName+"}");
				if(objs != null || objs.length !=0){
					int times=procedureName.split("?").length-1;
					if(times>=0){
						for(int j=0;j<=times;j++){
							cs.setObject(j+1, objs[j]);
						}
					}
				}
				cs.execute();
				return null;
			}
		});
	}

	@Override
	public T executeProcedure(final String procedureName, final Object[] objs, final int type) {
		
		return getHibernateTemplate().execute(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				CallableStatement cs=session.connection().prepareCall("{call "+procedureName+"}");
				if(objs != null || objs.length !=0){
					int times=procedureName.split("?").length-1;
					if(times>=1){
						for(int j=0;j<=times;j++){
							cs.setObject(j+1, objs[j]);
						}
					}
				}
				cs.registerOutParameter(procedureName.split("?").length-1, type);
				cs.execute();
				Object obj=cs.getObject(procedureName.split("?").length-1);
				return (T) obj;
			}
		});
	}

	@Override
	public List findBySql(String sql) {
		Session MySession = super.getSession();
		try {
			Query query = MySession.createSQLQuery(sql);
			List list= query.list();
			if(list == null){
				return Collections.EMPTY_LIST;
			}
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}finally{
			releaseSession(MySession);
		}
	}

	@Override
	public List findBySql(String sql, int page, int rows) {
		Session MySession = super.getSession();
		try {
			Query query = MySession.createSQLQuery(sql);
			query.setFirstResult((page-1)*rows);
			query.setMaxResults(rows);
			List<Object[]> list= query.list();
			if(list == null){
				return Collections.EMPTY_LIST;
			}
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}finally{
			releaseSession(MySession);
		}
	}

	@Override
	public Object sum(String hql) {
		
		Object count = getHibernateTemplate().find(hql).iterator().next();
		return count;
	}

}
