/**
* Description: car-eye车辆管理平台
* 文件名：CommondLog.java
* 版本信息：1.0
* 日期：2015-10-21
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.common.domain;

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

	private Integer id;
	
	/**车辆id**/
	private Integer carid;
	
	/**协议id**/
	private Integer msgid;
	
	private Integer userid;
	
	/**下发结果0：成功/确认；1：失败；2：消息有误；3：不支持**/
	private Integer status;
	
	/**流水号**/
	private Integer seq;
	
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

	public Integer getMsgid() {
		return msgid;
	}

	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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
	
	
	
}
