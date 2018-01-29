/**
* Description: car-eye车辆管理平台
* 文件名：ServiceWinService.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.taxi.api.service;

import java.util.List;
import java.util.Map;

import com.careye.taxi.domain.GroupMember;
import com.careye.taxi.domain.IntercomGroup;
import com.careye.taxi.domain.IntercomGroupInvite;
import com.careye.taxi.domain.IntercomGroupMember;
import com.careye.taxi.domain.IntercomGroupSearch;
import com.careye.taxi.domain.MyGroup;


/**
 * @项目名称：OCS
 * @类名称：TaxiService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-1-20 上午11:39:08
 * @修改人：zhangrong
 * @修改时间：2015-1-20 上午11:39:08
 * @修改备注：
 * @version 1.0
 */
public interface GroupService {
	
	/**
	 * 添加对讲组
	 */
	public int addIntercomGroup(IntercomGroup intercomGroup);
	/**
	 * 根据申请id获取对讲组信息
	 */
	public IntercomGroup getIntercomGroupDetail(int inviteid);

	/**
	 * 获取我加入的对讲组
	 * @param parseInt
	 * @return
	 */
	public List<MyGroup> myAddGroup(int carid);

	/**
	 * 获取接收申请列表
	 * @param carid
	 * @return
	 */
	public List<IntercomGroupInvite> myInvite(Map map);
	
	/**
	 * 根据id删除对讲组
	 */
	public int delIntercomGroup(String id);
	
	/**
	 * 添加对讲组成员
	 */
	public int addIntercomGroupMember(IntercomGroupMember intercomGroupMember);
	
	/**
	 * 删除对讲组成员
	 */
	public int deleteIntercomGroupMember(IntercomGroupMember intercomGroupMember);

	/**
	 * 获取组人员信息
	 * @param parseInt
	 * @return
	 */
	public List<GroupMember> getGroupMember(int gid);
	
	/**
	 * 根据对讲组id删除对讲组所有成员
	 */
	public int delAllIntercomGroupMember(String id);
	
	/**
	 * 添加对讲组申请信息 
	 */
	public int addIntercomGroupInvite(IntercomGroupInvite intercomGroupInvite);
	
	/**
	 * 根据组名称搜索对讲组列表
	 */
	public Map getIntercomGroupList(int currentPage,int everyPage, IntercomGroupSearch intercomGroupSearch);
	
	/**
	 * 根据设备传入的经纬度、范围（米）搜索周边组接口
	 */
	public Map getAroundIntercomGroupList(int currentPage,int everyPage, Map map);
	
	/**
	 * 获取我创建的组
	 */
	public List<MyGroup> myCreateGroup(String carid);

	/**
	 * 查看组成员是否存在
	 * @param intercomGroupMember
	 * @return
	 */
	public Integer isExistGroupMember(IntercomGroupMember intercomGroupMember);

	/**
	 * 修改申请
	 * @param appUserInvite
	 * @return
	 */
	public Integer updateIntercomGroupInvite(IntercomGroupInvite appUserInvite);
	
}



