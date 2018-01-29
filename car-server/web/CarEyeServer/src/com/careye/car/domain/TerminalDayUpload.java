/**
* Description: car-eye车辆管理平台
* 文件名：TerminalDayUpload.java
* 版本信息：1.0
* 日期：2015-5-5
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.domain;

/**
 * @项目名称：TAXISERVER
 * @类名称：TerminalDayUpload
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2015-5-5 下午07:35:52
 * @修改人：wuyongde
 * @修改时间：2015-5-5 下午07:35:52
 * @修改备注：
 * @version 1.0
 */
public class TerminalDayUpload {
	
	private Integer id;
	
	private Integer carid;
	
	private String terminal;
	
	private String carnumber;
	
	private String createtime;

	/**功能类型1 考拉FM*/
	private Integer type;
	
	/**使用时长 秒*/
	private Integer usinglen;
	
	/**使用流量 Kb / 默认为0*/
	private String flow;
	
	/**使用次数*/
	private String number;
	
	/**统计时间 YY-MM-DD-hh-mm-ss*/
	private String time;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUsinglen() {
		return usinglen;
	}

	public void setUsinglen(Integer usinglen) {
		this.usinglen = usinglen;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
