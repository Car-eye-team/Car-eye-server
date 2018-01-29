/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.io.Serializable;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：PositionInfo    
 * 类描述：运营数据实体    
 * 创建人：zr    
 * 创建时间：2015-5-14 下午05:43:50    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午05:43:50    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class RunDataInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**位置信息汇报(0x0200)消息体 */
	private PositionInfo kpositionInfo;
	
	/**位置信息汇报(0x0200)消息体 */
	private PositionInfo zpositionInfo;

	
	/**营运ID*/
	private int runid;
	
	/**人数*/
	private int number;
	
	/**评价ID*/
	private int evaluateid;
	
	/**评价选项*/
	private int options;
	
	/**评价选项扩展*/
	private int extend;
	
	/**车牌号*/
	private String carnumber;
	
	/**单位代码*/
	private String companycode;
	
	/**经营许可证号*/
	private String blnumber;
	
	/**司机代码*/
	private String drivercode;
	
	/**驾驶员唯一编号*/
	private String driverid;
	
	/**上车时间*/
	private String stime;
	
	/**下车时间*/
	private String etime;
	
	/**计程公里*/
	private String mileage;
	
	/**空驶公里*/
	private String airmileage;
	
	/**附加费*/
	private String fuelsurcharge;
	
	/**等待计时时间*/
	private String waitingtime;
	
	/**交易金额*/
	private String tradeamount;
	
	/**当前车次*/
	private int vehicletrips;
	
	/**交易时间*/
	private String tradetime;
	
	/**交易类型*/
	private int tradetype;
	
	/**卡类型*/
	private int cardtype;
	
	/**卡厂商*/
	private int cardoem;
	
	/**物理卡号*/
	private int csn;
	
	/**交易卡号*/
	private String tradecardno;
	
	/**硬件交易流水号*/
	private int tradeseq;
	
	/**SAM卡卡号*/
	private String samno;
	
	/**SAM交易序号*/
	private int samseq;
	
	/**卡交易金额*/
	private String cardtradeamount;
	
	/**交易后余额*/
	private String tradebalance;
	
	/**一卡通数据*/
	private String datahex;

	public PositionInfo getKpositionInfo() {
		return kpositionInfo;
	}

	public void setKpositionInfo(PositionInfo kpositionInfo) {
		this.kpositionInfo = kpositionInfo;
	}

	public PositionInfo getZpositionInfo() {
		return zpositionInfo;
	}

	public void setZpositionInfo(PositionInfo zpositionInfo) {
		this.zpositionInfo = zpositionInfo;
	}

	public int getRunid() {
		return runid;
	}

	public void setRunid(int runid) {
		this.runid = runid;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getEvaluateid() {
		return evaluateid;
	}

	public void setEvaluateid(int evaluateid) {
		this.evaluateid = evaluateid;
	}

	public int getOptions() {
		return options;
	}

	public void setOptions(int options) {
		this.options = options;
	}

	public int getExtend() {
		return extend;
	}

	public void setExtend(int extend) {
		this.extend = extend;
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

	public int getVehicletrips() {
		return vehicletrips;
	}

	public void setVehicletrips(int vehicletrips) {
		this.vehicletrips = vehicletrips;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public int getTradetype() {
		return tradetype;
	}

	public void setTradetype(int tradetype) {
		this.tradetype = tradetype;
	}

	public int getCardtype() {
		return cardtype;
	}

	public void setCardtype(int cardtype) {
		this.cardtype = cardtype;
	}

	public int getCardoem() {
		return cardoem;
	}

	public void setCardoem(int cardoem) {
		this.cardoem = cardoem;
	}

	public int getCsn() {
		return csn;
	}

	public void setCsn(int csn) {
		this.csn = csn;
	}

	public String getTradecardno() {
		return tradecardno;
	}

	public void setTradecardno(String tradecardno) {
		this.tradecardno = tradecardno;
	}

	public int getTradeseq() {
		return tradeseq;
	}

	public void setTradeseq(int tradeseq) {
		this.tradeseq = tradeseq;
	}

	public String getSamno() {
		return samno;
	}

	public void setSamno(String samno) {
		this.samno = samno;
	}

	public int getSamseq() {
		return samseq;
	}

	public void setSamseq(int samseq) {
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

	public String getBlnumber() {
		return blnumber;
	}

	public void setBlnumber(String blnumber) {
		this.blnumber = blnumber;
	}

	public String getDatahex() {
		return datahex;
	}

	public void setDatahex(String datahex) {
		this.datahex = datahex;
	}
	

}
