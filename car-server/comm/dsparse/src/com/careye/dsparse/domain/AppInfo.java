/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */



package com.careye.dsparse.domain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：AppInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-9-9 下午05:00:32    
 * 修改人：zr    
 * 修改时间：2015-9-9 下午05:00:32    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class AppInfo extends BaseInfo{
	
	/**车辆ID*/
	private int carid;
	
	/**消息类型*/
	private int msgtype;
	
	/**消息内容*/
	private String msgcontent;
	
	/**设备ID*/
	private int terid;
	
	/**充电用户ID*/
	private int userid;
	
	/**业务流水号*/
	private String serialnumber;
	
	/**用户手机号*/
	private String phone;
	
	/**设备号*/
	private String ternumber;
	
	/**设备手机号*/
	private String terphone;
	
	/**授权结果 1 同意 2 拒绝*/
	private int result;
	
	/**充电时长*/
	private String chargetime;
	
	/**充电费用*/
	private String money;
	
	/**开始时间*/
	private String starttime;
	
	/**结束时间*/
	private String endtime;
	
	/**原因*/
	private int reason;
	
	/**订单号*/
	private String orderid;
	
	/**订单状态*/
	private int orderstatus;
	
	/**车牌号*/
	private String carnumber;
	
	/**司机姓名*/
	private String drivername;
	
	/**司机手机号*/
	private String driverphone;
	
	/**时间*/
	private String time; 
	
	/**经度*/
	private int lng;
	
	/**纬度*/
	private int lat;
	
	private int s10;
	private int s11;
	private int s12;
	private int s13;
	private int s14;
	private int s15;
	private int s16;
	private int s17;
	
	
	
	public int getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(int msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}


	public int getTerid() {
		return terid;
	}

	public void setTerid(int terid) {
		this.terid = terid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTernumber() {
		return ternumber;
	}

	public void setTernumber(String ternumber) {
		this.ternumber = ternumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getChargetime() {
		return chargetime;
	}

	public void setChargetime(String chargetime) {
		this.chargetime = chargetime;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
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

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public String getTerphone() {
		return terphone;
	}

	public void setTerphone(String terphone) {
		this.terphone = terphone;
	}

	public int getS10() {
		return s10;
	}

	public void setS10(int s10) {
		this.s10 = s10;
	}

	public int getS11() {
		return s11;
	}

	public void setS11(int s11) {
		this.s11 = s11;
	}

	public int getS12() {
		return s12;
	}

	public void setS12(int s12) {
		this.s12 = s12;
	}

	public int getS13() {
		return s13;
	}

	public void setS13(int s13) {
		this.s13 = s13;
	}

	public int getS14() {
		return s14;
	}

	public void setS14(int s14) {
		this.s14 = s14;
	}

	public int getS15() {
		return s15;
	}

	public void setS15(int s15) {
		this.s15 = s15;
	}

	public int getS16() {
		return s16;
	}

	public void setS16(int s16) {
		this.s16 = s16;
	}

	public int getS17() {
		return s17;
	}

	public void setS17(int s17) {
		this.s17 = s17;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}
	

}
