/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsauth.mq.utils;

import java.util.Timer;

import org.apache.log4j.Logger;

import com.careye.dsauth.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：MqManager    
 * 类描述： 消息队列控制管理  
 * 创建人：Administrator    
 * 创建时间：2015-11-20 下午02:44:45    
 * 修改人：Administrator    
 * 修改时间：2015-11-20 下午02:44:45    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ClientMqManager {
	
	private final static Logger logger = Logger.getLogger(ClientMqManager.class);
	
	/**重新连接标志*/
	public static boolean RECONN_FLAG = true; 
	
	public static Timer connTimer = null;
	public static ClientMqReStartTimer clientMqReStartTimer = null;
	
	
	/**
	 * 重连ActiveMq服务
	 */
	public static void restartConnectMqServer(){
		try {
			
			if(clientMqReStartTimer != null){
				clientMqReStartTimer.cancel();
			}
			connTimer = new Timer();
			clientMqReStartTimer = new ClientMqReStartTimer();
			connTimer.schedule(clientMqReStartTimer, 5000, 30000);
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
	}
	
}
