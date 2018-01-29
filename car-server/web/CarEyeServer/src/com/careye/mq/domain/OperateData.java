/**
* Description: car-eye车辆管理平台
* 文件名：OperateData.java
* 版本信息：1.0
* 日期：2015-11-4
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：DSTAXISERVER
 * @类名称：OperateData
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2015-11-4 下午02:44:23
 * @修改人：wuyongde
 * @修改时间：2015-11-4 下午02:44:23
 * @修改备注：
 * @version 1.0
 */
public class OperateData {

	private Integer id;
	/**车辆id**/
	private Integer carid;
	/**操作时间**/
	private String createtime;
	/**载客人数**/
	private Integer peoplenumber;
	/**评价选项	0x00,没有做出评价；
	0x01,非常满意；
	0x02,满意；
	0x03,一般；
	0x04，不满意；
	0x05，投诉**/
	private Integer options;
	/**交易类型	0x01：现金 
	0x02：刷卡（为刷卡时以下字段填0）**/
	private Integer tradetype;
	/**卡类型	0x01：M1卡 
	0x02：CPU电子钱包 
	0x03：CPU电子现金**/
	private Integer cardtype;
	/**卡厂商	0x01：天府通 
	0x02：和信通**/
	private Integer cardoem;
	
	private Double klat;
	private Double klng;
	private Double zlat;
	private Double zlng;
	private String kalarminfo;
	private String kstateinfo;
	private String kaltitude;
	private String kspeed;
	private String kdirection;
	private String ktime;
	private String zalarminfo;
	private String zstateinfo;
	private String zaltitude;
	private String zspeed;
	private String zdirection;
	private String ztime;
	private String runid;
	private String evaluateid;
	private String carnumber;
	private String companycode;
	private String drivercode;
	private String driverid;
	private String stime;
	/**hhss分秒**/
	private String etime;
	private String mileage;
	private String airmileage;
	private String fuelsurcharge;
	/**分钟**/
	private String waitingtime;
	private String tradeamount;
	private String vehicletrips;
	private String tradetime;
	private String csn;
	private String tradecardno;
	private String tradeseq;
	private String samno;
	private String samseq;
	private String cardtradeamount;
	private String tradebalance;
	private String extend;
	private String kaddress;
	private String zaddress;
	
	
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
	public Integer getPeoplenumber() {
		return peoplenumber;
	}
	public void setPeoplenumber(Integer peoplenumber) {
		this.peoplenumber = peoplenumber;
	}
	public Integer getOptions() {
		return options;
	}
	public void setOptions(Integer options) {
		this.options = options;
	}
	public Integer getTradetype() {
		return tradetype;
	}
	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}
	public Integer getCardtype() {
		return cardtype;
	}
	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}
	public Integer getCardoem() {
		return cardoem;
	}
	public void setCardoem(Integer cardoem) {
		this.cardoem = cardoem;
	}
	public Double getKlat() {
		return klat;
	}
	public void setKlat(Double klat) {
		this.klat = klat;
	}
	public Double getKlng() {
		return klng;
	}
	public void setKlng(Double klng) {
		this.klng = klng;
	}
	public Double getZlat() {
		return zlat;
	}
	public void setZlat(Double zlat) {
		this.zlat = zlat;
	}
	public Double getZlng() {
		return zlng;
	}
	public void setZlng(Double zlng) {
		this.zlng = zlng;
	}
	public String getKalarminfo() {
		return kalarminfo;
	}
	public void setKalarminfo(String kalarminfo) {
		this.kalarminfo = kalarminfo;
	}
	public String getKstateinfo() {
		return kstateinfo;
	}
	public void setKstateinfo(String kstateinfo) {
		this.kstateinfo = kstateinfo;
	}
	public String getKaltitude() {
		return kaltitude;
	}
	public void setKaltitude(String kaltitude) {
		this.kaltitude = kaltitude;
	}
	public String getKspeed() {
		return kspeed;
	}
	public void setKspeed(String kspeed) {
		this.kspeed = kspeed;
	}
	public String getKdirection() {
		return kdirection;
	}
	public void setKdirection(String kdirection) {
		this.kdirection = kdirection;
	}
	public String getKtime() {
		return ktime;
	}
	public void setKtime(String ktime) {
		this.ktime = ktime;
	}
	public String getZalarminfo() {
		return zalarminfo;
	}
	public void setZalarminfo(String zalarminfo) {
		this.zalarminfo = zalarminfo;
	}
	public String getZstateinfo() {
		return zstateinfo;
	}
	public void setZstateinfo(String zstateinfo) {
		this.zstateinfo = zstateinfo;
	}
	public String getZaltitude() {
		return zaltitude;
	}
	public void setZaltitude(String zaltitude) {
		this.zaltitude = zaltitude;
	}
	public String getZspeed() {
		return zspeed;
	}
	public void setZspeed(String zspeed) {
		this.zspeed = zspeed;
	}
	public String getZdirection() {
		return zdirection;
	}
	public void setZdirection(String zdirection) {
		this.zdirection = zdirection;
	}
	public String getZtime() {
		return ztime;
	}
	public void setZtime(String ztime) {
		this.ztime = ztime;
	}
	public String getRunid() {
		return runid;
	}
	public void setRunid(String runid) {
		this.runid = runid;
	}
	public String getEvaluateid() {
		return evaluateid;
	}
	public void setEvaluateid(String evaluateid) {
		this.evaluateid = evaluateid;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getAirmileage() {
		return airmileage;
	}
	public void setAirmileage(String airmileage) {
		this.airmileage = airmileage;
	}
	public String getFuelsurcharge() {
		return fuelsurcharge;
	}
	public void setFuelsurcharge(String fuelsurcharge) {
		this.fuelsurcharge = fuelsurcharge;
	}
	public String getWaitingtime() {
		return waitingtime;
	}
	public void setWaitingtime(String waitingtime) {
		this.waitingtime = waitingtime;
	}
	public String getTradeamount() {
		return tradeamount;
	}
	public void setTradeamount(String tradeamount) {
		this.tradeamount = tradeamount;
	}
	public String getVehicletrips() {
		return vehicletrips;
	}
	public void setVehicletrips(String vehicletrips) {
		this.vehicletrips = vehicletrips;
	}
	public String getTradetime() {
		return tradetime;
	}
	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public String getTradecardno() {
		return tradecardno;
	}
	public void setTradecardno(String tradecardno) {
		this.tradecardno = tradecardno;
	}
	public String getTradeseq() {
		return tradeseq;
	}
	public void setTradeseq(String tradeseq) {
		this.tradeseq = tradeseq;
	}
	public String getSamno() {
		return samno;
	}
	public void setSamno(String samno) {
		this.samno = samno;
	}
	public String getSamseq() {
		return samseq;
	}
	public void setSamseq(String samseq) {
		this.samseq = samseq;
	}
	public String getCardtradeamount() {
		return cardtradeamount;
	}
	public void setCardtradeamount(String cardtradeamount) {
		this.cardtradeamount = cardtradeamount;
	}
	public String getTradebalance() {
		return tradebalance;
	}
	public void setTradebalance(String tradebalance) {
		this.tradebalance = tradebalance;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getZaddress() {
		return zaddress;
	}
	public void setZaddress(String zaddress) {
		this.zaddress = zaddress;
	}
	public String getKaddress() {
		return kaddress;
	}
	public void setKaddress(String kaddress) {
		this.kaddress = kaddress;
	}
	
	
}




