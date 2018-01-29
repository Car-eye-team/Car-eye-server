/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.utlis;

import com.careye.dsbusiness.constant.ConfigProperties;
import com.careye.dsbusiness.domain.MqInfo;

/**    
 *     
 * 项目名称：dsbusiness    
 * 类名称：PropertiesUtli    
 * 类描述：配置文件工具类    
 * 创建人：zr    
 * 创建时间：2015-5-12 下午02:02:38    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午02:02:38    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PropertiesUtli {

	/**
	 * 根据设备类型获取队列信息
	 * @param deviceType
	 * @return "ds_web_up_10001|all|taxiserver;ds_web_up_10002|23,24,22,26|fmsserver"
	 */
	public static MqInfo getMQInfoDeviceType(int deviceType){
		MqInfo mqInfo = new MqInfo();
		try {

			String queueInfo = ConfigProperties.DS_WEB_UP_QUEUE_NAME;
			String [] queue = queueInfo.split(";");
			for (int i = 0; i < queue.length; i++) {

				try {
					if(i == 0){
						String [] mqinfo =  queue[i].split("\\|");
						mqInfo.setMqName(mqinfo[0]);
						mqInfo.setDeviceType(mqinfo[1]);
						mqInfo.setServerName(mqinfo[2]);

						if(deviceType == 0){
							return mqInfo;
						}

					}else{
						String [] mqinfo =  queue[i].split("\\|");
						String [] dtype = mqinfo[1].split(",");
						for (int j = 0; j < dtype.length; j++) {
							if(deviceType == Integer.parseInt(dtype[j])){
								mqInfo.setMqName(mqinfo[0]);
								mqInfo.setDeviceType(mqinfo[1]);
								mqInfo.setServerName(mqinfo[2]);
								return mqInfo;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mqInfo;

	}
	
	/**
	 * 根据消息ID获取队列信息
	 * @param msgid 
	 * @return 
	 */
	public static MqInfo getMQInfoMsgId(int msgid){
		MqInfo mqInfo = null;
		try {

			String queueInfo = ConfigProperties.DS_WEB_MSGID_UP_QUEUE_NAME;
			String [] queue = queueInfo.split(";");
			for (int i = 0; i < queue.length; i++) {

				try {
						String [] mqinfo =  queue[i].split("\\|");
						String [] dtype = mqinfo[1].split(",");
						for (int j = 0; j < dtype.length; j++) {
							if(msgid == Integer.parseInt(dtype[j])){
								mqInfo = new MqInfo();
								mqInfo.setMqName(mqinfo[0]);
								mqInfo.setDeviceType(mqinfo[1]);
								mqInfo.setServerName(mqinfo[2]);
								return mqInfo;
							}
						}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mqInfo;

	}

}
