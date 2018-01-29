/**
 * Description: car-eye车辆管理平台
 * 文件名：InBoundInfo.java
 * 版本信息：1.0
 * 日期：2015-6-4
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.statement.oncalltransaction.domain;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：InBoundInfo
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-6-4 下午03:00:19
 * @修改人：Yuqk
 * @修改时间：2015-6-4 下午03:00:19
 * @修改备注：
 * @version 1.0
 */
public class InSystemInfo extends BaseDomain {
	/**
	 *主键 
	 */
	private Integer id;
	/**
	 * 客户id
	 */
	private Integer cid;
	/**
	 * 客户名称
	 */
	private String cusname;
	/**
	 * 坐席id
	 */
	private Integer agentid;
	/**
	 * 来电号码
	 */
	private String callnumber;
	/**
	 * 来电时间
	 */
	private String inboundcalltime;
	/**
	 * 挂断时间
	 */
	private String hangupcalltime;

	/*
	 * getter setter
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getCallnumber() {
		return callnumber;
	}

	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}

	public String getInboundcalltime() {
		return inboundcalltime;
	}

	public void setInboundcalltime(String inboundcalltime) {
		this.inboundcalltime = inboundcalltime;
	}

	public String getHangupcalltime() {
		return hangupcalltime;
	}

	public void setHangupcalltime(String hangupcalltime) {
		this.hangupcalltime = hangupcalltime;
	}
}
