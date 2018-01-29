/**
 * Description: 出租车系统
 * 文件名：CarOffLineJob.java
 * 版本信息：1.0
 * 日期：2015-3-20
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.job;

import java.util.List;

import org.apache.log4j.Logger;

import com.careye.car.domain.TransactionInfo;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：OrderOutdateCheckJob
 * @类描述：检测订单是否超时
 * @创建人：wuyongde
 * @创建时间：2015-3-20 下午02:50:44
 * @修改人：wuyongde
 * @修改时间：2015-3-20 下午02:50:44
 * @修改备注：
 * @version 1.0
 */
public class OrderOutdateCheckJob {

	protected static final Logger logger = Logger.getLogger(OrderOutdateCheckJob.class);

	public void startJob(){
		try {
			logger.info("检测订单是否超时 ......");
			List<TransactionInfo> list = ServiceConfig.transactionService.getUntreatedOrderList(null);
			if(list.size() > 0){
				for (TransactionInfo transactionInfo : list) {
					try {
						String time = transactionInfo.getUsetime();
						if(transactionInfo.getTratype() == 1){
							time = transactionInfo.getAppointmenttime();
						}
						
						int diff = DateUtil.currentTimeDiffToMin(time);
						if(diff > Constant.ORDER_OUTDATE){
							if(transactionInfo.getStatus() == 1){ //1 调派中
								//把调派车辆调度状态改为未调度
								ServiceConfig.transactionService.updateCarStateByOrderid(transactionInfo.getOrderid());
							}
							transactionInfo.setStatus(3);
							logger.info("处理订单号:"+transactionInfo.getOrderid());
							//更新订单信息
							ServiceConfig.transactionService.updateTransactionInfo(transactionInfo);
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			logger.error("检测订单是否超时检测任务启动异常"+e);
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new OrderOutdateCheckJob().startJob();
	}
	
}


