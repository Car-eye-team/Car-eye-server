package com.careye.taxi.domain;

import java.io.Serializable;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-20 下午01:53:08
 * @修改人：ll
 * @修改时间：2015-11-20 下午01:53:08
 * @修改备注：
 * @version 1.0
 */
public class TransactionDetails  implements Serializable{
	
	/** ID **/
    private Integer  id;
    /** 客户ID**/
    private Integer  cid;
    /** 客户名称 **/
    private String cname;
    /** 订单ID 格式：时间差+5位随机数  1427090421078+76589 **/
	private String orderid;
	/**0 未抢答 1 抢答 默认 0**/
    private Integer resstatus;
    /** 业务来源  1 电召 2 96106网站 3 飞嘀 4 服务窗**/
    private Integer source;
    /** 业务类型名称**/
    private String typename;
    /**业务类型   0,即派订单 1，预约订单*/
    private Integer tratype;
    /** 业务状态  0 无应答 1 调派中 2 已调派 3 取消 4 超时 5 完成**/
    private Integer status;
    /** 用户名称**/
    private String usernametwo;
    /** 性别  乘客性别，男:MALE,女FEMALE **/
	private String sex;
	/** 用户召车联系电话 **/
    private String phone;
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
    /** 抢答成功车辆ID  车辆信息表**/
    private Integer carid;
    /** 车牌号 **/
    private String carnumber;
    /**  抢答时间   车辆成功抢答时间**/
    private String answertime;
    /**  业务开始时间**/
    private String starttime;
    /** 业务结束时间**/
    private String endtime;
    /** 
	 * 回拨电话	1：回拨乘客电话成功
     *          0：回拨乘客电话失败
	 *  **/
	private String callbackphone;
	/** 是否合乘	是否合乘，1合乘，0不合乘  **/
	private Integer carpool;
	/** 合乘人数 **/
	private Integer carpoolpersonnum;
	/** 预约时间	订单类型为1 时 才有预约时间 **/
	private String appointmenttime;
	/** 电召费 **/
	private String callfee;
	/**备注 **/
    private String remark;
    /**创建人*/
	private String username;
	/**创建时间 **/
    private String createtime;
    /**语音路径**/
    private String filepath;
    
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
	public Integer getResstatus() {
		return resstatus;
	}
	public void setResstatus(Integer resstatus) {
		this.resstatus = resstatus;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Integer getTratype() {
		return tratype;
	}
	public void setTratype(Integer tratype) {
		this.tratype = tratype;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUsernametwo() {
		return usernametwo;
	}
	public void setUsernametwo(String usernametwo) {
		this.usernametwo = usernametwo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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
	public String getCallbackphone() {
		return callbackphone;
	}
	public void setCallbackphone(String callbackphone) {
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
    
}
