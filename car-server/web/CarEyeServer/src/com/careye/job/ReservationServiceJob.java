/**
 * Description: 出租车系统
 * 文件名：ReservationServiceJob.java
 * 版本信息：1.0
 * 日期：2015-3-23
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.job;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.careye.car.domain.TransactionInfo;
import com.careye.constant.ServiceConfig;
import com.careye.mq.utils.CallHandleUtil;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：ReservationServiceJob
 * @类描述：处理预约业务
 * @创建人：zr
 * @创建时间：2015-3-23 下午07:12:01
 * @修改人：zr
 * @修改时间：2015-3-23 下午07:12:01
 * @修改备注：
 * @version 1.0
 */
public class ReservationServiceJob {

	protected static final Logger logger = Logger.getLogger(ReservationServiceJob.class);

	public void startJob(){
		try {
			logger.info("预约业务检测任务启动......");
			List<TransactionInfo> list = ServiceConfig.transactionService.getYyTransactionInfoList(DateUtil.getSQLDate());
			if(list.size() >0){
				for (TransactionInfo transactionInfo : list) {
					try {
						
						StringBuffer contents = new StringBuffer();
						contents.append("有调度业务,请抢答,业务时间是:");
						contents.append(transactionInfo.getAppointmenttime());
						contents.append(",");
						contents.append(transactionInfo.getUsername());
						contents.append(",上车地点:");
						contents.append(transactionInfo.getSaddress());
						contents.append(",目的地:");
						contents.append(transactionInfo.getEaddress());
						contents.append(",召车");
						
						Double startlat = 0.0;
						if(transactionInfo.getSlat() != null){
							startlat = transactionInfo.getSlat();
						}

						Double startlng = 0.0;
						if(transactionInfo.getSlng() != null){
							startlng = transactionInfo.getSlng();
						}

						Double endlat = 0.0;
						if(transactionInfo.getElat() != null){
							endlat = transactionInfo.getElat();
						}

						Double endlng = 0.0;
						if(transactionInfo.getElng() != null){
							endlng = transactionInfo.getElng();
						}

						CallHandleUtil.sendOrder("", transactionInfo.getOrderid(), transactionInfo.getTratype(), transactionInfo.getCreatetime(),
								transactionInfo.getSaddress(), transactionInfo.getEaddress(),startlat, startlng, endlat, endlng, contents.toString());

						//更新业务状态为调派中
						TransactionInfo tInfo = new TransactionInfo();
						tInfo.setOrderid(transactionInfo.getOrderid());
						tInfo.setStatus(1);
						ServiceConfig.transactionService.updateTransactionInfo(tInfo);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			logger.error("预约业务检测任务启动异常"+e);
			e.printStackTrace();
		}

	}

}
