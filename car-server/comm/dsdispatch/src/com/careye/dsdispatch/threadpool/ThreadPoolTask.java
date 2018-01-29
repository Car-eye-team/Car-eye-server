/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.threadpool;

import java.io.Serializable;

import javax.jms.MapMessage;

import com.careye.dsdispatch.business.ReceiveMqBusiness;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ThreadPoolTask    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-9 下午02:59:24    
 * 修改人：zr    
 * 修改时间：2015-5-9 下午02:59:24    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ThreadPoolTask implements Runnable, Serializable{  
	private static final long serialVersionUID = 0;  

	private MapMessage msg;
	
	/**
	 * 消息类型 1 电召消息 2 终端512心跳消息
	 */
	private int msgtype;

	public ThreadPoolTask(MapMessage msg,int msgtype){  
		this.msg = msg;
		this.msgtype = msgtype;
	}

	@Override
	public void run() {
		
		try {
			ReceiveMqBusiness.mqMessageReceived(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}  
}
