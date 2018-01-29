/**
* Description: car-eye车辆管理平台
* 文件名：UserService.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.service;

import java.util.List;

import com.careye.system.domain.BlocAuthority;


/**
 * @项目名称：car-eyeTms
 * @类名称：AuthorityService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-2-27 上午09:19:18
 * @修改人：zhangrong
 * @修改时间：2014-2-27 上午09:19:18
 * @修改备注：
 * @version 1.0
 */
public interface AuthorityService{
	
	/**
	 * 删除用户组下面所有菜单
	 * @param usergroupid
	 * @return
	 */
	public int delUserGroupMenu(int usergroupid);
	/**
	 * 删除默认权限表记录
	 * @return
	 */
	public int delAuthorityMenu();
	
	/**
	 * 添加用户组权限
	 */
	public int addUserGroupMenu(List<BlocAuthority> list);
	/**
	 * 设置默认权限权限
	 */
	public int addAuthorityMenu(List<BlocAuthority> list);
	
	/**
	 * 所有菜单列表
	 */
	public List allMenuList();
	
	/**得到子节点的menuid**/
	public List getChildrenMenuId(int menuid);
	
	/**得到用户组的菜单id**/
	public List getUserGroupMenuId(int usergroupid);
	
	/**得到默认权限菜单id**/
	public List getAuthorityMenuId();
	
	/**按钮菜单列表**/
	public List getMenuidList(Integer usergroupid);
	
}






