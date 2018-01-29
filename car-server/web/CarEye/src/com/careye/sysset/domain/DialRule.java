/**
 * Description: car-eye车辆管理平台
 * 文件名：DialRule.java
 * 版本信息：1.0
 * 日期：2015-4-17
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.domain;

/**
 * @项目名称：car-eye
 * @类名称：DialRule
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-17 上午09:07:22
 * @修改人：Yuqk
 * @修改时间：2015-4-17 上午09:07:22
 * @修改备注：
 * @version 1.0
 */
public class DialRule {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 调度策略类型
	 */
	private Integer type;
	/**
	 * 半径范围大小
	 */
	private Float radius;
	/**
	 * 车辆类型,以逗号分割的多选
	 */
	private String cartype;
	/**
	 * 选择车辆数量
	 */
	private Integer carcount;
	/**
	 * 车辆状态1未调度2调度中3已调度
	 * 以逗号分割的多选
	 */
	private String carstatus;
	/**
	 * 空重车状态
	 * 以逗号分割的多选
	 */
	private String zkstate;
	/**
	 * 调派轮次
	 */
	private Integer assigncount;
	/**
	 * 调派间隔
	 */
	private Integer assignmin;
	/**
	 * 几分钟后生效
	 */
	private Integer effectmin;
	/**
	 * 生效时间
	 */
	private String effecttime;
	/**
	 * 总调派次数
	 */
	private Integer totalassigncount;
	/**
	 * 提前调派时间
	 */
	private Integer aheadassignmin;
	/**
	 * 即时派送轮数
	 */
	private Integer immassigncount;
	/**
	 * 指派等待时长
	 */
	private Integer assignwaitmin;
	/**
	 * 约车数量
	 */
	private Integer traincount;
	/**
	 * 加入开始时间
	 */
	private String stime;
	/**
	 * 加入结束时间
	 */
	private String etime;
	/**
	 * 优先原则
	 */
	private String principle;
	/**
	 * 用户ID
	 */
	private Integer userid;
	/**
	 * 创建时间
	 */
	private String createtime;
	/**
	 * 搜索起始时间
	 */
	private String searchstime;
	/**
	 * 搜索结束时间
	 */
	private String searchetime;
	/**
	 * 欠费是否可调度
	 */
	private Integer arrearage;
	/**
	 * 违约是否可调度
	 */
	private Integer breach;
	/**
	 * 黑名单是否可调度
	 */
	private Integer blacklist;

	/*
	 * getter setter
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public Float getRadius() {
		return radius;
	}

	public void setRadius(Float radius) {
		this.radius = radius;
	}

	public Integer getCarcount() {
		return carcount;
	}

	public void setCarcount(Integer carcount) {
		this.carcount = carcount;
	}

	public Integer getAssigncount() {
		return assigncount;
	}

	public void setAssigncount(Integer assigncount) {
		this.assigncount = assigncount;
	}

	public Integer getAssignmin() {
		return assignmin;
	}

	public void setAssignmin(Integer assignmin) {
		this.assignmin = assignmin;
	}

	public Integer getEffectmin() {
		return effectmin;
	}

	public void setEffectmin(Integer effectmin) {
		this.effectmin = effectmin;
	}

	public String getEffecttime() {
		return effecttime;
	}

	public void setEffecttime(String effecttime) {
		this.effecttime = effecttime;
	}

	public Integer getTotalassigncount() {
		return totalassigncount;
	}

	public void setTotalassigncount(Integer totalassigncount) {
		this.totalassigncount = totalassigncount;
	}

	public Integer getAheadassignmin() {
		return aheadassignmin;
	}

	public void setAheadassignmin(Integer aheadassignmin) {
		this.aheadassignmin = aheadassignmin;
	}

	public Integer getImmassigncount() {
		return immassigncount;
	}

	public void setImmassigncount(Integer immassigncount) {
		this.immassigncount = immassigncount;
	}

	public Integer getAssignwaitmin() {
		return assignwaitmin;
	}

	public void setAssignwaitmin(Integer assignwaitmin) {
		this.assignwaitmin = assignwaitmin;
	}

	public Integer getTraincount() {
		return traincount;
	}

	public void setTraincount(Integer traincount) {
		this.traincount = traincount;
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

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
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

	public String getSearchstime() {
		return searchstime;
	}

	public void setSearchstime(String searchstime) {
		this.searchstime = searchstime;
	}

	public String getSearchetime() {
		return searchetime;
	}

	public void setSearchetime(String searchetime) {
		this.searchetime = searchetime;
	}

	public Integer getArrearage() {
		return arrearage;
	}

	public void setArrearage(Integer arrearage) {
		this.arrearage = arrearage;
	}

	public Integer getBreach() {
		return breach;
	}

	public void setBreach(Integer breach) {
		this.breach = breach;
	}

	public Integer getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(Integer blacklist) {
		this.blacklist = blacklist;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
	}

	public String getZkstate() {
		return zkstate;
	}

	public void setZkstate(String zkstate) {
		this.zkstate = zkstate;
	}

}
