/**
* Description: car-eye车辆管理平台
* 文件名：CommondLog.java
* 版本信息：1.0
* 日期：2015-10-21
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：DSTAXI
 * @类名称：上线记录
 * @类描述：HeartRecord
 * @创建人：wuyongde
 * @创建时间：2015-10-21 下午03:54:28
 * @修改人：wuyongde
 * @修改时间：2015-10-21 下午03:54:28
 * @修改备注：
 * @version 1.0
 */
public class HeartRecord {

	private Integer id;
	
	/**车辆id**/
	private Integer carid;
	
	/**上线次数**/
	private Integer inlinecount;
	
	/**下线次数**/
	private Integer offlinecount;
	
	/**操作时间**/
	private String createtime;
	private String today;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getInlinecount() {
		return inlinecount;
	}

	public void setInlinecount(Integer inlinecount) {
		this.inlinecount = inlinecount;
	}

	public Integer getOfflinecount() {
		return offlinecount;
	}

	public void setOfflinecount(Integer offlinecount) {
		this.offlinecount = offlinecount;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	

	
}
