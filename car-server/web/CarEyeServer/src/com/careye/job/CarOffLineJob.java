/**
 * Description: 出租车系统
 * 文件名：CarOffLineJob.java
 * 版本信息：1.0
 * 日期：2015-3-20
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarInfo;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.mq.coder.ActivePush;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：CarOffLineJob
 * @类描述：离线时间超过7天，状态变成长离线
 * @创建人：zr
 * @创建时间：2015-3-20 下午02:50:44
 * @修改人：zr
 * @修改时间：2015-3-20 下午02:50:44
 * @修改备注：
 * @version 1.0
 */
public class CarOffLineJob {

	protected static final Logger logger = Logger.getLogger(CarOffLineJob.class);

	public void startJob(){
		try {
			logger.info("离线时间超过7天，状态变成长离线检测任务启动......");
			String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
			String queryDate = DateUtil.addMonth("yyyy-MM-dd HH:mm:ss", systemdate, 0, 0, - Constant.CAR_OFF_LINE_TIME,0,0,0);
			List<CarInfo> list = ServiceConfig.positionInfoService.getCarOffLine(queryDate);

			if(list.size() >0){
				for (CarInfo carInfo : list) {
					logger.info("==============检测到超时车辆："+carInfo.getCarnumber()+",更改车辆状态为长离线==============");
					ActivePush.vehicleStatePush(carInfo,1);
				}
			}

		} catch (Exception e) {
			logger.error("离线时间超过7天，状态变成长离线检测任务启动异常"+e);
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		CarOffLineJob carOffLineJob = new CarOffLineJob();
		carOffLineJob.startJob();
	}
}
