/**
* Description: car-eye车辆管理平台
* 文件名：ClockInfo.java
* 版本信息：1.0
* 日期：2015-11-3
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：DSTAXISERVER
 * @类名称：ClockInfo
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2015-11-3 上午10:52:28
 * @修改人：wuyongde
 * @修改时间：2015-11-3 上午10:52:28
 * @修改备注：
 * @version 1.0
 */
public class ClockInfo {
	
	private Integer id;
	
	private Integer carid;
	private String createtime;
	
	/**1 上班、2下班**/
	private Integer type;
	/**总营运次数**/
	private Integer count;
	/**操作结果	144:执行正确 255:执行错误**/
	private Integer result;
	/**脉冲数**/
	private Integer mcs;
	/**卡次**/
	private Integer cardnum;
	/**总营运次数**/
	private Integer totalnumber;
	/**车次**/
	private Integer vehicletrips;
	/**单位代码**/
	private String companycode;
	/**司机代码**/
	private String drivercode;
	/**车辆自编号**/
	private String vehicleid;
	/**时间**/
	private String signintime;
	/**驾驶员唯一编号**/
	private String driverid;
	/**上班时间**/
	private String stime;
	/**下班时间**/
	private String etime;
	/**当班公里**/
	private String dbmileage;
	/**当班营运公里**/
	private String dbyymileage;
	/**计时时间	秒**/
	private Integer jstmie;
	/**总计金额**/
	private String totalamount;
	/**卡收金额**/
	private String cardamount;
	/**班间公里	上一班签退到本班签到的公里数**/
	private String bjmileage;
	/**总计公里	计价器安装后累积的里程**/
	private String totalmileage;
	/**总营运公里	计价器安装后累积的里程**/
	private String totalyymileage;
	/**单价	格式XX.XX元**/
	private String price;
	/**总等待时间	秒**/
	private Integer totalwaittime;
	
	/**上班位置信息**/
	private Double sblat;
	private Double sblng;
	private Double sbblat;
	private Double sbblng;
	private String sbbaddress;
	private String sbaltitude;
	private String sbspeed;
	private String sbdirection;
	
	/**上班位置信息**/
	private Double xblat;
	private Double xblng;
	private Double xbblat;
	private Double xbblng;
	private String xbbaddress;
	private String xbaltitude;
	private String xbspeed;
	private String xbdirection;
	
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Integer getMcs() {
		return mcs;
	}
	public void setMcs(Integer mcs) {
		this.mcs = mcs;
	}
	public Integer getCardnum() {
		return cardnum;
	}
	public void setCardnum(Integer cardnum) {
		this.cardnum = cardnum;
	}
	public Integer getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(Integer totalnumber) {
		this.totalnumber = totalnumber;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getDrivercode() {
		return drivercode;
	}
	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
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
	public String getDbmileage() {
		return dbmileage;
	}
	public void setDbmileage(String dbmileage) {
		this.dbmileage = dbmileage;
	}
	public String getDbyymileage() {
		return dbyymileage;
	}
	public void setDbyymileage(String dbyymileage) {
		this.dbyymileage = dbyymileage;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getCardamount() {
		return cardamount;
	}
	public void setCardamount(String cardamount) {
		this.cardamount = cardamount;
	}
	public String getBjmileage() {
		return bjmileage;
	}
	public void setBjmileage(String bjmileage) {
		this.bjmileage = bjmileage;
	}
	public String getTotalmileage() {
		return totalmileage;
	}
	public void setTotalmileage(String totalmileage) {
		this.totalmileage = totalmileage;
	}
	public String getTotalyymileage() {
		return totalyymileage;
	}
	public void setTotalyymileage(String totalyymileage) {
		this.totalyymileage = totalyymileage;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getJstmie() {
		return jstmie;
	}
	public void setJstmie(Integer jstmie) {
		this.jstmie = jstmie;
	}
	public String getSignintime() {
		return signintime;
	}
	public void setSignintime(String signintime) {
		this.signintime = signintime;
	}
	public Integer getVehicletrips() {
		return vehicletrips;
	}
	public void setVehicletrips(Integer vehicletrips) {
		this.vehicletrips = vehicletrips;
	}
	public Integer getTotalwaittime() {
		return totalwaittime;
	}
	public void setTotalwaittime(Integer totalwaittime) {
		this.totalwaittime = totalwaittime;
	}
	public String getSbbaddress() {
		return sbbaddress;
	}
	public void setSbbaddress(String sbbaddress) {
		this.sbbaddress = sbbaddress;
	}
	public String getSbaltitude() {
		return sbaltitude;
	}
	public void setSbaltitude(String sbaltitude) {
		this.sbaltitude = sbaltitude;
	}
	public String getSbspeed() {
		return sbspeed;
	}
	public void setSbspeed(String sbspeed) {
		this.sbspeed = sbspeed;
	}
	public String getSbdirection() {
		return sbdirection;
	}
	public void setSbdirection(String sbdirection) {
		this.sbdirection = sbdirection;
	}
	public String getXbbaddress() {
		return xbbaddress;
	}
	public void setXbbaddress(String xbbaddress) {
		this.xbbaddress = xbbaddress;
	}
	public String getXbaltitude() {
		return xbaltitude;
	}
	public void setXbaltitude(String xbaltitude) {
		this.xbaltitude = xbaltitude;
	}
	public String getXbspeed() {
		return xbspeed;
	}
	public void setXbspeed(String xbspeed) {
		this.xbspeed = xbspeed;
	}
	public String getXbdirection() {
		return xbdirection;
	}
	public void setXbdirection(String xbdirection) {
		this.xbdirection = xbdirection;
	}
	public Double getSblat() {
		return sblat;
	}
	public void setSblat(Double sblat) {
		this.sblat = sblat;
	}
	public Double getSblng() {
		return sblng;
	}
	public void setSblng(Double sblng) {
		this.sblng = sblng;
	}
	public Double getSbblat() {
		return sbblat;
	}
	public void setSbblat(Double sbblat) {
		this.sbblat = sbblat;
	}
	public Double getSbblng() {
		return sbblng;
	}
	public void setSbblng(Double sbblng) {
		this.sbblng = sbblng;
	}
	public Double getXblat() {
		return xblat;
	}
	public void setXblat(Double xblat) {
		this.xblat = xblat;
	}
	public Double getXblng() {
		return xblng;
	}
	public void setXblng(Double xblng) {
		this.xblng = xblng;
	}
	public Double getXbblat() {
		return xbblat;
	}
	public void setXbblat(Double xbblat) {
		this.xbblat = xbblat;
	}
	public Double getXbblng() {
		return xbblng;
	}
	public void setXbblng(Double xbblng) {
		this.xbblng = xbblng;
	}

	
	
}



