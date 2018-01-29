package com.careye.sysset.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 
 * @项目名称：IMS
 * @类名称：NoticeType
 * @类描述：公告类型表
 * @创建人：huangqin
 * @创建时间：2014-12-19 上午09:19:56
 * @修改人：huangqin
 * @修改时间：2014-12-19 上午09:19:56
 * @修改备注：
 * @version 1.0
 */
public class CompanyType  implements Serializable{
	
	
     private Integer id;
	 
	 private String  typename;
	 
	 private String remark;
	 
	 private Integer userid;
	 
	 private String createtime;
	 
	 private String  username;
	 
	 private Integer typeid;

	 public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	private List<String>  ids=new ArrayList<String>();
	 
	 
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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