/**
* Description: 多森商用车平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.domain;

import java.io.Serializable;

/**
 * @项目名称：FMS
 * @类名称：EventSendRecord
 * @类描述：3.3.5	事件发送记录表
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class EventSendRecord implements Serializable{
	
	private Integer id;
	
	/**组织机构*/
	private Integer blocid;
	
	private String blocname;
	private String username;
	
	/**用户ID*/
	private Integer userid;
	
	/**事件内容*/
	private String content;
	
	/**车牌号*/
	private String carnumber;
	
	/**创建时间*/
	private String createtime;
	
	/**事件ID   系统事件表*/
	private Integer eventid;
	
	/**操作类型	1追加2更新 3 删除 4 删除所有事件*/
	private Integer opertype;
	
	/**流水号*/
	private Integer seq;
	
	/**处理结果  1成功 2失败*/
	private Integer result;
	
	/**原始数据包*/
	private String data;

	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	/**事件报告 1 未报告 2 报告 **/
	private Integer report;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getOpertype() {
		return opertype;
	}

	public void setOpertype(Integer opertype) {
		this.opertype = opertype;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getReport() {
		return report;
	}

	public void setReport(Integer report) {
		this.report = report;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

}
