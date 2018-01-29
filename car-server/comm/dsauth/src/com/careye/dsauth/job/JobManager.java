/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsauth.job;

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

import com.careye.dsauth.constant.Constant;
import com.careye.dsauth.redis.RedisManager;
import com.careye.redis.domain.AuthInfo;

/**    
 *     
 * 项目名称：dsauth    
 * 类名称：JobManager    
 * 类描述：定时任务管理    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午02:08:48    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午02:08:48    
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
			Scheduler authInfoJobScheduler = new StdSchedulerFactory(proPath).getScheduler();
			buildAuthInfoScheduler(authInfoJobScheduler);
			authInfoJobScheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			Scheduler authInfoAllJobScheduler = new StdSchedulerFactory(proPath).getScheduler();
			buildAuthInfoAllScheduler(authInfoAllJobScheduler);
			authInfoAllJobScheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 定时获取最新鉴权信息
	 * @param hardDiskJobScheduler
	 * @throws SchedulerException
	 */
	private void buildAuthInfoScheduler(Scheduler authInfoJobScheduler) throws SchedulerException {
		
		Map<String, AuthInfo> authinfomap = (Map<String, AuthInfo>) RedisManager.getInstance().getObject("authinfo");
		if(authinfomap != null){
			Constant.AUTHINFO_MAP = authinfomap;
		}

		JobDetail job = JobBuilder.newJob(AuthInfoJob.class).withIdentity("authInfoJobDetail", DEFAULT_GROUP).build();

		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("authInfoJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("50 * * * * ?")).build();

		authInfoJobScheduler.scheduleJob(job, cronTrigger);

	}
	
	/**
	 * 获取全部鉴权信息
	 * @param authInfoAllJobScheduler
	 * @throws SchedulerException
	 */
	private void buildAuthInfoAllScheduler(Scheduler authInfoAllScheduler) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(AuthInfoAllJob.class).withIdentity("authInfoAllJobDetail", DEFAULT_GROUP).build();

		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("authInfoAllJobTrigger", DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule("0 0 23 * * ?")).build();

		authInfoAllScheduler.scheduleJob(job, cronTrigger);

	}


}
