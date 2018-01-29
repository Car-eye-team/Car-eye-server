/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.job;

import java.io.File;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：JobManager    
 * 类描述：  定时任务  
 * 创建人：zr    
 * 创建时间：2015-5-25 下午01:47:59    
 * 修改人：zr    
 * 修改时间：2015-5-25 下午01:47:59    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class JobManager {

	private final static Logger logger = Logger.getLogger(JobManager.class);
	private final static String DEFAULT_GROUP = "default_group";

	public void startJob(){

		String proPath = "." + File.separator + "conf" + File.separator + "quartz.properties";

		try {
			//检查预约订单信息
			Scheduler bookingDispatchJobScheduler = new StdSchedulerFactory(proPath).getScheduler();
			buildBookingDispatchScheduler(bookingDispatchJobScheduler);
			bookingDispatchJobScheduler.start();
			
			/*//消息队列最后一次接收消息时间
			Scheduler mqMessageCheckJobScheduler = new StdSchedulerFactory(proPath).getScheduler();
			mqMessageCheckScheduler(mqMessageCheckJobScheduler);
			mqMessageCheckJobScheduler.start();*/
			
			//更新缓存中数据定时任务   
			Scheduler dataUpdateJobScheduler = new StdSchedulerFactory(proPath).getScheduler();
			dataUpdateScheduler(dataUpdateJobScheduler);
			dataUpdateJobScheduler.start();
			
			
			//redis缓存数据同步任务定时
			Scheduler synRedisDataJobScheduler = new StdSchedulerFactory(proPath).getScheduler();
			synRedisDataJobScheduler(synRedisDataJobScheduler);
			synRedisDataJobScheduler.start();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务启动异常!");
		}

	}

	/**
	 * 检查预约订单信息
	 * @param authInfoJobScheduler
	 * @throws SchedulerException
	 */
	private void buildBookingDispatchScheduler(Scheduler scheduler) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(BookingDispatchJob.class).withIdentity("bookingDispatchDetail", DEFAULT_GROUP).build();
		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("bookingDispatchJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
		scheduler.scheduleJob(job, cronTrigger);

	}
	
	/**
	 * 消息队列最后一次接收消息时间
	 * @param scheduler
	 * @throws SchedulerException
	 */
	private void mqMessageCheckScheduler(Scheduler scheduler) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(MqMessageCheckJob.class).withIdentity("mqMessageCheckDetail", DEFAULT_GROUP).build();
		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("mqMessageCheckJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
		scheduler.scheduleJob(job, cronTrigger);

	}
	
	/**
	 * 重新加载系统数据
	 * @param scheduler
	 * @throws SchedulerException
	 */
	private void dataUpdateScheduler(Scheduler scheduler) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(DataUpdateJob.class).withIdentity("DataUpdateDetail", DEFAULT_GROUP).build();
		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("DataUpdateJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * * * ?")).build();
		scheduler.scheduleJob(job, cronTrigger);

	}


	/**
	 * redis缓存数据同步任务定时  
	 * @param scheduler
	 * @throws SchedulerException
	 */
	private void synRedisDataJobScheduler(Scheduler scheduler) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(SynRedisDataJob.class).withIdentity("synRedisDataJobDetail", DEFAULT_GROUP).build();
		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("synRedisDataJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
		scheduler.scheduleJob(job, cronTrigger);

	}
	
	
	
}
