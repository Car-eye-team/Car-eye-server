/**
* Description: car-eye车辆管理平台
* 文件名：CarPositionInfoAction.java
* 版本信息：1.0
* 日期：2014-7-29
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.base.action.TreeDomain;
import com.careye.common.domain.MapInfo;
import com.careye.common.domain.PositionInfo;
import com.careye.common.service.MenuTreeService;
import com.careye.common.service.TerminalDeviceInfoService;
import com.careye.system.domain.UserCar;
import com.careye.utils.SessionUtils;


import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：CarPositionInfoAction
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-7-29 下午04:25:02
 * @修改人：zr
 * @修改时间：2014-7-29 下午04:25:02
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CarPositionInfoAction extends BasePageAction {

	private static final Logger logger = Logger.getLogger(CarPositionInfoAction.class);
	private TerminalDeviceInfoService terminalDeviceInfoService;
	private MenuTreeService menuTreeService;
	private MapInfo data;
	private String carnumber;
	private String deptid;
	private String ids;
	private Map result;
	private String success;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	
	/**
	 * 车辆位置以及资料信息
	 * @return
	 */
	public String queryCarTerminalDetail(){
		try {
			initMap();
			if(this.carnumber == null){
				return ERROR;
			}
			PositionInfo positionInfo = new PositionInfo();
			positionInfo = terminalDeviceInfoService.queryCarTerminalDetail(carnumber);
			result.put("data", positionInfo);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarPositionInfoAction 的方法 queryCarDetail执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 批量获取车辆位置信息
	 * @return
	 */
	public String queryCarAllDetail(){
		try {
			initMap();
			if(this.ids == null){
				return ERROR;
			}
			
			List<Integer> idlist = new ArrayList<Integer>();
			String idsarr [] = ids.split(",");
			for (int i = 0; i < idsarr.length; i++) {
				idlist.add(Integer.parseInt(idsarr[i]));
			}
			
			List<PositionInfo> list = terminalDeviceInfoService.queryCarAllDetail(idlist);
			result.put("list",list);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarPositionInfoAction 的方法 queryCarAllDetail执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	public TerminalDeviceInfoService getTerminalDeviceInfoService() {
		return terminalDeviceInfoService;
	}

	public void setTerminalDeviceInfoService(
			TerminalDeviceInfoService terminalDeviceInfoService) {
		this.terminalDeviceInfoService = terminalDeviceInfoService;
	}



	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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

	public static Logger getLogger() {
		return logger;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}

	public MapInfo getData() {
		return data;
	}

	public void setData(MapInfo data) {
		this.data = data;
	}


}
