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

	/**通讯端口*/
	public static int PORT = 8888;
	
	public static String IP = null;
	
	public static String MEMCACHED_IP = null;
	public static String MEMCACHED_PORT = null;
	
	/**深海捷电话对接接口*/
	public static String SOFT_PHONE_URL = null;
	
	/**百度地图API*/
	public static String MAPBAIDU_MAP_URL = null;

	public static String MAPBAIDU_TRAFFIC_CSS_URL = null;

	public static String MAPBAIDU_TRAFFIC_JS_URL = null;

	public static String MAPBAIDU_DISTANCETOOL_URL = null;

	/**百度地图获取当前城市信息*/
	public static String MAPBAIDU_CITYINFO_URL = null;

	public static String MAPBAIDU_AK = null;
	
	
	
	/**高德地图API**/
	public static String MAPGAODE_MAP_URL = null;
	
	/**地图类型**/
	public static String MAP_TYPE = null;
	
	/**MQ配置*/
	public static String MQ_SERVER = null;
	
	/**WEB下行MQ名称*/
	public static String DS_WEB_DOWN_QUEUE_NAME = null;
	
	public static String MONGO_DB = null;
	public static String MONGO_POSITION_COLLECTION = null; //  位置
	public static String MONGO_ALARM_COLLECTION = null; //	报警
	public static String MONGO_IP = null;
	public static String MONGO_PORT = null;
	
	/**MYSQL数据库操作的开启关闭状态   1开启  2关闭**/
	public static String CMSS_STATUS = null;
	/**GTALK数据库操作的开启关闭状态   1开启  2关闭**/
	public static String GTALK_STATUS = null;

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
			
			if(PORT == 8888){
				PORT = Integer.parseInt(new String(getValue("PORT").trim()));
			}
			
			
			if(MEMCACHED_IP == null){
				MEMCACHED_IP = getValue("MEMCACHED_IP").trim();
			}
			

			if(MEMCACHED_PORT == null){
				MEMCACHED_PORT = getValue("MEMCACHED_PORT").trim();
			}
			
			if(IP == null){
				IP = getValue("IP").trim();
			}
			
			if(MQ_SERVER == null){
				MQ_SERVER = getValue("MQ_SERVER").trim();
			}
						
			if(DS_WEB_DOWN_QUEUE_NAME == null){
				DS_WEB_DOWN_QUEUE_NAME = getValue("DS_WEB_DOWN_QUEUE_NAME").trim();
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
			
			if(MAPBAIDU_MAP_URL == null){
				MAPBAIDU_MAP_URL = getValue("MAPBAIDU_MAP_URL").trim();
			}

			if(MAPBAIDU_TRAFFIC_CSS_URL == null){
				MAPBAIDU_TRAFFIC_CSS_URL = getValue("MAPBAIDU_TRAFFIC_CSS_URL").trim();
			}

			if(MAPBAIDU_TRAFFIC_JS_URL == null){
				MAPBAIDU_TRAFFIC_JS_URL = getValue("MAPBAIDU_TRAFFIC_JS_URL").trim();
			}

			if(MAPBAIDU_DISTANCETOOL_URL == null){
				MAPBAIDU_DISTANCETOOL_URL = getValue("MAPBAIDU_DISTANCETOOL_URL").trim();
			}

			if(MAPBAIDU_CITYINFO_URL == null){
				MAPBAIDU_CITYINFO_URL = getValue("MAPBAIDU_CITYINFO_URL").trim();
			}

			if(MAPBAIDU_AK == null){
				MAPBAIDU_AK = getValue("MAPBAIDU_AK").trim();
			}

			if(MAPGAODE_MAP_URL == null){
				MAPGAODE_MAP_URL =  getValue("MAPGAODE_MAP_URL");
			}
			
			if(MAP_TYPE == null){
				MAP_TYPE =  getValue("MAP_TYPE");
			}
			
			if(SOFT_PHONE_URL == null){
				SOFT_PHONE_URL = getValue("SOFT_PHONE_URL").trim();
			}
			
			if(CMSS_STATUS == null){
				CMSS_STATUS = getValue("CMSS_STATUS").trim();
			}
			
			if(GTALK_STATUS == null){
				GTALK_STATUS = getValue("GTALK_STATUS").trim();
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
