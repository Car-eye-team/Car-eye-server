/**
* Description: 多森商用车平台
* 文件名：CarInfo.java
* 版本信息：1.0
* 日期：2014-3-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.taxi.domain;

/**
 * @项目名称：car-eyeTms
 * @类名称：CarStatus
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-4-16 上午10:49:19
 * @修改人：zhangrong
 * @修改时间：2014-4-16 上午10:49:19
 * @修改备注：
 * @version 1.0
 */
public class CarStatus {

    /**车牌号*/
    private String carnumber;
    
    /**车辆当前状态	2离线 3 熄火 4停车 5 行驶 6 报警 7在线8未定位*/
    private Integer carstatus;
    
    /**创建时间*/
    private String createtime;
    
    private String lng;
    private String lat;
    private String address;
    
    private String speed;
    private String direction;
    private String terminal;
    private String mileage;
    private String summileage;
    private String alarmname;
    private Integer alarmtype;
  
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public Integer getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getSummileage() {
		return summileage;
	}
	public void setSummileage(String summileage) {
		this.summileage = summileage;
	}
	public String getAlarmname() {
		return alarmname;
	}
	public void setAlarmname(String alarmname) {
		this.alarmname = alarmname;
	}
	public Integer getAlarmtype() {
		return alarmtype;
	}
	public void setAlarmtype(Integer alarmtype) {
		this.alarmtype = alarmtype;
	}	

}















