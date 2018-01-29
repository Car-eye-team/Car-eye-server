/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsauth.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.careye.dsauth.constant.ConfigProperties;
import com.careye.dsauth.utlis.ExceptionUtil;


/**
 * 项目名称：dsauth    
 * 类名称：ThreadPoolManager    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午04:45:41    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午04:45:41    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class ThreadPoolManager {


	private static final Logger logger = Logger.getLogger(ThreadPoolManager.class);

	protected static ThreadPoolManager tManager = new ThreadPoolManager();

	public static BlockingQueue<Runnable> queue = null;    //存放任务的队列。
	public static ThreadPoolExecutor executor = null;

	/**
	 * 创建一个新的实例 ThreadPoolManager.
	 *
	 */
	public ThreadPoolManager(){
		//initThreadPool();
	}

	public static ThreadPoolManager getInstance() {
		if (tManager == null) {
			tManager = new ThreadPoolManager();
		}
		return tManager;
	}

	/**
	 * 初始化线程池
	 */
	public void initThreadPool(){
		try {
			logger.info("线程池初始化:保留的线程池大小:"+ConfigProperties.corePoolSize+",线程池的最大大小:"+ConfigProperties.maximumPoolSize+",超时时间:"+ConfigProperties.keepAliveTime+"秒");
			//queue = new LinkedBlockingQueue<Runnable>();
			queue = new SynchronousQueue<Runnable>();

			//			AbortPolicy：直接抛出异常。
			//			CallerRunsPolicy：只用调用者所在线程来运行任务。
			//			DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
			//			DiscardPolicy：不处理，丢弃掉。
			executor = new ThreadPoolExecutor(ConfigProperties.corePoolSize, ConfigProperties.maximumPoolSize,ConfigProperties.keepAliveTime, TimeUnit.SECONDS, queue,Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
			logger.info("线程池初始化成功");
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
	}


}
