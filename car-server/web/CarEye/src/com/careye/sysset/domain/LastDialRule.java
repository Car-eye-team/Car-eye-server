/**
 * Description: car-eye车辆管理平台
 * 文件名：LastDialRule.java
 * 版本信息：1.0
 * 日期：2015-5-22
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.sysset.domain;

/**
 * @项目名称：car-eye
 * @类名称：LastDialRule
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-5-22 下午06:12:09
 * @修改人：Yuqk
 * @修改时间：2015-5-22 下午06:12:09
 * @修改备注：
 * @version 1.0
 */
public class LastDialRule {
	private Integer  id;
	/**
	 * 调度类型
	 */
	private Integer dr_type;
	//jsdd  12个
	/**
	 * 半径范围大小(米)
	 */
	private Float jsdd_radius;
	/**
	 * 车辆类型
	 */
	private String jsdd_cartype;
	/**
	 * 选择车辆数量(辆)
	 */
	private Integer jsdd_carcount;
	/**
	 * 车辆调度状态
	 */
	private String jsdd_carstatus;
	/**
	 * 空重车状态
	 */
	private String jsdd_zkstate;
	/**
	 * 调派轮次(轮)
	 */
	private Integer jsdd_assigncount;
	/**
	 * 调派间隔(秒)
	 */
	private Integer jsdd_assignmin;
	/**
	 * 几分钟后生效(0到10)
	 */
	private Integer jsdd_effectmin;
	/**
	 * 生效时间
	 */
	private String jsdd_effecttime;
	/**
	 * 欠费是否可调度
	 */
	private Integer jsdd_arrearage;
	/**
	 * 违约是否可调度
	 */
	private Integer jsdd_breach;
	/**
	 * 黑名单是否可调度
	 */
	private Integer jsdd_blacklist;
	//13个 +1
	/**
	 * 半径范围大小(米)
	 */	
	private Float yydd_radius;
	/**
	 * 车辆类型
	 */
	private String yydd_cartype;
	/**
	 * 选择车辆数量(辆) 即时订单有效
	 */
	private Integer yydd_carcount;
	/**
	 * 车辆调度状态
	 */
	private String yydd_carstatus;
	/**
	 * 空重车状态
	 */
	private String yydd_zkstate;
	/**
	 * 
	 */
//	private Integer yydd_assigncount;
	/**
	 * 几分钟后生效(0到10)
	 */	
	private Integer yydd_effectmin;
	/**
	 * 生效时间
	 */
	private String yydd_effecttime;
	/**
	 * 总调派次数(次)
	 */
	private Integer yydd_totalassigncount;
	/**
	 * 调派间隔(秒)
	 */
	private Integer yydd_assignmin;
	/**
	 * 提前调派时间(分钟)
	 */
	private Integer yydd_aheadassignmin;
	/**
	 * 即时派送轮数(轮)
	 */
	private Integer yydd_immassigncount;
	/**
	 * 欠费是否可调度
	 */
	private Integer yydd_arrearage;
	/**
	 * 违约是否可调度
	 */
	private Integer yydd_breach;
	/**
	 * 黑名单是否可调度
	 */
	private Integer yydd_blacklist;
	//11个
	/**
	 * 半径范围大小(米)
	 */	
	private Float zpms_radius;
	/**
	 * 选择车辆数量
	 */
	private Integer zpms_carcount;
	/**
	 * 车辆调度状态
	 */
	private String zpms_carstatus;
	/**
	 * 空重车状态
	 */
	private String zpms_zkstate;
	/**
	 * 调派间隔(分钟)
	 */
	private Integer zpms_assignmin;
	/**
	 * 几分钟后生效(0到10)
	 */
	private Integer zpms_effectmin;
	/**
	 * 生效时间
	 */
	private String zpms_effecttime;
	/**
	 * 指派等待时长(分钟)
	 */
	private Integer zpms_assignwaitmin;
	/**
	 * 欠费是否可调度
	 */
	private Integer zpms_arrearage;
	/**
	 * 违约是否可调度
	 */
	private Integer zpms_breach;
	/**
	 * 黑名单是否可调度
	 */	
	private Integer zpms_blacklist;
	//4个
	/**
	 * 约车数量(辆)
	 */
	private Integer zdd_traincount;
	/**
	 * 加入开始时间
	 */
	private String zdd_stime;
	/**
	 * 加入结束时间
	 */
	private String zdd_etime;
	/**
	 * 优先原则
	 */
	private String zdd_principle;
	/**
	 * 生效时间
	 */
	private String effecttime;
	/*
	 * getter setter
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDr_type() {
		return dr_type;
	}
	public void setDr_type(Integer drType) {
		dr_type = drType;
	}
	public Float getJsdd_radius() {
		return jsdd_radius;
	}
	public void setJsdd_radius(Float jsddRadius) {
		jsdd_radius = jsddRadius;
	}
	public String getJsdd_cartype() {
		return jsdd_cartype;
	}
	public void setJsdd_cartype(String jsddCartype) {
		jsdd_cartype = jsddCartype;
	}
	public Integer getJsdd_carcount() {
		return jsdd_carcount;
	}
	public void setJsdd_carcount(Integer jsddCarcount) {
		jsdd_carcount = jsddCarcount;
	}
	public String getJsdd_carstatus() {
		return jsdd_carstatus;
	}
	public void setJsdd_carstatus(String jsddCarstatus) {
		jsdd_carstatus = jsddCarstatus;
	}
	public String getJsdd_zkstate() {
		return jsdd_zkstate;
	}
	public void setJsdd_zkstate(String jsddZkstate) {
		jsdd_zkstate = jsddZkstate;
	}
	public Integer getJsdd_assigncount() {
		return jsdd_assigncount;
	}
	public void setJsdd_assigncount(Integer jsddAssigncount) {
		jsdd_assigncount = jsddAssigncount;
	}
	public Integer getJsdd_assignmin() {
		return jsdd_assignmin;
	}
	public void setJsdd_assignmin(Integer jsddAssignmin) {
		jsdd_assignmin = jsddAssignmin;
	}
	public Integer getJsdd_effectmin() {
		return jsdd_effectmin;
	}
	public void setJsdd_effectmin(Integer jsddEffectmin) {
		jsdd_effectmin = jsddEffectmin;
	}
	public String getJsdd_effecttime() {
		return jsdd_effecttime;
	}
	public void setJsdd_effecttime(String jsddEffecttime) {
		jsdd_effecttime = jsddEffecttime;
	}
	public Integer getJsdd_arrearage() {
		return jsdd_arrearage;
	}
	public void setJsdd_arrearage(Integer jsddArrearage) {
		jsdd_arrearage = jsddArrearage;
	}
	public Integer getJsdd_breach() {
		return jsdd_breach;
	}
	public void setJsdd_breach(Integer jsddBreach) {
		jsdd_breach = jsddBreach;
	}
	public Integer getJsdd_blacklist() {
		return jsdd_blacklist;
	}
	public void setJsdd_blacklist(Integer jsddBlacklist) {
		jsdd_blacklist = jsddBlacklist;
	}
	public Float getYydd_radius() {
		return yydd_radius;
	}
	public void setYydd_radius(Float yyddRadius) {
		yydd_radius = yyddRadius;
	}
	public String getYydd_cartype() {
		return yydd_cartype;
	}
	public void setYydd_cartype(String yyddCartype) {
		yydd_cartype = yyddCartype;
	}
	public Integer getYydd_carcount() {
		return yydd_carcount;
	}
	public void setYydd_carcount(Integer yyddCarcount) {
		yydd_carcount = yyddCarcount;
	}
	public String getYydd_carstatus() {
		return yydd_carstatus;
	}
	public void setYydd_carstatus(String yyddCarstatus) {
		yydd_carstatus = yyddCarstatus;
	}
	public String getYydd_zkstate() {
		return yydd_zkstate;
	}
	public void setYydd_zkstate(String yyddZkstate) {
		yydd_zkstate = yyddZkstate;
	}
	public Integer getYydd_effectmin() {
		return yydd_effectmin;
	}
	public void setYydd_effectmin(Integer yyddEffectmin) {
		yydd_effectmin = yyddEffectmin;
	}
	public String getYydd_effecttime() {
		return yydd_effecttime;
	}
	public void setYydd_effecttime(String yyddEffecttime) {
		yydd_effecttime = yyddEffecttime;
	}
	public Integer getYydd_totalassigncount() {
		return yydd_totalassigncount;
	}
	public void setYydd_totalassigncount(Integer yyddTotalassigncount) {
		yydd_totalassigncount = yyddTotalassigncount;
	}
	public Integer getYydd_aheadassignmin() {
		return yydd_aheadassignmin;
	}
	public void setYydd_aheadassignmin(Integer yyddAheadassignmin) {
		yydd_aheadassignmin = yyddAheadassignmin;
	}
	public Integer getYydd_immassigncount() {
		return yydd_immassigncount;
	}
	public void setYydd_immassigncount(Integer yyddImmassigncount) {
		yydd_immassigncount = yyddImmassigncount;
	}
	public Integer getYydd_arrearage() {
		return yydd_arrearage;
	}
	public void setYydd_arrearage(Integer yyddArrearage) {
		yydd_arrearage = yyddArrearage;
	}
	public Integer getYydd_breach() {
		return yydd_breach;
	}
	public void setYydd_breach(Integer yyddBreach) {
		yydd_breach = yyddBreach;
	}
	public Integer getYydd_blacklist() {
		return yydd_blacklist;
	}
	public void setYydd_blacklist(Integer yyddBlacklist) {
		yydd_blacklist = yyddBlacklist;
	}
	public Float getZpms_radius() {
		return zpms_radius;
	}
	public void setZpms_radius(Float zpmsRadius) {
		zpms_radius = zpmsRadius;
	}
	public Integer getZpms_carcount() {
		return zpms_carcount;
	}
	public void setZpms_carcount(Integer zpmsCarcount) {
		zpms_carcount = zpmsCarcount;
	}
	public String getZpms_carstatus() {
		return zpms_carstatus;
	}
	public void setZpms_carstatus(String zpmsCarstatus) {
		zpms_carstatus = zpmsCarstatus;
	}
	public String getZpms_zkstate() {
		return zpms_zkstate;
	}
	public void setZpms_zkstate(String zpmsZkstate) {
		zpms_zkstate = zpmsZkstate;
	}
	public Integer getZpms_assignmin() {
		return zpms_assignmin;
	}
	public void setZpms_assignmin(Integer zpmsAssignmin) {
		zpms_assignmin = zpmsAssignmin;
	}
	public Integer getZpms_effectmin() {
		return zpms_effectmin;
	}
	public void setZpms_effectmin(Integer zpmsEffectmin) {
		zpms_effectmin = zpmsEffectmin;
	}
	public String getZpms_effecttime() {
		return zpms_effecttime;
	}
	public void setZpms_effecttime(String zpmsEffecttime) {
		zpms_effecttime = zpmsEffecttime;
	}
	public Integer getZpms_assignwaitmin() {
		return zpms_assignwaitmin;
	}
	public void setZpms_assignwaitmin(Integer zpmsAssignwaitmin) {
		zpms_assignwaitmin = zpmsAssignwaitmin;
	}
	public Integer getZpms_arrearage() {
		return zpms_arrearage;
	}
	public void setZpms_arrearage(Integer zpmsArrearage) {
		zpms_arrearage = zpmsArrearage;
	}
	public Integer getZpms_breach() {
		return zpms_breach;
	}
	public void setZpms_breach(Integer zpmsBreach) {
		zpms_breach = zpmsBreach;
	}
	public Integer getZpms_blacklist() {
		return zpms_blacklist;
	}
	public void setZpms_blacklist(Integer zpmsBlacklist) {
		zpms_blacklist = zpmsBlacklist;
	}
	public Integer getZdd_traincount() {
		return zdd_traincount;
	}
	public void setZdd_traincount(Integer zddTraincount) {
		zdd_traincount = zddTraincount;
	}
	public String getZdd_stime() {
		return zdd_stime;
	}
	public void setZdd_stime(String zddStime) {
		zdd_stime = zddStime;
	}
	public String getZdd_etime() {
		return zdd_etime;
	}
	public void setZdd_etime(String zddEtime) {
		zdd_etime = zddEtime;
	}
	public String getZdd_principle() {
		return zdd_principle;
	}
	public void setZdd_principle(String zddPrinciple) {
		zdd_principle = zddPrinciple;
	}
	public Integer getYydd_assignmin() {
		return yydd_assignmin;
	}
	public void setYydd_assignmin(Integer yyddAssignmin) {
		yydd_assignmin = yyddAssignmin;
	}
	public String getEffecttime() {
		return effecttime;
	}
	public void setEffecttime(String effecttime) {
		this.effecttime = effecttime;
	}
}
