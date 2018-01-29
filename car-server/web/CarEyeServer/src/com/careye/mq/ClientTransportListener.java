/**    
 * Description: 多森软件物联网车联网平台    
 * 文件名：ClientTransportListener.java   
 * 版本信息：    
 * 日期：2015-11-20  
 * Copyright car-eye车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.mq;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.activemq.transport.TransportListener;
import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;
import com.careye.email.SendMail;


/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ClientTransportListener    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-11-20 下午02:20:46    
 * 修改人：Administrator    
 * 修改时间：2015-11-20 下午02:20:46    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ClientTransportListener implements TransportListener {

	private final static Logger logger = Logger.getLogger(ClientTransportListener.class);
	@Override
	public void onCommand(Object arg0) {
	}

	@Override
	public void onException(IOException arg0) {
		
		// TODO Auto-generated method stub
		logger.info("onException,与服务器连接发生错误......");
		String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
		SendMail.send(ConfigProperties.receiveExceptionEmail, "MQ服务器连接发生错误常", "系统时间：["+systemdate+"]"+arg0.getMessage());
	}

	@Override
	public void transportInterupted() {
		logger.info("transportInterupted,与服务器连接发生中断......");
		String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
		SendMail.send(ConfigProperties.receiveExceptionEmail, "MQ与服务器连接发生中断", "系统时间：["+systemdate+"]");
	}

	@Override
	public void transportResumed() {
		logger.info("transportResumed,与服务器连接成功....");
	} 

}
