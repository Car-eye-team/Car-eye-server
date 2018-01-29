/**
* Description: car-eye车辆管理平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;

/**
 * @项目名称：FMS
 * @类名称：CarDriver
 * @类描述：3.2.7	车辆控制记录表
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class ControlRecord implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	/**组织机构*/
	private Integer blocid;
	/**组织机构*/
	private String blocname;
	
	/**用户ID*/
	private Integer userid;
	/**用户*/
	private String username;
	
	/**车牌号*/
	private String carnumber;
	
	/**流水号*/
	private Integer seq;
	
	/**控制类型	1 加锁 2解锁*/
	private Integer controltype;
	
	/**处理结果	1成功 2失败*/
	private Integer result;
	
	/**创建时间*/
	private String createtime;
	
	/**原始数据包*/
	private String data;

	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	
	/**
	 * 控制内容CONTROLTEXT
	 * @return
	 */
	private String controltext;
	
	
	public String getControltext() {
		return controltext;
	}

	public void setControltext(String controltext) {
		this.controltext = controltext;
	}

	public String getStime() {
		return stime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}


	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getControltype() {
		return controltype;
	}

	public void setControltype(Integer controltype) {
		this.controltype = controltype;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
