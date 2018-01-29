/**
* Description: 出租车系统
* 文件名：TranDriverCancel.java
* 版本信息：1.0
* 日期：2015-4-15
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.domain;

/**
 * @项目名称：TAXISERVER
 * @类名称：TranDriverCancel
 * @类描述：司机取实体
 * @创建人：zr
 * @创建时间：2015-4-15 下午05:10:48
 * @修改人：zr
 * @修改时间：2015-4-15 下午05:10:48
 * @修改备注：
 * @version 1.0
 */
public class TranDriverCancel {
	
	private Integer id;
	
	/**订单ID*/
	private String orderid;
	
	/**司机ID*/
	private Integer driverid;
	
	/**车辆ID*/
	private Integer carid;
	
	/**车牌号*/
	private String carnumber;
	
	/**是否违约	0 未违约 1 违约*/
	private Integer wy;
	
	/**取消时间*/
	private String canceltime;
	
	/**备注*/
	private String remark;

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

	public Integer getDriverid() {
		return driverid;
	}

	public void setDriverid(Integer driverid) {
		this.driverid = driverid;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getWy() {
		return wy;
	}

	public void setWy(Integer wy) {
		this.wy = wy;
	}

	public String getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(String canceltime) {
		this.canceltime = canceltime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
