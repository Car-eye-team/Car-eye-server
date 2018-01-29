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
 * 类名称：LdCycInfo    
 * 类描述：蓝度乘用车实体    
 * 创建人：zr    
 * 创建时间：2015-7-27 下午05:41:38    
 * 修改人：zr    
 * 修改时间：2015-7-27 下午05:41:38    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class LdCycInfo extends BaseInfo{
	
	private Integer tripid;
	
	private String vid;
	
	private String vin;
	
	/**取得检测数据时间戮*/
	private String timestamp;
	
	/**报警类型*/
	private Integer alarmtype;
	
	/**纬度*/
	private int lat;
	
	/**经度*/
	private int lng;
	
	/**速度*/
	private String speed;
	
	/**当前行程行驶距离*/
	private String distance;
	
	private String drivermile;
	
	/**方向*/
	private int direction;
	
	/**定位时间*/
	private String gpstime;
	
	/**定位方式 1 基站定位 2 GPS定位*/
	private Integer method;
	
	/**故障个数*/
	private Integer faultnumber;

	
	/**实际水温数值*/
	private String temp;
	
	/**充电电压值*/
	private String voltage;
	
	/**设备拔下时间戮*/
	private String pulltime; 
	
	/**数据属性标识*/
	private Integer attrlabel;
	
	/**参数数量**/
	private Integer count;
	
	/**运行时长*/
	private Integer runtime;
	
	/**平均油耗*/
	private String oilwear;
	
	/**累计行驶里程*/
	private Integer summileage;
	
	/**累计平均油耗*/
	private String sumoilwear;
	
	/**本次急加速次数*/
	private Integer accelerationtimes;
	
	/**本次急减速次数*/
	private Integer decelerationtimes;
	
	/**本次急转向次数*/
	private Integer steeringtimes;
	
	/**本次超速行驶时间*/
	private Integer longtime;
	
	/**最高车速*/
	private Integer maxspeed;
	
	/**硬件版本号*/
	private String hversion;
	
	/**固件版本号*/
	private String fversion;
	
	/**软件版本号*/
	private String sversion;
	
	/**诊断程序类型*/
	private Integer type;
	
	/**恢复出厂设置序号*/
	private Integer serialnumber;
	
	/**数据内容*/
	private String content;
	
	/**严重等级*/
	private Integer level;
	
	/**错误代码*/
	private Integer errorcode;
	
	/**软件类别ID*/
	private Integer categoryid;
	
	/**动作参数数量*/
	private int actionnumber;
	
	/**恢复出厂设置序号*/
	private int restorefactory;
	
	/**是否执行清码动作*/
	private int clearcode;
	
	/**车辆信息参数数量*/
	private int vehiclenumber;
	
	/**品牌*/
	private int brand;
	
	/**系列*/
	private int series;
	
	/**年款*/
	private int modelyear;
	
	/**排量*/
	private String pl;
	
	/**网络配置数量*/
	private int nknumber;
	
	/**取参数网络配置*/
	private String ip1;
	private int port1;
	
	/**主动上传网络配置*/
	private String ip2;
	private int port2;
	
	/**报警数据网络配置*/
	private String ip3;
	private int port3;
	
	/**短信回复网络配置*/
	private String ip4;
	private int port4;
	
	/**定位信息网络配置*/
	private String ip5;
	private int port5;
	
	/**分段数量*/
	private int snumber;
	
	/**最高车速数组*/
	private List<LdCycInfoItems> speeditems = new ArrayList<LdCycInfoItems>();

	
	/**定位参数设置参数数量*/
	private int lnumber;
	
	/**定位间隔时间*/
	private int interval;
	
	/**距离与时间关系*/
	private int relation;
	
	/**报警设置参数数量*/
	private int anumber;

	/**超速最小车速*/
	private int aparam1;
	
	/**超速报警的最小持续时间*/
	private int aparam2;
	
	/**报警水温值*/
	private int aparam3;
	
	/**充电电压报警值*/
	private int aparam4;
	
	/**熄火后数据数量*/
	private int fnumber;
	
	/**熄火后关闭时间点*/
	private int fparam1;
	
	/**关机临界电压值*/
	private int fparam2;
	
	/**熄火后电压数据总数*/
	private int fcount;
	
	/**熄火后电池电压阈值数组*/
	private List<LdCycInfoItems> voltageitems = new ArrayList<LdCycInfoItems>();
	
	/**数据ID 数量*/
	private int datacount;
	
	/**数据间隔时间*/
	private int datainterval;
	
	/**数据数组*/
	private List<LdCycInfoItems> dataitems = new ArrayList<LdCycInfoItems>();
	
	/**软件升级ID*/
	private String updateid;
	
	private List<LdCycInfoItems> items = new ArrayList<LdCycInfoItems>();
	
	private List<LdCycInfoItems> items1 = new ArrayList<LdCycInfoItems>();
	
	public Integer getTripid() {
		return tripid;
	}

	public void setTripid(Integer tripid) {
		this.tripid = tripid;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getAlarmtype() {
		return alarmtype;
	}

	public void setAlarmtype(Integer alarmtype) {
		this.alarmtype = alarmtype;
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


	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
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


	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public Integer getFaultnumber() {
		return faultnumber;
	}

	public void setFaultnumber(Integer faultnumber) {
		this.faultnumber = faultnumber;
	}

	
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getPulltime() {
		return pulltime;
	}

	public void setPulltime(String pulltime) {
		this.pulltime = pulltime;
	}

	public List<LdCycInfoItems> getItems() {
		return items;
	}

	public void setItems(List<LdCycInfoItems> items) {
		this.items = items;
	}

	public Integer getAttrlabel() {
		return attrlabel;
	}

	public void setAttrlabel(Integer attrlabel) {
		this.attrlabel = attrlabel;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	
	public String getOilwear() {
		return oilwear;
	}

	public void setOilwear(String oilwear) {
		this.oilwear = oilwear;
	}

	public String getSumoilwear() {
		return sumoilwear;
	}

	public void setSumoilwear(String sumoilwear) {
		this.sumoilwear = sumoilwear;
	}

	public Integer getSummileage() {
		return summileage;
	}

	public void setSummileage(Integer summileage) {
		this.summileage = summileage;
	}


	public Integer getAccelerationtimes() {
		return accelerationtimes;
	}

	public void setAccelerationtimes(Integer accelerationtimes) {
		this.accelerationtimes = accelerationtimes;
	}

	public Integer getDecelerationtimes() {
		return decelerationtimes;
	}

	public void setDecelerationtimes(Integer decelerationtimes) {
		this.decelerationtimes = decelerationtimes;
	}


	public Integer getSteeringtimes() {
		return steeringtimes;
	}

	public void setSteeringtimes(Integer steeringtimes) {
		this.steeringtimes = steeringtimes;
	}

	public Integer getLongtime() {
		return longtime;
	}

	public void setLongtime(Integer longtime) {
		this.longtime = longtime;
	}

	public Integer getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Integer maxspeed) {
		this.maxspeed = maxspeed;
	}

	public String getHversion() {
		return hversion;
	}

	public void setHversion(String hversion) {
		this.hversion = hversion;
	}

	public String getFversion() {
		return fversion;
	}

	public void setFversion(String fversion) {
		this.fversion = fversion;
	}

	public String getSversion() {
		return sversion;
	}

	public void setSversion(String sversion) {
		this.sversion = sversion;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<LdCycInfoItems> getItems1() {
		return items1;
	}

	public void setItems1(List<LdCycInfoItems> items1) {
		this.items1 = items1;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public int getActionnumber() {
		return actionnumber;
	}

	public void setActionnumber(int actionnumber) {
		this.actionnumber = actionnumber;
	}

	public int getRestorefactory() {
		return restorefactory;
	}

	public void setRestorefactory(int restorefactory) {
		this.restorefactory = restorefactory;
	}

	public int getClearcode() {
		return clearcode;
	}

	public void setClearcode(int clearcode) {
		this.clearcode = clearcode;
	}


	public int getVehiclenumber() {
		return vehiclenumber;
	}

	public void setVehiclenumber(int vehiclenumber) {
		this.vehiclenumber = vehiclenumber;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getModelyear() {
		return modelyear;
	}

	public void setModelyear(int modelyear) {
		this.modelyear = modelyear;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public int getNknumber() {
		return nknumber;
	}

	public void setNknumber(int nknumber) {
		this.nknumber = nknumber;
	}

	public String getIp1() {
		return ip1;
	}

	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}

	public int getPort1() {
		return port1;
	}

	public void setPort1(int port1) {
		this.port1 = port1;
	}

	public String getIp2() {
		return ip2;
	}

	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}

	public int getPort2() {
		return port2;
	}

	public void setPort2(int port2) {
		this.port2 = port2;
	}

	public String getIp3() {
		return ip3;
	}

	public void setIp3(String ip3) {
		this.ip3 = ip3;
	}

	public int getPort3() {
		return port3;
	}

	public void setPort3(int port3) {
		this.port3 = port3;
	}

	public String getIp4() {
		return ip4;
	}

	public void setIp4(String ip4) {
		this.ip4 = ip4;
	}

	public int getPort4() {
		return port4;
	}

	public void setPort4(int port4) {
		this.port4 = port4;
	}

	public String getIp5() {
		return ip5;
	}

	public void setIp5(String ip5) {
		this.ip5 = ip5;
	}

	public int getPort5() {
		return port5;
	}

	public void setPort5(int port5) {
		this.port5 = port5;
	}

	public int getSnumber() {
		return snumber;
	}

	public void setSnumber(int snumber) {
		this.snumber = snumber;
	}

	public List<LdCycInfoItems> getSpeeditems() {
		return speeditems;
	}

	public void setSpeeditems(List<LdCycInfoItems> speeditems) {
		this.speeditems = speeditems;
	}

	public int getLnumber() {
		return lnumber;
	}

	public void setLnumber(int lnumber) {
		this.lnumber = lnumber;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}

	public int getAnumber() {
		return anumber;
	}

	public void setAnumber(int anumber) {
		this.anumber = anumber;
	}

	public int getAparam1() {
		return aparam1;
	}

	public void setAparam1(int aparam1) {
		this.aparam1 = aparam1;
	}

	public int getAparam2() {
		return aparam2;
	}

	public void setAparam2(int aparam2) {
		this.aparam2 = aparam2;
	}

	public int getAparam3() {
		return aparam3;
	}

	public void setAparam3(int aparam3) {
		this.aparam3 = aparam3;
	}

	public int getAparam4() {
		return aparam4;
	}

	public void setAparam4(int aparam4) {
		this.aparam4 = aparam4;
	}

	public int getFnumber() {
		return fnumber;
	}

	public void setFnumber(int fnumber) {
		this.fnumber = fnumber;
	}

	public int getFparam1() {
		return fparam1;
	}

	public void setFparam1(int fparam1) {
		this.fparam1 = fparam1;
	}

	public int getFparam2() {
		return fparam2;
	}

	public void setFparam2(int fparam2) {
		this.fparam2 = fparam2;
	}

	public int getFcount() {
		return fcount;
	}

	public void setFcount(int fcount) {
		this.fcount = fcount;
	}

	public List<LdCycInfoItems> getVoltageitems() {
		return voltageitems;
	}

	public void setVoltageitems(List<LdCycInfoItems> voltageitems) {
		this.voltageitems = voltageitems;
	}

	public int getDatacount() {
		return datacount;
	}

	public void setDatacount(int datacount) {
		this.datacount = datacount;
	}

	public int getDatainterval() {
		return datainterval;
	}

	public void setDatainterval(int datainterval) {
		this.datainterval = datainterval;
	}

	public List<LdCycInfoItems> getDataitems() {
		return dataitems;
	}

	public void setDataitems(List<LdCycInfoItems> dataitems) {
		this.dataitems = dataitems;
	}

	public String getUpdateid() {
		return updateid;
	}

	public void setUpdateid(String updateid) {
		this.updateid = updateid;
	}

	public String getDrivermile() {
		return drivermile;
	}

	public void setDrivermile(String drivermile) {
		this.drivermile = drivermile;
	}

	
}
