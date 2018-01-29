/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.constant.ConfigProperties;
import com.careye.dscomm.decoder.BbDecoder;
import com.careye.dscomm.decoder.DecoderUtil;
import com.careye.dscomm.domain.Protocol;
import com.careye.dscomm.encoder.BbEncoder;
import com.careye.dscomm.mq.JmsCommSender;
import com.careye.dscomm.socket.ClientQueueManager;
import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.ParseUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ReceiveBusiness    
 * 类描述：处理接收业务    
 * 创建人：zr
 * 创建时间：2015-5-9 下午03:13:13    
 * 修改人：zr    
 * 修改时间：2015-5-9 下午03:13:13    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ReceiveSocketBusiness {

	private final static Logger logger = Logger.getLogger(ReceiveSocketBusiness.class);

	/**
	 * 处理终端上行消息
	 * @param session
	 * @param message
	 */
	public static void messageReceived(IoSession session,String message,int type){

		try {

			logger.info("[client->server] [接收终端设备上行消息] "+message);
			byte[] data = ParseUtil.parseHexStrToByte(message);

			//判断协议类型
			int devicetype = DecoderUtil.protocolJudgment(data,message,type,session);
			logger.info("[client->server] [协议类型:"+devicetype+"]");

			if(devicetype != 0){

				Protocol protocol =  null;

				//协议处理：解析消息头、消息体
				switch (devicetype) {

				case 22: //部标协议
					protocol = BbDecoder.decoder808Data(data);

					if(protocol != null){

						//根据设备号判断为出租车部标还是货运部标808
						//(出租车部标)发起此次数据传输的终端ID号，终端标识第一字节为‘10’，后5字节为编码规则参见2.2.1
						//根据安装后终端自身的手机号转换。手机号不足 12位，则在前补充数字，大陆手机号补充数字0，港澳台则根据其区号进行位数补充。
						if(protocol.getTerminal().startsWith("10")){
							logger.info("[client->server] 接收到消息为出租车部标消息");
							devicetype = 29;
						}else{
							if(protocol.getMsgid() == 0x0900){
								logger.info("接收到808透传协议:"+protocol.getMsgbody());
								byte[] bodybyte = ParseUtil.parseHexStrToByte(protocol.getMsgbody());
								if(bodybyte != null){
									byte[] ldsycbodybyte = ParseUtil.byteTobyte(bodybyte,1, bodybyte.length-1);
									String ldsycbody = ParseUtil.parseByteToHexStr(ldsycbodybyte);
								}
							}
						}
					}

					break;


				default:
					break;
				}

				if(protocol != null){

					protocol.setDevicetype(devicetype);

					//终端设备链路信息处理
					protocol = sessionBusiness(protocol,session);
					byte[] msgbytes = null;
					if(protocol.isFlag()){

						//回复通用应答
						switch (devicetype) {

						case 22: //部标协议
							//回复通用应答  鉴权消息通用应答等到鉴权成功之后再应答
							if(protocol.getMsgid() != 258){
								msgbytes = BbEncoder.platformResponse(protocol.getTerminal(), protocol.getSeq(), protocol.getMsgid(), protocol.getResult());
								session.write(IoBuffer.wrap(msgbytes));
							}
							break;

						case 29: //出租车部标协议
							//回复通用应答  鉴权消息通用应答等到鉴权成功之后再应答
							if(protocol.getMsgid() != 258){
								msgbytes = BbEncoder.platformResponse(protocol.getTerminal(), protocol.getSeq(), protocol.getMsgid(), protocol.getResult());
								session.write(IoBuffer.wrap(msgbytes));
							}
							break;

						default:
							break;
						}

						//将消息写入至接入服务器上行消息队列中
						Map<String, Object> map = new HashMap<String, Object>();  
						map.put("ip", ConfigProperties.SERVER_IP);
						map.put("msgid",protocol.getMsgid());
						map.put("terminal",protocol.getTerminal());
						map.put("seq", protocol.getSeq());
						map.put("msgbody", protocol.getMsgbody());
						map.put("totalnum", protocol.getTotalnum());
						map.put("serialnum", protocol.getSerialnum());
						map.put("devicetype", devicetype); 
						map.put("reserve", protocol.getReserve2());
						map.put("servertype", 1); //服务器类型 1 TCP/IP 服务器 2 UDP 服务器
						JmsCommSender.getInstance().send(map);  

					}
				}else{
					logger.info("[client->server] [receive] 协议格式不正确,直接丢弃");
					//session.close(true);
					logger.error(message);
				}
			}else{
				logger.info("[client->server] [receive] 协议格式不正确,断开终端连接");
				session.close(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	} 

	/**
	 * 链路信息处理,处理用户是否通过注册或者鉴权操作，如果没有通过直接发送512或者其他消息，平台将主动断开客户端链接
	 * @param terminal
	 * @param msgid
	 * @param session
	 */
	public static Protocol sessionBusiness(Protocol protocol,IoSession session){

		try {

			//协议类型 22 部标协议  23雅迅协议
			int devicetype = protocol.getDevicetype();
			int msgid = protocol.getMsgid();
			String terminal = protocol.getTerminal();

			switch (devicetype) {

			//链路信息处理：部标协议
			case 22:  
				//收到鉴权消息，将链路信息加入终端链路信息MAP中
				if(msgid == 0x0102){
					ClientQueueManager.addClient(terminal, session,devicetype,null,null);
					protocol.setFlag(true);
					//直接发送终端注册，将链路信息加入终端链路信息MAP中
				}else if(msgid == 0x0100){
					ClientQueueManager.addClient(terminal, session,devicetype,null,null);
					protocol.setFlag(true);
					//终端注销,关闭session连接
				}else if(msgid == 0x0003){
					ClientQueueManager.deleteClientTerminal(terminal);
					protocol.setFlag(true);
				}else{
					//先判断当前终端链路信息是否存在，如果存在则接收并处理，否则直接删除链路信息
					IoSession ioSession = ClientQueueManager.getClientIoSession(terminal);
					if(ioSession == null){
						//关闭链接
						session.close(true);
						protocol.setFlag(false);

					}else{
						//更新链路信息，保证客户端链接队列中保持最新的链接
						ClientQueueManager.updateClient(terminal, ioSession,devicetype,null,null);
						protocol.setFlag(true);
					}
				}
				break;


				//链路信息处理：出租车部标协议
			case 29:  
				//收到鉴权消息，将链路信息加入终端链路信息MAP中
				if(msgid == 0x0102){
					ClientQueueManager.addClient(terminal, session,devicetype,null,null);
					protocol.setFlag(true);
					//直接发送终端注册，将链路信息加入终端链路信息MAP中
				}else if(msgid == 0x0100){
					ClientQueueManager.addClient(terminal, session,devicetype,null,null);
					protocol.setFlag(true);
					//终端注销,关闭session连接
				}else if(msgid == 0x0003){
					ClientQueueManager.deleteClientTerminal(terminal);
					protocol.setFlag(true);
				}else{
					//先判断当前终端链路信息是否存在，如果存在则接收并处理，否则直接删除链路信息
					IoSession ioSession = ClientQueueManager.getClientIoSession(terminal);
					if(ioSession == null){
						//关闭链接
						session.close(true);
						protocol.setFlag(false);

					}else{
						//更新链路信息，保证客户端链接队列中保持最新的链接
						ClientQueueManager.updateClient(terminal, ioSession,devicetype,null,null);
						protocol.setFlag(true);
					}
				}
				break;


			default:
				logger.info("[client->server] [receive] 接收到异常协议类型,系统不进行处理");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

		return protocol;
	}

}
