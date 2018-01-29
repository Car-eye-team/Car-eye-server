/**
 * Description: car-eye车辆管理平台
 * 文件名：AppTypeServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-8-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.sysset.domain.AppType;
import com.careye.sysset.domain.AppVersion;
import com.careye.sysset.service.AppTypeService;
import com.careye.utils.DateUtil;


/**
 * @项目名称：car-eye
 * @类名称：AppTypeServiceImpl
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-20 下午02:05:07
 * @修改人：Administrator
 * @修改时间：2015-8-20 下午02:05:07
 * @修改备注：
 * @version 1.0
 */
public class AppTypeServiceImpl extends GenericService implements AppTypeService {

	/**
	* 分页查询版本类型列表
	* findPageAppTypeList
	* TODO
	* @param currentPage
	* @param everyPage
	* @param apptype
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#findPageAppTypeList(java.lang.Integer, java.lang.Integer, com.duosen.gate.set.domain.AppType)
	*/
	@Override
	public Map findPageAppTypeList(Integer currentPage, Integer everyPage,
			AppType apptype) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-AppTypeSQL.findPageAppTypeList", "oracle-AppTypeSQL.findPageAppTypeListCount", apptype, currentPage, everyPage);
	}

	/**
	* 增加版本类型
	* addAppType
	* TODO
	* @param apptype
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#addAppType(com.duosen.gate.set.domain.AppType)
	*/
	@Override
	public int addAppType(AppType apptype) {
		// TODO Auto-generated method stub
		String current_time=DateUtil.getSQLDate();
		apptype.setCreatetime(current_time);
		return this.baseDao.save("oracle-AppTypeSQL.saveAppType", apptype);
	}

	/**
	* 版本类型是否存在
	* appTypeIsExist
	* TODO
	* @param apptype
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#appTypeIsExist(com.duosen.gate.set.domain.AppType)
	*/
	@Override
	public Integer appTypeIsExist(AppType apptype) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-AppTypeSQL.appTypeIsExist",apptype);
	}

	/**
	* 修改版本类型
	* updateAppType
	* TODO
	* @param apptype
	* @see com.duosen.gate.set.service.AppTypeService#updateAppType(com.duosen.gate.set.domain.AppType)
	*/
	@Override
	public int updateAppType(AppType apptype) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-AppTypeSQL.updateAppType",apptype);
	}

	/**
	* 删除版本类型
	* deleteAppType
	* TODO
	* @param parseInt
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#deleteAppType(int)
	*/
	@Override
	public int deleteAppType(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-AppTypeSQL.deleteAppType", id);
	}

	/**
	* 分页查询版本型号列表
	* findPageAppVersionList
	* TODO
	* @param page
	* @param limit
	* @param appversion
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#findPageAppVersionList(java.lang.Integer, java.lang.Integer, com.duosen.gate.set.domain.AppVersion)
	*/
	@Override
	public Map findPageAppVersionList(Integer currentPage, Integer everyPage,
			AppVersion appversion) {
		// TODO Auto-generated method stub                   
		return this.baseDao.findPageList("oracle-AppTypeSQL.findPageAppVersionList", "oracle-AppTypeSQL.findPageAppVersionListCount", appversion, currentPage, everyPage);
	}

	/**
	* 版本类型下拉列表
	* selAppVersionList
	* TODO
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#selAppVersionList()
	*/
	@Override
	public List<AppVersion> selAppVersionList() {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-AppTypeSQL.selAppVersionList");
	}

	/**
	* 版本型号是否存在
	* appVersionIsExist
	* TODO
	* @param appversion
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#appVersionIsExist(com.duosen.gate.set.domain.AppVersion)
	*/
	@Override
	public Integer appVersionIsExist(AppVersion appversion) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-AppTypeSQL.appVersionIsExist",appversion);
	}

	/**
	* 增加版本型号
	* addAppVersion
	* TODO
	* @param appversion
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#addAppVersion(com.duosen.gate.set.domain.AppVersion)
	*/
	@Override
	public int addAppVersion(AppVersion appversion) {
		// TODO Auto-generated method stub
		String current_time=DateUtil.getSQLDate();
		appversion.setCreatetime(current_time);
		return this.baseDao.save("oracle-AppTypeSQL.saveAppVersion", appversion);
	}

	/**
	* 修改版本型号
	* updateAppVersion
	* TODO
	* @param appversion
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#updateAppVersion(com.duosen.gate.set.domain.AppVersion)
	*/
	@Override
	public int updateAppVersion(AppVersion appversion) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-AppTypeSQL.updateAppVersion",appversion);
	}

	/**
	* 删除版本型号
	* deleteAppVersion
	* TODO
	* @param parseInt
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#deleteAppVersion(int)
	*/
	@Override
	public int deleteAppVersion(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-AppTypeSQL.deleteAppVersion", id);
	}

	/**
	* 某版本类型下的型号列表
	* queryGorupVersion
	* TODO
	* @param parseInt
	* @return
	* @see com.duosen.gate.set.service.AppTypeService#queryGorupVersion(int)
	*/
	@Override
	public int queryGorupVersion(int id) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-AppTypeSQL.queryGorupVersion",id);
	}

}
