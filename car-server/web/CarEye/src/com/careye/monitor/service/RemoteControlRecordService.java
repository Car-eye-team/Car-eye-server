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

import com.careye.monitor.domain.RemoteControl;
import com.careye.monitor.domain.TerminalSet;
import com.careye.system.domain.BlocUser;

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
public interface RemoteControlRecordService {

	/**
	 * 分页查询远程控制记录
	 * @param page
	 * @param limit
	 * @param remoteControlRecord
	 * @return
	 */
	public Map findPageRecordList(Integer page, Integer limit,
			RemoteControl remoteControl);
	/**
	 * 不分页查询远程控制记录    用于导出
	 * @param remoteControlRecord
	 * @return
	 */
	
	public List findRecordList(RemoteControl remoteControl);
	/**
	 * 分页查询终端参数设置记录
	 * @param page
	 * @param limit
	 * @param remoteControlRecord
	 * @return
	 */
	public Map findPageTerminalSetList(Integer page, Integer limit,
			TerminalSet terminalSet);
	/**
	 * 不分页查询终端参数设置记录    用于导出
	 * @param remoteControlRecord
	 * @return
	 */
	
	public List findTerminalSetList(TerminalSet terminalSet);
	
	/**
	 * 添加终端参数设置记录
	 * @param BlocUser
	 */
	public int addTerminalSet(TerminalSet terminalSet);
	/**
	 * 添加远程控制记录
	 * @param BlocUser
	 */
	public int addRemoteControl(RemoteControl remoteControl);

}
