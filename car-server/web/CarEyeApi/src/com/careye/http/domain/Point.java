/**
* Description: 多森商用车平台
* 文件名：Point.java
* 版本信息：1.0
* 日期：2013-9-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.http.domain;

/**
 * @项目名称：car-eye
 * @类名称：Point
 * @类描述：
 * @创建人：zr
 * @创建时间：2013-9-6 上午11:05:39
 * @修改人：zr
 * @修改时间：2013-9-6 上午11:05:39
 * @修改备注：
 * @version 1.0
 */
public class Point {

	private Integer id;
	
	/**经度*/
	private Double lng;

	/**纬度 */
	private Double lat;
	
	/**距离 公里*/
	private Integer distance;
	
	/**车牌号*/
	private String platecode;
	
	/**终端设备号*/
	private String terminalnum;
	
	
	public String getPlatecode() {
		return platecode;
	}

	public void setPlatecode(String platecode) {
		this.platecode = platecode;
	}

	public String getTerminalnum() {
		return terminalnum;
	}

	public void setTerminalnum(String terminalnum) {
		this.terminalnum = terminalnum;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
