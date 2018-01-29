/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsauth.business;

import java.util.HashMap;
import java.util.Map;

import javax.jms.MapMessage;

import org.apache.log4j.Logger;

import com.careye.dsauth.constant.ConfigProperties;
import com.careye.dsauth.constant.Constant;
import com.careye.dsauth.mq.JmsAuthSender;
import com.careye.redis.domain.AuthInfo;


/**    
 *     
 * 项目名称：dsauth    
 * 类名称：ReceiveBusiness    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午05:57:22    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午05:57:22    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ReceiveBusiness {

	private final static Logger logger = Logger.getLogger(ReceiveBusiness.class);

	/**
	 * 处理MQ消息队列接收
	 * @param msg
	 */
	public static void mqMessageReceived(MapMessage msg){

		try {

			if(msg != null){

				String terminal = msg.getString("terminal");
				String jsonMsg = msg.getString("jsonMsg");
				int msgid = msg.getInt("msgid");
				int seq = msg.getInt("seq");
				int devicetype = msg.getInt("devicetype");

				boolean flag = true;

				switch (devicetype) {

				case 22:
					logger.info("[部标设备鉴权] ["+terminal+"] auth info");
					break;
					
				case 29:
					logger.info("[出租车设备鉴权] ["+terminal+"] auth info");
					break;

				default:
					break;
				}

				try {
					int result = 0;

					if(flag){
						//Map<String, AuthInfo> map = (Map<String, AuthInfo>) RedisManager.getInstance().getObject("authinfo");
						Map<String, AuthInfo> map = Constant.AUTHINFO_MAP;
						if(map == null){
							map = new HashMap<String, AuthInfo>();
						}
						AuthInfo authInfo = map.get(terminal);

						if(authInfo == null){
							logger.info("Received: ["+terminal+"] 鉴权信息不存在");
							result = 1;
						}else{
							logger.info("Received: ["+terminal+"] 鉴权成功");
							result = 0;
						}
					}

					//将鉴权结果插入下行消息队列中
					sendAuthServer(msgid, seq, terminal, jsonMsg, devicetype, result);			

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 发送至鉴权服务器上行队列
	 * @param msgid 消息ID
	 * @param seq 序列号
	 * @param terminal 设备号
	 * @param jsonMsg json消息
	 * @param devicetype 设备类型
	 * @param result 鉴权结果 0 成功 1 失败
	 */
	public static void sendAuthServer(int msgid,int seq,String terminal,String jsonMsg,int devicetype,int result){

		try {
			logger.info("消息写入鉴权服务器下行队列 ["+ConfigProperties.DS_AUTH_DOWN_QUEUE_NAME+"]");
			MapMessage message = JmsAuthSender.getInstance().getSession().createMapMessage();      
			message.setInt("msgid",msgid);
			message.setString("terminal",terminal);
			message.setString("jsonMsg", jsonMsg);
			message.setInt("seq", seq);
			message.setInt("devicetype", devicetype);
			message.setInt("result", result);

			JmsAuthSender.getInstance().sendMapMessage(message);	

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
