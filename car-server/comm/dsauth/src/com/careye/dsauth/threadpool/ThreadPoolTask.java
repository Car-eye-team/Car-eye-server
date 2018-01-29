/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsauth.threadpool;

import java.io.Serializable;

/**
 * 项目名称：dsauth    
 * 类名称：ThreadPoolTask    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午04:45:32    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午04:45:32    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class ThreadPoolTask implements Runnable, Serializable{  
	private static final long serialVersionUID = 0;  

	private Object msgData;
	
	public ThreadPoolTask(Object msgData){  
		this.msgData = msgData;  
	}

	@Override
	public void run() {
		
		try {
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}  
}
