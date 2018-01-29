/**
* Description: car-eye车辆管理平台
* 文件名：CityInfo.java
* 版本信息：1.0
* 日期：2013-8-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：OCS
 * @类名称：PioPlace
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-1-21 上午09:56:46
 * @修改人：zhangrong
 * @修改时间：2015-1-21 上午09:56:46
 * @修改备注：
 * @version 1.0
 */
public class PioPlace implements java.io.Serializable {
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String address;
	
	private String street_id;
	
	private String telephone;
	
	private String uid;
	
	private String lnglat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String streetId) {
		street_id = streetId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	
	

}


