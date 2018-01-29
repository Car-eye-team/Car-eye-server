/**
 * Description: car-eye车辆管理平台
 * 文件名：RemoteControlRecordService.java
 * 版本信息：1.0
 * 日期：2015-3-23
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.monitor.service;

import java.util.List;
import java.util.Map;

import com.careye.monitor.domain.DispatcherInfo;
import com.careye.monitor.domain.DispatcherRecord;
import com.careye.monitor.domain.RemoteControl;

/**
 * @项目名称：car-eye
 * @类名称：RemoteControlRecordService
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-24 上午09:33:24
 * @修改人：Yuqk
 * @修改时间：2015-3-24 上午09:33:24
 * @修改备注：
 * @version 1.0
 */
public interface DispatcherRecordService {

	/**
	 * 分页查询调度记录
	 * @param page
	 * @param limit
	 * @param remoteControlRecord
	 * @return
	 */
	Map queryDispatcherRecord(Integer page, Integer limit,DispatcherRecord dispatcherRecord);
	
	
	/**
	 * 不分页查询调度记录 
	 * @param remoteControlRecord
	 * @return
	 */
	
	List<DispatcherRecord> selectRecordList(DispatcherRecord dispatcherRecord);
	
	
	
	/**
	 * 分页查询调度信息
	 * @param page
	 * @param limit
	 * @param remoteControlRecord
	 * @return
	 */
	Map queryDispatcherInfo(Integer page, Integer limit,DispatcherInfo dispatcherInfo);

	
	/**
	 * 添加调度信息
	 * @param dispatcherRecord
	 * @return
	 */
	public  int insertDispatcherInfo(DispatcherInfo dispatcherInfo);

	/**
	 * 修改调度信息
	 * @param dispatcherRecord
	 * @return
	 */
	public  int updateDispatcherInfo(DispatcherInfo dispatcherInfo);
	
	
}
