/**
* Description: car-eye车辆管理平台
* 文件名：SystemManageServiceImpl.java
* 版本信息：1.0
* 日期：2014-2-18
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.system.domain.BlocGroup;
import com.careye.system.service.UserGroupService;

/**
 * @项目名称：car-eyeTms
 * @类名称：SystemManageServiceImpl
 * @类描述：系统管理接口实现
 * @创建人：zr
 * @创建时间：2014-2-18 下午02:00:17
 * @修改人：zr
 * @修改时间：2014-2-18 下午02:00:17
 * @修改备注：
 * @version 1.0
 */
public class UserGroupServiceImpl extends GenericService implements UserGroupService{

	/**
	 * 根据用户组得到部门机构
	 */
	public Integer quertDeptByUserGroupId(Integer usergroupid){
		return (Integer)this.baseDao.queryForObject("oracle-userGroupSQL.quertDeptByUserGroupId", usergroupid);
	}
	public Integer quertDeptByUsergroupname(String usergroupname){
		return (Integer)this.baseDao.queryForObject("oracle-userGroupSQL.quertDeptByUsergroupname", usergroupname);
	}
	public Integer quertUsergroupidByUsergroupname(String usergroupname){
		return (Integer)this.baseDao.queryForObject("oracle-userGroupSQL.quertUsergroupidByUsergroupname", usergroupname);
	}
	
	/**
	 * 用户组下拉列表
	 * @return
	 */
	public List userGroupList(BlocGroup userGroup){
		return this.baseDao.queryForList("oracle-userGroupSQL.userGroupList",userGroup);
	}
	/**
	 * 获取用户组列表
	 * @param currentPage
	 * @param everyPage
	 * @param userGroup
	 * @return
	 */
	@Override
	public Map findPageUserGroupList(final int currentPage, final int everyPage,BlocGroup userGroup) {
		return this.baseDao.findPageList("oracle-userGroupSQL.findPageUserGroupList", "oracle-userGroupSQL.findPageUserGroupListcount", userGroup, currentPage, everyPage);
	}

	/**
	 * 删除用户组信息
	 * @param ids
	 * @return
	 */
	@Override
	public int deleteUserGroup(int id) {
		return this.baseDao.delete("oracle-userGroupSQL.deleteUserGroup", id);
	}

	/**
	  * 检查用户组名称是否存在
	  * @param usergroupname
	  * @return
	  */
	@Override
	public Integer userGroupNameIsExist(BlocGroup userGroup) {
		return (Integer) this.baseDao.queryForObject("oracle-userGroupSQL.userGroupNameIsExist", userGroup);
	}

	/**
	 * 添加用户组信息
	 * @param userGroup
	 * @return
	 */
	@Override
	public Integer addUserGroupInfo(BlocGroup userGroup) {
		return this.baseDao.save("oracle-userGroupSQL.addUserGroupInfo", userGroup);
	}

	/**
	 * 修改用户组信息
	 * @param userGroup
	 * @return
	 */
	@Override
	public Integer updateUserGroupInfo(BlocGroup userGroup) {
		return this.baseDao.update("oracle-userGroupSQL.updateUserGroupInfo", userGroup);
	}
	
	/**
	 * 查询用户组下是否存在用户信息
	 * @param usergroupid
	 * @return
	 */
	@Override
	public Integer queryUserGroupUser(int usergroupid) {
		return (Integer) this.baseDao.queryForObject("oracle-userGroupSQL.queryUserGroupUser", usergroupid);
	}
	
	/**
	 * 删除用户组拥有的权限
	 * @param usergroupid
	 * @return
	 */
	@Override
	public Integer deleteUserGroupAuth(int usergroupid) {
		return this.baseDao.delete("oracle-userGroupSQL.deleteUserGroupAuth", usergroupid);
	}
	@Override
	public Integer getUserGroupByName(String blocgroupname) {
		return (Integer) this.baseDao.queryForObject("oracle-userGroupSQL.getUserGroupByName", blocgroupname);
	}

}
