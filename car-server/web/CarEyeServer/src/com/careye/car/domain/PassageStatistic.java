package com.careye.car.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;


/**
 * 
 * @项目名称：CVPSERVER
 * @类名称：
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2015-9-21 上午10:43:32
 * @修改人：wuyongde
 * @修改时间：2015-9-21 上午10:43:32
 * @修改备注：
 * @version 1.0
 */
public class PassageStatistic extends BaseDomain{

	private Integer id;
	/**终端号码**/
	private String terminal;
	/**车牌号码**/
	private String carnumber;
	/**车辆id**/
	private Integer carid;
	/**人数**/
	private Integer peoplenumber;
	/**经度**/
	private Double lng;
	/**纬度**/
	private Double lat;
	/**计价器业务流水号**/
	private String serialnumber;
	/**地址**/
	private String address;
	/**上报时间**/
	private String uploadtime;
	/**创建时间**/
	private String createtime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
	}
	public Integer getPeoplenumber() {
		return peoplenumber;
	}
	public void setPeoplenumber(Integer peoplenumber) {
		this.peoplenumber = peoplenumber;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
