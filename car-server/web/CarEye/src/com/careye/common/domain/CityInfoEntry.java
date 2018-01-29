package com.careye.common.domain;
/**
 * 
* @项目名称：car-eye
* @类名称：CityInfoEntry
* @类描述：键值对 存储行政编码-行政区域名称
* @创建人：liliang
* @创建时间：2013-8-12 下午08:08:55
* @修改人：liliang
* @修改时间：2013-8-12 下午08:08:55
* @修改备注：
* @version 1.0
 */
public class CityInfoEntry {
	

	private String szcode;
	private String parentszcode;
	private String cnname;
	
	public String getSzcode() {
		return szcode;
	}
	public void setSzcode(String szcode) {
		this.szcode = szcode;
	}
	public String getParentszcode() {
		return parentszcode;
	}
	public void setParentszcode(String parentszcode) {
		this.parentszcode = parentszcode;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	
	
}
