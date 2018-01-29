/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：SynRedisDataJob    
 * 类描述：  redis缓存数据同步任务定时  
 * 创建人：Administrator    
 * 创建时间：2015-11-3 上午09:55:14    
 * 修改人：Administrator    
 * 修改时间：2015-11-3 上午09:55:14    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SynRedisDataJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		synRedisData();
	}
	
	/**
	 * 同步redis缓存数据
	 */
	public static void synRedisData(){
		
		try {
			//稍后处理
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
