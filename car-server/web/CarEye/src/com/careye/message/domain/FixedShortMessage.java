/**
* Description: 多森商用车平台
* 文件名：FixedShortMessage.java
* 版本信息：1.0
* 日期：2014-7-17
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.domain;

/**
 * @项目名称：FMSSERVER
 * @类名称：FixedShortMessage
 * @类描述：
 * @创建人：lenovo
 * @创建时间：2014-7-17 下午02:05:12
 * @修改人：lenovo
 * @修改时间：2014-7-17 下午02:05:12
 * @修改备注：
 * @version 1.0
 */
public class FixedShortMessage {
	
	
	private Integer id;
	
	/**组织机构*/
	private Integer deptid;
	
	/**结构名称*/
	private String deptname;
	
	/**用户ID*/
	private Integer userid;
	
	/**车牌号码*/
	private String carnumber;
	
	/**车辆ID*/
	private Integer carid;
	
	/**短消息内容*/
	private String content;
	
	/**创建时间*/
	private String createtime;
	
	private String stime;
	
	private String etime;
	

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

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
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

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
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
	
	
	
}
