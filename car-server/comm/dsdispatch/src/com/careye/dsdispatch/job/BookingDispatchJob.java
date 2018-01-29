/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsdispatch.job;

import java.util.Iterator;
import java.util.Set;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.processing.DispatchRuleProcessing;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.dsdispatch.timer.BookingDispatchTimer;
import com.careye.dsdispatch.utlis.DateUtil;
import com.careye.redis.domain.CallInfo;
import com.careye.redis.domain.DispatchRule;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：BookingDispatchJob    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-25 下午01:49:22    
 * 修改人：zr    
 * 修改时间：2015-5-25 下午01:49:22    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BookingDispatchJob implements Job{

	private final static Logger logger = Logger.getLogger(BookingDispatchJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			logger.info("定时检测预约订单是否快到时间......");
			Set<String> set = RedisManager.getInstance().getSetAll(Constant.BOOKING_ORDERID);
			Iterator<String> it = set.iterator();  
			while (it.hasNext()) {  
				String orderid = it.next();  

				//根据订单号获取订单详细信息
				CallInfo callInfo = (CallInfo) RedisManager.getInstance().getObject(Constant.DISPATCH_ORDERID+orderid);
				if(callInfo != null){
					String ordertime = callInfo.getOrdertime();

					//获取预约订单调度规则
					DispatchRule dispatchRule = DispatchRuleProcessing.getDispatchRule(ordertime, 2);
					if(dispatchRule == null){
						logger.info("预约订单调度规则为空,系统将不进行后面的处理");
					}else{
						//订单时间减当前时间是否超过提前调派时间
						long diff = DateUtil.dateDiff(ordertime,DateUtil.getSQLDate());
						
						if(diff <= dispatchRule.getAdvancedistime()*60*1000){

							logger.info("执行预约订单前的即时调派......");
							
							//调派间隔
							int interval = dispatchRule.getDispatchinterval();
							//启动定时器进行调派
							Timer timer = new Timer();
							BookingDispatchTimer dispatchTimer = new BookingDispatchTimer(dispatchRule,callInfo,callInfo.getDevicetype());
							timer.schedule(dispatchTimer,0, interval*1000);	
							
						}
					}

				}

			}  
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
