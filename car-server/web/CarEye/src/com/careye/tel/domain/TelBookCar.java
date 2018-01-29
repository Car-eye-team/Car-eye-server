/**
* Description: 多森商用车平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.tel.domain;

import java.io.Serializable;

/**
 * @项目名称：FMS
 * @类名称：TelBookCar
 * @类描述：3.4.4	车辆电话本表
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class TelBookCar implements Serializable{
	
	private Integer id;
	private Integer cartelbookid;
	
	/**组织机构*/
	private Integer deptid;
	
	/**用户ID*/
	private Integer userid;
	
	/** 电话本ID **/
	private Integer telbookid;
	
	/** 呼叫类型	1：呼入；2：呼出；3：呼入/呼出 **/
	private Integer calltype;
	
	/**联系人*/
	private String contacts;
	
	/**电话号码*/
	private String tel;
	
	/**车牌号*/
	private String carnumber;
	
	/**流水号*/
	private Integer seq;

	/**处理结果   1成功 2失败*/
	private Integer result;
	
	/**创建时间*/
	private String createtime;
	
	/**设备号**/
	private String devicenumber;
	private Integer opertype;

	/**备注**/
	private String remark;
	
	/**原始数据包**/
	private String data;
	
	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	
	public Integer getCartelbookid() {
		return cartelbookid;
	}

	public void setCartelbookid(Integer cartelbookid) {
		this.cartelbookid = cartelbookid;
	}

	public Integer getOpertype() {
		return opertype;
	}

	public void setOpertype(Integer opertype) {
		this.opertype = opertype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDevicenumber() {
		return devicenumber;
	}

	public void setDevicenumber(String devicenumber) {
		this.devicenumber = devicenumber;
	}

	public Integer getCalltype() {
		return calltype;
	}

	public void setCalltype(Integer calltype) {
		this.calltype = calltype;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public Integer getTelbookid() {
		return telbookid;
	}

	public void setTelbookid(Integer telbookid) {
		this.telbookid = telbookid;
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

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


	
	
}
