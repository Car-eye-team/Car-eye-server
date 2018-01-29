/**
 * Description: car-eye车辆管理平台
 * 文件名：UserGroupAction.java
 * 版本信息：1.0
 * 日期：2014-2-18
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.system.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.system.domain.BlocAuthority;
import com.careye.system.domain.BlocGroup;
import com.careye.system.domain.BlocUser;
import com.careye.system.service.AuthorityService;
import com.careye.system.service.UserGroupService;
import com.careye.system.service.UserService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

import common.Logger;

/**
 * @项目名称：car-eyeTms
 * @类名称：UserGroupAction
 * @类描述：用户组管理
 * @创建人：zhangrong
 * @创建时间：2014-2-18 下午02:14:24
 * @修改人：zhangrong
 * @修改时间：2014-2-18 下午02:14:24
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class UserGroupAction extends BasePageAction{

	private static final Logger logger = Logger.getLogger(UserGroupAction.class);

	private static final long serialVersionUID = 1L;

	private UserGroupService userGroupService;
	
	private AuthorityService authorityService;
	
	private UserService userService;

	private BlocGroup userGroup;
	
	private BlocUser user;

	private String blocgroupname;
	
	private Integer clobid;

	private String ids;
	
	private String success;

	private Map result;
	
	private List list;

	private Map errorReason;
	
	private Integer usergroupid;
	
	private String query;
	
	private Integer blocid;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
			errorReason = new HashMap();
		}
	}

	/**
	 * 根据用户组得到部门机构
	 * @return
	 */
	
	public String quertDeptByUserGroupId() {
		try {
			initMap();
			Integer deptid = null;
			if(this.usergroupid != null) {
				deptid = userGroupService.quertDeptByUserGroupId(usergroupid);
			}
			result.put("deptid", deptid);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserGroupAction 的方法 quertKilometersByCarnumber 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	public String quertDeptByUsergroupname() {
		try {
			initMap();
			Integer blocid = null;
			if(this.blocgroupname != null && !"".equals(blocgroupname)) {
				blocid = userGroupService.quertDeptByUsergroupname(URLDecoder.decode(blocgroupname,"UTF-8"));
			}
			result.put("blocid", blocid);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserGroupAction 的方法 quertDeptByUsergroupname 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	public String quertUsergroupidByUsergroupname() {
		try {
			initMap();
			Integer blocgroupid = null;
			if(this.blocgroupname != null && !"".equals(blocgroupname)) {
				blocgroupid = userGroupService.quertUsergroupidByUsergroupname(URLDecoder.decode(blocgroupname,"UTF-8"));
			}
			result.put("blocgroupid", blocgroupid);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserGroupAction 的方法 quertUsergroupidByUsergroupname 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 用户组下拉列表
	 * @return
	 */
	public String userGroupList(){
		try {
			initMap();
			Integer userid = null;
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
			}
			BlocGroup userGroup = new BlocGroup();
			userGroup.setUserid(userid);
			if(blocgroupname != null && !"".equals(blocgroupname)){
				userGroup.setBlocgroupname(URLDecoder.decode(blocgroupname,"UTF-8"));
			}
			list = userGroupService.userGroupList(userGroup);
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserGroupAction 的方法 userGroupList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 查询并获取用户组列表
	 * @return
	 */
	public String queryUserGroupList(){
		try {
			initMap();
			if(userGroup==null){
				userGroup = new BlocGroup();
			}
			if(blocgroupname!=null && !"".equals(blocgroupname)){
				userGroup.setBlocgroupname(URLDecoder.decode(blocgroupname, "UTF-8").trim());
			}
			if(blocid != null){
				userGroup.setBlocid(blocid);
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userGroup.setUserid(SessionUtils.getUserId());
			}
			result = userGroupService.findPageUserGroupList(this.getPage(), this.getLimit(), userGroup);	 
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserGroupAction 的方法 queryUserGroupList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 查询用户组下用户信息
	 * @return
	 */
	public String queryUserList(){
		try {
			initMap();
			if(user==null){
				user = new BlocUser();
			}
			user.setBlocgroupid(usergroupid);
			result = userService.findPageUserList(this.getPage(), this.getLimit(), user);	 
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserGroupAction 的方法 queryUserList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 添加用户组信息
	 * @return
	 */
	public String addUserGroupInfo(){
		try {

			initMap();
			if(userGroup==null){
				userGroup = new BlocGroup();
			}
			//查询用户组名称是否已注册
			int isUserGroupName = userGroupService.userGroupNameIsExist(userGroup);
			if(isUserGroupName>0){
				result.put("su", -1);
			}else{
				userGroup.setCreatetime(DateUtil.getSQLDate());
				if(userGroup.getId()!=null){
					int re = userGroupService.updateUserGroupInfo(userGroup);
					if(re<=0){
						result.put("su", -2);
					}else{
						result.put("su", 1);
					}
				}else{
					userGroup.setUserid(SessionUtils.getUserId());
					int re = userGroupService.addUserGroupInfo(userGroup);
					
					//分配默认权限
					List<Integer> menuidList = authorityService.getAuthorityMenuId();
					List<BlocAuthority> list = new ArrayList<BlocAuthority>();  
					for(Integer menuid : menuidList){
						BlocAuthority userGroupMenu = new BlocAuthority();
						userGroupMenu.setBlocgroupid(re);
						userGroupMenu.setMenuid(menuid);
						userGroupMenu.setType(1);
						userGroupMenu.setCreatetime(DateUtil.getSQLDate());
						list.add(userGroupMenu);
					}
					authorityService.addUserGroupMenu(list);
					if(re<=0){
						result.put("su", -2);
					}else{
						result.put("su", 1);
					}
				}
			}
			this.success = "true";
			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("UserGroupAction 的方法 addUserGroupInfo 执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}

	/**
	 * 删除用户组信息
	 * @return
	 */
	public String deleteUserGroup(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				//根据用户组ID查询该用户组下是否存在用户信息，如果存在将不能进行删除
				int count = userGroupService.queryUserGroupUser(Integer.parseInt(id[i]));
				if(count==0){
					int re = userGroupService.deleteUserGroup(Integer.parseInt(id[i]));
					if(re>0){
						//删除用户组对应拥有的权限
						userGroupService.deleteUserGroupAuth(Integer.parseInt(id[i]));
					}
					result.put("su", 1);
				}else{
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserGroupAction 的方法 deleteUserGroup 执行出错，原因：" + e, e);
			return ERROR;
		}
	}


	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public BlocGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(BlocGroup userGroup) {
		this.userGroup = userGroup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}


	public String getBlocgroupname() {
		return blocgroupname;
	}

	public void setBlocgroupname(String blocgroupname) {
		this.blocgroupname = blocgroupname;
	}

	public Map getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(Map errorReason) {
		this.errorReason = errorReason;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public Integer getUsergroupid() {
		return usergroupid;
	}

	public void setUsergroupid(Integer usergroupid) {
		this.usergroupid = usergroupid;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public Integer getClobid() {
		return clobid;
	}

	public void setClobid(Integer clobid) {
		this.clobid = clobid;
	}
	
	
	

}
