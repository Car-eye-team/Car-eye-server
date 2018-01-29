/**
* Description: car-eye车辆管理平台
* 文件名：BaseDomain.java
* 版本信息：1.0
* 日期：2014-3-1
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.base.action;


/**
 * @项目名称：car-eyeTms
 * @类名称：TreeDomain
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-3-1 下午04:39:33
 * @修改人：zr
 * @修改时间：2014-3-1 下午04:39:33
 * @修改备注：
 * @version 1.0
 */
public class TreeDomain {

	/**用户ID*/
	private Integer userid;
	private Integer assignuserid;
	
	/**用户名称*/
	private String username;
	
	/**组织机构ID*/
	private Integer blocid;
	
	/**查询组织机构ID*/
	private Integer querydeptid;
	
	/**组织机构名称*/
	private String blocname;
	
	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	/**车牌号*/
	private String carnumber;
	
	/**设备类型**/
	private String devicetype;
	
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

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



	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getQuerydeptid() {
		return querydeptid;
	}

	public void setQuerydeptid(Integer querydeptid) {
		this.querydeptid = querydeptid;
	}


	public Integer getAssignuserid() {
		return assignuserid;
	}

	public void setAssignuserid(Integer assignuserid) {
		this.assignuserid = assignuserid;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
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
