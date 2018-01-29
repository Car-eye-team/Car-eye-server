/**
* Description: car-eye车辆管理平台
* 文件名：SystemManageService.java
* 版本信息：1.0
* 日期：2014-2-18
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.service;

import java.util.List;
import java.util.Map;

import com.careye.system.domain.BlocGroup;


/**
 * @项目名称：car-eyeTms
 * @类名称：SystemManageService
 * @类描述：系统管理
 * @创建人：zr
 * @创建时间：2014-2-18 下午01:55:13
 * @修改人：zr
 * @修改时间：2014-2-18 下午01:55:13
 * @修改备注：
 * @version 1.0
 */
public interface UserGroupService {
	
	/**
	 * 根据用户组得到部门机构
	 */
	public Integer quertDeptByUserGroupId(Integer usergroupid);
	public Integer quertDeptByUsergroupname(String usergroupname);
	public Integer quertUsergroupidByUsergroupname(String usergroupname);
	
	/**
	 * 用户组下拉列表
	 * @return
	 */
	public List userGroupList(BlocGroup userGroup);
	/**
	 * 获取用户组列表
	 * @param currentPage
	 * @param everyPage
	 * @param userGroup
	 * @return
	 */
	public Map findPageUserGroupList(final int currentPage, final int everyPage,BlocGroup userGroup);
	
	/**
	 * 删除用户组信息
	 * @param ids
	 * @return
	 */
	public int deleteUserGroup(int id);
	
	 /**
	  * 检查用户组名称是否存在
	  * @param usergroupname
	  * @return
	  */
	public Integer userGroupNameIsExist(BlocGroup userGroup);
	
	/**
	 * 添加用户组信息
	 * @param userGroup
	 * @return
	 */
	public Integer addUserGroupInfo(BlocGroup userGroup);
	
	
	/**
	 * 修改用户组信息
	 * @param userGroup
	 * @return
	 */
	public Integer updateUserGroupInfo(BlocGroup userGroup);
	
	/**
	 * 查询用户组下是否存在用户信息
	 * @param usergroupid
	 * @return
	 */
	public Integer queryUserGroupUser(int usergroupid);
	
	/**
	 * 删除用户组拥有的权限
	 * @param usergroupid
	 * @return
	 */
	public Integer deleteUserGroupAuth(int usergroupid);
	
	/**
	 * 获取企业组ID
	 * @param blocgroupname
	 * @return
	 */
	public Integer getUserGroupByName(String blocgroupname);
	

}
