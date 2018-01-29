/**
 * Description: car-eye车辆管理平台
 * 文件名：AnswerCountServiceImpl.java
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
import com.careye.statement.oncalltransaction.domain.AnswerCountInfo;
import com.careye.statement.oncalltransaction.service.AnswerCountService;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：AnswerCountServiceImpl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 下午04:06:33
 * @修改人：Yuqk
 * @修改时间：2015-4-3 下午04:06:33
 * @修改备注：
 * @version 1.0
 */
public class AnswerCountServiceImpl extends GenericService implements
		AnswerCountService {
	private SysOperateLogService logService;
/**
 * @see AnswerCountService
 */
	@Override
	public List<AnswerCountInfo> excelExportAnswerCountList(
			AnswerCountInfo answerCountInfo) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出抢答数据列表", 4);
		return this.baseDao.queryForList(
				"oracle-answerCountSQL.findAnswerList", answerCountInfo);
	}

	@Override
	public Map findPageAnswerCountList(AnswerCountInfo answerCountInfo,
			int page, int limit) {
		logService.addLogInfo(SessionUtils.getUserId(), "分页查询抢答数据列表", 5);
		return this.baseDao.findPageList(
				"oracle-answerCountSQL.findAnswerList",
				"oracle-answerCountSQL.findAnswerListCount", answerCountInfo,
				page, limit);
	}
	@Override
	public List<AnswerCountInfo> excelExportSendSuccessList(
			AnswerCountInfo answerCountInfo) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出派车成功列表", 4);
		return this.baseDao.queryForList(
				"oracle-answerCountSQL.findSendSuccessList", answerCountInfo);
	}

	@Override
	public Map findPageSendSuccessList(AnswerCountInfo answerCountInfo,
			Integer page, Integer limit) {
		logService.addLogInfo(SessionUtils.getUserId(), "分页查询派车成功列表", 5);
		return this.baseDao.findPageList(
				"oracle-answerCountSQL.findSendSuccessList",
				"oracle-answerCountSQL.findSendSuccessListCount", answerCountInfo,
				page, limit);
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
}
