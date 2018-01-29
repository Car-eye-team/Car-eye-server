/**
* Description: car-eye车辆管理平台
* 文件名：UserLoginServiceImpl.java
* 版本信息：1.0
* 日期：2014-2-24
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.service.impl;

import java.util.List;

import com.careye.base.action.TreeDomain;
import com.careye.base.service.GenericService;
import com.careye.common.domain.MenuEntry;
import com.careye.common.domain.MenuTree;
import com.careye.common.service.MenuTreeService;
import com.careye.system.domain.Bloc;
import com.careye.system.domain.UserCar;

/**
 * @项目名称：car-eyeTms
 * @类名称：MenuTreeServiceImpl
 * @类描述：菜单动态加载树结构接口实现
 * @创建人：zhangrong
 * @创建时间：2014-2-26 下午14:40:07
 * @修改人：zhangrong
 * @修改时间：2014-2-26 下午14:40:07
 * @修改备注：
 * @version 1.0
 */
public class MenuTreeServiceImpl extends GenericService implements MenuTreeService{

	
	/**组织机构树形列表**/
	public List deptTreeList(Bloc orgazicationDept){
		return this.baseDao.queryForList("oracle-MenuTreeSQL.deptTreeList",orgazicationDept);
	}

	/**实时车辆树形列表**/
	public List carTreeList(TreeDomain baseDomain){
		return this.baseDao.queryForList("oracle-MenuTreeSQL.carTreeList",baseDomain);
	}
	
	/**实时车辆所有车辆树形列表**/
	public List allCarTreeList(TreeDomain baseDomain){
		List list = null;
		if(baseDomain.getCarnumber() == null){
			list = this.baseDao.queryForList("oracle-MenuTreeSQL.allCarTreeList",baseDomain);
		}else{
			list = this.baseDao.queryForList("oracle-MenuTreeSQL.allCarTreeByCarnumberList",baseDomain);
		}
		return list;
	}
	
	/**菜单列表**/
	public List<MenuEntry> getMenuList(Integer groupid){
		return this.baseDao.queryForList("oracle-MenuTreeSQL.getMenuList",groupid);
	}
	
	/**权限下菜单列表**/
	public List<MenuEntry> getAuthorityMenu(Integer groupid){
		return this.baseDao.queryForList("oracle-MenuTreeSQL.getAuthorityMenu",groupid);
	}
	
	/**统计组织机构下面车辆总数**/
	public Integer getTotalCars(TreeDomain baseDomain){
		return (Integer)this.baseDao.queryForObject("oracle-MenuTreeSQL.getTotalCars",baseDomain);
	}
	
	/**统计组织机构下面车辆在线数**/
	public Integer getInlineCars(TreeDomain baseDomain){
		return (Integer)this.baseDao.queryForObject("oracle-MenuTreeSQL.getInlineCars",baseDomain);
	}
	
	/**统计组织机构下面车辆长期离线数**/
	public Integer getLongOfflineCars(TreeDomain baseDomain){
		return (Integer)this.baseDao.queryForObject("oracle-MenuTreeSQL.getLongOfflineCars",baseDomain);
	}
	
	/**得到组织机构根节点**/
	public Integer getRootDeptid(){
		return (Integer)this.baseDao.queryForObject("oracle-MenuTreeSQL.getRootDeptid",null);
	}
	
	/**得到用户所能查看的车辆id**/
	public Integer getUserCaridCount(UserCar userCar){
		return (Integer)this.baseDao.queryForObject("oracle-MenuTreeSQL.getUserCaridCount",userCar);
	}
	
	/**得到用户所能查看的车辆id列表**/
	public List getUserCaridList(UserCar userCar){
		return this.baseDao.queryForList("oracle-MenuTreeSQL.getUserCaridList", userCar);
	}
	
	/**提醒类型树列表**/
	public List remindTreeList(){
		List<MenuTree> menuList = this.baseDao.queryForList("oracle-MenuTreeSQL.remindTreeList",null);
//		menuList.add(new MenuTree("4", "车辆温度报警",true,1,false, null, "0", null));
//		menuList.add(new MenuTree("3", "系统公告信息", true,1,false,null, "0", null));
//		menuList.add(new MenuTree("2", "系统消息提醒",false,0,true, null, "0", null));
		menuList.add(new MenuTree("1", "报警类型", false,0,true,null, "0", null));
		return menuList;
	}
	
	/**得到用户下面关闭提醒类型列表**/
	public List getRemindCheckedList(int assignuserid){
		return this.baseDao.queryForList("oracle-MenuTreeSQL.getRemindCheckedList", assignuserid);
	}
	
	/**根据组织机构模糊名得到组织机构id**/
	public Integer getDeptidByName(String deptname){
		return (Integer)this.baseDao.queryForObject("oracle-MenuTreeSQL.getDeptidByName", deptname);
	}
	
}





