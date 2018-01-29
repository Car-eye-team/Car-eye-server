/**
* Description: car-eye车辆管理平台
* 文件名：BaseDomain.java
* 版本信息：1.0
* 日期：2014-3-1
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.base.action;

/**
 * @car-eye车辆管理平台业务处理Tms
 * @类名称：BaseDomain
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-3-1 下午04:39:33
 * @修改人：zr
 * @修改时间：2014-3-1 下午04:39:33
 * @修改备注：
 * @version 1.0
 */
public class BaseDomain {

	/**用户ID*/
	private Integer userid;
	
	/**用户名称*/
	private String username;
	
	/**组织机构ID*/
	private Integer deptid;
	
	/**组织机构名称*/
	private String deptname;
	
	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;

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
	
	
	
}
