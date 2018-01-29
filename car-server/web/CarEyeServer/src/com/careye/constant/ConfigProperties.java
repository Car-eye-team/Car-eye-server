package com.careye.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigProperties {

	protected static final Logger logger = Logger.getLogger(ConfigProperties.class);

	private static InputStream inputStream;

	private static Properties p;

	/**发送邮件用户名*/
	public static String sendUserName = null;

	/**发送邮件邮箱地址*/
	public static String sendUserPassword = null;

	/**设置邮件服务器*/
	public static String smtphost = null;	

	/**发送邮件邮箱地址*/
	public static String sendUserAdr = null;

	/**接收异常邮箱*/
	public static String receiveExceptionEmail = null;

	/** 保留的线程池大小*/
	public static int corePoolSize = 0;
	/** 线程池的最大大小*/
	public static int maximumPoolSize = 0;
	/** 超时时间（秒）*/
	public static int keepAliveTime = 0;
	
	/**MQ配置*/
	public static String MQ_SERVER = null;
	
	/**WEB上行MQ名称*/
	public static String DS_WEB_UP_QUEUE_NAME = null;
	
	/**WEB下行MQ名称*/
	public static String DS_WEB_DOWN_QUEUE_NAME = null;
	
	/**FLEX消息队列*/
	public static String FLEX_QUEUE_NAME = null;
	
	/**电召业务接收MQ*/
	public static String DS_WEB_CALL_UP_QUEUE_NAME = null;
	
	/**百度地图API*/
	public static String MAPBAIDU_MAP_URL = null;
	/**百度地图获取当前城市信息*/
	public static String MAPBAIDU_CITYINFO_URL = null;
	/**百度地图AK*/
	public static String MAPBAIDU_AK = null;
	
	/**高德地图AK*/
	public static String GAODE_AK = null;
	/**地图类型(1百度2高德)*/
	public static String MAP_TYPE = null;
	
	public static String MONGO_DB = null;
	public static String MONGO_POSITION_COLLECTION = null;
	public static String MONGO_ALARM_COLLECTION = null;
	public static String MONGO_IP = null;
	public static String MONGO_PORT = null;
	
	/**项目访问路径*/
	public static String DOMAIN_PATH = null;

	public static final String proPath = "properties/mainconfig.properties";

	static{
		try {

			p = new Properties();
			inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream(proPath);
			try {
				p.load(inputStream);
				logger.info("读取程序公用配置文件成功！");
			} catch (IOException e) {
				logger.error("读取程序公用配置文件异常！", e);
			}
			if(sendUserName == null){
				sendUserName = getValue("sendUserName");
			}
			if(sendUserPassword == null){
				sendUserPassword = getValue("sendUserPassword");
			}
			if(smtphost == null){
				smtphost = getValue("smtphost");
			}

			if(sendUserAdr == null){
				sendUserAdr =  getValue("sendUserAdr");
			}

			if(receiveExceptionEmail == null){
				receiveExceptionEmail =  getValue("receiveExceptionEmail");
			}

			if(MQ_SERVER == null){
				MQ_SERVER = getValue("MQ_SERVER").trim();
			}
			
			if(DS_WEB_UP_QUEUE_NAME == null){
				DS_WEB_UP_QUEUE_NAME = getValue("DS_WEB_UP_QUEUE_NAME").trim();
			}
			
			if(DS_WEB_DOWN_QUEUE_NAME == null){
				DS_WEB_DOWN_QUEUE_NAME = getValue("DS_WEB_DOWN_QUEUE_NAME").trim();
			}
			
			if(FLEX_QUEUE_NAME == null){
				FLEX_QUEUE_NAME = getValue("FLEX_QUEUE_NAME").trim();
			}

			if(0 == corePoolSize){
				corePoolSize = Integer.parseInt(getValue("corePoolSize").trim());
			}

			if(0 == maximumPoolSize){
				maximumPoolSize = Integer.parseInt(getValue("maximumPoolSize").trim());
			}

			if(0 == keepAliveTime){
				keepAliveTime = Integer.parseInt(getValue("keepAliveTime").trim());
			}
			
			if(MAPBAIDU_MAP_URL == null){
				MAPBAIDU_MAP_URL = getValue("MAPBAIDU_MAP_URL").trim();
			}
			
			if(MAPBAIDU_CITYINFO_URL == null){
				MAPBAIDU_CITYINFO_URL = getValue("MAPBAIDU_CITYINFO_URL").trim();
			}
			
			if(MAPBAIDU_AK == null){
				MAPBAIDU_AK = getValue("MAPBAIDU_AK").trim();
			}
			
			if(MONGO_IP == null){
				MONGO_IP = getValue("MONGO_IP").trim();
			}
			
			if(MONGO_PORT == null){
				MONGO_PORT = getValue("MONGO_PORT").trim();
			}
			if(MONGO_DB == null){
				MONGO_DB = getValue("MONGO_DB").trim();
			}
			
			if(MONGO_POSITION_COLLECTION == null){
				MONGO_POSITION_COLLECTION = getValue("MONGO_POSITION_COLLECTION").trim();
			}
			
			if(MONGO_ALARM_COLLECTION == null){
				MONGO_ALARM_COLLECTION = getValue("MONGO_ALARM_COLLECTION").trim();
			}
			
			if(DOMAIN_PATH == null){
				DOMAIN_PATH = getValue("DOMAIN_PATH").trim();
			}
			
			if(DS_WEB_CALL_UP_QUEUE_NAME == null){
				DS_WEB_CALL_UP_QUEUE_NAME = getValue("DS_WEB_CALL_UP_QUEUE_NAME").trim();
			}
			
			if(GAODE_AK == null){
				GAODE_AK = getValue("GAODE_AK").trim();
			}
			
			if(MAP_TYPE == null){
				MAP_TYPE = getValue("MAP_TYPE").trim();
			}

		} catch (Exception e) {
			logger.error("读取程序公用配置文件属性异常！", e);
		}
	}


	public static String getValue(String key) {
		String value = null;
		if (p.containsKey(key)) {
			value = p.getProperty(key);
		}else {
			logger.error("配置文件中无名为"+ key +"键的值！");
		}
		return value;
	}
}
