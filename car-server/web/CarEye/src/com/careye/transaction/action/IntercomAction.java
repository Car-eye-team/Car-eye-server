/**
 * 
 */
package com.careye.transaction.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.transaction.domain.Intercom;
import com.careye.transaction.domain.RideOrder;
import com.careye.transaction.service.IntercomService;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @author Administrator
 *
 */
public class IntercomAction extends BasePageAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RideOrderAction.class);
	
	private IntercomService intercomService;
	private Intercom intercom;
	
	private String name;
	private String carnumber;
	private String s_time;
	private String e_time;
	
	private String success;
	private Map result;
	private String ids;
	private List list;
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}
	/**
	 * 组列表selGroupList
	 * @return
	 */
	public String selGroupList(){
		try {
			initMap();
			if (intercom == null) {
				intercom = new Intercom();
			}
			if (StringUtils.isNotEmty(name)){
				intercom.setName(URLDecoder.decode(name, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(s_time)) {
				intercom.setStime(URLDecoder.decode(s_time, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(e_time)) {
				intercom.setEtime(URLDecoder.decode(e_time, "UTF-8").trim());
			}
			result = intercomService.selGroupList(this.getPage(), this.getLimit(), intercom);
			
			
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("IntercomAction 的方法 selGroupList 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}
	/**
	 * 删除组deleteGroup
	 */
	public String deleteGroup(){
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int count = intercomService.queryGroup(Integer.parseInt(id[i]));
				if(count==0){
					int re = intercomService.deleteGroup(Integer.parseInt(id[i]));
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("IntercomAction 的方法 deleteGroup执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 组员列表selGroupList
	 * @return
	 */
	public String selMemberList(){
		try {
			initMap();
			if (intercom == null) {
				intercom = new Intercom();
			}
			if (StringUtils.isNotEmty(carnumber)){
				intercom.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").trim());
			}
			if (!StringUtils.isEmty(ids)) {
				String[] datas = ids.split(",");
				for (int j = 0; j < datas.length; j++) {
					intercom.getIds().add(datas[j]);
				}
			}
			result = intercomService.selMemberList(this.getPage(), this.getLimit(), intercom);
			
			
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("IntercomAction 的方法 selGroupList 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}
	/**
	 * 删除组员deleteMember
	 * @return
	 */
	public String deleteMember(){
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = intercomService.deleteMember(Integer.parseInt(id[i]));
				if (re <= 0) {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("IntercomAction 的方法 deleteMember执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	public IntercomService getIntercomService() {
		return intercomService;
	}

	public void setIntercomService(IntercomService intercomService) {
		this.intercomService = intercomService;
	}

	public Intercom getIntercom() {
		return intercom;
	}

	public void setIntercom(Intercom intercom) {
		this.intercom = intercom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String sTime) {
		s_time = sTime;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String eTime) {
		e_time = eTime;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	
	
}
