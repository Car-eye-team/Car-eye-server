/**
* Description: 多森商用车平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service;

import java.util.List;
import java.util.Map;

import com.careye.message.domain.EventCar;
import com.careye.message.domain.EventSendRecord;
import com.careye.message.domain.EventSystem;


/**
 * @项目名称：FMS
 * @类名称：EventService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface EventService {
	
	/**
	 * id查询系统事件
	 * @param eventSystem
	 * @return
	 */
	public EventSystem selectEventSystemById(int id);
	
	
	/**
	 *通 过   车牌号   查询车辆事件列表
	 * @param eventSystem
	 * @return
	 */
	public List<EventCar> selectCheckeventCarByCarNumber(EventCar eventCar);
	
	/**
	 *通 过   ID   查询车辆事件列表
	 * @param eventSystem
	 * @return
	 */
	public EventCar selectEventCarById(int id);
	
	/**
	 * 删除系统事件时查看车辆事件中是否已存在
	 * @param eventSystem
	 * @return
	 */
	public int queryEventCarIsExist(int eventid);
	
	
	/**
	 * 更新事件发送记录
	 * @param eventSystem
	 * @return
	 */
	public int updateEventSendRecord(EventCar eventCar);
	
	/**
	 * 根据流水号更新时间处理结果
	 * @param eventCar
	 * @return
	 */
	public int updateEventResult(EventCar eventCar);
	
	/**
	 * 更新事件报告
	 * @param eventCar
	 * @return
	 */
	public int updateEventReport(EventCar eventCar);
	
	
	/**
	 * 添加事件发送记录
	 * @param eventSystem
	 * @return
	 */
	public int insertEventSendRecord(EventCar eventCar);
	
	/**
	 * 更新车辆事件
	 * @param eventSystem
	 * @return
	 */
	public int updateEventCar(EventCar eventCar);
	
	
	/**
	 * 添加车辆事件
	 * @param eventSystem
	 * @return
	 */
	public int insertEventCar(EventCar eventCar);
	

	
	/**
	 * 更新系统事件
	 * @param eventSystem
	 * @return
	 */
	public int updateEventSystem(EventSystem eventSystem);
	
	
	/**
	 * 删除系统事件
	 * @param id
	 * @return
	 */
	public int deleteEventSystem(int id);
	
	/**
	 *   删除车辆事件
	 * @param id
	 * @return
	 */
	public int deleteEventCar(EventCar eventCar);
	
	/**
	 * 添加系统事件
	 * @param eventSystem
	 * @return
	 */
	public int insertEventSystem(EventSystem eventSystem);
	
	
	/**
	 * 条件查询系统事件
	 * @param eventSystem
	 * @return
	 */
	public Map selectCheckEventSystem(final int currentPage, final int everyPage,EventSystem eventSystem);

	/**
	 * 查询事件下发记录列表
	 * @param EventSendRecord
	 * @return
	 */
	public Map selectCheckEventSendRecord(final int currentPage, final int everyPage,EventSendRecord eventSendRecord);
	/**
	 * 通 过   车牌号   查询车辆事件列表
	 * @param EventCar
	 * @return
	 */
	public Map selectCheckeventCarByCarNumber(final int currentPage, final int everyPage,EventCar eventCar);
	
	
}
