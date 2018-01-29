/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.careye.dscomm.constant.ConfigProperties;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：MinaServer    
 * 类描述：socket通讯 控制    
 * 创建人：zr    
 * 创建时间：2015-5-9 上午10:49:36    
 * 修改人：zr    
 * 修改时间：2015-5-9 上午10:49:36    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DsCommServer {

	private final static Logger logger = Logger.getLogger(DsCommServer.class);	

	private static int PORT = 5656;

	private NioSocketAcceptor acceptor = null;

	public DsCommServer(){
		acceptor = new NioSocketAcceptor();
	}

	/**
	 * 启动通信服务
	 */
	public void startServer(){

		//配置CodecFactory
		/*acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( 
				Charset.forName( "UTF-8" ), 
				LineDelimiter.WINDOWS, LineDelimiter.WINDOWS)));*/

		//设置过滤器
		ExecutorService executor = Executors.newCachedThreadPool();  
		acceptor.getFilterChain().addLast("executor", new ExecutorFilter(executor));
		
		acceptor.setHandler( new DsCommServerHandler() );
		
		//设置的是主服务监听的端口可以重用 
		acceptor.setReuseAddress(true);  
		acceptor.getSessionConfig().setKeepAlive(true);
		((SocketSessionConfig)acceptor.getSessionConfig()).setReuseAddress(true); //设置每一个非主监听连接的端口可以重用   

		acceptor.getSessionConfig().setReadBufferSize(32*1024);
		acceptor.getSessionConfig().setSendBufferSize(32*1024);
		acceptor.getSessionConfig().setReceiveBufferSize(10240);
		//((SocketSessionConfig) acceptor.getSessionConfig()).setTcpNoDelay(true);// 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出 

		//设置主服务监听端口的监听队列的最大值为100，如果当前已经有100个连接，再新的连接来将被服务器拒绝   
		acceptor.setBacklog(80000000);

		// 发呆时间
		acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 300 );

		try {

			PORT = ConfigProperties.PORT;
			acceptor.bind( new InetSocketAddress(PORT) );
		} catch (IOException e) {
			logger.info("异常：服务器绑定端口号：" + PORT + ",出现异常"+e.getMessage());
			e.printStackTrace();
		}    
		logger.info("DsCommServer服务器已启动，监听端口" + PORT + " ...");	

	}
}
