/**
* Description: car-eye车辆管理平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：FMS
 * @类名称：CarDriver
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class CarDriver implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	/**司机手机号*/
	private String phone;
	
	/** 车牌号  **/
	private String carnumber;
	
	/** 司机名称  **/
	private String drivername;
	
	/** 司机代码  **/
	private String drivercode;
	
	private Double lng;
	private Double lat;
	
	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
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
	

}
