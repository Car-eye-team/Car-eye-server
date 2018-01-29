/**
* Description: car-eye车辆管理平台
* 文件名：UserGroupMenu.java
* 版本信息：1.0
* 日期：2014-2-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：BlocAuthority  
 * @类描述：集团权限表 和 集团默认权限表
 * @创建人：huangqin
 * @创建时间：2015-3-5 上午10:25:37
 * @修改人：huangqin
 * @修改时间：2015-3-5 上午10:25:37
 * @修改备注：
 * @version 1.0
 */
public class BlocAuthority  extends BaseDomain  implements  Serializable {
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/** ###############集团权限表#################  **/
	/**id*/
	private Integer id;
	/**集团组ID*/
	private Integer blocgroupid;
	/**菜单ID*/
	private Integer menuid;
	/**创建时间*/
	private String createtime;
	/**用户id*/
	private Integer userid;
	/**类型  1代表集团组分配的 2 代表为用户分配的*/
	private Integer type;
	
	/** ###############集团默认权限表(列  id menuid createtim 集团权限表都有）#################  **/
	
	   
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBlocgroupid() {
		return blocgroupid;
	}
	public void setBlocgroupid(Integer blocgroupid) {
		this.blocgroupid = blocgroupid;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}
