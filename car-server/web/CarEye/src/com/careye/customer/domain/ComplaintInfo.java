package com.careye.customer.domain;

import com.careye.base.action.BaseDomain;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-11 下午01:55:30
 * @修改人：ll
 * @修改时间：2016-5-11 下午01:55:30
 * @修改备注：
 * @version 1.0
 */
public class ComplaintInfo extends BaseDomain{
	
	/****/
	private Integer id;
	
	/**投诉时间**/
	private String complainttime;
	
	/**用户手机号**/
	private String phone;
	
	/**乘客手机号**/
	private String passengerphone;
	
	/**乘客姓名**/
	private String passengername;
	
	/**司机手机号**/
	private String driverphone;
	
	/**司机名称**/
	private String drivername;
	
	/**车牌号**/
	private String carnumber;
	
	/**投诉类型(多个投诉类型用逗号隔开)**/
	private String type;
	
	/**投诉类型名称(多个投诉类型用逗号隔开)**/
	private String typename;
	
	/**投诉描述**/
	private String remark;
	
	/**订单号**/
	private String orderid;
	
	/**处理状态(1未处理 2已处理)**/
	private Integer dealstatus;
	
	/**处理时间**/
	private String dealtime;
	
	/**处理人**/
	private String dealman;
	
	/**处理内容**/
	private String dealcontent;
	
	
	private String createtime;
	

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComplainttime() {
		return complainttime;
	}

	public void setComplainttime(String complainttime) {
		this.complainttime = complainttime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassengerphone() {
		return passengerphone;
	}

	public void setPassengerphone(String passengerphone) {
		this.passengerphone = passengerphone;
	}

	public String getPassengername() {
		return passengername;
	}

	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getDealstatus() {
		return dealstatus;
	}

	public void setDealstatus(Integer dealstatus) {
		this.dealstatus = dealstatus;
	}

	public String getDealtime() {
		return dealtime;
	}

	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}

	public String getDealman() {
		return dealman;
	}

	public void setDealman(String dealman) {
		this.dealman = dealman;
	}

	public String getDealcontent() {
		return dealcontent;
	}

	public void setDealcontent(String dealcontent) {
		this.dealcontent = dealcontent;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	
}
