/**
* Description: car-eye车辆管理平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;


/**
 * @项目名称：TAXI
 * @类名称：Taximeter
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-6-16 上午10:00:03
 * @修改人：huangqin
 * @修改时间：2015-6-16 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public class Taximeter implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;

	/** 主键，自增 **/
	private  Integer id;
	/** 终端号码 **/
	private  String  terminal;
	/** 车牌号 **/
	private  String  carnumber;
	/** 创建时间**/
	private  String  createtime;
	/** 备注**/
	private  String  remark;
	/** 出发地纬度 **/
	private  Double  slat;
	/** 出发地经度**/
	private  Double  slng;
	/** 目的地纬度**/
	private  Double  elat;
	/** 目的地经度**/
	private  Double  elng;
	/** 营运数据流水号**/
	private  String  taxseq;
	/** 司机标志号**/
	private  String  driverid;
	/** 上车天数**/
	private  Integer  day;
	/** 交易日期**/
	private  String  tradingdate;
	/** 上车时间**/
	private  String  stime;
	/** 下车时间**/
	private  String  etime;
	/** 用户卡内部序列号/ PSAM卡交易计数**/
	private  Integer  psamcount;
	/** 用户卡发行号**/
	private  String  psamno;
	/** 行驶里程**/
	private  String  mileage;
	/** 等候时间**/
	private  String  waitingtime;
	/** 空驶里程**/
	private  String  airmileage;
	/** 交易类型**/
	private  Integer  tradetype;
	/** 应收金额**/
	private  String  amountsreceivable;
	/** 实收金额**/
	private  String  realamount;
	/** 卡内余额**/
	private  String  cardbalance;
	/** 物理卡类型  0 – 未知类型
1 – MIFARE 1（s50）卡
2 – MIFARE PRO（或兼容）卡
5 – MIFARE 1（s70）卡
其它–保留**/
	private  Integer  wlcardtype;
	/** 卡类型**/
	private  Integer  cardtype;
	/** 用户卡交易计数**/
	private  Integer  usrtradecount;
	/** 终端交易计数/None**/
	private  Integer  tertradecount;
	/** PSAM卡号**/
	private  String  psamcardnum;
	/** 交易认证码**/
	private  String  tradeauthcode;
	/** 运营序号 **/
	private  String  opernum;
	/** 电召费**/
	private  String  callcharge;
	/** 燃油附加费**/
	private  String  fuelsurcharge;
	/** 其他费用**/
	private  String  otherexpenses;

	/** 上车开始时间**/
	private String stime1;
	/** 上车结束时间 **/
	private String etime1;
	
	/** 下车开始时间**/
	private String stime2;
	/** 下车结束时间 **/
	private String etime2;
	
	/** 开始时间**/
	private String stime3;
	/** 结束时间 **/
	private String etime3;
	/** 开始时间**/
	private String stime4;
	/** 结束时间 **/
	private String etime4;
	/**帧序号**/
	private Integer framenum;
	
	public String getStime4() {
		return stime4;
	}
	public void setStime4(String stime4) {
		this.stime4 = stime4;
	}
	public String getEtime4() {
		return etime4;
	}
	public void setEtime4(String etime4) {
		this.etime4 = etime4;
	}
	public String getStime2() {
		return stime2;
	}
	public void setStime2(String stime2) {
		this.stime2 = stime2;
	}
	public String getEtime2() {
		return etime2;
	}
	public void setEtime2(String etime2) {
		this.etime2 = etime2;
	}
	public String getStime3() {
		return stime3;
	}
	public void setStime3(String stime3) {
		this.stime3 = stime3;
	}
	public String getEtime3() {
		return etime3;
	}
	public void setEtime3(String etime3) {
		this.etime3 = etime3;
	}
	
	
	
	
	/**用户ID*/
	private Integer userid;
	
	/**用户名称*/
	private String username;
	
	/**组织机构ID*/
	private Integer blocid;
	
	/**组织机构名称*/
	private String blocname;
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTaxseq() {
		return taxseq;
	}
	public void setTaxseq(String taxseq) {
		this.taxseq = taxseq;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getTradingdate() {
		return tradingdate;
	}
	public void setTradingdate(String tradingdate) {
		this.tradingdate = tradingdate;
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
	public Integer getPsamcount() {
		return psamcount;
	}
	public void setPsamcount(Integer psamcount) {
		this.psamcount = psamcount;
	}
	public String getPsamno() {
		return psamno;
	}
	public void setPsamno(String psamno) {
		this.psamno = psamno;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getWaitingtime() {
		return waitingtime;
	}
	public void setWaitingtime(String waitingtime) {
		this.waitingtime = waitingtime;
	}
	public String getAirmileage() {
		return airmileage;
	}
	public void setAirmileage(String airmileage) {
		this.airmileage = airmileage;
	}
	public Integer getTradetype() {
		return tradetype;
	}
	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}
	public String getAmountsreceivable() {
		return amountsreceivable;
	}
	public void setAmountsreceivable(String amountsreceivable) {
		this.amountsreceivable = amountsreceivable;
	}
	public String getRealamount() {
		return realamount;
	}
	public void setRealamount(String realamount) {
		this.realamount = realamount;
	}
	public String getCardbalance() {
		return cardbalance;
	}
	public void setCardbalance(String cardbalance) {
		this.cardbalance = cardbalance;
	}
	public Integer getWlcardtype() {
		return wlcardtype;
	}
	public void setWlcardtype(Integer wlcardtype) {
		this.wlcardtype = wlcardtype;
	}
	public Integer getCardtype() {
		return cardtype;
	}
	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}
	public Integer getUsrtradecount() {
		return usrtradecount;
	}
	public void setUsrtradecount(Integer usrtradecount) {
		this.usrtradecount = usrtradecount;
	}
	public Integer getTertradecount() {
		return tertradecount;
	}
	public void setTertradecount(Integer tertradecount) {
		this.tertradecount = tertradecount;
	}
	public String getPsamcardnum() {
		return psamcardnum;
	}
	public void setPsamcardnum(String psamcardnum) {
		this.psamcardnum = psamcardnum;
	}
	public String getTradeauthcode() {
		return tradeauthcode;
	}
	public void setTradeauthcode(String tradeauthcode) {
		this.tradeauthcode = tradeauthcode;
	}
	public String getOpernum() {
		return opernum;
	}
	public void setOpernum(String opernum) {
		this.opernum = opernum;
	}
	public String getCallcharge() {
		return callcharge;
	}
	public void setCallcharge(String callcharge) {
		this.callcharge = callcharge;
	}
	public String getFuelsurcharge() {
		return fuelsurcharge;
	}
	public void setFuelsurcharge(String fuelsurcharge) {
		this.fuelsurcharge = fuelsurcharge;
	}
	public String getOtherexpenses() {
		return otherexpenses;
	}
	public void setOtherexpenses(String otherexpenses) {
		this.otherexpenses = otherexpenses;
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
	public Integer getFramenum() {
		return framenum;
	}
	public void setFramenum(Integer framenum) {
		this.framenum = framenum;
	}
	public Double getSlat() {
		return slat;
	}
	public void setSlat(Double slat) {
		this.slat = slat;
	}
	public Double getSlng() {
		return slng;
	}
	public void setSlng(Double slng) {
		this.slng = slng;
	}
	public Double getElat() {
		return elat;
	}
	public void setElat(Double elat) {
		this.elat = elat;
	}
	public Double getElng() {
		return elng;
	}
	public void setElng(Double elng) {
		this.elng = elng;
	}
	
}
