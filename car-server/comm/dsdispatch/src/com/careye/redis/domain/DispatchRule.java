/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.redis.domain;

import java.io.Serializable;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：DispatchRule    
 * 类描述： 调度规则实体   
 * 创建人：zr    
 * 创建时间：2015-5-22 上午10:48:41    
 * 修改人：zr    
 * 修改时间：2015-5-22 上午10:48:41    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DispatchRule implements Serializable{

	private static final long serialVersionUID = 1L;

	/**调度策略类型 1即时调度2策略预约调度策略3指派模式4再调度*/
	private Integer type;
	
	/**半径范围大小 米*/
	private Integer radius;
	
	/**车辆类型  1.出租车 默认1,可多选逗号隔开*/
	private String cartype;
	
	/**选择车辆数量*/
	private Integer carnum;
	
	/**车辆调度状态 1 未调度 2调度中 3已调度，可多选逗号隔开，如1,2代表选择未调度和调度中*/
	private String carstatus;
	
	/**空重车状态  默认 0（0 空车 1 重车）,可多选逗号隔开*/
	private String zkstate;
	
	/**调派轮次 必须要大于等于1次*/
	private Integer disnumber;
	
	/**调派间隔 （秒）*/
	private Integer dispatchinterval;
	
	/**生效时间 格式“yyyy-MM-dd HH:mm:ss”*/
	private String effecttime;
	
	/**总调派次数 必须要大于等于1次*/
	private Integer countdisnum;
	
	/**提前调派时间 （分钟）*/
	private Integer advancedistime;
	
	/**即时派送轮数*/
	private Integer instantdisnum;
	
	/**指派等待时长（分钟）*/
	private Integer waittime;
	
	/**约车数量*/
	private Integer trainnum;
	
	/**加入开始时间 格式“yyyy-MM-dd HH:mm:ss”*/
	private String stime;
	
	/**加入结束时间 格式“yyyy-MM-dd HH:mm:ss”*/
	private String etime;
	
	/**优先原则*/
	private String principle;
	
	/**欠费 1 可调度 2 不可调度*/
	private Integer arrearage;
	
	/**违约 1 可调度 2 不可调度*/
	private Integer breach;
	
	/**黑名单 1 可调度 2 不可调度*/
	private Integer blacklist;
	
	/**1 正常调整调度规则 2 连续多次调整未生效规则*/
	private Integer opertype;
	
	

	public Integer getOpertype() {
		return opertype;
	}

	public void setOpertype(Integer opertype) {
		this.opertype = opertype;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public Integer getCarnum() {
		return carnum;
	}

	public void setCarnum(Integer carnum) {
		this.carnum = carnum;
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

	public Integer getDisnumber() {
		return disnumber;
	}

	public void setDisnumber(Integer disnumber) {
		this.disnumber = disnumber;
	}

	public Integer getDispatchinterval() {
		return dispatchinterval;
	}

	public void setDispatchinterval(Integer dispatchinterval) {
		this.dispatchinterval = dispatchinterval;
	}

	public String getEffecttime() {
		return effecttime;
	}

	public void setEffecttime(String effecttime) {
		this.effecttime = effecttime;
	}

	public Integer getCountdisnum() {
		return countdisnum;
	}

	public void setCountdisnum(Integer countdisnum) {
		this.countdisnum = countdisnum;
	}

	public Integer getAdvancedistime() {
		return advancedistime;
	}

	public void setAdvancedistime(Integer advancedistime) {
		this.advancedistime = advancedistime;
	}

	public Integer getInstantdisnum() {
		return instantdisnum;
	}

	public void setInstantdisnum(Integer instantdisnum) {
		this.instantdisnum = instantdisnum;
	}

	public Integer getWaittime() {
		return waittime;
	}

	public void setWaittime(Integer waittime) {
		this.waittime = waittime;
	}

	public Integer getTrainnum() {
		return trainnum;
	}

	public void setTrainnum(Integer trainnum) {
		this.trainnum = trainnum;
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
	


	
}
