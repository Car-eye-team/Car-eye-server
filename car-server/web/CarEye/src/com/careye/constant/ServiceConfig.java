/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceConfig.java
 * 版本信息：1.0
 * 日期：2013-12-3
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.constant;

import com.careye.car.service.AreaSetService;
import com.careye.car.service.CarDriverService;
import com.careye.car.service.CarService;
import com.careye.car.service.PoiInfoService;
import com.careye.common.service.AlarmService;
import com.careye.common.service.LogService;
import com.careye.common.service.MenuTreeService;
import com.careye.common.service.SysOperateLogService;
import com.careye.component.springhelper.BeanLocator;
import com.careye.dssservice.service.DssService;
import com.careye.message.service.EventService;
import com.careye.message.service.LineSetService;
import com.careye.message.service.MenuServiceService;
import com.careye.message.service.QuestionService;
import com.careye.message.service.TextInfoService;
import com.careye.monitor.service.MultiMediaService;
import com.careye.system.service.OrgazicationDeptService;
import com.careye.system.service.UserService;
import com.careye.tel.service.TelBookService;
import com.careye.tel.service.TelCallService;

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

	public static MenuTreeService menuTreeService = null;
	public static OrgazicationDeptService orgazicationDeptService = null;
	public static CarService carService = null;
	public static AlarmService alarmService = null;
	
	public static CarDriverService carDriverService = null;
	public static TextInfoService textInfoService = null;
	public static EventService eventService = null;
	public static QuestionService questionService = null;
	public static PoiInfoService poiInfoService = null;
	public static AreaSetService areaSetService = null;
	public static TelCallService telCallService = null;
	public static TelBookService telBookService = null;
	public static UserService userService = null;
	public static SysOperateLogService logService = null;

	static{
		try {


			if(menuTreeService == null){
				menuTreeService = (MenuTreeService) BeanLocator.getInstance().getBean("menuTreeService");
			}
			
			if(orgazicationDeptService == null){
				orgazicationDeptService = (OrgazicationDeptService) BeanLocator.getInstance().getBean("orgazicationDeptService");
			}
			
			if(carService == null){
				carService = (CarService) BeanLocator.getInstance().getBean("carService");
			}
			
			if(alarmService == null){
				alarmService = (AlarmService) BeanLocator.getInstance().getBean("alarmService");
			}
			
			if(carDriverService == null){
				carDriverService = (CarDriverService) BeanLocator.getInstance().getBean("carDriverService");
			}
			
			
			if(textInfoService == null){
				textInfoService = (TextInfoService) BeanLocator.getInstance().getBean("textInfoService");
			}
			
			if(eventService == null){
				eventService = (EventService) BeanLocator.getInstance().getBean("eventService");
			}
			
			if(questionService == null){
				questionService = (QuestionService) BeanLocator.getInstance().getBean("questionService");
			}
			
			if(poiInfoService == null){
				poiInfoService = (PoiInfoService) BeanLocator.getInstance().getBean("poiInfoService");
			}
			
			if(areaSetService == null){
				areaSetService = (AreaSetService) BeanLocator.getInstance().getBean("areaSetService");
			}
			
			if(telCallService == null){
				telCallService = (TelCallService) BeanLocator.getInstance().getBean("telCallService");
			}
			
			if(telBookService == null){
				telBookService = (TelBookService) BeanLocator.getInstance().getBean("telBookService");
			}
			
			if(logService == null){
				logService = (SysOperateLogService) BeanLocator.getInstance().getBean("logService");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		ServiceConfig serviceConfig = new ServiceConfig();
	}

}
