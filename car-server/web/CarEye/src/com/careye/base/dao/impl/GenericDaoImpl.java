/**    
 * Description: car-eye车辆管理平台    
 * 文件名：GenericDaoImpl.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.base.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.careye.base.action.Page;
import com.careye.base.dao.GenericDao;

/**    
 *     
 * 项目名称：car-eye    
 * 类名称：GenericDaoImpl    
 * 类描述：dao接口实现 
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class GenericDaoImpl extends SqlMapClientDaoSupport implements GenericDao{

	protected static final Logger LOG = Logger.getLogger(GenericDaoImpl.class);

	
	/**
	 * 无参数获取对象
	 */
	public Object queryForObject(String sqlId) {
		Object entity = null;
		try {
			entity =  getSqlMapClientTemplate().queryForObject(sqlId);
		} catch (Exception e) {
			LOG.error("根据参数获取对象异常！", e);
		}
		return entity;
	}

	/**
	 * 根据参数获取对象
	 */
	public Object queryForObject(String sqlId, Object param) {
		Object entity = null;
		try {
			entity =  getSqlMapClientTemplate().queryForObject(sqlId, param);
		} catch (Exception e) {
			LOG.error("根据参数获取对象异常！", e);
		}
		return entity;
	}
	
	/**保存实体*/
	public int save(String sqlId, Object obj) {
		
		int  id = -1;
		Object re = getSqlMapClientTemplate().insert(sqlId, obj);
		try {
			if(re!=null){
				id = (Integer)re;
			}
		} catch (Exception e) {
			LOG.error("保存对象异常！", e);
		}
		return id;
	}
	
	/**查询条数*/
	public Integer getCount(String sqlId, Object obj) {
		return (Integer)getSqlMapClientTemplate().queryForObject(sqlId, obj);
	}


	/**
	 * 根据传入的Object修改对应的列的值
	 */
	public int update(String sqlId, Object obj) {
		int id = -1;
		try {
			id = getSqlMapClientTemplate().update(sqlId, obj);
		} catch (Exception e) {
			LOG.error("根据传入的bean修改对应的列的值修改数据异常！", e);
		}
		return id;
	}

	/**
	 * Description: 根据对象条件删除数据
	 */
	public int delete(String sqlId, Object obj) {
		int id = -1;
		try {
			id = getSqlMapClientTemplate().delete(sqlId, obj);
		} catch (Exception e) {
			LOG.error("根据对象条件删除数据异常！", e);
		}
		return id;
	}
	

	/**
	 * 没有参数查询结果集
	 */
	@SuppressWarnings("unchecked")
	public List queryForList(String sqlId) {
		List list = null;
		try {
			list =  getSqlMapClientTemplate().queryForList(sqlId);
		} catch (Exception e) {
			LOG.error("没有参数查询结果集异常！", e);
		}
		return list; 
	}

	/**
	 *根据条件查询结果集
	 */
	@SuppressWarnings("unchecked")
	public List queryForList(String sqlId, Object obj) {
		List list = null;
		try {
			list = getSqlMapClientTemplate().queryForList(sqlId, obj);
		} catch (Exception e) {
			LOG.error("根据条件查询结果集异常！", e);
		}
		return list; 
	}

//	/**
//	 * batchInsert:批量插入数据。 <br>
//	 * (用于批量插入数据，传入的数据是用列表存入的)
//	 * 
//	 * @param memberList
//	 * @param sql
//	 * @return
//	 */
//	@SuppressWarnings({ "unused", "rawtypes" })
//	public void batchInsert(final List<T> memberList, final String sqlId) {
//		try {
//			SqlMapClientCallback callback = new SqlMapClientCallback() {
//				public Object doInSqlMapClient(SqlMapExecutor executor)
//				throws SQLException {
//					executor.startBatch();
//					for (T e : memberList) {
//						executor.insert(sqlId, e);
//						/** statement在*MapSql.xml一条语句的id */
//					}
//					executor.executeBatch();
//					return null;
//
//				}
//
//			};
//
//		} catch (Exception e) {
//			LOG.error("批量插入数据异常！", e);
//		}
//	}
//
//	/**
//	 * 带缓存的批量插入数据
//	 * 
//	 * @param TreeCateList
//	 * @param sqlId
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void insertTreeCateBatch(final List<T> TreeCateList,
//			final String sqlId) {
//		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
//			public T doInSqlMapClient(SqlMapExecutor executor)
//			throws SQLException {
//				executor.startBatch();
//				int batch = 0;
//				for (T TreeCate : TreeCateList) {
//					/**
//					 * 调用获取sequence的方法。如果没有的话就去掉这行代码。
//					 * TreeCate.setTreeCateId(getNextId());
//					 * 参数1为：ibatis中需要执行的语句的id
//					 **/
//					executor.insert(sqlId, TreeCate);
//					batch++;
//					/** 每500条批量提交一次。 */
//					if (batch == 500) {
//						executor.executeBatch();
//						batch = 0;
//					}
//				}
//				executor.executeBatch();
//				return null;
//			}
//		});
//	}
//
//	/**
//	 * batchUpdate: 批量修改数据。 <br>
//	 * (适用于批量修改数据，传入的是数据列表)
//	 * 
//	 * @param memberList
//	 * @param sql
//	 * @return
//	 */
//	/**批量更新数据*/
//	@SuppressWarnings("rawtypes")
//	public void batchUpdate(final List list, final String sqlId) {
//		try {
//			sqlMapClient.startBatch();
//			for (Object e : list){
//				update(sqlId, e);
//			}
//			sqlMapClient.executeBatch();			
//		} catch (Exception e) {
//			LOG.error("批量更新数据异常！", e);
//		}
//
//	}

	/**单表查询，无需参数*/
	@SuppressWarnings("unchecked")
	public Page findPageNoParam(final String sqlId, final String countId, final int currentPage, final int everyPage) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setEveryPage(everyPage);
		Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject(countId);
		page.setTotalCount(totalCount);
		int skipResults = (currentPage - 1) * everyPage;
		int everyCount = getEveryCount(currentPage, everyPage, totalCount);
		try {
			List result =  getSqlMapClientTemplate().queryForList(sqlId, skipResults, everyCount);
			page.setResult(result);
		} catch (Exception e) {
			LOG.debug("查询分页异常！", e);
		}
		return page;
	}
	
	/**
	 * 根据实体部分属性查询实体分页数据
	 * 
	 * @param sqlId
	 *            sql语句id
	 * @param countId
	 *            查询总记录数语句id
	 * @param parameterObject
	 *            传入参数
	 * @param page
	 *            分页对象
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public Page findPageObject(final String sqlId, final String countId,final Object parameterObject, final int currentPage, final int everyPage) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setEveryPage(everyPage);
		Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject(countId, parameterObject);
		page.setTotalCount(totalCount);
		int skipResults = (currentPage - 1) * everyPage;
		int everyCount = getEveryCount(currentPage, everyPage, totalCount);
		try {
			List result = getSqlMapClientTemplate().queryForList(
					sqlId, parameterObject, skipResults, everyCount);
			page.setResult(result);
		} catch (Exception e) {
			LOG.debug("查询分页异常！", e);
		}
		return page;
	}
	
	
	public Map findPageList(final String sqlId, final String countId,final Object parameterObject, final int currentPage, final int everyPage) {
		Map reMap = new HashMap();
		Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject(countId, parameterObject);
		reMap.put("totalCount", totalCount);
		int skipResults = (currentPage - 1) * everyPage;
		int everyCount = getEveryCount(currentPage, everyPage, totalCount);
		try {
			List result = getSqlMapClientTemplate().queryForList(
					sqlId, parameterObject, skipResults, everyCount);
			reMap.put("list", result);
		} catch (Exception e) {
			LOG.error("查询分页异常！", e);
		}
		return reMap;
	}

	/**
	 * 根据实体部分属性查询多表分页数据
	 * 
	 * @param sqlId
	 *            sql语句id
	 * @param countId
	 *            查询总记录数语句id
	 * @param parameterObject
	 *            传入参数
	 * @param page
	 *            分页对象
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public Page<HashMap<String, Object>> findMapPage(final String sqlId,
			final String countId, final Object parameterObject,
			final int currentPage, final int everyPage) {
		Page<HashMap<String, Object>> page = new Page<HashMap<String, Object>>();
		page.setCurrentPage(currentPage);
		page.setEveryPage(everyPage);
		Integer totalCount = (Integer) getSqlMapClientTemplate()
		.queryForObject(countId, parameterObject);
		page.setTotalCount(totalCount);
		int skipResults = (currentPage - 1) * everyPage;
		int everyCount = getEveryCount(currentPage, everyPage, totalCount);
		try {
			List<HashMap<String, Object>> result = (List<HashMap<String, Object>>) getSqlMapClientTemplate()
			.queryForList(sqlId, parameterObject, skipResults,
					everyCount);
			page.setResult(result);
		} catch (Exception e) {
			LOG.debug("查询分页异常！", e);
		}

		return page;
	}

	private int getEveryCount(int currentPage, int everyPage, int totalCount) {
		int everyCount;
		int number = totalCount - currentPage * everyPage;
		if (number > 0) {
			everyCount = everyPage;
		} else {
			everyCount = everyPage + number;
		}
		return everyCount;
	}
}
