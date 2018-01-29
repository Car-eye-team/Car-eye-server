/**
 * Description: 出租车系统
 * 文件名：CarOnlineStatusJob.java
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
import com.careye.common.domain.HeartRecord;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.mq.coder.ActivePush;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：CarOnlineStatusJob
 * @类描述：5分钟无数据标志位离线
 * @创建人：zr
 * @创建时间：2015-3-20 下午03:13:08
 * @修改人：zr
 * @修改时间：2015-3-20 下午03:13:08
 * @修改备注：
 * @version 1.0
 */
public class CarOnlineStatusJob {

	protected static final Logger logger = Logger.getLogger(CarOnlineStatusJob.class);

	public void startJob(){
		try {
			logger.info("车辆在线状态检测任务启动......");
			String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
			String queryDate = DateUtil.addMonth("yyyy-MM-dd HH:mm:ss", systemdate, 0, 0,0,0,-Constant.CAR_ONLINE_TIME,0);
			List<CarInfo> list = ServiceConfig.positionInfoService.getCarOnlineStatus(queryDate);

			if(list.size() >0){
				for (CarInfo carInfo : list) {
					logger.info("==============检测到超时车辆："+carInfo.getCarnumber()+",更改车辆状态为离线==============");
					ActivePush.vehicleStatePush(carInfo,2);
					
					//插入一条离线记录
					HeartRecord heartRecord = new HeartRecord();
					heartRecord.setCarid(carInfo.getId());
					heartRecord.setOfflinecount(1);
					heartRecord.setInlinecount(0);
					heartRecord.setToday(DateUtil.getToDay());
					ServiceConfig.commonService.saveHeartRecord(heartRecord);
					
					
					/*List<Integer> blist = ServiceConfig.carService.getUpDeptlist(carInfo.getBlocid());
					if(list !=null){
						for (Integer blocid : blist) {
							ServiceConfig.carService.updateOneDeptTotal(blocid);
						}
					}*/
					//ServiceConfig.carService.updateOneDeptTotal(carInfo.getBlocid());
				}
			}
		} catch (Exception e) {
			logger.error("车辆在线状态检测任务启动异常"+e);
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		CarOnlineStatusJob carOnlineStatusJob = new CarOnlineStatusJob();
		carOnlineStatusJob.startJob();
	}

}
