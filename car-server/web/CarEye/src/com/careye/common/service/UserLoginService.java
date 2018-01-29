/**
* Description: car-eye车辆管理平台
* 文件名：UserLoginService.java
* 版本信息：1.0
* 日期：2014-2-24
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.service;

import com.careye.system.domain.BlocUser;
import com.careye.system.domain.SysAuthLoginLog;

/**
 * @项目名称：car-eyeTms
 * @类名称：UserLoginService
 * @类描述：用户登陆接口
 * @创建人：zr
 * @创建时间：2014-2-24 下午04:40:07
 * @修改人：zr
 * @修改时间：2014-2-24 下午04:40:07
 * @修改备注：
 * @version 1.0
 */
public interface UserLoginService {

	/**
	 * 根据用户信息获取用户详细信息
	 * @param user
	 * @return
	 */
	public BlocUser getUserInfo(BlocUser user);
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public Integer editPassWord(BlocUser user);
	
	/**
	 * 登陆日志
	 * @param memberLoginLog
	 */
	public void loginLog(SysAuthLoginLog loginLog);
	
}
