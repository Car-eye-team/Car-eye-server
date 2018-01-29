/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.domain.ClientInfo;
import com.careye.dscomm.utlis.DateUtil;
import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.StickPackageUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ClientManage    
 * 类描述： 客户端链路信息管理   
 * 创建人：zr    
 * 创建时间：2015-5-9 下午01:02:04    
 * 修改人：zr    
 * 修改时间：2015-5-9 下午01:02:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ClientQueueManager {

	private final static Logger logger = Logger.getLogger(ClientQueueManager.class);

	//终端链路信息保存map
	//public static Map<String, ClientInfo> clientmap = Collections.synchronizedMap(new HashMap<String, ClientInfo>());  //线程同步
	public static Map<String, ClientInfo> clientmap = new HashMap<String, ClientInfo>();

	/**
	 * 添加终端链路信息
	 * @param terminal
	 * @param session
	 * @param devicetype
	 */
	public static void addClient(String terminal,IoSession session,int devicetype,String reserve1,String reserve2){

		logger.info("client queue->add:"+terminal);
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setSession(session);
		clientInfo.setTerminal(terminal);
		clientInfo.setAddtime(DateUtil.getSQLDate());
		clientInfo.setDevicetype(devicetype);
		clientInfo.setReserve1(reserve1);
		clientInfo.setReserve2(reserve2);
		clientmap.put(terminal, clientInfo);

		logger.info("client queue->number:"+clientmap.size());

	}

	/**
	 * 更新终端链路信息
	 * @param terminal
	 * @param session
	 * @param devicetype
	 */
	public static void updateClient(String terminal,IoSession session,int devicetype,String reserve1,String reserve2){

		logger.info("client queue->update:"+terminal);
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setSession(session);
		clientInfo.setTerminal(terminal);
		clientInfo.setDevicetype(devicetype);
		clientInfo.setAddtime(DateUtil.getSQLDate());
		clientInfo.setReserve1(reserve1);
		clientInfo.setReserve2(reserve2);
		clientmap.put(terminal, clientInfo);

	}

	/**
	 * 删除终端链路信息
	 * @param terminal
	 * @param session
	 */
	public static void deleteClient(String terminal,IoSession session){
		logger.info("client queue->delete:"+terminal);
		clientmap.remove(terminal);
		session.close(true);
		deleteIoSessionRelatedInfo(session);
		logger.info("client queue->number:"+clientmap.size());
	}

	/**
	 * 根据设备号删除链路信息
	 * @param terminal
	 */
	public static void deleteClientTerminal(String terminal){
		ClientInfo clientInfo = clientmap.get(terminal);
		if(clientInfo != null){
			deleteIoSessionRelatedInfo(clientInfo.getSession());
			clientInfo.getSession().close(true);
			clientmap.remove(terminal);
		}
	}

	/**
	 * 根据链路信息删除队列终端信息
	 * @param session
	 */
	@SuppressWarnings({ "unchecked"})
	public static void deleteClientSession(IoSession session){

		try {
			Set set = clientmap.entrySet();
			Iterator it = set.iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry)it.next();
				ClientInfo clientInfo = (ClientInfo) entry.getValue();
				if(clientInfo.getSession().equals(session)){
					logger.info("client queue->delete:"+clientInfo.getTerminal());
					clientmap.remove(clientInfo.getTerminal());
					deleteIoSessionRelatedInfo(session);

					clientInfo.getSession().close(true);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
		logger.info("client queue->number:"+clientmap.size());
	}

	/**
	 * 根据设备号获取终端链路信息
	 * @param terminal
	 * @return
	 */
	public static IoSession getClientIoSession(String terminal){

		IoSession session = null;
		ClientInfo clientInfo = clientmap.get(terminal);
		if(clientInfo != null){
			session = clientInfo.getSession();
		}
		logger.info("client queue->"+terminal+"->search results:"+session);

		return session;
	}

	/**
	 * 根据设备号获取链路信息
	 * @param terminal
	 * @return
	 */
	public static ClientInfo getClientInfo(String terminal){
		ClientInfo clientInfo = clientmap.get(terminal);
		return clientInfo;
	}

	/**
	 * 根据链路信息获取终端设备号
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ClientInfo getClientTerminal(IoSession session){
		ClientInfo cInfo = null;
		try {
			Set set = clientmap.entrySet();
			Iterator it = set.iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry)it.next();
				ClientInfo clientInfo = (ClientInfo) entry.getValue();
				if(clientInfo.getSession().equals(session)){
					cInfo = clientInfo;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
		return cInfo;
	}

	/**
	 * 检查5分钟没有更新链接的终端删除
	 */
	@SuppressWarnings({ "unchecked"})
	public static void clientMapCheck(){

		try {
			Set set = clientmap.entrySet();
			Iterator it = set.iterator();
			List<String> list = new ArrayList<String>();
			while(it.hasNext()){

				Map.Entry<String, ClientInfo> entry=(Map.Entry<String, ClientInfo>)it.next();
				ClientInfo clientInfo = entry.getValue();
				int sec = DateUtil.secBetween(DateUtil.getSQLDate(), clientInfo.getAddtime());
				//超过3分钟断开连接并从队列中删除
				if(sec > 300){
					logger.info("["+clientInfo.getTerminal()+"] 链路连接超时 ["+sec+"秒],删除并断开链路");
					list.add(clientInfo.getTerminal());
					deleteIoSessionRelatedInfo(clientInfo.getSession());
					clientInfo.getSession().close(true);
				}
			}

			for (String str : list) {
				clientmap.remove(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
	}

	/**
	 * 删除链路关联信息
	 * @param session
	 */
	public static void deleteIoSessionRelatedInfo(IoSession session){
		//粘包session
		StickPackageUtil.tcpmap.remove(session);

		//协议类型session
		DsCommServerHandler.protocoltype.remove(session);

	}

}
