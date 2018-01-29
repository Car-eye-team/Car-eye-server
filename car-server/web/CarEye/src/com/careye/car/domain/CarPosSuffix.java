/**
* Description: car-eye车辆管理平台
* 文件名：CarPositionSuffix.java
* 版本信息：1.0
* 日期：2014-7-10
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

/**
 * @项目名称：FMS
 * @类名称：CarPositionSuffix
 * @类描述：
 * @创建人：lenovo
 * @创建时间：2014-7-10 上午11:13:25
 * @修改人：lenovo
 * @修改时间：2014-7-10 上午11:13:25
 * @修改备注：
 * @version 1.0
 */
public class CarPosSuffix {
	
	private Integer id;
	
	/**
	 * 创建年份
	 */
	private String createyear;
	
	/**
	 * 车辆ID	车辆信息表
	 */
	private Integer carid;
	
	/**
	 * 车辆位置表后缀名	CAR_POSITION_后缀名
	 */
	private String suffix;
	
	/**
	 * 表使用状态	1 使用 2 未使用
	 */
	private Integer state;
	
	/**
	 * 创建时间
	 */
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateyear() {
		return createyear;
	}

	public void setCreateyear(String createyear) {
		this.createyear = createyear;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	

}
