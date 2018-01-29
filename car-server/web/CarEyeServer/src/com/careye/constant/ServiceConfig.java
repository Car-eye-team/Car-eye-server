/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceConfig.java
 * 版本信息：1.0
 * 日期：2013-12-3
 * Copyright car-eye车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.constant;

import com.careye.car.service.CarConditionService;
import com.careye.car.service.CarService;
import com.careye.car.service.PositionInfoService;
import com.careye.car.service.TransactionService;
import com.careye.common.service.CommonService;
import com.careye.component.springhelper.BeanLocator;
import com.careye.ocs.service.OcsService;

/**
 * @项目名称：FMS
 * @类名称：ServiceConfig
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-5-22 上午11:47:51
 * @修改人：zr
 * @修改时间：2014-5-22 上午11:47:51
 * @修改备注：
 * @version 1.0
 */
public class ServiceConfig {

	public static CarService carService = null;
	public static PositionInfoService positionInfoService = null;
	public static TransactionService transactionService = null;
	public static CommonService commonService = null;
	public static CarConditionService carConditionService = null;
	public static OcsService ocsService = null;

	static{
		try {
			if(carService == null){
				carService = (CarService) BeanLocator.getInstance().getBean("carService");
			}
			
			if(positionInfoService == null){
				positionInfoService = (PositionInfoService) BeanLocator.getInstance().getBean("positionInfoService");
			}
			
			if(transactionService == null){
				transactionService = (TransactionService) BeanLocator.getInstance().getBean("transactionService");
			}
			
			if(commonService == null){
				commonService = (CommonService) BeanLocator.getInstance().getBean("commonService");
			}
			
			if(carConditionService == null){
				carConditionService = (CarConditionService) BeanLocator.getInstance().getBean("carConditionService");
			}
			
			if(ocsService == null){
				ocsService = (OcsService) BeanLocator.getInstance().getBean("ocsService");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
