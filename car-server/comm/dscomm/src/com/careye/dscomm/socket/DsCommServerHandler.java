/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.socket;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.ParseUtil;
import com.careye.dscomm.utlis.StickPackageUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：MinaServerHandler    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-9 上午10:55:27    
 * 修改人：zr    
 * 修改时间：2015-5-9 上午10:55:27    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DsCommServerHandler extends IoHandlerAdapter{

	private final static Logger logger = Logger.getLogger(DsCommServerHandler.class);

	public static Map<IoSession, Integer> protocoltype = new HashMap<IoSession, Integer>();
	
	public static IoSession lastsSession = null;

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		try {
			
			if(lastsSession != null){
				//如果上一次session与下一次session数据一样，做个延时处理
				if(session.equals(lastsSession)){
					Thread.sleep(1);
				}
			}
			lastsSession = session;
			
			IoBuffer ioBuffer = (IoBuffer)message;
			byte[] data = new byte[ioBuffer.limit()];
			ioBuffer.get(data); 
			String dataStr =  ParseUtil.parseByteToHexStr(data);
			
			logger.info("==============接收到数据================"+dataStr);

			/**
			 * 1:7E开头7E结尾 格式协议 (部标、雅迅)
			 */
			Integer ptype = protocoltype.get(session);

			if(dataStr.startsWith("7E")){
				ptype = 1;
			}
			
			protocoltype.put(session, ptype);

			if(ptype == null){
				ptype = 1;
			}
			//粘包处理
			StickPackageUtil.stickPackage(session, dataStr,ptype);
			
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable arg1)
	throws Exception {
		logger.info("检测到客户端链路异常: " + arg1.getMessage());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		try {
			/*ClientInfo clientInfo = ClientQueueManager.getClientTerminal(session);
			if(clientInfo != null){
				logger.info("[server->client] ["+clientInfo.getTerminal()+"] 消息发送成功");
			}else{
				logger.info("[server->client] ["+session+"] 消息发送成功");
			}*/
			
			logger.info("[server->client] ["+session+"] 消息发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("new session,["+session+"]");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("new user connection,["+session+"]");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

		logger.info("IDLE:"+session.getId() + "(SesssionID) 闲置的状态:-->" + status+",["+session+"]");

		ClientQueueManager.deleteIoSessionRelatedInfo(session);

		//删除队列终端信息
		ClientQueueManager.deleteClientSession(session);

		CloseFuture future = session.close(true);
		future.addListener(new IoFutureListener(){
			public void operationComplete(IoFuture future){
				if(future instanceof CloseFuture){
					((CloseFuture)future).setClosed();
					logger.info("have do the future set to closed");
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("检测到客户端关闭连接,["+session+"]");

		ClientQueueManager.deleteIoSessionRelatedInfo(session);

		//删除队列终端信息
		ClientQueueManager.deleteClientSession(session);

		CloseFuture future = session.close(true);
		future.addListener(new IoFutureListener(){
			public void operationComplete(IoFuture future){
				if(future instanceof CloseFuture){
					((CloseFuture)future).setClosed();
					logger.info("have do the future set to closed");
				}
			}
		});

	}

}
