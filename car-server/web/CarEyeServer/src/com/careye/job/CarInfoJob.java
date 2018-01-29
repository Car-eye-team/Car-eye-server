/**    
 * Description: 多森软件物联网车联网平台    
 * 文件名：CarInfoJob.java   
 * 版本信息：    
 * 日期：2015-11-9  
 * Copyright car-eye车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.job;

import java.util.List;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarInfo;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.utils.DateUtil;


/**    
 *     
 * 项目名称：CVPSERVER    
 * 类名称：CarInfoJob    
 * 类描述： 定时获取全部车辆信息至本地缓存   
 * 创建人：Administrator    
 * 创建时间：2015-11-9 上午11:04:10    
 * 修改人：Administrator    
 * 修改时间：2015-11-9 上午11:04:10    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CarInfoJob {

	protected static final Logger logger = Logger.getLogger(CarInfoJob.class);

	public void startJob(){
		try {
			logger.info("定时获取全部车辆信息至本地缓存,时间开始："+DateUtil.getSQLDate());
			List<CarInfo> list = ServiceConfig.carService.getCarInfoAll();
			for (CarInfo carInfo : list) {
				StringBuffer mapBuffer = new StringBuffer();
				mapBuffer.append(carInfo.getTerminal());
				mapBuffer.append("_");
				mapBuffer.append(carInfo.getDevicetype());
				Constant.CAR_MAP.put(mapBuffer.toString(), carInfo);
			}
			logger.info("时间结束："+DateUtil.getSQLDate()+",车辆数="+Constant.CAR_MAP.keySet().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CarInfoJob carInfoJob = new CarInfoJob();
		carInfoJob.startJob();
	}
}
