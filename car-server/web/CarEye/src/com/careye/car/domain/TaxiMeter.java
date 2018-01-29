/**
* Description: car-eye车辆管理平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：TaxiMeter
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-25 下午01:48:36
 * @修改人：huangqin
 * @修改时间：2015-3-25 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class TaxiMeter extends  BaseDomain implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	private Integer id;
	/** 车牌号  **/
	private String carnumber;
	private String flagnew;
	private String stime1;
	private String etime1;
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
	public String getFlagnew() {
		return flagnew;
	}
	public void setFlagnew(String flagnew) {
		this.flagnew = flagnew;
	}
	public String getStime1() {
		return stime1;
	}
	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}
	public String getEtime1() {
		return etime1;
	}
	public void setEtime1(String etime1) {
		this.etime1 = etime1;
	}
	
}
