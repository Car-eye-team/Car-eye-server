/**    
 * Description: car-eye车辆管理平台    
 * 文件名：GenericDao.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.base.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.careye.base.action.Page;

/**    
 *     
 * 项目名称：car-eye    
 * 类名称：GenericDao    
 * 类描述：dao接口
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
@Repository
@SuppressWarnings("unchecked")
public interface GenericDao {

	/**
	 * 无参数获取对象
	 */
	public Object queryForObject(String sqlId);

	/**
	 * 根据参数获取对象
	 */
	public Object queryForObject(String sqlId, Object param);

	/**保存实体*/
	public int save(String sqlId, Object obj);

	/**查询条数*/
	public Integer getCount(String sqlId, Object obj);
	/**
	 * 根据传入的Object修改对应的列的值
	 */
	public int update(String sqlId, Object obj);

	/**
	 * Description: 根据对象条件删除数据
	 */
	public int delete(String sqlId, Object obj);

	/**
	 * 没有参数查询结果集
	 */
	public List queryForList(String sqlId);

	/**
	 *根据条件查询结果集
	 */
	public List queryForList(String sqlId, Object obj);

	/**
	 * 单表查询，无需参数
	 * */
	public Page findPageNoParam(final String sqlId, final String countId, final int currentPage, final int everyPage);

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
	public Page findPageObject(final String sqlId, final String countId,final Object parameterObject, final int currentPage, final int everyPage);
	
	public Map findPageList(final String sqlId, final String countId,final Object parameterObject, final int currentPage, final int everyPage);

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
	public Page<HashMap<String, Object>> findMapPage(final String sqlId,final String countId, final Object parameterObject,final int currentPage, final int everyPage);


}
