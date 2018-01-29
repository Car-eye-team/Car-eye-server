/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoServiceImpl.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.common.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.domain.CityInfo;
import com.careye.common.domain.CityInfoCache;
import com.careye.common.domain.Entry;
import com.careye.common.service.CityInfoService;

/**
 * @项目名称：car-eye
 * @类名称：CityInfoServiceImpl
 * @类描述：
 * @创建人：liliang
 * @创建时间：2013-8-5 上午10:26:07
 * @修改人：liliang
 * @修改时间：2013-8-5 上午10:26:07
 * @修改备注：
 * @version 1.0
 */
public class CityInfoServiceImpl extends GenericService implements
		CityInfoService {

	/**
	 * 查询城市区域列表
	 * 
	 * @param cityInfo
	 *            查询条件，如parentId 为 0 ，表示查询省级行政区
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CityInfo> getCityInfoList(CityInfo cityInfo) {

		return this.baseDao.queryForList("oracle-cityInfoSQL.getCityList",
				cityInfo);

	}

	/**
	 * 通过行政编码得到区域信息
	 * 
	 * @return
	 */
	public CityInfo getCityInfosBySZCODE(String szcode) {
		return (CityInfo) this.baseDao.queryForObject(
				"oracle-cityInfoSQL.getCityInfosBySZCODE", szcode);
	}

	/**
	 * 通过行政编码得到区域信息
	 * 
	 * @param szcodes
	 *            list集合
	 * @return
	 */
	public List<CityInfo> getCityInfosBySZCODE(List<String> szcodes) {
		return this.baseDao.queryForList(
				"oracle-cityInfoSQL.getCityInfosBySZCODE", szcodes);
	}

	/**
	 * 得到省级行政列表
	 * 
	 * @return
	 */
	public List<Entry> getProvinceList() {
		return CityInfoCache.getProvinceList();
	}

	/**
	 * 得到市级列表
	 * 
	 * @param parentCode
	 *            通过父级行政编码，查询对应的下级区域列表
	 * @return
	 */
	public List<Entry> getCityList(String parentCode) {
		return CityInfoCache.getCityListByParentCode(parentCode);
	}

	/**
	 * 得到县级列表
	 * 
	 * @param parentCode
	 *            通过父级行政编码，查询对应的下级区域列表
	 * @return
	 */
	public List<Entry> getDistrictList(String parentCode) {
		return CityInfoCache.getDistrictByParentCode(parentCode);
	}

	/**
	 * 根据城市名称获取行政区编码
	 */
	@Override
	public String getCityCode(String cityname) {
		return (String) this.baseDao.queryForObject(
				"oracle-cityInfoSQL.getCityCode", cityname);
	}

	/**
	 * 根据城市名称获取城市信息
	 * 
	 * @param cityname
	 * @return
	 */
	@Override
	public CityInfo getCityInfo(String cityname) {
		return (CityInfo) this.baseDao.queryForObject(
				"oracle-cityInfoSQL.getCityInfo", cityname);
	}

	/**
	 * 根据省份城市名称获取市级城市信息
	 * 
	 * @param pname
	 * @return
	 */
	@Override
	public List<CityInfo> getProvinceCityList(String pname) {
		return this.baseDao.queryForList(
				"oracle-cityInfoSQL.getProvinceCityList", pname);
	}

	/**
	 * 根据上级编码获取下级编码和名称
	 */
	@Override
	public List<Map<Long, String>> findMapByParentCode(Long code) {
		return this.baseDao.queryForList(
				"oracle-cityInfoSQL.findMapByParentCode", code);
	}

	/**
	 * 根据下级编码查询上级编码和名称
	 * 
	 * @return
	 */
	@Override
	public Map<Long, String> findMapByChildCode(Long code) {
		return (Map<Long, String>) this.baseDao.queryForObject(
				"oracle-cityInfoSQL.findMapByChildCode", code);
	}

	/**
	 * 得到行政编码
	 * 
	 * @param cityInfo
	 * @return
	 */
	// public String getSZCODE(CityInfo cityInfo) {
	// return (String)
	// this.baseDao.queryForObject("oracle-cityInfoSQL.getSZCODE", cityInfo);
	// }

}
