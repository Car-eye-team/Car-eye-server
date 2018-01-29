/**
 * 
 */
package com.careye.transaction.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class Intercom extends BaseDomain implements Serializable{
	/**ID*/
	private Integer id;
	/**车辆id*/
	private Integer carid;
	/**对讲组id*/
	private Integer gid;
	/**是否为管理员*/
	private Integer isadmin;
	/**车牌*/
	private String carnumber;
	/**组名称*/
	private String name;
	/**描述*/
	private String remark;
	/**创建时间/加入时间*/
	private String createtime;
	/**经度*/
	private String lng;
	/**纬度*/
	private String lat;
	/**地址*/
	private String address;
	/**百度经度*/
	private String blng;
	/**百度纬度*/
	private String blat;
	/**百度地址*/
	private String baddress;
	/**
	 * 省市县
	 */
	private String province;
	private String city;
	private String district;
	
	private String stime;
	private String etime;
	
	private List<String> ids = new ArrayList<String>();
	
	
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
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
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBlng() {
		return blng;
	}
	public void setBlng(String blng) {
		this.blng = blng;
	}
	public String getBlat() {
		return blat;
	}
	public void setBlat(String blat) {
		this.blat = blat;
	}
	public String getBaddress() {
		return baddress;
	}
	public void setBaddress(String baddress) {
		this.baddress = baddress;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	
	
	
	
	
	



}
