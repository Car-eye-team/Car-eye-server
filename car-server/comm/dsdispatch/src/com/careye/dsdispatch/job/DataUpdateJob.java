/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.db.CarInfoDao;
import com.careye.dsdispatch.db.DispatchRuleDao;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.redis.domain.CarInfo;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：DataUpdateJob    
 * 类描述：更新缓存中数据定时任务    
 * 创建人：zr    
 * 创建时间：2015-6-3 下午05:51:41    
 * 修改人：zr    
 * 修改时间：2015-6-3 下午05:51:41    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DataUpdateJob implements Job{

	private final static Logger logger = Logger.getLogger(DataUpdateJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			logger.info("重新加载车辆信息加入至redis缓存中......");
			List<CarInfo> list = CarInfoDao.getCarInfoList();
			for (CarInfo carInfo : list) {
				RedisManager.getInstance().setObject(Constant.CARINFO_REDIS_NAME+carInfo.getTerminal(), carInfo);
			}

			logger.info("重新加载调度规则加入至redis缓存中......");
			DispatchRuleDao.getNewDispatchRule();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
