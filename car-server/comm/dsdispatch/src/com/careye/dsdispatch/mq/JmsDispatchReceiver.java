/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.mq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

import com.careye.dsdispatch.business.ReceiveMqBusiness;
import com.careye.dsdispatch.constant.ConfigProperties;
import com.careye.dsdispatch.mq.utils.ClientTransportListener;

/**
 *     
 * 项目名称：dsdispatch    
 * 类名称：JmsDispatchReceiver    
 * 类描述：MQ中读取调度下行队列消息 (电召消息)
 * 创建人：zr    
 * 创建时间：2015-5-20 上午09:25:23    
 * 修改人：zr    
 * 修改时间：2015-5-20 上午09:25:23    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class JmsDispatchReceiver implements MessageListener {

	private final static Logger logger = Logger.getLogger(JmsDispatchReceiver.class);

	// ConnectionFactory ：连接工厂，JMS 用它创建连接
	private ConnectionFactory connectionFactory = null;
	// Connection ：JMS 客户端到JMS Provider 的连接  
	private Connection connection = null;
	// Session： 一个发送或接收消息的线程  
	private Session session = null;
	// 消费者，消息接收者  
	private MessageConsumer consumer = null;
	// Destination ：消息的目的地;消息发送给谁
	private Destination destination = null;
	
	private ExecutorService threadPool = null;

	public JmsDispatchReceiver(){
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
			
			 //设置JAVA线程池  
	        this.threadPool = Executors.newFixedThreadPool(5);  
			
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,ConfigProperties.MQ_SERVER);

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
			destination = session.createQueue(ConfigProperties.DS_DISPATCH_DOWN_QUEUE_NAME);  
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

				final MapMessage msg = (MapMessage) message;

				if(msg != null){

					/*if(ThreadPoolManager.executor!=null){
						logger.info("[client->server] [receive] [threadpool] ===="+ThreadPoolManager.executor.getPoolSize()+"==="+ThreadPoolManager.executor.getLargestPoolSize()+"==="+ThreadPoolManager.queue.size());
						ThreadPoolManager.executor.execute(new ThreadPoolTask(msg,1));
					}*/

					this.threadPool.execute(new Runnable(){  
			            @Override  
			            public void run() {  
			                try {  
			                	ReceiveMqBusiness.mqMessageReceived(msg);
			                } catch (Exception e) {  
			                    e.printStackTrace();  
			                }  
			            }  
			        }); 
					
				}else {
					logger.info("接收调度服务器上行队列消息为空!");
				}
			} else {
				logger.info("接收调度服务器上行队列非约定格式消息,系统不处理: " + message);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("接收调度服务器上行消息异常,系统重启消息接收"+e);
			restart();
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

	/**
	 * 重启连接
	 */
	public void restart(){

		logger.info("===接入调度服务器MQ消息接收连接重启===");
		try {
			close();
			Thread.sleep(1000);
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
