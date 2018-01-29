/**
* Description: car-eye车辆管理平台
* 文件名：CarPositionInfo.java
* 版本信息：1.0
* 日期：2014-7-29
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：car-eye
 * @类名称：CarState
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-7-29 下午04:46:13
 * @修改人：zhangrong
 * @修改时间：2014-7-29 下午04:46:13
 * @修改备注：
 * @version 1.0
 */
public class CarState {
	
	private Integer id;
	private Integer carid;
    private Integer carstatus;
    private Integer kzstate;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
	}
	public Integer getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}
	public Integer getKzstate() {
		return kzstate;
	}
	public void setKzstate(Integer kzstate) {
		this.kzstate = kzstate;
	}
    
    
	
}
