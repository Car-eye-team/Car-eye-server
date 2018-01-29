/**
* Description: car-eye车辆管理平台
* 文件名：PowerSet.java
* 版本信息：1.0
* 日期：2014-9-18
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

/**
 * @项目名称：car-eye
 * @类名称：DialfeeSet
 * @类描述：电召费用设置
 * @创建人：huangqin
 * @创建时间：2015-4-16 上午09:30:07
 * @修改人：huangqin
 * @修改时间：2015-4-16 上午09:30:07
 * @修改备注：
 * @version 1.0
 */
public class DialfeeSet {

	/**id**/
	private Integer id;
	
	private String dialfee;
	/** 燃油费 **/
	private String oilcost;
	/** 合乘折扣率（百分百） **/
	private String discount;
	
	private String effecttime;
	
	private Integer userid;
	
	private String createtime;
    
	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;

	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDialfee() {
		return dialfee;
	}

	public void setDialfee(String dialfee) {
		this.dialfee = dialfee;
	}

	public String getEffecttime() {
		return effecttime;
	}

	public void setEffecttime(String effecttime) {
		this.effecttime = effecttime;
	}

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

	public String getOilcost() {
		return oilcost;
	}

	public void setOilcost(String oilcost) {
		this.oilcost = oilcost;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}





