/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：LdCycInfoItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-7-28 下午03:27:44    
 * 修改人：zr    
 * 修改时间：2015-7-28 下午03:27:44    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class LdCycInfoItems {
	
	/**故障码*/
	private String faultcode;
	
	/**故障属性*/
	private String faultattr;
	
	/**故障描述*/
	private String faultdesc;
	
	/**数据ID*/
	private Integer id;
	
	/**ID 数据内容*/
	private String value;
	
	/**设置速度值*/
	private Integer speedvalue;
	
	/**时间小计*/
	private Integer sumtime;
	
	/**距离小计*/
	private String sumdistance;
	
	/**纬度*/
	private Integer lat;
	
	/**经度*/
	private Integer lng;
	
	/**速度*/
	private String speed;
	
	/**当前行程行驶距离*/
	private String distance;
	
	/**方向*/
	private Integer direction;
	
	/**定位时间*/
	private String gpstime;
	
	/**定位方式 1 基站定位 2 GPS定位*/
	private Integer method;
	
	private Integer voltage;
	
	/**最高车速*/
	private Integer maxspeed;

	public String getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}

	public String getFaultattr() {
		return faultattr;
	}

	public void setFaultattr(String faultattr) {
		this.faultattr = faultattr;
	}

	public String getFaultdesc() {
		return faultdesc;
	}

	public void setFaultdesc(String faultdesc) {
		this.faultdesc = faultdesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSpeedvalue() {
		return speedvalue;
	}

	public void setSpeedvalue(Integer speedvalue) {
		this.speedvalue = speedvalue;
	}

	public Integer getSumtime() {
		return sumtime;
	}

	public void setSumtime(Integer sumtime) {
		this.sumtime = sumtime;
	}


	public String getSumdistance() {
		return sumdistance;
	}

	public void setSumdistance(String sumdistance) {
		this.sumdistance = sumdistance;
	}

	public Integer getLat() {
		return lat;
	}

	public void setLat(Integer lat) {
		this.lat = lat;
	}

	public Integer getLng() {
		return lng;
	}

	public void setLng(Integer lng) {
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

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
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

	public Integer getVoltage() {
		return voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}

	public Integer getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Integer maxspeed) {
		this.maxspeed = maxspeed;
	}
	
}
