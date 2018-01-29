/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.processing.DispatchProcessing;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.redis.domain.CallInfo;
import com.careye.redis.domain.DispatchRule;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：DispatchTimer    
 * 类描述：即时调派定时任务    
 * 创建人：zr    
 * 创建时间：2015-5-23 上午10:07:03    
 * 修改人：zr    
 * 修改时间：2015-5-23 上午10:07:03    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ImmediateDispatchTimer  extends TimerTask {
	
	private final static Logger logger = Logger.getLogger(ImmediateDispatchTimer.class);
	
	private DispatchRule dispatchRule = null;
	
	private CallInfo callInfo = null;
	
	private String msgbody = null;
	private int devicetype = 22;
	
	//type 订单类型  1 即时调度 2 策略预约调度策略 3 指派模式 4 再调度
	private int type = 0;
	
	//timer执行次数
	private int num = 0;
	
	public ImmediateDispatchTimer(DispatchRule dispatchRule,CallInfo callInfo,String msgbody,int type,int devicetype){
		this.dispatchRule = dispatchRule;
		this.callInfo = callInfo;
		this.msgbody = msgbody;
		this.type = type;
		this.devicetype = devicetype;
	}

	@Override
	public void run() {
		
		num+=1;
		
		int disnumber = 1;
		if(type == 1){
			disnumber = dispatchRule.getDisnumber();
		}else if(type == 2){	
			//预约订单采用总调派次数-即时派送几轮次
			disnumber = dispatchRule.getCountdisnum() - dispatchRule.getInstantdisnum();
		}
			
		String orderid = callInfo.getOrderid();
		
		//先获取redis缓存中订单是否存在，如果不存在则停止调度
		CallInfo cInfo  = (CallInfo) RedisManager.getInstance().getObject(Constant.DISPATCH_ORDERID+orderid);
		if(cInfo == null){
			cancel();
			logger.info("订单号["+orderid+"] 已完成调派,停止订单调派");
			return;
		}else{
			logger.info("订单号["+orderid+"] 查找合适车辆并进行调派......");
			//查找合适车辆并进行调派
			DispatchProcessing.findPeripheralVehicle(orderid, callInfo.getStartlng(), callInfo.getStartlat(), dispatchRule.getRadius(), callInfo.getMsgid(), msgbody,dispatchRule.getCarnum(),devicetype);
			
		}
		
		logger.info("订单号["+orderid+"] 进行第["+num+"]次调派,调度策略中设置调派轮次["+disnumber+"]次");
		
		//调派次数与调派轮次一样时将停止调派
		if(num >= disnumber){
			logger.info("订单号["+orderid+"] 调派次数已达到最大值(调派轮次),系统停止订单调派");
			cancel();
			
			//除预约订单外其余类型订单
			if(type != 2){
				RedisManager.getInstance().delObject(Constant.DISPATCH_ORDERID+orderid);
			}
		}
		
	}
	
}
