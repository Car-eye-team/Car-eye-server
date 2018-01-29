/**
 * Description: 出租车系统
 * 文件名：MqReadThread.java
 * 版本信息：1.0
 * 日期：2015-5-26
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq;

import org.apache.log4j.Logger;

/**
 * @项目名称：TAXISERVER
 * @类名称：MqReadThread
 * @类描述：MQ读线程 
 * @创建人：zr
 * @创建时间：2015-5-26 上午10:46:14
 * @修改人：zr
 * @修改时间：2015-5-26 上午10:46:14
 * @修改备注：
 * @version 1.0
 */
public class MqReadThread extends Thread{

	private static Logger logger = Logger.getLogger(MqReadThread.class);

	public void run() {
		try {
			//启动MQ消息接收
			MqReceiver mqReceiver = new MqReceiver();
			mqReceiver.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			//启动MQ电召消息消息接收（只有在出租车业务系统中才存在）
			MqCallReceiver mqCallReceiver = new MqCallReceiver();
			mqCallReceiver.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
