/**
* Description: 多森商用车平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.message.domain.EventCar;
import com.careye.message.domain.EventSendRecord;
import com.careye.message.domain.EventSystem;
import com.careye.message.domain.SendRecord;
import com.careye.message.service.EventService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：EventServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
public class EventServiceImpl extends GenericService implements EventService{

	/**
	 * id查询系统事件
	 * @param eventSystem
	 * @return
	 */
	@Override
	public EventSystem selectEventSystemById(int id) {
		// TODO Auto-generated method stub
		return (EventSystem)this.baseDao.queryForObject("oracle-eventSQL.selectEventSystemById", id);
	}
	
	/**
	 * 通 过   车牌号   查询车辆事件列表
	 * @param eventSystem
	 * @return
	 */
	@Override
	public List<EventCar> selectCheckeventCarByCarNumber(EventCar eventCar) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-eventSQL.selectCheckeventCarByCarNumber", eventCar);
	}
	/**
	 * 通 过   ID   查询车辆事件列表
	 * @param eventSystem
	 * @return
	 */
	@Override
	public EventCar selectEventCarById(int id) {
		// TODO Auto-generated method stub
		return (EventCar)this.baseDao.queryForObject("oracle-eventSQL.selectEventCarById", id);
	}
	/**
	 * 删除系统事件时查看车辆事件中是否已存在
	 * @param eventSystem
	 * @return
	 */
	@Override
	public int queryEventCarIsExist(int eventid) {
		// TODO Auto-generated method stub
		return this.baseDao.getCount("oracle-eventSQL.queryEventCarIsExist", eventid);
	}
	
	/**
	 * 添加事件发送记录
	 * @param eventSystem
	 * @return
	 */
	@Override
	public int insertEventSendRecord(EventCar eventCar) {
		// TODO Auto-generated method stub
		eventCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-eventSQL.insertEventSendRecord", eventCar);
	}


	/**
	 * 更新事件发送记录
	 * @param eventSystem
	 * @return
	 */
	@Override
	public int updateEventSendRecord(EventCar eventCar) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-eventSQL.updateEventSendRecord", eventCar);
	}

	/**
	 * 添加车辆事件
	 * @param eventSystem
	 * @return
	 */
	@Override
	public int insertEventCar(EventCar eventCar) {
		// TODO Auto-generated method stub
		eventCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-eventSQL.insertEventCar", eventCar);
	}


	/**
	 * 更新车辆事件
	 * @param eventSystem
	 * @return
	 */
	@Override
	public int updateEventCar(EventCar eventCar) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-eventSQL.updateEventCar", eventCar);
	}
	
	/**
	 * 删除车辆事件
	 * @param id
	 * @return
	 */
	@Override
	public int deleteEventCar(EventCar eventCar) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-eventSQL.deleteEventCar", eventCar);
	}
	
	/**
	 * 删除系统事件
	 * @param id
	 * @return
	 */
	@Override
	public int deleteEventSystem(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-eventSQL.deleteEventSystem", id);
	}

	/**
	 * 添加系统事件
	 * @param eventSystem
	 * @return
	 */
	@Override
	public int insertEventSystem(EventSystem eventSystem) {
		// TODO Auto-generated method stub
		eventSystem.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-eventSQL.insertEventSystem", eventSystem);
	}

     /**
	 * 查询系统事件
	 * @param eventSystem
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckEventSystem(int currentPage, int everyPage,
			EventSystem eventSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-eventSQL.selectCheckEventSystem",
				"oracle-eventSQL.selectEventSystem", eventSystem,currentPage,everyPage);
	}

	/**
	 * 更新系统事件
	 * @param eventSystem
	 * @return
	 */
	@Override
	public int updateEventSystem(EventSystem eventSystem) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-eventSQL.updateEventSystem", eventSystem);
	}

    /**
	 * 查询事件下发记录列表
	 * @param EventSendRecord
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckEventSendRecord(int currentPage, int everyPage,
			EventSendRecord eventSendRecord) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-eventSQL.selectCheckEventSendRecord",
				"oracle-eventSQL.selectEventSendRecordCount", eventSendRecord,currentPage,everyPage);
	}    
	/**
	 *  通 过   车牌号   查询车辆事件列表
	 * @param eventSystem
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckeventCarByCarNumber(int currentPage, int everyPage,
			EventCar eventCar) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-eventSQL.selectCheckeventCarByCarNumber",
				"oracle-eventSQL.selectEventCarByCarNumberCount", eventCar,currentPage,everyPage);
	}

	/**
	 * 根据流水号更新时间处理结果
	 * @param eventCar
	 * @return
	 */
	@Override
	public int updateEventResult(EventCar eventCar) {
		return this.baseDao.update("oracle-eventSQL.updateEventResult", eventCar);
	}

	/**
	 * 更新事件报告
	 * @param eventCar
	 * @return
	 */
	@Override
	public int updateEventReport(EventCar eventCar) {
		this.baseDao.update("oracle-eventSQL.updateEventCarReport", eventCar);
		return this.baseDao.update("oracle-eventSQL.updateEventReport", eventCar);
	}
	
}
