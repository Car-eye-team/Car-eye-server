/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.business;

import java.util.HashMap;
import java.util.Map;

import javax.jms.MapMessage;

import org.apache.log4j.Logger;

import com.careye.dsbusiness.constant.ConfigProperties;
import com.careye.dsbusiness.constant.Constant;
import com.careye.dsbusiness.domain.MqInfo;
import com.careye.dsbusiness.mq.JmsBusinessSender;
import com.careye.dsbusiness.mq.JmsWebCallSender;
import com.careye.dsbusiness.mq.JmsWebSender;
import com.careye.dsbusiness.utlis.BusinessUtil;
import com.careye.dsbusiness.utlis.PropertiesUtli;
import com.careye.redis.domain.AuthInfo;

/**    
 *     
 * 项目名称：dsbusiness    
 * 类名称：ReceiveBusiness    
 * 类描述：接收业务处理    
 * 创建人：zr    
 * 创建时间：2015-5-12 上午10:27:16    
 * 修改人：zr    
 * 修改时间：2015-5-12 上午10:27:16    
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
				String jsonStr = msg.getString("jsonMsg");
				int msgid = msg.getInt("msgid");
				int seq = msg.getInt("seq");
				int devicetype = msg.getInt("devicetype");
				int apptype = msg.getInt("apptype");

				logger.info("接收到业务分发上行消息队列消息:[seq:"+seq+"] [设备号:"+terminal+"] [设备类型:"+devicetype+"] [消息ID:"+msgid+"] ["+jsonStr+"]");

				if(terminal != null){

					//判断配置文件中的电召消息直接丢入上行电召业务队列(考虑到上行消息数据量比较大,将电召上行业务单独MQ进行处理)
					if(BusinessUtil.judgetMsgid(msgid)){

						Map<String, Object> map = new HashMap<String, Object>();  
						map.put("msgid",msgid);
						map.put("terminal",terminal);
						map.put("seq", seq);
						map.put("jsonMsg",jsonStr);
						map.put("devicetype", devicetype);
						JmsWebCallSender.getInstance().send(ConfigProperties.DS_WEB_CALL_UP_QUEUE_NAME, map);

						logger.info("写入电召上行队列["+msgid+"] ["+terminal+"] ["+devicetype+"]["+ConfigProperties.DS_WEB_CALL_UP_QUEUE_NAME+"] success");
					}else{

						MqInfo mqInfo = null;
						//根据消息ID 获取队列信息
						mqInfo = PropertiesUtli.getMQInfoMsgId(msgid);
						if(mqInfo == null){
							//根据设备类型获取队列信息
							mqInfo = PropertiesUtli.getMQInfoDeviceType(devicetype);
						}

						if(mqInfo != null){
							try {
								Map<String, Object> map = new HashMap<String, Object>();  
								map.put("msgid",msgid);
								map.put("terminal",terminal);
								map.put("seq", seq);
								map.put("jsonMsg",jsonStr);
								map.put("devicetype", devicetype);

								switch (devicetype) {

								//如果部标设备，根据鉴权表中平台类型进行判断插入那个业务上行队列中
								case 22:
									try {
										//Map<String, AuthInfo> authinfomap = (Map<String, AuthInfo>) RedisManager.getInstance().getObject("authinfo");
										Map<String, AuthInfo> authinfomap = Constant.AUTHINFO_MAP;
										AuthInfo authInfo = null;
										if(authinfomap != null){
											authInfo = authinfomap.get(terminal);
											if(authInfo!=null){
												if(authInfo.getPlatformType()!=null){
													mqInfo.setMqName(authInfo.getPlatformType());
												}
											}
										}

									} catch (Exception e) {
										e.printStackTrace();
									}
									break;

								default:
									break;
								}

								JmsWebSender.getInstance().send(mqInfo.getMqName(), map);
								logger.info("write ["+msgid+"] ["+terminal+"] ["+devicetype+"] ["+mqInfo.getServerName()+"] ["+mqInfo.getMqName()+"] success");
							} catch (Exception e) {
								e.printStackTrace();
								logger.info("写入  ["+msgid+"] ["+terminal+"] ["+mqInfo.getServerName()+"] ["+mqInfo.getMqName()+"] error"+e.getMessage());
							}
						}


						//测试代码
						if(msgid == 256){
							jsonStr = "{\"msgid\":33024,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\""+terminal+"\",\"seq\":18,\"seqR\":55,\"result\":0,\"passwd\":\"123456\"}";
							//将鉴权结果插入下行消息队列中
							MapMessage message = JmsBusinessSender.getInstance().getSession().createMapMessage();      
							message.setInt("seq", seq);
							message.setInt("msgid",33024);
							message.setString("terminal",terminal);
							message.setString("jsonMsg", jsonStr);
							message.setInt("devicetype", devicetype);
							JmsBusinessSender.getInstance().sendMapMessage(message);
						}
					}

				}else{
					logger.info("terminal为null,系统不进行处理");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("接收到业务分发上行消息队列消息处理异常"+e);
		}

	}
}
