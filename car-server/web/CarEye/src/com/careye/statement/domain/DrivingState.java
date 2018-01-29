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

/**
 * @项目名称：car-eye
 * @类名称：DrivingState
 * @类描述：车辆行驶状态报表
 * @创建人：lxh
 * @创建时间：2014-6-11 下午02:12:37
 * @修改人：lxh
 * @修改时间：2014-6-11 下午02:12:37
 * @修改备注：
 * @version 1.0
 */
public class DrivingState implements Serializable{
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;

	/**ID*/
	private Integer id;

	/**ID*/
	private Integer userid;
	
	
	/**总里程*/
	private Double summil;
	
	/**里程*/
	private Double mileage;
	
	/**速度*/
	private Double speed;
	
	/**经纬度*/
	private String lnglat;

	/**纬度*/
	private Double lng;

	/**纬度*/
	private Double lat;
	
	/**集团*/
	private String blocname;
	
	/**终端号码*/
	private String terminal;
	
	/** 车牌号 **/
	private String carnumber;
	/** 车辆id **/
	private Integer carid;
	
	/** 创建时间 **/
	private String createtime;
	
	/** 车辆当前状态 **/
	private Integer carstatus;
	
	/** 地址 **/
	private String address;
	

	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	/**车辆位置表*/
	private String positiontable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Double getSummil() {
		return summil;
	}

	public void setSummil(Double summil) {
		this.summil = summil;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}


	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPositiontable() {
		return positiontable;
	}

	public void setPositiontable(String positiontable) {
		this.positiontable = positiontable;
	}
	
	
	
}
