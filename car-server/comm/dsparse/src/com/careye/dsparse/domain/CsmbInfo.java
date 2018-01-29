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
 * 类名称：CsmbInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-3-3 下午08:07:43    
 * 修改人：Administrator    
 * 修改时间：2016-3-3 下午08:07:43    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CsmbInfo extends BaseInfo{
	
	/**设备类型*/
	private String devicetype;
	
	/**电池电量*/
	private int kwh;
	
	/**GSM 信号*/
	private int gsm;
	
	/**定位状态*/
	private int gpsflag;
	
	/**动作状态*/
	private int acton;
	
	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;

	/**速度*/
	private String speed;

	/**方向*/
	private String direction;

	/**高度*/
	private String altitude;
	
	/**卫星数*/
	private int number;
	
	/**时间*/
	private String time;
	
	/**时间间隔*/
	private int interval;
	
	/**标志*/
	private int flag;
	
	/**总数*/
	private int total;
	
	/**号码*/
	private String phone;
	
	/**E/W*/
	private int ew;
	
	/**分钟*/
	private int min;
	
	/**数组*/
	private List<CsmbInfoItems> items = new ArrayList<CsmbInfoItems>();
	
	
	public int getEw() {
		return ew;
	}

	public void setEw(int ew) {
		this.ew = ew;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public int getKwh() {
		return kwh;
	}

	public void setKwh(int kwh) {
		this.kwh = kwh;
	}

	public int getGsm() {
		return gsm;
	}

	public void setGsm(int gsm) {
		this.gsm = gsm;
	}

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}

	public int getActon() {
		return acton;
	}

	public void setActon(int acton) {
		this.acton = acton;
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


	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<CsmbInfoItems> getItems() {
		return items;
	}

	public void setItems(List<CsmbInfoItems> items) {
		this.items = items;
	}
	
	
}
