/**
 * Description: 出租车系统
 * 文件名：ActivePush.java
 * 版本信息：1.0
 * 日期：2015-3-18
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq.coder;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.careye.car.domain.Alarm;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PositionInfo;
import com.careye.constant.ConfigProperties;
import com.careye.constant.ServiceConfig;
import com.careye.mq.MqUtil;
import com.careye.mq.domain.Protocol;
import com.careye.mq.utils.ToolsUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：ActivePush
 * @类描述：主动推送
 * @创建人：zr
 * @创建时间：2015-3-18 下午03:50:24
 * @修改人：zr
 * @修改时间：2015-3-18 下午03:50:24
 * @修改备注：
 * @version 1.0
 */
public class ActivePush {

	private static Logger logger = Logger.getLogger(ActivePush.class);

	/**
	 * 状态信息推送
	 * @param carInfo
	 * @param carstatus
	 * @return
	 */
	public static CarInfo vehicleStatePush(CarInfo carInfo,int carstatus){

		//更新车辆状态为在线状态
		try {
			if(carInfo != null){
				//根据车辆ID获取车辆状态,如果车辆状态与当前状态一致则不推送 
				int car_status = ServiceConfig.carService.getCarStatus(carInfo.getId()); 
				carInfo.setCarstatus(carstatus); //1长时间离线2离线3 熄火4停车 5 行驶 6 报警 7在线 8 未定位
				try {
					//更新车辆状态与空重状态
					Map<String, Object> paramsMap = new HashMap<String, Object>();
					paramsMap.put("carid",carInfo.getId());
					paramsMap.put("carstatus",carstatus);
					paramsMap.put("acc",0);
					paramsMap.put("kzstate", carInfo.getKzstate());
					ServiceConfig.carService.updateCarState(paramsMap);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(car_status != carstatus){

					try {
						//编码车辆状态推送
						String out = DSEncoder.EncoderVehicleStatePush(carInfo);
						//插入至FLEX推送队列
						MqUtil.writeFlex(29, out);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						//车辆从离线、长离状态转换成行驶、停车、未定位等状态时，更新机构表中的离线、长离数
						if(car_status == 1 || car_status == 2){

							/*List<Integer> list = ServiceConfig.carService.getUpDeptlist(carInfo.getBlocid());
							if(list !=null){
								for (Integer blocid : list) {
									ServiceConfig.carService.updateOneDeptTotal(blocid);
								}
							}*/

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				//更改缓存中的状态
				try {
					//1长时间离线2离线3 熄火4停车 5 行驶 6 报警 7在线 8 未定位
					ToolsUtil.updateCarStatus(carInfo.getTerminal(), car_status,carInfo.getKzstate());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return carInfo;

	}

	/**
	 * 推送实时位置信息
	 * @param positionInfo
	 * @param carInfo
	 */
	public static void realTimePosition(PositionInfo positionInfo,CarInfo carInfo){

		try {
			if(positionInfo.getBlat() == null && positionInfo.getBlng() == null){
				positionInfo.setBlat(positionInfo.getLat());
				positionInfo.setBlng(positionInfo.getLng());
			}

			String out = DSEncoder.EncoderRealTimePosition(positionInfo,carInfo);
			MqUtil.writeFlex(29, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 


	/**
	 * 报警信息推送
	 * @param carInfol
	 * @param carstatus
	 * @param alarm
	 */
	public static void alarmPush(CarInfo carInfo,Integer carstatus,Alarm alarm){
		try {
			if(carInfo != null){

				//根据报警类型获取报警声音路径
				String musicaddr = ServiceConfig.carService.getMusicaddrByAlarmtype(alarm.getAlarmkey());
				if(musicaddr == null || "".equals(musicaddr)){
					alarm.setMusicaddr("upload/alarmmusic/default.wav");
				}else{
					alarm.setMusicaddr(musicaddr);
				}

				String out = DSEncoder.EncoderAlarm(carInfo, alarm);
				MqUtil.writeFlex(29, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 位置应答信息推送
	 * @param terminalPositionInfo
	 * @param carInfo
	 */
	public static void positionAnswerPush(PositionInfo positionInfo,CarInfo carInfo,Protocol protocol){

		try {

			String out = DSEncoder.EncoderPositionAnswerPush(positionInfo,carInfo,protocol);
			MqUtil.writeFlex(29, out);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 视频回放列表推送
	 * @param protocol
	 * @param carInfo
	 */
	public static void playbackListPush(CarInfo carInfo,Protocol protocol,String id,String items){

		try {

			String out = DSEncoder.EncoderPlaybackListPush(carInfo,protocol,id,items);
			MqUtil.writeFlex(29, out);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 视频回放结束推送
	 * @param protocol
	 * @param carInfo
	 */
	public static void playbackFinishPush(CarInfo carInfo,Protocol protocol,String id){

		try {

			String out = DSEncoder.EncoderPlaybackFinishPush(carInfo,protocol,id);
			MqUtil.writeFlex(29, out);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
