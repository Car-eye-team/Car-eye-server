/**
* Description: 多森商用车平台
* 文件名：BaiDuInfo.java
* 版本信息：1.0
* 日期：2013-10-16
* Copyright car-eye车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.http.domain;

/**
 * @项目名称：cvp
 * @类名称：BaiDuInfo
 * @类描述：百度信息实体
 * @创建人：zr
 * @创建时间：2013-10-16 上午11:31:59
 * @修改人：zr
 * @修改时间：2013-10-16 上午11:31:59
 * @修改备注：
 * @version 1.0
 */
public class BaiDuInfo {
	
	/**经纬度地址*/
	private String address;
	
	/**省*/
	private String province;
	
	/**市*/
	private String city;
	
	/**县*/
	private String district;
	
	/**百度经度*/
	private Double blng;

	/**百度纬度 */
	private Double blat;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Double getBlng() {
		return blng;
	}

	public void setBlng(Double blng) {
		this.blng = blng;
	}

	public Double getBlat() {
		return blat;
	}

	public void setBlat(Double blat) {
		this.blat = blat;
	}
	
	
}
