/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.server;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.careye.dsbusiness.constant.ConfigProperties;
import com.careye.dsbusiness.job.JobManager;
import com.careye.dsbusiness.mq.JmsBusinessReceiver;
import com.careye.dsbusiness.redis.RedisManager;
import com.careye.dsbusiness.threadpool.ThreadPoolManager;


/**    
 *     
 * 项目名称：dsbusiness    
 * 类名称：StartDsBusinessServer    
 * 类描述：启动业务分发服务器    
 * 创建人：zr    
 * 创建时间：2015-5-12 上午09:50:02    
 * 修改人：zr    
 * 修改时间：2015-5-12 上午09:50:02    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StartDsBusinessServer {

	private final static Logger logger = Logger.getLogger(StartDsBusinessServer.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StartDsBusinessServer dsBusinessServer = new StartDsBusinessServer();
		dsBusinessServer.startServer();
	}

	/**
	 * 启动服务
	 */
	public void startServer(){

		try {

			//启动log4j日志
			String proPath = "." + File.separator + "conf" + File.separator + "log4j.properties";
			PropertyConfigurator.configure(proPath);

			logger.info("开始启动DsBusinessServer服务......");

			logger.info("启动log4j日志服务......");

			//启动系统参数读取
			ConfigProperties configProperties = new ConfigProperties();
			configProperties.start();
			logger.info("启动系统参数读取服务......");
			
			logger.info("初始化系统线程池......");
			ThreadPoolManager.getInstance().initThreadPool();
			
			logger.info("初始化系统Redis缓存......");
			RedisManager.getInstance();
			
			logger.info("启动系统定时任务......");
			JobManager jobManager = new JobManager();
			jobManager.startJob();

			logger.info("启动MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsBusinessReceiver jmsBusinessReceiver = new JmsBusinessReceiver();
				jmsBusinessReceiver.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
