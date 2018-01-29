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
 * @类名称：SoundRecord
 * @类描述：3.6.3	录音发送记录表
 * @创建人：zhangrong
 * @创建时间：2014-6-9 下午05:13:16
 * @修改人：zhangrong
 * @修改时间：2014-6-9 下午05:13:16
 * @修改备注：
 * @version 1.0
 */
public class SoundRecord {
	
	private Integer id;
	
	/**组织机构**/
	private Integer deptid;
	
	private String deptname;
	
	/**用户ID**/
	private Integer userid;
	
	/**终端号码**/
	private String terminal;
	
	/**车牌号**/
	private String carnumber;
	
	/**原始数据包**/
	private String data;
	
	/**创建时间**/
	private String createtime;
	
	/**0：停止录音；0x01:开始录音**/
	private Integer command;
	
	/**录音时间**/
	private Integer srtime;
	
	/**保存标志**/
	private Integer saveflag;
	
	/**音频采样率 1:320*210;0：8K；1:11K；2:23K；3：32K**/
	private Integer samplingrate;
	
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

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
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

	public Integer getSrtime() {
		return srtime;
	}

	public void setSrtime(Integer srtime) {
		this.srtime = srtime;
	}

	public Integer getSaveflag() {
		return saveflag;
	}

	public void setSaveflag(Integer saveflag) {
		this.saveflag = saveflag;
	}

	public Integer getSamplingrate() {
		return samplingrate;
	}

	public void setSamplingrate(Integer samplingrate) {
		this.samplingrate = samplingrate;
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
	
	

	
}
