/**
 * Description: 出租车系统
 * 文件名：TransactionInfo.java
 * 版本信息：1.0
 * 日期：2015-3-23
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.car.domain;

/**
 * @项目名称：TAXISERVER
 * @类名称：TransactionInfo
 * @类描述：业务实体
 * @创建人：zr
 * @创建时间：2015-3-23 下午03:15:53
 * @修改人：zr
 * @修改时间：2015-3-23 下午03:15:53
 * @修改备注：
 * @version 1.0
 */
public class TransactionInfo {

	private Integer id;

	/**订单ID*/
	private String orderid;

	/**车辆ID*/
	private Integer carid;
	/**司机代码*/
	private String drivercode;

	/**终端设备号*/
	private String terminal;

	/**车牌号*/
	private String carnumber;

	/**创建时间*/
	private String createtime;

	/**客户ID*/
	private Integer cid;

	/**业务来源	1 电召 2 96106网站 3 飞嘀 4 服务窗*/
	private Integer source;

	/**用户召车联系电话*/
	private String phone;

	/**用户名称*/
	private String username;

	/**召车出发地址*/
	private String saddress;

	/**召车纬度*/
	private Double slat;

	/**召车经度c*/
	private Double slng;

	/**用户目的地地址*/
	private String eaddress;

	/**目的地纬度*/
	private Double elat;

	/**目的地经度*/
	private Double elng;
	
	/**业务状态0 无应答 
	1 调派中 
	2 已调派
	3 取消（司机取消、乘客取消订单时都将该订单处理为取消）
	4 超时 
	5 载客中（司机端点击乘客已上车更改状态） 
	6 待支付（司机端点击到达目的地后变成改状态） 
	7 待评价 (支付完成后的状态变成待评价)
	8 完成（评价完后订单变成完成状态） */
	private Integer status;
	
	/**业务类型	0,即派订单 1，预约订单2，指派订单*/
	private Integer tratype;
	
	/**是否抢答	0 未抢答 1 抢答 默认 0*/
	private Integer resstatus;
	
	/**抢答时间	车辆成功抢答时间*/
	private String answertime;
	
	/**业务开始时间*/
	private String starttime;
	
	/**业务结束时间*/
	private String endtime;
	
	/**电召费*/
	private Float callfee;
	
	/**1：回拨乘客电话成功0：回拨乘客电话失败*/
	private Integer callbackphone;
	private Integer carpoolPersonNum;
	
	/**预约时间	订单类型为1 时 才有预约时间*/
	private String appointmenttime;
	
	
	/**0 乘客取消 1 司机取消**/
	private Integer cancel;
	
	/**中标标志	默认1， 1未中标 2 中标**/
	private Integer zbstatus;
	
	/**用车时间**/
	private String usetime;
	
	/**1 未支付 2 已支付**/
	private Integer pay;
	
	/**0x00：没有接到
	0x01：接到客人，完成电召
	0x02：回拨乘客电话成功
	0x03：回拨乘客电话失败
	0x04：取消电召
	0x05：乘客上车
	0x06：到达目的地
	0x07：乘客已支付**/
	private Integer processstatus;
	
	
	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
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

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
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

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTratype() {
		return tratype;
	}

	public void setTratype(Integer tratype) {
		this.tratype = tratype;
	}

	public Integer getResstatus() {
		return resstatus;
	}

	public void setResstatus(Integer resstatus) {
		this.resstatus = resstatus;
	}

	public String getAnswertime() {
		return answertime;
	}

	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Float getCallfee() {
		return callfee;
	}

	public void setCallfee(Float callfee) {
		this.callfee = callfee;
	}

	public Integer getCallbackphone() {
		return callbackphone;
	}

	public void setCallbackphone(Integer callbackphone) {
		this.callbackphone = callbackphone;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}

	public Integer getZbstatus() {
		return zbstatus;
	}

	public void setZbstatus(Integer zbstatus) {
		this.zbstatus = zbstatus;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public Integer getProcessstatus() {
		return processstatus;
	}

	public void setProcessstatus(Integer processstatus) {
		this.processstatus = processstatus;
	}

	public Integer getCarpoolPersonNum() {
		return carpoolPersonNum;
	}

	public void setCarpoolPersonNum(Integer carpoolPersonNum) {
		this.carpoolPersonNum = carpoolPersonNum;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}
	
	
}
