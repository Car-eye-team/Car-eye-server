/**
* Description: car-eye车辆管理平台
* 文件名：OnCallCountInfo.java
* 版本信息：1.0
* 日期：2015-4-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.oncalltransaction.domain;

/**
 * @项目名称：car-eye
 * @类名称：OnCallCountInfo
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 上午10:34:03
 * @修改人：Yuqk
 * @修改时间：2015-4-3 上午10:34:03
 * @修改备注：
 * @version 1.0
 */
public class OnCallCountInfo {
	/**
	 * 统计类型		case 1: return "拨入电话数量";
					case 2: return "接入系统数量";
					case 3: return "下发业务数量";
					case 4: return "抢答业务数量";
					case 5: return "派车成功数量";
					case 6: return "客户取消数量";
					case 7: return "司机取消数量";
					case 8: return "客户违约数量";
					case 9: return "司机违约数量";
	 */
	private Integer counttype;
	/**
	 * 统计数量
	 */
	private Integer countnumber;
	/**
	 * 查询开始时间
	 */
	private String stime;
	/**
	 * 查询结束时间
	 */
	private String etime;
	/**
	 * 集团id
	 */
	private Integer blocid;
	/**
	 * 车牌号
	 */
	private String carnumber;
	/**
	 * 司机姓名
	 */
	private String drivername;
	/**
	 * 客户姓名
	 */
	private String cusname;
	
	/*
	 * getter  setter
	 */
	public Integer getCounttype() {
		return counttype;
	}
	public void setCounttype(Integer counttype) {
		this.counttype = counttype;
	}
	public Integer getCountnumber() {
		return countnumber;
	}
	public void setCountnumber(Integer countnumber) {
		this.countnumber = countnumber;
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
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getCusname() {
		return cusname;
	}
	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
}
