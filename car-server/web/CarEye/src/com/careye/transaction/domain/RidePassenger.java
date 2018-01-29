/**
 * 
 */
package com.careye.transaction.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class RidePassenger extends BaseDomain implements Serializable{
	
	private Integer id;    //ID
	private Integer seq;  //拼车序号
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
	
	private String sumtime;  //总用车时长
	 
	private String passagename;  //乘客姓名
	private String phone;		//电话
	
	private String carid;
	private String carnumber;
	
	private List<String> ids = new ArrayList<String>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSumtime() {
		return sumtime;
	}

	public void setSumtime(String sumtime) {
		this.sumtime = sumtime;
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

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	
	
	

}
