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
 * @类名称：QuestionSendRecord
 * @类描述：3.3.8	问题发送记录表
 * @创建人：lxh
 * @创建时间：2014-5-27 下午01:48:36
 * @修改人：lxh
 * @修改时间：2014-5-27 下午01:48:36
 * @修改备注：
 * @version 1.0
 */
public class QuestionSendRecord implements Serializable{
	
	private Integer id;
	
	/**组织机构*/
	private Integer blocid;
	private String blocname;
	
	/**用户ID*/
	private Integer userid;
	private String username;
	
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

	/** 问题ID **/
	private Integer qid;
	private String question;

	/** 答案ID **/
	private Integer aid;
	private String answer;
	
	/**车牌号*/
	private String carnumber;

	/**问题内容*/
	private String content;
	
	/**创建时间*/
	private String createtime;
	
	private String etime;
	private String stime;

	/**流水号*/
	private Integer seq;

	/**处理结果     1成功 2失败*/
	private Integer result;
	
	/**原始数据包*/
	private String data;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

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

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	
	
}
