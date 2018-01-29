/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.redis.domain;

import java.io.Serializable;

import com.careye.dsdispatch.constant.BaseInfo;


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
public class CallInfo extends BaseInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
	
	/**招标数据包*/
	private String msgbody;
	
	/**设备类型*/
	private int devicetype;
	
	/**开始地址*/
	private String startaddr;
	
	/**结束地址*/
	private String endaddr;
	
	/**备注*/
	private String remark;
	

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
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

	public String getMsgbody() {
		return msgbody;
	}

	public void setMsgbody(String msgbody) {
		this.msgbody = msgbody;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
