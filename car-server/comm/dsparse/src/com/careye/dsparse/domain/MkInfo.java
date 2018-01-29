/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：MkInfo    
 * 类描述： 深圳麦卡途科技有限公司MK6(8)000产品协议实体   
 * 创建人：Administrator    
 * 创建时间：2015-12-9 下午06:54:10    
 * 修改人：Administrator    
 * 修改时间：2015-12-9 下午06:54:10    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MkInfo extends BaseInfo{

	/**类型*/
	private int type;

	/**制造商*/
	private String mac;

	/**软件版本号*/
	private String ver;

	/**设备ID*/
	private String terid;

	/**省电模式 0 不开启 1 开启 */
	private int mode;

	/**时差 */
	private int timediff;

	/**报警类型*/
	private int alarmtype;

	/**报警对应值*/
	private String value1;

	/**报警对应值*/
	private String value2;

	/**报警时间*/
	private String alarmtime;

	/**经度*/
	private int lng;

	/**纬度*/
	private int lat;

	/**定位状态*/
	private int gpsflag;

	/**北纬/南纬*/
	private int ns;

	/**东经/西经*/
	private int ew;

	/**结果*/
	private int result;

	/**参数1*/
	private int param1;

	/**参数2*/
	private int param2;

	/**参数3*/
	private int param3;

	/**参数4*/
	private int param4;

	/**设备别名*/
	private String alias;

	/**时间*/
	private String time;

	/**里程*/
	private String mileage;

	/**唤醒方式*/
	private int way;

	/**报告时间*/
	private String reporttime;

	/**传感器震劢等级*/
	private int level;

	/**IP/域名*/
	private String ip;

	/**端口*/
	private String port;

	/**apn*/
	private String apn;

	/**手机号*/
	private String phone;

	/**apn用户名*/
	private String username;

	/**apn密码*/
	private String password;

	/**IMEI号*/
	private String imei;

	/**心跳间隔*/
	private int interval;

	/**上传通道*/
	private int channel;

	/**点火上传间隔*/
	private int iinterval;

	/**熄火上传的间隔*/
	private int rinterval;

	/**数据类型*/
	private int datatype;

	/**行程ID*/
	private int tripid;

	/**上传时间*/
	private String uploadtime;

	/**速度*/
	private String speed;

	/**方向*/
	private int direction;

	/**GPS卫星个数*/
	private int gpsnum;

	/**GSM 信号质量*/
	private int gsm;

	/**保留*/
	private String reserve;

	/**流水号*/
	private int sn;
	
	private int s10;
	private int s11;
	private int s12;
	private int s13;
	private int s14;
	private int s15;
	private int s16;
	private int s17;

	private int s20;
	private int s21;
	private int s22;
	private int s23;
	private int s24;

	private int s30;
	private int s31;
	private int s32;
	private int s33;
	private int s34;
	private int s35;
	private int s36;
	private int s37;

	private int s40;
	private int s41;
	private int s42;
	private int s43;
	private int s44;
	private int s45;
	private int s46;
	private int s47;

	/**负荷计算值*/
	private String cvol;

	/**冷却液温度*/
	private int ect;

	/**发劢机转速*/
	private int rpm;

	/**OBD车速*/
	private int spd;

	/**点火提前角*/
	private int tiaa;

	/**进气歧管绝对压力*/
	private int imap;

	/**控制模块电压*/
	private String vbat;

	/**进气温度*/
	private int temp;

	/**空气流量*/
	private int taf;

	/**节气门相对位置*/
	private String tpos;

	/**长期燃油修正*/
	private String lfc;

	/**空燃比系数*/
	private String arc;

	/**节气门绝对位置*/
	private String tap;

	/**燃油压力*/
	private int kpa;

	/**瞬时油耗1*/
	private String fli1;

	/**瞬时油耗2*/
	private String fli2;

	/**车型*/
	private int vehiclemodel;

	/**油箱容积*/
	private int volume;
	
	/**车架号*/
	private String vin;

	/**故障码*/
	private String faultcode;
	
	/**点火时间*/
	private String starttime;
	
	/**熄火时间*/
	private String endtime;
	
	/**该次行驶时间*/
	private int runtime;
	
	/**该次耗油量*/
	private String fuelt;
	
	/**最高速度*/
	private int maxspeed;
	
	/**发动机最高转速*/
	private int maxrpm;
	
	/**冷却液最高温度*/
	private int etc;
	
	/**急加速次数*/
	private int racls;
	
	/**急减速次数*/
	private int brakes;
	
	/**超速行驶时间*/
	private int overtime;
	
	/**超速行驶的里程*/
	private String overmileage;
	
	/**超速行驶的耗油量*/
	private String overfuelt;
	
	/**高速行驶的时间*/
	private int hstime;
	
	/**高速行驶的里程*/
	private String hsmileage;
	
	/**高速行驶的耗油量*/
	private String hsfuelt;
	
	/**中速行驶的时间*/
	private int istime;
	
	/**中速行驶的里程*/
	private String ismileage;
	
	/**中速行驶的耗油量*/
	private String isfuelt;
	
	/**低速行驶的时间*/
	private int lstime;
	
	/**低速行驶的里程*/
	private String lsmileage;
	
	/**低速行驶的耗油量*/
	private String lsfuelt;
	
	/**怠速的时间*/
	private int idletime;
	
	/**怠速的耗油量*/
	private String idlefuelt;
	
	/**急转弯次数*/
	private int tst;
	
	/**超速次数*/
	private int spdnum;
	
	/**热车时间*/
	private int hottime;
	
	/**快速变道*/
	private int rapidcl;
	
	/**急加油(地板油)*/
	private int hco;
	
	/**点火次数*/
	private int starts;
	
	/**软件版本号*/
	private String softversion;
	
	/**总数*/
	private int count;
	
	/**指令长度*/
	private int len;
	
	/**下一条包序号*/
	private int nextpseq;
	
	/**包序号*/
	private int pseq;
	
	/**数据*/
	private String datahex;
	
	
	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getNextpseq() {
		return nextpseq;
	}

	public void setNextpseq(int nextpseq) {
		this.nextpseq = nextpseq;
	}

	public int getPseq() {
		return pseq;
	}

	public void setPseq(int pseq) {
		this.pseq = pseq;
	}

	public String getDatahex() {
		return datahex;
	}

	public void setDatahex(String datahex) {
		this.datahex = datahex;
	}

	public String getSoftversion() {
		return softversion;
	}

	public void setSoftversion(String softversion) {
		this.softversion = softversion;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStarts() {
		return starts;
	}

	public void setStarts(int starts) {
		this.starts = starts;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getFuelt() {
		return fuelt;
	}

	public void setFuelt(String fuelt) {
		this.fuelt = fuelt;
	}

	public int getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(int maxspeed) {
		this.maxspeed = maxspeed;
	}

	public int getMaxrpm() {
		return maxrpm;
	}

	public void setMaxrpm(int maxrpm) {
		this.maxrpm = maxrpm;
	}

	public int getEtc() {
		return etc;
	}

	public void setEtc(int etc) {
		this.etc = etc;
	}

	public int getRacls() {
		return racls;
	}

	public void setRacls(int racls) {
		this.racls = racls;
	}

	public int getBrakes() {
		return brakes;
	}

	public void setBrakes(int brakes) {
		this.brakes = brakes;
	}

	public int getOvertime() {
		return overtime;
	}

	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}

	public String getOvermileage() {
		return overmileage;
	}

	public void setOvermileage(String overmileage) {
		this.overmileage = overmileage;
	}

	public String getOverfuelt() {
		return overfuelt;
	}

	public void setOverfuelt(String overfuelt) {
		this.overfuelt = overfuelt;
	}

	public int getHstime() {
		return hstime;
	}

	public void setHstime(int hstime) {
		this.hstime = hstime;
	}

	public String getHsmileage() {
		return hsmileage;
	}

	public void setHsmileage(String hsmileage) {
		this.hsmileage = hsmileage;
	}

	public String getHsfuelt() {
		return hsfuelt;
	}

	public void setHsfuelt(String hsfuelt) {
		this.hsfuelt = hsfuelt;
	}

	public int getIstime() {
		return istime;
	}

	public void setIstime(int istime) {
		this.istime = istime;
	}

	public String getIsmileage() {
		return ismileage;
	}

	public void setIsmileage(String ismileage) {
		this.ismileage = ismileage;
	}

	public String getIsfuelt() {
		return isfuelt;
	}

	public void setIsfuelt(String isfuelt) {
		this.isfuelt = isfuelt;
	}

	public int getLstime() {
		return lstime;
	}

	public void setLstime(int lstime) {
		this.lstime = lstime;
	}

	public String getLsmileage() {
		return lsmileage;
	}

	public void setLsmileage(String lsmileage) {
		this.lsmileage = lsmileage;
	}

	public String getLsfuelt() {
		return lsfuelt;
	}

	public void setLsfuelt(String lsfuelt) {
		this.lsfuelt = lsfuelt;
	}

	public int getIdletime() {
		return idletime;
	}

	public void setIdletime(int idletime) {
		this.idletime = idletime;
	}

	public String getIdlefuelt() {
		return idlefuelt;
	}

	public void setIdlefuelt(String idlefuelt) {
		this.idlefuelt = idlefuelt;
	}

	public int getTst() {
		return tst;
	}

	public void setTst(int tst) {
		this.tst = tst;
	}

	public int getSpdnum() {
		return spdnum;
	}

	public void setSpdnum(int spdnum) {
		this.spdnum = spdnum;
	}

	public int getHottime() {
		return hottime;
	}

	public void setHottime(int hottime) {
		this.hottime = hottime;
	}

	public int getRapidcl() {
		return rapidcl;
	}

	public void setRapidcl(int rapidcl) {
		this.rapidcl = rapidcl;
	}

	public int getHco() {
		return hco;
	}

	public void setHco(int hco) {
		this.hco = hco;
	}

	public String getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getVehiclemodel() {
		return vehiclemodel;
	}

	public void setVehiclemodel(int vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getTerid() {
		return terid;
	}

	public void setTerid(String terid) {
		this.terid = terid;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getTimediff() {
		return timediff;
	}

	public void setTimediff(int timediff) {
		this.timediff = timediff;
	}

	public int getAlarmtype() {
		return alarmtype;
	}

	public void setAlarmtype(int alarmtype) {
		this.alarmtype = alarmtype;
	}


	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getAlarmtime() {
		return alarmtime;
	}

	public void setAlarmtime(String alarmtime) {
		this.alarmtime = alarmtime;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}

	public int getNs() {
		return ns;
	}

	public void setNs(int ns) {
		this.ns = ns;
	}

	public int getEw() {
		return ew;
	}

	public void setEw(int ew) {
		this.ew = ew;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public int getParam2() {
		return param2;
	}

	public void setParam2(int param2) {
		this.param2 = param2;
	}

	public int getParam3() {
		return param3;
	}

	public void setParam3(int param3) {
		this.param3 = param3;
	}

	public int getParam4() {
		return param4;
	}

	public void setParam4(int param4) {
		this.param4 = param4;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getIinterval() {
		return iinterval;
	}

	public void setIinterval(int iinterval) {
		this.iinterval = iinterval;
	}

	public int getRinterval() {
		return rinterval;
	}

	public void setRinterval(int rinterval) {
		this.rinterval = rinterval;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getTripid() {
		return tripid;
	}

	public void setTripid(int tripid) {
		this.tripid = tripid;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getGpsnum() {
		return gpsnum;
	}

	public void setGpsnum(int gpsnum) {
		this.gpsnum = gpsnum;
	}

	public int getGsm() {
		return gsm;
	}

	public void setGsm(int gsm) {
		this.gsm = gsm;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public int getS20() {
		return s20;
	}

	public void setS20(int s20) {
		this.s20 = s20;
	}

	public int getS21() {
		return s21;
	}

	public void setS21(int s21) {
		this.s21 = s21;
	}

	public int getS22() {
		return s22;
	}

	public void setS22(int s22) {
		this.s22 = s22;
	}

	public int getS23() {
		return s23;
	}

	public void setS23(int s23) {
		this.s23 = s23;
	}

	public int getS24() {
		return s24;
	}

	public void setS24(int s24) {
		this.s24 = s24;
	}

	public int getS30() {
		return s30;
	}

	public void setS30(int s30) {
		this.s30 = s30;
	}

	public int getS31() {
		return s31;
	}

	public void setS31(int s31) {
		this.s31 = s31;
	}

	public int getS32() {
		return s32;
	}

	public void setS32(int s32) {
		this.s32 = s32;
	}

	public int getS33() {
		return s33;
	}

	public void setS33(int s33) {
		this.s33 = s33;
	}

	public int getS34() {
		return s34;
	}

	public void setS34(int s34) {
		this.s34 = s34;
	}

	public int getS35() {
		return s35;
	}

	public void setS35(int s35) {
		this.s35 = s35;
	}

	public int getS36() {
		return s36;
	}

	public void setS36(int s36) {
		this.s36 = s36;
	}

	public int getS37() {
		return s37;
	}

	public void setS37(int s37) {
		this.s37 = s37;
	}

	public int getS40() {
		return s40;
	}

	public void setS40(int s40) {
		this.s40 = s40;
	}

	public int getS41() {
		return s41;
	}

	public void setS41(int s41) {
		this.s41 = s41;
	}

	public int getS42() {
		return s42;
	}

	public void setS42(int s42) {
		this.s42 = s42;
	}

	public int getS43() {
		return s43;
	}

	public void setS43(int s43) {
		this.s43 = s43;
	}

	public int getS44() {
		return s44;
	}

	public void setS44(int s44) {
		this.s44 = s44;
	}

	public int getS45() {
		return s45;
	}

	public void setS45(int s45) {
		this.s45 = s45;
	}

	public int getS46() {
		return s46;
	}

	public void setS46(int s46) {
		this.s46 = s46;
	}

	public int getS47() {
		return s47;
	}

	public void setS47(int s47) {
		this.s47 = s47;
	}

	public String getCvol() {
		return cvol;
	}

	public void setCvol(String cvol) {
		this.cvol = cvol;
	}


	public int getEct() {
		return ect;
	}

	public void setEct(int ect) {
		this.ect = ect;
	}

	public int getRpm() {
		return rpm;
	}

	public void setRpm(int rpm) {
		this.rpm = rpm;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}


	public int getTiaa() {
		return tiaa;
	}

	public void setTiaa(int tiaa) {
		this.tiaa = tiaa;
	}

	public int getImap() {
		return imap;
	}

	public void setImap(int imap) {
		this.imap = imap;
	}

	public String getVbat() {
		return vbat;
	}

	public void setVbat(String vbat) {
		this.vbat = vbat;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}


	public int getTaf() {
		return taf;
	}

	public void setTaf(int taf) {
		this.taf = taf;
	}

	public String getTpos() {
		return tpos;
	}

	public void setTpos(String tpos) {
		this.tpos = tpos;
	}

	public String getLfc() {
		return lfc;
	}

	public void setLfc(String lfc) {
		this.lfc = lfc;
	}

	public String getArc() {
		return arc;
	}

	public void setArc(String arc) {
		this.arc = arc;
	}

	public String getTap() {
		return tap;
	}

	public void setTap(String tap) {
		this.tap = tap;
	}

	public int getKpa() {
		return kpa;
	}

	public void setKpa(int kpa) {
		this.kpa = kpa;
	}

	public String getFli1() {
		return fli1;
	}

	public void setFli1(String fli1) {
		this.fli1 = fli1;
	}

	public String getFli2() {
		return fli2;
	}

	public void setFli2(String fli2) {
		this.fli2 = fli2;
	}

	public int getS10() {
		return s10;
	}

	public void setS10(int s10) {
		this.s10 = s10;
	}

	public int getS11() {
		return s11;
	}

	public void setS11(int s11) {
		this.s11 = s11;
	}

	public int getS12() {
		return s12;
	}

	public void setS12(int s12) {
		this.s12 = s12;
	}

	public int getS13() {
		return s13;
	}

	public void setS13(int s13) {
		this.s13 = s13;
	}

	public int getS14() {
		return s14;
	}

	public void setS14(int s14) {
		this.s14 = s14;
	}

	public int getS15() {
		return s15;
	}

	public void setS15(int s15) {
		this.s15 = s15;
	}

	public int getS16() {
		return s16;
	}

	public void setS16(int s16) {
		this.s16 = s16;
	}

	public int getS17() {
		return s17;
	}

	public void setS17(int s17) {
		this.s17 = s17;
	}
	

}
