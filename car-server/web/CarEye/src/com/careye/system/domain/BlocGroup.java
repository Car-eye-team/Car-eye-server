/**
* Description: car-eye车辆管理平台
* 文件名：UserGroup.java
* 版本信息：1.0
* 日期：2014-2-18
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：BlocGroup
 * @类描述：集团组表
 * @创建人：huangqin
 * @创建时间：2015-3-5 上午10:25:37
 * @修改人：huangqin
 * @修改时间：2015-3-5 上午10:25:37
 * @修改备注：
 * @version 1.0
 */
public class BlocGroup extends BaseDomain  implements  Serializable {

	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**ID*/
	private Integer id;
	/**集团组名称*/
	private String blocgroupname;
	/**集团组描述*/
	private String blocgroupdesc;
	/**用户ID*/
	private Integer userid;
	/**创建时间*/
	private String createtime;
	
	/** 集团组id **/
	private Integer blocgroupid;
	
	private Integer blocid;
	private String blocname;
	private String username;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBlocgroupname() {
		return blocgroupname;
	}
	public void setBlocgroupname(String blocgroupname) {
		this.blocgroupname = blocgroupname;
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
	public Integer getBlocgroupid() {
		return blocgroupid;
	}
	public void setBlocgroupid(Integer blocgroupid) {
		this.blocgroupid = blocgroupid;
	}
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBlocgroupdesc() {
		return blocgroupdesc;
	}
	public void setBlocgroupdesc(String blocgroupdesc) {
		this.blocgroupdesc = blocgroupdesc;
	}
	
}
