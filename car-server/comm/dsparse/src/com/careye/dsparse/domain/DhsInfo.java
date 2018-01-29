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
 * 类名称：DhsInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-12-28 上午10:17:15    
 * 修改人：Administrator    
 * 修改时间：2015-12-28 上午10:17:15    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DhsInfo extends BaseInfo{

	/**行程ID*/
	private int tripid;

	/**时间*/
	private String time;

	/**状态*/
	private int state;

	/**电瓶电压*/
	private String vbat;

	/**发送机转速*/
	private int rpm;

	/**行驶车速*/
	private int spd;

	/**节气门开度*/
	private String tp;

	/**发送机负荷*/
	private String lod;

	/**冷却液温度*/
	private int ect;

	/**瞬时油耗*/
	private String ifuels;

	/**平均油耗*/
	private String avgsfuels;

	/**本次行驶里程*/
	private String milest;

	/**总里程*/
	private String miles;

	/**本次耗油量*/
	private String fuelt;

	/**累计耗油量*/
	private String fuels;

	/**当前故障码数量*/
	private int fcodes;

	/**本次急加速次数*/
	private int racls;

	/**本次急减速次数*/
	private int brakes;

	/**本次急转弯次数*/
	private int tst;

	/**本次热车时长*/
	private String hottime;

	/**本次怠速时长*/
	private String idletime;

	/**本次行驶时长*/
	private String runtime;

	/**本次怠速油耗*/
	private String idlefuelt;

	/**本次行驶油耗*/
	private String runfuelt;

	/**本次最高转速*/
	private int maxrpm;

	/**本次最高车速*/
	private int maxspd;

	/**点火次数*/
	private int starts;

	private int gpsflag;

	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;

	/**速度*/
	private String speed;

	/**方向*/
	private int direction;

	/**高度*/
	private int altitude;

	/**固件版本号*/
	private String fversion;

	/**车架号*/
	private String vin;

	/**故障信息*/
	private String fault;

	/**故障总数**/
	private int total;

	/**软件版本号*/
	private String sversion;

	/**系统调试信息*/
	private String debuginfo;

	/**启动时间*/
	private String starttime;

	/**熄火时间*/
	private String endtime;
	
	/**0x00 查询 0x01 设置*/
	private int type;
	
	/**IP*/
	private String ip;
	
	/**端口*/
	private int port;
	
	/**APN*/
	private String apn;
	
	/**数据类型*/
	private int datatype;
	
	/**间隔*/
	private int interval;
	
	/**热点名称*/
	private String name;
	
	/**热点密码*/
	private String password;
	
	/**平均车速*/
	private int avgspd;
	
	private String phone;
	
	private String content;
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAvgspd() {
		return avgspd;
	}

	public void setAvgspd(int avgspd) {
		this.avgspd = avgspd;
	}

	public int getTripid() {
		return tripid;
	}

	public void setTripid(int tripid) {
		this.tripid = tripid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public String getVbat() {
		return vbat;
	}

	public void setVbat(String vbat) {
		this.vbat = vbat;
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

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getLod() {
		return lod;
	}

	public void setLod(String lod) {
		this.lod = lod;
	}

	public int getEct() {
		return ect;
	}

	public void setEct(int ect) {
		this.ect = ect;
	}

	public String getIfuels() {
		return ifuels;
	}

	public void setIfuels(String ifuels) {
		this.ifuels = ifuels;
	}

	public String getAvgsfuels() {
		return avgsfuels;
	}

	public void setAvgsfuels(String avgsfuels) {
		this.avgsfuels = avgsfuels;
	}

	public String getMilest() {
		return milest;
	}

	public void setMilest(String milest) {
		this.milest = milest;
	}

	public String getMiles() {
		return miles;
	}

	public void setMiles(String miles) {
		this.miles = miles;
	}


	public String getFuelt() {
		return fuelt;
	}

	public void setFuelt(String fuelt) {
		this.fuelt = fuelt;
	}

	public String getFuels() {
		return fuels;
	}

	public void setFuels(String fuels) {
		this.fuels = fuels;
	}

	public int getFcodes() {
		return fcodes;
	}

	public void setFcodes(int fcodes) {
		this.fcodes = fcodes;
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

	public int getTst() {
		return tst;
	}

	public void setTst(int tst) {
		this.tst = tst;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public String getHottime() {
		return hottime;
	}

	public void setHottime(String hottime) {
		this.hottime = hottime;
	}

	public String getIdletime() {
		return idletime;
	}

	public void setIdletime(String idletime) {
		this.idletime = idletime;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getIdlefuelt() {
		return idlefuelt;
	}

	public void setIdlefuelt(String idlefuelt) {
		this.idlefuelt = idlefuelt;
	}

	public String getRunfuelt() {
		return runfuelt;
	}

	public void setRunfuelt(String runfuelt) {
		this.runfuelt = runfuelt;
	}

	public int getMaxrpm() {
		return maxrpm;
	}

	public void setMaxrpm(int maxrpm) {
		this.maxrpm = maxrpm;
	}

	public int getMaxspd() {
		return maxspd;
	}

	public void setMaxspd(int maxspd) {
		this.maxspd = maxspd;
	}

	public int getStarts() {
		return starts;
	}

	public void setStarts(int starts) {
		this.starts = starts;
	}

	public Integer getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(Integer gpsflag) {
		this.gpsflag = gpsflag;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
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

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public String getFversion() {
		return fversion;
	}

	public void setFversion(String fversion) {
		this.fversion = fversion;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getFault() {
		return fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSversion() {
		return sversion;
	}

	public void setSversion(String sversion) {
		this.sversion = sversion;
	}

	public String getDebuginfo() {
		return debuginfo;
	}

	public void setDebuginfo(String debuginfo) {
		this.debuginfo = debuginfo;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}


}
