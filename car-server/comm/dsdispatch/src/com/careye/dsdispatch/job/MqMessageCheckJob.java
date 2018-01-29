/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.careye.dsdispatch.constant.ConfigProperties;
import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.mq.JmsDispatchReceiverHeartbeat;
import com.careye.dsdispatch.utlis.DateUtil;


/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：MqMessageCheckJob    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-30 下午01:37:13    
 * 修改人：zr    
 * 修改时间：2015-5-30 下午01:37:13    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MqMessageCheckJob implements Job{

	private final static Logger logger = Logger.getLogger(MqMessageCheckJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		//接入服务器上行队列心跳消息接收判断
		if(Constant.LAST_RECEIVE_COMM_HEARTBEAT_TIME != null){

			try {
				int sec = DateUtil.secBetween(DateUtil.getSQLDate(), Constant.LAST_RECEIVE_COMM_HEARTBEAT_TIME);
				int interval = ConfigProperties.RECEIVE_MSG_INTERVAL*60;
				//超过5分钟没有消息重新启动消息接收
				if(sec >= interval){
					logger.info("最后一次接收接入服务器上行队列心跳消息时间["+Constant.LAST_RECEIVE_COMM_HEARTBEAT_TIME+"] ["+sec+"]秒,超过["+interval+"] 秒,重启MQ连接");
					JmsDispatchReceiverHeartbeat.getInstance().restart();
					Constant.LAST_RECEIVE_COMM_HEARTBEAT_TIME = DateUtil.getSQLDate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
