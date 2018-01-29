/**
* Description: 多森商用车平台
* 文件名：Line.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：FMS
 * @类名称：Line
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-5-27 下午04:19:19
 * @修改人：zr
 * @修改时间：2014-5-27 下午04:19:19
 * @修改备注：
 * @version 1.0
 */
public class Line {
	
	private Integer id;
	
	/**路线ID*/
	private Integer rid;
	
	/**路线属性*/
	private String rattr;
	
	/**起始时间*/
	private String sdate;
	
	/**结束时间*/
	private String edate;
	
	/**路线总拐点数*/
	private Integer icount;
	
	/**拐点ID*/
	private Integer iid;
	
	/**路段ID*/
	private Integer lid;
	
	/**拐点纬度*/
    private double lat;

    /**拐点经度*/
    private double lng;
    
    /**路段宽度*/
    private Integer width;
    
    /**路段属性*/
    private String lattr;
    
    /**路段行驶时长阀值*/
    private Integer rtime;
    
    /**路段行驶不足阀值*/
    private Integer stime;
    
    /**路段最高速度*/
    private String tspeed;
    
    /**路段超速持续时间*/
    private Integer pspeed;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRattr() {
		return rattr;
	}

	public void setRattr(String rattr) {
		this.rattr = rattr;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public Integer getIcount() {
		return icount;
	}

	public void setIcount(Integer icount) {
		this.icount = icount;
	}

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getLattr() {
		return lattr;
	}

	public void setLattr(String lattr) {
		this.lattr = lattr;
	}

	public Integer getRtime() {
		return rtime;
	}

	public void setRtime(Integer rtime) {
		this.rtime = rtime;
	}

	public Integer getStime() {
		return stime;
	}

	public void setStime(Integer stime) {
		this.stime = stime;
	}

	public String getTspeed() {
		return tspeed;
	}

	public void setTspeed(String tspeed) {
		this.tspeed = tspeed;
	}

	public Integer getPspeed() {
		return pspeed;
	}

	public void setPspeed(Integer pspeed) {
		this.pspeed = pspeed;
	}
    

}
