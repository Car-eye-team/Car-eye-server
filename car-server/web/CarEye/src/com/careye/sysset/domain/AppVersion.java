/**
 * Description: car-eye车辆管理平台
 * 文件名：AppType.java
 * 版本信息：1.0
 * 日期：2015-8-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */ 
package com.careye.sysset.domain;

import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;


/**
 * @项目名称：car-eye
 * @类名称：AppType
 * @类描述：
 * @创建人：Administrator 
 * @创建时间：2015-8-20 上午11:44:02
 * @修改人：Administrator
 * @修改时间：2015-8-20 上午11:44:02
 * @修改备注：
 * @version 1.0
 */
public class AppVersion extends BaseDomain{
	private Integer id;
	private Integer typeid;
	private String version;
	private String typename;
	private String versionname;
	private String downloadaddr;
	private String upgradecontent;
	private String createtime;
	private String creater;
	private List<Integer> ids = new ArrayList<Integer>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getVersionname() {
		return versionname;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public String getDownloadaddr() {
		return downloadaddr;
	}
	public void setDownloadaddr(String downloadaddr) {
		this.downloadaddr = downloadaddr;
	}
	public String getUpgradecontent() {
		return upgradecontent;
	}
	public void setUpgradecontent(String upgradecontent) {
		this.upgradecontent = upgradecontent;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
}
