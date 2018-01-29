/**
 * Description: car-eye车辆管理平台
 * 文件名：SetAction.java
 * 版本信息：1.0
 * 日期：2014-7-13
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.net.URLCodec;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.constant.WebConstants;
import com.careye.sysset.domain.AlarmtypeSet;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.domain.DeviceType;
import com.careye.sysset.domain.DialfeeSet;
import com.careye.sysset.domain.PageSet;
import com.careye.sysset.service.DialfeeSetService;
import com.careye.sysset.service.SetService;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.FileHandler;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import com.mchange.v2.sql.SqlUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：DialfeeSetAction
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-4-16 上午09:30:07
 * @修改人：huangqin
 * @修改时间：2015-4-16 上午09:30:07
 * @修改备注：
 * @version 1.0
 */
public class DialfeeSetAction extends BasePageAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DialfeeSetAction.class);
	private DialfeeSetService dialfeeSetService;
	private DialfeeSet dialfeeSet;
	
	private String dianfee;
	private String oilcost;
	private String discount;
	public String getDianfee() {
		return dianfee;
	}


	public void setDianfee(String dianfee) {
		this.dianfee = dianfee;
	}


	public String getEffecttime() {
		return effecttime;
	}


	public void setEffecttime(String effecttime) {
		this.effecttime = effecttime;
	}


	public String getOilcost() {
		return oilcost;
	}


	public void setOilcost(String oilcost) {
		this.oilcost = oilcost;
	}


	public String getDiscount() {
		return discount;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}


	private String effecttime;
    
	private Map result;
	private String success;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	
	/**
	 * 分页查询设备类型设置信息
	 * @return
	 */
	public String queryDialFeeSetList() {
		
		try {
			initMap();
			if(dialfeeSet==null){
				dialfeeSet=new DialfeeSet();
			}
			result=dialfeeSetService.findPageDialfeeSetList(this.getPage(),this.getLimit(),dialfeeSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("DialfeeSetAction的方法 queryDialFeeSetList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	
	/**
	 * 添加电召费用设置信息
	 * @param carTypeName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveDialFeeSet(){
		try {
			initMap();
			if(dialfeeSet==null){
				dialfeeSet=new DialfeeSet();
			}
			
			if(StringUtils.isNotEmty(dianfee)){
				dialfeeSet.setDialfee(URLDecoder.decode(dianfee, "utf-8"));
			}
			if(StringUtils.isNotEmty(oilcost)){
				dialfeeSet.setOilcost(URLDecoder.decode(oilcost, "utf-8"));
			}
			if(StringUtils.isNotEmty(discount)){
				dialfeeSet.setDiscount(URLDecoder.decode(discount, "utf-8"));
			}
			if(StringUtils.isNotEmty(effecttime)){
				dialfeeSet.setEffecttime(URLDecoder.decode(effecttime, "utf-8").trim());
			}
			
			int count=-1;
			dialfeeSet.setUserid(SessionUtils.getUser().getId());
			dialfeeSet.setCreatetime(DateUtil.getSQLDate());
			count = dialfeeSetService.insertDialfeeSet(dialfeeSet);
			
			result.put("su", count);
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("DialfeeSetAction的方法 saveDialFeeSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	/**
	 * 得到最新一条记录信息
	 * @return
	 */
	public String getDialfeeSetMaxId() {
		try {
			initMap();
			dialfeeSet=dialfeeSetService.getDialfeeSetMaxId();
			result.put("dialfeeSet", dialfeeSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("DialfeeSetAction的方法 getDialfeeSetMaxId执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public DialfeeSetService getDialfeeSetService() {
		return dialfeeSetService;
	}


	public void setDialfeeSetService(DialfeeSetService dialfeeSetService) {
		this.dialfeeSetService = dialfeeSetService;
	}


	public DialfeeSet getDialfeeSet() {
		return dialfeeSet;
	}


	public void setDialfeeSet(DialfeeSet dialfeeSet) {
		this.dialfeeSet = dialfeeSet;
	}


	public Map getResult() {
		return result;
	}


	public void setResult(Map result) {
		this.result = result;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static Logger getLogger() {
		return logger;
	}
	


	
}
