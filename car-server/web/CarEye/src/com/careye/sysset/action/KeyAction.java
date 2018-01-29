/**
 * Description: car-eye车辆管理平台
 * 文件名：AppManageAction.java
 * 版本信息：1.0
 * 日期：2015-8-20
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.sysset.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.sysset.domain.Key;
import com.careye.sysset.service.KeyService;
import com.careye.utils.Base64Tool;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import com.ctc.wstx.util.StringUtil;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：AppManageAction
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2015-8-20 上午11:46:26
 * @修改人：Administrator
 * @修改时间：2015-8-20 上午11:46:26
 * @修改备注：
 * @version 1.0
 */
public class KeyAction extends BasePageAction {
	private static final Logger logger = Logger.getLogger(KeyAction.class);
	private KeyService keyservice;
	private Key key;

	private Map result;
	private List list;
	private String success;
	private String ids;
	private String keyname;
	private String descs;
	private String typeid;
	private String status;
	private String s_time;
	private String e_time;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	public String selAppTypeList() {
		try {
			initMap();
			list = keyservice.selAppTypeList();
			result.put("list", list);

			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction的方法 selAppVersionList执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public String keyList() {
		try {
			initMap();
			if (key == null) {
				key = new Key();
			}
			if (StringUtils.isNotEmty(keyname)) {
				key.setKey(URLDecoder.decode(keyname, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(descs)) {
				key.setDescs(URLDecoder.decode(descs, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(typeid)) {
				key.setTypeid(typeid);
			}
			if (StringUtils.isNotEmty(status)) {
				key.setStatus(status);
			}
			if (StringUtils.isNotEmty(s_time)) {
				key.setStime(s_time);
			}
			if (StringUtils.isNotEmty(e_time)) {
				key.setEtime(e_time);
			}
			result = keyservice.findPageKeyList(this.getPage(),
					this.getLimit(), key);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("KeyAction的方法 keyList执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public String keySave() {
		try {
			initMap();
			if (key == null) {
				return ERROR;
			}
			int record = keyservice.keyIsExist(key);
			if (record > 0) {
				// 密钥名已经存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if (key.getId() != null) {
				count = keyservice.updateKey(key);
			} else {
				key.setUserid(SessionUtils.getUserId());
				String ak = Base64Tool.getBASE64(
						String.valueOf("duosengate"
								+ System.currentTimeMillis())).toLowerCase();
				key.setKey(DateUtil.StringChange(ak));
				count = keyservice.addKey(key);
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
			logger.error("KeyAction的方法 keySave执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	public String deleteKey() {
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = keyservice.deleteKey(Integer.parseInt(id[i]));
				if (re <= 0) {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction 的方法 deleteKey执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public String activeKey() {
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = keyservice.activeKey(Integer.parseInt(id[i]));
				if (re <= 0) {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction 的方法 activeKey执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public String inactiveKey() {
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = keyservice.inactiveKey(Integer.parseInt(id[i]));
				if (re <= 0) {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AppManageAction 的方法 activeKey执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	public KeyService getKeyservice() {
		return keyservice;
	}

	public void setKeyservice(KeyService keyservice) {
		this.keyservice = keyservice;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
