package wpq.shop.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import wpq.shop.model.Page;
import wpq.shop.model.ShopException;
import wpq.shop.model.SystemContext;
import wpq.shop.model.User;
import wpq.shop.util.DaoUtil;
import wpq.shop.util.MybatisUtil;

public class BaseDao<T> {
	
	public BaseDao() {
		DaoUtil.diDao(this);
	}
	
	public void add(T t) {
		SqlSession session=null;
		try {
			session = MybatisUtil.createSqlSession();
			session.insert(t.getClass().getName()+".add", t);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
	}
	
	public void delete(Class clz,Object obj) {
		SqlSession session=null;
		try {
			session = MybatisUtil.createSqlSession();
			session.delete(clz.getName()+".delete", obj);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
	}
	
	public void deleteByOrderId(Class clz,Object obj) {
		SqlSession session=null;
		try {
			session = MybatisUtil.createSqlSession();
			session.delete(clz.getName()+".deleteByOrderId", obj);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
	}
	
	public void update(T t) {
		SqlSession session=null;
		try {
			session = MybatisUtil.createSqlSession();
			session.insert(t.getClass().getName()+".update", t);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
	}
	
	
	public T load(Class clz,Map<String,Object> params){
		T t = loadBySqlId(clz.getName()+".load", params);
		return t;
	}
	
	public T loadBySqlId(String sqlId,Map<String,Object> params){
		SqlSession session=null;
		T t =null;
		try {
			session = MybatisUtil.createSqlSession();
			t = (T)session.selectOne(sqlId,params);
		} catch (Exception e) {
			session.rollback();
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
		return t;
	}
	
	public List<T> loadByOrderId(Class clz,Map<String,Object> params){
		SqlSession session=null;
		List<T> t =null;
		try {
			session = MybatisUtil.createSqlSession();
			t = session.selectList(clz.getName()+".loadByOrderId",params);
		} catch (Exception e) {
			session.rollback();
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
		return t;
	}
	
	public Page<T> find(Class clz,Map<String,Object> params) {
		Page<T> pages = new Page<T>();
		int pageOffset = SystemContext.getPageOffset();
		int pageSize = SystemContext.getPageSize();
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();
		SqlSession session = null;
		params.put("sort", sort);
		params.put("order", order);
		params.put("pageOffset", pageOffset);
		params.put("pageSize", pageSize);
		pages.setPageOffset(pageOffset);
		pages.setPageSize(pageSize);
		try{
			session = MybatisUtil.createSqlSession();
			List<T> datas = session.selectList(clz.getName()+".find", params);
			int tr = session.selectOne(clz.getName()+".find_count", params);
			pages.setDatas(datas);
			pages.setTotalRecord(tr);
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
		return pages;
	}
	
	//只在AddressDao中使用过
	public List<T> list(Class clz,Object obj){
		SqlSession session =null;
		List<T> lists = null;
		try{
			session = MybatisUtil.createSqlSession();
			lists = session.selectList(clz.getName()+".list",obj);
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
		return lists;
	}
	
	public List<T> listByCondition(Class clz,Map<String, Object> params){
		SqlSession session = null;
		List<T> lists = null;
		try {
			session = MybatisUtil.createSqlSession();
			lists = session.selectList(clz.getName()+".listByCondition",params);
		} finally {
			MybatisUtil.closeSqlSession(session);
		}
		return lists;
		
	}
}
