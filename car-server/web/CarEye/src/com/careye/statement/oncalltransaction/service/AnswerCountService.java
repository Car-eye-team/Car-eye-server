/**
 * Description: car-eye车辆管理平台
 * 文件名：AnswerCountService.java
 * 版本信息：1.0
 * 日期：2015-4-3
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.statement.oncalltransaction.service;

import java.util.List;
import java.util.Map;

import com.careye.statement.oncalltransaction.domain.AnswerCountInfo;

/**
 * @项目名称：car-eye
 * @类名称：AnswerCountService
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 下午04:05:50
 * @修改人：Yuqk
 * @修改时间：2015-4-3 下午04:05:50
 * @修改备注：
 * @version 1.0
 */
public interface AnswerCountService {
	/**
	 * 分页查询抢答数据列表
	 * 
	 * @return
	 */
	public Map findPageAnswerCountList(AnswerCountInfo answerCountInfo,
			int page, int limit);

	/**
	 * 导出抢答数据列表
	 * 
	 * @return
	 */
	public List<AnswerCountInfo> excelExportAnswerCountList(
			AnswerCountInfo answerCountInfo);

	/**
	 * 导出派车成功数据列表
	 * 
	 * @return
	 */
	public List<AnswerCountInfo> excelExportSendSuccessList(
			AnswerCountInfo answerCountInfo);
	/**
	 * 分页查询派车成功数据列表
	 * 
	 * @return
	 */
	public Map findPageSendSuccessList(AnswerCountInfo answerCountInfo,
			Integer page, Integer limit);
}
