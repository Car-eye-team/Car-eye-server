/**
* Description: car-eye车辆管理平台
* 文件名：UserLoginAction.java
* 版本信息：1.0
* 日期：2014-2-24
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.common.service.MenuTreeService;
import com.careye.common.service.UserLoginService;
import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.system.domain.UserCar;
import com.careye.system.service.UserService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

import common.Logger;

/**
 * @项目名称：car-eyeTms
 * @类名称：UserLoginAction
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-2-24 下午04:10:05
 * @修改人：zr
 * @修改时间：2014-2-24 下午04:10:05
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class UserLoginAction extends BasePageAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserLoginAction.class);
	
	private UserLoginService userLoginService;
	private MenuTreeService menuTreeService;
	private UserService userService;
	
	private BlocUser user;
	
	private Map result;
	private String success;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 获取验证码
	 * @return
	 */
	public String getVeryCode(){
		initMap();
		String verycode = (String) ServletActionContext.getRequest().getSession().getAttribute("rand");
		result.put("verycode", verycode);
		return SUCCESS;
	}
	
	
	/**
	 * 用户登陆
	 * @return
	 */
	public String userLogin(){
		
		try {
			initMap();
			if (user == null){
				return ERROR;
			}
			logger.info("用户开始登陆");
//			判断验证码是否正确
			String rand = (String) ServletActionContext.getRequest().getSession().getAttribute("rand");
			if(!rand.equals(user.getVeryCode())){
				logger.info("用户登陆，验证码不正确");
				result.put("su", -1);
			}else{
				logger.info("用户登陆，开始验证用户名密码,用户名："+user.getLoginname()+",密码:"+user.getPassword());
				//验证用户名密码是否正确
				BlocUser userInfo = userLoginService.getUserInfo(user);
				if(userInfo == null){
					result.put("su", -2);
				}else{
					if(userInfo.getState() == 2){
						result.put("su", 0);
					}else{
						result.put("su", 1);
						//将用户信息存入session中
						ServletActionContext.getRequest().getSession().setAttribute(WebConstants.LOGIN_USER, userInfo);
						ServletActionContext.getRequest().getSession().setMaxInactiveInterval(60*60*24);
						
						// 增加登陆日志，成功登陆
						SysAuthLoginLog loginLog = new SysAuthLoginLog();
						loginLog.setUserid(userInfo.getId());
						loginLog.setBlocid(userInfo.getBlocid());
						loginLog.setLogindate(DateUtil.getSQLDate());
						loginLog.setLoginip(SessionUtils.getRemoteRequestIp());
						loginLog.setLoginflag("SUCCESS");
						loginLog.setStatus(1);
						userLoginService.loginLog(loginLog);
						
						logger.info("登录成功");
						
					}
				}
			}
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("UserLoginAction的方法 userLogin执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 用户注销
	 * @return
	 */
	public String logout(){
		initMap();
		BlocUser userInfo = SessionUtils.getUser();
		// 增加退出日志
		SysAuthLoginLog loginLog = new SysAuthLoginLog();
		loginLog.setUserid(userInfo.getId());
		loginLog.setBlocid(userInfo.getBlocid());
		loginLog.setLogindate(DateUtil.getSQLDate());
		loginLog.setLoginip(SessionUtils.getRemoteRequestIp());
		loginLog.setLoginflag("SUCCESS");
		loginLog.setStatus(2);
		userLoginService.loginLog(loginLog);
		
		ServletActionContext.getRequest().getSession().invalidate();
		ServletActionContext.getRequest().getSession().setAttribute(WebConstants.LOGIN_USER, null);
		
		return SUCCESS;
	}
	
	
	/**
	 * 修改密码
	 * @return
	 */
	public String editPassWord(){
		
		try {
			initMap();
			BlocUser usersession = (BlocUser) ServletActionContext.getRequest().getSession().getAttribute(WebConstants.LOGIN_USER);
			if(user.getPassword().equals(usersession.getPassword())){
				if(user.getNewpassword().equals(user.getRenewpassword())){
					user.setId(usersession.getId());
					user.setIseditpw(2);
					userLoginService.editPassWord(user);
					ServletActionContext.getRequest().getSession().invalidate();
					ServletActionContext.getRequest().getSession().setAttribute(WebConstants.LOGIN_USER, null);
					result.put("su", 0);
				}else{
					result.put("su", -2);
				}
			}else{
				result.put("su", -1);
			}
			this.success = "true";
			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("UserLoginAction 的方法 editPassWord 执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}


	public UserLoginService getUserLoginService() {
		return userLoginService;
	}


	public void setUserLoginService(UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public BlocUser getUser() {
		return user;
	}


	public void setUser(BlocUser user) {
		this.user = user;
	}


	public Map getResult() {
		return result;
	}


	public void setResult(Map result) {
		this.result = result;
	}


	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}


}





