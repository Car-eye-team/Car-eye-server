/**
* Description: car-eye车辆管理平台
* 文件名：User.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.domain;



/**
 * @项目名称：car-eyeTms
 * @类名称：User
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-2-19 上午09:09:18
 * @修改人：zhangrong
 * @修改时间：2014-2-19 上午09:09:18
 * @修改备注：
 * @version 1.0
 */
public class UserCar {

	private static final long serialVersionUID = 1L;
	
private Integer id;
	
	/**用户ID*/
	private Integer userid;
	
	/**车辆ID*/
	private Integer carid;
	
	/**类型 	1代表公司内部车辆分配 2平台车辆分配*/
	private Integer type;
	
	/**创建时间**/
	private String createtime;
	
	private String carnumber;
	private String deptname;


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

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	

}
