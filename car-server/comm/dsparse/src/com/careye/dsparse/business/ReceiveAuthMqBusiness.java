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
import com.careye.dsparse.mq.JmsCommSender;
import com.careye.dsparse.redis.RedisManager;
import com.careye.dsparse.utlis.ExceptionUtil;

/**
 *     
 * 项目名称：dsparse    
 * 类名称：ReceiveAuthMqBusiness    
 * 类描述： 处理鉴权业务   
 * 创建人：zr    
 * 创建时间：2015-5-12 下午07:06:10    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午07:06:10    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class ReceiveAuthMqBusiness {

	private final static Logger logger = Logger.getLogger(ReceiveAuthMqBusiness.class);

	public static void mqMessageReceived(MapMessage msg){

		try {

			if(msg != null){

				String terminal = msg.getString("terminal");
				String jsonMsg = msg.getString("jsonMsg");
				int msgid = msg.getInt("msgid");
				int seq = msg.getInt("seq");
				int result = msg.getInt("result");
				int devicetype = msg.getInt("devicetype");

				if(devicetype == 11){
					devicetype = 22;
				}

				logger.info("接收鉴权服务器发送鉴权结果消息:[设备类型:"+devicetype+"] [流水号:"+seq+"] [设备号:"+terminal+"] [消息ID:"+msgid+"] [鉴权结果:"+result+"] ["+jsonMsg+"]");

				//根据设备号获取当前设备的路由信息
				/*Map<String, String> map = (Map<String, String>) RedisManager.getInstance().getObject("clientroute");
				String ip = map.get(terminal);*/

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

				//写入至接入服务器下行队列
				if(ip != null){

					StringBuffer mqBuffer = new StringBuffer();
					String msginfo = null;
					if(servertype == 1){
						msginfo = "TCP接入服务器";
						mqBuffer.append(ConfigProperties.DS_COMM_DOWN_QUEUE_NAME);
					}else{
						msginfo = "UDP接入服务器";
						mqBuffer.append(ConfigProperties.DS_UDP_DOWN_QUEUE_NAME);
					}
					mqBuffer.append("_");
					mqBuffer.append(ip);
					

					Map<String, Object> map = new HashMap<String, Object>();       
					map.put("msgid",msgid);
					map.put("terminal",terminal);
					map.put("msgbody", jsonMsg);
					map.put("result", result);
					map.put("seq", seq);
					map.put("devicetype", devicetype);

					JmsCommSender.getInstance().send(mqBuffer.toString(), map);
					logger.info("鉴权消息写入"+msginfo+"下行消息队列 ["+mqBuffer.toString()+"]");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

}
