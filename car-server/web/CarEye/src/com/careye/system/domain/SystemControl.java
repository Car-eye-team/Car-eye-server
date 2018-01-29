/**
* Description: car-eye车辆管理平台
* 文件名：SystemControl.java
* 版本信息：1.0
* 日期：2013-11-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.domain;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：SystemControl
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-5-19 下午05:43:57
 * @修改人：zhangrong
 * @修改时间：2014-5-19 下午05:43:57
 * @修改备注：
 * @version 1.0
 */
public class SystemControl extends BaseDomain{

	/**ID*/
	private Integer id;
	
	/**控制项名称*/
	private String scname;
	
	/**系统控制别名*/
	private String scalias;
	
	/**系统控制说明*/
	private String scdesc;
	
	/**控制状态*/
	private Integer scstatus;
	
	/**控制值*/
	private String scnum;
	
	/**操作时间*/
	private String operationtime;
	
	/**提醒间隔*/
	private Integer remindspace;
	
	/**提醒间隔单位   1小时 2天 3 公里 4元 5升*/
	private Integer unit;
	
	/**提前提醒单位   1小时 2天 3 公里 4元 5升*/
	private Integer controlunit;
	
	/**提醒次数*/
	private Integer remindcount;
	
	
	
	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getRemindspace() {
		return remindspace;
	}

	public void setRemindspace(Integer remindspace) {
		this.remindspace = remindspace;
	}

	public Integer getRemindcount() {
		return remindcount;
	}

	public void setRemindcount(Integer remindcount) {
		this.remindcount = remindcount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScname() {
		return scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}

	public String getScalias() {
		return scalias;
	}

	public void setScalias(String scalias) {
		this.scalias = scalias;
	}

	public String getScdesc() {
		return scdesc;
	}

	public void setScdesc(String scdesc) {
		this.scdesc = scdesc;
	}

	public Integer getScstatus() {
		return scstatus;
	}

	public void setScstatus(Integer scstatus) {
		this.scstatus = scstatus;
	}

	public String getScnum() {
		return scnum;
	}

	public void setScnum(String scnum) {
		this.scnum = scnum;
	}

	public String getOperationtime() {
		return operationtime;
	}

	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}

	public Integer getControlunit() {
		return controlunit;
	}

	public void setControlunit(Integer controlunit) {
		this.controlunit = controlunit;
	}

	
}
