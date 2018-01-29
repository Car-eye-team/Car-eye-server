/**
* Description: car-eye车辆管理平台
* 文件名：MemberLoginLog.java
* 版本信息：1.0
* 日期：2013-9-28
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：SysAuthLoginLog
 * @类描述：用户登陆日志表
 * @创建人：huangqin
 * @创建时间：2015-3-5 上午10:25:37
 * @修改人：huangqin
 * @修改时间：2015-3-5 上午10:25:37
 * @修改备注：
 * @version 1.0
 */
public class SysAuthLoginLog extends BaseDomain implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**集团 外键（集团表ID）*/
	private Integer blocid;
	private String blocname;
	
	/**用户ID*/
	private Integer userid;
	private String loginname;
	private String username;
	
	/**时间*/
	private String logindate;
	/**登陆IP*/
	private String loginip;
	/**登陆状态  是否成功,SUCCESS,FAILED*/
	private String loginflag;
	/**其他信息*/
	private String remark;
	/**状态  1登录 2退出*/
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public String getLoginflag() {
		return loginflag;
	}
	public void setLoginflag(String loginflag) {
		this.loginflag = loginflag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
