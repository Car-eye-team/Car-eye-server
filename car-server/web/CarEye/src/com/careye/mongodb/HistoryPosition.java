/**
* Description: car-eye车辆管理平台
* 文件名：CarPosition.java
* 版本信息：1.0
* 日期：2015-6-10
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mongodb;

/**
 * @项目名称：car-eye
 * @类名称：CarPosition
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-6-10 下午04:39:54
 * @修改人：zhangrong
 * @修改时间：2015-6-10 下午04:39:54
 * @修改备注：
 * @version 1.0
 */
public class HistoryPosition {

	private Object _id;
	private String carnumber;
	private Integer carstatus;
	private String terminal;
	private Double glng;
	private Double glat;
	private String gpstime;
	private String gaddress;
	
	public Object get_id() {
		return _id;
	}
	public void set_id(Object id) {
		_id = id;
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
	public Integer getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}
	public Double getGlng() {
		return glng;
	}
	public void setGlng(Double glng) {
		this.glng = glng;
	}
	public Double getGlat() {
		return glat;
	}
	public void setGlat(Double glat) {
		this.glat = glat;
	}
	public String getGpstime() {
		return gpstime;
	}
	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}
	public String getGaddress() {
		return gaddress;
	}
	public void setGaddress(String gaddress) {
		this.gaddress = gaddress;
	}
	
	
}
