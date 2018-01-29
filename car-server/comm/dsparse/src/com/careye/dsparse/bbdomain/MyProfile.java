/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**
 * 
 * @项目名称：dsparsed
 * @类名称：MyProfile
 * @类描述：我的资料
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午06:22:15
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午06:22:15
 * @修改备注：
 * @version 1.0
 */
public class MyProfile extends  BaseInfo{
   
	/**车牌号 **/
	private String carnumber; 
	/**车辆归属地**/
	private String attribution; 
	/**车体状况 **/
	private String carstatus; 
	/**外形 **/
	private String shape; 
	/** 载重**/
	private String load; 
	/** 行驶证号**/
	private String drivlicnum; 
	/** 行驶公里数**/
	private String kilometers; 
	/** GPS**/
	private String gps; 
	/** 上次上报险时间**/
	private String insurancetime; 
	/** 是否带车载终端**/
	private String vehicleterminal; 
	/** 联系人**/
	private String name; 
	/** 固话**/
	private String tel; 
	/** 手机**/
	private String phone;
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getAttribution() {
		return attribution;
	}
	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	public String getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getLoad() {
		return load;
	}
	public void setLoad(String load) {
		this.load = load;
	}
	public String getDrivlicnum() {
		return drivlicnum;
	}
	public void setDrivlicnum(String drivlicnum) {
		this.drivlicnum = drivlicnum;
	}
	public String getKilometers() {
		return kilometers;
	}
	public void setKilometers(String kilometers) {
		this.kilometers = kilometers;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getInsurancetime() {
		return insurancetime;
	}
	public void setInsurancetime(String insurancetime) {
		this.insurancetime = insurancetime;
	}
	public String getVehicleterminal() {
		return vehicleterminal;
	}
	public void setVehicleterminal(String vehicleterminal) {
		this.vehicleterminal = vehicleterminal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	} 
	
}
