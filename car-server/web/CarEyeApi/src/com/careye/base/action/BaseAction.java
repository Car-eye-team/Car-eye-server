/**    
 * Description: car-eye车辆管理平台    
 * 文件名：BaseAction.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.base.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.careye.component.springhelper.BeanLocator;
import com.opensymphony.xwork2.ActionSupport;


/**    
 *     
 * 项目名称：car-eye    
 * 类名称：BaseAction    
 * 类描述：基础action类
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -5576203072511510919L;

	protected static final Logger LOG = Logger.getLogger(BaseAction.class);

	private String jsonText; /**用于页面获取*/

	private long actionExecTime = 0; /**action执行时间*/

	protected static final String  INDEX = "index";

	protected static final String ADD = "add";

	protected static final String EDIT = "edit";

	protected static final String DELETE = "delete";

	protected static final String CREATE = "create";

	protected static final String LIST = "list";

	protected static final String UPDATE = "update";

	protected static final String SAVE = "save";

	protected static final String JSON = "json";

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public long getActionExecTime() {
		return (System.currentTimeMillis() - actionExecTime);
	}

	public void setActionExecTime(long actionExecTime) {
		this.actionExecTime = actionExecTime;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	/**
	 * 得到相应接口.
	 * @param key 接口名称
	 * @return 接口
	 */
	public final Object getBean(String key) {
		return BeanLocator.getInstance().getBean(key);
	}


}
