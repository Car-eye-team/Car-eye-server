/**
* Description: car-eye车辆管理平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service;

import java.util.Map;

import com.careye.car.domain.AreaCar;
import com.careye.car.domain.AreaSet;
import com.careye.mq.domain.ZoneAlarm;

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
public interface AreaSetService {

	/**
	 * 条件查询系统区域信息分页
	 * @param areaSet
	 * @return
	 */
	public Map queryPageAreaSetList(final int currentPage, final int everyPage,AreaSet areaSet);

	/**
	 * 添加系统区域信息
	 * @param areaSet
	 * @return
	 */
	public int insertAreaSet(AreaSet areaSet);
	
	
	/**
	 * 删除系统区域信息
	 * @param id
	 * @return
	 */
	public int deleteAreaSet(int id);
	
	
	/**
	 * 更新系统区域信息
	 * @param areaSet
	 * @return
	 */
	public int updateAreaSet(AreaSet areaSet);
	
	
	
	/**
	 * 查看车辆区域追加数量
	 * @param areaCar
	 * @return
	 */
	public int queryAreaSetCount(int areaid);
	
	/**
	 * 系统区域详情
	 * @param areaSet
	 * @return
	 */
	public ZoneAlarm queryAreaById(int id);
	
	
	/**
	 * 查看区域下发记录
	 * @param areaSet
	 * @return
	 */
	public Map queryAreaSendRecordList(final int currentPage, final int everyPage,AreaSet areaSet);

	/**
	 * 条件查询车辆区域信息分页
	 * @param areaCar
	 * @return
	 */
	public Map queryPageAreaCarList(final int currentPage, final int everyPage,AreaCar areaCar);

	/**
	 * 删除车辆区域信息
	 * @param id
	 * @return
	 */
	public int deleteAreaCar(int id);
	
	/**
	 * 根据车辆区域id查看区域详情
	 * @param areaCar
	 * @return
	 */
	public AreaSet getAreaCarDetail(int id);
	
	/**
	 * 添加车辆区域操作记录表
	 * @param areaCar
	 * @return
	 */
	public int insertAreaCarRecord(ZoneAlarm zoneAlarm);
	
	public String getCarnumberByTer(String terminal);
	
	/**
	 * 添加车辆区域信息
	 * @param areaCar
	 * @return
	 */
	public int insertAreaCar(AreaCar areaCar);
	/**
	 * 查看车辆区域是否存在
	 * @param areaCar
	 * @return
	 */
	public int queryAreaCarIsExist(AreaCar areaCar);
	
	/**
	 * 更新区域处理结果
	 * @param areaSet
	 * @return
	 */
	public int updateAreaCarResult(AreaSet areaSet);
	
	/**
	 * 根据区域id查看区域详情
	 * @param areaCar
	 * @return
	 */
	public AreaSet getAreaSetDetail(int id);
	
}










