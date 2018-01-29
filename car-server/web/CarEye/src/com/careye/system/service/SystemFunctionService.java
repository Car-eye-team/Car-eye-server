/**
* Description: car-eye车辆管理平台
* 文件名：SystemFunctionService.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.service;


import java.util.List;
import java.util.Map;

import com.careye.system.domain.SysFunctionMenu;



/**
 * @项目名称：car-eyeTms
 * @类名称：SystemFunctionService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-2-24 下午14:19:18
 * @修改人：zhangrong
 * @修改时间：2014-2-24 下午14:19:18
 * @修改备注：
 * @version 1.0
 */
public interface SystemFunctionService{
	
	/**系统菜单下拉列表 key :menu,value:menuname **/
	public List getMenuList(SysFunctionMenu systemFunction);
	
	/**
	 * 分页系统功能列表
	 * @return
	 */
	public Map findPageSystemFunctionList(int currentPage, int everyPage,SysFunctionMenu systemFunction);
	/**
	 * 添加系统功能
	 * @param SysFunctionMenu
	 */
	public int addSystemFunction(SysFunctionMenu systemFunction);
	/**
	 * 更新系统功能
	 * @param SysFunctionMenu
	 */
	public int updateSystemFunction(SysFunctionMenu systemFunction);
	/**
	 * 查看系统功能名是否存在
	 * @param userInfo
	 * @return
	 */
	public int menuNameIsExist(SysFunctionMenu systemFunction);
	
	/**
	 * 激活系统功能
	 * @param id
	 * @return
	 */
	public int activeSystemFunction(int menuid);
	/**
	 * 禁用系统功能
	 * @param id
	 * @return
	 */
	public int inactiveSystemFunction(int menuid);

}
