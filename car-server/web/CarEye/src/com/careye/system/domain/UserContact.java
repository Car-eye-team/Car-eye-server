/**
* Description: car-eye车辆管理平台
* 文件名：UserContact.java
* 版本信息：1.0
* 日期：2014-8-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.domain;

/**
 * @项目名称：FMS
 * @类名称：UserContact
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-8-26 下午05:53:15
 * @修改人：zhangrong
 * @修改时间：2014-8-26 下午05:53:15
 * @修改备注：
 * @version 1.0
 */
public class UserContact {

	/**主键**/
	private Integer id;
	
	/**用户id**/
	private Integer userid;
	
	/**投诉电话**/
	private String complaintel;
	
	/**业务经理姓名**/
	private String mgrname;
	
	/**业务经理电话**/
	private String mgrphone;
	
	/**维护人员姓名**/
	private String repairname;
	
	/**维护人员电话**/
	private String repairphone;
	
	/**技术支持电话**/
	private String supporttel;
	
	/**24小时客服电话**/
	private String servicetel;
	
	/**创建时间**/
	private String createtime;

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

	public String getComplaintel() {
		return complaintel;
	}

	public void setComplaintel(String complaintel) {
		this.complaintel = complaintel;
	}

	public String getMgrname() {
		return mgrname;
	}

	public void setMgrname(String mgrname) {
		this.mgrname = mgrname;
	}

	public String getMgrphone() {
		return mgrphone;
	}

	public void setMgrphone(String mgrphone) {
		this.mgrphone = mgrphone;
	}

	public String getRepairname() {
		return repairname;
	}

	public void setRepairname(String repairname) {
		this.repairname = repairname;
	}

	public String getRepairphone() {
		return repairphone;
	}

	public void setRepairphone(String repairphone) {
		this.repairphone = repairphone;
	}

	public String getSupporttel() {
		return supporttel;
	}

	public void setSupporttel(String supporttel) {
		this.supporttel = supporttel;
	}

	public String getServicetel() {
		return servicetel;
	}

	public void setServicetel(String servicetel) {
		this.servicetel = servicetel;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	
	
	
}
