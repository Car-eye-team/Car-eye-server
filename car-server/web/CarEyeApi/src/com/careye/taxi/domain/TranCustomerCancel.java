/**
* Description: car-eye车辆管理平台
* 文件名：OrderInfo.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.taxi.domain;

import java.io.Serializable;

/**
 * @项目名称：OCS
 * @类名称：TranCustomerCancel
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-5-26 上午09:41:49
 * @修改人：huangqin
 * @修改时间：2015-5-260 上午09:41:49
 * @修改备注：
 * @version 1.0
 */
public class TranCustomerCancel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 主键**/
	private  Integer id;
	/** 订单Id**/
	private String  orderid; 
	/** 乘客姓名**/
	private String  username;
	/** 联系电话**/
	private String  phone;
	/** 是否违约**/
	private String  wy;
	/** 备注**/
	private String  remark; 
	/** 取消时间**/
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
	
}
