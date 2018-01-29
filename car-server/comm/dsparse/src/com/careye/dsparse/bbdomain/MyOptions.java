
/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**
 * 
 * @项目名称：dsparse
 * @类名称：MyOptions
 * @类描述：我的车源
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午06:22:52
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午06:22:52
 * @修改备注：
 * @version 1.0
 */
public class MyOptions extends  BaseInfo{
     /**车源ID **/
	 private int carsourceid;
	 /** 生效状态**/
	 private int effect;
	 /** 会员ID**/
	 private int memberid;
	 /** 类型**/
	 private String type;
	 /** 用途**/
	 private String use;
	 /** 出发地**/
	 private String inchoat;
	 /** 目的地**/
	 private String destination;
	 /** 是否加急**/
	 private String isexpedited;
	 /** 运输路线**/
	 private String transportline;
	 /** 发布时间**/
	 private String releasetime;
	public int getCarsourceid() {
		return carsourceid;
	}
	public void setCarsourceid(int carsourceid) {
		this.carsourceid = carsourceid;
	}
	public int getEffect() {
		return effect;
	}
	public void setEffect(int effect) {
		this.effect = effect;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getInchoat() {
		return inchoat;
	}
	public void setInchoat(String inchoat) {
		this.inchoat = inchoat;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getIsexpedited() {
		return isexpedited;
	}
	public void setIsexpedited(String isexpedited) {
		this.isexpedited = isexpedited;
	}
	public String getTransportline() {
		return transportline;
	}
	public void setTransportline(String transportline) {
		this.transportline = transportline;
	}
	public String getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
}
