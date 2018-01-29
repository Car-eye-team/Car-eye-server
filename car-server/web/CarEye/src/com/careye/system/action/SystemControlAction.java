/**
 * Description: car-eye车辆管理平台
 * 文件名：SystemSetAction.java
 * 版本信息：1.0
 * 日期：2013-11-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.system.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;

import com.careye.base.action.BasePageAction;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.SystemControl;
import com.careye.system.service.SystemControlService;

/**
 * @项目名称：car-eye
 * @类名称：SystemControlAction
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-5-19 下午04:37:50
 * @修改人：zhangrong
 * @修改时间：2014-5-19 下午04:37:50
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class SystemControlAction extends BasePageAction {

	private static final Logger logger = Logger.getLogger(SystemControlAction.class);

	private SystemControlService systemControlService;
	private SystemControl systemControl;
	private SystemControl data;
	
	private Map result;
	private List list;
	
	private String ids;
	private String scname;
	private String customerid;
	private BlocUser user;
	private String scnum;
	
	private String success;
	
	private void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	
	/**
	 * 获取控制项列表
	 * @return
	 */
	public String querySystemControlList() {
		
		try {
			initMap();
			if(systemControl==null){
				systemControl = new SystemControl();
			}
			if(scname != null && !scname.equals("null") && !scname.equals("")){
				systemControl.setScname(URLDecoder.decode(scname, "UTF-8"));
			}
			result = systemControlService.findPageSystemControlList(this.getPage(), this.getLimit(), systemControl);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemSetAction的方法 querySystemControlList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	

	/**
	 * 通过控制名找出对应控制值
	 * @return
	 */
	public String getScnumByScnum() {
		
		try {
			initMap();
			if(scname != null && !scname.equals("null") && !scname.equals("")){
				data = systemControlService.getScnumByScnum(URLDecoder.decode(scname, "UTF-8"));
				result.put("data", data);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemSetAction的方法 getScnumByScnum执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 获取控制项列表
	 * @return
	 */
	public String selectSystemControlList() {
		
		try {
			initMap();
			list = systemControlService.findSystemControlList();
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemSetAction的方法 selectSystemControlList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 保存系统参数设置
	 * @return
	 */
	public String saveSystemControl() {
		
		try {
			initMap();
			if (systemControl == null)
				return ERROR;
			int count = 0;
			if(systemControl.getId()!=null){	//更新
				int record = systemControlService.validateupSystemControl(systemControl);
				if (record > 0) {
					result.put("su", -1);
					this.success = "true";
					return SUCCESS;
				}
				count = systemControlService.updateSystemControl(systemControl);
			}else{	//增加
				int record = systemControlService.validateSystemControlAlias(systemControl.getScalias());
				int record1 = systemControlService.validateupSystemControl(systemControl);
				if (record > 0||record1>0) {
					result.put("su", -1);
					this.success = "true";
					return SUCCESS;
				}
				count = systemControlService.addSystemControl(systemControl);
			}
			if(count > 0){
				result.put("su", 1);
				this.success = "true";
			}else{
				result.put("su", -2);
			}
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("SystemSetAction的方法 saveSystemSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 删除系统参数设置
	 * @return
	 */
	public String deleteSystemControl(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				systemControlService.deleteSystemControl(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SystemSetAction 的方法 deleteSystemSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	public SystemControlService getSystemControlService() {
		return systemControlService;
	}

	public void setSystemControlService(
			SystemControlService systemControlService) {
		this.systemControlService = systemControlService;
	}

	public SystemControl getSystemControl() {
		return systemControl;
	}

	public void setSystemControl(SystemControl systemControl) {
		this.systemControl = systemControl;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getScname() {
		return scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}


	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public BlocUser getUser() {
		return user;
	}
	public void setUser(BlocUser user) {
		this.user = user;
	}

	public String getScnum() {
		return scnum;
	}

	public void setScnum(String scnum) {
		this.scnum = scnum;
	}

	public SystemControl getData() {
		return data;
	}

	public void setData(SystemControl data) {
		this.data = data;
	}
	

}