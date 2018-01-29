/**
* Description: car-eye车辆管理平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：car-eye
 * @类名称：DriverPhoto
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-4-14 下午01:48:36
 * @修改人：zhangrong
 * @修改时间：2014-4-14 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class DriverPhoto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	/**服务监督卡号*/
	private String drivercode;
	
	/**司机姓名*/
	private String drivername;
	
	/**单位名称*/
	private String company;
	
	/**性别	男/女*/
	private String sex;
	
	/**备注*/
	private String remark;
	
	/**照片路径*/
	private String photopath;
	
	/**服务等级*/
	private String serverlevel;
	
	/**创建时间*/
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getServerlevel() {
		return serverlevel;
	}

	public void setServerlevel(String serverlevel) {
		this.serverlevel = serverlevel;
	}
	
	

}
