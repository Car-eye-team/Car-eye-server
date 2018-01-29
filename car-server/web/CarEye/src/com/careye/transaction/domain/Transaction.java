/**
* Description: car-eye车辆管理平台
* 文件名：CustomerInfo.java
* 版本信息：1.0
* 日期：2014-3-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.transaction.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：CustomerInfo
 * @类描述：客户信息
 * @创建人：huangqin
 * @创建时间：2015-3-16 上午10:07:35
 * @修改人：huangqin
 * @修改时间：2015-3-16 上午10:07:35
 * @修改备注：
 * @version 1.0
 */
public class Transaction  extends BaseDomain implements Serializable{
    /**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/** ID **/
    private Integer  id;
    private Integer  t_id;
    /** 客户ID**/
    private Integer  cid;
    /** 业务来源  1 电召 2网站 **/
    private Integer source;
    /** 业务类型  外键（业务类型ID） **/
    private Integer typeid;
    private Integer t_typeid;
    /** 用户召车联系电话 **/
    private String phone;
    private String passengername;
    private String t_phone;
    /** 用户名称**/
    private String usernametwo;
    /** 召车出发地址**/
    private String saddress;
    /** 召车纬度**/
    private String slat;
    /** 召车经度**/
    private String slng;
    /** 用户目的地地址**/
    private String eaddress;
    /** 目的地纬度**/
    private String elat;
    /** 目的地经度**/
    private String elng;
    /** 业务状态  0 无应答 1 调派中 2 已调派 3 取消 4 超时 5 完成**/
    private Integer status;
    /**业务类型   0:即时召车；1:预约召车；2:车辆指派*/
    private Integer tratype;
    /** 抢答成功车辆ID  车辆信息表**/
    private Integer carid;
    /**0 未抢答 1 抢答 默认 0**/
    private Integer resstatus;
    /**  抢答时间   车辆成功抢答时间**/
    private String answertime;
    /**  业务开始时间**/
    private String starttime;
    /** 业务结束时间**/
    private String endtime;
    /**备注 **/
    private String remark;
    private String t_remark;
    /**创建时间 **/
    private String createtime;
    
    /** 登陆名称 **/
    private String loginname;
    /** 业务类型名称**/
    private String typename;
    /** 客户名称 **/
    private String cname;
    /** 车牌号 **/
    private String carnumber;
    /** 订单ID 格式：时间差+5位随机数  1427090421078+76589 **/
	private String orderid;
	/** 性别  乘客性别，男:MALE,女FEMALE **/
	private String sex;
	/** 
	 * 回拨电话	1：回拨乘客电话成功
     *          0：回拨乘客电话失败
	 *  **/
	private Integer callbackphone;
	/** 是否合乘	是否合乘，1合乘，0不合乘  **/
	private Integer carpool;
	/** 合乘人数 **/
	private Integer carpoolpersonnum;
	/** 预约时间	订单类型为1 时 才有预约时间 **/
	private String appointmenttime;
	/** 电召费 **/
	private String callfee;
	
    
    private String  stime1;
    private String  etime1;
    private String  stime2;
    private String  etime2;
    /**
     * 叫车类型
     */
    private Integer calltype;
    /**
     * 是否上车 1未上车2已上车
     */
    private Integer isup;
    /**
     * 用车时间
     */
    private String usetime;
    /**
     * 支付状态 1未支付2已支付
     */
    private Integer pay;
    /**
     * 语音URL路径
     */
    private String filepath;
    
    /** 放主键 **/
	private List<Integer> ids = new ArrayList<Integer>();
	/**
	 * 召车车辆carNumbers,以逗号分隔
	 */
	private String carNumbers;


	/**
	 * drivername  当班司机
	 * @return
	 */
	private String drivername;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getUsernametwo() {
		return usernametwo;
	}

	public void setUsernametwo(String usernametwo) {
		this.usernametwo = usernametwo;
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

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
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

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getStime1() {
		return stime1;
	}

	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}

	public String getEtime1() {
		return etime1;
	}

	public void setEtime1(String etime1) {
		this.etime1 = etime1;
	}

	public String getStime2() {
		return stime2;
	}

	public void setStime2(String stime2) {
		this.stime2 = stime2;
	}

	public String getEtime2() {
		return etime2;
	}

	public void setEtime2(String etime2) {
		this.etime2 = etime2;
	}

	public Integer getT_id() {
		return t_id;
	}

	public void setT_id(Integer tId) {
		t_id = tId;
	}

	public Integer getT_typeid() {
		return t_typeid;
	}

	public void setT_typeid(Integer tTypeid) {
		t_typeid = tTypeid;
	}

	public String getT_phone() {
		return t_phone;
	}

	public void setT_phone(String tPhone) {
		t_phone = tPhone;
	}

	public String getT_remark() {
		return t_remark;
	}

	public void setT_remark(String tRemark) {
		t_remark = tRemark;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getCallbackphone() {
		return callbackphone;
	}

	public void setCallbackphone(Integer callbackphone) {
		this.callbackphone = callbackphone;
	}

	public Integer getCarpool() {
		return carpool;
	}

	public void setCarpool(Integer carpool) {
		this.carpool = carpool;
	}

	public Integer getCarpoolpersonnum() {
		return carpoolpersonnum;
	}

	public void setCarpoolpersonnum(Integer carpoolpersonnum) {
		this.carpoolpersonnum = carpoolpersonnum;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}

	public String getCallfee() {
		return callfee;
	}

	public void setCallfee(String callfee) {
		this.callfee = callfee;
	}

	public Integer getCalltype() {
		return calltype;
	}

	public void setCalltype(Integer calltype) {
		this.calltype = calltype;
	}

	public Integer getIsup() {
		return isup;
	}

	public void setIsup(Integer isup) {
		this.isup = isup;
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getPassengername() {
		return passengername;
	}

	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}

	public String getCarNumbers() {
		return carNumbers;
	}

	public void setCarNumbers(String carNumbers) {
		this.carNumbers = carNumbers;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	
	
}
