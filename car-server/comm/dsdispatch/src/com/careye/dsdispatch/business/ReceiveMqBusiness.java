/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.business;

import javax.jms.MapMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.processing.CarInfoProcessing;
import com.careye.dsdispatch.processing.DispatchProcessing;
import com.careye.dsdispatch.processing.DispatchRuleProcessing;
import com.careye.dsdispatch.processing.TerminalDataProcessing;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：ReceiveMqBusiness    
 * 类描述：处理调度下行消息队列    
 * 创建人：zr    
 * 创建时间：2015-5-20 上午09:27:35    
 * 修改人：zr    
 * 修改时间：2015-5-20 上午09:27:35    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ReceiveMqBusiness {

	private final static Logger logger = Logger.getLogger(ReceiveMqBusiness.class);

	/**
	 * 处理MQ消息队列接收
	 * @param msg
	 */
	public static void mqMessageReceived(MapMessage msg){

		try {

			int msgid = msg.getInt("msgid");
			String msgbody = msg.getString("msgbody");
			int devicetype = msg.getInt("devicetype");
			String terminal = msg.getString("terminal");   //如果发送端不能确定发送给那个设备，设备号直接用0填充
			String jsonmsgbody = msg.getString("jsonmsgbody");

			logger.info("接收到协议调度服务器下行队列消息:[设备类型:"+devicetype+"] [设备号:"+terminal+"] [消息ID:"+msgid+"] ["+jsonmsgbody+"] ");
			if(jsonmsgbody!=null){

				JSONObject jsonObject = null;
				try {
					jsonObject = JSONObject.fromObject(jsonmsgbody);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("json数据转换成json对象异常"+e);
				}

				//根据不同的消息处理不同的业务
				switch (msgid) {

				//部标心跳协议（收到需要处理系统初始化创建的终端设备map数据）
				case 512:
					TerminalDataProcessing.processing(msgid, jsonObject);
					break;

					//抢答
				case 20740:
					logger.info("处理抢答指令......");
					DispatchProcessing.callAnswer(msgid,terminal,jsonObject,jsonmsgbody);
					break;
					
					//招标
				case 20995:
					logger.info("处理招标......");
					DispatchProcessing.dispatch(msgid, jsonObject,msgbody,devicetype);
					break;

					//车辆信息操作通知
				case 21000:	
					logger.info("处理车辆信息操作通知协议");
					CarInfoProcessing.processing(jsonObject);
					break;

					//语音叫车
				case 21001:
					DispatchProcessing.dispatch(msgid, jsonObject,msgbody,devicetype);
					logger.info("处理语音叫车......");
					break;

					//调度规则通知	
				case 21003:
					logger.info("处理调度规则通知......");
					DispatchRuleProcessing.processing(jsonObject);
					break;
					
					//取消电召	
				case 21004:
					logger.info("处理取消电召......");
					DispatchProcessing.cancelCall(jsonObject);
					break;
					
					
					//出租车部标订单抢答
				case 0x0B01:
					logger.info("处理出租车部标订单抢答指令......");
					DispatchProcessing.callAnswer(msgid,terminal,jsonObject,jsonmsgbody);
					break;
					
					//订单任务下发
				case 0x8B00:
					logger.info("处理出租车部标订单任务下发......");
					DispatchProcessing.dispatch(msgid, jsonObject,msgbody,devicetype);
					break;
					
					//订单取消	
				case 0x8B09:
					logger.info("处理出租车部标订单取消电召......");
					DispatchProcessing.cancelCall(jsonObject);
					break;

				default:
					logger.info("收到 ["+msgid+"] 消息,系统未实现处理业务,将不进行处理");
					break;
				}			

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
