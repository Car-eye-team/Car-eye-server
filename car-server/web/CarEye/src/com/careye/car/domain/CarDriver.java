/**
* Description: car-eye车辆管理平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：FMS
 * @类名称：CarDriver
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class CarDriver implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer carid;
	
	/**组织机构*/
	private Integer blocid;
	/**组织机构*/
	private String blocname;
	
	/**用户ID*/
	private Integer userid;
	
	/**姓名*/
	private String drivername;
	
	/**联系电话*/
	private String tel;
	
	/**手机号*/
	private String phone;
	
	/**性别*/
	private Integer sex;
	
	/**联系地址*/
	private String addr;
	
	/**身份证号*/
	private String idnumber;
	
	/**出生日期*/
	private String birthday;
	
	/**从业资格证*/
	private String qualificationcertificate;
	
	/**发证机构*/
	private String certifyingauthority;
	
	/**驾驶证号*/
	private String drivecrednum;
	
	/**驾驶证年审日期*/
	private String driverannualdate;
	
	/**创建时间*/
	private String createtime;
	
	/**服务监督卡号*/
	private String sscno;
    
	/** 车牌号  **/
	private String carnumber;
	
	/**民族**/
	private String nation;
	
	/**文化程度(1高中2大专3本科4硕士5博士)**/
	private Integer education;
	
	/**政治面貌(1群众2共青团员3中共预备党员4中共党员)**/
	private Integer political;
	
	/**当前状态(1正常2注销)**/
	private Integer nowstatus;
	
	/**司机代码**/
	private String drivercode;
	
	/**--------------------驾驶证信息--------------------------**/
	
	private Integer driverid;
	
	/**驾驶证号**/
	private String drivingnumber;
	
	/**准驾车型**/
	private String zjcartype;
	
	/**驾驶证有效期(年)**/
	private Integer validity;
	
	/**备注**/
	private String remark;
	
	/**初次领证日期**/
	private String firstlztime;
	
	/**发证日期**/
	private String fztime;
	
	public List<Integer> getCarids() {
		return carids;
	}

	public void setCarids(List<Integer> carids) {
		this.carids = carids;
	}

	private List<Integer> carids = new ArrayList<Integer>();
	
	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}


	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
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


	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getQualificationcertificate() {
		return qualificationcertificate;
	}

	public void setQualificationcertificate(String qualificationcertificate) {
		this.qualificationcertificate = qualificationcertificate;
	}

	public String getCertifyingauthority() {
		return certifyingauthority;
	}

	public void setCertifyingauthority(String certifyingauthority) {
		this.certifyingauthority = certifyingauthority;
	}

	public String getDrivecrednum() {
		return drivecrednum;
	}

	public void setDrivecrednum(String drivecrednum) {
		this.drivecrednum = drivecrednum;
	}

	public String getDriverannualdate() {
		return driverannualdate;
	}

	public void setDriverannualdate(String driverannualdate) {
		this.driverannualdate = driverannualdate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getSscno() {
		return sscno;
	}

	public void setSscno(String sscno) {
		this.sscno = sscno;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getPolitical() {
		return political;
	}

	public void setPolitical(Integer political) {
		this.political = political;
	}

	public Integer getNowstatus() {
		return nowstatus;
	}

	public void setNowstatus(Integer nowstatus) {
		this.nowstatus = nowstatus;
	}

	public Integer getDriverid() {
		return driverid;
	}

	public void setDriverid(Integer driverid) {
		this.driverid = driverid;
	}

	public String getDrivingnumber() {
		return drivingnumber;
	}

	public void setDrivingnumber(String drivingnumber) {
		this.drivingnumber = drivingnumber;
	}

	public String getZjcartype() {
		return zjcartype;
	}

	public void setZjcartype(String zjcartype) {
		this.zjcartype = zjcartype;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFirstlztime() {
		return firstlztime;
	}

	public void setFirstlztime(String firstlztime) {
		this.firstlztime = firstlztime;
	}

	public String getFztime() {
		return fztime;
	}

	public void setFztime(String fztime) {
		this.fztime = fztime;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}
	
}
