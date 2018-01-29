/**
* Description: car-eye车辆管理平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.AreaCar;
import com.careye.car.domain.AreaSet;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.statement.domain.AreaMore;

/**
 * @项目名称：FMS
 * @类名称：AreaSetService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface AreaMoreService {

	/**
	 * 条件查询多区域信息分页
	 * @param areaSet
	 * @return
	 */
	public Map queryPageAreaMoreList(final int currentPage, final int everyPage,AreaMore areaMore);
	
	
	/**
	 * 条件查询根据多区域信息车辆位置信息
	 * @param areaSet
	 * @return
	 */
	public List<TerminalPositionInfo> queryCarPositionInfoList(TerminalPositionInfo terminalPositionInfo,TerminalPositionInfo te);

	/**
	 * 条件查询根据多区域信息车辆位置信息
	 * @param areaSet
	 * @return
	 */
	public List<TerminalPositionInfo> queryCarPositionInfoTwoList(TerminalPositionInfo terminalPositionInfo);

	/**
	 * 添加多区域信息
	 * @param areaSet
	 * @return
	 */
	public int insertAreaMore(AreaMore areaMore);
	
	
	/**
	 * 删除多区域信息
	 * @param id
	 * @return
	 */
	public int deleteAreaMore(int id);
	
	
	/**
	 * 更新多区域信息
	 * @param areaSet
	 * @return
	 */
	public int updateAreaMore(AreaMore areaMore);
	
	
	
	/**
	 * 查询是否相同的名字存在
	 * 
	 * @param name
	 * @return
	 */
	public Integer queryAreaMoreNameIsExits(AreaMore areaMore);
	
	
}







