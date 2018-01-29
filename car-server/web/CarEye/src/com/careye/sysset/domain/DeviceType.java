/**
* Description: car-eye车辆管理平台
* 文件名：DeviceType.java
* 版本信息：1.0
* 日期：2014-7-8
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

/**
 * @项目名称：FMS
 * @类名称：DeviceType
 * @类描述：设备类型
 * @创建人：lenovo
 * @创建时间：2014-7-8 下午01:32:44
 * @修改人：lenovo
 * @修改时间：2014-7-8 下午01:32:44
 * @修改备注：
 * @version 1.0
 */
public class DeviceType {
	
	private Integer id;
	
	/**设备类型ID**/
	private Integer typeid;
	
	/**用户类型	默认 11**/
	private Integer usertype;
	
	/**设备类型名称**/
	private String typename;
	
	/**所属厂家**/
	private String company;
	
	/**设备网络类型**/
	private String inteltype;
	
	/**用户id**/
	private Integer userid;
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**创建时间**/
	private String createtime;

	
	
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getInteltype() {
		return inteltype;
	}

	public void setInteltype(String inteltype) {
		this.inteltype = inteltype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	
}
