/**
* Description: 出租车系统
* 文件名：Alarm.java
* 版本信息：1.0
* 日期：2015-3-14
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;

/**
 * @项目名称：TAXISERVER
 * @类名称：Alarm 
 * @类描述：
 * @创建人：zr
 * @创建时间：2015-3-14 下午03:03:51
 * @修改人：zr
 * @修改时间：2015-3-14 下午03:03:51
 * @修改备注：
 * @version 1.0
 */
public class Alarm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	/**集团ID*/
	private Integer blocid;
	
	/**集团名称*/
	private String blocname;
	
	/**用户ID*/
	private Integer userid;
	
	/**终端号码*/
	private String terminal;
	
	/**车牌号*/
	private String carnumber;
	
	/**车id*/
	private Integer carid;
	
	/**报警名称*/
	private String alarmname;
	
	/**报警KEY*/
	private String alarmkey;
	
	/**经度*/
	private Double lng;
	
	/**纬度*/
	private Double lat;
	
	/**高度*/
	private String altitude;
	
	/**速度*/
	private String speed;
	
	/**方向*/
	private String direction;
	
	/**地址*/
	private String address;
	
	/**省*/
	private String province;
	
	/**市*/
	private String city;
	
	/**县*/
	private String district;
	
	/**百度经度*/
	private Double blng;
	
	/**百度纬度*/
	private Double blat;
	
	/**高德经度*/
	private Double glng;
	
	/**高德纬度*/
	private Double glat;
	
	/**高德地址*/
	private String gaddress;
	
	/**原始数据包*/
	private String rawdata;
	
	/**报警时间*/
	private String alarmtime;
	
	/**报警来源	1 终端 2 服务器端 默认终端*/
	private Integer alarmsource;
	
	/**1：未处理 2 已处理默认：1*/
	private Integer prostatus;
	
	/**处理内容*/
	private String procontent;
	
	/**处理时间*/
	private String protime;
	
	/**备注*/
	private String remark;
	
	/**创建时间*/
	private String createtime;
	
	/**报警声音文件路径*/
	private String musicaddr;
	
	private Integer alarmtype;
	
	/**
	 * 报警区域id
	 */
	private Integer areaid;
	

	public String getMusicaddr() {
		return musicaddr;
	}

	public void setMusicaddr(String musicaddr) {
		this.musicaddr = musicaddr;
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

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
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

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getAlarmname() {
		return alarmname;
	}

	public void setAlarmname(String alarmname) {
		this.alarmname = alarmname;
	}

	public String getAlarmkey() {
		return alarmkey;
	}

	public void setAlarmkey(String alarmkey) {
		this.alarmkey = alarmkey;
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

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
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


	public String getGaddress() {
		return gaddress;
	}

	public void setGaddress(String gaddress) {
		this.gaddress = gaddress;
	}

	public String getRawdata() {
		return rawdata;
	}

	public void setRawdata(String rawdata) {
		this.rawdata = rawdata;
	}

	public String getAlarmtime() {
		return alarmtime;
	}

	public void setAlarmtime(String alarmtime) {
		this.alarmtime = alarmtime;
	}

	public Integer getAlarmsource() {
		return alarmsource;
	}

	public void setAlarmsource(Integer alarmsource) {
		this.alarmsource = alarmsource;
	}

	public Integer getProstatus() {
		return prostatus;
	}

	public void setProstatus(Integer prostatus) {
		this.prostatus = prostatus;
	}

	public String getProcontent() {
		return procontent;
	}

	public void setProcontent(String procontent) {
		this.procontent = procontent;
	}

	public String getProtime() {
		return protime;
	}

	public void setProtime(String protime) {
		this.protime = protime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getAlarmtype() {
		return alarmtype;
	}

	public void setAlarmtype(Integer alarmtype) {
		this.alarmtype = alarmtype;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	
	
	
}

