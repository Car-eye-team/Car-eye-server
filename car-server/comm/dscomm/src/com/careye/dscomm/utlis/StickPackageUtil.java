/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.utlis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.encoder.BbEncoderUtil;
import com.careye.dscomm.threadpool.ThreadPoolManager;
import com.careye.dscomm.threadpool.ThreadPoolTask;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：StickPackageUtil    
 * 类描述：粘包处理工具类    
 * 创建人：zr    
 * 创建时间：2015-5-27 下午03:58:31    
 * 修改人：zr    
 * 修改时间：2015-5-27 下午03:58:31    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StickPackageUtil {

	private final static Logger logger = Logger.getLogger(StickPackageUtil.class);

	public static Map<IoSession, StringBuffer> tcpmap = new HashMap<IoSession, StringBuffer>();

	public static Map<IoSession, String> cachemap = new HashMap<IoSession, String>();

	//public static Object anObject = new Object();

	/**
	 * 粘包处理
	 * @param session
	 * @param msg
	 */
	public static void stickPackage(IoSession session,String msg,int protocoltype){

		try {

			//转换成大写
			String data = msg.toUpperCase();
			switch (protocoltype) {
			//7E开头7E结尾 格式协议
			case 1:
				handle7E(session,data);
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("粘包异常"+e);
		}

	}

	/**
	 * 处理7E开头7E结尾 格式协议 粘包
	 * @param session
	 * @param data
	 */
	public static void handle7E(IoSession session,String data){

		try {

			StringBuffer tcp7EBuffer = tcpmap.get(session);
			if(tcp7EBuffer == null){
				tcp7EBuffer = new StringBuffer();
			}

			//判断tcp7EBuffer中的数据是否已7E开头，如果不是则先到的不是7E开头，而是中间段数据
			if(data.startsWith("7E") && !data.endsWith("7E")){
				if(!tcp7EBuffer.toString().startsWith("7E") && !tcp7EBuffer.toString().endsWith("7E")){
					data = String.format("%s%s",data,tcp7EBuffer.toString());
					tcp7EBuffer = new StringBuffer();
					tcpmap.put(session, tcp7EBuffer);

					//如果协议中上一段协议7E000000 又来了一条7E999999 则将上一条7E丢弃
				}else if(tcp7EBuffer.toString().startsWith("7E")){
					tcp7EBuffer = new StringBuffer();
				}
				//处理第一条协议7E00000000到了，中间又来了一条完整的7E000007E 的问题
			}else if(data.startsWith("7E") && data.endsWith("7E") && !data.contains("7E7E")){
				int re = BbEncoderUtil.checkCode(ParseUtil.parseHexStrToByte(data));
				if(re > 0){
					sendBusinessProcessing(session,data,re);
					data = "";
				}
			}

			////处理分包先到7E0000 再到00007E 最后到000000 的情况
			String cacheStr = cachemap.get(session);
			if(cacheStr != null){
				String outmsg = String.format("%s%s%s",tcp7EBuffer.toString(),data,cacheStr);
				int re = BbEncoderUtil.checkCode(ParseUtil.parseHexStrToByte(outmsg));
				if(re > 0){
					sendBusinessProcessing(session,outmsg,re);
				}

				tcp7EBuffer = new StringBuffer();
				tcpmap.put(session, tcp7EBuffer);
				cachemap.put(session, null);
				return;
			}

			tcp7EBuffer.append(data);

			String strBuffer = tcp7EBuffer.toString();
			if(!strBuffer.equals("7E") && strBuffer.endsWith("7E")){
				if(strBuffer.contains("7E7E")){
					String[] dataArr = strBuffer.split("7E7E");
					int len = dataArr.length;
					for(int i = 0;i<len;i++){
						String dataStr = dataArr[i];

						if(dataStr.startsWith("7E")){
							dataStr = String.format("%s%s",dataStr,"7E");
						}else if(dataStr.endsWith("7E")){
							dataStr = String.format("%s%s","7E",dataStr);
						}else{
							dataStr = String.format("%s%s%s","7E",dataStr,"7E");
						}
						int re = BbEncoderUtil.checkCode(ParseUtil.parseHexStrToByte(dataStr));
						sendBusinessProcessing(session,dataStr,re);
						tcp7EBuffer  = new StringBuffer();
					}
					//处理7E7E结尾
					if(strBuffer.endsWith("7E7E")){
						tcp7EBuffer.append("7E");  //最后加7E
					}

				}else{
					if(strBuffer.startsWith("7E") && strBuffer.endsWith("7E")){
						int re = BbEncoderUtil.checkCode(ParseUtil.parseHexStrToByte(tcp7EBuffer.toString()));
						if(re>0){
							sendBusinessProcessing(session,strBuffer,re);
							tcp7EBuffer  = new StringBuffer();
						}else{
							cachemap.put(session, data);
							String tcpstr = tcp7EBuffer.toString().replace(data, "");
							tcp7EBuffer  = new StringBuffer();
							tcp7EBuffer.append(tcpstr);
							tcpmap.put(session, tcp7EBuffer);
						}
					}
				}
			}else{
				//处理分包 00007E先到 而7E0000后到的情况
				String tcpstr = tcp7EBuffer.toString().replace(data, "");
				if(tcpstr.endsWith("7E") && data.startsWith("7E")){
					String outmsg = String.format("%s%s",data,tcpstr);
					if(outmsg.startsWith("7E") && outmsg.endsWith("7E")){
						int re = BbEncoderUtil.checkCode(ParseUtil.parseHexStrToByte(outmsg));
						sendBusinessProcessing(session,outmsg,re);
						tcp7EBuffer  = new StringBuffer();
						tcpmap.put(session, tcp7EBuffer);
					}
				}
			}

			tcpmap.put(session, tcp7EBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 发给业务进行处理
	 * @param session
	 * @param message
	 * @param type 针对协议头与协议尾一样的情况
	 */
	public static void sendBusinessProcessing(IoSession session,String message,int type){

		if(ThreadPoolManager.executor!=null){
			logger.info("[client->server] [threadpool] ===="+ThreadPoolManager.executor.getPoolSize()+"==="+ThreadPoolManager.executor.getLargestPoolSize()+"==="+ThreadPoolManager.queue.size());
			ThreadPoolManager.executor.execute(new ThreadPoolTask(session,message,type));
		}

	}

}
