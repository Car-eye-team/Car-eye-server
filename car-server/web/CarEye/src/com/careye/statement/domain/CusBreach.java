/**
* Description: car-eye车辆管理平台
* 文件名：DrivingState.java
* 版本信息：1.0
* 日期：2014-6-11
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @项目名称：car-eye
 * @类名称：CusBreach
 * @类描述：客户违约数统计报表
 * @创建人：huangqin
 * @创建时间：2015-4-3 下午02:12:37
 * @修改人：huangqin
 * @修改时间：2015-4-3 下午02:12:37
 * @修改备注：
 * @version 1.0
 */
public class CusBreach extends  BaseDomain  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String  orderid;
	
	private String username;
	
	private String phone;
	
	private String wy;
	
	private String remark;
	
	private String canceltime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWy() {
		return wy;
	}

	public void setWy(String wy) {
		this.wy = wy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(String canceltime) {
		this.canceltime = canceltime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
