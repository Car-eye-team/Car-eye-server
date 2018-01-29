/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.mq.utils;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.mq.JmsDispatchReceiver;
import com.careye.dsdispatch.mq.JmsDispatchReceiverHeartbeat;
import com.careye.dsdispatch.utlis.ExceptionUtil;

public class ClientMqReStartTimer extends TimerTask {
	private final static Logger logger = Logger.getLogger(ClientMqReStartTimer.class);
	@Override
	public void run() {
		
		try {
			logger.info("消息队列服务断开,系统自动进行连接");
			
			logger.info("启动MQ下行电召相关消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsDispatchReceiver jmsDispatchReceiver = new JmsDispatchReceiver();
				jmsDispatchReceiver.start();
			}

			logger.info("启动MQ下行终端设备心跳消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsDispatchReceiverHeartbeat jmsDispatchReceiverHeartbeat = new JmsDispatchReceiverHeartbeat();
				jmsDispatchReceiverHeartbeat.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
		
	}
}
