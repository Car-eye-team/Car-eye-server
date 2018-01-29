/**
* Description: car-eye车辆管理平台
* 文件名：UserAction.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.system.domain.SysFunctionMenu;
import com.careye.system.service.SystemFunctionService;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：SystemFunctionAction
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2013-2-24 下午15:52:38
 * @修改人：zhangrong
 * @修改时间：2013-2-24 下午15:52:38
 * @修改备注：
 * @version 1.0
 */
public class SystemFunctionAction extends BasePageAction{
	
	private static final Logger logger = Logger.getLogger(SystemFunctionAction.class);
	
	private SystemFunctionService systemFunctionService;
	
	private SysFunctionMenu systemFunction;
	private Map result;
	private List menuList;
	private String success;
	private String ids;
	
	private String parentmenuname;
	private String menuname;
	private String menulevel;
	private String parentmenuid;
	private String medetype;
	private String query;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 系统菜单下拉列表 key :menu,value:menuname 
	 * @return
	 */
	public String menuList() {
		
		try {
			initMap();
			Integer level ;
			if(this.menulevel == null || this.menulevel.equals("")){
				level = null;
			}else{
				level = Integer.parseInt(menulevel)-1;
			}
			systemFunction = new SysFunctionMenu();
			systemFunction.setMenulevel(level);
			if(this.parentmenuid != null && !this.parentmenuid.equals("")){
				systemFunction.setParentmenuid(Integer.parseInt(this.parentmenuid));
			}
			menuList = systemFunctionService.getMenuList(systemFunction);	
			result.put("list", menuList);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemFunction的方法 systemFunctionList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 系统功能列表
	 * @return
	 */
	public String systemFunctionList() {
		
		try {
			initMap();
			if(systemFunction==null){
				systemFunction = new SysFunctionMenu();
			}
			if(StringUtils.isNotEmty(parentmenuname)){
				systemFunction.setParentmenuname(URLDecoder.decode(parentmenuname, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(menuname)){
				systemFunction.setMenuname(URLDecoder.decode(menuname, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(menulevel)){
				systemFunction.setMenulevel(Integer.parseInt(menulevel));
			}
			if(StringUtils.isNotEmty(medetype)){
				systemFunction.setMedetype(Integer.parseInt(medetype));
			}
			result = systemFunctionService.findPageSystemFunctionList(this.getPage(), this.getLimit(), systemFunction);	
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemFunction的方法 systemFunctionList执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	
	/**
	 * 增加、修改系统功能
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addOrUpdateSystemFunction() {
		
		try {
			initMap();
			if (systemFunction == null)
				return ERROR;
			//检查用户名是否重复
			int record = systemFunctionService.menuNameIsExist(systemFunction);
			if (record > 0) {
				//系统菜单名已经存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if(systemFunction.getMenuid()!=null){
				 count = systemFunctionService.updateSystemFunction(systemFunction);
			}else{
				 count = systemFunctionService.addSystemFunction(systemFunction);
			}
			if(count > 0){
				result.put("returnType", 0);
				this.success = "true";
			}else{
				result.put("returnType", -1);
			}
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("SystemFunction的方法 addOrUpdateSystemFunction执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 激活系统功能
	 * @return
	 */
	public String activeSystemFunction(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				systemFunctionService.activeSystemFunction(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemFunctionAction 的方法 activeSystemFunction执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 禁用系统功能
	 * @return
	 */
	public String inactiveSystemFunction(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				systemFunctionService.inactiveSystemFunction(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemFunctionAction 的方法 inactiveSystemFunction执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SystemFunctionService getSystemFunctionService() {
		return systemFunctionService;
	}

	public void setSystemFunctionService(SystemFunctionService systemFunctionService) {
		this.systemFunctionService = systemFunctionService;
	}

	public SysFunctionMenu getSystemFunction() {
		return systemFunction;
	}

	public void setSystemFunction(SysFunctionMenu systemFunction) {
		this.systemFunction = systemFunction;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getParentmenuname() {
		return parentmenuname;
	}

	public void setParentmenuname(String parentmenuname) {
		this.parentmenuname = parentmenuname;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(String menulevel) {
		this.menulevel = menulevel;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}



	public String getMedetype() {
		return medetype;
	}

	public void setMedetype(String medetype) {
		this.medetype = medetype;
	}

	public String getParentmenuid() {
		return parentmenuid;
	}

	public void setParentmenuid(String parentmenuid) {
		this.parentmenuid = parentmenuid;
	}


}
