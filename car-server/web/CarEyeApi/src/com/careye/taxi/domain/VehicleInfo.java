/**
* Description: car-eye车辆管理平台
* 文件名：VehicleInfo.java
* 版本信息：1.0
* 日期：2015-1-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.taxi.domain;

/**
 * @项目名称：OCS
 * @类名称：VehicleInfo
 * @类描述：车辆信息实体
 * @创建人：zr
 * @创建时间：2015-1-19 下午04:12:12
 * @修改人：zr
 * @修改时间：2015-1-19 下午04:12:12
 * @修改备注：
 * @version 1.0
 */
public class VehicleInfo {
	
	/**
	 * 纬度
	 */
	private double lng;
	
	/**
	 * 经度
	 */
	private double lat;
	
	/**集团名称**/
	private String corpname;
	
	/**车辆颜色**/
	private String color;
	
	/**车辆描述**/
	private String typedesc;
	
	/**高度**/
	private String head;
	
	/***速度**/
	private String speed;
	
	/**司机名称*/
	private String cname;
	
	/**司机性别*/
	private String sex;
	
	/**终端设备手机号*/
	private String cellphone;
	
	/**司机手机号*/
	private String driverphone;
	
	/**方向**/
	private String direction;
	
	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTypedesc() {
		return typedesc;
	}

	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	/**
	 * 车牌号
	 */
	private String carnumber;


	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
}
