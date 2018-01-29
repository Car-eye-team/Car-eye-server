/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.threadpool;

import java.io.Serializable;

import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.business.ReceiveSocketBusiness;
import com.careye.dscomm.utlis.ExceptionUtil;

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

	private String msgData;
	
	private IoSession session;
	
	private int type;

	public ThreadPoolTask(IoSession session,String msgData,int type){  
		this.msgData = msgData;  
		this.session = session;
		this.type = type;
	}

	@Override
	public void run() {
		
		try {
			ReceiveSocketBusiness.messageReceived(session, msgData,type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
		
	}  
}
