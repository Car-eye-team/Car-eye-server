/**
 * Description: car-eye车辆管理平台
 * 文件名：TerminalPositionInfo.java
 * 版本信息：1.0
 * 日期：2013-8-10
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.common.domain;

import java.io.Serializable;

import com.careye.car.domain.CarInfo;

/**
 * @项目名称：car-eye
 * @类名称：TerminalPositionInfo
 * @类描述：5.2	
 * @创建人：zr
 * @创建时间：2013-8-10 下午05:06:53
 * @修改人：zr
 * @修改时间：2013-8-10 下午05:06:53
 * @修改备注：
 * @version 1.0
 */
public class CarDetail extends CarInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	/** 主键 ID **/
	private Integer id;
	/** 集团 **/
	private String  blocname;
	/** 集团电话 **/
	private String bloctel;
	/** 设备类型 **/
	private String cdttypename;
	/** 车辆用途 **/
	private String  usename;
	/** 车牌号 **/
	private String carnumber;
	/** 车辆类型 **/
	private String cttypename;
	/** 终端设备号  **/
	private String terminal;
	/** 联系人**/
	private String contract;
	/** 终端手机号 **/
	private String phone;
	/** 车架号  **/
	private String framenumber;
	/** 行驶证号  **/
	private String drivlicnum;
	/** 车辆归属地 **/
	private String address;
	/** 司机姓名 **/
	private String drivername;
	/** 司机手机号 **/
	private String driverphone;
	/** 身份证 **/
	private String idnumber;
	/** 司机代码 **/
	private String drivercode;
	/** 性别 **/
	private String driversex;
	/**
	 * 车牌颜色 carnumbercolorname
		车身颜色 carcolorname
		设备类型 devicetypename
		燃料类型  fueltypename
		当前状态  nowstatusname
	 */
	
	private String carnumbercolorname;
	private String carcolorname;
	private String devicetypename;
	private String fueltypename;
	private String nowstatusname;
	private String electagstatusname;
	
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public String getCdttypename() {
		return cdttypename;
	}
	public void setCdttypename(String cdttypename) {
		this.cdttypename = cdttypename;
	}
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getCttypename() {
		return cttypename;
	}
	public void setCttypename(String cttypename) {
		this.cttypename = cttypename;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFramenumber() {
		return framenumber;
	}
	public void setFramenumber(String framenumber) {
		this.framenumber = framenumber;
	}
	public String getDrivlicnum() {
		return drivlicnum;
	}
	public void setDrivlicnum(String drivlicnum) {
		this.drivlicnum = drivlicnum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getBloctel() {
		return bloctel;
	}
	public void setBloctel(String bloctel) {
		this.bloctel = bloctel;
	}
	public String getDriverphone() {
		return driverphone;
	}
	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getDrivercode() {
		return drivercode;
	}
	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}
	public String getDriversex() {
		return driversex;
	}
	public void setDriversex(String driversex) {
		this.driversex = driversex;
	}
	public String getCarnumbercolorname() {
		return carnumbercolorname;
	}
	public void setCarnumbercolorname(String carnumbercolorname) {
		this.carnumbercolorname = carnumbercolorname;
	}
	public String getCarcolorname() {
		return carcolorname;
	}
	public void setCarcolorname(String carcolorname) {
		this.carcolorname = carcolorname;
	}
	public String getDevicetypename() {
		return devicetypename;
	}
	public void setDevicetypename(String devicetypename) {
		this.devicetypename = devicetypename;
	}
	public String getFueltypename() {
		return fueltypename;
	}
	public void setFueltypename(String fueltypename) {
		this.fueltypename = fueltypename;
	}
	public String getNowstatusname() {
		return nowstatusname;
	}
	public void setNowstatusname(String nowstatusname) {
		this.nowstatusname = nowstatusname;
	}
	public String getElectagstatusname() {
		return electagstatusname;
	}
	public void setElectagstatusname(String electagstatusname) {
		this.electagstatusname = electagstatusname;
	}
	
	
}





