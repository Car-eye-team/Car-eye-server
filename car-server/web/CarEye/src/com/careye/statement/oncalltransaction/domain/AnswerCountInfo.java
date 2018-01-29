/**
* Description: car-eye车辆管理平台
* 文件名：AnswerCountInfo.java
* 版本信息：1.0
* 日期：2015-4-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.oncalltransaction.domain;

/**
 * @项目名称：car-eye
 * @类名称：AnswerCountInfo
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 下午03:58:32
 * @修改人：Yuqk
 * @修改时间：2015-4-3 下午03:58:32
 * @修改备注：
 * @version 1.0
 */
public class AnswerCountInfo {
	/**
	 * 记录id
	 */
	private Integer id;
	/**
	 * 集团id
	 */
	private Integer blocid;
	/**
	 * 集团名称
	 */
	private String blocname;
	/**
	 * 车辆id
	 */
	private Integer carid;
	/**
	 * 车牌号
	 */
	private String carnumber;
	/**
	 * 司机id
	 */
	private Integer driverid;
	/**
	 * 司机姓名
	 */
	private String drivername;
	/**
	 * 终端号
	 */
	private String terminal;
	/**
	 * 订单号
	 */
	private String orderid;
	/**
	 * 创建时间
	 */
	private String createtime;
	/**
	 * 统计开始时间
	 */
	private String stime;
	/**
	 * 统计结束时间
	 */
	private String etime;
	/**
	 * 中标状态
	 */
	private Integer zbstatus;
	/*
	 * getter setter
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
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
	public Integer getDriverid() {
		return driverid;
	}
	public void setDriverid(Integer driverid) {
		this.driverid = driverid;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public Integer getZbstatus() {
		return zbstatus;
	}
	public void setZbstatus(Integer zbstatus) {
		this.zbstatus = zbstatus;
	}
}

