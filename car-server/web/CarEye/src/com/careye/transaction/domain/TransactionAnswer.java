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
public class TransactionAnswer  extends BaseDomain implements Serializable{
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer carid;
	
	private Integer blocid;
	private Integer preblocid;
	
	/**用户ID*/
	private Integer userid;
	
	/**终端号码*/
	private String terminal;
	
	/**查询密码*/
	private String password;
	
	/**车牌号*/
	private String carnumber;
	
	/**设备手机号*/
	private String phone;
	
	/**省	车辆归属地*/
	private String province;
	private String provincename;
	
	/**市	车辆归属地*/
	private String city;
	private String cityname;
	
	/**县/区	车辆归属地*/
	private String district;
	private String districtname;
	
	/**设备类型*/
	private Integer devicetype;
	
	/**车辆颜色*/
	private String color;
	
	/**车辆类型*/
	private Integer cartype;
	
	/**车辆用途*/
	private Integer caruse;
	private String usename;
	
	/**主驾驶员*/
	private Integer onedriverid;
	
	/**副驾驶员*/
	private Integer twodriverid;
	
	/**燃油种类93#汽油、97#汽油、柴油*/
	private String oiltype;
	
	/**车架号*/
	private String framenumber;
	
	/**行驶证号*/
	private String drivlicnum;
	
	/**变速箱号*/
	private String transnumber;
	
	/**排气量*/
	private String ventingmeasure;
	
	/**发动机号*/
	private String enginenumber;
	
	/**发动机号*/
	private String buytime;
	
	/**发动机号*/
	private String createtime;
	
	/**备注*/
	private String remark;
	
	/**车辆状态**/
	private Integer carstatus;
	private Integer online;
	
	private String stime;
	private String etime;
	
	/**集团名称**/
	private String blocname;
	
	/**设备类型名称**/
	private String typename;
	
	/**车辆类型名称**/
	private String cartypename;
	
	/**主驾驶员**/
	private String drivername1;
	
	/**副驾驶员**/
	private String drivername2;
	
	private String orderid;
    private  String carnumbertwo;
    
    private String sendtime;
    private Integer isbidwin;
/*
 * getter setter
 */
    public String getCarnumbertwo() {
		return carnumbertwo;
	}

	public void setCarnumbertwo(String carnumbertwo) {
		this.carnumbertwo = carnumbertwo;
	}

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

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public Integer getPreblocid() {
		return preblocid;
	}

	public void setPreblocid(Integer preblocid) {
		this.preblocid = preblocid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getCartype() {
		return cartype;
	}

	public void setCartype(Integer cartype) {
		this.cartype = cartype;
	}

	public Integer getCaruse() {
		return caruse;
	}

	public void setCaruse(Integer caruse) {
		this.caruse = caruse;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public Integer getOnedriverid() {
		return onedriverid;
	}

	public void setOnedriverid(Integer onedriverid) {
		this.onedriverid = onedriverid;
	}

	public Integer getTwodriverid() {
		return twodriverid;
	}

	public void setTwodriverid(Integer twodriverid) {
		this.twodriverid = twodriverid;
	}

	public String getOiltype() {
		return oiltype;
	}

	public void setOiltype(String oiltype) {
		this.oiltype = oiltype;
	}

	public String getFramenumber() {
		return framenumber;
	}

	public void setFramenumber(String framenumber) {
		this.framenumber = framenumber;
	}

	public String getDrivlicnum() {
		return drivlicnum;
	}

	public void setDrivlicnum(String drivlicnum) {
		this.drivlicnum = drivlicnum;
	}

	public String getTransnumber() {
		return transnumber;
	}

	public void setTransnumber(String transnumber) {
		this.transnumber = transnumber;
	}

	public String getVentingmeasure() {
		return ventingmeasure;
	}

	public void setVentingmeasure(String ventingmeasure) {
		this.ventingmeasure = ventingmeasure;
	}

	public String getEnginenumber() {
		return enginenumber;
	}

	public void setEnginenumber(String enginenumber) {
		this.enginenumber = enginenumber;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
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

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCartypename() {
		return cartypename;
	}

	public void setCartypename(String cartypename) {
		this.cartypename = cartypename;
	}

	public String getDrivername1() {
		return drivername1;
	}

	public void setDrivername1(String drivername1) {
		this.drivername1 = drivername1;
	}

	public String getDrivername2() {
		return drivername2;
	}

	public void setDrivername2(String drivername2) {
		this.drivername2 = drivername2;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public Integer getIsbidwin() {
		return isbidwin;
	}

	public void setIsbidwin(Integer isbidwin) {
		this.isbidwin = isbidwin;
	}
}
