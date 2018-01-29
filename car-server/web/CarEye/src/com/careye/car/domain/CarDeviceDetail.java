/**
* Description: car-eye车辆管理平台
* 文件名：CarDeviceDetail.java
* 版本信息：1.0
* 日期：2015-5-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.domain;

/**
 * @项目名称：car-eye
 * @类名称：CarDeviceDetail
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-5-5 下午03:50:04
 * @修改人：zhangrong
 * @修改时间：2015-5-5 下午03:50:04
 * @修改备注：
 * @version 1.0
 */
public class CarDeviceDetail {

	/**用户类型**/
	private Integer usertype;
	
	/**终端类型**/
	private Integer typeid;

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	
}
