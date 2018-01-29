/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceArea.java
 * 版本信息：1.0
 * 日期：2015-3-31
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.domain;

/**
 * @项目名称：car-eye
 * @类名称：ServiceArea
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-31 下午02:06:28
 * @修改人：Yuqk
 * @修改时间：2015-3-31 下午02:06:28
 * @修改备注：
 * @version 1.0
 */
public class ServiceArea {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 省编码
	 */
	private Long pcode;
	/**
	 * 市编码
	 */
	private Long ccode;
	/**
	 * 县编码
	 */
	private Long cycode;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 县
	 */
	private String county;
	/**
	 * 开通用户id
	 */
	private Integer userid;
	/**
	 * 开通时间
	 */
	private String createtime;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否开通
	 */
	private Integer isopen;
	/**
	 * 开通用户登录名
	 */
	private String loginname;

	/*
	 * getter setter
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getPcode() {
		return pcode;
	}

	public void setPcode(Long pcode) {
		this.pcode = pcode;
	}

	public Long getCcode() {
		return ccode;
	}

	public void setCcode(Long ccode) {
		this.ccode = ccode;
	}

	public Long getCycode() {
		return cycode;
	}

	public void setCycode(Long cycode) {
		this.cycode = cycode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
}
