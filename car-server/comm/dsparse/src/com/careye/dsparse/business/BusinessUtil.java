/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.business;

import java.util.HashMap;
import java.util.Map;

import javax.jms.MapMessage;

import org.apache.log4j.Logger;

import com.careye.dsparse.constant.ConfigProperties;
import com.careye.dsparse.constant.Constant;
import com.careye.dsparse.mq.JmsAuthSender;
import com.careye.dsparse.mq.JmsBusinessSender;
import com.careye.dsparse.mq.JmsCommSender;
import com.careye.dsparse.mq.JmsDispatchSender;
import com.careye.dsparse.mq.JmsDispatchSenderHeartbeat;
import com.careye.dsparse.redis.RedisManager;
import com.careye.dsparse.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：BusinessUtil    
 * 类描述：业务处理工具类    
 * 创建人：zr    
 * 创建时间：2015-5-21 下午03:12:50    
 * 修改人：zr    
 * 修改时间：2015-5-21 下午03:12:50    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BusinessUtil {

	private final static Logger logger = Logger.getLogger(BusinessUtil.class);
	
	
	/**
	 * 操作设备路由信息
	 * @param terminal 设备号
	 * @param ip 登录IP
	 * @param servertype 服务器类型
	 * @param devicetype 设备类型
	 * @param msgid 消息ID
	 */
	public static void clientRouteInfo(String terminal,String ip,int servertype,int devicetype,int msgid){

		boolean flag = false;

		switch (devicetype) {

		//部标协议
		case 22:
			if(msgid == 258 || msgid == 256){
				flag = true;
			}
			break;

			//出租车部标协议
		case 29:
			if(msgid == 258 || msgid == 256){
				flag = true;
			}
			break;
			
		default:
			flag = true;
			break;
		}

		if(flag){

			/*Map<String, String> map = (Map<String, String>) RedisManager.getInstance().getObject("clientroute");
			if(map == null){
				map = new HashMap<String, String>();
			}
			map.put(terminal, ip);
			RedisManager.getInstance().setObject("clientroute", map);*/

			StringBuffer clientrouteBuffer = new StringBuffer();
			clientrouteBuffer.append(ip);
			clientrouteBuffer.append(",");
			clientrouteBuffer.append(servertype);

			//保存设备路由信息
			StringBuffer keyBuffer = new StringBuffer();
			keyBuffer.append(Constant.CLIENT_ROUTE);
			keyBuffer.append(terminal);
			RedisManager.getInstance().set(keyBuffer.toString(), clientrouteBuffer.toString());
		}

	}

	/**
	 * 发送至业务分发服务器上行队列
	 * @param msgid 消息ID
	 * @param seq 序列号
	 * @param terminal 设备号
	 * @param jsonMsg json消息
	 * @param devicetype 设备类型
	 */
	public static void sendBusinessServer(int msgid,int seq,String terminal,String jsonMsg,int devicetype,int apptype){

		try {
			logger.info("消息写入业务分发服务器上行队列 ["+ConfigProperties.DS_BUSINESS_UP_QUEUE_NAME+"]");
			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("msgid",msgid);
			map.put("seq", seq);
			map.put("terminal",terminal);
			map.put("jsonMsg", jsonMsg);
			map.put("devicetype", devicetype);
			map.put("apptype", apptype);
			JmsBusinessSender.getInstance().send(map);
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

	/**
	 * 发送至鉴权服务器上行队列
	 * @param msgid 消息ID
	 * @param seq 序列号
	 * @param terminal 设备号
	 * @param jsonMsg json消息
	 * @param devicetype 设备类型
	 */
	public static void sendAuthServer(int msgid,int seq,String terminal,String jsonMsg,int devicetype){

		try {
			logger.info("消息写入鉴权服务器上行队列 ["+ConfigProperties.DS_AUTH_UP_QUEUE_NAME+"]");
			MapMessage message = JmsAuthSender.getInstance().getSession().createMapMessage();      
			message.setInt("msgid",msgid);
			message.setInt("seq", seq);
			message.setString("terminal",terminal);
			message.setString("jsonMsg", jsonMsg);
			message.setInt("devicetype", devicetype);
			JmsAuthSender.getInstance().sendMapMessage(message);

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

	/**
	 * 发送至接入服务器下行队列
	 * @param msgid 消息ID
	 * @param seq 序列号
	 * @param terminal 设备号
	 * @param msgbody 十六进制消息体
	 * @param devicetype 设备类型
	 */
	public static void sendCommServer(int msgid,int seq,String terminal,String msgbody,int devicetype,Object reserve1){

		try {
			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("msgid",msgid);
			map.put("seq", seq);
			map.put("terminal",terminal);
			map.put("msgbody", msgbody);
			map.put("devicetype", devicetype);
			map.put("reserve1", reserve1);

			//根据设备号获取当前设备的路由信息
			StringBuffer keyBuffer = new StringBuffer();
			keyBuffer.append(Constant.CLIENT_ROUTE);
			keyBuffer.append(terminal);
			String clientroute = RedisManager.getInstance().get(keyBuffer.toString());
			String ip = null;
			int servertype = 1;
			if(clientroute != null){
				if(clientroute.indexOf(",") >= 0){
					String[] routearray = clientroute.split(",");
					//路由IP
					ip = routearray[0]; 
					//服务器类型 1 TCP/IP 服务器 2 UDP 服务器
					servertype = Integer.parseInt(routearray[1]);
				}
			}

			if(ip != null){
				StringBuffer mqBuffer = new StringBuffer();
				String msg = null;
				if(servertype == 1){
					msg = "TCP接入服务器";
					mqBuffer.append(ConfigProperties.DS_COMM_DOWN_QUEUE_NAME);
				}else{
					msg = "UDP接入服务器";
					mqBuffer.append(ConfigProperties.DS_UDP_DOWN_QUEUE_NAME);
				}
				mqBuffer.append("_");
				mqBuffer.append(ip);

				logger.info("业务消息["+msgid+"]写入"+msg+"下行消息队列 ["+mqBuffer.toString()+"]");
				JmsCommSender.getInstance().send(mqBuffer.toString(), map);
			}else{
				logger.info("未找到对应接入服务器,消息将不发送["+msgid+"] ["+terminal+"]["+msgbody+"]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
	}

	/**
	 * 发送至调度服务器下行队列(主要是电召消息)
	 * @param msgid 消息ID
	 * @param terminal 设备号
	 * @param msgbody 十六进制消息
	 * @param devicetype 设备类型
	 * @param jsonmsgbody json消息
	 */
	public static void sendDispatchServer(int msgid,String terminal,String msgbody,int devicetype,String jsonmsgbody){

		try {

			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("msgid",msgid);
			map.put("terminal",terminal);
			map.put("msgbody", msgbody);
			map.put("devicetype", devicetype);
			map.put("jsonmsgbody", jsonmsgbody);
			logger.info("消息写入至调度服务器下行队列 ["+ConfigProperties.DS_DISPATCH_DOWN_QUEUE_NAME+"]");
			JmsDispatchSender.getInstance().send(map);

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

	/**
	 * 发送至调度服务器下行队列(主要是512等心跳消息)
	 * @param msgid 消息ID
	 * @param terminal 设备号
	 * @param msgbody 十六进制消息
	 * @param devicetype 设备类型
	 * @param jsonmsgbody json消息
	 */
	public static void sendDispatchServerHeartbeat(int msgid,String terminal,String msgbody,int devicetype,String jsonmsgbody){

		try {

			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("msgid",msgid);
			map.put("terminal",terminal);
			map.put("msgbody", msgbody);
			map.put("devicetype", devicetype);
			map.put("jsonmsgbody", jsonmsgbody);
			logger.info("消息写入至调度服务器下行队列 ["+ConfigProperties.DS_DISPATCH_HEARTBEAT_DOWN_QUEUE_NAME+"]");
			JmsDispatchSenderHeartbeat.getInstance().send(map);

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

	/**
	 * 判断消息发送方向
	 * @param msgid 消息ID
	 * @param devicetype 设备类型 
	 * @return 0 发送至接入服务器下行队列，1 发送至调度服务器下行队列
	 */
	public static int judgeSendDirection(int msgid,int devicetype){

		int re = 0; 
		try {

			boolean flag = judgetMsgid(msgid);
			if(flag){
				re = 1;
			}

			/*switch (devicetype) {
			case 22:  //部标协议


				break;

			case 23:  //雅迅协议

				break;

			default:
				break;
			}*/


		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

		return re;

	}

	/**
	 * 判断消息ID是否需要发到调度服务器
	 * @param msgid 消息ID
	 * @return true 发送 false 不发送
	 */
	public static boolean judgetMsgid(int msgid){

		try {
			if(ConfigProperties.DISPATCH_MSGID == null || "".equals(ConfigProperties.DISPATCH_MSGID)){
				return false;
			}else{
				String[] dmsgid = ConfigProperties.DISPATCH_MSGID.split(",");
				int size = dmsgid.length;
				for (int i = 0; i < size; i++) {
					if(Integer.parseInt(dmsgid[i]) == msgid){
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
			return false;
		}

	}

}
