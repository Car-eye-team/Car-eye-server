/**
 * 
 */
package com.careye.advert.service.impl;

import java.util.Map;

import com.careye.advert.domain.AdvertType;
import com.careye.advert.service.AdvertTypeService;
import com.careye.base.service.GenericService;
import com.careye.utils.DateUtil;

/**
 * @author Administrator
 *
 */
public class AdvertTypeServiceImpl extends GenericService implements AdvertTypeService{

	@Override
	public Map getAdvertTypeList(Integer currentPage, Integer everyPage,
			AdvertType advertType) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-advertTypeSQL.getAdvertTypeList",
				"oracle-advertTypeSQL.getAdvertTypeListCount", advertType,currentPage,everyPage);
	}

	/**
	* 是否存在
	*/
	public Integer nameIsExist(AdvertType advertType) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-advertTypeSQL.nameIsExist",advertType);
	}

	/**
	* 修改
	*/
	public int updateType(AdvertType advertType) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-advertTypeSQL.updateType",advertType);
	}
	/**
	* 增加
	*/
	public int addType(AdvertType advertType) {
		// TODO Auto-generated method stub
		String current_time=DateUtil.getSQLDate();
		advertType.setCreatetime(current_time);
		return this.baseDao.save("oracle-advertTypeSQL.saveType", advertType);
	}
	
	/**
	 * 是否被使用
	 */
	public int queryAdvert(int id) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-advertTypeSQL.queryAdvert",id);
	}
	
	/**
	 * 删除
	 */
	public int deleteAdvertType(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-advertTypeSQL.deleteAdvertType", id);
	}

}
