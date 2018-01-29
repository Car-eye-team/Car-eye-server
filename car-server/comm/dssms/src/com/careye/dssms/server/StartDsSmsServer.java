/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.server;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.careye.dssms.constant.ConfigProperties;
import com.careye.dssms.mq.JmsSmsReceiver;
import com.careye.dssms.threadpool.ThreadPoolManager;


/**    
 *     
 * 项目名称：dssms    
 * 类名称：StartDsSmsServer    
 * 类描述：启动短信服务器        
 * 创建人：zr    
 * 创建时间：2015-6-24 下午07:37:23    
 * 修改人：zr    
 * 修改时间：2015-6-24 下午07:37:23    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StartDsSmsServer {
	
	private final static Logger logger = Logger.getLogger(StartDsSmsServer.class);
	
	public static void main(String[] args) {
		StartDsSmsServer dsSmsServer = new StartDsSmsServer();
		dsSmsServer.startServer();
	}
	
	/**
	 * 启动服务
	 */
	public void startServer(){

		try {

			//启动log4j日志
			String proPath = "." + File.separator + "conf" + File.separator + "log4j.properties";
			PropertyConfigurator.configure(proPath);

			logger.info("开始启动DsSmsServer服务......");

			logger.info("启动log4j日志服务......");

			//启动系统参数读取
			ConfigProperties configProperties = new ConfigProperties();
			configProperties.start();
			logger.info("启动系统参数读取服务......");

			logger.info("初始化系统线程池......");
			ThreadPoolManager.getInstance().initThreadPool();

			logger.info("启动MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsSmsReceiver jmsSmsReceiver = new JmsSmsReceiver();
				jmsSmsReceiver.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
