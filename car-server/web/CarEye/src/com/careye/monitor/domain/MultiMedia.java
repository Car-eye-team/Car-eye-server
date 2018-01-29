/**
 * Description: car-eye车辆管理平台
 * 文件名：MultiMedia.java
 * 版本信息：1.0
 * 日期：2015-3-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.monitor.domain;

/**
 * @项目名称：car-eye
 * @类名称：MultiMedia
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-26 下午03:22:34
 * @修改人：Yuqk
 * @修改时间：2015-3-26 下午03:22:34
 * @修改备注：
 * @version 1.0
 */
public class MultiMedia {
	private Integer id;
	private Integer carid;

	/** 组织机构 **/
	private Integer blocid;

	private String blocname;

	/** 用户ID **/
	private Integer userid;

	/** 终端号码 **/
	private String terminal;

	/** 车牌号 **/
	private String carnumber;

	/** 多媒体数据ID **/
	private Integer dataid;

	/** 多媒体类型 0：图像；1：音频；2：视频 **/
	private Integer mediatype;

	/** 多媒体格式编码 0：JPEG；1：TIF；2：MP3；3：WAV；4：WMV；其他保留 **/
	private Integer format;

	/** 事件项编码 0：平台下发指令；
	1：定时动作；
	2：抢劫报警触发；
	3：碰撞侧翻报警触发； 
	4：司机上班签到
	5：司机下班签退
	6: 空车转重车
	7：重车转空车**/
	private Integer eventcode;
	
	
	/**
	 * word导出时  显示
	 */
	private String eventcodeStr;

	/** 通道ID **/
	private Integer accessid;

	/** 摄像头立即拍摄记录表ID **/
	private Integer msid;

	/** 多媒体数据保存路径 **/
	private String multimediapath;

	/** 原始数据包 **/
	private String data;

	/** 创建时间 **/
	private String createtime;
	/**
	 * 媒体id 
	 */
	private Integer mediaid;

	private String stime;

	private String etime;

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

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getDataid() {
		return dataid;
	}

	public void setDataid(Integer dataid) {
		this.dataid = dataid;
	}

	public Integer getMediatype() {
		return mediatype;
	}

	public void setMediatype(Integer mediatype) {
		this.mediatype = mediatype;
	}

	public Integer getFormat() {
		return format;
	}

	public void setFormat(Integer format) {
		this.format = format;
	}

	public Integer getEventcode() {
		return eventcode;
	}

	public void setEventcode(Integer eventcode) {
		this.eventcode = eventcode;
	}

	public Integer getAccessid() {
		return accessid;
	}

	public void setAccessid(Integer accessid) {
		this.accessid = accessid;
	}

	public Integer getMsid() {
		return msid;
	}

	public void setMsid(Integer msid) {
		this.msid = msid;
	}

	public String getMultimediapath() {
		return multimediapath;
	}

	public void setMultimediapath(String multimediapath) {
		this.multimediapath = multimediapath;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getMediaid() {
		return mediaid;
	}

	public void setMediaid(Integer mediaid) {
		this.mediaid = mediaid;
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

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getEventcodeStr() {
		return eventcodeStr;
	}

	public void setEventcodeStr(String eventcodeStr) {
		this.eventcodeStr = eventcodeStr;
	}
	

}
