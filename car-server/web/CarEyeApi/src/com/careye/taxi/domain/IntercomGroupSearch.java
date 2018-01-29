package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-5 下午02:03:36
 * @修改人：ll
 * @修改时间：2016-5-5 下午02:03:36
 * @修改备注：
 * @version 1.0
 */
public class IntercomGroupSearch {
	
	/****/
	private Integer id;
	
	/**车辆id**/
	private Integer carid;
	
	private String carnumber;
	
	/**组名称**/
	private String name;
	
	/**描述**/
	private String remark;
	
	/**创建时间**/
	private String createtime;
	
	/**是否为组成员(0否  1是)**/
	private Integer isMember;
	
	private String lng;
	private String lat;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getIsMember() {
		return isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	
}
