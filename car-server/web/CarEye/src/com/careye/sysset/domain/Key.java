/**
 * Description: car-eye车辆管理平台
 * 文件名：Key.java
 * 版本信息：1.0
 * 日期：2015-8-25
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.domain;

import com.careye.base.action.BaseDomain;


/**
 * @项目名称：car-eye
 * @类名称：Key
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-25 下午02:03:04
 * @修改人：Administrator
 * @修改时间：2015-8-25 下午02:03:04
 * @修改备注：
 * @version 1.0
 */
public class Key extends BaseDomain{
	private Integer id;
	private String typeid;
	private String key;
	private String descs;
	private String status;
	private String requestcount;
	private String createtime;
	private String creater;
	private String typename;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestcount() {
		return requestcount;
	}
	public void setRequestcount(String requestcount) {
		this.requestcount = requestcount;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	

}
