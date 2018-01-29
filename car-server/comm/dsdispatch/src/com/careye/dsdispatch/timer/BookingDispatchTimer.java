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
 * 类名称：BookingDispatchTimer    
 * 类描述：预约调派定时任务     
 * 创建人：zr    
 * 创建时间：2015-5-25 上午09:04:04    
 * 修改人：zr    
 * 修改时间：2015-5-25 上午09:04:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BookingDispatchTimer extends TimerTask {

	private final static Logger logger = Logger.getLogger(BookingDispatchTimer.class);

	private DispatchRule dispatchRule = null;

	private CallInfo callInfo = null;
	
	private int devicetype = 22;

	//timer执行次数
	private int num = 0;

	public BookingDispatchTimer(DispatchRule dispatchRule,CallInfo callInfo,int devicetype){
		this.dispatchRule = dispatchRule;
		this.callInfo = callInfo;
		this.devicetype = devicetype;
	}

	@Override
	public void run() {

		num+=1;

		int disnumber = dispatchRule.getInstantdisnum();

		String orderid = callInfo.getOrderid();

		//先获取redis缓存中订单是否存在，如果不存在则停止调度
		CallInfo cInfo  = (CallInfo) RedisManager.getInstance().getObject(Constant.DISPATCH_ORDERID+orderid);
		if(cInfo == null){
			cancel();
			logger.info("订单号["+orderid+"] 已完成调派,停止订单调派");
			return;
		}else{

			//查找合适车辆并进行调派
			DispatchProcessing.findPeripheralVehicle(orderid, callInfo.getStartlng(), callInfo.getStartlat(), dispatchRule.getRadius(), callInfo.getMsgid(), callInfo.getMsgbody(),dispatchRule.getCarnum(),devicetype);

		}

		logger.info("订单号["+orderid+"] 进行第["+num+"]次调派,调度策略中设置调派轮次["+disnumber+"]次");

		//调派次数与调派轮次一样时将停止调派
		if(num >= disnumber){
			logger.info("订单号["+orderid+"] 调派次数已达到最大值(调派轮次),系统停止订单调派");
			cancel();

			//删除预约订单信息
			RedisManager.getInstance().delSet(Constant.BOOKING_ORDERID, orderid);
			RedisManager.getInstance().delObject(Constant.DISPATCH_ORDERID+orderid);

		}
	}
}
