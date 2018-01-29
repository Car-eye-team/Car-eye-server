/**
 * Description: 车辆管理系统
 * 文件名：MqUtil.java
 * 版本信息：1.0
 * 日期：2015-5-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq;

import javax.jms.MapMessage;

import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;



/**
 * @项目名称：car-eyeSERVER
 * @类名称：MqUtil
 * @类描述：
 * @创建人：zr
 * @创建时间：2015-5-26 上午10:35:06
 * @修改人：zr
 * @修改时间：2015-5-26 上午10:35:06
 * @修改备注：
 * @version 1.0
 */
public class MqUtil {
	
	private final static Logger logger = Logger.getLogger(MqUtil.class);
	

	/**
	 * MQ消息写入
	 * @param devicetype 终端设备类型
	 * @param jsonmsg json消息
	 * @param mqname 写入消息队列名称
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean write(Integer devicetype,String jsonmsg){

		try {
			//写入消息队列名称
			String mqname = ConfigProperties.DS_WEB_DOWN_QUEUE_NAME;
			logger.info("消息写入MQ ["+mqname+"],devicetype="+devicetype);
			MapMessage message = MqSender.getInstance().getSession().createMapMessage();
			message.setInt("devicetype", devicetype==null?29:devicetype);
			message.setString("jsonMsg", jsonmsg);
			MqSender.getInstance().sendMapMessage(message, mqname);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("消息写入MQ异常"+e);
			return false;
		}
	} 
	

}
