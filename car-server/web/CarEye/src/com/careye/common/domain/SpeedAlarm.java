/**
* Description: car-eye车辆管理平台
* 文件名：SpeedAlarm.java
* 版本信息：1.0
* 日期：2014-6-10
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：FMS
 * @类名称：SpeedAlarm
 * @类描述：超速报警
 * @创建人：zr
 * @创建时间：2014-6-10 上午10:58:30
 * @修改人：zr
 * @修改时间：2014-6-10 上午10:58:30
 * @修改备注：
 * @version 1.0
 */
public class SpeedAlarm {
	
	private Integer id;
	
	/**组织机构**/
	private Integer blocid;
	
	private String blocname;
	
	/**用户id**/
	private Integer userid;
	
	/**终端号码**/
	private String terminal;
	
	/**车牌号**/
	private String carnumber;
	
	/**起始时间**/
	private String stime;
	
	/**结束时间**/
	private String etime;
	
	/**最高速度**/
	private Float maxspeed;
	
	/**超速持续时间**/
	private Integer speedtime;
	
	/**创建时间**/
	private String createtime;
	
	/**类型	1:圆形区域;
			2:矩形区域;
			3:多边形区域;
			4:路段
			**/
	private Integer type;
	
	/**区域或路段ID**/
	private Integer speedAreaId;
	
	/**区域或路段名称**/
	private String speedAreaName;

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

	public Float getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Float maxspeed) {
		this.maxspeed = maxspeed;
	}

	public Integer getSpeedtime() {
		return speedtime;
	}

	public void setSpeedtime(Integer speedtime) {
		this.speedtime = speedtime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSpeedAreaId() {
		return speedAreaId;
	}

	public void setSpeedAreaId(Integer speedAreaId) {
		this.speedAreaId = speedAreaId;
	}

	public String getSpeedAreaName() {
		return speedAreaName;
	}

	public void setSpeedAreaName(String speedAreaName) {
		this.speedAreaName = speedAreaName;
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
