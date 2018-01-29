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

import com.careye.base.action.BaseDomain;
import com.careye.utils.StringUtils;

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
public class DispatcherRecord extends BaseDomain  implements  Serializable{
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/**  车牌号  **/
	private String carnumber;
	/** 设置状态  1成功  2失败 **/
	private Integer  status;
	/** 创建时间 **/
	private String createtime;
	/** 备注 **/
	private String remark;
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
	
	/** 车牌号id **/
	private Integer carid;
	/** 终端设备号 **/
	private String terminal;
	
	/**流水号**/
	private Integer seq;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getDist() {
		return dist;
	}

	public void setDist(Integer dist) {
		this.dist = dist;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}
