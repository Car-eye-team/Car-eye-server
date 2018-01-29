/**
* Description: car-eye车辆管理平台
* 文件名：AlarmType.java
* 版本信息：1.0
* 日期：2014-6-10
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：FMS
 * @类名称：AlarmType
 * @类描述：报警类型
 * @创建人：zr
 * @创建时间：2014-6-10 下午04:48:47
 * @修改人：zr
 * @修改时间：2014-6-10 下午04:48:47
 * @修改备注：
 * @version 1.0
 */
public class AlarmType {

	/**报警key*/
	private String alarmkey;
	
	/**报警值*/
	private Integer alarmvalue;
	
	/**报警名称*/
	private String alarmname;

	public String getAlarmkey() {
		return alarmkey;
	}

	public void setAlarmkey(String alarmkey) {
		this.alarmkey = alarmkey;
	}

	public Integer getAlarmvalue() {
		return alarmvalue;
	}

	public void setAlarmvalue(Integer alarmvalue) {
		this.alarmvalue = alarmvalue;
	}

	public String getAlarmname() {
		return alarmname;
	}

	public void setAlarmname(String alarmname) {
		this.alarmname = alarmname;
	}
	
	
	
}
