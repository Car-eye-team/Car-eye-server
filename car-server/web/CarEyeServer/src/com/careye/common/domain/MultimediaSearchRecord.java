/**
* Description: 车队管理系统
* 文件名：MultimediaSearchRecord.java
* 版本信息：1.0
* 日期：2014-6-9
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：FMS
 * @类名称：MultimediaSearchRecord
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2014-6-9 上午10:45:20
 * @修改人：wuyongde
 * @修改时间：2014-6-9 上午10:45:20
 * @修改备注：
 * @version 1.0
 */
public class MultimediaSearchRecord {

	private Integer id;
	
	/**组织机构**/
	private Integer deptid;
	
	private String deptname;
	
	/**用户ID**/
	private Integer userid;
	
	/**终端号码**/
	private String terminal;
	
	/**车id**/
	private Integer carid;
	
	/**原始数据包**/
	private String data;
	
	/**创建时间**/
	private String createtime;
	
	/**通道ID**/
	private Integer accessid;
	
	/**多媒体类型0：图像；1：音频；2：视频**/
	private Integer multimediatype;
	
	/**事件项编码0：平台下发指令；1：定时动作；2：抢劫报警触发；3：碰撞侧翻报警触发；其他保留**/
	private Integer eventcode;
	
	/**起始时间**/
	private String stime;
	
	/**结束时间**/
	private String etime;
	
	/**流水号**/
	private Integer seq;
	
	/**处理结果**/
	private Integer result;
	
	/**类型1检索 2 上传**/
	private Integer type;
	
	/**删除标志 0:保留;1:删除;**/
	private Integer del;

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

	public Integer getAccessid() {
		return accessid;
	}

	public void setAccessid(Integer accessid) {
		this.accessid = accessid;
	}

	public Integer getMultimediatype() {
		return multimediatype;
	}

	public void setMultimediatype(Integer multimediatype) {
		this.multimediatype = multimediatype;
	}

	public Integer getEventcode() {
		return eventcode;
	}

	public void setEventcode(Integer eventcode) {
		this.eventcode = eventcode;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}
	
}
