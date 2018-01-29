/**
* Description: car-eye车辆管理平台
* 文件名：OrderInfo.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.wx.domain;

/**
 * @项目名称：OCS
 * @类名称：OrderInfo
 * @类描述：
 * @创建人：zr
 * @创建时间：2015-1-20 上午09:41:49
 * @修改人：zr
 * @修改时间：2015-1-20 上午09:41:49
 * @修改备注：
 * @version 1.0
 */
public class OrderInfo {
	
	/**订单ID  3开头乐嘀  5开头微信*/
	private String orderid;
	
	/**乘客名称*/
	private String passengerName;
	
	/**乘客电话*/
	private String telPhone;
	/**约车人电话*/
	private String reservationsTel;
	
	/**性别*/
	private String sex;
	
	/**订单时间*/
	private String orderTime;
	
	/**用车时间 utc时间*/
	private String useTime;
	/**抢答时间 */
	private String answertime;
	
	/**是否合乘，1合乘，0不合乘*/
	private Integer carpool;
	
	/**合乘人数*/
	private Integer carpoolPersonNum;
	
	/**用车地点*/
	private String useaddr;
	
	/**下车地点*/
	private String destinationaddr;
	
	/**出发地纬度*/
	private double slat;
	
	/**出发地经度*/
	private double slng;
	
	/**订单类型 0,即派订单 1，预约订单*/
	private Integer orderTyp;
	
	/**司机姓名**/
	private String vname;
	/**司机电话**/
	private String cellphone;
	
	private Integer id;
	
	
	/**目的地纬度*/
	private double elat;
	
	/**目的地经度*/
	private double elng;
	
	/**订单状态**/
	private Integer orderstatus;
	
	/***费用**/
	private String cost;
	
	private Integer pay;
	
	private String createtime;
	
	private String userid;
	
	private String content;
	private String operattype;
	private String carnumber;
	
	/**车载号码**/
	private String phone;
	
	private String dsex;
	
	/**是否上车	0 默认 1 未上车 2 上车*/
	private Integer isup;
	
	/**支付方式	0 默认 1 支付宝 2 扫描支付 3 现金支付*/
	private Integer paymethods;
	
	/**交易状态	WAIT_BUYER_PAY 交易创建，等待买家付款 TRADE_CLOSED 在指定时间段内未支付时关闭的交易
	 *  在交易完成全额退款成功时关闭的交易。TRADE_SUCCESS交易成功，
	 *  且可对该交易做操作，如：多级分润、退款等 TRADE_PENDING 
	 *   等待卖家收款（买家付款后，如果卖家账号被冻结）TRADE_FINISHED 
	 *   交易成功且结束，即不可再做任何操作*/
	private String tradestatus;
	
	/**商户唯一订单**/
	private String outtradeno; 
	
	/**纬度*/
	private double lat;
	
	/**经度*/
	private double lng;
	
	/**服务监督卡号*/
	private String drivercode;
	
	/**录音文件url*/
	private String url;
	
	/**车辆好评次数*/
	private Integer praise;
	
	/**过程状态
	 * 0x00：没有接到
	0x01：接到客人，完成电召
	0x02：回拨乘客电话成功
	0x03：回拨乘客电话失败
	0x04：取消电召
	0x05：乘客上车
	0x06：到达目的地
	0x07：乘客已支付*/
	private Integer processstatus;
	
	/**
	 * 订单来源类型1 微信公众号 2 app 3网站
	 */
	private Integer sourcetype;
	
	/**是否评价	0 未评价 1 已评价*/
	private Integer isappraise;
	
	/**是否投诉 0 未投诉 1 已投诉*/
	private Integer iscomplaint;
	
	public Integer getPaymethods() {
		return paymethods;
	}

	public void setPaymethods(Integer paymethods) {
		this.paymethods = paymethods;
	}

	public Integer getIsup() {
		return isup;
	}

	public void setIsup(Integer isup) {
		this.isup = isup;
	}

	public String getDsex() {
		return dsex;
	}

	public void setDsex(String dsex) {
		this.dsex = dsex;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public double getSlat() {
		return slat;
	}

	public void setSlat(double slat) {
		this.slat = slat;
	}

	public double getSlng() {
		return slng;
	}

	public void setSlng(double slng) {
		this.slng = slng;
	}

	public double getElat() {
		return elat;
	}

	public void setElat(double elat) {
		this.elat = elat;
	}

	public double getElng() {
		return elng;
	}

	public void setElng(double elng) {
		this.elng = elng;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public Integer getCarpool() {
		return carpool;
	}

	public void setCarpool(Integer carpool) {
		this.carpool = carpool;
	}

	public Integer getCarpoolPersonNum() {
		return carpoolPersonNum;
	}

	public void setCarpoolPersonNum(Integer carpoolPersonNum) {
		this.carpoolPersonNum = carpoolPersonNum;
	}

	public String getUseaddr() {
		return useaddr;
	}

	public void setUseaddr(String useaddr) {
		this.useaddr = useaddr;
	}

	public String getDestinationaddr() {
		return destinationaddr;
	}

	public void setDestinationaddr(String destinationaddr) {
		this.destinationaddr = destinationaddr;
	}

	

	public Integer getOrderTyp() {
		return orderTyp;
	}

	public void setOrderTyp(Integer orderTyp) {
		this.orderTyp = orderTyp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperattype() {
		return operattype;
	}

	public void setOperattype(String operattype) {
		this.operattype = operattype;
	}

	public String getTradestatus() {
		return tradestatus;
	}

	public void setTradestatus(String tradestatus) {
		this.tradestatus = tradestatus;
	}

	public String getOuttradeno() {
		return outtradeno;
	}

	public void setOuttradeno(String outtradeno) {
		this.outtradeno = outtradeno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getProcessstatus() {
		return processstatus;
	}

	public void setProcessstatus(Integer processstatus) {
		this.processstatus = processstatus;
	}

	public String getReservationsTel() {
		return reservationsTel;
	}

	public void setReservationsTel(String reservationsTel) {
		this.reservationsTel = reservationsTel;
	}

	public Integer getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(Integer sourcetype) {
		this.sourcetype = sourcetype;
	}

	public String getAnswertime() {
		return answertime;
	}

	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}

	public Integer getIsappraise() {
		return isappraise;
	}

	public void setIsappraise(Integer isappraise) {
		this.isappraise = isappraise;
	}

	public Integer getIscomplaint() {
		return iscomplaint;
	}

	public void setIscomplaint(Integer iscomplaint) {
		this.iscomplaint = iscomplaint;
	}
	
	
}
