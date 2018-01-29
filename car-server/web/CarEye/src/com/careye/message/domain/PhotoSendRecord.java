/**
* Description: 多森商用车平台
* 文件名：MultiMediaInfo.java
* 版本信息：1.0
* 日期：2014-6-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.domain;

/**
 * @项目名称：FMS
 * @类名称：PhotoSendRecord
 * @类描述：多媒体信息
 * @创建人：zr
 * @创建时间：2014-6-5 下午05:13:16
 * @修改人：zr
 * @修改时间：2014-6-5 下午05:13:16
 * @修改备注：
 * @version 1.0
 */
public class PhotoSendRecord {
	
	private Integer id;
	
	/**组织机构**/
	private Integer blocid;
	
	private String blocname;
	
	/**用户ID**/
	private Integer userid;
	
	/**终端号码**/
	private String terminal;
	
	/**车牌号**/
	private String carnumber;
	private Integer carid;
	
	/**通道ID**/
	private Integer accessid;
	
	/**原始数据包**/
	private String data;
	
	/**创建时间**/
	private String createtime;
	
	/**0表示停止拍摄；0xFFFF表示录像；其他表示拍照张数**/
	private Integer command;
	
	/**秒，0表示按最小间隔拍照或一直录像**/
	private Integer pstime;
	
	/**通道号**/
	private Integer channel;
	
	/**1：保存；0：实时上传**/
	private Integer saveflag;
	
	/**分辨率 1:320*210;
	2:640*480:
	3:800*600;
	4:1024*768;
	5:176*144;[QCIF];
	6:352*288;[CIF];
	7:704*288;[HALF D1];
	8:701*576;[D1];
	**/
	private Integer resolutionratio;
	
	/**图像/视频质量1-10,  1代表质量损失最小，10表示压缩比最大**/
	private Integer picturequality;
	
	/**亮度**/
	private Integer lighteness;
	
	/**对比度**/
	private Integer contrast;
	
	/**饱和度**/
	private Integer saturation;
	
	/**色度**/
	private Integer chroma;
	
	/**流水号**/
	private Integer seq;
	
	/**处理结果**/
	private Integer result;
	
	private String stime;
	
	private String etime;

	
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
	public Integer getAccessid() {
		return accessid;
	}
	public void setAccessid(Integer accessid) {
		this.accessid = accessid;
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
	public Integer getCommand() {
		return command;
	}
	public void setCommand(Integer command) {
		this.command = command;
	}
	public Integer getPstime() {
		return pstime;
	}
	public void setPstime(Integer pstime) {
		this.pstime = pstime;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public Integer getSaveflag() {
		return saveflag;
	}
	public void setSaveflag(Integer saveflag) {
		this.saveflag = saveflag;
	}
	public Integer getResolutionratio() {
		return resolutionratio;
	}
	public void setResolutionratio(Integer resolutionratio) {
		this.resolutionratio = resolutionratio;
	}
	public Integer getPicturequality() {
		return picturequality;
	}
	public void setPicturequality(Integer picturequality) {
		this.picturequality = picturequality;
	}
	public Integer getLighteness() {
		return lighteness;
	}
	public void setLighteness(Integer lighteness) {
		this.lighteness = lighteness;
	}
	public Integer getContrast() {
		return contrast;
	}
	public void setContrast(Integer contrast) {
		this.contrast = contrast;
	}
	public Integer getSaturation() {
		return saturation;
	}
	public void setSaturation(Integer saturation) {
		this.saturation = saturation;
	}
	public Integer getChroma() {
		return chroma;
	}
	public void setChroma(Integer chroma) {
		this.chroma = chroma;
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

	
}
