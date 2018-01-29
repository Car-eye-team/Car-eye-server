/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.server;

import java.io.File;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.careye.dscomm.constant.ConfigProperties;
import com.careye.dscomm.mq.JmsCommReceiver;
import com.careye.dscomm.redis.RedisManager;
import com.careye.dscomm.socket.DsCommServer;
import com.careye.dscomm.threadpool.ThreadPoolManager;
import com.careye.dscomm.timer.ClientQueueCheckTimer;
import com.careye.dscomm.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：DsCommServer    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-9 上午10:35:24    
 * 修改人：zr    
 * 修改时间：2015-5-9 上午10:35:24    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StartDsCommServer {

	private final static Logger logger = Logger.getLogger(StartDsCommServer.class);

	private Timer timer = new Timer();

	//终端队列检测定时
	private ClientQueueCheckTimer queuetimer = new ClientQueueCheckTimer();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StartDsCommServer dsCommServer = new StartDsCommServer();
		dsCommServer.startServer();
	}

	/**
	 * 启动服务
	 */
	public void startServer(){

		try {

			//启动log4j日志
			String proPath = "." + File.separator + "conf" + File.separator + "log4j.properties";
			PropertyConfigurator.configure(proPath);

			logger.info("开始启动CommServer服务......");

			logger.info("启动log4j日志服务......");

			//启动系统参数读取
			ConfigProperties configProperties = new ConfigProperties();
			configProperties.start();
			logger.info("启动系统参数读取服务......");

			logger.info("初始化系统线程池......");
			ThreadPoolManager.getInstance().initThreadPool();
			
			logger.info("初始化系统Redis缓存......");
			RedisManager.getInstance();

			logger.info("启动通信服务......");
			DsCommServer minaServer = new DsCommServer();
			minaServer.startServer();

			/*logger.info("启动中导通信服务......");
			ZdCommServer zDCommServer = new ZdCommServer();
			zDCommServer.startServer();*/

			logger.info("启动终端队列超时检测服务......");
			int timerout = ConfigProperties.CLIENT_QUEUE_TIME;
			timer.schedule(queuetimer, 5*1000, timerout*1000);	

			logger.info("启动MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsCommReceiver jms = new JmsCommReceiver();
				jms.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

}
