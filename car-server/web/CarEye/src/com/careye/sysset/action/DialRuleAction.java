/**
 * Description: car-eye车辆管理平台
 * 文件名：DialRuleAction.java
 * 版本信息：1.0
 * 日期：2015-4-17
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.mq.HandleUtil;
import com.careye.sysset.domain.DialRule;
import com.careye.sysset.domain.LastDialRule;
import com.careye.sysset.service.DialRuleService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：DialRuleAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-17 上午09:05:53
 * @修改人：Yuqk
 * @修改时间：2015-4-17 上午09:05:53
 * @修改备注：
 * @version 1.0
 */
public class DialRuleAction extends BasePageAction {
	private static final long serialVersionUID = 2016318923875666595L;
	private static final Logger logger = Logger.getLogger(DialRuleAction.class);
	private DialRuleService dialRuleService;
	private DialRule dialRule;
	private Map result;
	private String success;

	private String type;
	private String radius;
	private String cartype;
	private String carcount;
	private String carstatus;
	private String zkstate;
	private String assigncount;
	private String assignmin;
	private String effectmin;
	// private String effecttime;
	private String totalassigncount;
	private String aheadassignmin;
	private String immassigncount;
	private String assignwaitmin;
	private String traincount;
	private String stime;
	private String etime;
	private String principle;
	private String arrearage;
	private String breach;
	private String blacklist;

	private LastDialRule data;
	/**
	 * 查询参数
	 */
	private String searchstime;
	private String searchetime;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 查询最新电召规则
	 * 
	 * @return
	 */
	public String findLastDialRule() {

		try {
			initMap();
			if (data == null) {
				data = new LastDialRule();
			}
			if (StringUtils.isNotEmty(type)) {
				data.setDr_type(Integer.parseInt(type));
			} else {
				data.setDr_type(1);
			}
			// result.put("dialRule",(DialRule)
			// dialRuleService.findLastDialRule(dialRule));
			data = (LastDialRule) dialRuleService.findLastDialRule(data);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("DialRuleAction的方法 findLastDialRule执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 分页查询电召规则设置信息
	 * 
	 * @return
	 */
	public String findRecordList() {

		try {
			initMap();
			if (dialRule == null) {
				dialRule = new DialRule();
			}
			if (StringUtils.isNotEmty(type)) {
				dialRule.setType(Integer.parseInt(type));
			} else {
				dialRule.setType(1);
			}
			if (StringUtils.isNotEmty(searchstime)) {
				dialRule
						.setSearchstime(URLDecoder.decode(searchstime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(searchetime)) {
				dialRule
						.setSearchetime(URLDecoder.decode(searchetime, "UTF-8"));
			}
			result = dialRuleService.findRecordList(this.getPage(), this
					.getLimit(), dialRule);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("DialRuleAction的方法 findRecordList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 添加电召规则设置信息
	 * 
	 * @param
	 * @return
	 */
	public String addRecord() {
		try {
			initMap();
			if (dialRule == null) {
				dialRule = new DialRule();
			}
			if (StringUtils.isNotEmty(type)) {
				dialRule.setType(Integer.parseInt(type));
			}
			if (StringUtils.isNotEmty(radius)) {
				dialRule.setRadius(Float.parseFloat(radius));
			}
			if (StringUtils.isNotEmty(cartype)) {
				dialRule.setCartype(URLDecoder.decode(cartype, "UTF-8"));
			}
			if (StringUtils.isNotEmty(carcount)) {
				dialRule.setCarcount(Integer.parseInt(carcount));
			}
			if (StringUtils.isNotEmty(carstatus)) {
				dialRule.setCarstatus(URLDecoder.decode(carstatus, "UTF-8"));
			}
			if (StringUtils.isNotEmty(zkstate)) {
				dialRule.setZkstate(URLDecoder.decode(zkstate, "UTF-8"));
			}
			if (StringUtils.isNotEmty(assigncount)) {
				dialRule.setAssigncount(Integer.parseInt(assigncount));
			}
			if (StringUtils.isNotEmty(assignmin)) {
				dialRule.setAssignmin(Integer.parseInt(assignmin));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (StringUtils.isNotEmty(effectmin)) {
				int m = Integer.parseInt(effectmin);
				dialRule.setEffectmin(m);
				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.MINUTE, m);
				dialRule.setEffecttime(sdf.format(ca.getTime()));
			}else{
				dialRule.setEffecttime(DateUtil.getSQLDate());
			}
			if (StringUtils.isNotEmty(totalassigncount)) {
				dialRule
						.setTotalassigncount(Integer.parseInt(totalassigncount));
			}
			if (StringUtils.isNotEmty(aheadassignmin)) {
				dialRule.setAheadassignmin(Integer.parseInt(aheadassignmin));
			}
			if (StringUtils.isNotEmty(immassigncount)) {
				dialRule.setImmassigncount(Integer.parseInt(immassigncount));
			}
			if (StringUtils.isNotEmty(assignwaitmin)) {
				dialRule.setAssignwaitmin(Integer.parseInt(assignwaitmin));
			}
			if (StringUtils.isNotEmty(traincount)) {
				dialRule.setTraincount(Integer.parseInt(traincount));
			}
			if (StringUtils.isNotEmty(stime)) {
				dialRule.setStime(URLDecoder.decode(stime, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(etime)) {
				dialRule.setEtime(URLDecoder.decode(etime, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(principle)) {
				dialRule.setPrinciple(URLDecoder.decode(principle, "UTF-8"));
			}
			if (StringUtils.isNotEmty(arrearage)) {
				dialRule.setArrearage(Integer.parseInt(arrearage));
			}
			if (StringUtils.isNotEmty(breach)) {
				dialRule.setBreach(Integer.parseInt(breach));
			}
			if (StringUtils.isNotEmty(blacklist)) {
				dialRule.setBlacklist(Integer.parseInt(blacklist));
			}
			dialRule.setUserid(SessionUtils.getUser().getId());
			dialRule.setCreatetime(DateUtil.getSQLDate());
			if (data == null) {
				data = new LastDialRule();
			}
			data.setDr_type(dialRule.getType());
			LastDialRule ldr = dialRuleService.findLastDialRule(data);
			int type = 1;
			if (ldr != null&&StringUtils.isNotEmty(ldr.getEffecttime())) {// 如果有最新规则
				Date ldrEffectTime = sdf.parse(ldr.getEffecttime());
				if(new Date().compareTo(ldrEffectTime)>=0){// 最新规则已经生效
					dialRuleService.addHistoryDialRule(dialRule);// 将新表中数据更新到历史表
				}else{
					type = 2;
				}
				dialRuleService.delLastDialRule(dialRule.getType());//并未生效则直接删除
			}
			int su = dialRuleService.addLastDialRule(dialRule);
			if(su>0){
				//调度规则通知
				HandleUtil.notifyDialRule("", "", HandleUtil.getSerialId(), dialRule,type);
			}
			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("DialRuleAction的方法 addRecord执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	public String getArrearage() {
		return arrearage;
	}

	public void setArrearage(String arrearage) {
		this.arrearage = arrearage;
	}

	public String getBreach() {
		return breach;
	}

	public void setBreach(String breach) {
		this.breach = breach;
	}

	public String getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
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

	public DialRuleService getDialRuleService() {
		return dialRuleService;
	}

	public void setDialRuleService(DialRuleService dialRuleService) {
		this.dialRuleService = dialRuleService;
	}

	public DialRule getDialRule() {
		return dialRule;
	}

	public void setDialRule(DialRule dialRule) {
		this.dialRule = dialRule;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCarcount() {
		return carcount;
	}

	public void setCarcount(String carcount) {
		this.carcount = carcount;
	}

	public String getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
	}

	public String getZkstate() {
		return zkstate;
	}

	public void setZkstate(String zkstate) {
		this.zkstate = zkstate;
	}

	public String getAssigncount() {
		return assigncount;
	}

	public void setAssigncount(String assigncount) {
		this.assigncount = assigncount;
	}

	public String getAssignmin() {
		return assignmin;
	}

	public void setAssignmin(String assignmin) {
		this.assignmin = assignmin;
	}

	public String getEffectmin() {
		return effectmin;
	}

	public void setEffectmin(String effectmin) {
		this.effectmin = effectmin;
	}

	public String getTotalassigncount() {
		return totalassigncount;
	}

	public void setTotalassigncount(String totalassigncount) {
		this.totalassigncount = totalassigncount;
	}

	public String getAheadassignmin() {
		return aheadassignmin;
	}

	public void setAheadassignmin(String aheadassignmin) {
		this.aheadassignmin = aheadassignmin;
	}

	public String getImmassigncount() {
		return immassigncount;
	}

	public void setImmassigncount(String immassigncount) {
		this.immassigncount = immassigncount;
	}

	public String getAssignwaitmin() {
		return assignwaitmin;
	}

	public void setAssignwaitmin(String assignwaitmin) {
		this.assignwaitmin = assignwaitmin;
	}

	public String getTraincount() {
		return traincount;
	}

	public void setTraincount(String traincount) {
		this.traincount = traincount;
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

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public String getSearchstime() {
		return searchstime;
	}

	public void setSearchstime(String searchstime) {
		this.searchstime = searchstime;
	}

	public String getSearchetime() {
		return searchetime;
	}

	public void setSearchetime(String searchetime) {
		this.searchetime = searchetime;
	}

	public LastDialRule getData() {
		return data;
	}

	public void setData(LastDialRule data) {
		this.data = data;
	}
}
