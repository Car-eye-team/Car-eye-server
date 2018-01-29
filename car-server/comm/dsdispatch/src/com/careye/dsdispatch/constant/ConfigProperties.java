/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.constant;

import java.io.File;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.utlis.Configuration;

/**    
 *     
 * 项目名称：dsauth    
 * 类名称：ConfigProperties    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-11 上午11:45:57    
 * 修改人：zr    
 * 修改时间：2015-5-11 上午11:45:57    
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

	/**调度上行队列名称*/
	public static String DS_DISPATCH_UP_QUEUE_NAME = null;

	/**调度下行队列名称(主要跑电召相关消息)*/
	public static String DS_DISPATCH_DOWN_QUEUE_NAME = null;

	/**调度下行队列名称(主要跑512等的心跳消息)*/
	public static String DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME = null;

	/**redis服务器IP*/
	public static String REDIS_IP = null;

	/**redis服务器端口*/
	public static int REDIS_PORT = 0;
	
	public static String REDIS_PASSWORD = null;

	/**接入服务器上行队列名称*/
	public static String DS_COMM_UP_QUEUE_NAME = null;

	/**接入服务器下行队列名称*/
	public static String DS_COMM_DOWN_QUEUE_NAME = null;

	/**UDP接入消息队列-上行队列名称*/
	public static String DS_UDP_UP_QUEUE_NAME = null;

	/**UDP接入消息队列-下行队列名称*/
	public static String DS_UDP_DOWN_QUEUE_NAME = null;

	/**业务服务器上行队列*/
	public static String DS_BUSINESS_UP_QUEUE_NAME = null;

	/**MQ消息接收时间间隔*/
	public static int RECEIVE_MSG_INTERVAL = 0;

	public static String LEFT_LNG = null;

	public static String LEFT_LAT = null;

	public static String RIGHT_LNG = null;

	public static String RIGHT_LAT = null;

	/**
	 * MQ线程池配置
	 */
	public static int MQ_MAXCONNECTIONS = 0;

	public static int MQ_MAXACTIVESESSION = 0;

	public static String MEMCACHED_IP = null;
	public static String MEMCACHED_PORT = null;

	public static int CACHE_TYPE = 0;

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

			String path = "." + File.separator + "conf" + File.separator + "dsdispatch.properties";
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

			if(DS_DISPATCH_UP_QUEUE_NAME == null){
				DS_DISPATCH_UP_QUEUE_NAME = sysConfg.getValue("DS_DISPATCH_UP_QUEUE_NAME").trim();
			}

			if(DS_DISPATCH_DOWN_QUEUE_NAME == null){
				DS_DISPATCH_DOWN_QUEUE_NAME = sysConfg.getValue("DS_DISPATCH_DOWN_QUEUE_NAME").trim();
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

			if(DS_COMM_UP_QUEUE_NAME == null){
				DS_COMM_UP_QUEUE_NAME = sysConfg.getValue("DS_COMM_UP_QUEUE_NAME").trim();
			}

			if(DS_COMM_DOWN_QUEUE_NAME == null){
				DS_COMM_DOWN_QUEUE_NAME = sysConfg.getValue("DS_COMM_DOWN_QUEUE_NAME").trim();
			}

			if(DS_UDP_UP_QUEUE_NAME == null){
				DS_UDP_UP_QUEUE_NAME = sysConfg.getValue("DS_UDP_UP_QUEUE_NAME").trim();
			}

			if(DS_UDP_DOWN_QUEUE_NAME == null){
				DS_UDP_DOWN_QUEUE_NAME = sysConfg.getValue("DS_UDP_DOWN_QUEUE_NAME").trim();
			}

			if(DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME == null){
				DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME = sysConfg.getValue("DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME").trim();
			}

			if(DS_BUSINESS_UP_QUEUE_NAME == null){
				DS_BUSINESS_UP_QUEUE_NAME = sysConfg.getValue("DS_BUSINESS_UP_QUEUE_NAME").trim();
			}

			if(0 == RECEIVE_MSG_INTERVAL){
				RECEIVE_MSG_INTERVAL = Integer.parseInt(sysConfg.getValue("RECEIVE_MSG_INTERVAL").trim());
			}

			if(LEFT_LNG == null){
				LEFT_LNG = sysConfg.getValue("LEFT_LNG").trim();
			}

			if(LEFT_LAT == null){
				LEFT_LAT = sysConfg.getValue("LEFT_LAT").trim();
			}

			if(RIGHT_LNG == null){
				RIGHT_LNG = sysConfg.getValue("RIGHT_LNG").trim();
			}

			if(RIGHT_LAT == null){
				RIGHT_LAT = sysConfg.getValue("RIGHT_LAT").trim();
			}

			if(0 == MQ_MAXCONNECTIONS){
				MQ_MAXCONNECTIONS = Integer.parseInt(sysConfg.getValue("MQ_MAXCONNECTIONS").trim());
			}


			if(0 == MQ_MAXACTIVESESSION){
				MQ_MAXACTIVESESSION = Integer.parseInt(sysConfg.getValue("MQ_MAXACTIVESESSION").trim());
			}

			if(MEMCACHED_IP == null){
				MEMCACHED_IP = sysConfg.getValue("MEMCACHED_IP").trim();
			}

			if(MEMCACHED_PORT == null){
				MEMCACHED_PORT = sysConfg.getValue("MEMCACHED_PORT").trim();
			}

			if(0 == CACHE_TYPE){
				CACHE_TYPE = Integer.parseInt(sysConfg.getValue("CACHE_TYPE").trim());
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
		}
	}

}
