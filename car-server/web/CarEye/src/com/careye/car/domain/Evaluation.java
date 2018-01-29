/**
 * Description: car-eye车辆管理平台
 * 文件名：Evaluation.java
 * 版本信息：1.0
 * 日期：2015-3-27
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.car.domain;

/**
 * @项目名称：car-eye
 * @类名称：Evaluation
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-27 下午04:35:07
 * @修改人：Yuqk
 * @修改时间：2015-3-27 下午04:35:07
 * @修改备注：
 * @version 1.0
 */
public class Evaluation {
	/**
	 *主键id 
	 */
	private Integer id;
	/**
	 * 车辆id
	 */
	private Integer carid;
	/**
	 * 车牌号
	 */
	private String carnumber;
	/**
	 * 当班司机id
	 */
	private Integer shiftdriverid;
	/**
	 * 当班司机姓名
	 */
	private String shiftdrivername;
	/**
	 * 评价结果
	 */
	private Integer resulttype;
	/**
	 * 评价原因
	 */
	private Integer reason;
	/**
	 * 评价时间，记录创建时间
	 */
	private String createtime;
	/**
	 * 开始时间
	 */
	private String stime;
	/**
	 * 结束时间
	 */
	private String etime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 当前用户id,用于控制权限
	 */
	private Integer userid;
	
	/** 结果 **/
	private Integer result;
	/*
	 * getter setter
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getShiftdriverid() {
		return shiftdriverid;
	}
	public void setShiftdriverid(Integer shiftdriverid) {
		this.shiftdriverid = shiftdriverid;
	}
	public String getShiftdrivername() {
		return shiftdrivername;
	}
	public void setShiftdrivername(String shiftdrivername) {
		this.shiftdrivername = shiftdrivername;
	}
	
	public Integer getResulttype() {
		return resulttype;
	}
	public void setResulttype(Integer resulttype) {
		this.resulttype = resulttype;
	}
	public Integer getReason() {
		return reason;
	}
	public void setReason(Integer reason) {
		this.reason = reason;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	
	
}
