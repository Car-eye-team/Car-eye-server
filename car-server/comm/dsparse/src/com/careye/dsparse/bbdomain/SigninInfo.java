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
 * 类描述：签到信息实体    
 * 创建人：zr    
 * 创建时间：2015-5-14 下午05:43:50    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午05:43:50    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SigninInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String blnumber;
	
	private String carnumber;
	
	/**应答流水号*/
	private int seqR;
	
	/**单位代码*/
	private String companycode;
	
	/**司机代码*/
	private String drivercode;
	
	/**车辆自编号*/
	private String vehicleid;
	
	/**总营运次数*/
	private int count;
	
	/**操作结果*/
	private int result;
	
	/**驾驶员唯一编号*/
	private String driverid;
	
	/**签到时间*/
	private String signintime;
	
	/**脉冲数*/
	private int mcs;
	
	/**上班时间*/
	private String stime;
	
	/**下班时间*/
	private String etime;
	
	/**当班公里*/
	private String dbmileage;
	
	/**当班营运公里*/
	private String dbyymileage;
	
	/**车次*/
	private int vehicletrips;
	
	/**计时时间*/
	private String jstmie;
	
	/**总计金额*/
	private String totalamount;
	
	/**卡收金额*/
	private String cardamount;
	
	/**卡次*/
	private int cardnum;
	
	/**班间公里*/
	private String bjmileage;
	
	/**总计公里*/
	private String totalmileage;
	
	/**总营运公里*/
	private String totalyymileage;
	
	/**单价*/
	private String price;
	
	/**总营运次数*/
	private int totalnumber;
	
	/**总等待时间*/
	private String totalwaittime;
	
	/**位置信息汇报(0x0200)消息体 */
	private PositionInfo positionInfo;


	public String getBlnumber() {
		return blnumber;
	}

	public void setBlnumber(String blnumber) {
		this.blnumber = blnumber;
	}

	public int getMcs() {
		return mcs;
	}

	public void setMcs(int mcs) {
		this.mcs = mcs;
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

	public int getVehicletrips() {
		return vehicletrips;
	}

	public void setVehicletrips(int vehicletrips) {
		this.vehicletrips = vehicletrips;
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

	public int getCardnum() {
		return cardnum;
	}

	public void setCardnum(int cardnum) {
		this.cardnum = cardnum;
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

	public int getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}

	public String getTotalwaittime() {
		return totalwaittime;
	}

	public void setTotalwaittime(String totalwaittime) {
		this.totalwaittime = totalwaittime;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public String getSignintime() {
		return signintime;
	}

	public void setSignintime(String signintime) {
		this.signintime = signintime;
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	


}
