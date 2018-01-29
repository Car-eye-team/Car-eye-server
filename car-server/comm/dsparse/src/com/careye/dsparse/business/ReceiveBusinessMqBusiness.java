/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.business;


import javax.jms.MapMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.dsparse.processing.BbJsonProtocolProcessing;
import com.careye.dsparse.processing.BbTaxiJsonProtocolProcessing;
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.ParseUtil;


/**
 *     
 * 项目名称：dsparse    
 * 类名称：ReceiveBusinessMqBusiness    
 * 类描述：处理业务分发业务    
 * 创建人：zr    
 * 创建时间：2015-5-12 下午07:06:53    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午07:06:53    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class ReceiveBusinessMqBusiness {

	private final static Logger logger = Logger.getLogger(ReceiveBusinessMqBusiness.class);


	public static void mqMessageReceived(MapMessage msg){

		try {
			if(msg != null){
				//Json指令
				String jsonStr = msg.getString("jsonMsg");

				if(jsonStr != null){

					//终端设备类型
					int devicetype = msg.getInt("devicetype");

					//设备类型11也为部标设备
					if(devicetype == 11){
						devicetype = 22;
					}

					JSONObject jsonObject = JSONObject.fromObject(jsonStr);
					//终端设备号
					String terminal =  jsonObject.getString("terminal");
					//消息ID
					int msgid = jsonObject.getInt("msgid");
					//消息流水号
					int seq = jsonObject.getInt("seq");

					logger.info("接收到业务分发服务器下行队列消息:[seq："+seq+"] [terminal："+terminal+"] [devicetype："+devicetype+"] [msgid："+msgid+"] ["+jsonStr+"] ");

					//解析json协议、封装二进制协议
					String msgbody = null;
					//备用字段
					Object reserve = null;
					switch (devicetype) {

					case 22:  //808部标协议

						//配货站相关消息
						if(terminal.startsWith("9") || terminal.startsWith("6")){
							terminal = ParseUtil.stringToString(terminal, 11);
						}

						msgbody = BbJsonProtocolProcessing.processing(msgid, terminal, jsonObject);
						break;

					case 29:  //出租车部标协议
						msgbody = BbTaxiJsonProtocolProcessing.processing(msgid, terminal, jsonObject);
						break;

					default:
						break;
					}

					//判断消息是往接入服务器发送 还是 调度服务器发送 0 发送至接入服务器下行队列，1 发送至调度服务器下行队列
					int flag = BusinessUtil.judgeSendDirection(msgid, devicetype);
					//发送至接入服务器下行队列
					switch (flag) {
					//发送至接入服务器下行队列
					case 0:  
						BusinessUtil.sendCommServer(msgid,seq,terminal,msgbody,devicetype,reserve);	
						break;

						//发送至调度服务器下行队列	
					case 1:  
						BusinessUtil.sendDispatchServer(msgid, terminal, msgbody, devicetype, jsonStr);
						//取消电召需要通知司机
						if(msgid == 0x8B09){
							BusinessUtil.sendCommServer(msgid,seq,terminal,msgbody,devicetype,reserve);	
						}
						break;

					default:
						break;
					}

				}else{
					logger.info("接收到业务分发服务器下行队列消息内容为空,系统不处理");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

}
