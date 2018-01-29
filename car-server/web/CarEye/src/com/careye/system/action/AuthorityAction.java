/**
 * Description: car-eye车辆管理平台
 * 文件名：UserGroupAction.java
 * 版本信息：1.0
 * 日期：2014-2-18
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.system.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;


import com.careye.base.action.BasePageAction;
import com.careye.common.domain.EntityTreeUtil;
import com.careye.common.domain.MenuTree;
import com.careye.common.service.MenuTreeService;
import com.careye.system.domain.BlocAuthority;
import com.careye.system.domain.BlocGroup;
import com.careye.system.service.AuthorityService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

import common.Logger;

/**
 * @项目名称：car-eyeTms
 * @类名称：AuthorityAction
 * @类描述：权限管理
 * @创建人：zhangrong
 * @创建时间：2014-2-18 下午02:14:24
 * @修改人：zhangrong
 * @修改时间：2014-2-18 下午02:14:24
 * @修改备注：
 * @version 1.0
 */
public class AuthorityAction extends BasePageAction{

	private static final Logger logger = Logger.getLogger(AuthorityAction.class);

	private static final long serialVersionUID = 1L;

	private AuthorityService authorityService;
	
	private BlocAuthority userGroupMenu;
	private BlocGroup userGroup;
	private String ids;
	private String success;
	private String query;
	private Map result;
	private String node;
	
	private List<MenuTree> menuList;
	private JSONArray menutree;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 得到用户按钮菜单列表
	 * @return
	 */
	public String getMenuidList(){
		try {
			initMap();
		    List<Integer> menuidlist = new ArrayList<Integer>();  
		    if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
		    Integer usergroupid = SessionUtils.getUser().getBlocgroupid();
		    if(SessionUtils.getUser().getLoginname() != null && "admin".equals(SessionUtils.getUser().getLoginname())){
		    	usergroupid = null;
			}
		    menuidlist = authorityService.getMenuidList(usergroupid);
		    result.put("menuidlist", menuidlist);
		    
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AuthorityAction 的方法 getMenuidList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	private List<Integer> strListToIntList(List<String> list) {
		List<Integer> result = new ArrayList<Integer>();
		for (String s : list) {
			if ("".equals(s))
				continue;
			result.add(Integer.parseInt(s));
		}
		return result; 
	}
	
	/**分配权限**/
	public String assignAuthority() {
		try {
			initMap();
			if (userGroup == null)
				return ERROR;
			if (ids == null)				//用户id 拼接的字符串
				return ERROR;
			//先删除该用户组下所有菜单
		    authorityService.delUserGroupMenu(userGroup.getBlocgroupid());
		    
		    List<BlocAuthority> list = new ArrayList<BlocAuthority>();  
		    for(String str : ids.split(",")){  
		    	if(!str.equals("root")){
		    		BlocAuthority userGroupMenu = new BlocAuthority();
		 			userGroupMenu.setBlocgroupid(userGroup.getBlocgroupid());
		    		userGroupMenu.setMenuid(Integer.parseInt(str));
		    		userGroupMenu.setCreatetime(DateUtil.getSQLDate());
		    		list.add(userGroupMenu);  
		    	}
		    }  
			
			if(userGroup.getBlocgroupid()== -1){
				authorityService.delAuthorityMenu();
			}
			
			if(userGroup.getBlocgroupid()== -1){	//分配默认权限
				authorityService.addAuthorityMenu(list);
			}else{		//分配用户组权限
				authorityService.addUserGroupMenu(list);
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AuthorityAction 的方法 assignAuthority 执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	
	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
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

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public BlocAuthority getUserGroupMenu() {
		return userGroupMenu;
	}

	public void setUserGroupMenu(BlocAuthority userGroupMenu) {
		this.userGroupMenu = userGroupMenu;
	}

	public BlocGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(BlocGroup userGroup) {
		this.userGroup = userGroup;
	}

	public List<MenuTree> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuTree> menuList) {
		this.menuList = menuList;
	}

	public JSONArray getMenutree() {
		return menutree;
	}

	public void setMenutree(JSONArray menutree) {
		this.menutree = menutree;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}


}
