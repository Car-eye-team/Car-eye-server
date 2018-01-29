/**
* Description: car-eye车辆管理平台
* 文件名：CarPositionInfo.java
* 版本信息：1.0
* 日期：2014-7-29
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：FMS
 * @类名称：CarPositionInfo
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-7-29 下午04:46:13
 * @修改人：zr
 * @修改时间：2014-7-29 下午04:46:13
 * @修改备注：
 * @version 1.0
 */
public class PositionInfo {
	
	private Integer carid;
    private String carstatus;
    private String carnumber;
    private Double lng;
    private Double lat;
    private Double blng;
    private Double blat;
	private String direction;
    private String speed;
    private String terminal;
    private Double glng;
    private Double glat;
    private String gaddress;
    private String address;
    private String mileage;
    private String createtime;
    private String phone;
    private String drivername;
    private String deptname;
    private String cartype;
    private String icon;
    private String drivecrednum;
    private String jobcertification;
    private String roadtransport;
	private String remark;
	

	private String buytime;
	private String usename;
	private String oiltype;
	private String drivlicnum;
	private String ventingmeasure;
	private String enginenumber;
	private String altitude;
	private String onedrivername;
	private String twodrivername;
	private String onephone;
	private String twophone;
	private String blocname;
	
	/**空重车状态0 空车 1 重车 默认：0 */
	private String kzstate;
	
	
	public String getOnephone() {
		return onephone;
	}
	public void setOnephone(String onephone) {
		this.onephone = onephone;
	}
	public String getTwophone() {
		return twophone;
	}
	public void setTwophone(String twophone) {
		this.twophone = twophone;
	}
	public String getBuytime() {
		return buytime;
	}
	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	public String getOiltype() {
		return oiltype;
	}
	public void setOiltype(String oiltype) {
		this.oiltype = oiltype;
	}
	public String getDrivlicnum() {
		return drivlicnum;
	}
	public void setDrivlicnum(String drivlicnum) {
		this.drivlicnum = drivlicnum;
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
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getOnedrivername() {
		return onedrivername;
	}
	public void setOnedrivername(String onedrivername) {
		this.onedrivername = onedrivername;
	}
	public String getTwodrivername() {
		return twodrivername;
	}
	public void setTwodrivername(String twodrivername) {
		this.twodrivername = twodrivername;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
	}
	public String getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public Double getGlng() {
		return glng;
	}
	public void setGlng(Double glng) {
		this.glng = glng;
	}
	public Double getGlat() {
		return glat;
	}
	public void setGlat(Double glat) {
		this.glat = glat;
	}
	public String getGaddress() {
		return gaddress;
	}
	public void setGaddress(String gaddress) {
		this.gaddress = gaddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDrivecrednum() {
		return drivecrednum;
	}
	public void setDrivecrednum(String drivecrednum) {
		this.drivecrednum = drivecrednum;
	}
	public String getJobcertification() {
		return jobcertification;
	}
	public void setJobcertification(String jobcertification) {
		this.jobcertification = jobcertification;
	}
	public String getRoadtransport() {
		return roadtransport;
	}
	public void setRoadtransport(String roadtransport) {
		this.roadtransport = roadtransport;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getKzstate() {
		return kzstate;
	}
	public void setKzstate(String kzstate) {
		this.kzstate = kzstate;
	}
    
    
	
}
