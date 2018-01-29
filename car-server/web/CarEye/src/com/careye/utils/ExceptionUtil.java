/**    
 * Description: 晟鸿科技通讯平台    
 * 文件名：ExceptionUtil.java   
 * 版本信息：    
 * 日期：2015-5-9  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;
import com.careye.email.SendMail;


/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ExceptionUtil    
 * 类描述：异常处理类    
 * 创建人：zr    
 * 创建时间：2015-5-9 下午02:57:49    
 * 修改人：zr    
 * 修改时间：2015-5-9 下午02:57:49    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ExceptionUtil {

	private final static Logger logger = Logger.getLogger(ExceptionUtil.class);	
	/**    
	 * 得到Exception所在代码的行数    
	 * 如果没有行信息,返回null    
	 */      
	public static void getInfo(Exception e){  
		
		StackTraceElement[] trace =e.getStackTrace();   
		StringBuffer errormsg = new StringBuffer();
		for (StackTraceElement stackTraceElement : trace) {
			String exclass = stackTraceElement.getClassName();
			String method = stackTraceElement.getMethodName();
			errormsg.append("[类:" + exclass + "]调用"+ method + "在第" + stackTraceElement.getLineNumber()+ "行代码处发生异常,异常类型:" + e.getClass().getName());
			errormsg.append("<br />");
		}
		logger.error(errormsg.toString());
		String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
		//邮件发送成功
		SendMail.send(ConfigProperties.receiveExceptionEmail, "【邮件服务】系统异常邮件", "系统时间：["+systemdate+"] \r\n"+ errormsg.toString());
	}

}
