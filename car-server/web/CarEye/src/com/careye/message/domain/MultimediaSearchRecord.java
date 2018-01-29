/**
* Description: 多森商用车平台
* 文件名：MultimediaSearchRecord.java
* 版本信息：1.0
* 日期：2014-6-9
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.domain;

/**
 * @项目名称：FMS
 * @类名称：MultimediaSearchRecord
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-6-9 上午10:45:20
 * @修改人：zhangrong
 * @修改时间：2014-6-9 上午10:45:20
 * @修改备注：
 * @version 1.0
 */
public class MultimediaSearchRecord {

	private Integer id;
	
	/**组织机构**/
	private Integer blocid;
	
	private String blocname;
	
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
	private String carnumber;
	
	/**通道ID2 ： 通道2   
	1：  通道1 **/
	private Integer accessid;
	
	/**多媒体类型0：图像；1：音频；2：视频**/
	private Integer multimediatype;
	
	/**0：平台下发指令；
	1：定时动作；
	2：抢劫报警触发；
	3：碰撞侧翻报警触发；
	4：进入重车拍照（抬表）；
	5：进入空车拍照（压表）；
	6：服务评价拍照；
	其他保留**/
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

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	
}
