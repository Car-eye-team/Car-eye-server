/**
* Description: car-eye车辆管理平台
* 文件名：PowerSet.java
* 版本信息：1.0
* 日期：2014-9-18
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

/**
 * @项目名称：FMS
 * @类名称：AlarmmusicSet
 * @类描述：报警声音设置
 * @创建人：zhangrong
 * @创建时间：2014-11-19 上午09:30:07
 * @修改人：zhangrong
 * @修改时间：2014-11-19 上午09:30:07
 * @修改备注：
 * @version 1.0
 */
public class AlarmtypeSet {

	/**id**/
	private Integer id;
	
	/**userid**/
	private Integer userid;
	
	/**登录名**/
	private String loginname;
	
	/**报警类型**/
	private String alarmkey;
	
	/**报警名称**/
	private String alarmname;
	
	/**报警声音路径**/
	private String musicaddr;
	
	private String createtime;
	
	private String remark;

	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	private String oldmusicaddr;
	
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

	public String getAlarmkey() {
		return alarmkey;
	}

	public void setAlarmkey(String alarmkey) {
		this.alarmkey = alarmkey;
	}

	public String getMusicaddr() {
		return musicaddr;
	}

	public void setMusicaddr(String musicaddr) {
		this.musicaddr = musicaddr;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getAlarmname() {
		return alarmname;
	}

	public void setAlarmname(String alarmname) {
		this.alarmname = alarmname;
	}

	public String getOldmusicaddr() {
		return oldmusicaddr;
	}

	public void setOldmusicaddr(String oldmusicaddr) {
		this.oldmusicaddr = oldmusicaddr;
	}

	
	
	
}





