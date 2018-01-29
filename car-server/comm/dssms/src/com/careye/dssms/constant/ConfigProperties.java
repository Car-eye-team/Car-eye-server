/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.constant;

import java.io.File;

import org.apache.log4j.Logger;

import com.careye.dssms.utlis.Configuration;

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

	/**上行队列名称*/
	public static String DS_SMS_UP_QUEUE_NAME = null;

	/**下行队列名称*/
	public static String DS_SMS_DOWN_QUEUE_NAME = null;

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

	/**
	 * 阿里大于短信
	 */
	public static String ACCESS_KEY_ID = null;
	public static String ACCESS_KEY_SECRET = null;

	public void start(){
		try {

			String path = "." + File.separator + "conf" + File.separator + "dssms.properties";
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

			if(DS_SMS_UP_QUEUE_NAME == null){
				DS_SMS_UP_QUEUE_NAME = sysConfg.getValue("DS_SMS_UP_QUEUE_NAME").trim();
			}

			if(DS_SMS_DOWN_QUEUE_NAME == null){
				DS_SMS_DOWN_QUEUE_NAME = sysConfg.getValue("DS_SMS_DOWN_QUEUE_NAME").trim();
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

			
			if(ACCESS_KEY_ID == null){
				ACCESS_KEY_ID = sysConfg.getValue("ACCESS_KEY_ID").trim();
			}
			
			if(ACCESS_KEY_SECRET == null){
				ACCESS_KEY_SECRET = sysConfg.getValue("ACCESS_KEY_SECRET").trim();
			}

		} catch (Exception e) {
			logger.error("读取程序公用配置文件属性异常！", e);
		}
	}

}
