/**    
 * Description: 晟鸿科技通讯平台    
 * 文件名：JmsReceiver.java   
 * 版本信息：    
 * 日期：2015-5-9  
 * Copyright car-eye车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;

/**
 * @项目名称：TAXISERVER
 * @类名称：JmsDispatchReceiver
 * @类描述：
 * @创建人：zr
 * @创建时间：2015-5-26 上午09:21:56
 * @修改人：zr
 * @修改时间：2015-5-26 上午09:21:56
 * @修改备注：
 * @version 1.0
 */
public class MqCallReceiver implements MessageListener {

	private final static Logger logger = Logger.getLogger(MqCallReceiver.class);

	// ConnectionFactory ：连接工厂，JMS 用它创建连接
	public ConnectionFactory connectionFactory = null;
	// Connection ：JMS 客户端到JMS Provider 的连接  
	public Connection connection = null;
	// Session： 一个发送或接收消息的线程  
	public Session session = null;
	// 消费者，消息接收者  
	public MessageConsumer consumer = null;
	// Destination ：消息的目的地;消息发送给谁
	public Destination destination = null;

	public MqCallReceiver(){
	}

	/**
	 * 启动
	 */
	public void start(){
		init();
	}

	/**
	 * 初始化
	 */
	public void init(){

		try{
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,"failover:("+ConfigProperties.MQ_SERVER+")");

			// 构造从工厂得到连接对象  
			connection = connectionFactory.createConnection();
			//监听ＡctiveＭＱ服务器的连接状态
			((ActiveMQConnection) connection).addTransportListener(new ClientTransportListener());
			//启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.FALSE,  
					Session.AUTO_ACKNOWLEDGE);  
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
			destination = session.createQueue(ConfigProperties.DS_WEB_CALL_UP_QUEUE_NAME);  
			consumer = session.createConsumer(destination);  

			// 开始监听
			consumer.setMessageListener(this);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {  
				//if (null != connection)  
				//connection.close();  
			} catch (Throwable ignore) {  
			} 
		}
	}

	/**
	 * 接收消息
	 */
	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof MapMessage) {

				MapMessage msg = (MapMessage) message;
				/*if(ThreadPoolManager.executor!=null){
					logger.info("[JAVA通信平台消息] [receive] [threadpool] ===="+ThreadPoolManager.executor.getPoolSize()+"==="+ThreadPoolManager.executor.getLargestPoolSize()+"==="+ThreadPoolManager.queue.size());
					ThreadPoolManager.executor.execute(new ThreadPoolTask(msg,2));
				}*/
				logger.info("[JAVA通信平台消息] 电召业务消息");
				MqClientUtil.getInstance().messageMqReceived(null,msg,2);
				
			} else {
				logger.info("接收到非约定类型mq消息: " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 关闭连接
	public void close(){
		try {
			if (consumer != null)
				consumer.close();
			if (session != null)
				session.close();
			if (connection != null)
				connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
