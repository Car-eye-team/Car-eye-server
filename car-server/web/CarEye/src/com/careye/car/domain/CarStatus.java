/**
* Description: car-eye车辆管理平台
* 文件名：CarInfo.java
* 版本信息：1.0
* 日期：2014-3-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eyeTms
 * @类名称：CarStatus
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-4-16 上午10:49:19
 * @修改人：zhangrong
 * @修改时间：2014-4-16 上午10:49:19
 * @修改备注：
 * @version 1.0
 */
public class CarStatus extends BaseDomain {
	
	/**车辆ID*/
    private Integer carid;
    
    /**车辆所属车队*/
    private Integer motorcadeid;
    
    /**车辆所属车队名称*/
    private String motorcade_name;

    /**车牌号*/
    private String carnumber;

    /**省*/
    private String province;

    /**市*/
    private String city;

    /**县/区*/
    private String district;

    /**作业状态 1 待命 2 作业 3 停运*/
    private Integer status;
    
    /**车辆当前状态	2离线 3 熄火 4停车 5 行驶 6 报警 7在线8未定位*/
    private Integer carstatus;
    
    /**心跳时间差*/
    private Integer hearttime;
    
    /**是否带终端 1 带 2 不带*/
    private Integer vehicleterminal;
    
    /**终端号码*/
    private String terminalnum;
    
    /**创建时间*/
    private String createtime;
    
    /**1代表将要删除**/
    private Integer delete;
    
    /**停车时间*/
    private String stoptime;
    
    private String lng;
    private String lat;
    private String address;
    private String carbrand;
    private String carmodename;
    private String chauffeurname;
    private String phone;
    
    private String speed;
    private String direction;
    private String terminal;
    private String mileage;
    private String summileage;
    
    /**
     * 时间差
     */
    private Integer diff;
    
    private String systime;
    
    /**如果大于0，表示离线*/
    private Integer offtime;

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getMotorcadeid() {
		return motorcadeid;
	}

	public void setMotorcadeid(Integer motorcadeid) {
		this.motorcadeid = motorcadeid;
	}

	public String getMotorcade_name() {
		return motorcade_name;
	}

	public void setMotorcade_name(String motorcadeName) {
		motorcade_name = motorcadeName;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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

	public Integer getHearttime() {
		return hearttime;
	}

	public void setHearttime(Integer hearttime) {
		this.hearttime = hearttime;
	}

	public Integer getVehicleterminal() {
		return vehicleterminal;
	}

	public void setVehicleterminal(Integer vehicleterminal) {
		this.vehicleterminal = vehicleterminal;
	}

	public String getTerminalnum() {
		return terminalnum;
	}

	public void setTerminalnum(String terminalnum) {
		this.terminalnum = terminalnum;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDiff() {
		return diff;
	}

	public void setDiff(Integer diff) {
		this.diff = diff;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}

	public Integer getOfftime() {
		return offtime;
	}

	public void setOfftime(Integer offtime) {
		this.offtime = offtime;
	}

	public String getCarbrand() {
		return carbrand;
	}

	public void setCarbrand(String carbrand) {
		this.carbrand = carbrand;
	}

	public String getCarmodename() {
		return carmodename;
	}

	public void setCarmodename(String carmodename) {
		this.carmodename = carmodename;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getChauffeurname() {
		return chauffeurname;
	}

	public void setChauffeurname(String chauffeurname) {
		this.chauffeurname = chauffeurname;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getSummileage() {
		return summileage;
	}

	public void setSummileage(String summileage) {
		this.summileage = summileage;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
		this.delete = delete;
	}

	public Integer getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}
	
	
	

}















