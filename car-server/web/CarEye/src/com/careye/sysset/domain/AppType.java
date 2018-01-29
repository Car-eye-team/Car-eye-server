/**
 * Description: car-eye车辆管理平台
 * 文件名：AppType.java
 * 版本信息：1.0
 * 日期：2015-8-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.domain;

import com.careye.base.action.BaseDomain;


/**
 * @项目名称：car-eye
 * @类名称：AppType
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-20 上午11:44:02
 * @修改人：Administrator
 * @修改时间：2015-8-20 上午11:44:02
 * @修改备注：
 * @version 1.0
 */
public class AppType extends BaseDomain{
	private Integer id;
	private String typename;
	private String createtime;
	private String remark;
	private String creater;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
}
