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
 * 类名称：HlsscInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-9-24 下午01:54:52    
 * 修改人：Administrator    
 * 修改时间：2015-9-24 下午01:54:52    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class HlsscInfo extends BaseInfo{
	
	/**行程ID*/
	private String tripid;
	
	/**电压*/
	private String vbat;
	
	/**冷却液温度*/
	private String ect;
	
	/**车速*/
	private String spd;
	
	/**DTC:002,&P0023&P0126 002代表总数&P0023&P0126 代表具体的故障码*/
	private String dtc;
	
	/**加速度传感器 -y*/
	private String gy;
	
	/**加速度传感器 -z*/
	private String gz;
	
	/**加速度传感器 -x*/
	private String gx;
	
	/**发动机当前载负*/
	private String lod;
	
	/**绝对节气门开度（位置）*/
	private String tp;
	
	/** YM M表示百公里油耗 Y表示MAP的油耗算法*/
	private String ym;
	
	/** YM M表示百公里油耗 Y表示MAP的油耗算法*/
	private String yh;
	
	/**剩余油量*/
	private String fli;
	
	/**车行时间(s)*/
	private String times;
	
	/**急刹车*/
	private String brakes;
	
	/**急加速*/
	private String rapid;
	
	/**发动机转速*/
	private String rpm;
	
	/**此处行程油耗*/
	private String fuelst;
	
	/**行车里程*/
	private String milest; 
	
	/**油耗计算方式：0MAP方式   1MAF方式*/
	private String maf;
	
	/**0熄火  1点火;*/
	private String power;
	
	/**当次行程平均车速;*/
	private String avgspd;
	
	/**当次行程最大转速*/
	private String maxrpm;
	
	/**当次行程最小转速;*/
	private String minrpm;
	
	/**当次行程最大加速度;*/
	private String maxacl;
	
	/**当次行程最大车速;*/
	private String maxspd;
	
	/**点火次数;*/
	private String starts;
	
	/**急加速次数;*/
	private String racls;
	
	/**总油耗 L;*/
	private String fuels;
	
	/**总里程 KM; */
	private String miles;
	
	/**当次油耗 L;*/
	private String fuelt;
	
	/**当次行程 KM;*/
	private String milet;
	
	private Integer gpsflag;
	
	/**纬度*/
	private int lat;
	
	/**经度*/
	private int lng;
	
	/**速度*/
	private String speed;
	
	/**方向*/
	private int direction;
	
	/**时间*/
	private String gpstime;
	
	/**速度*/
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
	
	

	public String getFault() {
		return fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public String getSversion() {
		return sversion;
	}

	public void setSversion(String sversion) {
		this.sversion = sversion;
	}

	public String getVbat() {
		return vbat;
	}

	public void setVbat(String vbat) {
		this.vbat = vbat;
	}

	public String getEct() {
		return ect;
	}

	public void setEct(String ect) {
		this.ect = ect;
	}

	public String getSpd() {
		return spd;
	}

	public void setSpd(String spd) {
		this.spd = spd;
	}

	public String getDtc() {
		return dtc;
	}

	public void setDtc(String dtc) {
		this.dtc = dtc;
	}

	public String getGy() {
		return gy;
	}

	public void setGy(String gy) {
		this.gy = gy;
	}

	public String getGz() {
		return gz;
	}

	public void setGz(String gz) {
		this.gz = gz;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String getLod() {
		return lod;
	}

	public void setLod(String lod) {
		this.lod = lod;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getYh() {
		return yh;
	}

	public void setYh(String yh) {
		this.yh = yh;
	}

	public String getFli() {
		return fli;
	}

	public void setFli(String fli) {
		this.fli = fli;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getBrakes() {
		return brakes;
	}

	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}

	public String getRapid() {
		return rapid;
	}

	public void setRapid(String rapid) {
		this.rapid = rapid;
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm;
	}

	public String getFuelst() {
		return fuelst;
	}

	public void setFuelst(String fuelst) {
		this.fuelst = fuelst;
	}

	public String getMilest() {
		return milest;
	}

	public void setMilest(String milest) {
		this.milest = milest;
	}

	public String getMaf() {
		return maf;
	}

	public void setMaf(String maf) {
		this.maf = maf;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getAvgspd() {
		return avgspd;
	}

	public void setAvgspd(String avgspd) {
		this.avgspd = avgspd;
	}

	public String getMaxrpm() {
		return maxrpm;
	}

	public void setMaxrpm(String maxrpm) {
		this.maxrpm = maxrpm;
	}

	public String getMinrpm() {
		return minrpm;
	}

	public void setMinrpm(String minrpm) {
		this.minrpm = minrpm;
	}

	public String getMaxacl() {
		return maxacl;
	}

	public void setMaxacl(String maxacl) {
		this.maxacl = maxacl;
	}

	public String getMaxspd() {
		return maxspd;
	}

	public void setMaxspd(String maxspd) {
		this.maxspd = maxspd;
	}

	public String getStarts() {
		return starts;
	}

	public void setStarts(String starts) {
		this.starts = starts;
	}

	public String getRacls() {
		return racls;
	}

	public void setRacls(String racls) {
		this.racls = racls;
	}

	public String getFuels() {
		return fuels;
	}

	public void setFuels(String fuels) {
		this.fuels = fuels;
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

	public String getMilet() {
		return milet;
	}

	public void setMilet(String milet) {
		this.milet = milet;
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


	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public String getTripid() {
		return tripid;
	}

	public void setTripid(String tripid) {
		this.tripid = tripid;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDebuginfo() {
		return debuginfo;
	}

	public void setDebuginfo(String debuginfo) {
		this.debuginfo = debuginfo;
	}

	
	
}
