/**
* Description: car-eye车辆管理平台
* 文件名：DrivingState.java
* 版本信息：1.0
* 日期：2015-3-23
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.monitor.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：DispatcherRecord
 * @类描述：
 * @创建人：haungqin
 * @创建时间：2015-3-25 下午08:18:50
 * @修改人：haungqin
 * @修改时间：2015-3-25 下午08:18:50
 * @修改备注：
 * @version 1.0
 */
public class DispatcherInfo extends BaseDomain  implements  Serializable{
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/**  调度类型  1、终端接收TTS语音播报调度信息。
   2、终端接收只显示不播报的调度信息。  **/
	private String type;
	/** 紧急  0否 1是**/
	private Integer  emergency;
	/** 终端显示器显示  0否 1是**/
	private Integer  lcd;
	/** 终端TTS播读 0否 1是**/
	private Integer  tts;
	/** 广告屏显示 0否 1是**/
	private Integer  adv;
	/** 电召消息 0否 1是**/
	private Integer  action;
	
	/** 经纬度 **/
	private Integer dist;
	
	public Integer getDist() {
		return dist;
	}
	public void setDist(Integer dist) {
		this.dist = dist;
	}
	/** 是否预设  0否 1是**/
	private Integer  flag;
	
	/** 创建时间 **/
	private String createtime;
	/** 调度内容 **/
	private String remark;
	
	private List<Integer> carids=new ArrayList<Integer>();
	
	private String caridsStr;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getEmergency() {
		return emergency;
	}
	public void setEmergency(Integer emergency) {
		this.emergency = emergency;
	}
	public Integer getLcd() {
		return lcd;
	}
	public void setLcd(Integer lcd) {
		this.lcd = lcd;
	}
	public Integer getTts() {
		return tts;
	}
	public void setTts(Integer tts) {
		this.tts = tts;
	}
	public Integer getAdv() {
		return adv;
	}
	public void setAdv(Integer adv) {
		this.adv = adv;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
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
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public List<Integer> getCarids() {
		return carids;
	}
	public void setCarids(List<Integer> carids) {
		this.carids = carids;
	}
	public String getCaridsStr() {
		return caridsStr;
	}
	public void setCaridsStr(String caridsStr) {
		this.caridsStr = caridsStr;
	}
}
