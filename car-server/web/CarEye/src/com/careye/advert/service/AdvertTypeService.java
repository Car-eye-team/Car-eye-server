/**
 * 
 */
package com.careye.advert.service;

import java.util.Map;

import com.careye.advert.domain.AdvertType;


/**
 * @author Administrator
 *
 */
public interface AdvertTypeService {
	
	/**
	 * 广告类型列表分页查询
	 * getAdvertTypeList
	 */
	public Map getAdvertTypeList(Integer currentPage, Integer everyPage,AdvertType advertType);
	
	/**
	 * 名称是否存在
	 * @return
	 */
	public Integer nameIsExist(AdvertType advertType);

	/**
	 * 修改
	 */
	public int updateType(AdvertType advertType);
	/**
	 * 增加
	 * @return
	 */
	public int addType(AdvertType advertType);
	
	/**
	 * 是否被使用
	 */
	public int queryAdvert(int id);
	/**
	 * 删除
	 */
	public int deleteAdvertType(int id);
	
	

}
