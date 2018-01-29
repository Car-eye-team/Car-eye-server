/**
 * Description: car-eye车辆管理平台
 * 文件名：CarInfo.java
 * 版本信息：1.0
 * 日期：2014-5-22
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.taxi.domain;

import java.io.Serializable;

/**
* @项目名称：car-eyeSERVER
* @类名称：CarInfo
* @类描述：车辆信息实体
* @创建人：zr
* @创建时间：2015-3-13 下午03:15:16
* @修改人：zr
* @修改时间：2015-3-13 下午03:15:16
* @修改备注：
* @version 1.0
 */
public class CarInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer blocid;
	private Integer preblocid;

	/**用户ID*/
	private Integer userid;

	/**终端号码*/
	private String terminal;

	/**查询密码*/
	private String password;

	/**车牌号*/
	private String carnumber;

	/**设备手机号*/
	private String phone;

	/**设备类型*/
	private Integer devicetype;

	/**车辆颜色*/
	private String color;

	/**车辆类型*/
	private Integer cartype;

	/**车辆用途*/
	private Integer caruse;
	private String usename;

	/**备注*/
	private String remark;

	/**车辆状态**/
	private Integer carstatus;
	/**调度状 1 未调度 2调度中 3已调度**/
	private Integer dispaterstatus;
	
	/**空重状态0 空车 1 重车 默认：0**/
	private int kzstate;
	
	private Integer online;

	private String stime;
	private String etime;

	/**集团名称**/
	private String blocname;

	/**设备类型名称**/
	private String typename;

	/**车辆类型名称**/
	private String cartypename;
	
	/**车辆里程**/
	private Float mileage;
	
	/**文本信息**/
	private String textmessage;
	
	/**司机代码**/
	private String drivercode;
	
	public Float getMileage() {
		return mileage;
	}

	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getCartype() {
		return cartype;
	}

	public void setCartype(Integer cartype) {
		this.cartype = cartype;
	}

	public Integer getCaruse() {
		return caruse;
	}

	public void setCaruse(Integer caruse) {
		this.caruse = caruse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public Integer getPreblocid() {
		return preblocid;
	}

	public void setPreblocid(Integer preblocid) {
		this.preblocid = preblocid;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCartypename() {
		return cartypename;
	}

	public void setCartypename(String cartypename) {
		this.cartypename = cartypename;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public int getKzstate() {
		return kzstate;
	}

	public void setKzstate(int kzstate) {
		this.kzstate = kzstate;
	}

	public String getTextmessage() {
		return textmessage;
	}

	public void setTextmessage(String textmessage) {
		this.textmessage = textmessage;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public Integer getDispaterstatus() {
		return dispaterstatus;
	}

	public void setDispaterstatus(Integer dispaterstatus) {
		this.dispaterstatus = dispaterstatus;
	}

	
}
