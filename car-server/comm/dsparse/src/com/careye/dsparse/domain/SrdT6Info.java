/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：SrdT6Info    
 * 类描述：深圳速锐得科技有限公司T6产品协议实体    
 * 创建人：Administrator    
 * 创建时间：2015-12-22 上午11:15:16    
 * 修改人：Administrator    
 * 修改时间：2015-12-22 上午11:15:16    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SrdT6Info extends BaseInfo{
	
	/**类型*/
	private int type;
	
	/**消息*/
	private String msg;
	
	/**时间*/
	private String time;
	
	/**电瓶电压*/
	private String vbat;
	
	/**发动机转速*/
	private int rpm;
	
	/**行驶车速*/
	private int spd;
	
	/**节气门开度*/
	private String tpos;
	
	/**发动机负荷*/
	private String cvol;
	
	/**冷却液温度*/
	private int etc;
	
	/**瞬时油耗*/
	private String fli;
	
	/**平均油耗*/
	private String oilwear;
	
	/**本次行驶里程*/
	private String drivermile;
	
	/**总里程*/
	private String mileage;
	
	/**本次耗油量*/
	private String fuelt;
	
	/**累计耗油量*/
	private String sumfuelt;
	
	/**故障码数量*/
	private int faultcode;
	
	/**本次急加速*/
	private int racls;
	
	/**本次急减速*/
	private int brakes;
	
	/**本次急转弯*/
	private int tst;
	
	/**总点火次数*/
	private int starts;
	
	/**累计行驶时间*/
	private String runtime;
	
	/**累计怠速时间*/
	private String idletime;
	
	/**平均热车时间*/
	private int hottime;
	
	/**平均车速*/
	private int avgspd;
	
	/**最高车速*/
	private int maxspd;
	
	/**最高转速*/
	private int maxrpm;
	
	/**累计急加速*/
	private int sumracls;
	
	/**累计急减速*/
	private int sumbrakes;
	
	/**累计急转弯*/
	private int sumtst;
	
	/**油箱剩余油量*/
	private String remainl;
	
	/**位置数组*/
	private List<SrdT6InfoItems> items = new ArrayList<SrdT6InfoItems>();
	
	/**启动时间*/
	private String starttime;
	
	/**熄火时间*/
	private String endtime;
	
	/**定位类型*/
	private int gpsflag;
	
	/**经度*/
	private String lng = "0";
	
	/**纬度*/
	private String lat = "0";
	
	/**休眠时间*/
	private String sleeptime;
	
	/**行驶里程*/
	private String runmileage;
	
	/**怠速油耗*/
	private String idlefuelt;
	
	/**行驶油耗*/
	private String runfuelt;
	
	/**网络信号值*/
	private int signal;
	
	/**设备序列号*/
	private String serialnumber;
	
	/**软件版本号*/
	private String sversion;
	
	/**硬件版本号*/
	private String fversion;
	
	/**应答结果*/
	private String result;
	
	/**间隔*/
	private int interval;
	
	/**FTP 账号用户名*/
	private String username;
	
	/**FTP 账号密码*/
	private String password;
	
	/**升级程序文件名*/
	private String filename;
	
	/**文件所在路径*/
	private String filepath;
	
	/**协议类型*/
	private String protocol;
	
	/**车架号*/
	private String vin;
	
	/**数量*/
	private int count;
	
	/**故障码字符串*/
	private String faultcodestr;
	
	/**IMEI 号*/
	private String imei;
	
	/**CCID 号*/
	private String ccid;
	
	/**IMSI 号*/
	private String imsi;
	
	/**IP地址*/
	private String ip;
	
	/**域名*/
	private String domain;
	
	/**端口*/
	private String port;	
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCcid() {
		return ccid;
	}

	public void setCcid(String ccid) {
		this.ccid = ccid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFaultcodestr() {
		return faultcodestr;
	}

	public void setFaultcodestr(String faultcodestr) {
		this.faultcodestr = faultcodestr;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getSversion() {
		return sversion;
	}

	public void setSversion(String sversion) {
		this.sversion = sversion;
	}

	public String getFversion() {
		return fversion;
	}

	public void setFversion(String fversion) {
		this.fversion = fversion;
	}

	public String getRunmileage() {
		return runmileage;
	}

	public void setRunmileage(String runmileage) {
		this.runmileage = runmileage;
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

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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


	public String getTpos() {
		return tpos;
	}

	public void setTpos(String tpos) {
		this.tpos = tpos;
	}


	public String getCvol() {
		return cvol;
	}

	public void setCvol(String cvol) {
		this.cvol = cvol;
	}

	public int getEtc() {
		return etc;
	}

	public void setEtc(int etc) {
		this.etc = etc;
	}

	public String getFli() {
		return fli;
	}

	public void setFli(String fli) {
		this.fli = fli;
	}

	public String getOilwear() {
		return oilwear;
	}

	public void setOilwear(String oilwear) {
		this.oilwear = oilwear;
	}

	public String getDrivermile() {
		return drivermile;
	}

	public void setDrivermile(String drivermile) {
		this.drivermile = drivermile;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getFuelt() {
		return fuelt;
	}

	public void setFuelt(String fuelt) {
		this.fuelt = fuelt;
	}

	public String getSumfuelt() {
		return sumfuelt;
	}

	public void setSumfuelt(String sumfuelt) {
		this.sumfuelt = sumfuelt;
	}

	public int getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(int faultcode) {
		this.faultcode = faultcode;
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

	public int getStarts() {
		return starts;
	}

	public void setStarts(int starts) {
		this.starts = starts;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getIdletime() {
		return idletime;
	}

	public void setIdletime(String idletime) {
		this.idletime = idletime;
	}

	public int getHottime() {
		return hottime;
	}

	public void setHottime(int hottime) {
		this.hottime = hottime;
	}

	public int getAvgspd() {
		return avgspd;
	}

	public void setAvgspd(int avgspd) {
		this.avgspd = avgspd;
	}

	public int getMaxspd() {
		return maxspd;
	}

	public void setMaxspd(int maxspd) {
		this.maxspd = maxspd;
	}

	public int getMaxrpm() {
		return maxrpm;
	}

	public void setMaxrpm(int maxrpm) {
		this.maxrpm = maxrpm;
	}

	public int getSumracls() {
		return sumracls;
	}

	public void setSumracls(int sumracls) {
		this.sumracls = sumracls;
	}

	public int getSumbrakes() {
		return sumbrakes;
	}

	public void setSumbrakes(int sumbrakes) {
		this.sumbrakes = sumbrakes;
	}

	public int getSumtst() {
		return sumtst;
	}

	public void setSumtst(int sumtst) {
		this.sumtst = sumtst;
	}


	public String getRemainl() {
		return remainl;
	}

	public void setRemainl(String remainl) {
		this.remainl = remainl;
	}

	public List<SrdT6InfoItems> getItems() {
		return items;
	}

	public void setItems(List<SrdT6InfoItems> items) {
		this.items = items;
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

	public String getSleeptime() {
		return sleeptime;
	}

	public void setSleeptime(String sleeptime) {
		this.sleeptime = sleeptime;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
