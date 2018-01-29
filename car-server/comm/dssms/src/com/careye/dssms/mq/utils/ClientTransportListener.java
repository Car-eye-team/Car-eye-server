/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.mq.utils;

import java.io.IOException;

import org.apache.activemq.transport.TransportListener;
import org.apache.log4j.Logger;

import com.careye.dssms.email.SendMail;
import com.careye.dssms.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ClientTransportListener    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-11-20 下午02:20:46    
 * 修改人：Administrator    
 * 修改时间：2015-11-20 下午02:20:46    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ClientTransportListener implements TransportListener {

	private final static Logger logger = Logger.getLogger(ClientTransportListener.class);
	@Override
	public void onCommand(Object arg0) {
		ClientMqManager.RECONN_FLAG = true;
		if(ClientMqManager.clientMqReStartTimer != null){
			ClientMqManager.clientMqReStartTimer.cancel();
		}
	}

	@Override
	public void onException(IOException arg0) {
		
		ExceptionUtil.getInfo(arg0);
		// TODO Auto-generated method stub
		logger.info("onException,与服务器连接发生错误......");
		//服务器断开，启动定时器重新连接服务器
		if(ClientMqManager.RECONN_FLAG){
			ClientMqManager.RECONN_FLAG = false;
			//ClientMqManager.restartConnectMqServer();
		}
	}

	@Override
	public void transportInterupted() {
		logger.info("transportInterupted,与服务器连接发生中断......");
		SendMail.send("MQ消息队列与服务器连接发生中断......");
	}

	@Override
	public void transportResumed() {
		logger.info("transportResumed,恢复与服务器连接....");
	} 

}
