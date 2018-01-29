/**
 * Description: car-eye车辆管理平台
 * 文件名：RemoteControlReocordServiceImp.java
 * 版本信息：1.0
 * 日期：2015-3-24
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.monitor.domain.RemoteControl;
import com.careye.monitor.domain.TerminalSet;
import com.careye.monitor.service.RemoteControlRecordService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：RemoteControlReocordServiceImp
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-24 上午09:36:12
 * @修改人：Yuqk
 * @修改时间：2015-3-24 上午09:36:12
 * @修改备注：
 * @version 1.0
 */
public class RemoteControlReocordServiceImpl extends GenericService implements
		RemoteControlRecordService {
	private  SysOperateLogService logService;
	/**
	 *分页查询远程控制记录
	 */
	@Override
	public Map findPageRecordList(Integer page, Integer limit,
			RemoteControl remoteControl) {
			logService.addLogInfo(SessionUtils.getUserId(), "查询远程控制记录", 5);
		return this.baseDao.findPageList(
				"oracle-remoteControlRecordSQL.queryPageRemoteControlRecord",
				"oracle-remoteControlRecordSQL.queryRemoteControlRecordCount",
				remoteControl, page, limit);
	}

	/**
	 * 不分页查询远程控制记录用于导出
	 */
	@Override
	public List findRecordList(RemoteControl remoteControl) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出远程控制记录", 4);
		return this.baseDao.queryForList(
				"oracle-remoteControlRecordSQL.queryPageRemoteControlRecord",
				remoteControl);
	}

	/**
	 * 分页查询终端参数设置记录
	 * @param page
	 * @param limit
	 * @param remoteControlRecord
	 * @return
	 */
	public Map findPageTerminalSetList(Integer page, Integer limit,
			TerminalSet terminalSet){
		return this.baseDao.findPageList(
				"oracle-remoteControlRecordSQL.queryPageTerminalSetRecord",
				"oracle-remoteControlRecordSQL.queryTerminalSetRecordCount",
				terminalSet, page, limit);
	}
	
	
	/**
	 * 不分页查询终端参数设置记录    用于导出
	 * @param remoteControlRecord
	 * @return
	 */
	
	public List findTerminalSetList(TerminalSet terminalSet){
		return this.baseDao.queryForList(
				"oracle-remoteControlRecordSQL.queryPageTerminalSetRecord",
				terminalSet);
	}
	
	
	/**
	 * 添加远程控制记录
	 * @param BlocUser
	 */
	public int addRemoteControl(RemoteControl remoteControl){
		remoteControl.setUserid(SessionUtils.getUserId());
		remoteControl.setCreatetime(DateUtil.getSQLDate());
		logService.addLogInfo(SessionUtils.getUserId(), "添加远程控制记录", 1);
		return this.baseDao.save("oracle-remoteControlRecordSQL.addRemoteControl", remoteControl);
	}
	
	/**
	 * 添加终端参数设置记录
	 * @param BlocUser
	 */
	public int addTerminalSet(TerminalSet terminalSet){
		terminalSet.setUserid(SessionUtils.getUserId());
		terminalSet.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-remoteControlRecordSQL.addTerminalSet", terminalSet);
	}
	
	
	/*
	 * getter  setter
	 */
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}


}
