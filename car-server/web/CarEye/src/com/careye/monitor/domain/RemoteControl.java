/**
* Description: car-eye车辆管理平台
* 文件名：DrivingState.java
* 版本信息：1.0
* 日期：2015-3-23
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.monitor.domain;

/**
 * @项目名称：car-eye
 * @类名称：RemoteControl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-23 下午08:18:50
 * @修改人：Yuqk
 * @修改时间：2015-3-23 下午08:18:50
 * @修改备注：
 * @version 1.0
 */
public class RemoteControl {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 车牌号
	 */
	private String carnumber;
	/**
	 * 指令类型
	 */
	private String type;
	/**
	 * 设置状态
	 */
	private String status;
	/**
	 * 开始时间
	 */
	private String stime;
	/**
	 * 结束时间
	 */
	private String etime;
	/**
	 * 创建时间
	 */
	private String createtime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 记录创建用户的id
	 */
	private Integer userid;
	/**
	 * 记录创建用户的登录名
	 */
	private String loginname;
	
	/**流水号**/
	private Integer seq;
	
	/*
	 * setter getter
	 */
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}
