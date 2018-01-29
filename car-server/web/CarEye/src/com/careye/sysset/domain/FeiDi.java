package com.careye.sysset.domain;

import java.io.Serializable;

/**
 * 
 * @项目名称：IMS
 * @类名称：Notice
 * @类描述：公告表
 * @创建人：huangqin
 * @创建时间：2014-12-19 上午09:19:56
 * @修改人：huangqin
 * @修改时间：2014-12-19 上午09:19:56
 * @修改备注：
 * @version 1.0
 */
public class FeiDi implements Serializable{
	/**  主键 **/
	private Integer id;
	/** 下载地址  **/
	private String url;
	/**  内容 **/
	private String content;
	/**  公告类型 **/
	private Integer typeid;
	/**  用户id **/
	private Integer userid;
	/**  创建时间**/
	private String createtime;
	/** 开始时间 **/
	private String stime;
	/** 结束时间**/
	private String etime;
	/** 用户名称 **/
	private String username;
    /** 类型名称 **/
	private String  typename;
	
	private Integer feiDitypeid;
	
	private String oldurl;

	


	public String getOldurl() {
		return oldurl;
	}

	public void setOldurl(String oldurl) {
		this.oldurl = oldurl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getFeiDitypeid() {
		return feiDitypeid;
	}

	public void setFeiDitypeid(Integer feiDitypeid) {
		this.feiDitypeid = feiDitypeid;
	}
	
	
}