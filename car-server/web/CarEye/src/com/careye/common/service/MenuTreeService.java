/**
* Description: car-eye车辆管理平台
* 文件名：UserLoginService.java
* 版本信息：1.0
* 日期：2014-2-24
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.service;

import java.util.List;

import com.careye.base.action.BaseDomain;
import com.careye.base.action.TreeDomain;
import com.careye.common.domain.MenuEntry;
import com.careye.system.domain.Bloc;
import com.careye.system.domain.UserCar;


/**
 * @项目名称：car-eyeTms
 * @类名称：MenuTreeService
 * @类描述：桌面菜单，功能菜单动态加载树结构接口
 * @创建人：zhangrong
 * @创建时间：2014-2-26 下午14:40:07
 * @修改人：zhangrong
 * @修改时间：2014-2-26 下午14:40:07
 * @修改备注：
 * @version 1.0
 */
public interface MenuTreeService {

	
	/**组织机构树形列表**/
	public List deptTreeList(Bloc orgazicationDept);
	
	/**实时车辆树形列表**/
	public List carTreeList(TreeDomain baseDomain);
	
	/**实时车辆所有车辆树形列表**/
	public List allCarTreeList(TreeDomain baseDomain);
	
	/**菜单列表**/
	public List<MenuEntry> getMenuList(Integer groupid);
	
	/**权限下菜单列表**/
	public List<MenuEntry> getAuthorityMenu(Integer groupid);
	
	/**统计组织机构下面车辆总数**/
	public Integer getTotalCars(TreeDomain baseDomain);
	
	/**统计组织机构下面车辆在线数**/
	public Integer getInlineCars(TreeDomain baseDomain);
	
	/**统计组织机构下面车辆长期离线数**/
	public Integer getLongOfflineCars(TreeDomain baseDomain);
	
	/**得到组织机构根节点**/
	public Integer getRootDeptid();
	
	/**得到用户所能查看的车辆id数量**/
	public Integer getUserCaridCount(UserCar userCar);
	
	/**得到用户所能查看的车辆id列表**/
	public List getUserCaridList(UserCar userCar);
	
	/**提醒类型树列表**/
	public List remindTreeList();
	
	/**得到用户下面关闭提醒类型列表**/
	public List getRemindCheckedList(int assignuserid);
	
	/**根据组织机构模糊名得到组织机构id**/
	public Integer getDeptidByName(String deptname);
	
}
