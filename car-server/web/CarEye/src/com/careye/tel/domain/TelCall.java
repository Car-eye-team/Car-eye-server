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
 * @类名称：TelCall
 * @类描述：3.4.1	电话回拨消息表
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class TelCall implements Serializable{
	
	private Integer id;
	
	/**组织机构*/
	private Integer deptid;
	
	/**用户ID*/
	private Integer userid;
	
	/** 呼叫类型	1：呼入；2：呼出；3：呼入/呼出 **/
	private Integer calltype;
	
	/** 标志	0：普通通话；1：监听 **/
	private Integer flag;
	
	/**电话号码*/
	private String tel;

	/**备注*/
	private String remark;
	
	/**创建时间*/
	private String createtime;

	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public Integer getCalltype() {
		return calltype;
	}

	public void setCalltype(Integer calltype) {
		this.calltype = calltype;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	
	
}
