/**
* Description: car-eye车辆管理平台
* 文件名：CommondLog.java
* 版本信息：1.0
* 日期：2015-10-21
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.monitor.domain;

/**
 * @项目名称：DSTAXI
 * @类名称：指令下发日志
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-10-21 下午03:54:28
 * @修改人：zhangrong
 * @修改时间：2015-10-21 下午03:54:28
 * @修改备注：
 * @version 1.0
 */
public class CommondLog {
	
	private String username;
	private Integer blocid;
	private String blocname;
	private String carnumber;
	private String terminal;
	private String stime;
	private String etime;

	private Integer id;
	
	/**车辆id**/
	private Integer carid;
	
	/**协议id**/
	private String msgid;
	
	private Integer userid;
	
	/**下发结果0：成功/确认；1：失败；2：消息有误；3：不支持**/
	private Integer status;
	
	/**流水号**/
	private String seq;
	
	/**下发数据**/
	private String data;
	
	/**备注**/
	private String remark;
	
	/**协议名称**/
	private String msgtype;
	
	/**操作时间**/
	private String createtime;
	
	/**应答时间**/
	private String restime;

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRestime() {
		return restime;
	}

	public void setRestime(String restime) {
		this.restime = restime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	
}
