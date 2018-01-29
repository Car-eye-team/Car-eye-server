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
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @项目名称：car-eye
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

	private Integer id;
	
	/**车辆id**/
	private Integer carid;
	/**部门id**/
	private Integer blocid;

	/**信息ID */
	private Integer msgid;

	

	/**经度*/
	private Double lng;
	private Double lng1;

	/**纬度 */
	private Double lat;
	private Double lat1;
	
	private Double glng;
	private Double glat;
	private Double glng1;
	private Double glat1;
	private String gaddress;

	/**高度 */
	private String altitude;

	/**终端号码 */
	private String terminal;
	
	/**车牌号*/
	private String carnumber;
	
	/**车辆当前状态 在线、离线、 熄火、 行驶、停车、报警*/
	private String carstatus;
	
	/**速度 */
	private String speed;
	
	/**方向 */
	private String direction;
	
	/**GPS时间 */
	private String gpstime;
	
	/**里程 */
	private String mileage;
	private String daymileage;
	
	/**GPS是否有效 */
	private int gpsflag;
	
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
	
	/**空重车状态0 空车 1 重车 默认：0 */
	private String kzstate;
	
	/**时间差*/
	private int diff;
	
	/**0: ACC关;1:ACC开*/
	private Integer acc;
	
	private String drivername;
	
	private String blocname;
	
	private String stime;
	
	private String etime;
	
	private String typename;
	
	private Integer devicetype;
	private Integer cartype;
	
	/**车辆类型名**/
	private String cartypename;
	
	private String usename;
	
	private String color;
	private String tableName;
	private String carnumbercolor;
	
	/** 左上点纬度 **/
	private String latlt;
	/** 左上点经度 **/
	private String lnglt;
	/** 右下点纬度 **/
	private String latrb;
	/** 右下点经度 **/
	private String lngrb;
	/** 区域类型 **/
	private Integer areatype;
	
	
	/**
	 * 车辆在线情况word导出  
	 */
	private Integer count1;//离线
	private Integer count2;//在线
	
	
	public Integer getCount1() {
		return count1;
	}

	public void setCount1(Integer count1) {
		this.count1 = count1;
	}

	public Integer getCount2() {
		return count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	private List<TerminalPositionInfo>  list=new ArrayList<TerminalPositionInfo>();
	
	private List<String> ids=new ArrayList<String>();
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<TerminalPositionInfo> getList() {
		return list;
	}

	public void setList(List<TerminalPositionInfo> list) {
		this.list = list;
	}

	public Integer getAreatype() {
		return areatype;
	}

	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}

	public String getLatlt() {
		return latlt;
	}

	public void setLatlt(String latlt) {
		this.latlt = latlt;
	}

	public String getLnglt() {
		return lnglt;
	}

	public void setLnglt(String lnglt) {
		this.lnglt = lnglt;
	}

	public String getLatrb() {
		return latrb;
	}

	public void setLatrb(String latrb) {
		this.latrb = latrb;
	}

	public String getLngrb() {
		return lngrb;
	}

	public void setLngrb(String lngrb) {
		this.lngrb = lngrb;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCarnumbercolor() {
		return carnumbercolor;
	}

	public void setCarnumbercolor(String carnumbercolor) {
		this.carnumbercolor = carnumbercolor;
	}

	/**排序列名 车牌号、状态、速度、方向、总里程、更新时间*/
	private String sortcolumn;
	/**排序方式  升序asc  降序 desc*/
	private String sortway;
	
	

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public Double getLng1() {
		return lng1;
	}

	public void setLng1(Double lng1) {
		this.lng1 = lng1;
	}

	public Double getLat1() {
		return lat1;
	}

	public void setLat1(Double lat1) {
		this.lat1 = lat1;
	}

	public Double getGlng1() {
		return glng1;
	}

	public void setGlng1(Double glng1) {
		this.glng1 = glng1;
	}

	public Double getGlat1() {
		return glat1;
	}

	public void setGlat1(Double glat1) {
		this.glat1 = glat1;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
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

	public String getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
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

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
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

	public Integer getApart() {
		return apart;
	}

	public void setApart(Integer apart) {
		this.apart = apart;
	}

	public String getPlatecode() {
		return platecode;
	}

	public void setPlatecode(String platecode) {
		this.platecode = platecode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public Integer getAcc() {
		return acc;
	}

	public void setAcc(Integer acc) {
		this.acc = acc;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}

	public Integer getCartype() {
		return cartype;
	}

	public void setCartype(Integer cartype) {
		this.cartype = cartype;
	}

	public String getCartypename() {
		return cartypename;
	}

	public void setCartypename(String cartypename) {
		this.cartypename = cartypename;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDaymileage() {
		return daymileage;
	}

	public void setDaymileage(String daymileage) {
		this.daymileage = daymileage;
	}

	public String getSortcolumn() {
		return sortcolumn;
	}

	public void setSortcolumn(String sortcolumn) {
		this.sortcolumn = sortcolumn;
	}

	public String getSortway() {
		return sortway;
	}

	public void setSortway(String sortway) {
		this.sortway = sortway;
	}

	public String getKzstate() {
		return kzstate;
	}

	public void setKzstate(String kzstate) {
		this.kzstate = kzstate;
	}

	


}



