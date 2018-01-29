/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.business;

import java.util.Map;

import javax.jms.MapMessage;

import org.apache.log4j.Logger;

import com.careye.dsparse.bbdomain.Auth;
import com.careye.dsparse.constant.BaseInfo;
import com.careye.dsparse.constant.Constant;
import com.careye.dsparse.decoder.BbByteDecoder;
import com.careye.dsparse.decoder.BbTaxiByteDecoder;
import com.careye.dsparse.encoder.BbJsonEncoder;
import com.careye.dsparse.encoder.BbTaxiJsonEncoder;
import com.careye.dsparse.processing.BbByteProtocolProcessing;
import com.careye.dsparse.processing.BbTaxiByteProtocolProcessing;
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.ParseUtil;
import com.careye.redis.domain.AuthInfo;


/**
 *     
 * 项目名称：dsparse    
 * 类名称：ReceiveCommMqBusiness    
 * 类描述：处理接入服务业务    
 * 创建人：zr    
 * 创建时间：2015-5-12 下午07:09:17    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午07:09:17    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class ReceiveCommMqBusiness {

	private final static Logger logger = Logger.getLogger(ReceiveCommMqBusiness.class);

	public static void mqMessageReceived(MapMessage msg){

		try {
			if(msg != null){

				String ip = msg.getString("ip");
				int msgid = msg.getInt("msgid");
				String terminal = msg.getString("terminal");
				int seq = msg.getInt("seq");
				String msgbody = msg.getString("msgbody");
				int totalnum = msg.getInt("totalnum");
				int serialnum = msg.getInt("serialnum");
				int devicetype = msg.getInt("devicetype");
				int servertype = msg.getInt("servertype"); //服务器类型 1 TCP/IP 服务器 2 UDP 服务器

				//设备类型11也为部标设备
				if(devicetype == 11){
					devicetype = 22;
				}

				//操作路由信息
				BusinessUtil.clientRouteInfo(terminal, ip, servertype, devicetype, msgid);

				logger.info("接收接入服务器发送的消息: ["+ip+"] [设备号:"+terminal+"] [消息ID:"+msgid+"] ["+msgbody+"]");
				BaseInfo baseInfo = new BaseInfo();
				baseInfo.setMsgid(msgid);
				baseInfo.setSubpacket(serialnum);
				baseInfo.setTotalpacket(totalnum);
				baseInfo.setEncryption(0);
				if(msgbody==null || "".equals(msgbody)){
					baseInfo.setBodysize(0);
				}else{
					if(devicetype == 34 || devicetype == 39){
						baseInfo.setBodysize(msgbody.length());
					}else{
						baseInfo.setBodysize(ParseUtil.parseHexStrToByte(msgbody).length);
					}
				}

				baseInfo.setTerminal(terminal);
				baseInfo.setSeq(seq);

				String jsonMsg = null;
				//根据协议不一致，判断协议解析
				switch (devicetype) {

				case 22:  //部标协议
					//只有鉴权消息单独处理，写入至鉴权上行消息队列中,其他业务消息写入至业务分发上行消息队列中
					if(msgid == 258){

						//解析鉴权协议
						Auth aInfo = BbByteDecoder.decoderAuthMsg(msgbody);

						aInfo.setPasswd(aInfo.getPasswd());

						//组装json协议
						jsonMsg = BbJsonEncoder.encoderAuthJson(baseInfo,aInfo);
						//写入鉴权消息上行队列中
						BusinessUtil.sendAuthServer(msgid, seq, terminal, jsonMsg, devicetype);

					}else{
						//二进制协议解析、JSON协议组装
						jsonMsg = BbByteProtocolProcessing.processing(msgid, terminal, msgbody,baseInfo);
						logger.info("部标协议转换内部 JSON协议 :[seq:"+seq+"] [terminal:"+terminal+"] [msgid:"+msgid+"] ["+jsonMsg+"] ");
					}
					break;

				case 29:  //出租车部标协议
					//只有鉴权消息单独处理，写入至鉴权上行消息队列中,其他业务消息写入至业务分发上行消息队列中
					if(msgid == 258){

						//解析鉴权协议
						Auth aInfo = BbTaxiByteDecoder.decoderAuthMsg(msgbody);

						aInfo.setPasswd("123456");

						//组装json协议
						jsonMsg = BbTaxiJsonEncoder.encoderAuthJson(baseInfo,aInfo);
						//写入鉴权消息上行队列中
						BusinessUtil.sendAuthServer(msgid, seq, terminal, jsonMsg, devicetype);

					}else{
						//二进制协议解析、JSON协议组装
						jsonMsg = BbTaxiByteProtocolProcessing.processing(msgid, terminal, msgbody,baseInfo);
						logger.info("出租车部标协议转换内部 JSON协议 :[seq:"+seq+"] [terminal:"+terminal+"] [msgid:"+msgid+"] ["+jsonMsg+"] ");
					}
					break;

				default:
					logger.info("设备类型不正确,系统不处理该协议 ["+devicetype+"]");
					break;
				}

				if(devicetype == 22 || devicetype == 29){
					//心跳消息发送一份给调度服务器(只需要在配置文件中DISPATCH_MSGID值中加入需要发给调度系统的消息ID)
					if(BusinessUtil.judgetMsgid(msgid)){

						if(msgid == 512){
							boolean flag = false;

							if(devicetype == 29){
								flag = true;
							}else{
								//商用车512消息不需要转入至调度服务器
								Map<String, AuthInfo> authinfomap = Constant.AUTHINFO_MAP;
								if(authinfomap != null){
									AuthInfo authInfo = authinfomap.get(terminal);
									if(authInfo!= null){
										if(authInfo.getDeviceType() == 25){
											logger.info("["+authInfo.getDeviceType()+"] 商用车512消息不转发给调度服务器 ");
										}else{
											flag = true;
										}
									}
								}
							}

							if(flag){
								BusinessUtil.sendDispatchServerHeartbeat(msgid, terminal, msgbody, devicetype, jsonMsg);
							}

						}else{
							//抢答信息需要转发一份给调度服务器
							BusinessUtil.sendDispatchServer(msgid, terminal, msgbody, devicetype, jsonMsg);
						}
					}
				}

				//写入业务分发上行消息队列
				if(jsonMsg != null){
					int apptype = 0;
					if(devicetype == 27){
						apptype = Integer.parseInt(msg.getString("reserve"));
					}
					BusinessUtil.sendBusinessServer(msgid, seq, terminal, jsonMsg, devicetype,apptype);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

}
