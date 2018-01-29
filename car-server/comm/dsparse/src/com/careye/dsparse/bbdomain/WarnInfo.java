/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**
 * @项目名称：dsparse
 * @类名称：WarnInfo
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-7-8 上午10:23:32
 * @修改人：zhangrong
 * @修改时间：2015-7-8 上午10:23:32
 * @修改备注：
 * @version 1.0
 */
public class WarnInfo {

	/**正常阀值**/
	private String normal;
	/**正常阀值**/
	private String warn;
	/**正常阀值**/
	private String alarm;
	/**正常阀值**/
	private String temperature;
	
	
	public String getNormal() {
		return normal;
	}
	public void setNormal(String normal) {
		this.normal = normal;
	}
	public String getWarn() {
		return warn;
	}
	public void setWarn(String warn) {
		this.warn = warn;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	
	
}
