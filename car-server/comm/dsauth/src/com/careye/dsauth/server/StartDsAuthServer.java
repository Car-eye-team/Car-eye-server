/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsauth.server;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.careye.dsauth.constant.ConfigProperties;
import com.careye.dsauth.job.JobManager;
import com.careye.dsauth.mq.JmsAuthReceiver;
import com.careye.dsauth.redis.RedisManager;

/**    
 *     
 * 项目名称：dsauth    
 * 类名称：StartServer    
 * 类描述：启动鉴权服务    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午12:00:31    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午12:00:31    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StartDsAuthServer {
	
	private final static Logger logger = Logger.getLogger(StartDsAuthServer.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StartDsAuthServer dsAuthServer = new StartDsAuthServer();
		dsAuthServer.startServer();
	}
	
	/**
	 * 启动服务
	 */
	public void startServer(){
		
		try {
			//启动log4j日志
			String proPath = "." + File.separator + "conf" + File.separator + "log4j.properties";
			PropertyConfigurator.configure(proPath);
			
			logger.info("开始启动DsAuthServer服务......");
			
			logger.info("启动log4j日志服务......");
			
			//启动系统参数读取
			ConfigProperties configProperties = new ConfigProperties();
			configProperties.start();
			logger.info("启动系统参数读取服务......");
			
			logger.info("初始化系统线程池......");
			//ThreadPoolManager.getInstance().initThreadPool();
			
			logger.info("初始化系统Redis缓存......");
			RedisManager.getInstance();
			
			logger.info("启动系统定时任务......");
			JobManager jobManager = new JobManager();
			jobManager.startJob();
			
			logger.info("启动MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsAuthReceiver jms = new JmsAuthReceiver();
				jms.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
