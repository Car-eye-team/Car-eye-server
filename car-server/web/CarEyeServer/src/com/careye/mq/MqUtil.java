/**
 * Description: 出租车系统
 * 文件名：MqUtil.java
 * 版本信息：1.0
 * 日期：2015-5-26
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq;

import javax.jms.MapMessage;

import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;

/**
 * @项目名称：TAXISERVER
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
	 * 启动MQ接收
	 * @return
	 */
	public static boolean start(){
		try {
			logger.info("初始化MQ............");
			for(int i=0;i<5;i++){
				MqReadThread mqReadThread = new MqReadThread();
				mqReadThread.start();
			}
			logger.info("初始化MQ完成.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化MQ异常"+e);
			return false;
		}
	}
	

	/**
	 * MQ消息写入
	 * @param devicetype 终端设备类型
	 * @param jsonmsg json消息
	 * @param mqname 写入消息队列名称
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean write(int devicetype,String jsonmsg){

		try {
			//写入消息队列名称
			String mqname = ConfigProperties.DS_WEB_DOWN_QUEUE_NAME;
			logger.info("消息写入MQ ["+mqname+"]");
			logger.info("设备类型 ["+devicetype+"]");
			MapMessage message = MqSender.getInstance().getSession().createMapMessage();
			message.setInt("devicetype", devicetype);
			message.setString("jsonMsg", jsonmsg);
			MqSender.getInstance().sendMapMessage(message, mqname);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("消息写入MQ异常"+e);
			return false;
		}
	} 
	
	/**
	 * FLEX消息队列写入
	 * @param devicetype 终端设备类型(如果不确定，直接写0)
	 * @param jsonmsg json消息
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean writeFlex(int devicetype,String jsonmsg){

		try {
			//写入消息队列名称
			String mqname = ConfigProperties.FLEX_QUEUE_NAME;
			logger.info("消息写入FLEX MQ ["+mqname+"]");
			MapMessage message = MqSender.getInstance().getSession().createMapMessage();
			message.setInt("devicetype", devicetype);
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
