/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**
 * 
 * @项目名称：dsparsed
 * @类名称：ReceivingContact
 * @类描述：收货联系人
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午06:21:23
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午06:21:23
 * @修改备注：
 * @version 1.0
 */
public class ReceivingContact extends  BaseInfo{

	/**标志位 **/
	private int  flag;
	/** 纬度**/
	private int lat;
	/** 经度**/
	private int lng;
	/** 订单ID**/
	private String orderid;
	/** 联系人**/
	private String fullname;
	/** 联系电话**/
	private String tel;
	/** 联系手机**/
	private String phone;
	/** 目的地址**/
	private String address;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getLng() {
		return lng;
	}
	public void setLng(int lng) {
		this.lng = lng;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
