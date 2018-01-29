/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.careye.dsparse.constant.ConfigProperties;
import com.careye.dsparse.constant.Constant;
import com.careye.dsparse.mq.JmsCommReceiver;
import com.careye.dsparse.utlis.DateUtil;
import com.careye.dsparse.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：MqMessageCheckJob    
 * 类描述：MQ消息检查定时任务    
 * 创建人：zr    
 * 创建时间：2015-5-30 上午11:18:47    
 * 修改人：zr    
 * 修改时间：2015-5-30 上午11:18:47    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MqMessageCheckJob implements Job{

	private final static Logger logger = Logger.getLogger(MqMessageCheckJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		//接入服务器上行队列消息接收判断
		if(Constant.LAST_RECEIVE_COMM_TIME != null){

			try {
				int sec = DateUtil.secBetween(DateUtil.getSQLDate(), Constant.LAST_RECEIVE_COMM_TIME);
				int interval = ConfigProperties.RECEIVE_MSG_INTERVAL*60;
				//超过5分钟没有消息重新启动消息接收
				if(sec >= interval){
					logger.info("最后一次接收接入服务器上行队列消息时间["+Constant.LAST_RECEIVE_COMM_TIME+"] ["+sec+"]秒,超过["+interval+"] 秒,重启MQ连接");
					JmsCommReceiver.getInstance().restart();
					Constant.LAST_RECEIVE_COMM_TIME = DateUtil.getSQLDate();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtil.getInfo(e);
			}
		}

	}
}
