/**
* Description: car-eye车辆管理平台
* 文件名：SetParamSet.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：CarType
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午10:51:59
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午10:51:59
 * @修改备注：
 * @version 1.0
 */
public class YangZhaoStand extends BaseDomain  implements  Serializable {
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**id**/
	
	private Integer  id;
    /**标志编号 **/
	private String flagno;
	 /** 84坐标经度**/
	private String lng;
	 /** 84坐标纬度**/
	private String lat;
	 /** 省编码**/
	private String province;
	 /** 市编码**/
	private String city;
	 /** 县编码**/
	private String county;
	 /** 道路名称**/
	private String streetname;
	 /** 位置描述**/
	private String remark;
	 /** 停车位类型**/
	private String stoptype;
	 /** 服务类别**/
	private String servicetype;
	 /** **/
	private String createtime;
	
	
	private String  provincename;
	private String  cityname;
	private String  countyname;
	
	/**
	 * 杨召站名称
	 */
	private String yzstandname;
	
	
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCountyname() {
		return countyname;
	}
	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFlagno() {
		return flagno;
	}
	public void setFlagno(String flagno) {
		this.flagno = flagno;
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
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStoptype() {
		return stoptype;
	}
	public void setStoptype(String stoptype) {
		this.stoptype = stoptype;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getYzstandname() {
		return yzstandname;
	}
	public void setYzstandname(String yzstandname) {
		this.yzstandname = yzstandname;
	}
	
}




