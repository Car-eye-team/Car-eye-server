package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-11 下午04:32:52
 * @修改人：ll
 * @修改时间：2016-5-11 下午04:32:52
 * @修改备注：
 * @version 1.0
 */
public class ComplaintTmp {
	
	private Integer id;
	
	/**车牌号**/
	private String carnumber;
	
	/**司机手机号**/
	private String driverphone;
	
	/**司机名称**/
	private String drivername;
	
	/**处理状态(1未处理 2已处理)**/
	private Integer dealstatus;
	
	/**投诉时间(只显示时分)**/
	private String complainttime;
	
	/**投诉日期**/
	private String complaintdate;

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getComplainttime() {
		return complainttime;
	}

	public void setComplainttime(String complainttime) {
		this.complainttime = complainttime;
	}

	public Integer getDealstatus() {
		return dealstatus;
	}

	public void setDealstatus(Integer dealstatus) {
		this.dealstatus = dealstatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getComplaintdate() {
		return complaintdate;
	}

	public void setComplaintdate(String complaintdate) {
		this.complaintdate = complaintdate;
	}
	
}
