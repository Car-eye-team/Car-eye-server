/**
 * Description: car-eye车辆管理平台
 * 文件名：FeedbackAdvice.java
 * 版本信息：1.0
 * 日期：2015-8-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.domain;

import com.careye.base.action.BaseDomain;


/**
 * @项目名称：car-eye
 * @类名称：FeedbackAdvice
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-26 下午03:57:06
 * @修改人：Administrator
 * @修改时间：2015-8-26 下午03:57:06
 * @修改备注：
 * @version 1.0
 */
public class FeedbackAdvice extends BaseDomain{
	private Integer id;
	private String createtime;
	private String version;
	private String typeid;
	private String feedbackcontent;
	private String dealcontent;
	private String status;
	private String typename;
	private String versionname;
	private String creater;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getFeedbackcontent() {
		return feedbackcontent;
	}
	public void setFeedbackcontent(String feedbackcontent) {
		this.feedbackcontent = feedbackcontent;
	}
	public String getDealcontent() {
		return dealcontent;
	}
	public void setDealcontent(String dealcontent) {
		this.dealcontent = dealcontent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getVersionname() {
		return versionname;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	

}
