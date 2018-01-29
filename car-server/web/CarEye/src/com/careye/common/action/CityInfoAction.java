/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoAction.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.common.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.careye.base.action.BasePageAction;
import com.careye.common.domain.CityInfo;
import com.careye.common.domain.CityInfoCache;
import com.careye.common.domain.Entry;
import com.careye.common.service.CityInfoService;

import common.Logger;

/**
 * @项目名称：car-eyeFms
 * @类名称：CityInfoAction
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-26 下午03:24:20
 * @修改人：lxh
 * @修改时间：2014-5-26 下午03:24:20
 * @修改备注：
 * @version 1.0
 */
public class CityInfoAction extends BasePageAction {

	private static final Logger logger = Logger.getLogger(CityInfoAction.class);
	private CityInfo cityInfo;
	private CityInfoService cityInfoService;
	
	private Map result;
	private int count; 
	
	private String clevel;
	private String provinceszcode; 	//	省份编码
	private String cityszcode;		//	市编码
	
	private Map<String, String> paramsMap; // 用于接收参数
	
	//初始化返回JSON的Map对象
	private void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	
	/**加载省份**/
	public String getProvinceList() {
		initMap();
		List<Entry> list = null;
		list = CityInfoCache.getProvinceList();
		result.put("list", list);
		return SUCCESS;
	}
	
	/**加载市**/
	public String getCityList() {
		try{
			initMap();
			List<Entry> list = null;
			if (clevel != null && clevel.equals("2")){
				list = cityInfoService.getCityList(URLDecoder.decode(provinceszcode, "UTF-8"));
			}else {
				
			}
			result.put("list", list);
			return SUCCESS;
		}catch (Exception e) {
			logger.error("CityInfoAction 的方法 getCityList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**加载县**/
	public String getDistrictList() {
		try{
			initMap();
			List<Entry> list = null;
		    if(clevel != null && clevel.equals("3")){
				list = cityInfoService.getDistrictList(URLDecoder.decode(cityszcode, "UTF-8"));
			}else{
	
			}
			result.put("list", list);
			return SUCCESS;
		}catch (Exception e) {
			logger.error("CityInfoAction 的方法 getDistrictList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	

	public CityInfo getCityInfo() {
		return cityInfo;
	}


	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}

	public CityInfoService getCityInfoService() {
		return cityInfoService;
	}

	public void setCityInfoService(CityInfoService cityInfoService) {
		this.cityInfoService = cityInfoService;
	}

	public Map<String, String> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, String> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public String getClevel() {
		return clevel;
	}

	public void setClevel(String clevel) {
		this.clevel = clevel;
	}


	public String getProvinceszcode() {
		return provinceszcode;
	}

	public void setProvinceszcode(String provinceszcode) {
		this.provinceszcode = provinceszcode;
	}

	public String getCityszcode() {
		return cityszcode;
	}

	public void setCityszcode(String cityszcode) {
		this.cityszcode = cityszcode;
	}
	
	

}
