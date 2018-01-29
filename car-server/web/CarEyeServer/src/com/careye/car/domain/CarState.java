/**
* Description: car-eye车辆管理平台
* 文件名：CarState.java
* 版本信息：1.0
* 日期：2015-5-25
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.domain;

/**
 * @项目名称：TAXISERVER
 * @类名称：CarState
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2015-5-25 下午05:43:18
 * @修改人：wuyongde
 * @修改时间：2015-5-25 下午05:43:18
 * @修改备注：
 * @version 1.0
 */
public class CarState {

	private Integer id;

	/**车辆ID*/
	private Integer carid;
	
	/**车辆当前状态	1长时间离线2离线3 熄火4停车 5 行驶 6 报警 7在线 8 未定位 默认：1*/
	private Integer carstatus;
	
	/**空重车状态	0 空车 1 重车 默认：0*/
	private Integer kzstate;
	
	/**调度状态	默认1 1 未调度 2调度中 3已调度**/
	private Integer dispaterstatus;

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

	public Integer getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}

	public Integer getKzstate() {
		return kzstate;
	}

	public void setKzstate(Integer kzstate) {
		this.kzstate = kzstate;
	}

	public Integer getDispaterstatus() {
		return dispaterstatus;
	}

	public void setDispaterstatus(Integer dispaterstatus) {
		this.dispaterstatus = dispaterstatus;
	}
	
	
}
