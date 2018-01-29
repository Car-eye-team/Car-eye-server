/**
 * Description: car-eye车辆管理平台
 * 文件名：RegionSetAction.java
 * 版本信息：1.0
 * 日期：2015-4-7
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.domain.Region;
import com.careye.sysset.service.RegionService;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：RegionAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-7 上午11:02:12
 * @修改人：Yuqk
 * @修改时间：2015-4-7 上午11:02:12
 * @修改备注：
 * @version 1.0
 */
public class RegionAction extends BasePageAction {
	private static final Logger logger = Logger.getLogger(RegionAction.class);
	private Map result;
	private int su;
	private String success;
	private String ids;
	private String clevel;
	private Region region;
	
	private String parentid;
	private String parentname;
	private String enname;
	private String stenname;
	private String szcode;
	private String cnname;
	private String bmcode;
	private String stcnname;
	private RegionService regionService;
	private Integer id;
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 分页查询区划列表
	 * 
	 * @return
	 */
	public String findPageRegionList() {
		try {
			initMap();
			if (region == null) {
				region = new Region();
			}
			if(StringUtils.isNotEmty(cnname)){
				region.setCnname(URLDecoder.decode(cnname,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(clevel)){
				region.setClevel(Integer.parseInt(URLDecoder.decode(clevel,"UTF-8").trim()));
			}
			if(StringUtils.isNotEmty(parentname)){
				region.setParentname(URLDecoder.decode(parentname,"UTF-8").trim());
			}
			result = regionService.findPageRegionList(region, this.getPage(),
					this.getLimit());
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RegionAction的方法 findPageRegionList执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	/**
	 * 保存或更新行政区划
	 * 
	 * @return
	 */
	public String saveOrUpdateRegion() {
		try {
			initMap();
			if (region == null) {
				region = new Region();
			}
			if(StringUtils.isNotEmty(enname)){
				region.setEnname(URLDecoder.decode(enname,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stenname)){
				region.setStenname(URLDecoder.decode(stenname,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(szcode)){
				region.setSzcode(Long.parseLong(URLDecoder.decode(szcode,"UTF-8").trim()));
			}
			if(StringUtils.isNotEmty(bmcode)){
				region.setBmcode(Long.parseLong(URLDecoder.decode(bmcode,"UTF-8").trim()));
			}
			if(StringUtils.isNotEmty(cnname)){
				region.setCnname(URLDecoder.decode(cnname,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stcnname)){
				region.setStcnname(URLDecoder.decode(stcnname,"UTF-8").trim());
			}
			int count = -1;
			if (region.getId() == null) {
				count = regionService.saveRegion(region);
			} else {
				count = regionService.updateRegion(region);
			}
			result.put("su", count);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("RegionAction的方法 saveOrUpdateRegion执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 删除行政区划
	 * 
	 * @return
	 */
	public String deleteRegion() {
		try {
			initMap();
			if (ids == null)
				return ERROR;
			List<String> idList = Arrays.asList(ids.split(","));
			int count = -1;
			count =regionService.deleteRegion(idList);
			result.put("su", count);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("RegionAction的方法 deleteRegion执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 *依据行政级别查询父级所有区划
	 * 
	 * @return
	 */
	public String findParentRegionListByClevel() {
		try {
			initMap();
			if (clevel == null)
				return ERROR;
			int cl=Integer.parseInt(URLDecoder.decode(clevel,"UTF-8"));
			List<Region> list=new ArrayList<Region>();
			if(id==null){
				list = regionService.findRegionListByClevel( cl- 1);
			}else{
				list = regionService.findRegionListByClevel(cl- 1<=0?1:cl - 1);
			}
			
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RegionAction的方法 findRegionByClevel执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/*
	 * getter setter
	 */
	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public int getSu() {
		return su;
	}

	public void setSu(int su) {
		this.su = su;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public RegionService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	public String getClevel() {
		return clevel;
	}

	public void setClevel(String clevel) {
		this.clevel = clevel;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getStenname() {
		return stenname;
	}

	public void setStenname(String stenname) {
		this.stenname = stenname;
	}

	public String getSzcode() {
		return szcode;
	}

	public void setSzcode(String szcode) {
		this.szcode = szcode;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getBmcode() {
		return bmcode;
	}

	public void setBmcode(String bmcode) {
		this.bmcode = bmcode;
	}

	public String getStcnname() {
		return stcnname;
	}

	public void setStcnname(String stcnname) {
		this.stcnname = stcnname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

}
