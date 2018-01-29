/**
 * 
 */
package com.careye.advert.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.advert.domain.AdvertType;
import com.careye.advert.service.AdvertTypeService;
import com.careye.base.action.BasePageAction;
import com.careye.common.action.AlarmAction;
import com.careye.common.domain.Alarm;
import com.careye.mongodb.MongoDB;
import com.careye.sysset.domain.CarType;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @author Administrator
 *
 */
public class AdvertTypeAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(AdvertTypeAction.class);
	
	private AdvertType advertType;
	private AdvertTypeService advertTypeService;
	
	private String typename;
	private String stime;
	private String etime;
	private String ids;
	private String success;
	private Map result;
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * getAdvertTypeList
	 * 广告类型列表分页查询
	 * @return
	 */
	public String getAdvertTypeList(){
		try {
			initMap();
			if(advertType==null){
				advertType=new AdvertType();
			}
			if(StringUtils.isNotEmty(stime)){
				advertType.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				advertType.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(typename)){
				advertType.setTypename(URLDecoder.decode(typename,"UTF-8").trim());
			}
			result = advertTypeService.getAdvertTypeList(this.getPage(),this.getLimit(), advertType);
			
			return SUCCESS;

		} catch (Exception e) {
			logger.error("AdvertTypeAction的方法 getAdvertTypeList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 保存
	 * saveAdvertType
	 * @return
	 */
	public String saveAdvertType(){
		try {
			initMap();
			if (advertType == null) {
				return ERROR;
			}
			int record = advertTypeService.nameIsExist(advertType);
			if (record > 0) {
				// 名称是否存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if (advertType.getId() != null) {
				count = advertTypeService.updateType(advertType);
			} else {
				count = advertTypeService.addType(advertType);
			}
			if (count > 0) {
				result.put("returnType", 0);
				this.success = "true";
			} else {
				result.put("returnType", -2);
			}

			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("AdvertTypeAction的方法 saveAdvertType执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 删除deleteAdvertType
	 * @return
	 */
	public String deleteAdvertType(){
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int count = advertTypeService.queryAdvert(Integer.parseInt(id[i]));
				if(count==0){
					int re = advertTypeService.deleteAdvertType(Integer.parseInt(id[i]));
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AdvertTypeAction 的方法 deleteAdvertType执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	public AdvertType getAdvertType() {
		return advertType;
	}
	public void setAdvertType(AdvertType advertType) {
		this.advertType = advertType;
	}
	public AdvertTypeService getAdvertTypeService() {
		return advertTypeService;
	}
	public void setAdvertTypeService(AdvertTypeService advertTypeService) {
		this.advertTypeService = advertTypeService;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
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
	
	
	
}
