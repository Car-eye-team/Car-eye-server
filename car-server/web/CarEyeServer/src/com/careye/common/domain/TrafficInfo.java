/**
* Description: 多森商用车平台
* 文件名：TrafficInfo.java
* 版本信息：1.0
* 日期：2014-8-8
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：FMSSERVER
 * @类名称：TrafficInfo
 * @类描述：路况实体
 * @创建人：zr
 * @创建时间：2014-8-8 上午10:18:11
 * @修改人：zr
 * @修改时间：2014-8-8 上午10:18:11
 * @修改备注：
 * @version 1.0
 */
public class TrafficInfo {
	
	private String createUser;
	
	/**
	 * 播报速度
	 */
	private String trafficSpeeds;
	
	/**
	 * 播报单元id
	 */
	private String geoID;
	
	/**
	 * 路况时间，格林威治标准时间GMT
	 */
	private String createTime;
	
	private String geoName;
	
	/**
	 * 播报的内容
	 */
	private String reportText;
	
	private String startTime;
	
	private String endTime;
	
	private String title;
	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getTrafficSpeeds() {
		return trafficSpeeds;
	}

	public void setTrafficSpeeds(String trafficSpeeds) {
		this.trafficSpeeds = trafficSpeeds;
	}

	public String getGeoID() {
		return geoID;
	}

	public void setGeoID(String geoID) {
		this.geoID = geoID;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getGeoName() {
		return geoName;
	}

	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}
	
	
	
}
