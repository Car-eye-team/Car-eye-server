/**    
 * Description: 晟鸿科技通讯平台    
 * 文件名：JmsSender.java   
 * 版本信息：    
 * 日期：2015-5-11  
 * Copyright car-eye车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;

/**
 *     
 * 项目名称：dsdispatch    
 * 类名称：JmsCommSender    
 * 类描述：接入服务器下行消息队列
 * 创建人：zr    
 * 创建时间：2015-5-20 下午03:54:41    
 * 修改人：zr    
 * 修改时间：2015-5-20 下午03:54:41    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class MqSender {

	private final static Logger logger = Logger.getLogger(MqSender.class);

	private static ConnectionFactory connectionFactory = null;  //ConnectionFactory ：连接工厂，JMS 用它创建连接 
	private static Connection connection = null;  // Provider 的连接  
	private static Session session = null; //Session： 一个发送或接收消息的线程  

	protected static MqSender mqSender = new MqSender();

	public MqSender(){
		init();
	}

	/**
	 * 获取唯一实例.
	 * @return
	 */
	public static MqSender getInstance(){
		if (mqSender == null) {
			mqSender = new MqSender();
		}
		return mqSender;
	}

	/**
	 * 获取session
	 * @return
	 */
	public Session getSession(){
		return session;
	}

	/**
	 * 初始化链接
	 */
	public static void init(){

		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar  
		if(connectionFactory == null){
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,"failover:("+ConfigProperties.MQ_SERVER+")");
		}

		try{
			if(connection == null){
				// 构造从工厂得到连接对象  
				connection = connectionFactory.createConnection();
				//启动
				connection.start();
			}

			if(session == null){
				// 获取操作连接  
				session = connection.createSession(Boolean.TRUE.booleanValue(), 
						Session.AUTO_ACKNOWLEDGE);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			logger.info("Producer:->Closing connection");

			if (session != null)
				session.close();
			if (connection != null)
				connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 消息发送
	 * · StreamMessage -- Java原始值的数据流

		· MapMessage--一套名称-值对

		· TextMessage--一个字符串对象

		· ObjectMessage--一个序列化的 Java对象

		· BytesMessage--一个未解释字节的数据流
	 */
	public static void sendTextMessage(String msg,String mqname){

		try {

			//获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
			Destination destination = session.createQueue(mqname);
			// 得到消息生成者【发送者】 
			MessageProducer producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定  
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			//发送消息
			TextMessage message = session.createTextMessage(msg); 
			producer.send(message); 
			session.commit();

			//关闭producer
			producer.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送map方式
	 * @param message
	 * @param mqname
	 */
	public static void sendMapMessage(MapMessage message,String mqname){

		try {

			//获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
			Destination destination = session.createQueue(mqname);
			// 得到消息生成者【发送者】 
			MessageProducer producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定  
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			//发送消息
			producer.send(message); 
			session.commit();

			//关闭producer
			producer.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
