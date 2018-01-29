/**
* Description: car-eye车辆管理平台
* 文件名：DriverInfo.java
* 版本信息：1.0
* 日期：2015-1-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.taxi.domain;

/**
 * @项目名称：OCS
 * @类名称：DriverInfo
 * @类描述：司机信息实体
 * @创建人：zr
 * @创建时间：2015-1-19 下午06:17:33
 * @修改人：zr
 * @修改时间：2015-1-19 下午06:17:33
 * @修改备注：
 * @version 1.0
 */
public class DriverListInfo {
	
	private Integer id;
	
	/**司机名称*/
	private String cname;
	
	/**司机性别*/
	private String sex;
	
	/**司机手机号*/
	private String cellphone;
	
	/**车载号码**/
	private String phone;
	
	/**车牌号*/
	private String carnumber;
	/**设备号*/
	private String terminal;
	
	/**服务监督卡号*/
	private String drivercode;
	
	/**订单号*/
	private String orderid;
	
	/**驾驶证号*/
	private String drivecrednum;
	
	/**集团名称*/
	private String blocname;
	
	/*********************服务证信息*******************/
	/**发证日期**/
	private String fztime;
	/**星级**/
	private String starlevel;
	/**有效期(年)**/
	private String validity;
	/**照片路径**/
	private String picturepath;
	/**二维码地址**/
	private String qrcodepath;
	/**版本号**/
	private String version;
	
	/**是否为当班司机(0否 1是)**/
	private String isduty;
	

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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

	public String getDrivecrednum() {
		return drivecrednum;
	}

	public void setDrivecrednum(String drivecrednum) {
		this.drivecrednum = drivecrednum;
	}


	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getFztime() {
		return fztime;
	}

	public void setFztime(String fztime) {
		this.fztime = fztime;
	}

	public String getStarlevel() {
		return starlevel;
	}

	public void setStarlevel(String starlevel) {
		this.starlevel = starlevel;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getPicturepath() {
		return picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIsduty() {
		return isduty;
	}

	public void setIsduty(String isduty) {
		this.isduty = isduty;
	}

	public String getQrcodepath() {
		return qrcodepath;
	}

	public void setQrcodepath(String qrcodepath) {
		this.qrcodepath = qrcodepath;
	}

	
}
