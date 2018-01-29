/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.mq;

import java.util.Map;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;

import com.careye.dsparse.constant.ConfigProperties;
import com.careye.dsparse.utlis.ExceptionUtil;

/**
 *     
 * 项目名称：dsparse    
 * 类名称：JmsBusinessSender    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-12 下午06:55:17    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午06:55:17    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class JmsBusinessSender implements ExceptionListener{

	//设置连接的最大连接数
	private int maxConnections = ConfigProperties.MQ_MAXCONNECTIONS;
	//设置每个连接中使用的最大活动会话数
	private int maximumActiveSessionPerConnection = ConfigProperties.MQ_MAXACTIVESESSION;
	//强制使用同步返回数据的格式
	private boolean useAsyncSendForJMS = true;

	private PooledConnectionFactory connectionFactory;

	protected static JmsBusinessSender jmsBusinessSender = null;

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

	public JmsBusinessSender() {
		init();
	}

	private void init() {
		try {
			//ActiveMQ的连接工厂
			ActiveMQConnectionFactory actualConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,ConfigProperties.MQ_SERVER);
			actualConnectionFactory.setUseAsyncSend(this.useAsyncSendForJMS);
			//Active中的连接池工厂
			this.connectionFactory = new PooledConnectionFactory(actualConnectionFactory);
			this.connectionFactory.setCreateConnectionOnStartup(true);
			this.connectionFactory.setMaxConnections(this.maxConnections);
			this.connectionFactory.setMaximumActiveSessionPerConnection(this.maximumActiveSessionPerConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 执行发送消息的具体方法
	 * @param queue
	 * @param map
	 */
	public void send(final Map<String, Object> map) {
		try {
			sendMsg(map);
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
	}

	/**
	 * 执行消息发送
	 * @param queue
	 * @param map
	 * @throws Exception
	 */
	private void sendMsg(Map<String, Object> map) throws Exception {

		Connection connection = null;
		Session session = null;
		try {
			//从连接池工厂中获取一个连接
			connection = this.connectionFactory.createConnection();
			//false 参数表示 为非事务型消息，后面的参数表示消息的确认类型
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//PTP消息方式     
			Destination destination = session.createQueue(ConfigProperties.DS_BUSINESS_UP_QUEUE_NAME);
			//Creates a MessageProducer to send messages to the specified destination
			MessageProducer producer = session.createProducer(destination);
			//set delevery mode
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//map convert to javax message
			Message message = getMessage(session, map);
			producer.send(message);
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}finally {
			closeSession(session);
			closeConnection(connection);
		}
	}

	private Message getMessage(Session session, Map<String, Object> map) throws JMSException {
		MapMessage message = session.createMapMessage();
		if (map != null && !map.isEmpty()) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				message.setObject(key, map.get(key));
			}
		}
		return message;
	}

	private void closeSession(Session session) {
		try {
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
	}

	private void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
	}

	@Override
	public void onException(JMSException arg0) {
		arg0.printStackTrace();
	}

}
