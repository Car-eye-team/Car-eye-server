/**
* Description: car-eye车辆管理平台
* 文件名：MemberService.java
* 版本信息：1.0
* 日期：2013-7-30
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.common.domain.CityInfo;
import com.careye.common.domain.Entry;



/**
 * @项目名称：car-eye
 * @类名称：CityInfoService
 * @类描述：获取省市区域信息
 * @创建人：liliang
 * @创建时间：2013-7-30 下午12:05:36
 * @修改人：liliang
 * @修改时间：2013-7-30 下午12:05:36
 * @修改备注：
 * @version 1.0
 */
public interface CityInfoService {
	
	/**
	 * 通过行政编码得到区域信息
	 * @param szcodes list集合
	 * @return 
	 */
	public List<CityInfo> getCityInfosBySZCODE(List<String> szcodes);
	
	/**
	 * 得到省级行政列表
	 * @return
	 */
	public List<Entry> getProvinceList();
	/**
	 * 得到市级列表
	 * @param parentCode 通过父级行政编码，查询对应的下级区域列表
	 * @return
	 */
	public List<Entry> getCityList(String parentCode);
	/**
	 * 得到县级列表
	 * @param parentCode 通过父级行政编码，查询对应的下级区域列表
	 * @return
	 */
	public List<Entry> getDistrictList(String parentCode);
	/**
	 * 得到行政编码
	 * @param cityInfo
	 * @return
	 */
	//public String getSZCODE(CityInfo cityInfo);
	
	/**
	 * 根据城市名称获取行政区编码
	 */
	public String getCityCode(String cityname);
	
	/**
	 * 根据城市名称获取城市信息
	 * @param cityname
	 * @return
	 */
	public CityInfo getCityInfo(String cityname);
	
	/**
	 * 根据省份城市名称获取市级城市信息
	 * @param pname
	 * @return
	 */
	public List<CityInfo> getProvinceCityList(String pname);
	/**
	 * 查询城市区域列表
	 * @param cityInfo  查询条件，如parentId 为 0 ，表示查询省级行政区
	 * @return
	 */
	public List<CityInfo> getCityInfoList(CityInfo cityInfo);
	/**
	 * 根据上级编码查询下级编码和名称
	 * @return
	 */
	public List<Map<Long,String>> findMapByParentCode(Long code);
	/**
	 * 根据下级编码查询上级编码和名称
	 * @return
	 */
	public Map<Long,String> findMapByChildCode(Long code);
}
