/**
* Description: car-eye车辆管理平台
* 文件名：Alarm.java
* 版本信息：1.0
* 日期：2014-5-23
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

import java.io.Serializable;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：FMS
 * @类名称：Alarm
 * @类描述：报警实体类
 * @创建人：zr
 * @创建时间：2014-5-23 上午11:25:42
 * @修改人：zr
 * @修改时间：2014-5-23 上午11:25:42
 * @修改备注：
 * @version 1.0
 */
public class Alarm  extends BaseDomain  implements  Serializable{
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/** 主键 Id **/
	private Integer id;
	private Integer carid;
	/**集团Id **/
	private Integer blocid;
	/** 终端号码 **/
	private String terminal;
	/** 车牌号**/
	private String carnumber;
	/** 报警名称**/
	private String alarmname;
	/** 报警KEY**/
	private String alarmkey;
	/**经度 **/
	private String lng;
	/**纬度 **/
	private String lat;
	/**高度 **/
	private String altitude;
	/** 速度**/
	private String speed;
	/** 方向**/
	private String direction;
	/** 地址**/
	private String address;
	/** 省**/
	private String province;
	/** 市**/
	private String city;
	/** 县**/
	private String district;
	/** 百度经度**/
	private String blng;
	/** 百度纬度**/
	private String blat;
	/** 高德经度**/
	private String glng;
	/** 高德纬度**/
	private String glat;
	/** 高德地址**/
	private String gaddress;
	/** 原始数据包**/
	private String rawdata;
	/** 报警时间**/
	private String alarmtime;
	/** 报警来源  1 终端 2 服务器端 默认终端**/
	private Integer alarmsource;
	/** 1：未处理 2 已处理   默认：1**/
	private Integer prostatus;
	/** 处理内容**/
	private String procontent;
	/** 处理时间**/
	private String protime;
	/** 处理人**/
	private String dealperson;
	/** 备注**/
	private String remark;
	
	
	/** 报警天数表 **/
	private String remingtable;
	
    private String loginname;
    private String blocname;
    private String stime1;
    private String etime1;
    
    private Integer carstatus;
    
	/**报警处理时间**/
	private String dealtime;
	private String createtime;
	/**报警处理内容**/
	private String dealcontent;
	/**报警总数数**/
	private Integer remindcount;
	/**报警未处理数**/
	private String unsolvecount;
	
	private Integer flag;
	/**报警1未处理 2 已处理3已过期**/
	private Integer status;
	
	
	private Integer diff;
	
	private String drivername;
	private String phone;
	
	private Integer alarmtype;
	private Integer areaid;
	
	private List<String> idlist;
    
    
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
	public String getRemingtable() {
		return remingtable;
	}
	public void setRemingtable(String remingtable) {
		this.remingtable = remingtable;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public String getStime1() {
		return stime1;
	}
	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}
	public String getEtime1() {
		return etime1;
	}
	public void setEtime1(String etime1) {
		this.etime1 = etime1;
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
	public String getBlng() {
		return blng;
	}
	public void setBlng(String blng) {
		this.blng = blng;
	}
	public String getBlat() {
		return blat;
	}
	public void setBlat(String blat) {
		this.blat = blat;
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
	public Integer getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}
	public String getDealperson() {
		return dealperson;
	}
	public void setDealperson(String dealperson) {
		this.dealperson = dealperson;
	}
	public String getDealtime() {
		return dealtime;
	}
	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getDealcontent() {
		return dealcontent;
	}
	public void setDealcontent(String dealcontent) {
		this.dealcontent = dealcontent;
	}
	public Integer getRemindcount() {
		return remindcount;
	}
	public void setRemindcount(Integer remindcount) {
		this.remindcount = remindcount;
	}
	public String getUnsolvecount() {
		return unsolvecount;
	}
	public void setUnsolvecount(String unsolvecount) {
		this.unsolvecount = unsolvecount;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDiff() {
		return diff;
	}
	public void setDiff(Integer diff) {
		this.diff = diff;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<String> getIdlist() {
		return idlist;
	}
	public void setIdlist(List<String> idlist) {
		this.idlist = idlist;
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
