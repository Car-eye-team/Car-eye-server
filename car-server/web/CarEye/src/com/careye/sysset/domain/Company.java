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
public class Company implements Serializable{
     
     private Integer id;
	 
	 private Integer typeid;
	 
	 private String companyname;
	 
	 private String  content;
	 
	 private String typename;
	 
	 
	 
	 public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getContentshort() {
		return contentshort;
	}

	public void setContentshort(String contentshort) {
		this.contentshort = contentshort;
	}

	private String  contentshort;
	 
	 private Integer userid;
	 
	 private String  createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	 
	 
	 
	 
	
}