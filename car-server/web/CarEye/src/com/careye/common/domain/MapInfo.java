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
public class MapInfo {
	
	private Integer carid;
	private String carstatus;
    private Double blng;
    private Double blat;
    private Double lng;
    private Double lat;
    private Double glng;
    private Double glat;
    private String gaddress;
    private String icon;
    private String direction;
    private String speed;
    private String address;
    private String mileage;
    private String createtime;
    private String phone;
    private String terminal;
    private String vediono;
    private Integer vediotype;
    
    /**空重车状态0 空车 1 重车 默认：0 */
	private String kzstate;
	private Integer acc;
    
    
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
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
	public String getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
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
	public String getGaddress() {
		return gaddress;
	}
	public void setGaddress(String gaddress) {
		this.gaddress = gaddress;
	}
	public String getKzstate() {
		return kzstate;
	}
	public void setKzstate(String kzstate) {
		this.kzstate = kzstate;
	}
	public Integer getAcc() {
		return acc;
	}
	public void setAcc(Integer acc) {
		this.acc = acc;
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
