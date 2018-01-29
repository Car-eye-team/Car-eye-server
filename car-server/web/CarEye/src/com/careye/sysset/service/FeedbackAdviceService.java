/**
 * Description: car-eye车辆管理平台
 * 文件名：FeedbackAdviceService.java
 * 版本信息：1.0
 * 日期：2015-8-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.service;

import java.util.Map;

import com.careye.sysset.domain.FeedbackAdvice;


/**
 * @项目名称：car-eye
 * @类名称：FeedbackAdviceService
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-26 下午04:02:16
 * @修改人：Administrator
 * @修改时间：2015-8-26 下午04:02:16
 * @修改备注：
 * @version 1.0
 */
public interface FeedbackAdviceService {

	/**
	 * 分页意见反馈列表
	 * @param page
	 * @param limit
	 * @param feedbackadvice
	 * @return
	 */
	public Map findPageFeedbackAdviceList(Integer currentPage, Integer everyPage,
			FeedbackAdvice feedbackadvice);

	/**
	 * 处理意见
	 * @param feedbackadvice
	 * @return
	 */
	public int disposeAdvice(FeedbackAdvice feedbackadvice);

	/**
	 * 删除意见反馈
	 * @param parseInt
	 * @return
	 */
	public int deleteFeedbackAdvice(int id);

}
