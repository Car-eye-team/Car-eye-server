/**
* Description: car-eye车辆管理平台
* 文件名：UserServiceImpl.java
* 版本信息：1.0
* 日期：2013-9-9
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.system.domain.SysFunctionMenu;
import com.careye.system.service.SystemFunctionService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：UserServiceImpl
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-2-24 下午14:19:18
 * @修改人：zhangrong
 * @修改时间：2014-2-24 下午14:19:18
 * @修改备注：
 * @version 1.0
 */
public class SystemFunctionServiceImpl extends GenericService implements SystemFunctionService{
	
	/**系统菜单下拉列表 key :menu,value:menuname **/
	public List getMenuList(SysFunctionMenu systemFunction){
		return this.baseDao.queryForList("oracle-systemfunctionSQL.getMenuList",systemFunction);
	}
	
	/**
	 * 分页系统功能列表
	 * @return
	 */
	public Map findPageSystemFunctionList(int currentPage, int everyPage,SysFunctionMenu systemFunction){
		return this.baseDao.findPageList("oracle-systemfunctionSQL.findPageSystemFunctionList", "oracle-systemfunctionSQL.findPageSystemFunctionListCount", systemFunction, currentPage, everyPage);
	}
	/**
	 * 添加系统菜单
	 * @param SysFunctionMenu
	 */
	public int addSystemFunction(SysFunctionMenu systemFunction){
		int menuId = -1;
		Integer maxId = (Integer)this.baseDao.queryForObject("oracle-systemfunctionSQL.getMaxMenuId",systemFunction);
		if (systemFunction.getMenulevel() == 1) {			//一级菜单
			if (maxId == null) {
				menuId = 11;
			} else {
				menuId = maxId + 1;
			}
			systemFunction.setParentmenuid(0);
		}
		else if (systemFunction.getMenulevel() >= 2) {
			if (maxId == null) {
				menuId = new Integer(systemFunction.getParentmenuid() + "01");
			} else {
				menuId = maxId + 1;
			}
		}
	
		String current_time = DateUtil.getSQLDate();
		systemFunction.setCreatetime(current_time);
		systemFunction.setMenuid(menuId);
		systemFunction.setUserid(SessionUtils.getUserId());	//创建用户
		return this.baseDao.save("oracle-systemfunctionSQL.addSystemFunction", systemFunction);
	}
	/**
	 * 更新系统菜单
	 * @param SysFunctionMenu
	 */
	public int updateSystemFunction(SysFunctionMenu systemFunction){
		return this.baseDao.update("oracle-systemfunctionSQL.updateSystemFunction", systemFunction);
	}
	/**
	 * 查看系统功能名是否存在
	 * @param userInfo
	 * @return
	 */
	public int menuNameIsExist(SysFunctionMenu systemFunction){
		return (Integer)this.baseDao.queryForObject("oracle-systemfunctionSQL.menuNameIsExist",systemFunction);
	}
	
	/**
	 * 激活系统功能
	 * @param id
	 * @return
	 */
	public int activeSystemFunction(int menuid){
		return this.baseDao.update("oracle-systemfunctionSQL.activeSystemFunction", menuid);
	}
	/**
	 * 禁用系统功能
	 * @param id
	 * @return
	 */
	public int inactiveSystemFunction(int menuid){
		return this.baseDao.update("oracle-systemfunctionSQL.inactiveSystemFunction", menuid);
	}
	
	
}
