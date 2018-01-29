/**
* Description: car-eye车辆管理平台
* 文件名：TerminalInfo.java
* 版本信息：1.0
* 日期：2014-5-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @项目名称：FMS
 * @类名称：TerminalInfo
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-5-26 下午05:28:06
 * @修改人：zr
 * @修改时间：2014-5-26 下午05:28:06
 * @修改备注：
 * @version 1.0
 */
public class TerminalParameter implements Serializable{

    private static final long serialVersionUID = 1978157949060756317L;

    /***************************** 基本参数设置 *****************************/
    /**
     * 终端心跳发送间隔(S)
     */
    private Integer terminalHeatTime;

    /**
     * TCP消息重传次数
     */
    private Integer tcpResend;

    /**
     * UDP消息重传次数
     */
    private Integer udpResend;

    /**
     * 主服务器APN参数
     */
    private String apnParam;

    /**
     * 主服务器无线通讯拨号密码
     */
    private String mainPwd;

    /**
     * 备份服务器APN参数
     */
    private String backApnParam;

    /**
     * 备份服务器无线通讯拨号密码
     */
    private String backPwd;

    /**
     * 位置汇报策略
     */
    private Integer pointUp = 1;

    /**
     * 休眠时汇报时间间隔(S)
     */
    private Integer sleepUp;
    
    /**
     * 驾驶员未登录汇报时间间隔
     */
    private Integer unLoginTime;

    /**
     * 缺省时间汇报间隔(S)
     */
    private Integer defaultSecondUp;

    /**
     * 驾驶员未登陆汇报距离间隔(S)
     */
    private Integer unLoginSecond;

    /**
     * 紧急报警时汇报距离间隔(S)
     */
    private Integer emerMileSecond;

    /**
     * 监控平台电话号码
     */
    private String platMonitorPhone;

    /**
     * 恢复出厂设置电话号码
     */
    private String recovePhone;

    /**
     * 接受终端SMS文本报警号码
     */
    private String smsPhone;

    /**
     * 每次最长通话时间(S)
     */
    private Integer longSecond;

    /**
     * 监听电话号码
     */
    private String monitorPhone;

    /**
     * TCP消息应答超时时间(S)
     */
    private Integer tcpTimeOut;

    /**
     * UDP消息应答超时时间(S)
     */
    private Integer udpTimeOut;
    
    /**
     * SMS消息应答超时时间
     */
    private Integer smsTimeOut;

    /**
     * SMS消息重传次数
     */
    private Integer smsRecount;

    /**
     * 主服务器无线通讯拨号用户名
     */
    private String mainLogin;

    /**
     * 主服务器地址
     */
    private String mainAddr;

    /**
     * 备份服务器无线通讯拨号用户名
     */
    private String backLogin;

    /**
     * 备份服务器地址
     */
    private String backAddr;

    /**
     * 位置汇报方案
     */
    private Integer pointUpScheme = 1;

    /**
     * 紧急报警时汇报时间间隔(S)
     */
    private Integer emerSecond;

    /**
     * 缺省距离汇报间隔(米)
     */
    private Integer defaultUpMile;

    /**
     * 休眠时汇报距离间隔(米)
     */
    private Integer sleepUpMile;

    /**
     * 拐点补传角度(度)
     */
    private Integer inflection;

    /**
     * 复位电话号码
     */
    private String resetPhone;

    /**
     * 监控平台SMS电话号码
     */
    private String platformSmsPhone;

    /**
     * 终端电话接听策略
     */
    private Integer terminalPick = 1;

    /**
     * 当月最长通话时间
     */
    private Integer monthSecond;

    /**
     * 监管平台特权短信号码
     */
    private String platformAdminSmsPhone;
    
    /**
     * 报险电话号码
     */
    private String insurancePhone;

    /***************************** 行驶参数设置 *****************************/

    /**
     * 最高速度(km/小时)
     */
    private Integer highSpeed;

    /**
     * 连续驾驶时间上限(S)
     */
    private Integer continuTimeTop;

    /**
     * 最小休息时间(S)
     */
    private Integer smallSleepTime;

    /**
     * 超速持续时间(S)
     */
    private Integer superSpeedSecood;

    /**
     * 当天累计驾驶时间上限(S)
     */
    private Integer totalDriverTime;

    /**
     * 最长停车时间(S)
     */
    private Integer longunDriverSecond;

    /***************************** 其它参数设置 *****************************/

    /**
     * 车辆里程表读数
     */
    private Integer carMile;

    /**
     * 车辆所在的市域ID
     */
    private Integer carCityId;

    /**
     * 车牌颜色
     */
    private Integer carColor;

    /**
     * 车辆所在省域ID
     */
    private Integer carProvinteId;

    /**
     * 机动车号牌
     */
    private String carCard;

    private int[] terminal;
    
    private String simCode;
    
    private Integer reqCount;
    
    private Date localTime;
    
    private Integer reCount;
    
    
    /**************************************车辆位置信息参数*************************/
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
	private String terminalnum;
	
	/**车牌号*/
	private String carnumber;
	
	/**车辆当前状态 在线、离线、 熄火、 行驶、停车、报警 未定位*/
	private String carstatus;
	
	/**运输状态、 休息  、待配货、 运输中  默认*/
	private String travelposition;
	
	/**速度 */
	private String speed;
	
	/**里程 */
	private String mileage;
	
	/**方向 */
	private String direction;
	
	/**GPS时间 */
	private String gpstime;
	
	/**GPS是否有效 */
	private Integer gpsflag;
	
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
	private Integer diff;
	
	/**状态*/
	private String sn;
	
	/**状态值*/
	private Integer snvalue;
	
	/**报警标志*/
	private String an;
	
	/**报警标志值*/
	private Integer anvalue;
	
	private String carmodel;
	private String cartype;
	private String drivername;
	private String deptname;
	/**车辆图标**/
	private String icon;
	
	/**服务器TCP端口*/
	private String tcpPort;
	
	/**服务器UDP端口*/
	private String udpPort;

	public Integer getTerminalHeatTime() {
		return terminalHeatTime;
	}

	public void setTerminalHeatTime(Integer terminalHeatTime) {
		this.terminalHeatTime = terminalHeatTime;
	}

	public Integer getTcpResend() {
		return tcpResend;
	}

	public void setTcpResend(Integer tcpResend) {
		this.tcpResend = tcpResend;
	}

	public Integer getUdpResend() {
		return udpResend;
	}

	public void setUdpResend(Integer udpResend) {
		this.udpResend = udpResend;
	}

	public String getApnParam() {
		return apnParam;
	}

	public void setApnParam(String apnParam) {
		this.apnParam = apnParam;
	}

	public String getMainPwd() {
		return mainPwd;
	}

	public void setMainPwd(String mainPwd) {
		this.mainPwd = mainPwd;
	}

	public String getBackApnParam() {
		return backApnParam;
	}

	public void setBackApnParam(String backApnParam) {
		this.backApnParam = backApnParam;
	}

	public String getBackPwd() {
		return backPwd;
	}

	public void setBackPwd(String backPwd) {
		this.backPwd = backPwd;
	}

	public Integer getPointUp() {
		return pointUp;
	}

	public void setPointUp(Integer pointUp) {
		this.pointUp = pointUp;
	}

	public Integer getSleepUp() {
		return sleepUp;
	}

	public void setSleepUp(Integer sleepUp) {
		this.sleepUp = sleepUp;
	}

	public Integer getUnLoginTime() {
		return unLoginTime;
	}

	public void setUnLoginTime(Integer unLoginTime) {
		this.unLoginTime = unLoginTime;
	}

	public Integer getDefaultSecondUp() {
		return defaultSecondUp;
	}

	public void setDefaultSecondUp(Integer defaultSecondUp) {
		this.defaultSecondUp = defaultSecondUp;
	}

	public Integer getUnLoginSecond() {
		return unLoginSecond;
	}

	public void setUnLoginSecond(Integer unLoginSecond) {
		this.unLoginSecond = unLoginSecond;
	}

	public Integer getEmerMileSecond() {
		return emerMileSecond;
	}

	public void setEmerMileSecond(Integer emerMileSecond) {
		this.emerMileSecond = emerMileSecond;
	}

	public String getPlatMonitorPhone() {
		return platMonitorPhone;
	}

	public void setPlatMonitorPhone(String platMonitorPhone) {
		this.platMonitorPhone = platMonitorPhone;
	}

	public String getRecovePhone() {
		return recovePhone;
	}

	public void setRecovePhone(String recovePhone) {
		this.recovePhone = recovePhone;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public Integer getLongSecond() {
		return longSecond;
	}

	public void setLongSecond(Integer longSecond) {
		this.longSecond = longSecond;
	}

	public String getMonitorPhone() {
		return monitorPhone;
	}

	public void setMonitorPhone(String monitorPhone) {
		this.monitorPhone = monitorPhone;
	}

	public Integer getTcpTimeOut() {
		return tcpTimeOut;
	}

	public void setTcpTimeOut(Integer tcpTimeOut) {
		this.tcpTimeOut = tcpTimeOut;
	}

	public Integer getUdpTimeOut() {
		return udpTimeOut;
	}

	public void setUdpTimeOut(Integer udpTimeOut) {
		this.udpTimeOut = udpTimeOut;
	}

	public Integer getSmsTimeOut() {
		return smsTimeOut;
	}

	public void setSmsTimeOut(Integer smsTimeOut) {
		this.smsTimeOut = smsTimeOut;
	}

	public Integer getSmsRecount() {
		return smsRecount;
	}

	public void setSmsRecount(Integer smsRecount) {
		this.smsRecount = smsRecount;
	}

	public String getMainLogin() {
		return mainLogin;
	}

	public void setMainLogin(String mainLogin) {
		this.mainLogin = mainLogin;
	}

	public String getMainAddr() {
		return mainAddr;
	}

	public void setMainAddr(String mainAddr) {
		this.mainAddr = mainAddr;
	}

	public String getBackLogin() {
		return backLogin;
	}

	public void setBackLogin(String backLogin) {
		this.backLogin = backLogin;
	}

	public String getBackAddr() {
		return backAddr;
	}

	public void setBackAddr(String backAddr) {
		this.backAddr = backAddr;
	}

	public Integer getPointUpScheme() {
		return pointUpScheme;
	}

	public void setPointUpScheme(Integer pointUpScheme) {
		this.pointUpScheme = pointUpScheme;
	}

	public Integer getEmerSecond() {
		return emerSecond;
	}

	public void setEmerSecond(Integer emerSecond) {
		this.emerSecond = emerSecond;
	}

	public Integer getDefaultUpMile() {
		return defaultUpMile;
	}

	public void setDefaultUpMile(Integer defaultUpMile) {
		this.defaultUpMile = defaultUpMile;
	}

	public Integer getSleepUpMile() {
		return sleepUpMile;
	}

	public void setSleepUpMile(Integer sleepUpMile) {
		this.sleepUpMile = sleepUpMile;
	}

	public Integer getInflection() {
		return inflection;
	}

	public void setInflection(Integer inflection) {
		this.inflection = inflection;
	}

	public String getResetPhone() {
		return resetPhone;
	}

	public void setResetPhone(String resetPhone) {
		this.resetPhone = resetPhone;
	}

	public String getPlatformSmsPhone() {
		return platformSmsPhone;
	}

	public void setPlatformSmsPhone(String platformSmsPhone) {
		this.platformSmsPhone = platformSmsPhone;
	}

	public Integer getTerminalPick() {
		return terminalPick;
	}

	public void setTerminalPick(Integer terminalPick) {
		this.terminalPick = terminalPick;
	}

	public Integer getMonthSecond() {
		return monthSecond;
	}

	public void setMonthSecond(Integer monthSecond) {
		this.monthSecond = monthSecond;
	}

	public String getPlatformAdminSmsPhone() {
		return platformAdminSmsPhone;
	}

	public void setPlatformAdminSmsPhone(String platformAdminSmsPhone) {
		this.platformAdminSmsPhone = platformAdminSmsPhone;
	}

	public String getInsurancePhone() {
		return insurancePhone;
	}

	public void setInsurancePhone(String insurancePhone) {
		this.insurancePhone = insurancePhone;
	}

	public Integer getHighSpeed() {
		return highSpeed;
	}

	public void setHighSpeed(Integer highSpeed) {
		this.highSpeed = highSpeed;
	}

	public Integer getContinuTimeTop() {
		return continuTimeTop;
	}

	public void setContinuTimeTop(Integer continuTimeTop) {
		this.continuTimeTop = continuTimeTop;
	}

	public Integer getSmallSleepTime() {
		return smallSleepTime;
	}

	public void setSmallSleepTime(Integer smallSleepTime) {
		this.smallSleepTime = smallSleepTime;
	}

	public Integer getSuperSpeedSecood() {
		return superSpeedSecood;
	}

	public void setSuperSpeedSecood(Integer superSpeedSecood) {
		this.superSpeedSecood = superSpeedSecood;
	}

	public Integer getTotalDriverTime() {
		return totalDriverTime;
	}

	public void setTotalDriverTime(Integer totalDriverTime) {
		this.totalDriverTime = totalDriverTime;
	}

	public Integer getLongunDriverSecond() {
		return longunDriverSecond;
	}

	public void setLongunDriverSecond(Integer longunDriverSecond) {
		this.longunDriverSecond = longunDriverSecond;
	}

	public Integer getCarMile() {
		return carMile;
	}

	public void setCarMile(Integer carMile) {
		this.carMile = carMile;
	}

	public Integer getCarCityId() {
		return carCityId;
	}

	public void setCarCityId(Integer carCityId) {
		this.carCityId = carCityId;
	}

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public Integer getCarProvinteId() {
		return carProvinteId;
	}

	public void setCarProvinteId(Integer carProvinteId) {
		this.carProvinteId = carProvinteId;
	}

	public String getCarCard() {
		return carCard;
	}

	public void setCarCard(String carCard) {
		this.carCard = carCard;
	}


	public String getSimCode() {
		return simCode;
	}

	public void setSimCode(String simCode) {
		this.simCode = simCode;
	}

	public Integer getReqCount() {
		return reqCount;
	}

	public void setReqCount(Integer reqCount) {
		this.reqCount = reqCount;
	}

	public Date getLocalTime() {
		return localTime;
	}

	public void setLocalTime(Date localTime) {
		this.localTime = localTime;
	}

	public Integer getReCount() {
		return reCount;
	}

	public void setReCount(Integer reCount) {
		this.reCount = reCount;
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

	public String getTerminalnum() {
		return terminalnum;
	}

	public void setTerminalnum(String terminalnum) {
		this.terminalnum = terminalnum;
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

	public String getTravelposition() {
		return travelposition;
	}

	public void setTravelposition(String travelposition) {
		this.travelposition = travelposition;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
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

	public Integer getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(Integer gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getGpsflagtext() {
		return gpsflagtext;
	}

	public void setGpsflagtext(String gpsflagtext) {
		this.gpsflagtext = gpsflagtext;
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

	public Integer getDiff() {
		return diff;
	}

	public void setDiff(Integer diff) {
		this.diff = diff;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getSnvalue() {
		return snvalue;
	}

	public void setSnvalue(Integer snvalue) {
		this.snvalue = snvalue;
	}

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}

	public Integer getAnvalue() {
		return anvalue;
	}

	public void setAnvalue(Integer anvalue) {
		this.anvalue = anvalue;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(String tcpPort) {
		this.tcpPort = tcpPort;
	}

	public String getUdpPort() {
		return udpPort;
	}

	public void setUdpPort(String udpPort) {
		this.udpPort = udpPort;
	}

	public int[] getTerminal() {
		return terminal;
	}

	public void setTerminal(int[] terminal) {
		this.terminal = terminal;
	}
	
}



