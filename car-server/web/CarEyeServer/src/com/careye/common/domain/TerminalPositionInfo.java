/**
 * Description: 多森商用车平台
 * 文件名：TerminalPositionInfo.java
 * 版本信息：1.0
 * 日期：2013-8-10
 * Copyright car-eye车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.common.domain;

import java.io.Serializable;

/**
 * @项目名称：cvp
 * @类名称：TerminalPositionInfo
 * @类描述：5.2	终端设备位置信息表
 * @创建人：zr
 * @创建时间：2013-8-10 下午05:06:53
 * @修改人：zr
 * @修改时间：2013-8-10 下午05:06:53
 * @修改备注：
 * @version 1.0
 */
public class TerminalPositionInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer userid;
	private Integer deptid;

	private Integer id;
	
	/**车辆id**/
	private Integer carid;

	/**信息ID */
	private Integer msgid;

	/**经度*/
	private Double lng;

	/**纬度 */
	private Double lat;

	/**高度 */
	private String altitude;

	/**终端号码 */
	private String terminal;
	
	/**车牌号*/
	private String carnumber;
	
	/**车辆当前状态 2离线3 熄火4停车 5 行驶 6 报警 7在线*/
	private String carstatus;
	
	/**运输状态、 休息  、待配货、 运输中  默认*/
	private String travelposition;
	
	/**速度 */
	private String speed;
	
	/**方向 */
	private String direction;
	
	/**GPS时间 */
	private String gpstime;
	
	/**GPS是否有效 */
	private int gpsflag;
	
	/**无效、有效、上次信号有效 */
	private String gpsflagtext;
	
	/**创建时间 */
	private String createtime;
	
	
	/**备注信息*/
	private String remark;
	
	/**经纬度地址*/
	private String address;
	
	/**省*/
	private String province;
	
	/**市*/
	private String city;
	
	/**县*/
	private String district;
	
	/**百度经度*/
	private Double blng;

	/**百度纬度 */
	private Double blat;
	
	/**最后位置时间与当前时间相差时间 */
	private Integer apart;
	
	/**车牌号 */
	private String platecode;
	
	/**终端设备车载手机号码 */
	private String phone;
	
	/**时间差*/
	private int diff;
	
	/**状态*/
	private String sn;
	
	/**状态值*/
	private int snvalue;
	
	/**报警标志*/
	private String an;
	
	/**报警标志值*/
	private int anvalue;
	
	/**百度经度*/
	private Double glng;

	/**百度纬度 */
	private Double glat;
	
	/**高德地址**/
	private String gaddress;
	
	/**里程**/
	private String mileage;
	
	/**总里程**/
	private String summileage;
	
	private String carmodel;
	private String cartype;
	private String drivername;
	
	private String deptname;
	
	private String stime;
	
	private String etime;
	
	/**马良所需经度*/
	private String mlng;
	
	/**马良所需纬度*/
	private String mlat;
	
	/**插入时间*/
	private String inserttime;
	
	/**ACC状态 0 关 1 开*/
	private int acc;

	/***************OBD相关***************/
	private String vid;
	
	/**车辆识别码*/
	private String vin;
	
	/**行程ID*/
	private Integer tripid;
	
	/**表名*/
	private String positiontable;
	
	
	
	public TerminalPositionInfo() {
		super();
	}
	
	

	public TerminalPositionInfo(Integer carid, String terminal,
			String carnumber, Integer msgid, Double lng, Double lat,
			String carstatus, String speed, String direction,
			String gpstime, int gpsflag, String createtime, String remark,
			String address, String province, String city, String district,
			Double blng, Double blat,String vid, String vin, Integer tripid,
			int acc,String mileage) {
		super();
		this.carid = carid;
		this.terminal = terminal;
		this.carnumber = carnumber;
		this.msgid = msgid;
		this.lng = lng;
		this.lat = lat;
		this.carstatus = carstatus;
		this.speed = speed;
		this.direction = direction;
		this.gpstime = gpstime;
		this.gpsflag = gpsflag;
		this.createtime = createtime;
		this.remark = remark;
		this.address = address;
		this.province = province;
		this.city = city;
		this.district = district;
		this.blng = blng;
		this.blat = blat;
		this.vid = vid;
		this.vin = vin;
		this.tripid = tripid;
		this.acc = acc;
		this.mileage = mileage;
	}



	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
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

	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public String getPlatecode() {
		return platecode;
	}

	public void setPlatecode(String platecode) {
		this.platecode = platecode;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getApart() {
		return apart;
	}

	public void setApart(Integer apart) {
		this.apart = apart;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
	}

	public String getTravelposition() {
		return travelposition;
	}

	public void setTravelposition(String travelposition) {
		this.travelposition = travelposition;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getGpsflagtext() {
		return gpsflagtext;
	}

	public void setGpsflagtext(String gpsflagtext) {
		this.gpsflagtext = gpsflagtext;
	}

	public String getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getMlng() {
		return mlng;
	}

	public void setMlng(String mlng) {
		this.mlng = mlng;
	}

	public String getMlat() {
		return mlat;
	}

	public void setMlat(String mlat) {
		this.mlat = mlat;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getTripid() {
		return tripid;
	}

	public void setTripid(Integer tripid) {
		this.tripid = tripid;
	}



	public String getPositiontable() {
		return positiontable;
	}



	public void setPositiontable(String positiontable) {
		this.positiontable = positiontable;
	}



	public Integer getDeptid() {
		return deptid;
	}



	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}



	public String getSummileage() {
		return summileage;
	}



	public void setSummileage(String summileage) {
		this.summileage = summileage;
	}
	
	
}



