/**
 * Description: car-eye车辆管理平台
 * 文件名：ExceptionInterceptor.java
 * 版本信息：1.0
 * 日期：2013-7-22
 * Copyright car-eye车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;
import com.careye.email.SendMail;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @car-eye车辆管理平台业务处理
 * @类名称：ExceptionInterceptor
 * @类描述：异常拦截拦截器
 * @创建人：zr
 * @创建时间：2013-7-22 下午05:26:00
 * @修改人：zr
 * @修改时间：2013-7-22 下午05:26:00
 * @修改备注：
 * @version 1.0
 */
public class ExceptionInterceptor  extends AbstractInterceptor {


	private static final long serialVersionUID = 1008901298342362080L;  
	private static final Logger log = Logger.getLogger(ExceptionInterceptor.class);  

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {  
		String actionName = invocation.getInvocationContext().getName();
		try {  
			String result = invocation.invoke();  
			return result;  
		} catch (Exception e) {  
			
			String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
			//邮件发送成功
			SendMail.send(ConfigProperties.receiveExceptionEmail, "Action方法："+actionName+",产生异常", "系统时间：["+systemdate+"]"+e.toString());
			log.error(actionName, e);
			
			return "error";  
		}  
	}  
}
