/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：callInfo    
 * 类描述： 电召信息   
 * 创建人：zr    
 * 创建时间：2015-5-22 下午04:21:52    
 * 修改人：zr    
 * 修改时间：2015-5-22 下午04:21:52    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CallInfo extends BaseInfo{

	/**定单号*/
	private String orderid;

	/**定单时间*/
	private String ordertime;

	/**抢答方式*/
	private int answermode;

	/**订单类型*/
	private int ordertype;

	/**抢答电话号码*/
	private String answerphone;

	/**开始纬度*/
	private int startlat;

	/**开始经度*/
	private int startlng;

	/**结束纬度*/
	private int endlat;

	/**结束经度*/
	private int endlng;

	/**调度简短信息*/
	private String contents;

	/**录音文件地址*/
	private String url;

	/**处理结果 1 成功 2 失败*/
	private int result;

	/***电召费*/
	private String callfee;

	/**车牌号*/
	private String carnumber;

	/**乘客姓名*/
	private String passengername;

	/**乘客电话*/
	private String passengerphone;

	/**本机手机号*/
	private String localphone;

	/**出发地*/
	private String startaddr;

	/**目的地*/
	private String endaddr;

	/**取消原因*/
	private int reason;

	/**评价时间*/
	private String evaluationtime;

	/**服务资格证号*/
	private String sqcn;

	/**司机代码*/
	private String drivercode;
	
	/**评价内容*/
	private int econtent;
	
	/**时间*/
	private String time;
	
	/**人数*/
	private int number;
	
	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;
	
	/**计价器业务流水号*/
	private String serialnumber;
	
	/**备注*/
	private String remark;
	
	/**订单完成时间*/
	private String completiontime;
	
	/**位置信息汇报(0x0200)消息体 */
	private PositionInfo positionInfo;
	
	/**订单状态*/
	private int orderstatus;

	/**评价级别*/
	private int level;
	
	/**评价内容*/
	private String content;

	public String getPassengerphone() {
		return passengerphone;
	}

	public void setPassengerphone(String passengerphone) {
		this.passengerphone = passengerphone;
	}

	public String getLocalphone() {
		return localphone;
	}

	public void setLocalphone(String localphone) {
		this.localphone = localphone;
	}

	public String getCallfee() {
		return callfee;
	}

	public void setCallfee(String callfee) {
		this.callfee = callfee;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public int getAnswermode() {
		return answermode;
	}

	public void setAnswermode(int answermode) {
		this.answermode = answermode;
	}

	public int getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
	}

	public String getAnswerphone() {
		return answerphone;
	}

	public void setAnswerphone(String answerphone) {
		this.answerphone = answerphone;
	}

	public int getStartlat() {
		return startlat;
	}

	public void setStartlat(int startlat) {
		this.startlat = startlat;
	}

	public int getStartlng() {
		return startlng;
	}

	public void setStartlng(int startlng) {
		this.startlng = startlng;
	}

	public int getEndlat() {
		return endlat;
	}

	public void setEndlat(int endlat) {
		this.endlat = endlat;
	}

	public int getEndlng() {
		return endlng;
	}

	public void setEndlng(int endlng) {
		this.endlng = endlng;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStartaddr() {
		return startaddr;
	}

	public void setStartaddr(String startaddr) {
		this.startaddr = startaddr;
	}

	public String getEndaddr() {
		return endaddr;
	}

	public void setEndaddr(String endaddr) {
		this.endaddr = endaddr;
	}

	public String getPassengername() {
		return passengername;
	}

	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getEvaluationtime() {
		return evaluationtime;
	}

	public void setEvaluationtime(String evaluationtime) {
		this.evaluationtime = evaluationtime;
	}

	public String getSqcn() {
		return sqcn;
	}

	public void setSqcn(String sqcn) {
		this.sqcn = sqcn;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public int getEcontent() {
		return econtent;
	}

	public void setEcontent(int econtent) {
		this.econtent = econtent;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public String getCompletiontime() {
		return completiontime;
	}

	public void setCompletiontime(String completiontime) {
		this.completiontime = completiontime;
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
