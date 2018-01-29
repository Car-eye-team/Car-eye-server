/**
 * 
 */
package com.careye.transaction.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class RideOrder extends BaseDomain implements Serializable {
	
	private Integer id;    //ID
	private Integer carid;   //车辆id
	private String remark;  //备注
	private String orderid;  //订单号
	private String saddress;  //起点
	private String slat;  //起点纬度
	private String slng;  //起点经度
	private String eaddress;  //终点
	private String elat;  //终点纬度
	private String elng;  //终点经度
	private String stime;  //开始时间
	private String etime;  //结束时间
	private String summile;  //总行车里程
	private String totalfee;  //总费用
	private Integer ordersatus;  //订单状态
	private String createtime;  //创建时间
	
	private Integer blocid;  //集团id
	private String bloc_name; //集团名称
	private String carnumber;  //车牌号
	private String devicenumber; //设备号
	private String terminal;//设备号
	private String sumtime;  //总用车时长
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
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
	public String getEaddress() {
		return eaddress;
	}
	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
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
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getBloc_name() {
		return bloc_name;
	}
	public void setBloc_name(String blocName) {
		bloc_name = blocName;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getSumtime() {
		return sumtime;
	}
	public void setSumtime(String sumtime) {
		this.sumtime = sumtime;
	}
	public String getDevicenumber() {
		return devicenumber;
	}
	public void setDevicenumber(String devicenumber) {
		this.devicenumber = devicenumber;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	
	

}
