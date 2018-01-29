/**
* Description: car-eye车辆管理平台
* 文件名：OrderInfo.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.ocs.domain;

/**
 * @项目名称：OCS
 * @类名称：OrderInfo
 * @类描述：
 * @创建人：zr
 * @创建时间：2015-1-20 上午09:41:49
 * @修改人：zr
 * @修改时间：2015-1-20 上午09:41:49
 * @修改备注：
 * @version 1.0
 */
public class OrderInfo {
	
	/**订单ID  3开头乐嘀  5开头微信*/
	private String orderid;
	
	/**订单状态**/
	private Integer orderstatus;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	
	
}
