/**
 * Description: car-eye车辆管理平台
 * 文件名：OnCallCountServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-4-3
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.statement.oncalltransaction.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.statement.oncalltransaction.domain.InBoundInfo;
import com.careye.statement.oncalltransaction.domain.InSystemInfo;
import com.careye.statement.oncalltransaction.domain.OnCallCountInfo;
import com.careye.statement.oncalltransaction.service.OnCallCountService;
import com.careye.transaction.domain.Transaction;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：OnCallCountServiceImpl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 上午10:39:25
 * @修改人：Yuqk
 * @修改时间：2015-4-3 上午10:39:25
 * @修改备注：
 * @version 1.0
 */
public class OnCallCountServiceImpl extends GenericService implements
		OnCallCountService {
	private SysOperateLogService logService;

	/**
	 * @see OnCallCountService
	 */
	@Override
	public List<OnCallCountInfo> findOnCallCountData(
			OnCallCountInfo onCallCountInfo) {
		logService.addLogInfo(SessionUtils.getUserId(), "查询电召统计数据", 5);
		return this.baseDao.queryForList(
				"oracle-onCallCountSQL.findOnCallCountData", onCallCountInfo);
	}

	/**
	 * getter setter
	 * 
	 * @return
	 */
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	/**
	 * exportInBoundList TODO
	 * 
	 * @param onCallCountInfo
	 * @return
	 * @see com.careye.statement.oncalltransaction.service.OnCallCountService#exportInBoundList(com.careye.statement.oncalltransaction.domain.OnCallCountInfo)
	 */
	@Override
	public List<InBoundInfo> exportInBoundList(OnCallCountInfo onCallCountInfo) {
		return this.baseDao.queryForList(
				"oracle-onCallCountSQL.findInBoundList", onCallCountInfo);
	}

	/**
	 * exportInSystemList TODO
	 * 
	 * @param onCallCountInfo
	 * @return
	 * @see com.careye.statement.oncalltransaction.service.OnCallCountService#exportInSystemList(com.careye.statement.oncalltransaction.domain.OnCallCountInfo)
	 */
	@Override
	public List<InSystemInfo> exportInSystemList(OnCallCountInfo onCallCountInfo) {
		return this.baseDao.queryForList(
				"oracle-onCallCountSQL.findInSystemList", onCallCountInfo);
	}

	/**
	 * exportTransactionList TODO
	 * 
	 * @param onCallCountInfo
	 * @return
	 * @see com.careye.statement.oncalltransaction.service.OnCallCountService#exportTransactionList(com.careye.statement.oncalltransaction.domain.OnCallCountInfo)
	 */
	@Override
	public List<Transaction> exportTransactionList(
			OnCallCountInfo onCallCountInfo) {
		return this.baseDao
				.queryForList("oracle-onCallCountSQL.findTransactionList",onCallCountInfo);
	}

	/**
	 * findInBoundPageList TODO
	 * 
	 * @param page
	 * @param limit
	 * @param onCallCountInfo
	 * @return
	 * @see com.careye.statement.oncalltransaction.service.OnCallCountService#findInBoundPageList(java.lang.Integer,
	 *      java.lang.Integer,
	 *      com.careye.statement.oncalltransaction.domain.OnCallCountInfo)
	 */
	@Override
	public Map findInBoundPageList(Integer page, Integer limit,
			OnCallCountInfo onCallCountInfo) {
		return this.baseDao.findPageList(
				"oracle-onCallCountSQL.findInBoundList",
				"oracle-onCallCountSQL.findInBoundListCount", onCallCountInfo,
				page, limit);
	}

	/**
	 * findInSystemPageList TODO
	 * 
	 * @param page
	 * @param limit
	 * @param onCallCountInfo
	 * @return
	 * @see com.careye.statement.oncalltransaction.service.OnCallCountService#findInSystemPageList(java.lang.Integer,
	 *      java.lang.Integer,
	 *      com.careye.statement.oncalltransaction.domain.OnCallCountInfo)
	 */
	@Override
	public Map findInSystemPageList(Integer page, Integer limit,
			OnCallCountInfo onCallCountInfo) {
		return this.baseDao.findPageList(
				"oracle-onCallCountSQL.findInSystemList",
				"oracle-onCallCountSQL.findInSystemListCount", onCallCountInfo,
				page, limit);
	}

	/**
	 * findTransactionList TODO
	 * 
	 * @param onCallCountInfo
	 * @return
	 * @see com.careye.statement.oncalltransaction.service.OnCallCountService#findTransactionList(com.careye.statement.oncalltransaction.domain.OnCallCountInfo)
	 */
	@Override
	public Map findTransactionList(OnCallCountInfo onCallCountInfo,Integer page,Integer limit) {
		return this.baseDao.findPageList(
				"oracle-onCallCountSQL.findTransactionList",
				"oracle-onCallCountSQL.findTransactionListCount",
				onCallCountInfo, page, limit);
	}

}
