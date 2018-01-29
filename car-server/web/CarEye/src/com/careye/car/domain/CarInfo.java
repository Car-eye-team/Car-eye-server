/**
* Description: car-eye车辆管理平台
* 文件名：CarInfo.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;

import net.sf.oval.constraint.Size;

/**
 * @项目名称：FMS
 * @类名称：CarInfo
 * @类描述：车辆信息实体
 * @创建人：zr
 * @创建时间：2014-5-22 上午10:21:04
 * @修改人：zr
 * @修改时间：2014-5-22 上午10:21:04
 * @修改备注：
 * @version 1.0
 */
public class CarInfo implements Serializable{
	
	private Integer id;
	private Integer carid;
	
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
	
	/**省	车辆归属地*/
	private String province;
	private String provincename;
	
	/**市	车辆归属地*/
	private String city;
	private String cityname;
	
	/**县/区	车辆归属地*/
	private String district;
	private String districtname;
	
	/**设备类型*/
	private Integer devicetype;
	
	/**车辆颜色*/
	private String color;
	
	/**车辆类型*/
	private Integer cartype;
	
	/**车辆用途*/
	private Integer caruse;
	private String usename;
	
	/**主驾驶员*/
	private Integer onedriverid;
	
	/**副驾驶员*/
	private Integer twodriverid;
	
	/**燃油种类93#汽油、97#汽油、柴油*/
	private String oiltype;
	
	/**车架号*/
	private String framenumber;
	
	/**行驶证号*/
	private String drivlicnum;
	
	/**变速箱号*/
	private String transnumber;
	
	/**排气量*/
	private String ventingmeasure;
	
	/**发动机号*/
	private String enginenumber;
	
	/**发动机号*/
	private String buytime;
	
	/**发动机号*/
	private String createtime;
	
	/**备注*/
	private String remark;
	
	/**车辆状态**/
	private Integer carstatus;
	/**车辆控制状态0初始1加锁 2 解锁**/
	private Integer controlstatus;
	private Integer online;
	
	private String stime;
	private String etime;
	
	/**集团名称**/
	private String blocname;
	
	/**设备类型名称**/
	private String typename;
	
	/**车辆类型名称**/
	private String cartypename;
	
	/**主驾驶员**/
	private String drivername1;
	
	/**副驾驶员**/
	private String drivername2;
	
	/**当班司机**/
	private String drivername3;
	private String Devicenumber;
	
	private Integer usertype;

	/**
	 * 区域id
	 */
	private Integer areaid;
	
	/** 雅讯设备号 **/
	private String yxterminal;
	
	/**车身颜色**/
	private String carcolor;
	
	/**生产厂家**/
	private String factory;
	
	/**车辆型号**/
	private String carmodel;
	
	/**经营权号**/
	private String managenumber;
	
	/**车主姓名**/
	private String ownername;
	
	/**车主地址**/
	private String owneraddress;
	
	/**设备号**/
	private String devicenumber;
	
	/**设备型号**/
	private String devicemodel;
	
	/**安装日期**/
	private String installtime;
	
	/**登记日期**/
	private String registertime;
	
	/**--------------------车辆属性信息 ---------------------------**/
	
	/**核定载客**/
	private String seatnumber;
	
	/**所有权性质**/
	private String ownership;
	
	/**入户日期**/
	private String entertime;
	
	/**出厂日期**/
	private String factorytime;
	
	/**燃料类型(1单一汽油、2单一柴油、3燃气、4双燃料、5电动、9其它)**/
	private Integer fueltype;
	
	/**发动机排量**/
	private String enginecapacity;
	
	/**排放标准**/
	private String capacitystandard;
	
	/**当前状态(1正常2注销)**/
	private Integer nowstatus;
	
	/**合同承包期(年)**/
	private String contracttime;
	
	/**财产险**/
	private String proinsure;
	
	/**交强险**/
	private String accinsure;
	
	/**乘座险**/
	private String ridinsure;
	
	/**三责险**/
	private String cominsure;
	
	/**车损险**/
	private String dlwinsure;
	
	/**电子标签状态**/
	private String electagstatus;
	
	/**文本信息**/
	private String textmessage;
	
	/**司机代码**/
	private String drivercode;
	
	/**经营性质*/
	private String management_nature;
	
	
	/***  营运证信息表   */
	
	private String operatenumber;
	private String operatestatus;
	private String operateproperty;
	private String licensetime;
	private String certificatetype;
	private String firstregisttime;
	private String entryperson;
	private String entrytime;
	private String entryremark;
	
	/**视频类型 1多森 2国基*/
	private Integer vediotype;
	
	
	public String getOperatenumber() {
		return operatenumber;
	}

	public void setOperatenumber(String operatenumber) {
		this.operatenumber = operatenumber;
	}

	public String getOperatestatus() {
		return operatestatus;
	}

	public void setOperatestatus(String operatestatus) {
		this.operatestatus = operatestatus;
	}

	public String getOperateproperty() {
		return operateproperty;
	}

	public void setOperateproperty(String operateproperty) {
		this.operateproperty = operateproperty;
	}

	public String getLicensetime() {
		return licensetime;
	}

	public void setLicensetime(String licensetime) {
		this.licensetime = licensetime;
	}

	public String getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getFirstregisttime() {
		return firstregisttime;
	}

	public void setFirstregisttime(String firstregisttime) {
		this.firstregisttime = firstregisttime;
	}

	public String getEntryperson() {
		return entryperson;
	}

	public void setEntryperson(String entryperson) {
		this.entryperson = entryperson;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getEntryremark() {
		return entryremark;
	}

	public void setEntryremark(String entryremark) {
		this.entryremark = entryremark;
	}

	/**
	 * 设备手机号terphone
	 * @return
	 */
	private String terphone;
	
	//视频编号
	private String vediono;
	
	public String getTerphone() {
		return terphone;
	}

	public void setTerphone(String terphone) {
		this.terphone = terphone;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	/**当班司机 **/
	private String drivername;
	
	public String getYxterminal() {
		return yxterminal;
	}

	public void setYxterminal(String yxterminal) {
		this.yxterminal = yxterminal;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
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

	public Integer getOnedriverid() {
		return onedriverid;
	}

	public void setOnedriverid(Integer onedriverid) {
		this.onedriverid = onedriverid;
	}

	public Integer getTwodriverid() {
		return twodriverid;
	}

	public void setTwodriverid(Integer twodriverid) {
		this.twodriverid = twodriverid;
	}

	public String getOiltype() {
		return oiltype;
	}

	public void setOiltype(String oiltype) {
		this.oiltype = oiltype;
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

	public String getTransnumber() {
		return transnumber;
	}

	public void setTransnumber(String transnumber) {
		this.transnumber = transnumber;
	}

	public String getVentingmeasure() {
		return ventingmeasure;
	}

	public void setVentingmeasure(String ventingmeasure) {
		this.ventingmeasure = ventingmeasure;
	}

	public String getEnginenumber() {
		return enginenumber;
	}

	public void setEnginenumber(String enginenumber) {
		this.enginenumber = enginenumber;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public String getDrivername1() {
		return drivername1;
	}

	public void setDrivername1(String drivername1) {
		this.drivername1 = drivername1;
	}

	public String getDrivername2() {
		return drivername2;
	}

	public void setDrivername2(String drivername2) {
		this.drivername2 = drivername2;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getDrivername3() {
		return drivername3;
	}

	public void setDrivername3(String drivername3) {
		this.drivername3 = drivername3;
	}
	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getDevicenumber() {
		return Devicenumber;
	}

	public void setDevicenumber(String devicenumber) {
		Devicenumber = devicenumber;
	}

	public String getCarcolor() {
		return carcolor;
	}

	public void setCarcolor(String carcolor) {
		this.carcolor = carcolor;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}

	public String getManagenumber() {
		return managenumber;
	}

	public void setManagenumber(String managenumber) {
		this.managenumber = managenumber;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getOwneraddress() {
		return owneraddress;
	}

	public void setOwneraddress(String owneraddress) {
		this.owneraddress = owneraddress;
	}

	public String getDevicemodel() {
		return devicemodel;
	}

	public void setDevicemodel(String devicemodel) {
		this.devicemodel = devicemodel;
	}

	public String getInstalltime() {
		return installtime;
	}

	public void setInstalltime(String installtime) {
		this.installtime = installtime;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public Integer getControlstatus() {
		return controlstatus;
	}

	public void setControlstatus(Integer controlstatus) {
		this.controlstatus = controlstatus;
	}

	public String getSeatnumber() {
		return seatnumber;
	}

	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getEntertime() {
		return entertime;
	}

	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}

	public String getFactorytime() {
		return factorytime;
	}

	public void setFactorytime(String factorytime) {
		this.factorytime = factorytime;
	}

	public Integer getFueltype() {
		return fueltype;
	}

	public void setFueltype(Integer fueltype) {
		this.fueltype = fueltype;
	}

	public String getEnginecapacity() {
		return enginecapacity;
	}

	public void setEnginecapacity(String enginecapacity) {
		this.enginecapacity = enginecapacity;
	}

	public String getCapacitystandard() {
		return capacitystandard;
	}

	public void setCapacitystandard(String capacitystandard) {
		this.capacitystandard = capacitystandard;
	}

	public Integer getNowstatus() {
		return nowstatus;
	}

	public void setNowstatus(Integer nowstatus) {
		this.nowstatus = nowstatus;
	}

	public String getContracttime() {
		return contracttime;
	}

	public void setContracttime(String contracttime) {
		this.contracttime = contracttime;
	}

	public String getProinsure() {
		return proinsure;
	}

	public void setProinsure(String proinsure) {
		this.proinsure = proinsure;
	}

	public String getAccinsure() {
		return accinsure;
	}

	public void setAccinsure(String accinsure) {
		this.accinsure = accinsure;
	}

	public String getRidinsure() {
		return ridinsure;
	}

	public void setRidinsure(String ridinsure) {
		this.ridinsure = ridinsure;
	}

	public String getCominsure() {
		return cominsure;
	}

	public void setCominsure(String cominsure) {
		this.cominsure = cominsure;
	}

	public String getDlwinsure() {
		return dlwinsure;
	}

	public void setDlwinsure(String dlwinsure) {
		this.dlwinsure = dlwinsure;
	}

	public String getElectagstatus() {
		return electagstatus;
	}

	public void setElectagstatus(String electagstatus) {
		this.electagstatus = electagstatus;
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

	public String getManagement_nature() {
		return management_nature;
	}

	public void setManagement_nature(String managementNature) {
		management_nature = managementNature;
	}

	public String getVediono() {
		return vediono;
	}

	public void setVediono(String vediono) {
		this.vediono = vediono;
	}

	public Integer getVediotype() {
		return vediotype;
	}

	public void setVediotype(Integer vediotype) {
		this.vediotype = vediotype;
	}
		
}
