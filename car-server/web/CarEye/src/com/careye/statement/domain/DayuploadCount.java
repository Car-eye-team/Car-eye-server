/**
 * Description: car-eye车辆管理平台
 * 文件名：DayuploadCount.java
 * 版本信息：1.0
 * 日期：2015-5-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.statement.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：DayuploadCount
 * @类描述：
 * @创建人：Yuqk 
 * @创建时间：2015-5-5 下午05:27:40
 * @修改人：Yuqk
 * @修改时间：2015-5-5 下午05:27:40
 * @修改备注：
 * @version 1.0
 */
public class DayuploadCount extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 2667756388110769858L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 车辆id
	 */
	private Integer carid;
	/**
	 * 终端号
	 */
	private String terminal;
	/**
	 * 车牌号
	 */
	private String carnumber;
	/**
	 * 统计时间
	 */
	private String time;
	/**
	 * 功能类型
	 */
	private Integer type;
	/**
	 * 使用次数
	 */
	private Integer numbercount;
	/**
	 * 使用时长
	 */
	private Integer usinglen;
	/**
	 * 使用流量
	 */
	private String flow;
	/**
	 * 创建类型
	 */
	private String createtime;
	/**
	 * 集团名称
	 */
	private String blocname;
	/*getter setter
	 * 
	 */
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNumbercount() {
		return numbercount;
	}
	public void setNumbercount(Integer numbercount) {
		this.numbercount = numbercount;
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	
}
