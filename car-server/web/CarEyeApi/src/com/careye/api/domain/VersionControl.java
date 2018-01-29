package com.careye.api.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @项目名称：APP
 * @类名称：VersionControl
 * @类描述：
 * @创建人：
 * @创建时间：2014-11-26 下午01:22:56
 * @修改人：
 * @修改时间：2013-9-28 下午01:22:56
 * @修改备注：
 * @version 1.0
 */
public class VersionControl {
	/** ID 主键  **/
	private Integer id;
	/** 版本号 **/
	private String version;
	/**  APP版本类型 **/
	private Integer typeid;
	/** 版本名称  **/
	private String versionname;
	/** 下载地址  **/
	private String downloadaddr;
	/**  版本升级内容 **/
	private String upgradecontent;
	/** 用户id  **/
	private Integer userid;
	/**  操作时间 **/
	private String createtime;
	
	
	private String typename;
	
	private  String stime;
	
	private  String etime;
	
	private  String username;
    
	private  String olddownloadaddr;
	
	/** 放主键 **/
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public String getOlddownloadaddr() {
		return olddownloadaddr;
	}

	public void setOlddownloadaddr(String olddownloadaddr) {
		this.olddownloadaddr = olddownloadaddr;
	}
}