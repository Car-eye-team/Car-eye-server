/**
* Description: car-eye车辆管理平台
* 文件名：DrivingState.java
* 版本信息：1.0
* 日期：2014-6-11
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @项目名称：car-eye
 * @类名称：DrivingState
 * @类描述：车辆行驶状态报表
 * @创建人：lxh
 * @创建时间：2014-6-11 下午02:12:37
 * @修改人：lxh
 * @修改时间：2014-6-11 下午02:12:37
 * @修改备注：
 * @version 1.0
 */
public class CarPosition extends  BaseDomain  implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String  terminal;
	private Integer msgid;
	private String  lng;
	private String  lat;
	private String  altitude;
	private String  speed;
	private String  direction;
	private String  gpstime;
	private String  gpsflag;
	private String  createtime;
	private String  address;
	private String  province;
	private String  city;
	private String  district;
	private String  blng;
	private String  carnumber;
	private String  carid;
	private String  glng;
	private String  glat;
	private String  gaddress;
	private String  mileage;
	private String  acc;
	private String  remark;
	private String  daymileage;
	private Integer flagTwo;
	private Integer flag;
	private Integer blocid;
	private String  blocname;
	private List<Integer> carids=new ArrayList<Integer>();
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
	public Integer getMsgid() {
		return msgid;
	}
	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
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
	public String getGpstime() {
		return gpstime;
	}
	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}
	public String getGpsflag() {
		return gpsflag;
	}
	public void setGpsflag(String gpsflag) {
		this.gpsflag = gpsflag;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
	public String getBlng() {
		return blng;
	}
	public void setBlng(String blng) {
		this.blng = blng;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public String getGlng() {
		return glng;
	}
	public void setGlng(String glng) {
		this.glng = glng;
	}
	public String getGlat() {
		return glat;
	}
	public void setGlat(String glat) {
		this.glat = glat;
	}
	public String getGaddress() {
		return gaddress;
	}
	public void setGaddress(String gaddress) {
		this.gaddress = gaddress;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDaymileage() {
		return daymileage;
	}
	public void setDaymileage(String daymileage) {
		this.daymileage = daymileage;
	}
	public Integer getFlagTwo() {
		return flagTwo;
	}
	public void setFlagTwo(Integer flagTwo) {
		this.flagTwo = flagTwo;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
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
	public List<Integer> getCarids() {
		return carids;
	}
	public void setCarids(List<Integer> carids) {
		this.carids = carids;
	}
}
