/**
 * Description: car-eye车辆管理平台
 * 文件名：AppTypeService.java
 * 版本信息：1.0
 * 日期：2015-8-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.service;

import java.util.List;
import java.util.Map;

import com.careye.sysset.domain.AppType;
import com.careye.sysset.domain.AppVersion;


/**
 * @项目名称：car-eye
 * @类名称：AppTypeService
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-20 下午01:37:46
 * @修改人：Administrator
 * @修改时间：2015-8-20 下午01:37:46
 * @修改备注：
 * @version 1.0
 */
public interface AppTypeService {

	/**
	 * 分页查询版本类型列表
	 * @param currentPage
	 * @param everyPage
	 * @param apptype
	 * @return
	 */
	public Map findPageAppTypeList(Integer currentPage, Integer everyPage, AppType apptype);

	/**
	 * 增加版本类型
	 * @param apptype
	 * @return
	 */
	public int addAppType(AppType apptype);

	/**
	 * 版本类型是否存在
	 * @param apptype
	 * @return
	 */
	public Integer appTypeIsExist(AppType apptype);

	/**
	 * 修改版本类型
	 * @param apptype
	 */
	public int updateAppType(AppType apptype);

	/**
	 * 删除版本类型
	 * @param parseInt
	 * @return
	 */
	public int deleteAppType(int id);

	/**
	 * 分页查询版本型号列表
	 * @param page
	 * @param limit
	 * @param appversion
	 * @return
	 */
	public Map findPageAppVersionList(Integer currentPage, Integer everyPage,
			AppVersion appversion);

	/**
	 * 版本类型下拉列表
	 * @return
	 */
	public List<AppVersion> selAppVersionList();

	/**
	 * 版本型号是否存在
	 * @param appversion
	 * @return
	 */
	public Integer appVersionIsExist(AppVersion appversion);

	/**
	 * 增加版本型号
	 * @param appversion
	 * @return
	 */
	public int addAppVersion(AppVersion appversion);

	/**
	 * 修改版本型号
	 * @param appversion
	 * @return
	 */
	public int updateAppVersion(AppVersion appversion);

	/**
	 * 删除版本型号
	 * @param parseInt
	 * @return
	 */
	public int deleteAppVersion(int id);

	/**
	 * 某版本类型下的型号列表
	 * @param parseInt
	 * @return
	 */
	public int queryGorupVersion(int id);

}
