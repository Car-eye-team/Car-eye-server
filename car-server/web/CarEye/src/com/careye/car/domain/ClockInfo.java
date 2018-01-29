/**
* Description: car-eye车辆管理平台
* 文件名：ClockInfo.java
* 版本信息：1.0
* 日期：2015-11-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.domain;


/**
 * @项目名称：DSTAXISERVER
 * @类名称：ClockInfo
 * @类描述：考勤信息
 * @创建人：zhangrong
 * @创建时间：2015-11-3 上午10:52:28
 * @修改人：zhangrong
 * @修改时间：2015-11-3 上午10:52:28
 * @修改备注：
 * @version 1.0
 */
public class ClockInfo {
	
	private Integer userid;
	
	private Integer id;
	
	private Integer carid;
	private String createtime;
	
	private String carnumber;
	private String terminal;
	
	/**组织机构ID*/
	private Integer blocid;
	
	/**组织机构名称*/
	private String blocname;
	
	private String startstime;
	private String startetime;
	private String endstime;
	private String endetime;
	
	/**1 上班、2下班**/
	private Integer type;
	/**总营运次数**/
	private String count;
	/**操作结果	144:执行正确 255:执行错误**/
	private Integer result;
	/**脉冲数**/
	private String mcs;
	/**卡次**/
	private String cardnum;
	/**总营运次数**/
	private String totalnumber;
	/**车次**/
	private String vehicletrips;
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
	private String jstmie;
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
	private String totalwaittime;
	
	private String sbblng;
	private String sbblat;
	private String sbbaddress;
	private String xbblng;
	private String xbblat;
	private String xbbaddress;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
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
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public String getStartstime() {
		return startstime;
	}
	public void setStartstime(String startstime) {
		this.startstime = startstime;
	}
	public String getStartetime() {
		return startetime;
	}
	public void setStartetime(String startetime) {
		this.startetime = startetime;
	}
	public String getEndstime() {
		return endstime;
	}
	public void setEndstime(String endstime) {
		this.endstime = endstime;
	}
	public String getEndetime() {
		return endetime;
	}
	public void setEndetime(String endetime) {
		this.endetime = endetime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getMcs() {
		return mcs;
	}
	public void setMcs(String mcs) {
		this.mcs = mcs;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(String totalnumber) {
		this.totalnumber = totalnumber;
	}
	public String getVehicletrips() {
		return vehicletrips;
	}
	public void setVehicletrips(String vehicletrips) {
		this.vehicletrips = vehicletrips;
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
	public String getSignintime() {
		return signintime;
	}
	public void setSignintime(String signintime) {
		this.signintime = signintime;
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
	public String getJstmie() {
		return jstmie;
	}
	public void setJstmie(String jstmie) {
		this.jstmie = jstmie;
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
	public String getTotalwaittime() {
		return totalwaittime;
	}
	public void setTotalwaittime(String totalwaittime) {
		this.totalwaittime = totalwaittime;
	}
	public String getSbblng() {
		return sbblng;
	}
	public void setSbblng(String sbblng) {
		this.sbblng = sbblng;
	}
	public String getSbblat() {
		return sbblat;
	}
	public void setSbblat(String sbblat) {
		this.sbblat = sbblat;
	}
	public String getSbbaddress() {
		return sbbaddress;
	}
	public void setSbbaddress(String sbbaddress) {
		this.sbbaddress = sbbaddress;
	}
	public String getXbblng() {
		return xbblng;
	}
	public void setXbblng(String xbblng) {
		this.xbblng = xbblng;
	}
	public String getXbblat() {
		return xbblat;
	}
	public void setXbblat(String xbblat) {
		this.xbblat = xbblat;
	}
	public String getXbbaddress() {
		return xbbaddress;
	}
	public void setXbbaddress(String xbbaddress) {
		this.xbbaddress = xbbaddress;
	}
	

}



