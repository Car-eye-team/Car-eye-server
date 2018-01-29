/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.business;

import javax.jms.MapMessage;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.encoder.BbEncoder;
import com.careye.dscomm.encoder.BbEncoderUtil;
import com.careye.dscomm.socket.ClientQueueManager;
import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.ParseUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ReceiveMqBusiness    
 * 类描述：处理接入下行消息队列    
 * 创建人：zr    
 * 创建时间：2015-5-14 上午10:12:33    
 * 修改人：zr    
 * 修改时间：2015-5-14 上午10:12:33    
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
			if(msg != null){

				String terminal = msg.getString("terminal");
				int msgid = msg.getInt("msgid");
				int seq = msg.getInt("seq");
				int devicetype = msg.getInt("devicetype");

				//设备类型11也为部标设备
				if(devicetype == 11){
					devicetype = 22;
				}

				logger.info("[接收到协议解析服务器下行消息] ["+devicetype+"] ["+msgid+"] ["+terminal+"]");
				byte[] msgbytes = null;

				switch (devicetype) {


				case 22:  //部标协议
					try {
						if(msgid == 258){
							//结果 0 成功 1 失败
							int result = msg.getInt("result");
							if(result == 1){
								logger.info("["+terminal+"] 鉴权失败,断开连接");
								//回复通用应答
								msgbytes = BbEncoder.platformResponse(terminal, seq, msgid, result);
								IoSession session = ClientQueueManager.getClientIoSession(terminal);
								if(session != null && session.isConnected()){
									session.write(IoBuffer.wrap(msgbytes));
								}
								Thread.sleep(500);

								ClientQueueManager.deleteClientTerminal(terminal);
							}else{
								logger.info("["+terminal+"] 鉴权成功");
								//回复通用应答
								msgbytes = BbEncoder.platformResponse(terminal, seq, msgid, result);
							}

						}else{
							String msgbody = msg.getString("msgbody");
							byte[] msgbodyHex = null;
							if(msgbody!=null){
								msgbodyHex = ParseUtil.parseHexStrToByte(msgbody);				
							}
							//封装协议头、协议尾
							msgbytes = BbEncoderUtil.getProtocol808(terminal,msgid,msgbodyHex,seq);
						}

					} catch (Exception e) {
						e.printStackTrace();
						ExceptionUtil.getInfo(e);
					}
					break;


				case 29:  //出租车部标协议
					try {
						if(msgid == 258){
							//结果 0 成功 1 失败
							int result = msg.getInt("result");
							if(result == 1){
								logger.info("["+terminal+"] 鉴权失败,断开连接");
								//回复通用应答
								msgbytes = BbEncoder.platformResponse(terminal, seq, msgid, result);
								IoSession session = ClientQueueManager.getClientIoSession(terminal);
								if(session != null && session.isConnected()){
									session.write(IoBuffer.wrap(msgbytes));
								}
								Thread.sleep(500);
								ClientQueueManager.deleteClientTerminal(terminal);
							}else{
								logger.info("["+terminal+"] 鉴权成功");
								//回复通用应答
								msgbytes = BbEncoder.platformResponse(terminal, seq, msgid, result);
							}

						}else{
							String msgbody = msg.getString("msgbody");
							byte[] msgbodyHex = null;
							if(msgbody!=null){
								msgbodyHex = ParseUtil.parseHexStrToByte(msgbody);				
							}
							//封装协议头、协议尾
							msgbytes = BbEncoderUtil.getProtocol808(terminal,msgid,msgbodyHex,seq);
						}

					} catch (Exception e) {
						e.printStackTrace();
						ExceptionUtil.getInfo(e);
					}
					break;


				default:
					logger.info("接收到异常协议类型,系统不进行处理");
					break;
				}

				//消息发送
				if(msgbytes!= null){
					String msgHex = ParseUtil.parseByteToHexStr(msgbytes);
					logger.info("send ["+terminal+"] ["+msgHex+"]");
					IoSession session = ClientQueueManager.getClientIoSession(terminal);
					if(session != null && session.isConnected()){
						session.write(IoBuffer.wrap(msgbytes));
						logger.info("send ["+terminal+"] ["+msgid+"] ["+msgHex+"] success");

						//检查离线消息
						OfflineMessageBusiness.sendOfflineMessage(terminal, session, devicetype,msgid);
					}else {
						logger.info("设备终端链路信息不存在,写入离线消息缓存"); 
						OfflineMessageBusiness.addOfflineMessage(terminal, msgHex,msgid);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}

	}

}
