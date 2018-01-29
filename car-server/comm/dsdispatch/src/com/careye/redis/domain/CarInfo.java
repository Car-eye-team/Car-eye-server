/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.redis.domain;

import java.io.Serializable;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：CarInfo    
 * 类描述：车辆信息实体    
 * 创建人：zr    
 * 创建时间：2015-5-19 下午08:12:55    
 * 修改人：zr    
 * 修改时间：2015-5-19 下午08:12:55    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CarInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**车辆ID*/
	private int carid;
	
	/**车牌号*/
	private String carnumber;
	
	/**设备号*/
	private String terminal;
	
	/**经度*/
	private double lng;
	
	/**纬度*/
	private double lat;
	
	/**空重状态 0 空车 1 重车*/
	private int kzstate;
	
	/**终端设备类型*/
	private int devicetype;
	
	/**距离*/
	private Integer distance;
	
	/**添加时间*/
	private String addtime;
	
	
	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public int getKzstate() {
		return kzstate;
	}

	public void setKzstate(int kzstate) {
		this.kzstate = kzstate;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	
	
}
