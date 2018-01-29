/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.constant;

import java.io.File;

import org.apache.log4j.Logger;

import com.careye.dsparse.utlis.Configuration;
import com.careye.dsparse.utlis.ExceptionUtil;

/**
 *     
 * 项目名称：dsparse    
 * 类名称：ConfigProperties    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-12 下午05:03:47    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午05:03:47    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class ConfigProperties {

	protected static final Logger logger = Logger.getLogger(ConfigProperties.class);


	/** 保留的线程池大小*/
	public static int corePoolSize = 0;
	/** 线程池的最大大小*/
	public static int maximumPoolSize = 0;
	/** 超时时间（秒）*/
	public static int keepAliveTime = 0;

	/**MQ服务器连接地址(MQ负载时,各个链接地址间用“,”隔开)*/
	public static String MQ_SERVER = null;

	/**业务消息队列-上行队列名称*/
	public static String DS_BUSINESS_UP_QUEUE_NAME = null;

	/**业务消息队列-下行队列名称*/
	public static String DS_BUSINESS_DOWN_QUEUE_NAME = null;

	/**鉴权消息队列-上行队列名称*/
	public static String DS_AUTH_UP_QUEUE_NAME = null;

	/**鉴权消息队列-下行队列名称*/
	public static String DS_AUTH_DOWN_QUEUE_NAME = null;

	/**接入消息队列-上行队列名称*/
	public static String DS_COMM_UP_QUEUE_NAME = null;

	/**接入消息队列-下行队列名称*/
	public static String DS_COMM_DOWN_QUEUE_NAME = null;

	/**UDP接入消息队列-上行队列名称*/
	public static String DS_UDP_UP_QUEUE_NAME = null;

	/**UDP接入消息队列-下行队列名称*/
	public static String DS_UDP_DOWN_QUEUE_NAME = null;

	/**调度消息队列-上行队列名称*/
	public static String DS_DISPATCH_UP_QUEUE_NAME = null;

	/**调度下行队列名称(主要跑电召相关消息)*/
	public static String DS_DISPATCH_DOWN_QUEUE_NAME = null;

	/**调度下行队列名称(主要跑512等的心跳消息)*/
	public static String DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME = null;

	/**调度消息ID*/
	public static String DISPATCH_MSGID = null;

	/**
	 * REDIS服务器
	 */
	public static String REDIS_IP = null;

	public static int REDIS_PORT = 0;
	public static String REDIS_PASSWORD = null;

	/**
	 * MQ线程池配置
	 */
	public static int MQ_MAXCONNECTIONS = 0;

	public static int MQ_MAXACTIVESESSION = 0;

	/**
	 * 消息接收时间间隔
	 */
	public static int RECEIVE_MSG_INTERVAL = 0;

	/**发送邮件用户名*/
	public static String SEND_USER_NAME = null;

	/**设置邮件服务器*/
	public static String SMTP_HOST = null;	

	/**发送邮件邮箱地址*/
	public static String SEND_USER_ADDR = null;

	/**发送邮件用户密码*/
	public static String SEND_USER_PASSWD = null;

	/**接收异常邮箱*/
	public static String RECEIVE_EMAIL = null;

	public void start(){
		try {

			String path = "." + File.separator + "conf" + File.separator + "dsparse.properties";
			Configuration sysConfg = new Configuration(path);
			if(sysConfg == null) {
				logger.info("错误：读取配置文件" + path + " , 失败!");
			}

			if(0 == corePoolSize){
				corePoolSize = Integer.parseInt(sysConfg.getValue("corePoolSize").trim());
			}

			if(0 == maximumPoolSize){
				maximumPoolSize = Integer.parseInt(sysConfg.getValue("maximumPoolSize").trim());
			}

			if(0 == keepAliveTime){
				keepAliveTime = Integer.parseInt(sysConfg.getValue("keepAliveTime").trim());
			}

			if(MQ_SERVER == null){
				MQ_SERVER = sysConfg.getValue("MQ_SERVER").trim();
			}

			if(DS_BUSINESS_UP_QUEUE_NAME == null){
				DS_BUSINESS_UP_QUEUE_NAME = sysConfg.getValue("DS_BUSINESS_UP_QUEUE_NAME").trim();
			}

			if(DS_BUSINESS_DOWN_QUEUE_NAME == null){
				DS_BUSINESS_DOWN_QUEUE_NAME = sysConfg.getValue("DS_BUSINESS_DOWN_QUEUE_NAME").trim();
			}

			if(DS_AUTH_UP_QUEUE_NAME == null){
				DS_AUTH_UP_QUEUE_NAME = sysConfg.getValue("DS_AUTH_UP_QUEUE_NAME").trim();
			}

			if(DS_AUTH_DOWN_QUEUE_NAME == null){
				DS_AUTH_DOWN_QUEUE_NAME = sysConfg.getValue("DS_AUTH_DOWN_QUEUE_NAME").trim();
			}

			if(DS_COMM_UP_QUEUE_NAME == null){
				DS_COMM_UP_QUEUE_NAME = sysConfg.getValue("DS_COMM_UP_QUEUE_NAME").trim();
			}

			if(DS_COMM_DOWN_QUEUE_NAME == null){
				DS_COMM_DOWN_QUEUE_NAME = sysConfg.getValue("DS_COMM_DOWN_QUEUE_NAME").trim();
			}

			if(DS_DISPATCH_UP_QUEUE_NAME == null){
				DS_DISPATCH_UP_QUEUE_NAME = sysConfg.getValue("DS_DISPATCH_UP_QUEUE_NAME").trim();
			}

			if(DS_UDP_UP_QUEUE_NAME == null){
				DS_UDP_UP_QUEUE_NAME = sysConfg.getValue("DS_UDP_UP_QUEUE_NAME").trim();
			}

			if(DS_UDP_DOWN_QUEUE_NAME == null){
				DS_UDP_DOWN_QUEUE_NAME = sysConfg.getValue("DS_UDP_DOWN_QUEUE_NAME").trim();
			}

			if(DS_DISPATCH_DOWN_QUEUE_NAME == null){
				DS_DISPATCH_DOWN_QUEUE_NAME = sysConfg.getValue("DS_DISPATCH_DOWN_QUEUE_NAME").trim();
			}

			if(DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME == null){
				DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME = sysConfg.getValue("DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME").trim();
			}

			if(REDIS_IP == null){
				REDIS_IP = sysConfg.getValue("REDIS_IP").trim();
			}

			if(0 == REDIS_PORT){
				REDIS_PORT = Integer.parseInt(sysConfg.getValue("REDIS_PORT").trim());
			}
			
			if(REDIS_PASSWORD == null){
				REDIS_PASSWORD = sysConfg.getValue("REDIS_PASSWORD").trim();
			}

			if(DISPATCH_MSGID == null){
				DISPATCH_MSGID = sysConfg.getValue("DISPATCH_MSGID").trim();
			}

			if(0 == RECEIVE_MSG_INTERVAL){
				RECEIVE_MSG_INTERVAL = Integer.parseInt(sysConfg.getValue("RECEIVE_MSG_INTERVAL").trim());
			}

			if(0 == MQ_MAXCONNECTIONS){
				MQ_MAXCONNECTIONS = Integer.parseInt(sysConfg.getValue("MQ_MAXCONNECTIONS").trim());
			}


			if(0 == MQ_MAXACTIVESESSION){
				MQ_MAXACTIVESESSION = Integer.parseInt(sysConfg.getValue("MQ_MAXACTIVESESSION").trim());
			}

			if(SEND_USER_NAME == null){
				SEND_USER_NAME = sysConfg.getValue("SEND_USER_NAME").trim();
			}

			if(SMTP_HOST == null){
				SMTP_HOST = sysConfg.getValue("SMTP_HOST").trim();
			}

			if(SEND_USER_ADDR == null){
				SEND_USER_ADDR = sysConfg.getValue("SEND_USER_ADDR").trim();
			}

			if(SEND_USER_PASSWD == null){
				SEND_USER_PASSWD = sysConfg.getValue("SEND_USER_PASSWD").trim();
			}

			if(RECEIVE_EMAIL == null){
				RECEIVE_EMAIL = sysConfg.getValue("RECEIVE_EMAIL").trim();
			}

		} catch (Exception e) {
			logger.error("读取程序公用配置文件属性异常！", e);
			ExceptionUtil.getInfo(e);
		}
	}

}
