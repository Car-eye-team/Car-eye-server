/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoAction.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.sysset.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.jms.Session;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.object.SqlUpdate;

import com.careye.base.action.BasePageAction;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.domain.CarUse;
import com.careye.sysset.service.CarTypeService;
import com.careye.sysset.service.CarUseService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;


import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：CarTypeAction
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-3-3 下午05:24:20
 * @修改人：zhangrong
 * @修改时间：2013-3-3 下午05:24:20
 * @修改备注：
 * @version 1.0
 */
public class CarUseAction extends BasePageAction {
	private static final Logger logger = Logger.getLogger(CarUseAction.class);
	
	private CarUseService carUseService;
	private CarUse carUse;
	
	private Map result;
	private String success;
	private List list;
	private String ids;
	private int su;
	private String usename;

	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 分页查询车辆用途名称信息
	 * @return
	 */
	public String queryPageCarUseList() {
		
		try {
			initMap();
			if(carUse==null){
				carUse=new CarUse();
			}
			if(usename!=null&&!usename.equals("")){
				carUse.setUsename(URLDecoder.decode(usename, "UTF-8"));
			}
			result=carUseService.selectPageCarUse(this.getPage(),this.getLimit(), carUse);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarUseAction的方法 queryPageCarUseList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 保存车辆用途信息
	 * @param carUseName
	 * @return
	 */
	public String saveCarUse(){
		try {
			initMap();
			if(carUse==null){
				carUse=new CarUse();
			}
			int count=-1;
			carUse.setUserid(SessionUtils.getUser().getId());
			if(carUse.getId()==null){
			   count = carUseService.insertCarUse(carUse);
			}else{
				count=carUseService.updateCarUse(carUse);
			}
			result.put("su", count);
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("CarUseAction的方法 saveCarUse执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 删除车辆用途名称信息
	 * @param carUseName
	 * @return
	 */
	public String deleteCarUse(){
		try {
			initMap();
			if (ids == null)
				return ERROR;
			String id[] = ids.split(",");
			List<String> idList = Arrays.asList(ids.split(","));
			int count=carUseService.deleteCarUse(idList);
			result.put("su", count);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarUseAction 的方法 deleteCarUseName 执行出错，原因：" + e, e);
			return ERROR;
		}
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

	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getSu() {
		return su;
	}
	public void setSu(int su) {
		this.su = su;
	}
	public static Logger getLogger() {
		return logger;
	}
	public CarUseService getCarUseService() {
		return carUseService;
	}
	public void setCarUseService(CarUseService carUseService) {
		this.carUseService = carUseService;
	}
	public CarUse getCarUse() {
		return carUse;
	}
	public void setCarUse(CarUse carUse) {
		this.carUse = carUse;
	}
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}

}
