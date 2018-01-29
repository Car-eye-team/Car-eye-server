/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.job;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.careye.dsbusiness.constant.Constant;
import com.careye.dsbusiness.redis.RedisManager;
import com.careye.redis.domain.AuthInfo;


/**    
 *     
 * 项目名称：dsbusiness    
 * 类名称：JobManager    
 * 类描述：定时任务 管理     
 * 创建人：zr    
 * 创建时间：2015-6-1 上午10:37:28    
 * 修改人：zr    
 * 修改时间：2015-6-1 上午10:37:28    
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
		
			/*Scheduler scheduler = new StdSchedulerFactory(proPath).getScheduler();
			mqMessageCheckJobScheduler(scheduler);
			scheduler.start();*/
			
			Scheduler scheduler = new StdSchedulerFactory(proPath).getScheduler();
			synAuthInfoJobScheduler(scheduler);
			scheduler.start();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务启动异常!");
		}

	}
	
	/**
	 * 启动MQ消息检查定时任务
	 * @param scheduler
	 * @throws SchedulerException
	 */
	@SuppressWarnings("unused")
	private void mqMessageCheckJobScheduler(Scheduler scheduler) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(MqMessageCheckJob.class).withIdentity("mqMessageCheckDetail", DEFAULT_GROUP).build();
		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("mqMessageCheckJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
		scheduler.scheduleJob(job, cronTrigger);

	}
	
	/**
	 * 同步鉴权信息定时任务
	 * @param scheduler
	 * @throws SchedulerException
	 */
	@SuppressWarnings("unchecked")
	private void synAuthInfoJobScheduler(Scheduler scheduler) throws SchedulerException {
		
		Map<String, AuthInfo> authinfomap = (Map<String, AuthInfo>) RedisManager.getInstance().getObject("authinfo");
		if(authinfomap != null){
			Constant.AUTHINFO_MAP = authinfomap;
		}
		
		JobDetail job = JobBuilder.newJob(SynAuthInfoJob.class).withIdentity("synAuthInfoJobDetail", DEFAULT_GROUP).build();
		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("synAuthInfoJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ?")).build();
		scheduler.scheduleJob(job, cronTrigger);
		
	}
	
}
