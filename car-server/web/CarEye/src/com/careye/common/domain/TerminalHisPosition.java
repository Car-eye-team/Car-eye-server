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

/**
 * @项目名称：car-eye
 * @类名称：TerminalPositionInfo
 * @类描述：终端历史位置实体
 * @创建人：zr
 * @创建时间：2013-8-10 下午05:06:53
 * @修改人：zr
 * @修改时间：2013-8-10 下午05:06:53
 * @修改备注：
 * @version 1.0
 */
public class TerminalHisPosition implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**信息ID */
	private Integer msgid;
	
	/**数据包序号*/
	private Integer seq;

	/**经度*/
	private Double lng;

	/**纬度 */
	private Double lat;
	
	/**百度经度*/
	private Double blng;

	/**百度纬度 */
	private Double blat;
	/**百度经度*/
	private Double glng;
	
	/**百度纬度 */
	private Double glat;

	/**高度 */
	private String altitude;

	/**终端号码 */
	private String terminal;
	
	/**车牌号*/
	private String carnumber;
	
	/**速度 */
	private String speed;
	
	/**方向 */
	private String direction;
	
	/**里程*/
	private Float mileage;
	
	/**GPS时间 */
	private String gpstime;
	
	/**GPS是否有效 */
	private int gpsflag;
	
	/**创建时间 */
	private String createtime;
	
	/**数据包*/
	private String datapacket;
	
	/**经纬度地址*/
	private String address;
	
	/**省*/
	private String province;
	
	/**市*/
	private String city;
	
	/**县*/
	private String district;
	
	/**状态*/
	private String sn;
	
	/**状态值*/
	private int snvalue;
	
	/**报警标志*/
	private String an;
	
	/**报警标志值*/
	private int anvalue;
	
	/**车辆当前状态	2离线 3 熄火 4停车 5 行驶 6 报警 7在线*/
	private int carstatus;
	
	/**油量*/
	private Float oil;
	
	/**总油耗*/
	private Float oilsum;
	
	/**100公里平均油耗*/
	private Float oilavg;
	
	/**瞬时油耗*/
	private Float oilat;
	
	
	public Float getOil() {
		return oil;
	}

	public void setOil(Float oil) {
		this.oil = oil;
	}

	public Float getOilsum() {
		return oilsum;
	}

	public void setOilsum(Float oilsum) {
		this.oilsum = oilsum;
	}

	public Float getOilavg() {
		return oilavg;
	}

	public void setOilavg(Float oilavg) {
		this.oilavg = oilavg;
	}

	public Float getOilat() {
		return oilat;
	}

	public void setOilat(Float oilat) {
		this.oilat = oilat;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getSnvalue() {
		return snvalue;
	}

	public void setSnvalue(int snvalue) {
		this.snvalue = snvalue;
	}

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}

	public int getAnvalue() {
		return anvalue;
	}

	public void setAnvalue(int anvalue) {
		this.anvalue = anvalue;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMsgid() {
		return msgid;
	}

	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
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

	public Float getMileage() {
		return mileage;
	}

	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDatapacket() {
		return datapacket;
	}

	public void setDatapacket(String datapacket) {
		this.datapacket = datapacket;
	}

	public int getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(int carstatus) {
		this.carstatus = carstatus;
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
	
	

}
