/**
 * Description: car-eye车辆管理平台
 * 文件名：FeedbackAdviceAction.java
 * 版本信息：1.0
 * 日期：2015-8-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.sysset.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.sysset.domain.FeedbackAdvice;
import com.careye.sysset.service.FeedbackAdviceService;
import com.careye.utils.StringUtils;


import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：FeedbackAdviceAction
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015-8-26 下午03:55:26
 * @修改人：Administrator
 * @修改时间：2015-8-26 下午03:55:26
 * @修改备注：
 * @version 1.0
 */
public class FeedbackAdviceAction extends BasePageAction {
	private static final Logger logger = Logger
			.getLogger(FeedbackAdviceAction.class);
	private FeedbackAdviceService feedbackservice;
	private FeedbackAdvice feedbackadvice;
	private Map result;
	private List list;
	private String ids;
    private String typeid;
    private String s_time;
    private String e_time;
    private String success;
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	public String feedbackadviceList() {
		try {
			initMap();
			if (feedbackadvice == null) {
				feedbackadvice = new FeedbackAdvice();
			}
			if (StringUtils.isNotEmty(typeid)) {
				feedbackadvice.setTypeid(typeid);
			}
			if (StringUtils.isNotEmty(s_time)) {
				feedbackadvice.setStime(s_time);
			}
			if (StringUtils.isNotEmty(e_time)) {
				feedbackadvice.setEtime(e_time);
			}
			result = feedbackservice.findPageFeedbackAdviceList(this.getPage(),
					this.getLimit(), feedbackadvice);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("FeedbackAdviceAction的方法 feedbackadviceList执行出错，原因：" + e, e);
			return ERROR;
		}

	}
	public String saveAdviceDispose() {
		try {
			initMap();
			if (feedbackadvice == null) {
				return ERROR;
			}
			int count = 0;
			if (feedbackadvice.getId()!= null) {
				count = feedbackservice.disposeAdvice(feedbackadvice);
			} 
			if (count > 0) {
				result.put("returnType", 0);
				this.success = "true";
			} else {
				result.put("returnType", -2);
			}

			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("FeedbackAdviceAction的方法 saveAdviceDispose执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	public String deleteFeedbackAdvice() {
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = feedbackservice.deleteFeedbackAdvice(Integer.parseInt(id[i]));
				if (re <= 0) {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("FeedbackAdviceAction 的方法 deleteFeedbackAdvice执行出错，原因：" + e, e);
			return ERROR;
		}

	}
	public FeedbackAdviceService getFeedbackservice() {
		return feedbackservice;
	}

	public void setFeedbackservice(FeedbackAdviceService feedbackservice) {
		this.feedbackservice = feedbackservice;
	}

	public FeedbackAdvice getFeedbackadvice() {
		return feedbackadvice;
	}

	public void setFeedbackadvice(FeedbackAdvice feedbackadvice) {
		this.feedbackadvice = feedbackadvice;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String sTime) {
		s_time = sTime;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String eTime) {
		e_time = eTime;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	

}
