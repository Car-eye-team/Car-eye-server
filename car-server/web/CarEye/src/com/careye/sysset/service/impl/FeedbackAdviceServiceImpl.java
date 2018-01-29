/**
 * Description: car-eye车辆管理平台
 * 文件名：FeedbackAdviceServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-8-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.sysset.domain.FeedbackAdvice;
import com.careye.sysset.service.FeedbackAdviceService;


/**
 * @项目名称：car-eye
 * @类名称：FeedbackAdviceServiceImpl
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-26 下午04:02:44
 * @修改人：Administrator
 * @修改时间：2015-8-26 下午04:02:44
 * @修改备注：
 * @version 1.0
 */
public class FeedbackAdviceServiceImpl extends GenericService implements  FeedbackAdviceService{

	/**
	* 分页意见反馈列表
	* findPageFeedbackAdviceList
	* TODO
	* @param currentPage
	* @param everyPage
	* @param feedbackadvice
	* @return
	* @see com.duosen.gate.set.service.FeedbackAdviceService#findPageFeedbackAdviceList(java.lang.Integer, java.lang.Integer, com.duosen.gate.set.domain.FeedbackAdvice)
	*/
	@Override
	public Map findPageFeedbackAdviceList(Integer currentPage,
			Integer everyPage, FeedbackAdvice feedbackadvice) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-FeedbackAdviceSQL.findPageFeedbackAdviceList", "oracle-FeedbackAdviceSQL.findPageFeedbackAdviceListCount", feedbackadvice, currentPage, everyPage);
	}

	/**
	* 处理意见
	* disposeAdvice
	* TODO
	* @param feedbackadvice
	* @return
	* @see com.duosen.gate.set.service.FeedbackAdviceService#disposeAdvice(com.duosen.gate.set.domain.FeedbackAdvice)
	*/
	@Override
	public int disposeAdvice(FeedbackAdvice feedbackadvice) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-FeedbackAdviceSQL.disposeAdvice", feedbackadvice);
	}

	/**
	* 删除意见反馈
	* deleteFeedbackAdvice
	* TODO
	* @param parseInt
	* @return
	* @see com.duosen.gate.set.service.FeedbackAdviceService#deleteFeedbackAdvice(int)
	*/
	@Override
	public int deleteFeedbackAdvice(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-FeedbackAdviceSQL.deleteAdvice", id);
	}

}
