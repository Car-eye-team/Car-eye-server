package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-4-13 上午10:17:05
 * @修改人：ll
 * @修改时间：2016-4-13 上午10:17:05
 * @修改备注：
 * @version 1.0
 */
public class ShunfengPassengers {
	
	/****/
	private Integer id;
	
	/**拼车序号**/
	private Integer seq;
	
	/**备注**/
	private String remark;
	
	/**订单号(订单表订单号)**/
	private String orderid;
	
	/**起点**/
	private String saddress;
	
	/**起点纬度**/
	private String slat;
	
	/**起点经度**/
	private String slng;
	
	/**终点**/
	private String eaddress;
	
	/**终点纬度**/
	private String elat;
	
	/**终点经度**/
	private String elng;
	
	/**开始时间**/
	private String stime;
	
	/**结束时间**/
	private String etime;
	
	/**总行车里程**/
	private String summile;
	
	/**总费用**/
	private String totalfee;
	
	/**订单状态(1进行中、2完成)**/
	private Integer ordersatus;
	
	/**创建时间**/
	private String createtime;
	
	/**乘客姓名**/
	private String passagename;
	
	/**联系电话**/
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

	public String getSlat() {
		return slat;
	}

	public void setSlat(String slat) {
		this.slat = slat;
	}

	public String getSlng() {
		return slng;
	}

	public void setSlng(String slng) {
		this.slng = slng;
	}

	public String getElat() {
		return elat;
	}

	public void setElat(String elat) {
		this.elat = elat;
	}

	public String getElng() {
		return elng;
	}

	public void setElng(String elng) {
		this.elng = elng;
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

	public String getSummile() {
		return summile;
	}

	public void setSummile(String summile) {
		this.summile = summile;
	}

	public String getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}

	public Integer getOrdersatus() {
		return ordersatus;
	}

	public void setOrdersatus(Integer ordersatus) {
		this.ordersatus = ordersatus;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPassagename() {
		return passagename;
	}

	public void setPassagename(String passagename) {
		this.passagename = passagename;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
