/**
* Description: 多森商用车平台
* 文件名：Affiche.java
* 版本信息：1.0
* 日期：2014-6-25
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.domain;

/**
 * @项目名称：FMS
 * @类名称：Affiche
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-6-25 上午11:39:23
 * @修改人：zr
 * @修改时间：2014-6-25 上午11:39:23
 * @修改备注：
 * @version 1.0
 */
public class Affiche {
	
	private Integer id;
	
	/**组织机构*/
	private Integer deptid;
	
	/**用户id*/
	private Integer userid;
	
	/** 用户登录名*/
	private String loginname;
	
	private String username;
	
	/**组织机构名称*/
	private String deptname;
	
	/**公告内容*/
	private String content;
	
	/**创建时间*/
	private String createtime;
	
	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;

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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
