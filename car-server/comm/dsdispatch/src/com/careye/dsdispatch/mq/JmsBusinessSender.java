/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.mq;

import java.io.IOException;

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
import org.apache.activemq.transport.TransportListener;
import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.ConfigProperties;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：JmsBusinessSender    
 * 类描述： 业务服务器上行队列   
 * 创建人：zr    
 * 创建时间：2015-5-23 下午03:50:39    
 * 修改人：zr    
 * 修改时间：2015-5-23 下午03:50:39    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class JmsBusinessSender implements TransportListener{

	private final static Logger logger = Logger.getLogger(JmsBusinessSender.class);

	private ConnectionFactory connectionFactory = null;  //ConnectionFactory ：连接工厂，JMS 用它创建连接 
	private Connection connection = null;  // Provider 的连接  
	private Session session = null; //Session： 一个发送或接收消息的线程  
	private Destination destination = null; //// MessageProducer：消息发送者  
	private MessageProducer producer = null; // TextMessage message;  
	protected static JmsBusinessSender jmsBusinessSender = null;
	private Boolean connected = false;

	public JmsBusinessSender(){
		close();
		init();
	}

	/**
	 * 获取唯一实例.
	 * @return
	 */
	public static JmsBusinessSender getInstance(){
		if (jmsBusinessSender == null) {
			jmsBusinessSender = new JmsBusinessSender();
		}
		return jmsBusinessSender;
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
	public void init(){

		try{

			connected = true;

			// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar  
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,ConfigProperties.MQ_SERVER);

			((ActiveMQConnectionFactory) connectionFactory).setTransportListener(this);

			// 构造从工厂得到连接对象  
			connection = connectionFactory.createConnection();
			//启动
			connection.start();

			// 获取操作连接  
			session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
			//获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
			destination = session.createQueue(ConfigProperties.DS_BUSINESS_UP_QUEUE_NAME);


			// 得到消息生成者【发送者】 
			producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定  
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		}catch(Exception e){
			e.printStackTrace();
			jmsBusinessSender = null;
		}
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			if (producer != null)
				producer.close();
			if (session != null)
				session.close();
			if (connection != null)
				connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
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
	public void sendTextMessage(String msg){
		try {
			TextMessage message = session.createTextMessage(msg); 
			if(connected){
				producer.send(message); 
			}
		} catch (JMSException e) {
			e.printStackTrace();
			jmsBusinessSender = null;
		}
	}

	/**
	 * 消息发送 MapMessage--一套名称-值对
	 */
	public void sendMapMessage(MapMessage message){
		try {
			if(connected){
				producer.send(message); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			jmsBusinessSender = null;
		}
	}

	@Override
	public void onCommand(Object arg0) {
	}

	@Override
	public void onException(IOException arg0) {

		arg0.printStackTrace();
		logger.info("MQ消息链路异常"+arg0.getMessage());
		connected = false;
		jmsBusinessSender = null;

	}

	@Override
	public void transportInterupted() {
		connected = false;
		jmsBusinessSender = null;
	}

	@Override
	public void transportResumed() {
		connected = true;
	}

}
