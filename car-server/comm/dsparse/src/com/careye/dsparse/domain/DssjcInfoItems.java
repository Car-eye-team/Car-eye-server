/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：DssjcInfoItems    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-7-9 上午11:59:46    
 * 修改人：Administrator    
 * 修改时间：2016-7-9 上午11:59:46    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DssjcInfoItems {
	
	private Integer id;
	
	private String name;
	
	private String price;
	
	private Integer gpsflag;

	/**纬度*/
	private Integer lat;

	/**经度*/
	private Integer lng;

	/**速度*/
	private String speed;

	/**方向*/
	private Integer direction;

	/**高度*/
	private Integer altitude;
	
	/**总里程*/
	private String miles;
	

	/**时间*/
	private String time;
	
	/**故障码*/
	private String faultcode;
	
	/**故障描述*/
	private String faultdesc;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(Integer gpsflag) {
		this.gpsflag = gpsflag;
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

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	public String getMiles() {
		return miles;
	}

	public void setMiles(String miles) {
		this.miles = miles;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}

	public String getFaultdesc() {
		return faultdesc;
	}

	public void setFaultdesc(String faultdesc) {
		this.faultdesc = faultdesc;
	}
	
	
}
