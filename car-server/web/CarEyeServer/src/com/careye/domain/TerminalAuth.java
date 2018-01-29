/**
* Description: car-eye车辆管理平台
* 文件名：TerminalAuth.java
* 版本信息：1.0
* 日期：2013-8-7
* Copyright car-eye车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.domain;

import java.io.Serializable;

/**
 * @car-eye车辆管理平台业务处理
 * @类名称：TerminalAuth
 * @类描述：终端鉴权实体类
 * @创建人：zr
 * @创建时间：2013-8-7 下午02:19:10
 * @修改人：zr
 * @修改时间：2013-8-7 下午02:19:10
 * @修改备注：
 * @version 1.0
 */
public class TerminalAuth  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 车载手机号码
	 */
	private String carTel;
	
	/**
     * 所属组织机构
     */
    private String deptId;
	
	/**
	 * 终端登录密码
	 */
	private String carPassword;
	
	
	private int userType;
	
	private int NOTIFY_ID;
	


	public int getNOTIFY_ID() {
		return NOTIFY_ID;
	}


	public void setNOTIFY_ID(int nOTIFYID) {
		NOTIFY_ID = nOTIFYID;
	}


	public String getCarTel() {
		return carTel;
	}


	public void setCarTel(String carTel) {
		this.carTel = carTel;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public String getCarPassword() {
		return carPassword;
	}


	public void setCarPassword(String carPassword) {
		this.carPassword = carPassword;
	}


	public int getUserType() {
		return userType;
	}


	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	

	
}
