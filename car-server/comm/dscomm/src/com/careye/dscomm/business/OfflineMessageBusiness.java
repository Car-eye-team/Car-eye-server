/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.redis.RedisManager;
import com.careye.dscomm.utlis.DateUtil;
import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.ParseUtil;
import com.careye.redis.domain.OfflineMessage;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：OfflineMessageBusiness    
 * 类描述：离线消息业务处理    
 * 创建人：zr    
 * 创建时间：2015-5-19 上午09:27:28    
 * 修改人：zr    
 * 修改时间：2015-5-19 上午09:27:28    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class OfflineMessageBusiness {

	private final static Logger logger = Logger.getLogger(OfflineMessageBusiness.class);

	/**
	 * 加入离线消息
	 * @param terminal 终端设备号
	 * @param msgHex 消息
	 * @param msgid 消息ID
	 */
	@SuppressWarnings("unchecked")
	public static void addOfflineMessage(String terminal,String msgHex,int msgid){

		try {
			String key = terminal+"_offlineMessage";

			//获取是否存在当前设备的离线消息
			List<OfflineMessage> list =  (List<OfflineMessage>) RedisManager.getInstance().getObject(key);
			if(list == null){
				list = new ArrayList<OfflineMessage>();
			}

			OfflineMessage oMessage = new OfflineMessage();
			oMessage.setMsghex(msgHex);
			oMessage.setTerminal(terminal);
			oMessage.setAddtime(DateUtil.getSQLDate());
			oMessage.setMsgid(msgid);
			list.add(oMessage);
			//加入至缓存服务器中
			RedisManager.getInstance().setObject(key, list);


		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}

	}

	/**
	 * 发送离线消息
	 * @param terminal
	 * @param session
	 * @param devicetype
	 * @param msgid
	 */
	@SuppressWarnings("unchecked")
	public static void sendOfflineMessage(String terminal,IoSession session,int devicetype,int msgid){

		try {
			boolean flag = false;
			switch (devicetype) {
			case 22: //部标协议
				if(msgid == 258){
					flag = true;
				}
				break;
				
			case 29: //出租车部标协议
				if(msgid == 258){
					flag = true;
				}
				break;

			default:
				break;
			}

			if(flag){
				String key = terminal+"_offlineMessage";

				//获取是否存在当前设备的离线消息
				List<OfflineMessage> list =  (List<OfflineMessage>) RedisManager.getInstance().getObject(key);
				if(list != null){

					logger.info("[设备号:"+terminal+"] 总共有["+list.size()+"条离线消息]");
					for (OfflineMessage offlineMessage : list) {
						try {
							String msgHex = offlineMessage.getMsghex();
							String addTime = offlineMessage.getAddtime();
							//判断离线消息是否超过7天，超过则不进行发送(zr 2016-03-29)
							int day = DateUtil.daysBetween(addTime, DateUtil.getSQLDate());
							if(day <= 7){
								byte[] msgbytes = ParseUtil.parseHexStrToByte(msgHex);
								session.write(IoBuffer.wrap(msgbytes));
								logger.info("发送离线消息 [设备号:"+terminal+"] [消息ID:"+offlineMessage.getMsgid()+"] ["+msgHex+"] 成功");
							}
						} catch (Exception e) {
							e.printStackTrace();
							ExceptionUtil.getInfo(e);
						}
						Thread.sleep(500);
					}
					//删除设备离线消息
					RedisManager.getInstance().delObject(key);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
	}

}
