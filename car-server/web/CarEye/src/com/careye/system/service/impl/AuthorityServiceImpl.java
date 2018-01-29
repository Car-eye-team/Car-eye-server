/**
* Description: car-eye车辆管理平台
* 文件名：AuthorityServiceImpl.java
* 版本信息：1.0
* 日期：2014-2-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.service.impl;

import java.util.List;

import com.careye.base.service.GenericService;
import com.careye.system.domain.BlocAuthority;
import com.careye.system.service.AuthorityService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：car-eyeTms
 * @类名称：AuthorityServiceImpl
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2014-2-27 下午01:51:21
 * @修改人：Administrator
 * @修改时间：2014-2-27 下午01:51:21
 * @修改备注：
 * @version 1.0
 */
public class AuthorityServiceImpl extends GenericService implements AuthorityService {

	/**
	 * 删除用户组下面所有菜单
	 * @param usergroupid
	 * @return
	 */
	public int delUserGroupMenu(int usergroupid){
		return this.baseDao.delete("oracle-authoritySQL.delUserGroupMenu", usergroupid);
	}

	/**
	 * 删除默认权限表记录
	 * @return
	 */
	public int delAuthorityMenu(){
		return this.baseDao.delete("oracle-authoritySQL.delAuthorityMenu", null);
	}
	
	/**
	 * 添加用户组权限
	 */
	public int addUserGroupMenu(List<BlocAuthority> list){
		return this.baseDao.save("oracle-authoritySQL.addUserGroupMenu", list) ;
	}
	
	/**
	 * 设置默认权限权限
	 */
	public int addAuthorityMenu(List<BlocAuthority> list){
		return this.baseDao.save("oracle-authoritySQL.addAuthorityMenu", list) ;
	}
	
	/**
	 * 所有菜单列表
	 */
	public List allMenuList(){
		return this.baseDao.queryForList("oracle-authoritySQL.allMenuList");
	}
	
	/**得到子节点的menuid**/
	public List getChildrenMenuId(int menuid){
		return this.baseDao.queryForList("oracle-authoritySQL.getChildrenMenuId",menuid);
	}
	
	/**得到用户组的菜单id**/
	public List getUserGroupMenuId(int usergroupid){
		return this.baseDao.queryForList("oracle-authoritySQL.getUserGroupMenuId",usergroupid);
	}
	
	/**得到默认权限菜单id**/
	public List getAuthorityMenuId(){
		return this.baseDao.queryForList("oracle-authoritySQL.getAuthorityMenuId",null);
	}
	
	/**按钮菜单列表**/
	public List getMenuidList(Integer usergroupid){
		return this.baseDao.queryForList("oracle-authoritySQL.getMenuidList",usergroupid);
	}
	
}





