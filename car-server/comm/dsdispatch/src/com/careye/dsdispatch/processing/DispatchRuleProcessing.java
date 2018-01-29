/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.processing;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.dsdispatch.utlis.DateUtil;
import com.careye.redis.domain.DispatchRule;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：DispatchRuleProcessing    
 * 类描述：调度规则处理    
 * 创建人：zr    
 * 创建时间：2015-5-22 上午10:59:04    
 * 修改人：zr    
 * 修改时间：2015-5-22 上午10:59:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DispatchRuleProcessing {

	private final static Logger logger = Logger.getLogger(DispatchRuleProcessing.class);

	/**
	 * 调度规则处理
	 * @param jsonObject json数据包
	 */
	public static void processing(JSONObject jsonObject){

		try {
			if(jsonObject != null){

				DispatchRule dispatchRule = (DispatchRule) JSONObject.toBean(jsonObject, DispatchRule.class);
				if(dispatchRule != null){

					logger.info("处理规则 [1 即时调度 2 策略预约调度策略 3 指派模式4 再调度] ["+dispatchRule.getType()+"]");

					//获取redis缓存中的新的调度规则
					DispatchRule newDispatchRule = (DispatchRule) RedisManager.getInstance().getObject(Constant.DISPATCH_RULE_NEW+dispatchRule.getType());
					if(newDispatchRule == null){
						logger.info("新的调度规则数据为空,将最新的调度规则加入redis缓存中");
						RedisManager.getInstance().setObject(Constant.DISPATCH_RULE_NEW+dispatchRule.getType(), dispatchRule);

						//判断老的规则是否为空，为空则加入新的规则
						DispatchRule oleDispatchRule = (DispatchRule) RedisManager.getInstance().getObject(Constant.DISPATCH_RULE_OLD+dispatchRule.getType());
						if(oleDispatchRule == null){
							RedisManager.getInstance().setObject(Constant.DISPATCH_RULE_OLD+dispatchRule.getType(), dispatchRule);
						}

					}else {
						
						logger.info("更新redis缓存中新的调度规则 key["+Constant.DISPATCH_RULE_NEW+dispatchRule.getType()+"]");
						RedisManager.getInstance().setObject(Constant.DISPATCH_RULE_NEW+dispatchRule.getType(), dispatchRule);
						
						if(dispatchRule.getOpertype() == 1){
							logger.info("将redis缓存中新的调度规则覆盖现有缓存中老的调度规则 key["+Constant.DISPATCH_RULE_OLD+dispatchRule.getType()+"]");
							RedisManager.getInstance().setObject(Constant.DISPATCH_RULE_OLD+dispatchRule.getType(), newDispatchRule);
						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据订单时间获取需要执行的调度规则
	 * @param ordertime 订单时间 格式：yyyy-MM-dd HH:mm:ss
	 * @param type 订单类型  1 即时调度 2 策略预约调度策略 3 指派模式 4 再调度
	 * @return
	 */
	public static DispatchRule getDispatchRule(String ordertime,int type){

		logger.info("根据订单时间获取需要执行的调度规则 ["+ordertime+"]  [1 即时调度 2 策略预约调度策略 3 指派模式4 再调度] ["+type+"]");
		try {

			//获取最新的调度规则
			DispatchRule newDispatchRule = (DispatchRule) RedisManager.getInstance().getObject(Constant.DISPATCH_RULE_NEW+type);
			if(newDispatchRule == null){
				logger.info("缓存中未找到新的调度规则");
				return null;
			}else{

				//获取新调度规则的生效时间
				String effecttime = newDispatchRule.getEffecttime();
				if(effecttime == null){
					logger.info("调度规则生效时间为空,采用最新的调度规则进行调度");
					return newDispatchRule;
				}else{

					//判断调度规则的生效时间与订单时间对比
					long diff = DateUtil.dateDiff(effecttime,ordertime);
					//生效时间-订单时间<0 说明新的调度规则已生效,采用新的调度规则,反之采用旧的调度规则
					if(diff<=0){
						logger.info("新调度规则已生效,采用新调度规则");
						return newDispatchRule;
					}else{
						logger.info("新调度规则未生效,采用旧调度规则");
						DispatchRule oldDispatchRule = (DispatchRule) RedisManager.getInstance().getObject(Constant.DISPATCH_RULE_OLD+type);
						return oldDispatchRule;
					}				
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("根据订单时间获取需要执行的调度规则异常,"+e);
			return null;
		}

	}


}
