/**
* Description: car-eye车辆管理平台
* 文件名：Menu.java
* 版本信息：1.0
* 日期：2013-9-9
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.domain;


/**
 * @项目名称：car-eye
 * @类名称：SysFunctionMenu
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-5 上午10:25:37
 * @修改人：huangqin
 * @修改时间：2015-3-5 上午10:25:37
 * @修改备注：
 * @version 1.0
 */
public class SysFunctionMenu implements java.io.Serializable {
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**菜单编号*/ 
	private Integer menuid;
	/**父级ID*/ 
	private Integer parentmenuid;
	private String parentmenuname;
	
	/**菜单名称*/ 
	private String menuname;
	/**地址*/ 
	private String menuaddr;
	/**排序号*/ 
	private Integer menusort;
	/**用户ID*/ 
	private Integer userid;
	/**创建时间*/ 
	private String createtime;
	/**菜单具体类型  0树枝 ,1叶子, 2左按钮,3右按钮,4右键 5页面按钮*/ 
	private Integer medetype;
	/**菜单类型 (0-菜单  1-按钮)*/ 
	private Integer menutype;
	/**菜单级别1、2、3、4*/ 
	private Integer menulevel;
	/**是否激活 1 是 0 否*/ 
	private Integer  ifactivate;
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public Integer getParentmenuid() {
		return parentmenuid;
	}
	public void setParentmenuid(Integer parentmenuid) {
		this.parentmenuid = parentmenuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuaddr() {
		return menuaddr;
	}
	public void setMenuaddr(String menuaddr) {
		this.menuaddr = menuaddr;
	}
	public Integer getMenusort() {
		return menusort;
	}
	public void setMenusort(Integer menusort) {
		this.menusort = menusort;
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
	
	public Integer getMedetype() {
		return medetype;
	}
	public void setMedetype(Integer medetype) {
		this.medetype = medetype;
	}
	public Integer getMenutype() {
		return menutype;
	}
	public void setMenutype(Integer menutype) {
		this.menutype = menutype;
	}
	public Integer getMenulevel() {
		return menulevel;
	}
	public void setMenulevel(Integer menulevel) {
		this.menulevel = menulevel;
	}
	public Integer getIfactivate() {
		return ifactivate;
	}
	public void setIfactivate(Integer ifactivate) {
		this.ifactivate = ifactivate;
	}
	public String getParentmenuname() {
		return parentmenuname;
	}
	public void setParentmenuname(String parentmenuname) {
		this.parentmenuname = parentmenuname;
	}
	
	
}
