/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceAreaAction.java
 * 版本信息：1.0
 * 日期：2015-3-31
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.common.domain.Entry;
import com.careye.common.service.CityInfoService;
import com.careye.sysset.domain.ServiceArea;
import com.careye.sysset.service.ServiceAreaService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：ServiceAreaAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-31 下午02:05:02
 * @修改人：Yuqk
 * @修改时间：2015-3-31 下午02:05:02
 * @修改备注：
 * @version 1.0
 */
public class ServiceAreaAction extends BasePageAction {
	private static final long serialVersionUID = 4477196504746255089L;
	private static final Logger logger = Logger.getLogger(SetAction.class);
	private ServiceAreaService serviceAreaService;// getCityList,getDistrictList
	private CityInfoService cityInfoService;

	private ServiceArea serviceArea;

	private String pcode;
	private String province;
	private String ccode;
	private String city;
	private String cycode;
	private String county;

	private Map result;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 查询省级服务区域
	 * 
	 * @return
	 */
	public String findServiceProvince() {

		try {
			initMap();
			if (serviceArea == null) {
				serviceArea = new ServiceArea();
			}
			result.put("list", serviceAreaService.findServiceProvince());
			return SUCCESS;
		} catch (Exception e) {
			logger.error(
					"ServiceAreaAction的方法 findServiceProvince执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	/**
	 * 查询市级服务区域
	 * 
	 * @return
	 */
	public String findServiceCity() {

		try {
			initMap();
			if (serviceArea == null) {
				serviceArea = new ServiceArea();
			}
			if (StringUtils.isEmty(pcode)) {
				return ERROR;
			}
			serviceArea.setPcode(Long.parseLong(pcode));
			result.put("list", serviceAreaService.findServiceCity(serviceArea));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ServiceAreaAction的方法 findServiceCity执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	/**
	 * 查询县级服务区域
	 * 
	 * @return
	 */
	public String findServiceCounty() {

		try {
			initMap();
			if (serviceArea == null) {
				serviceArea = new ServiceArea();
			}
			if (StringUtils.isEmty(ccode)) {
				return ERROR;
			}
			serviceArea.setCcode(Long.parseLong(ccode));
			result.put("list", serviceAreaService
					.findServiceCounty(serviceArea));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ServiceAreaAction的方法 findServiceCounty执行出错，原因：" + e,
					e);
			return ERROR;
		}

	}

	/**
	 * 开通服务区域
	 * 
	 * @return
	 */
	public String openServiceArea() {
		try {
			initMap();
			if (serviceArea == null) {
				serviceArea = new ServiceArea();
			}
			List<ServiceArea> list = new ArrayList<ServiceArea>();// 所有记录集合

			if (StringUtils.isNotEmty(pcode)) {
				List<Map<Long, String>> cityList = cityInfoService
						.findMapByParentCode(Long.parseLong(pcode));
				for (Map<Long, String> m : cityList) {
					Long cityCode = Long.parseLong(m.get("SZCODE"));
					List<Map<Long, String>> countyList = cityInfoService
							.findMapByParentCode(cityCode);
					for (Map<Long, String> n : countyList) {
						ServiceArea sa = new ServiceArea();
						Long countyCode = Long.parseLong(n.get("SZCODE"));
						sa.setCounty(n.get("CNNAME"));
						sa.setCycode(countyCode);

						sa.setCity(m.get("CNNAME"));
						sa.setCcode(cityCode);
						sa.setProvince(province);// 传递code的时候同时传递名称
						sa.setPcode(Long.parseLong(pcode));
						sa.setCreatetime(DateUtil.getSQLDate());
						sa.setUserid(SessionUtils.getUserId());
						list.add(sa);
					}
				}
			} else if (StringUtils.isNotEmty(ccode)) {
				Map<Long, String> provinceMap = cityInfoService
						.findMapByChildCode(Long.parseLong(ccode));
				List<Map<Long, String>> countyList = cityInfoService
						.findMapByParentCode(Long.parseLong(ccode));
				for (Map<Long, String> n : countyList) {
					ServiceArea sa = new ServiceArea();
					Long countyCode = Long.parseLong(n.get("SZCODE"));
					sa.setCounty(n.get("CNNAME"));
					sa.setCycode(countyCode);

					sa.setCcode(Long.parseLong(ccode));
					sa.setCity(city);
					sa.setProvince(provinceMap.get("CNNAME"));
					sa.setPcode(Long.parseLong(provinceMap.get("SZCODE")));
					sa.setCreatetime(DateUtil.getSQLDate());
					sa.setUserid(SessionUtils.getUserId());
					list.add(sa);
				}
			} else if (StringUtils.isNotEmty(cycode)) {
				List<String> cycodeList = Arrays.asList(cycode.split(","));
				List<String> countyList = Arrays.asList(county.split(","));
				if (cycodeList.size() != countyList.size()) {
					return ERROR;
				}
				for (int i = 0, len = cycodeList.size(); i < len; i++) {
					ServiceArea sa = new ServiceArea();
					Long key=Long.parseLong(cycodeList.get(i));
					String val=countyList.get(i);
					sa.setCounty(val);
					sa.setCycode(key);

					Map<Long, String> cityMap = cityInfoService
							.findMapByChildCode(key);
					ccode = cityMap.get("SZCODE");
					sa.setCcode(Long.parseLong(ccode));
					sa.setCity(cityMap.get("CNNAME"));
					Map<Long, String> provinceMap = cityInfoService
							.findMapByChildCode(Long.parseLong(ccode));
					sa.setProvince(provinceMap.get("CNNAME"));
					sa.setPcode(Long.parseLong(provinceMap.get("SZCODE")));
					sa.setCreatetime(DateUtil.getSQLDate());
					sa.setUserid(SessionUtils.getUserId());
					list.add(sa);	
				}
			}
			for (ServiceArea sa : list) {
				serviceAreaService.openServiceArea(sa);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ServiceAreaAction的方法 openServiceArea执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	/**
	 * 关闭省级服务区域
	 * 
	 * @return
	 */
	public String closeServiceProvince() {

		try {
			initMap();
			if (serviceArea == null) {
				serviceArea = new ServiceArea();
			}
			if (StringUtils.isEmty(pcode)) {
				return ERROR;
			}
			serviceAreaService.closeServiceProvince(Long.parseLong(pcode));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ServiceAreaAction的方法 closeServiceProvince执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}

	/**
	 * 关闭市级服务区域
	 * 
	 * @return
	 */
	public String closeServiceCity() {

		try {
			initMap();
			if (serviceArea == null) {
				serviceArea = new ServiceArea();
			}
			if (StringUtils.isEmty(ccode)) {
				return ERROR;
			}
			serviceAreaService.closeServiceCity(Long.parseLong(ccode));
			return SUCCESS;
		} catch (Exception e) {
			logger
					.error("ServiceAreaAction的方法 closeServiceCity执行出错，原因：" + e,
							e);
			return ERROR;
		}
	}

	/**
	 * 关闭县级服务区域
	 * 
	 * @return
	 */
	public String closeServiceCounty() {

		try {
			initMap();
			if (serviceArea == null) {
				serviceArea = new ServiceArea();
			}
			if (StringUtils.isEmty(cycode)) {
				return ERROR;
			}
			for(String c:cycode.split(",")){
				serviceAreaService.closeServiceCounty(Long.parseLong(c));	
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ServiceAreaAction的方法closeServiceCounty执行出错，原因：" + e,
					e);
			return ERROR;
		}

	}

	/**
	 * getter setter
	 * 
	 * @return
	 */
	public ServiceAreaService getServiceAreaService() {
		return serviceAreaService;
	}

	public void setServiceAreaService(ServiceAreaService serviceAreaService) {
		this.serviceAreaService = serviceAreaService;
	}

	public CityInfoService getCityInfoService() {
		return cityInfoService;
	}

	public void setCityInfoService(CityInfoService cityInfoService) {
		this.cityInfoService = cityInfoService;
	}

	public ServiceArea getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(ServiceArea serviceArea) {
		this.serviceArea = serviceArea;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCycode() {
		return cycode;
	}

	public void setCycode(String cycode) {
		this.cycode = cycode;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

}
