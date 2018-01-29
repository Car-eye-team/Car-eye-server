/**
* Description: 车队管理系统
* 文件名：PoiInfo.java
* 版本信息：1.0
* 日期：2014-6-4
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：FMS
 * @类名称：PoiInfo
 * @类描述：POI信息实体
 * @创建人：zr
 * @创建时间：2014-6-4 上午11:46:12
 * @修改人：zr
 * @修改时间：2014-6-4 上午11:46:12
 * @修改备注：
 * @version 1.0
 */
public class PoiInfo {

	private Integer id;
	
	/**组织机构*/
	private Integer deptid;
	
	/**组织机构名称*/
	private String deptname;
	
	/**用户ID*/
	private Integer userid;
	
	/**经度*/
	private double lng;
	
	/**纬度*/
	private double lat;
	
	/**Poi信息点名称*/
	private String poiname;
	
	/**车牌号*/
	private String carnumber;
	
	/**流水号*/
	private Integer seq;
	
	/**处理结果	1成功 2失败*/
	private Integer result;
	
	/**创建时间*/
	private String createtime;
	
	/**原始数据包*/
	private String datapacket;
	
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getPoiname() {
		return poiname;
	}

	public void setPoiname(String poiname) {
		this.poiname = poiname;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDatapacket() {
		return datapacket;
	}

	public void setDatapacket(String datapacket) {
		this.datapacket = datapacket;
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

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	
	
}
