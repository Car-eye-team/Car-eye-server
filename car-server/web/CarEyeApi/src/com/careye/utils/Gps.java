/**
* Description: 出租车系统
* 文件名：Gps.java
* 版本信息：1.0
* 日期：2015-4-8
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.utils;

/**
 * @项目名称：OCS
 * @类名称：Gps
 * @类描述：
 * @创建人：zr
 * @创建时间：2015-4-8 下午04:15:14
 * @修改人：zr
 * @修改时间：2015-4-8 下午04:15:14
 * @修改备注：
 * @version 1.0
 */
public class Gps {

	private double wgLat;
	private double wgLon;

	public Gps(double wgLat, double wgLon) {
		setWgLat(wgLat);
		setWgLon(wgLon);
	}

	public double getWgLat() {
		return wgLat;
	}

	public void setWgLat(double wgLat) {
		this.wgLat = wgLat;
	}

	public double getWgLon() {
		return wgLon;
	}

	public void setWgLon(double wgLon) {
		this.wgLon = wgLon;
	}

	@Override
	public String toString() {
		return wgLat + "," + wgLon;
	}
}
