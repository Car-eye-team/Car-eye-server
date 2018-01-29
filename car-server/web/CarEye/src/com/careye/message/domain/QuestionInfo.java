/**
* Description: 多森商用车平台
* 文件名：CarDriver.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.domain;

import java.io.Serializable;

/**
 * @项目名称：FMS
 * @类名称：QuestionInfo
 * @类描述：3.3.6	问题表
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class QuestionInfo implements Serializable{
	
	private Integer id;
	
	/**组织机构*/
	private Integer blocid;
	
	/**用户ID*/
	private Integer userid;
	
	/** 紧急 **/
	private Integer emergency;
	
	/** 终端显示器显示 **/
	private Integer lcd;
	
	/** 终端TTS播读 **/
	private Integer tts;
	
	/**广告屏显示 **/
	private Integer adv;
	
	/** 电召消息**/
	private Integer action;
	
	/** 带经纬度 **/
	private Integer dist;
	
	/**问题内容*/
	private String content;
	
	/**创建时间*/
	private String createtime;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	
	
}
