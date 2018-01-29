/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.server;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.careye.dsparse.constant.ConfigProperties;
import com.careye.dsparse.job.JobManager;
import com.careye.dsparse.mq.JmsAuthReceiver;
import com.careye.dsparse.mq.JmsBusinessReceiver;
import com.careye.dsparse.mq.JmsCommReceiver;
import com.careye.dsparse.mq.JmsUdpReceiver;
import com.careye.dsparse.redis.RedisManager;
import com.careye.dsparse.threadpool.ThreadPoolManager;
import com.careye.dsparse.utlis.ExceptionUtil;

/**
 *     
 * 项目名称：dsparse    
 * 类名称：StartDsParseServer    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-12 下午05:18:02    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午05:18:02    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class StartDsParseServer {

	private final static Logger logger = Logger.getLogger(StartDsParseServer.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StartDsParseServer dsBusinessServer = new StartDsParseServer();
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

			logger.info("启动TCP接入服务器上行MQ消息读取服务......");

			for (int i = 0; i < 5; i++) {
				JmsCommReceiver jmsCommReceiver = new JmsCommReceiver();
				jmsCommReceiver.start();
			}

			logger.info("启动UDP接入服务器上行MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsUdpReceiver jmsUdpReceiver = new JmsUdpReceiver();
				jmsUdpReceiver.start();
			}

			logger.info("启动鉴权服务器下行MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsAuthReceiver jmsAuthReceiver = new JmsAuthReceiver();
				jmsAuthReceiver.start();
			}

			logger.info("启动业务分发服务器下行MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsBusinessReceiver  jmsBusinessReceiver = new JmsBusinessReceiver();
				jmsBusinessReceiver.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}


	}

}
