/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.careye.dscomm.socket.ClientQueueManager;
import com.careye.dscomm.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ClientQueueCheckTimer    
 * 类描述：终端队列检测    
 * 创建人：zr    
 * 创建时间：2015-5-9 下午02:42:42    
 * 修改人：zr    
 * 修改时间：2015-5-9 下午02:42:42    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ClientQueueCheckTimer extends TimerTask {

	private final static Logger logger = Logger.getLogger(ClientQueueCheckTimer.class);
	@Override
	public void run() {
		
		try {
			logger.info("client queue->check:start");
			ClientQueueManager.clientMapCheck();
			logger.info("client queue->check:end");
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
		
	}
	
}
