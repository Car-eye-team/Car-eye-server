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
 * 类名称：DfrcInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-3-23 下午06:13:12    
 * 修改人：Administrator    
 * 修改时间：2016-3-23 下午06:13:12    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DfrcInfo extends BaseInfo{
	
	/**定位状态*/
	private int gpsflag;
	
	/**纬度*/
	private String lat;
	
	/**经度*/
	private String lng;
	
	/**高度*/
	private String altitude;
	
	/**速度*/
	private String speed;
	
	/**方向*/
	private String direction;
	
	/**总里程*/
	private String miles;
	
	/**信息点名称*/
	private String poiname;
	
	/**普通心跳包时间间隔*/
	private int interval1;
	
	/**带位置信息的心跳包时间间隔*/
	private int interval2;
	
	/**版本号*/
	private String version;
	
	/**IP地址*/
	private String ip;
	
	/**端口*/
	private String port;
	
	/**APN*/
	private String apn;
	
	/**时间*/
	private String time;
	
	/**扩展字段*/
	private String extendcontent;
	

	public String getExtendcontent() {
		return extendcontent;
	}

	public void setExtendcontent(String extendcontent) {
		this.extendcontent = extendcontent;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
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

	public String getMiles() {
		return miles;
	}

	public void setMiles(String miles) {
		this.miles = miles;
	}

	public String getPoiname() {
		return poiname;
	}

	public void setPoiname(String poiname) {
		this.poiname = poiname;
	}

	public int getInterval1() {
		return interval1;
	}

	public void setInterval1(int interval1) {
		this.interval1 = interval1;
	}

	public int getInterval2() {
		return interval2;
	}

	public void setInterval2(int interval2) {
		this.interval2 = interval2;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
	
	
}
