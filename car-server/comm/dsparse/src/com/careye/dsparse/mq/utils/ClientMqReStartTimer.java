/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.mq.utils;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.careye.dsparse.mq.JmsAuthReceiver;
import com.careye.dsparse.mq.JmsBusinessReceiver;
import com.careye.dsparse.mq.JmsCommReceiver;
import com.careye.dsparse.mq.JmsUdpReceiver;
import com.careye.dsparse.utlis.ExceptionUtil;

public class ClientMqReStartTimer extends TimerTask {
	private final static Logger logger = Logger.getLogger(ClientMqReStartTimer.class);
	@Override
	public void run() {

		try {
			logger.info("消息队列服务断开,系统自动进行连接");
			logger.info("启动TCP接入服务器上行MQ消息读取服务......");

			for (int i = 0; i < 5; i++) {
				JmsCommReceiver jmsCommReceiver = new JmsCommReceiver();
				jmsCommReceiver.start();
			}

			logger.info("启动UDP接入服务器上行MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsUdpReceiver jmsUdpReceiver = new JmsUdpReceiver();
				jmsUdpReceiver.start();
			}

			logger.info("启动鉴权服务器下行MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsAuthReceiver jmsAuthReceiver = new JmsAuthReceiver();
				jmsAuthReceiver.start();
			}

			logger.info("启动业务分发服务器下行MQ消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsBusinessReceiver  jmsBusinessReceiver = new JmsBusinessReceiver();
				jmsBusinessReceiver.start();
			}


		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}
}
