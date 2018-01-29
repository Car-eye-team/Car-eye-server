/**
* Description: car-eye车辆管理平台
* 文件名：MemberLoginLog.java
* 版本信息：1.0
* 日期：2013-9-28
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.api.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;



/**
 * @项目名称：fms
 * @类名称：AdviceFeedBack
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-12-1 上午14:47:56
 * @修改人：zhangrong
 * @修改时间：2014-12-1 上午14:47:56
 * @修改备注：
 * @version 1.0
 */
public class AdviceFeedBack extends BaseDomain implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**APP版本类型*/
	private Integer typeid;
	/**APP版本类型名称*/
	private String typename;
	/**APP版本名称*/
	private String versionname;
	/**版本号*/
	private String version;
	/**用户名*/
	private String loginname;
	/**时间*/
	private String createtime;
	/**意见反馈内容*/
	private String feedbackcontent;
	/**处理内容*/
	private String dealcontent;
	/**意见处理状态 默认1， 1未处理 2 已处理*/
	private Integer status;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
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
	
}
