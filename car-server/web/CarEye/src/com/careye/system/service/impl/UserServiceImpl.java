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
import com.careye.common.domain.SysOperateLog;
import com.careye.common.service.SysOperateLogService;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.system.domain.UserCar;
import com.careye.system.domain.UserContact;
import com.careye.system.domain.WindowSet;
import com.careye.system.service.UserService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：car-eye
 * @类名称：UserServiceImpl
 * @类描述：
 * @创建人：liliang
 * @创建时间：2013-9-9 下午05:58:21
 * @修改人：liliang
 * @修改时间：2013-9-9 下午05:58:21
 * @修改备注：
 * @version 1.0
 */
public class UserServiceImpl extends GenericService implements UserService{
	
	private SysOperateLogService logService;

	/**
	 * 修改报警声音
	 * @param user
	 * @return
	 */
	public int updateUserWarnswitch(BlocUser user){
		return this.baseDao.update("oracle-UserSQL.updateUserWarnswitch", user);
	}
	
	/**
	 * 根据用户id和密码得到用户
	 * @param user
	 * @return
	 */
	public BlocUser getUserDetatail(BlocUser user){
		return (BlocUser)this.baseDao.queryForObject("oracle-UserSQL.getUserDetatail", user);
	}

	
	/**
	 * 报警提示设置分页用户列表
	 * @return
	 */
	public Map findPageUserListPrompt(int currentPage, int everyPage,BlocUser user){
		return this.baseDao.findPageList("oracle-UserSQL.findPageUserListPrompt",
				"oracle-UserSQL.findPageUserListCountPrompt", user, currentPage, everyPage);
	}
	
	/**
	 * 分页用户列表
	 * @return
	 */
	public Map findPageUserList(int currentPage, int everyPage,BlocUser user) {
		return this.baseDao.findPageList("oracle-UserSQL.findPageUserList", "oracle-UserSQL.findPageUserListCount", user, currentPage, everyPage);
	}
	/**
	 * 添加用户
	 * @param BlocUser
	 */
	public int addUser(BlocUser user) {
		user.setCreatetime(DateUtil.getSQLDate());
//		String encrypted = EncryAndDecryUtils.encryption(user.getPassword());	//密码加密
//		user.setPassword(encrypted);
		return this.baseDao.save("oracle-UserSQL.addUser", user);
	}
	/**
	 * 更新用户
	 * @param BlocUser
	 */
	public int updateUser(BlocUser user){
		return this.baseDao.update("oracle-UserSQL.updateUser", user);
	}
	/**
	 * 登陆用户名是否存在
	 * @param userInfo
	 * @return
	 */
	public Integer loginNameIsExist(BlocUser user){
		return (Integer)this.baseDao.queryForObject("oracle-UserSQL.loginNameIsExist",user);
	}
	
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	public int deleteUser(int id){
		return this.baseDao.delete("oracle-UserSQL.deleteUser", id);
	}
	
	/**
	 * 激活用户
	 * @param id
	 * @return
	 */
	public int activeUser(int id){
		return this.baseDao.update("oracle-UserSQL.activeUser", id);
	}
	
	/**
	 * 禁用用户
	 * @param id
	 * @return
	 */
	public int inactiveUser(int id){
		return this.baseDao.update("oracle-UserSQL.inactiveUser", id);
	}
	
	/**
	 * 分页用户登陆日志列表
	 * @return
	 */
	public Map findPageLoginLogList(int currentPage, int everyPage,SysAuthLoginLog loginLog){
		return this.baseDao.findPageList("oracle-UserSQL.findPageLoginLogList", "oracle-UserSQL.findPageLoginLogListCount", loginLog, currentPage, everyPage);
	}
	/**
	 * 用户登陆日志列表  Excel
	 * @return
	 */
	public List<SysAuthLoginLog> exportLoginLogList(SysAuthLoginLog loginLog){
		if (loginLog.getId()!=null) {
			logService.addLogInfo(loginLog.getId(), "用户登陆日志导出", 4);
		}
		return this.baseDao.queryForList("oracle-UserSQL.findPageLoginLogList", loginLog);
	}
	public int exportLoginLogListCount(SysAuthLoginLog loginLog){
		return this.baseDao.getCount("oracle-UserSQL.findPageLoginLogListCount", loginLog);
	}
	
	/**
	 * 删除用户登陆日志信息
	 * @param id
	 * @return
	 */
	public int deleteLoginLog(int id){
		return this.baseDao.delete("oracle-UserSQL.deleteLoginLog", id);
	}
	
	/**
	 * 删除用户所能查看车辆
	 * @param id
	 * @return
	 */
	public int delUserCar(int userid){
		return this.baseDao.delete("oracle-UserSQL.delUserCar", userid);
	}
	
	/**
	 * 批量增加用户所能查看车辆
	 * @param list
	 * @return
	 */
	public int addUserCar(List list){
		return this.baseDao.save("oracle-UserSQL.addUserCar", list) ;
	}
	
	/**
	 * 删除用户所能关闭提醒类型
	 * @param id
	 * @return
	 */
	public int delAssignRemind(int userid){
		return this.baseDao.delete("oracle-UserSQL.delAssignRemind", userid);
	}
	
	/**
	 * 批量增加用户所能关闭提醒类型
	 * @param list
	 * @return
	 */
	public int addAssignRemind(List list){
		return this.baseDao.save("oracle-UserSQL.addAssignRemind", list) ;
	}
	
	/**
	 * 查看指定用户提醒类型打开关闭状态，1打开 2 关闭
	 */
	public String queryRemindStatus(WindowSet windowSet){
		Integer count = (Integer)this.baseDao.queryForObject("oracle-UserSQL.queryRemindStatus", windowSet);
		if(count > 0){	//关闭
			return "2";
		}else{
			return "1";
		}
	}
	
	/**
	 * 根据报警名称得到报警key
	 */
	public String getAlarmkeyByName(String name){
		return (String)this.baseDao.queryForObject("oracle-UserSQL.getAlarmkeyByName", name);
	}
	
	/**查看用户是否存在用户联系记录**/
	public boolean queryUserContactIsExist(int userid){
		Integer count = (Integer)this.baseDao.queryForObject("oracle-UserSQL.queryUserContactCount", userid);
		if(count==null || count == 0){
			return false;
		}
		return true;
	}
	
	/**更新联系人**/
	public Integer updateUserContact(UserContact userContact){
		return this.baseDao.update("oracle-UserSQL.updateUserContact", userContact);
	}
	
	/**添加联系人**/
	public Integer addUserContact(UserContact userContact){
		return this.baseDao.save("oracle-UserSQL.addUserContact", userContact);
	}
	
	/**保存用户联系人**/
	public Integer saveUserContact(UserContact userContact){
		boolean boo = this.queryUserContactIsExist(userContact.getUserid());
		if(boo){	
			return this.updateUserContact(userContact);
		}else{	
			return this.addUserContact(userContact);
		}
	}
	
	/**查询用户联系人信息**/
	public UserContact loadUserContact(Integer userid){
		return (UserContact)this.baseDao.queryForObject("oracle-UserSQL.loadUserContact", userid);
	}
	
	/**
	 * 用户平台车辆列表
	 * @return
	 */
	public Map findPagePtcarList(int currentPage, int everyPage,UserCar userCar){
		return this.baseDao.findPageList("oracle-UserSQL.findPagePtcarList", "oracle-UserSQL.findPagePtcarListCount", userCar, currentPage, everyPage);
	}
	
	/**
	 * 删除平台分配车辆
	 * @param id
	 * @return
	 */
	public int deletePtcar(int id){
		return this.baseDao.delete("oracle-UserSQL.deletePtcar", id);
	}
	/**
	 * 删除内部分配车辆
	 * @param id
	 * @return
	 */
	public int deleteNbcar(int id){
		return this.baseDao.delete("oracle-UserSQL.deleteNbcar", id);
	}
	/**
	 * 查看平台是否存在某车辆
	 * @param id
	 * @return
	 */
	public int getPtcarCount(UserCar userCar){
		return (Integer)this.baseDao.queryForObject("oracle-UserSQL.getPtcarCount", userCar);
	}
	
	/**
	 * 查看平台是否存在某车辆
	 * @param id
	 * @return
	 */
	public int queryUserPtcarIsAssign(int userid){
		return (Integer)this.baseDao.queryForObject("oracle-UserSQL.queryUserPtcarIsAssign", userid);
	}

	
	/**
	 * 界面展开分页列表
	 * @return
	 */
	public Map findPageSetList(int currentPage, int everyPage,BlocUser user) {
		return this.baseDao.findPageList("oracle-UserSQL.findPageSetList", "oracle-UserSQL.findPageSetListCount", user, currentPage, everyPage);
	}
	
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	@Override
	public int getLoginNameCount(String loginname) {
		return (Integer) this.baseDao.queryForObject("oracle-UserSQL.getLoginNameCount", loginname);
	}

	@Override
	public Integer getUserIdByName(String loginname) {
		return (Integer) this.baseDao.queryForObject("oracle-UserSQL.getUserIdByName", loginname);
	}
	
	
}



