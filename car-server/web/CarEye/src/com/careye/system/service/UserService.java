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
import java.util.Map;

import com.careye.system.domain.BlocUser;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.system.domain.UserCar;
import com.careye.system.domain.UserContact;
import com.careye.system.domain.WindowSet;


/**
 * @项目名称：car-eyeTms
 * @类名称：UserService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-2-19 上午09:19:18
 * @修改人：zhangrong
 * @修改时间：2014-2-19 上午09:19:18
 * @修改备注：
 * @version 1.0
 */
public interface UserService{
	
	/**
	 * 修改报警声音
	 * @param user
	 * @return
	 */
	public int updateUserWarnswitch(BlocUser user);
	
	/**
	 * 根据用户id和密码得到用户
	 * @param user
	 * @return
	 */
	public BlocUser getUserDetatail(BlocUser user);
	
	/**
	 * 分页用户列表
	 * @return
	 */
	public Map findPageUserList(int currentPage, int everyPage,BlocUser user);
	
	/**
	 * 报警提示设置分页用户列表
	 * @return
	 */
	public Map findPageUserListPrompt(int currentPage, int everyPage,BlocUser user);
	
	/**
	 * 添加用户
	 * @param BlocUser
	 */
	public int addUser(BlocUser user);
	/**
	 * 更新用户
	 * @param BlocUser
	 */
	public int updateUser(BlocUser user);
	/**
	 * 登陆用户名是否存在
	 * @param userInfo
	 * @return
	 */
	public Integer loginNameIsExist(BlocUser user);
	
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	public int deleteUser(int id);
	
	/**
	 * 激活用户
	 * @param id
	 * @return
	 */
	public int activeUser(int id);
	/**
	 * 禁用用户
	 * @param id
	 * @return
	 */
	public int inactiveUser(int id);
	
	/**
	 * 分页用户登陆日志列表
	 * @return
	 */
	public Map findPageLoginLogList(int currentPage, int everyPage,SysAuthLoginLog loginLog);
	
	/**
	 * 用户登陆日志列表  Excel
	 * @return
	 */
	public List<SysAuthLoginLog> exportLoginLogList(SysAuthLoginLog loginLog);
	public int exportLoginLogListCount(SysAuthLoginLog loginLog);
	
	/**
	 * 删除用户登陆日志信息
	 * @param id
	 * @return
	 */
	public int deleteLoginLog(int id);
	
	/**
	 * 删除用户所能查看车辆
	 * @param id
	 * @return
	 */
	public int delUserCar(int userid);
	
	/**
	 * 批量增加用户所能查看车辆
	 * @param list
	 * @return
	 */
	public int addUserCar(List list);
	/**
	 * 删除用户所能关闭提醒类型
	 * @param id
	 * @return
	 */
	public int delAssignRemind(int userid);
	
	/**
	 * 批量增加用户所能关闭提醒类型
	 * @param list
	 * @return
	 */
	public int addAssignRemind(List list);
	
	/**
	 * 查看指定用户提醒类型打开关闭状态，1打开 2 关闭
	 */
	public String queryRemindStatus(WindowSet windowSet);
	
	/**
	 * 根据报警名称得到报警key
	 */
	public String getAlarmkeyByName(String name);
	
	/**保存用户联系人**/
	public Integer saveUserContact(UserContact userContact);
	
	/**查看用户是否存在用户联系记录**/
	public boolean queryUserContactIsExist(int userid);	
	
	/**更新联系人**/
	public Integer updateUserContact(UserContact userContact);
	
	/**添加联系人**/
	public Integer addUserContact(UserContact userContact);
	
	/**查询用户联系人信息**/
	public UserContact loadUserContact(Integer userid);
	
	/**
	 * 用户平台车辆列表
	 * @return
	 */
	public Map findPagePtcarList(int currentPage, int everyPage,UserCar userCar);

	/**
	 * 删除平台分配车辆
	 * @param id
	 * @return
	 */
	public int deletePtcar(int id);
	/**
	 * 删除内部分配车辆
	 * @param id
	 * @return
	 */
	public int deleteNbcar(int id);
	
	/**
	 * 查看是否存在某车辆 1 内部 2 平台
	 * @param id
	 * @return
	 */
	public int getPtcarCount(UserCar userCar);
	
	/**
	 * 查看平台是否存在某车辆
	 * @param id
	 * @return
	 */
	public int queryUserPtcarIsAssign(int userid);
	
	/**
	 * 界面展开分页列表
	 * @return
	 */
	public Map findPageSetList(int currentPage, int everyPage,BlocUser user);
	
	/**
	 * 根据用户检查用户是否存在
	 * @param loginname
	 * @return
	 */
	public int getLoginNameCount(String loginname);
	
	/**
	 * 根据登录名获取用户ID
	 * @param loginname
	 * @return
	 */
	public Integer getUserIdByName(String loginname);
	
}



