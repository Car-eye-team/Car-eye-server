/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**
 * 
 * @项目名称：QHFMS
 * @类名称：NotifyingMessage
 * @类描述：告知信息
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午06:07:30
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午06:07:30
 * @修改备注：
 * @version 1.0
 */
public class NotifyingMessage extends  BaseInfo{
	
	/** 订单ID **/
	private String orderid;
	/** 告知时间**/
	private String informtime;
	/** 告知内容**/
	private String informcontent;
	/** 告知人**/
	private String informname;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getInformtime() {
		return informtime;
	}
	public void setInformtime(String informtime) {
		this.informtime = informtime;
	}
	public String getInformcontent() {
		return informcontent;
	}
	public void setInformcontent(String informcontent) {
		this.informcontent = informcontent;
	}
	public String getInformname() {
		return informname;
	}
	public void setInformname(String informname) {
		this.informname = informname;
	}
	
	
}
