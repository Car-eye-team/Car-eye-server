/**    
 * Description: 远佳物联网车联网平台    
 * 文件名：UserRegisterAction.java   
 * 版本信息：    
 * 日期：2017-9-7  
 * Copyright 深圳远佳智慧科技有限公司 Copyright (c) 2017     
 * 版权所有    
 *    
 */
package com.careye.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.common.service.UserRegisterService;
import com.careye.constant.WebConstants;
import com.careye.http.HttpRequester;
import com.careye.http.HttpRespons;
import com.careye.system.domain.Bloc;
import com.careye.system.domain.BlocAuthority;
import com.careye.system.domain.BlocGroup;
import com.careye.system.domain.BlocUser;
import com.careye.system.service.AuthorityService;
import com.careye.system.service.OrgazicationDeptService;
import com.careye.system.service.UserGroupService;
import com.careye.system.service.UserService;
import com.careye.utils.DateUtil;
import common.Logger;

/**    
 *     
 * 项目名称：TAXI    
 * 类名称：UserRegisterAction    
 * 类描述：用户注册    
 * 创建人：Administrator    
 * 创建时间：2017-9-7 上午09:56:01    
 * 修改人：Administrator    
 * 修改时间：2017-9-7 上午09:56:01    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class UserRegisterAction extends BasePageAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserRegisterAction.class);
	private UserRegisterService userRegisterService;
	private OrgazicationDeptService orgazicationDeptService;
	private UserService userService;
	private UserGroupService userGroupService;
	private AuthorityService authorityService;
	private CarService carService;

	private BlocUser user;

	private Map result;
	private String success;

	//手机号
	private String phone;
	//密码
	private String password;

	//车牌号
	private String carnumber;

	private String code;

	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 用户注册
	 * @return
	 */
	public String register(){
		try {
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserRegisterAction 的方法 register 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 获取短信验证码
	 * @return
	 */
	public String getSmsCode(){

		try {
			initMap();

			try {
				HttpRequester request = new HttpRequester();
				StringBuffer sendParm = new StringBuffer();
				request.setDefaultContentEncoding("utf-8");
				HttpRespons hr = null;
				sendParm.append(WebConstants.URL+WebConstants.SEND_SMS_CODE);
				sendParm.append("?&ak="+WebConstants.KEY);
				sendParm.append("&phone="+phone);
				sendParm.append("&type=1");
				hr = request.sendPost(sendParm.toString());
				String json = hr.getContent();
				JSONObject obj = JSONObject.fromObject(json);
				int status = obj.getInt("status");
				if (status == 0) {
					int su = obj.getInt("su");
					result.put("su", su);
				}else{
					result.put("su", -1);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("su", -2);
			}

			this.success = "true";
			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("UserRegisterAction 的方法 getCode 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 用户注册
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String userRegister(){


		/*phone = "18668171282";
		password = "123456";
		carnumber = "粤B71282";*/
		try {
			initMap();

			try {
				HttpRequester request = new HttpRequester();
				StringBuffer sendParm = new StringBuffer();
				request.setDefaultContentEncoding("utf-8");
				HttpRespons hr = null;
				sendParm.append(WebConstants.URL+WebConstants.CHECK_SMS_CODE);
				sendParm.append("?&ak="+WebConstants.KEY);
				sendParm.append("&phone="+phone);
				sendParm.append("&authcode="+code);
				hr = request.sendPost(sendParm.toString());
				String json = hr.getContent();
				JSONObject obj = JSONObject.fromObject(json);
				int su = obj.getInt("su");
				if (su == 0) {
					result.put("su", su);
				}else{
					result.put("su", -1);
					this.success = "true";
					return SUCCESS;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("su", -1);
				this.success = "true";
				return SUCCESS;
			}


			//操作对应机构（上级机构ID:461）
			Bloc orgazicationDept = new Bloc();
			orgazicationDept.setParentid(461);
			orgazicationDept.setBloc_name(phone);

			//先获取结构是否存在，存在获取机构ID
			int blocid = 0;
			Integer dpid = orgazicationDeptService.getDeptIdByDeptName(phone);
			if(dpid == null){
				blocid = orgazicationDeptService.addOrgazicationDept(orgazicationDept);
			}else{
				blocid = dpid;
			}
			logger.info("企业ID："+blocid);


			//操作对应企业组
			int blocgroupid = 0;
			//先获取企业组是否存在，存在获取组ID
			Integer bid = userGroupService.getUserGroupByName(phone);
			if(bid == null){
				BlocGroup userGroup = new BlocGroup();
				userGroup.setUserid(1);
				userGroup.setBlocgroupname(phone);
				userGroup.setBlocid(blocid);
				userGroup.setCreatetime(DateUtil.getSQLDate());
				blocgroupid = userGroupService.addUserGroupInfo(userGroup);

				//分配默认权限
				List<Integer> menuidList = authorityService.getAuthorityMenuId();
				List<BlocAuthority> list = new ArrayList<BlocAuthority>();  
				for(Integer menuid : menuidList){
					BlocAuthority userGroupMenu = new BlocAuthority();
					userGroupMenu.setBlocgroupid(blocgroupid);
					userGroupMenu.setMenuid(menuid);
					userGroupMenu.setType(1);
					userGroupMenu.setCreatetime(DateUtil.getSQLDate());
					list.add(userGroupMenu);
				}
				authorityService.addUserGroupMenu(list);

			}else{
				blocgroupid = bid;
			}

			logger.info("企业组ID："+blocgroupid);

			//操作企业用户
			int userid = 0;
			Integer uid = userService.getUserIdByName(phone);
			BlocUser user = new BlocUser();
			user.setBlocid(blocid);
			user.setBlocgroupid(blocgroupid);
			user.setUsername(phone);
			user.setLoginname(phone);
			user.setPassword(password);
			user.setUsersex(1);
			user.setState(1);
			user.setCreatetime(DateUtil.getSQLDate());
			if(uid == null){
				userid = userService.addUser(user);
			}else{
				userid = uid;
				user.setId(userid);
				userService.updateUser(user);
			}
			logger.info("企业用户ID："+userid);

			//操作车辆信息
			CarInfo carInfo = new  CarInfo();
			carInfo.setBlocid(blocid);
			carInfo.setTerminal(phone);
			carInfo.setDevicetype(7);
			carInfo.setCarcolor("1");
			carInfo.setUserid(userid);
			carInfo.setPassword(password);
			carInfo.setPhone(phone);
			carInfo.setVediotype(1);
			carInfo.setVediono(phone);
			carInfo.setCarnumber(carnumber);

			//根据设备号获取车辆ID
			Integer carid = carService.getCaridByTerminal(phone);
			if(carid != null){
				carInfo.setId(carid);
			}
			Integer su = carService.saveCarInfo(carInfo);
			logger.info("su=="+su);
			result.put("su", 0);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			result.put("su", -2);
			this.success = "true";
			logger.error("UserRegisterAction的方法 userRegister执行出错，原因：" + e, e);
			return SUCCESS;
		}
	}


	public UserRegisterService getUserRegisterService() {
		return userRegisterService;
	}

	public void setUserRegisterService(UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public OrgazicationDeptService getOrgazicationDeptService() {
		return orgazicationDeptService;
	}

	public void setOrgazicationDeptService(
			OrgazicationDeptService orgazicationDeptService) {
		this.orgazicationDeptService = orgazicationDeptService;
	}


	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}



	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
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

	public BlocUser getUser() {
		return user;
	}

	public void setUser(BlocUser user) {
		this.user = user;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



}
