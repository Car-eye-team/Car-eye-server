/**
 * Description: car-eye车辆管理平台
 * 文件名：OnCallCountService.java
 * 版本信息：1.0
 * 日期：2015-4-3
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.statement.oncalltransaction.service;

import java.util.List;
import java.util.Map;

import com.careye.statement.oncalltransaction.domain.InBoundInfo;
import com.careye.statement.oncalltransaction.domain.InSystemInfo;
import com.careye.statement.oncalltransaction.domain.OnCallCountInfo;
import com.careye.transaction.domain.Transaction;

/**
 * @项目名称：car-eye
 * @类名称：OnCallCountService
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 上午10:38:22
 * @修改人：Yuqk
 * @修改时间：2015-4-3 上午10:38:22
 * @修改备注：
 * @version 1.0
 */
public interface OnCallCountService {
	/**
	 * 查询电召统计数据
	 * 
	 * @param onCallCountInfo
	 * @return
	 */
	public List<OnCallCountInfo> findOnCallCountData(
			OnCallCountInfo onCallCountInfo);

	/**
	 * 导出拨入电话
	 * @param inBoundInfo
	 * @return
	 */
	public List<InBoundInfo> exportInBoundList(OnCallCountInfo onCallCountInfo);


	/**
	 * 查询拨入电话
	 * @param page
	 * @param limit
	 * @param onCallCountInfo
	 * @return
	 */
	public Map findInBoundPageList(Integer page, Integer limit,
			OnCallCountInfo onCallCountInfo);

	/**
	 * 导出接入系统
	 * @param onCallCountInfo
	 * @return
	 */
	public List<InSystemInfo> exportInSystemList(OnCallCountInfo onCallCountInfo);

	/**
	 * 查询接入系统
	 * @param page
	 * @param limit
	 * @param onCallCountInfo
	 * @return
	 */
	public Map findInSystemPageList(Integer page, Integer limit,
			OnCallCountInfo onCallCountInfo);

	/**
	 * 查询业务
	 * @param onCallCountInfo
	 * @return
	 */
	public Map findTransactionList(OnCallCountInfo onCallCountInfo,Integer page,Integer limit);

	/**
	 * 导出业务
	 * @param onCallCountInfo
	 * @return
	 */
	public List<Transaction> exportTransactionList(
			OnCallCountInfo onCallCountInfo);
}
