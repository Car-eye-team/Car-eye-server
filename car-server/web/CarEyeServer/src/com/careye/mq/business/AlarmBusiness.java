/**
 * Description: 出租车系统
 * 文件名：AlarmPro.java
 * 版本信息：1.0
 * 日期：2015-3-14
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq.business;

import java.util.List;

import com.careye.car.domain.Alarm;
import com.careye.car.domain.CarInfo;
import com.careye.constant.ServiceConfig;
import com.careye.http.domain.BaiDuInfo;
import com.careye.mongodb.MongoDB;
import com.careye.mq.coder.ActivePush;
import com.careye.mq.domain.Protocol;
import com.careye.mq.utils.HandleUtil;
import com.careye.mq.utils.ToolsUtil;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：AlarmPro
 * @类描述：报警处理
 * @创建人：zr
 * @创建时间：2015-3-14 下午02:08:26
 * @修改人：zr
 * @修改时间：2015-3-14 下午02:08:26
 * @修改备注：
 * @version 1.0
 */
public class AlarmBusiness {

	/**
	 * 报警处理
	 * @param protocol
	 * @param gaoDeInfo
	 * @param carInfo
	 * @param message
	 * @param dlng
	 * @param dlat
	 */
	public static void alarm(Protocol protocol,BaiDuInfo baiDuInfo,CarInfo carInfo,String message,double dlng,double dlat){

		try {

			Alarm alarm = new Alarm();
			alarm.setCarid(carInfo.getId());
			alarm.setUserid(carInfo.getUserid());
			alarm.setBlocid(carInfo.getBlocid());
			alarm.setBlocname(carInfo.getBlocname());
			alarm.setTerminal(protocol.getTerminal());
			alarm.setCarnumber(carInfo.getCarnumber());
			alarm.setLng(dlng);
			alarm.setLat(dlat);
			alarm.setAltitude(protocol.getAltitude());
			//处理速度，保留两位小数点
			try {
				if(protocol.getSpeed()!=null){
					Double speed = Double.parseDouble(protocol.getSpeed());
					String speedstr = String.format("%.1f",speed);
					alarm.setSpeed(speedstr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String direction = "0";
			if(protocol.getDirection() == null || "".equals(protocol.getDirection())){
				direction = "0";
			}else{
				direction = protocol.getDirection();
			}
			alarm.setDirection(direction);

			//将GPS时间转换成时间格式
			try {
				alarm.setAlarmtime(DateUtil.numToDate(protocol.getTime()));
			} catch (Exception e) {
				e.printStackTrace();
				alarm.setAlarmtime(DateUtil.getSQLDate());
			}
			alarm.setCreatetime(DateUtil.getSQLDate());
			if(baiDuInfo != null){
				alarm.setProvince(baiDuInfo.getProvince());
				alarm.setCity(baiDuInfo.getCity());
				alarm.setDistrict(baiDuInfo.getDistrict());
				alarm.setBlng(baiDuInfo.getBlng());
				alarm.setBlat(baiDuInfo.getBlat());
				alarm.setAddress(baiDuInfo.getAddress());
			}

			alarm.setRawdata(message);
			alarm.setAlarmsource(1);
			alarm.setProstatus(1);

			//处理报警名称、报警key
			List<String> list = ToolsUtil.getAlarmStatusKey(protocol);

			for (String alarmkey : list) {
				try {
					alarm.setAlarmkey(alarmkey);
					//根据报警标志获取报警类型名
					String alarmname = ServiceConfig.carService.getAlarmName(alarmkey);
					alarm.setAlarmname(alarmname);

					//报警推送
					ActivePush.alarmPush(carInfo,6, alarm);

					//插入报警
					MongoDB.getInstance().addAlarm(alarm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			HandleUtil.alarmConfirm(protocol.getTerminal());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
