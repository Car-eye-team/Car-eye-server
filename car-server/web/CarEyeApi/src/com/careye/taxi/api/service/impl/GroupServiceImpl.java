/**
* Description: car-eye车辆管理平台
* 文件名：ServiceWinServiceImpl.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.taxi.api.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.http.BaiDuHttp;
import com.careye.http.domain.BaiDuInfo;
import com.careye.taxi.api.service.GroupService;
import com.careye.taxi.domain.GroupMember;
import com.careye.taxi.domain.IntercomGroup;
import com.careye.taxi.domain.IntercomGroupInvite;
import com.careye.taxi.domain.IntercomGroupMember;
import com.careye.taxi.domain.IntercomGroupSearch;
import com.careye.taxi.domain.MyGroup;
import com.careye.utils.DateUtil;




/**
 * @项目名称：OCS
 * @类名称：TaxiServiceImpl
 * @类描述：飞嘀打车操作taxi数据库
 * @创建人：zhangrong
 * @创建时间：2015-1-20 上午11:39:33
 * @修改人：zhangrong
 * @修改时间：2015-1-20 上午11:39:33
 * @修改备注：
 * @version 1.0
 */
public class GroupServiceImpl extends GenericService implements GroupService{

	/**
	 * 添加对讲组
	 */
	public int addIntercomGroup(IntercomGroup intercomGroup){
		if(intercomGroup.getLng() != null && intercomGroup.getLat() != null){
			BaiDuInfo baiDuInfo = BaiDuHttp.getBaiDuInfo(intercomGroup.getLat(), intercomGroup.getLng());
			
			intercomGroup.setBlng(baiDuInfo.getBlng());
			intercomGroup.setBlat(baiDuInfo.getBlat());
			intercomGroup.setBaddress(baiDuInfo.getAddress());
			intercomGroup.setProvince(baiDuInfo.getProvince());
			intercomGroup.setCity(baiDuInfo.getCity());
			intercomGroup.setDistrict(baiDuInfo.getDistrict());
		}
		int gid = this.baseDao.save("oracle-GroupSQL.addIntercomGroup", intercomGroup);
		
		//添加成员
		IntercomGroupMember intercomGroupMember = new IntercomGroupMember();
		intercomGroupMember.setGid(gid);
		intercomGroupMember.setCarid(intercomGroup.getCarid());
		intercomGroupMember.setIsadmin(1);//默认为管理员
		intercomGroupMember.setCreatetime(DateUtil.getSQLDate());
		
		addIntercomGroupMember(intercomGroupMember);
		
		return gid;
	}
	
	/**
	 * 根据申请id获取对讲组信息
	 */
	public IntercomGroup getIntercomGroupDetail(int inviteid){
		return (IntercomGroup) this.baseDao.queryForObject("oracle-GroupSQL.getIntercomGroupDetail", inviteid);
	}
	
	/**
	 * 根据id删除对讲组
	 */
	public int delIntercomGroup(String id){
		//删除对讲组所有成员
		delAllIntercomGroupMember(id);
		return this.baseDao.delete("oracle-GroupSQL.delIntercomGroup", id);
	}
	
	/**
	 * 添加对讲组成员
	 */
	public int addIntercomGroupMember(IntercomGroupMember intercomGroupMember){
		return this.baseDao.save("oracle-GroupSQL.addIntercomGroupMember", intercomGroupMember);
	}
	
	/**
	 * 删除对讲组成员
	 */
	public int deleteIntercomGroupMember(IntercomGroupMember intercomGroupMember){
		return this.baseDao.delete("oracle-GroupSQL.deleteIntercomGroupMember", intercomGroupMember);
	}
	
	/**
	 * 根据对讲组id删除对讲组所有成员
	 */
	public int delAllIntercomGroupMember(String id){
		return this.baseDao.delete("oracle-GroupSQL.delAllIntercomGroupMember", id);
	}
	
	
	/**
	 * 获取我加入的对讲组
	 * @param parseInt
	 * @return
	 */
	public List<MyGroup> myAddGroup(int carid){
		return this.baseDao.queryForList("oracle-GroupSQL.myAddGroup", carid);
	}
	
	/**
	 * 获取接收申请列表
	 * @param carid
	 * @return
	 */
	public List<IntercomGroupInvite> myInvite(Map map){
		return this.baseDao.queryForList("oracle-GroupSQL.myInvite", map);
	}
	
	/**
	 * 获取组人员信息
	 * @param parseInt
	 * @return
	 */
	public List<GroupMember> getGroupMember(int gid){
		return this.baseDao.queryForList("oracle-GroupSQL.getGroupMember", gid);
	}
	
	/**
	 * 添加对讲组申请信息 
	 */
	public int addIntercomGroupInvite(IntercomGroupInvite intercomGroupInvite){
		return this.baseDao.save("oracle-GroupSQL.addIntercomGroupInvite", intercomGroupInvite);
	}
	
	/**
	 * 根据组名称搜索对讲组列表
	 */
	public Map getIntercomGroupList(int currentPage,int everyPage, IntercomGroupSearch intercomGroupSearch){
		return this.baseDao.findPageList("oracle-GroupSQL.getIntercomGroupList", 
				"oracle-GroupSQL.getIntercomGroupListCount", intercomGroupSearch, currentPage, everyPage);
	}
	
	/**
	 * 根据设备传入的经纬度、范围（米）搜索周边组接口
	 */
	public Map getAroundIntercomGroupList(int currentPage,int everyPage, Map map){
		return this.baseDao.findPageList("oracle-GroupSQL.getAroundIntercomGroupList", 
				"oracle-GroupSQL.getAroundIntercomGroupListCount", map, currentPage, everyPage);
	}
	
	
	/**
	 * 获取我创建的组
	 */
	public List<MyGroup> myCreateGroup(String carid){
		return this.baseDao.queryForList("oracle-GroupSQL.myCreateGroup", carid);
	}
	
	/**
	 * 查看组成员是否存在
	 * @param intercomGroupMember
	 * @return
	 */
	public Integer isExistGroupMember(IntercomGroupMember intercomGroupMember){
		return (Integer) this.baseDao.queryForObject("oracle-GroupSQL.isExistGroupMember", intercomGroupMember);
	}
	
	/**
	 * 修改申请
	 * @param appUserInvite
	 * @return
	 */
	public Integer updateIntercomGroupInvite(IntercomGroupInvite appUserInvite){
		return this.baseDao.update("oracle-GroupSQL.updateIntercomGroupInvite", appUserInvite);
	}
	
}










