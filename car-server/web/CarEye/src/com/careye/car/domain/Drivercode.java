/**
* Description: car-eye车辆管理平台
* 文件名：DeviceType.java
* 版本信息：1.0
* 日期：2014-7-8
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

/**
 * @项目名称：FMS
 * @类名称：DeviceType
 * @类描述：设备类型
 * @创建人：lenovo
 * @创建时间：2014-7-8 下午01:32:44
 * @修改人：lenovo
 * @修改时间：2014-7-8 下午01:32:44
 * @修改备注：
 * @version 1.0
 */
public class Drivercode {
	
	
	private Integer carid;
	private String carnumber;
	
	/**司机名称**/
	private String drivername;
	
	/**司机代码**/
	private String drivercode;

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	
	
	
}
